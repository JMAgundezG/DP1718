package Map;

import Game.Game;
import Tools.Dir;


import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Implementation of the Path.
 *
 * @author José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This is where all the paths of the characters are generated.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class Path {



    /**
     * Method that says the right hand direction of a given one.
     * @param dir the direction we have.
     * @return the right hand direction of that one. I.e.: if we have North, the right hand one is East.
     */
    static public Dir RightHand(Dir dir) {
        Dir newDir = Dir.N;
        switch (dir) {
            case N:
                newDir = Dir.E;
                break;
            case E:
                newDir = Dir.S;
                break;
            case S:
                newDir = Dir.W;
                break;
            case W:
                newDir = Dir.N;
                break;
        }

        return newDir;
    }

    /**
     * Method that says the left hand direction of a given one.
     * @param dir the direction we have.
     * @return the left hand direction of that one. I.e.: if we have North, the left hand one is West.
     */
    static public Dir LeftHand(Dir dir) {
        Dir newDir = Dir.N;
        switch (dir) {
            case N:
                newDir = Dir.W;
                break;
            case W:
                newDir = Dir.S;
                break;
            case S:
                newDir = Dir.E;
                break;
            case E:
                newDir = Dir.N;
                break;
        }

        return newDir;
    }

    /**
     * Method that says the number of the room we are aiming to with a given direction.
     * @param nRoom the room we are in.
     * @param dir the direction we want to move to.
     * @return the number of the destiny room.
     */
    static public int neighbourRoomNumber(int nRoom, Dir dir) {
        Map map = Game.getSI().getMap();
        int newRoom = nRoom;
        switch (dir) {
            case E:
                newRoom = nRoom + 1;
                break;

            case W:
                newRoom = nRoom - 1;
                break;

            case S:
                newRoom = nRoom + map.getColumns();
                break;

            case N:
                newRoom = nRoom - map.getColumns();
        }

        return newRoom;
    }


    /**
     * Method that uses the most frequented rooms of the map to generate the first possible path.
     * @return the first possible path that we can use.
     */
    static public LinkedList<Dir> firstPosiblePath(){
        LinkedList<Integer> rooms = (LinkedList<Integer>) Game.
                getSI().getMap().getPossiblePaths().getFirst().
                clone();
        rooms.addFirst(0);
        return RoomsToDirections(rooms);
    }

    /**
     * Method that transforms the rooms into directions.
     * @param rooms the list of rooms that we have generated with a path.
     * @return the list of rooms transformed to a list of directions forming a path.
     */
    public static LinkedList<Dir> RoomsToDirections(LinkedList<Integer> rooms) {

        Map map = Game.getSI().getMap();
        LinkedList<Dir> directions = new LinkedList<>();

        int previousRoom = rooms.getFirst();
        for (int i = 1; i < rooms.size(); i++) {
            if (previousRoom == rooms.get(i) - 1) {
                directions.addLast(Dir.E);
            }
            if (previousRoom == rooms.get(i) + 1) {
                directions.addLast(Dir.W);
            }
            if (previousRoom == rooms.get(i) - map.getColumns()) {
                directions.addLast(Dir.S);
            }
            if (previousRoom == rooms.get(i) + map.getColumns()) {
                directions.addLast(Dir.N);
            }

            previousRoom = rooms.get(i);
        }
        return directions;
    }

    /**
     *
     * @param start
     * @param finish
     * @return
     */
    static public LinkedList paths(int start, int finish){
        LinkedList<LinkedList<Integer>> solutions = new LinkedList<>();
        path(start, finish, new LinkedList<Integer>(), solutions);
        return solutions;
    }

    /**
     * Backtracking method that generates the depth path.
     * @param start the starting room.
     * @param finish the finishing room.
     * @param partialSolution the partial solution of the path.
     * @param solutions a list that contains all the generated possible solutions.
     */
    private static void path(int start, int finish, LinkedList<Integer> partialSolution, LinkedList<LinkedList<Integer>> solutions) {
        Integer nextRoom;
        TreeSet<Integer> adySet = new TreeSet<>();
        Game.getSI().getMap().getGraph().adyacentes(start, adySet);
        if (start == finish) {
            LinkedList<Integer> solution = (LinkedList<Integer>) partialSolution.clone();
            solutions.addLast(solution);
        }
        while (!adySet.isEmpty()) {
            nextRoom = adySet.first();
            adySet.remove(nextRoom);
            if (!partialSolution.contains(nextRoom)) {
                partialSolution.add(nextRoom);
                path(nextRoom, finish, partialSolution, solutions);
                partialSolution.remove(nextRoom);
            }

        }
    }

    /**
     * Method that generates the shortest path between two rooms.
     * @param start the starting room.
     * @param finish the finishing room.
     * @return the generated path.
     */
    public static LinkedList<Dir> shortestPath(int start, int finish){
        LinkedList path = Game.getSI().getMap().getGraph().path(start,finish);
        path.addFirst(start);
        return RoomsToDirections(path);
    }

    /**
     * Method that shows the path. The list of directions.
     * @param dirs the path we want to show.
     * @return the String containing the path.
     */
    public static String showPath(LinkedList<Dir> dirs){
        String message = "";
        for(Dir dir : dirs){
            message += " " + dir.toString();
        }
        return message;
    }
}
