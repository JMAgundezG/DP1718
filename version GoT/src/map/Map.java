package map;

import characters.Characters;
import characters.Lannister;
import characters.WhiteWalkers;
import datastructures.Grafo;
import tools.Dir;
import tools.GenAleatorios;
import tools.Tools;

import java.util.*;

import static tools.Dir.*;

//TODO cosas importantes ∇


public class Map {

    private Room[][] rooms;
    private int dimX;
    private int dimY;
    private int throneRoom;
    private Door throneDoor;
    private LinkedList<Characters> CharactersList;
    private LinkedList<Walls> walls;
    private Room winRoom;
    private Grafo graph;
    private int[] mostFreqRooms;
    private LinkedList<LinkedList<Integer>> posiblePaths;
    private String initialMap;

    /**
     * game.Map constructor
     *
     * @param dimY it should be an integer bigger than 0
     * @param dimX it should be an integer bigger than 0
     * @param throneRoom it should be an integer between 0 and dimX*dimY
     * @param depth configures the lock's depth
     */
    public Map(int dimY, int dimX, int throneRoom, int depth) {

        this.dimX = dimX;
        this.dimY = dimY;
        this.throneRoom = throneRoom;
        this.CharactersList = new LinkedList<>();
        this.rooms = new Room[dimX][dimY];
        this.throneDoor = new Door(throneRoom, depth);
        this.walls = new LinkedList<>();
        this.mostFreqRooms = new int[dimX * dimY];
        this.posiblePaths = new LinkedList<>();
        for (int i = 0; i < mostFreqRooms.length; i++) {
            mostFreqRooms[i] = 0;

        }

        int k = 0;
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                this.rooms[i][j] = new Room(k, i, j);
                k++;
            }
        }
        this.winRoom = new Room(1111, 1111, 1111);
        graph = new Grafo();
        crearGrafo();
        createMaze();
        graph.warshall();
        graph.floyd();
        initialMap = showMap();
        System.out.print(showMap());
        erasePercentageOfWalls();
        LinkedList<Integer> solucionParcial = new LinkedList<>();
        mostFrequentedRooms(0, (dimX * dimY) - 1, solucionParcial);
        spendKeys();
    }

    /**
     * Creates all the nodes of the map
     */
    private void crearGrafo() {
        for (int node = 0; node < dimX * dimY; node++) {
            this.graph.nuevoNodo(node);
        }
    }

    /**
     * Getter of the map's graph
     *
     * @return graph
     */
    private Grafo getGraph() {
        return graph;
    }

    /**
     * Getter of dimX
     *
     * @return dimX
     */
    public int getDimX() {
        return dimX;
    }

    /**
     * Getter of dimY
     *
     * @return dimY
     */
    public int getDimY() {
        return dimY;
    }

    /**
     * Getter of the Throne's Door
     *
     * @return Throne's Door
     */
    public Door getThroneDoor() {
        return throneDoor;
    }

    /**
     * Getter of the room which collects the characters who entries in
     * the throneRoom
     *
     * @return Winning Room
     */
    public Room getWinRoom() {
        return winRoom;
    }

    /**
     * Method that generate the keys needed to spend in the map
     *
     * @return An arrayList of keys
     */
    public LinkedList<Key> generateKeys() {
        LinkedList<Key> keys = new LinkedList<>();

        for (int i = 0; i < 30; i++) {
            if (i % 2 == 1) {
                keys.addLast(new Key(i));
                keys.addLast(new Key(i));
            } else {
                keys.addLast(new Key(i));
            }
        }
        return keys;
    }



    /**
     * Method that finds the room we are searching by its number
     * The method needs that the room exists
     *
     * @param numberOfRoom number of the room we want to search
     * @return the room
     */


    public Room findRoom(int numberOfRoom) {
        Room r = null;
        try{
            r = rooms[numberOfRoom / getDimY()][numberOfRoom % getDimY()];
        }catch (NullPointerException e){
            System.out.println("The room that you are searching doesn't exist");
        }
        return r;
    }


    /**
     * Method that finds the room we are searching by its position in the map
     * The method needs that the room exists
     *
     * @param posX row of the room
     * @param posY column of the room
     * @return The room of that position
     */
    public Room findRoom(int posX, int posY) {
        Room r = null;
        try {
            r = rooms[posX][posY];
        }catch (NullPointerException e){
            System.out.println("The room that you are searching doesn't exist");
        }
        return r;
    }

    /**
     * Make a first-depth's algorithm to take all posible paths and say which are the
     * most frequented rooms
     *
     * @param start initial room
     * @param finish final room
     * @param partialSolution list to save the partial Path
     */
    private void mostFrequentedRooms(int start, int finish, LinkedList<Integer> partialSolution) {
        Integer nextRoom;
        TreeSet<Integer> adySet = new TreeSet<>();
        this.graph.adyacentes(start, adySet);
        if (start == finish) {
            LinkedList<Integer> solution = (LinkedList<Integer>) partialSolution.clone();
            solution.addFirst(0);
            addSolution(solution);
        }
            while (!adySet.isEmpty()) {
                nextRoom = adySet.first();
                adySet.remove(nextRoom);
                if (!partialSolution.contains(nextRoom)){
                    partialSolution.add(nextRoom);
                    mostFrequentedRooms(nextRoom, finish, partialSolution);
                    partialSolution.remove(nextRoom);
                }

            }
        }


    /**
     * increases the most frequented rooms vector and saves the possible path
     * @param solution the path that the method will use
     */
    private void addSolution(LinkedList<Integer> solution) {
        this.posiblePaths.add(solution);
        for (int i = 0; i < solution.size(); i++) {
            this.mostFreqRooms[solution.get(i)] += 1;
        }
    }

    /**
     * Moves the characters that are in throneRoom and can be kings/queens and moves them to win room (1111)
     */
    public void moveToWinRoom(){
        int j = 0;
        LinkedList <Characters> chs = findRoom(getThroneRoom()).getCharactersList();
        for (int i = 0; i < chs.size(); i++) {
            Characters c = chs.get(j);
            if(!(c instanceof WhiteWalkers || c instanceof Lannister)){
                winRoom.insertCharacter(c);
                chs.remove(c);
                c.setNumberOfRoom(1111);
            }
            else{
                j++;
            }
        }
    }

    /**
     * It Saves the message in a string
     *
     * @return the message
     */
    public String showMap() {
        String result = "";
        for (int i = 0; i < getDimY(); i++) {
            result += " _";
        }
        result += "\n";
        for (int x = 0; x < getDimX(); x++) {
            result += "|";
            for (int y = 0; y < getDimY(); y++) {
                result += paintRoom((getDimY() * x) + y);

            }
            result += "\n";
        }

        for(int x = 0; x < getDimX();x++){
            for(int y = 0; y < getDimY();y++){
                if(!this.rooms[x][y].showKeys().equals("")) {
                    result += "(" + this.rooms[x][y].showKeys() + ")\n";
                }
            }
        }
        return result;
    }

    /**
     * Saves in a string the room features
     *
     * @param numberOfRoom number of the room we want to paint
     * @return an string with the room in it
     */
    private String paintRoom(int numberOfRoom) {
        Room room = findRoom(numberOfRoom);
        String message = room.WhoAreInTheRoom();
        if (message.equals(" ") && wall(numberOfRoom, numberOfRoom + getDimY())) {
            message = "_";
        }
        if (wall(numberOfRoom, numberOfRoom + 1) || numberOfRoom % dimY == dimY - 1) {
            message += "|";
        } else
            message += " ";
        return message;
    }

    /**
     * @param room1 source room
     * @param room2 destiny room
     * @return a boolean that says if there are a wall between two rooms( if you can move from room1 to room2)
     */
    private boolean wall(int room1, int room2) {
        return !graph.adyacente(room1, room2);
    }

    /**
     * Says if a character makes a movement from a room in any dir
     * @param room the room the character stays
     * @param dir the direction of the movement
     * @return if it can be possible
     */
    public boolean availableMovement(int room, Dir dir) {
        boolean available = false;
        switch (dir) {
            case N:
                if (room >= getDimY() && !wall(room, room - getDimY())) {
                    available = true;
                }
                break;
            case S:
                if (room < getDimY() * (getDimX() - 1) && !wall(room, room + getDimY())) {
                    available = true;
                }
                break;
            case O:
                if (room % getDimY() > 0 && !wall(room, room - 1)) {
                    available = true;
                }
                break;
            case E:
                if (room % getDimY() < (getDimY() - 1) && !wall(room, room + 1)) {
                    available = true;
                }
                break;

        }
        return available;
    }

    /**
     * remove a human from the charactersList
     * @param human the human will be killed
     */
    public void killHuman(Characters human) {

        this.CharactersList.remove(human);
    }

    public int getNumberOfRooms() {
        return this.dimX * this.dimY;
    }


    public String showKeys() {
        String message = "";
        for (int i = 0; i < dimX * dimY; i++) {
            Room room = findRoom(i);
            if (room.keysInFloor()) {
                message += room.showKeys();
            }
        }
        return message;
    }


    /**
     * Set the marks for the initial algorythms
     */
    private void setMarksInicial() {
        for (int i = 0; i < dimX * dimY; i++) {
            Room room = findRoom(i);
            room.setMark(i);
        }
    }

    /**
     * Create the maze on the map
     */
    private void createMaze() {
        setMarksInicial();
        setWalls();
        while (!walls.isEmpty()) {
            int gen = GenAleatorios.generarNumero(walls.size());
            Walls wall = walls.get(gen);
            Room room1 = findRoom(wall.getSrc());
            Room room2 = findRoom(wall.getDst());
            if (room1.getMark() != room2.getMark()) {
                graph.nuevoArco(room1.getNumberOfRoom(), room2.getNumberOfRoom(), 1);
                graph.nuevoArco(room2.getNumberOfRoom(), room1.getNumberOfRoom(), 1);
                setAllMarks(room1.getMark(), room2.getMark());
            }
            walls.remove(wall);

        }
    }

    /**
     * Erase the 5% of walls of the maze
     */
    private void erasePercentageOfWalls() {
        /* 5% of walls */
        int percentage = (dimX * dimY * 5) / 100;
        int i = 0;
        while (i < percentage) {
            int gen2 = GenAleatorios.generarNumero(dimX * dimY);
            Room room = findRoom(gen2);
            if (conditionForPercentage(room, N)) {
                graph.nuevoArco(gen2, gen2 - dimY, 1);
                graph.nuevoArco(gen2 - dimY, gen2, 1);
                i++;
            } else {
                if (conditionForPercentage(room, S)) {
                    graph.nuevoArco(gen2, gen2 + dimY, 1);
                    graph.nuevoArco(gen2 + dimY, gen2, 1);
                    i++;
                } else {
                    if (conditionForPercentage(room, E)) {
                        graph.nuevoArco(gen2, gen2 + 1, 1);
                        graph.nuevoArco(gen2 + 1, gen2, 1);
                        i++;
                    } else {
                        if (conditionForPercentage(room, O)) {
                            graph.nuevoArco(gen2, gen2 - 1, 1);
                            graph.nuevoArco(gen2 - 1, gen2, 1);
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
     * Condition for the 5% algorythm
     */
    private boolean conditionForPercentage(Room room, Dir dir) {
        boolean solution = false;
        int n = room.getNumberOfRoom();
        if (!availableMovement(n, dir)) {
            switch (dir) {
                case N:
                    if (n >= dimY) {
                        if (getPath(n, n - dimY).size() > 3) {
                            solution = true;
                        }
                    }
                    break;

                case S:
                    if (room.getPosX() < getDimX() - 1) {
                        if (getPath(n, n + dimY).size() > 3) {
                            solution = true;
                        }
                    }
                    break;
                case E:
                    if (room.getPosY() < dimY - 1) {
                        if (getPath(n, n + 1).size() > 3) {
                            solution = true;
                        }
                    }
                    break;
                case O:
                    if (room.getPosY() > 0) {
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
     * Set the same mark to the rooms that has the dstValue mark
     */
    private void setAllMarks(int srcValue, int dstValue) {
        Room room;
        for (int i = 0; i < this.dimX * this.dimY; i++) {
            room = findRoom(i);
            if (room.getMark() == dstValue) {
                room.setMark(srcValue);
            }

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
     * Spend keys in the different rooms
     */
    private void spendKeys() {
        int max = 0;
        LinkedList<Key> keys = generateKeys();
        LinkedList<Room> rooms = new LinkedList<>();
        int cont = 9;
        while (cont>0) {
            for (int i = 0; i < this.mostFreqRooms.length; i++) {
                if (this.mostFreqRooms[i] > this.mostFreqRooms[max]) {
                    max = i;
                }
            }
            if (mostFreqRooms[max] == 0) {
                for (int i = 0; i < mostFreqRooms.length && rooms.size() < 9; i++) {
                    if (!rooms.contains(findRoom(i))) {
                        rooms.add(findRoom(i));
                        cont--;
                    }
                }
            } else {
                rooms.addLast(findRoom(max));
                cont--;
                mostFreqRooms[max] = 0;
                max = 0;
            }
        }
        for (Room room: rooms) {
            room.insertKey(keys.removeFirst());
            room.insertKey(keys.removeFirst());
            room.insertKey(keys.removeFirst());
            room.insertKey(keys.removeFirst());
            room.insertKey(keys.removeFirst());
        }
    }


    public LinkedList<Characters> getCharactersList() {
        return CharactersList;
    }

    /**
     * Calls the "Path" method of the graph
     *
     * @param source  source room
     * @param destiny destiny room
     * @return A linkedList with the path
     */
    public LinkedList<Integer> getPath(int source, int destiny) {
        return this.getGraph().path(source, destiny);
    }

    public int getNW() {
        return 0;
    }

    public int getNE() {
        return getDimY() - 1;
    }

    public int getSW() {
        return getDimY() * (getDimX() - 1);
    }

    public int getSE() {
        return getDimX() * getDimY() - 1;
    }

    public int getThroneRoom() {
        return throneRoom;
    }

    public LinkedList<LinkedList<Integer>> getPosiblePaths() {
        return posiblePaths;
    }

    public String getInitialMap() {
        return initialMap;
    }

    }
