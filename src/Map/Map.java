package game;

import GameCharacters.MetaHuman;
import GameCharacters.SuperHeroe;
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
     * List of the game characters
     */
    private LinkedList<MetaHuman> gameCharacters;

    /**
     * Singleton instance of the map
     */
    private static Map singletonInstance = null;

    /**
     * Graph of the map
     */
    private Grafo graph;

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

        this.gameCharacters = new LinkedList<>();

        //Creating the maze
        createGraph();
        graph.warshall();
        graph.floyd();
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
     *  Set walls for the maze
     */
    private void setWalls() {
        for (int node = 0; node < dimX * dimY; node++) {
            if (node >= dimY) {         //N
                walls.addLast(new Walls(node, node - dimY));
            }
            if (node % dimY < dimY - 1) {     //E
                this.walls.addLast(new Walls(node, node + 1));
            }
            if (node < (dimY * (dimX - 1))) { //S
                walls.addLast(new Walls(node, node + dimY));
            }
            if (node % dimY > 0) {          //O
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
            square square1 = getSquare(wall.getSrc());
            square square2 = getSquare(wall.getDst());
            if (square1.getNodeNumber() != square2.getNodeNumber()) {
                graph.nuevoArco(square1.getNumberOfsquare(), square2.getNumberOfsquare(), 1);
                graph.nuevoArco(square2.getNumberOfsquare(), square1.getNumberOfsquare(), 1);
                setAllMarks(square1.getNodeNumber(), square2.getNodeNumber());
            }
            walls.remove(wall);

        }
    }

    public void simulate(){
        int turn = 0;
        while(!doorMan.isGateOpened() && turn<50) {
            for (MetaHuman gc : gameCharacters) {
                gc.useWeapon();
            }
        turn++;
            for (MetaHuman gc : gameCharacters) {
                System.out.println(gc.toString());
            }
        }
    }
    /**
     * Method to add a character to the game
     */
    public void addCharacter(MetaHuman m){
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


    /**
     * Getter of square matrix
     * @return a [rows][columns] game.Square matrix
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
