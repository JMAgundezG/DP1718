package tools;

import game.Game;
import map.Map;

import java.io.*;
import java.util.LinkedList;


public class Tools {

    /**
     * The maximum turns of the game
     */
    public static int MaxTurn = 100;

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
                newDir = Dir.O;
                break;
            case O:
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
                newDir = Dir.O;
                break;
            case O:
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
        Map map = Game.getSingletonInstance().getMap();
        int newRoom = nRoom;
        switch (dir) {
            case E:
                newRoom = nRoom + 1;
                break;

            case O:
                newRoom = nRoom - 1;
                break;

            case S:
                newRoom = nRoom + map.getDimY();
                break;

            case N:
                newRoom = nRoom - map.getDimY();
        }

        return newRoom;
    }

    /**
     * Method that makes from a rooms' list a Directions list
     */
    static LinkedList<Dir> roomsToDirections(LinkedList<Integer> rooms) {

        Map map = Game.getSingletonInstance().getMap();
        LinkedList<Dir> directions = new LinkedList<>();

        int previousRoom = rooms.getFirst();
        for (int i = 1; i < rooms.size(); i++) {
            if (previousRoom == rooms.get(i) - 1) {
                directions.addLast(Dir.E);
            }
            if (previousRoom == rooms.get(i) + 1) {
                directions.addLast(Dir.O);
            }
            if (previousRoom == rooms.get(i) - map.getDimY()) {
                directions.addLast(Dir.S);
            }
            if (previousRoom == rooms.get(i) + map.getDimY()) {
                directions.addLast(Dir.N);
            }

            previousRoom = rooms.get(i);
        }
        return directions;
    }

    /**
     * Method to create the .log file
     */
    static public void writeGame(String message) {
        try {
            PrintWriter writer = new PrintWriter("record.log", "UTF-8");
            writer.println(message);
            writer.close();
        } catch (IOException e) {
            System.out.println("There was an error making the log file");
        }
    }
}