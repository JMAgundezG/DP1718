package tools;

import datastructures.Grafo;
import game.Game;
import map.Map;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public interface Path {

    /**
     *
     * @return the lannister's movement
     */
    static  LinkedList<Dir> LannisterMovement() {

        Map map = Game.getSingletonInstance().getMap();
        LinkedList<Integer> rooms = new LinkedList<>();

        rooms.add(map.getSE());

        rooms.addAll(map.getPath(map.getSE(), map.getNE()));
        rooms.addAll(map.getPath(map.getNE(), map.getNW()));
        rooms.addAll(map.getPath(map.getNW(), map.getSW()));
        rooms.addAll(map.getPath(map.getSW(), map.getSE()));

        return Tools.roomsToDirections(rooms);
    }

    /**
     * Creates the Targaryen's movement with the wallFollower algorithm
     */
    static LinkedList<Dir> TargaryenMovement(int initialPosition) {
        LinkedList<Dir> movements = new LinkedList<>();
        int nRoom = initialPosition;
        Map map = Game.getSingletonInstance().getMap();
        Dir lastDir = Dir.S;
        boolean flag = false;
        /*
         *Starting the WallFollower Algorithm
         */
        while (nRoom != map.getThroneDoor().getNumberOfRoom()) {

            while (!flag) {
                if (!map.availableMovement(nRoom, Tools.RightHand(lastDir))) {
                    if (map.availableMovement(nRoom, lastDir)) {
                        nRoom = Tools.neighbourRoomNumber(nRoom, lastDir);
                        flag = true;
                    } else {
                        lastDir = Tools.LeftHand(lastDir);

                    }
                } else {
                    lastDir = Tools.RightHand(lastDir);
                    nRoom = Tools.neighbourRoomNumber(nRoom, lastDir);
                    flag = true;
                }
            }
            movements.addLast(lastDir);
            flag = false;
        }
        return movements;
    }

    /**
     *
     * @return the whitewalker path
     */
    static LinkedList<Dir> whiteWalkerMovement(){
        Map map = Game.getSingletonInstance().getMap();
        LinkedList<Integer> rooms = new LinkedList<>();

        rooms.add(map.getSW());
        rooms.addAll(map.getPath(map.getSW(),map.getNW()));
        rooms.addAll(map.getPath(map.getNW(),map.getNE()));
        rooms.addAll(map.getPath(map.getNE(),map.getSE()));
        rooms.addAll(map.getPath(map.getSE(),map.getSW()));

        return Tools.roomsToDirections(rooms);
    }

    /**
     * Uses the "MostFrequentedRooms" of map to take his path
     */
    static LinkedList<Dir> StarkMovement(){
        LinkedList<Integer> rooms = (LinkedList<Integer>) Game.
                getSingletonInstance().getMap().getPosiblePaths().getFirst().
                clone();
        return Tools.roomsToDirections(rooms);
    }

    /**
     * Makes a message with a path
     */
     static String showPath(LinkedList<Dir> dirs){
        String message = "";
        for(Dir dir : dirs){
            message+=" "+dir.toString();
        }
        return message;
    }
}
