package Map;

import GameCharacters.GameCharacter;
import GameCharacters.SuperHeroe;
import Tools.Dir;
import Tools.GenAleatorios;
import datastructures.Grafo;

import java.util.LinkedList;

public class Map {

    /**
     * Matrix of squares
     */
    private Square[][] map;

    /**
     * number of columns
     */
    private int columns;

    /**
     * Number of rows
     */
    private int rows;

    /**
     * The doorman
     */
    private DoorMan doorMan;

    /**
     * Daily planet's number of square
     */
    private int dailyPlanet;

    /**
     * List of the Map characters
     */
    private LinkedList<GameCharacter> gameCharacters;

    /**
     * Singleton instance of the map
     */
    private static Map singletonInstance = null;

    /**
     * Graph of the map
     */
    private Grafo graph;

    /**
     * List of walls
     */
    private LinkedList<Walls> walls;

    /**
     * Map constructor
     * @param rows number of rows
     * @param columns number of columns
     * @param dailyPlanet number of the dailyPlanet's square
     * @param depth number of depth
     */
    private Map(int rows, int columns, int dailyPlanet, int depth){

        this.map = new Square[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.dailyPlanet = dailyPlanet;

        int k = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = new Square(k);
                k++;
            }
        }

        this.doorMan = new DoorMan(depth);
        this.walls = new LinkedList<>();
        this.gameCharacters = new LinkedList<>();

        //Creating the maze
        createGraph();
        createMaze();
        graph.warshall();
        graph.floyd();
        erasePercentageOfWalls();

    }



    public static Map getSingleton(){
        return singletonInstance;
    }

    public static Map getSingletonInstance(int rows, int columns, int dailyPlanet, int depth){
        if(singletonInstance == null){
            singletonInstance = new Map(rows, columns, dailyPlanet, depth);
        }
        return singletonInstance;
    }

    /**
     * Creates all the nodes of the map
     */
    private void createGraph() {
        for (int node = 0; node < columns * rows; node++) {
            this.graph.nuevoNodo(node);
        }
    }

    /**
     * Set the marks for the initial algorythms
     */
    private void setNodeNumbersInicial() {
        for (int i = 0; i < rows * columns; i++) {
           Square s = getSquare(i);
            s.setNodeNumber(i);
        }
    }

    /**
     * Set the same mark to the rooms that has the dstValue mark
     */
    private void setAllMarks(int srcValue, int dstValue) {
        Square square;
        for (int i = 0; i < this.rows * this.columns ; i++) {
            square = getSquare(i);
            if (square.getNodeNumber() == dstValue) {
                square.setNodeNumber(srcValue);
            }
        }
    }
    /**
     *  Set walls for the maze
     */
    private void setWalls() {
        for (int node = 0; node < rows * columns ; node++) {
            if (node >= columns ) {         //N
                walls.addLast(new Walls(node, node - columns ));
            }
            if (node % columns < columns - 1) {     //E
                this.walls.addLast(new Walls(node, node + 1));
            }
            if (node < (columns * (rows - 1))) { //S
                walls.addLast(new Walls(node, node + columns ));
            }
            if (node % columns > 0) {          //O
                walls.addLast(new Walls(node, node - 1));
            }

        }
    }
    /**
     * Create the maze on the map
     */
    private void createMaze() {
        setNodeNumbersInicial();
        setWalls();
        while (!walls.isEmpty()) {
            int gen = GenAleatorios.generarNumero(walls.size());
            Walls wall = walls.get(gen);
            Square square1 = getSquare(wall.getSrc());
            Square square2 = getSquare(wall.getDst());
            if (square1.getNodeNumber() != square2.getNodeNumber()) {
                graph.nuevoArco(square1.getNumber(), square2.getNumber(), 1);
                graph.nuevoArco(square2.getNumber(), square1.getNumber(), 1);
                setAllMarks(square1.getNodeNumber(), square2.getNodeNumber());
            }
            walls.remove(wall);

        }
    }

    /**
     * Erase the 5% of walls of the maze
     */
    private void erasePercentageOfWalls() {
        /* 5% of walls */
        int percentage = (rows * columns * 5) / 100;
        int i = 0;
        while (i < percentage) {
            int gen = GenAleatorios.generarNumero(rows * columns);
            Square sq = getSquare(gen);
            if (conditionForPercentage(sq, Dir.N)) {
                graph.nuevoArco(gen, gen - columns, 1);
                graph.nuevoArco(gen - columns, gen, 1);
                i++;
            } else {
                if (conditionForPercentage(sq, Dir.S)) {
                    graph.nuevoArco(gen, gen + columns, 1);
                    graph.nuevoArco(gen + columns, gen, 1);
                    i++;
                } else {
                    if (conditionForPercentage(sq, Dir.E)) {
                        graph.nuevoArco(gen, gen + 1, 1);
                        graph.nuevoArco(gen + 1, gen, 1);
                        i++;
                    } else {
                        if (conditionForPercentage(sq, Dir.O)) {
                            graph.nuevoArco(gen, gen - 1, 1);
                            graph.nuevoArco(gen - 1, gen, 1);
                            i++;
                        }
                    }
                }
            }
        }
        graph.floyd();
        graph.warshall();
    }

    /**
     * @param sq1 source sq
     * @param sq2 destiny sq
     * @return a boolean that says if there are a wall between two sqs( if you can move from sq1 to sq2)
     */
    private boolean wall(int sq1, int sq2) {
        return !graph.adyacente(sq1, sq2);
    }

    /**
     * Says if a character makes a Movement from a room in any dir
     * @param square the square the character stays
     * @param dir the direction of the Movement
     * @return if it can be possible
     */
    public boolean availableMovement(int square, Dir dir) {
        boolean available = false;
        switch (dir) {
            case N:
                if (square >= columns  && !wall(square, square - columns )) {
                    available = true;
                }
                break;
            case S:
                if (square < columns  * (rows  - 1) && !wall(square, square + columns )) {
                    available = true;
                }
                break;
            case O:
                if (square % columns  > 0 && !wall(square, square - 1)) {
                    available = true;
                }
                break;
            case E:
                if (square % columns  < (columns  - 1) && !wall(square, square + 1)) {
                    available = true;
                }
                break;

        }
        return available;
    }

    /**
     * Calls the "Path" method of the graph
     *
     * @param source  source room
     * @param destiny destiny room
     * @return A linkedList with the path
     */
    public LinkedList<Integer> getPath(int source, int destiny) {
        return this.graph.path(source, destiny);
    }
    
    /**
     * Condition for the 5% algorythm
     */
    private boolean conditionForPercentage(Square sq, Dir dir) {
        boolean solution = false;
        int n = sq.getNumber();
        if (!availableMovement(n, dir)) {
            switch (dir) {
                case N:
                    if (n >= columns) {
                        if (getPath(n, n - columns).size() > 3) {
                            solution = true;
                        }
                    }
                    break;

                case S:
                    if (getRowOfSquare(n) < rows  - 1) {
                        if (getPath(n, n + columns).size() > 3) {
                            solution = true;
                        }
                    }
                    break;
                case E:
                    if (getColumnOfSquare(n) < columns - 1) {
                        if (getPath(n, n + 1).size() > 3) {
                            solution = true;
                        }
                    }
                    break;
                case O:
                    if (getColumnOfSquare(n) > 0) {
                        if (getPath(n, n - 1).size() > 3) {
                            solution = true;
                        }
                    }
                    break;
            }
        }
        return solution;
    }

    public void simulate(){
        int turn = 0;
        while(!doorMan.isGateOpened() && turn<50) {
            for (GameCharacter gc : gameCharacters) {
                gc.useWeapon();
            }
        turn++;
            for (GameCharacter gc : gameCharacters) {
                System.out.println(gc.toString());
            }
        }
    }
    /**
     * Method to add a character to the Map
     */
    public void addCharacter(GameCharacter m){
        this.gameCharacters.add(m);
    }

    /**
     * Getter of columns value
     * @return columns
     */
    public int getColumns() {
        return columns;
    }


    /**
     * Getter of rows value
     * @return rows
     */
    public int getRows() {
        return rows;
    }


    public int getRowOfSquare(int nSquare){
        return nSquare / columns;
    }


    public int getColumnOfSquare(int nSquare){
        return nSquare % columns;
    }
    /**
     * Getter of square matrix
     * @return a [rows][columns] Map.Square matrix
     */
    public Square[][] getMap() {
        return map;
    }

    /**
     * Setter of the columns value
     * @param columns number of columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }


    /**
     * Setter of the rows value
     * @param rows number of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    public DoorMan getDoorMan() {
        return doorMan;
    }

    public int getDailyPlanet() {
        return dailyPlanet;
    }

    /*
            TODO
             */
    public void spendWeaponsD1(){
        int k = 0;
        int numWeaponsSquares = 60;
        Weapon[] weaponsSquares = {new Weapon("Mjolnir",29), new Weapon("Anillo",1),
                new Weapon("Garra",27), new Weapon("Weapondura",3), new Weapon("Red",25), 
                new Weapon("Escudo",5), new Weapon("Lucille",23), new Weapon("Lawgiver",7), 
                new Weapon("GuanteInfinito",21), new Weapon("LazoVerdad",9),
                new Weapon("CadenaFuego",19), new Weapon("Capa",11),
                new Weapon("Flecha",17), new Weapon("Tridente",13),
                new Weapon("Antorcha",15), new Weapon("Baston",28),
                new Weapon("Latigo",2), new  Weapon("MazaOro",26),
                new Weapon("CampoMagnetico",4), new Weapon("Tentaculo",24),
                new Weapon("CampoEnergia",6), new Weapon("Cetro",22),
                new Weapon("RayoEnergia",8), new Weapon("Laser",20), new Weapon("Bola",10), 
                new Weapon("Espada",18),  new Weapon("Sable",12),  new Weapon("Acido",16),
                new Weapon("Gema",14), new Weapon("Nullifier",23), new Weapon("Mjolnir",1), 
                new Weapon("Anillo",29), new Weapon("Garra",3), new Weapon("Weapondura",27),
                new Weapon("Red",5),  new Weapon("Escudo",25), new Weapon("Lucille",7), 
                new Weapon("Lawgiver",23), new Weapon("GuanteInfinito",9),
                new Weapon("LazoVerdad",21), new Weapon("CadenaFuego",11),
                new Weapon("Capa",19), new Weapon("Flecha",13), new Weapon("Tridente",17), 
                new Weapon("Antorcha",28), new Weapon("Baston",15), new Weapon("Latigo",26), 
                new Weapon("MazaOro",2), new Weapon("CampoMagnetico",24),
                new Weapon("Tentaculo",4), new Weapon("CampoEnergia",22),
                new Weapon("Cetro",6), new Weapon("RayoEnergia",20), new Weapon("Laser",8), 
                new Weapon("Bola",18), new Weapon("Espada",10), new Weapon("Sable",16), 
                new Weapon("Acido",12), new Weapon("Gema",1), new Weapon("Nullifier",3)};

        int[] squares = {1, 2, 8, 14, 15, 21, 27, 35, 28, 29, 33, 34};
        for (int i = 0; i < squares.length && k < numWeaponsSquares; i++) {
            Square s = getSquare(squares[i]);
            for (int j = 0; j < 5; j++) {
                s.saveWeapon(weaponsSquares[k]);
                k++;
            }
        }
    }

    /**
     * Method to print the map info on terminal
     */
    public void show(){
        System.out.printf("(Mapa:" + Integer.toString(dailyPlanet)+")");
        System.out.println(doorMan.toString());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println(map[i][j].toString());
            }
        }

    }

    public Square getSquare(int nSquare) {
        Square s = null;
        try{
            s = map[nSquare / columns][nSquare % columns];
        }catch (NullPointerException e){
            System.out.println("The square that you are searching doesn't exists");
        }
        return s;
    }

    public Square getSquare(int row, int col){
        Square s = null;
        try {
            s = map[row][col];
        }catch (NullPointerException e){
            System.err.println("The square that you are searching doesn't exists");
        }
        return s;
    }

    static public void main(String[] args){
        Map map = getSingletonInstance(6,6,35,4);
        map.show();
        /*
        Parte 1:
        Funciona el reparto de llaves y la creación de salas
         */

        SuperHeroe A = new SuperHeroe("Joe","D",35);
        SuperHeroe B = new SuperHeroe("BB", "B", 35);
        SuperHeroe C = new SuperHeroe("CECCCECECEC", "C", 35);

        Weapon[] weaponsSquares = {new Weapon("Mjolnir",29), new Weapon("Anillo",1),
                new Weapon("Garra",27), new Weapon("Weapondura",3), new Weapon("Red",25),
                new Weapon("Escudo",5), new Weapon("Lucille",23), new Weapon("Lawgiver",7),
                new Weapon("GuanteInfinito",21), new Weapon("LazoVerdad",9),
                new Weapon("CadenaFuego",19), new Weapon("Capa",11),
                new Weapon("Flecha",17), new Weapon("Tridente",13),
                new Weapon("Antorcha",15), new Weapon("Baston",28),
                new Weapon("Latigo",2), new  Weapon("MazaOro",26),
                new Weapon("CampoMagnetico",4), new Weapon("Tentaculo",24),
                new Weapon("CampoEnergia",6), new Weapon("Cetro",22),
                new Weapon("RayoEnergia",8), new Weapon("Laser",20), new Weapon("Bola",10),
                new Weapon("Espada",18),  new Weapon("Sable",12),  new Weapon("Acido",16),
                new Weapon("Gema",14), new Weapon("Nullifier",23), new Weapon("Mjolnir",1),
                new Weapon("Anillo",29), new Weapon("Garra",3), new Weapon("Weapondura",27),
                new Weapon("Red",5),  new Weapon("Escudo",25), new Weapon("Lucille",7),
                new Weapon("Lawgiver",23), new Weapon("GuanteInfinito",9),
                new Weapon("LazoVerdad",21), new Weapon("CadenaFuego",11),
                new Weapon("Capa",19), new Weapon("Flecha",13), new Weapon("Tridente",17),
                new Weapon("Antorcha",28), new Weapon("Baston",15), new Weapon("Latigo",26),
                new Weapon("MazaOro",2), new Weapon("CampoMagnetico",24),
                new Weapon("Tentaculo",4), new Weapon("CampoEnergia",22),
                new Weapon("Cetro",6), new Weapon("RayoEnergia",20), new Weapon("Laser",8),
                new Weapon("Bola",18), new Weapon("Espada",10), new Weapon("Sable",16),
                new Weapon("Acido",12), new Weapon("Gema",1), new Weapon("Nullifier",3)};
        Weapon aux = weaponsSquares[0];

        Weapon[] ws = {new Weapon("La más baja",1),new Weapon("2",2),new Weapon("3",3),
                new Weapon("4",4),new Weapon("5",5),new Weapon("6",6),new Weapon("7",7),new Weapon("8",8),new Weapon("9",9)};
        for (Weapon w: ws) {
            A.getwTree().insertData(w);
            B.getwTree().insertData(w);
            C.getwTree().insertData(w);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("PROBANDO A BUSCAR EL ARMA MÁS GRANDE");
        System.err.println("PROBANDO A BUSCAR EL ARMA MÁS GRANDE");
        System.err.println("PROBANDO A BUSCAR EL ARMA MÁS GRANDE");
        System.err.println("PROBANDO A BUSCAR EL ARMA MÁS GRANDE");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        A.getwTree().showInOrder();

        System.out.println("\n\n\n\n");
        System.out.println("TODAS LAS ARMAS DEL HÉROE");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        A.getwTree().insertData(new Weapon("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA",999));
        System.out.println(A.biggestWeapon());
        A.getwTree().delete(new Weapon("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA",999));
        System.out.println(A.biggestWeapon());

        /*
        Parte 2:
        El árbol funciona de momento correctamente
         */
        map.addCharacter(A);
        map.addCharacter(B);
        map.addCharacter(C);
        for (Weapon w: weaponsSquares) {
            A.getwTree().insertData(w);
            B.getwTree().insertData(w);
            C.getwTree().insertData(w);
        }
        map.simulate();

    }
}
