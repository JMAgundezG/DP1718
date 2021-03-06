package Map;

import Datastructures.Grafo;
import Game.Game;
import Tools.Dir;


import java.util.LinkedList;
import java.util.TreeSet;

public class Path {

    static public LinkedList<Dir> wallFollower(int initialPosition) {
        LinkedList<Dir> movements = new LinkedList<>();
        int nRoom = initialPosition;
        Map map = Game.getSI().getMap();
        Dir lastDir = Dir.S;
        boolean flag = false;
        /*
         *Starting the WallFollower Algorithm
         */
        while (nRoom != map.getDailyPlanet()) {

            while (!flag) {
                if (!map.availableMovement(nRoom, RightHand(lastDir))) {
                    if (map.availableMovement(nRoom, lastDir)) {
                        nRoom = neighbourRoomNumber(nRoom, lastDir);
                        flag = true;
                    } else {
                        lastDir = LeftHand(lastDir);

                    }
                } else {
                    lastDir = RightHand(lastDir);
                    nRoom = neighbourRoomNumber(nRoom, lastDir);
                    flag = true;
                }
            }
            movements.addLast(lastDir);
            flag = false;
        }
        return movements;
    }

    static public LinkedList<Dir> wallFollowerLeftHanded(int initialPosition) {
        LinkedList<Dir> movements = new LinkedList<>();
        int nRoom = initialPosition;
        Map map = Game.getSI().getMap();
        Dir lastDir = Dir.S;
        boolean flag = false;
        /*
         *Starting the WallFollower Algorithm
         */
        while (nRoom != map.getDailyPlanet()) {

            while (!flag) {
                if (!map.availableMovement(nRoom, LeftHand(lastDir))) {
                    if (map.availableMovement(nRoom, lastDir)) {
                        nRoom = neighbourRoomNumber(nRoom, lastDir);
                        flag = true;
                    } else {
                        lastDir = RightHand(lastDir);

                    }
                } else {
                    lastDir = LeftHand(lastDir);
                    nRoom = neighbourRoomNumber(nRoom, lastDir);
                    flag = true;
                }
            }
            movements.addLast(lastDir);
            flag = false;
        }
        return movements;
    }

    /**
     * Says the right hand of a direction
     */
    static Dir RightHand(Dir dir) {
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
     * Says the left hand of a direction
     */
    static Dir LeftHand(Dir dir) {
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
     * Says the neighbour room number of another room and a direction
     */
    static int neighbourRoomNumber(int nRoom, Dir dir) {
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
     * Uses the "MostFrequentedRooms" of map to take his path
     */
    static public LinkedList<Dir> firstPosiblePath(){
        LinkedList<Integer> rooms = (LinkedList<Integer>) Game.
                getSI().getMap().getPosiblePaths().getFirst().
                clone();
        rooms.addFirst(0);
        return RoomsToDirections(rooms);
    }

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


    static public LinkedList paths(int start, int finish){
        LinkedList<LinkedList<Integer>> solutions = new LinkedList<>();
        path(start, finish, new LinkedList<Integer>(), solutions);
        return solutions;
    }
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
}
