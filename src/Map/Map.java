package Map;

import Game.Game;
import GameCharacters.GameCharacter;
import Tools.Dir;
import Tools.GenAleatorios;
import Datastructures.Grafo;

import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Implementation of the Map.
 *
 * @author José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 3.0
 * This is where all the action happens.
 * In the version 2.0, We have added the graph,
 * the walls and some methods that are needed
 * for the second delivery.
 * We have also modified the constructor.
 * In the version 3.0, we have added some methods useful to calculate the most frequented rooms
 * in order to deliver the weapons properly. We have also added a winnersSquare.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class Map {

    /**
     * Singleton design pattern instance of the map.
     */
    private static Map singletonInstance = null;
    /**
     * The squares's matrix that forms the map.
     */
    private Square[][] map;
    /**
     * The columns of the matrix.
     */
    private int columns;
    /**
     * The rows of the matrix.
     */
    private int rows;
    /**
     * The last room of the map.
     */
    private DoorMan doorMan;
    /**
     * The last room of the map.
     */
    private int dailyPlanet;
    /**
     * List that contains all the characters of the game.
     */
    private LinkedList<GameCharacter> gameCharacters;
    /**
     * Map's graph.
     */
    private Grafo graph;

    /**
     * The list of walls.
     */
    private LinkedList<Walls> walls;

    /**
     * Attribute that contains the string with the percentage of walls.
     */
    private String mapWithPercentageOfWalls;

    /**
     * Additional square where the winners are going to be placed.
     */
    private Square winnersSquare;

    /**
     * List that contains all the possible paths for the shortcuts.
     */
    private LinkedList<LinkedList<Integer>> possiblePaths;

    /**
     * Array used to store the frequency of steps in of every room.
     */
    private int[] mostFreqRooms;

    /**
     * Private constructor according to the singleton design pattern.
     * It creates the map, all the squares and the corresponding maze using some graph methods.
     *
     * @param rows        The number of rows of the matrix.
     * @param columns     The number of columns of the matrix.
     * @param dailyPlanet The last room of the matrix.
     * @param depth       The depth the tree requires for the portal to be opened.
     */
    public Map(int rows, int columns, int dailyPlanet, int depth) {

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
        this.graph = new Grafo();
        createGraph();
        createMaze();
        graph.warshall();
        graph.floyd();
        System.out.print(matrixString());
        this.mapWithPercentageOfWalls = matrixString();
        erasePercentageOfWalls();
        this.winnersSquare = new Square(1111);
        this.possiblePaths = new LinkedList<>();
        this.mostFreqRooms = new int[this.dailyPlanet + 1];
        LinkedList<Integer> partialSolution = new LinkedList<>();
        mostFrequentedRooms(0, dailyPlanet, partialSolution);
        spendWeapons();

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
     * Method that sets the numbers of the nodes for the initial algorithms.
     */
    private void setNodeNumbersInicial() {
        for (int i = 0; i < rows * columns; i++) {
            Square s = getSquare(i);
            s.setNodeNumber(i);
        }
    }

    /**
     * Method that equals the values of a number of rooms.
     * @param srcValue the value we want to have.
     * @param dstValue the value we want to change.
     */
    private void setAllMarks(int srcValue, int dstValue) {
        Square square;
        for (int i = 0; i < this.rows * this.columns; i++) {
            square = getSquare(i);
            if (square.getNodeNumber() == dstValue) {
                square.setNodeNumber(srcValue);
            }
        }
    }

    /**
     * Creates the walls of the map.
     */
    private void setWalls() {
        for (int node = 0; node < rows * columns; node++) {
            if (node >= columns) {         //N
                walls.addLast(new Walls(node, node - columns));
            }
            if (node % columns < columns - 1) {     //E
                this.walls.addLast(new Walls(node, node + 1));
            }
            if (node < (columns * (rows - 1))) { //S
                walls.addLast(new Walls(node, node + columns));
            }
            if (node % columns > 0) {          //W
                walls.addLast(new Walls(node, node - 1));
            }

        }
    }

    /**
     * Creates the maze of the map.
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
     * Erases the 5% of the walls of the maze.
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
                        if (conditionForPercentage(sq, Dir.W)) {
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
     * Method that determines whether you can go from sq1 to sq2 or not.
     *
     * @param sq1 source square.
     * @param sq2 destiny square.
     * @return a boolean that says if the two squares are adjacent or not.
     */
    private boolean wall(int sq1, int sq2) {
        return !graph.adyacente(sq1, sq2);
    }

    /**
     * Method that determines if a character can move to the desired direction.
     *
     * @param square the square where the character is currently at.
     * @param dir    the direction where we want to move.
     * @return a boolean that says if the character can move or not.
     */
    public boolean availableMovement(int square, Dir dir) {
        boolean available = false;
        switch (dir) {
            case N:
                if (square >= columns && !wall(square, square - columns)) {
                    available = true;
                }
                break;
            case S:
                if (square < columns * (rows - 1) && !wall(square, square + columns)) {
                    available = true;
                }
                break;
            case W:
                if (square % columns > 0 && !wall(square, square - 1)) {
                    available = true;
                }
                break;
            case E:
                if (square % columns < (columns - 1) && !wall(square, square + 1)) {
                    available = true;
                }
                break;

        }
        return available;
    }

    /**
     * Calls the path method of the graph.
     *
     * @param source  source room.
     * @param destiny destiny room.
     * @return a LinkedList that contains the path that makes the character go from source to destiny.
     */
    public LinkedList<Integer> getPath(int source, int destiny) {
        return this.graph.path(source, destiny);
    }

    /**
     * Condition for the 5% algorithm.
     * @param sq the square we are in and trying to check.
     * @param dir the direction we want to move.
     * @return whether we can remove the wall or not.
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
                    if (getRowOfSquare(n) < rows - 1) {
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
                case W:
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

    /**
     * Method that adds a character into the map.
     * @param m the character to introduce.
     */
    public void addCharacter(GameCharacter m) {
        this.gameCharacters.add(m);
    }

    /**
     * Getter of the columns attribute.
     *
     * @return the columns attribute..
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Setter of the columns attribute.
     *
     * @param columns the columns attribute.
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Getter of the rows attribute.
     *
     * @return the rows attribute..
     */
    public int getRows() {
        return rows;
    }

    /**
     * Setter of the rows attribute.
     *
     * @param rows the rows attribute..
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Method that returns the row of the square.
     *
     * @param nSquare the square we are using.
     * @return the row the square belongs to.
     */
    public int getRowOfSquare(int nSquare) {
        return nSquare / columns;
    }

    /**
     * Method that returns the column of the square.
     *
     * @param nSquare the square we are using.
     * @return the column the square belongs to.
     */
    public int getColumnOfSquare(int nSquare) {
        return nSquare % columns;
    }

    /**
     * Getter of the squares's matrix. The map itself.
     *
     * @return a [rows][columns] squares matrix.
     */
    public Square[][] getMap() {
        return map;
    }

    /**
     * Method that returns the attribute doorMan.
     *
     * @return the doorMan attribute..
     */
    public DoorMan getDoorMan() {
        return doorMan;
    }

    /**
     * Method that returns the attribute dailyPlanet.
     *
     * @return the dailyPlanet attribute.
     */
    public int getDailyPlanet() {
        return dailyPlanet;
    }

    /**
     * Method that creates an array with all the weapons.
     * @return the corresponding array containing the weapons.
     */
    public Weapon[] spendWeaponsD1() {
        return new Weapon[]{new Weapon("Mjolnir", 29), new Weapon("Anillo", 1),
                new Weapon("Garra", 27), new Weapon("Armadura", 3), new Weapon("Red", 25),
                new Weapon("Escudo", 5), new Weapon("Lucille", 23), new Weapon("Lawgiver", 7),
                new Weapon("GuanteInfinito", 21), new Weapon("LazoVerdad", 9),
                new Weapon("CadenaFuego", 19), new Weapon("Capa", 11),
                new Weapon("Flecha", 17), new Weapon("Tridente", 13),
                new Weapon("Antorcha", 15), new Weapon("Baston", 28),
                new Weapon("Latigo", 2), new Weapon("MazaOro", 26),
                new Weapon("CampoMagnetico", 4), new Weapon("Tentaculo", 24),
                new Weapon("CampoEnergia", 6), new Weapon("Cetro", 22),
                new Weapon("RayoEnergia", 8), new Weapon("Laser", 20), new Weapon("Bola", 10),
                new Weapon("Espada", 18), new Weapon("Sable", 12), new Weapon("Acido", 16),
                new Weapon("Gema", 14), new Weapon("Nullifier", 23), new Weapon("Mjolnir", 1),
                new Weapon("Anillo", 29), new Weapon("Garra", 3), new Weapon("Armadura", 27),
                new Weapon("Red", 5), new Weapon("Escudo", 25), new Weapon("Lucille", 7),
                new Weapon("Lawgiver", 23), new Weapon("GuanteInfinito", 9),
                new Weapon("LazoVerdad", 21), new Weapon("CadenaFuego", 11),
                new Weapon("Capa", 19), new Weapon("Flecha", 13), new Weapon("Tridente", 17),
                new Weapon("Antorcha", 28), new Weapon("Baston", 15), new Weapon("Latigo", 26),
                new Weapon("MazaOro", 2), new Weapon("CampoMagnetico", 24),
                new Weapon("Tentaculo", 4), new Weapon("CampoEnergia", 22),
                new Weapon("Cetro", 6), new Weapon("RayoEnergia", 20), new Weapon("Laser", 8),
                new Weapon("Bola", 18), new Weapon("Espada", 10), new Weapon("Sable", 16),
                new Weapon("Acido", 12), new Weapon("Gema", 1), new Weapon("Nullifier", 3)};

    }

    /**
     * Performs a depth-first algorithm to take all the possible paths and determines
     * which rooms are the most frequented ones.
     *
     * @param start           initial room.
     * @param finish          final room.
     * @param partialSolution list to save the partial Path.
     */
    private void mostFrequentedRooms(int start, int finish, LinkedList<Integer> partialSolution) {
        Integer nextRoom;
        TreeSet<Integer> adySet = new TreeSet<>();
        this.graph.adyacentes(start, adySet);
        if (start == finish) {
            LinkedList<Integer> solution = (LinkedList<Integer>) partialSolution.clone();
            addSolution(solution);
        }
        while (!adySet.isEmpty()) {
            nextRoom = adySet.first();
            adySet.remove(nextRoom);
            if (!partialSolution.contains(nextRoom)) {
                partialSolution.add(nextRoom);
                mostFrequentedRooms(nextRoom, finish, partialSolution);
                partialSolution.remove(nextRoom);
            }

        }
    }


    /**
     * Increases the frequency of the room and saves the possible path.
     *
     * @param solution the path the method will use and save.
     */
    private void addSolution(LinkedList<Integer> solution) {
        solution.addFirst(0);
        this.possiblePaths.add(solution);
        for (int i = 0; i < solution.size(); i++) {
            this.mostFreqRooms[solution.get(i)] += 1;
        }
    }

    /**
     * Method that delivers five weapons into each one of the most frequented rooms.
     */
    private void spendWeapons() {
        int max = 0;
        Weapon[] wps = spendWeaponsD1();
        LinkedList<Square> squares = new LinkedList<>();
        int cont = 12;
        while (cont > 0) {
            for (int i = 0; i < this.mostFreqRooms.length; i++) {
                if (this.mostFreqRooms[i] > this.mostFreqRooms[max]) {
                    max = i;
                }
            }
            if (mostFreqRooms[max] == 0) {
                for (int i = 0; i < mostFreqRooms.length && squares.size() < 9; i++) {
                    if (!squares.contains(getSquare(i))) {
                        squares.add(getSquare(i));
                        cont--;
                    }
                }
            } else {
                squares.addLast(getSquare(max));
                cont--;
                mostFreqRooms[max] = 0;
                max = 0;
            }
        }
        int k = 0;
        for (Square s : squares) {
            for (int i = 0; i < 5; i++) {
                s.saveWeapon(wps[k]);
                k++;
            }
        }

    }

    /**
     * Method to show the map information on screen.
     */
    public void show() {
        System.out.printf("(Mapa:" + Integer.toString(dailyPlanet) + ")");
        System.out.println(doorMan.toString());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println(map[i][j].toString());
            }
        }

    }


    /**
     * Method used to find any square of the map given the id.
     *
     * @param nSquare the square that we are looking for.
     * @return the ID of the square that we were looking for.
     */
    public Square getSquare(int nSquare) {
        Square s = null;
        try {
            s = map[nSquare / columns][nSquare % columns];
        } catch (NullPointerException e) {
            System.out.println("The square that you are looking for doesn't exist");
        }
        return s;
    }

    /**
     * Method used to find any square of the map given its row and column.
     *
     * @param row the row where the square is placed.
     * @param col the column where the square is placed.
     * @return the ID of the square that we were looking for.
     */
    public Square getSquare(int row, int col) {
        Square s = null;
        try {
            s = map[row][col];
        } catch (NullPointerException e) {
            System.err.println("The square that you are searching doesn't exists");
        }
        return s;
    }

    /**
     * Returns the north west room
     *
     * @return 0
     */
    public int getNW() {
        return 0;
    }

    /**
     * Returns the north east room
     *
     * @return columns - 1
     */
    public int getNE() {
        return columns - 1;
    }

    /**
     * Returns the south east room
     *
     * @return columns * rows - 1
     */
    public int getSE() {
        return columns * rows - 1;
    }

    /**
     * Returns the south west room
     *
     * @return columns * rows - columns
     */
    public int getSW() {
        return columns * rows - columns;
    }

    /**
     * Returns the size of the map
     *
     * @return columns * rows
     */
    public int getSize() {
        return columns * rows;
    }

    /**
     * Getter of the winnersSquare attribute.
     *
     * @return the winnersSquare attribute.
     */
    public Square getWinnersSquare() {
        return winnersSquare;
    }

    /**
     * Getter of the possiblePaths attribute.
     *
     * @return the possiblePaths attribute.
     */
    public LinkedList<LinkedList<Integer>> getPossiblePaths() {
        return possiblePaths;
    }

    /**
     * Getter of the graph.
     *
     * @return the graph attribute.
     */
    public Grafo getGraph() {
        return graph;
    }

    /**
     * Getter of the mapWithPercentageOfWalls attribute.
     *
     * @return mapWithPercentageOfWalls attribute.
     */
    public String getMapWithPercentageOfWalls() {
        return mapWithPercentageOfWalls;
    }


    /**
     * Method that generates a string showing all the square information.
     *
     * @param nSquare number of the room we want to paint
     * @return a string with the room and all the information in it.
     */
    private String paintSquare(int nSquare) {
        Square s = getSquare(nSquare);
        String message = s.stringOfCharacters();
        if (message.equals(" ") && wall(nSquare, nSquare + columns)) {
            message = "_";
        }
        if (wall(nSquare, nSquare + 1) || nSquare % columns == columns - 1) {
            message += "|";
        } else
            message += " ";
        return message;
    }

    /**
     * Method that generates a string containing all the matrix.
     *
     * @return the string containing the matrix information.
     */

    public String matrixString() {
        String result = "";
        for (int i = 0; i < columns; i++) {
            result += " _";
        }
        result += "\n";
        for (int x = 0; x < rows; x++) {
            result += "|";
            for (int y = 0; y < columns; y++) {
                result += paintSquare((columns * x) + y);

            }
            result += "\n";
        }
        return result;
    }

    /**
     * toString method that generates the initial information of the map (the weapons and the map itself).
     * @return the generated string with all the information.
     */
    public String toString() {
        String result = matrixString();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (this.map[x][y].getWeaponList().size() > 0) {
                    result += "(" + this.map[x][y].showWeapons() + ")\n";
                }
            }
        }
        return result;
    }

    /**
     * Main method of the map class.
     * @param args args.
     */
    static public void main(String[] args){
        Game.getSI(6,6,35,4);
        for (LinkedList<Integer> a:Game.getSI().getMap().getPossiblePaths()){
            for (Integer j: a) {
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Method that generates a string with the initial characters of the game.
     * @return the string generated with the characters information.
     */
    public String createdGameCharacters() {
        String message = "";
        for (int i = 0; i < columns * rows; i++) {
            message += getSquare(i).showInitialCharacters();
        }
        System.out.print(message);
        return message;
    }

}
