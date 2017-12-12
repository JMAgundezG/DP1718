package Tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Implementation of the Tools Class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * Auxiliar class used to simulate movements, generate paths and to contain constants..
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class Tools {

    /**
     * The maximum of turns of the game.
     */
    public static int MAX_TURN= 50;

    /**
     * Method that generates the possible movements in the Delivery 2.
     * @return the list containing the movements.
     */
    public static LinkedList<Dir> D2Movements(){
        Dir[] a = new Dir[]{Dir.S, Dir.E, Dir.N, Dir.E, Dir.W};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }


    /**
     * ExtraFlight movements
     * @return the list containing the path.
     */
    public static LinkedList<Dir> SHFMovements(){
        Dir[] a = new Dir[]{Dir.E,Dir.E,Dir.E,Dir.N,Dir.E,Dir.E,Dir.S};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }



    /**
     * ExtraSensorial movements
     * @return the list containing the path.
     */
    public static LinkedList<Dir> SHESMovements(){
        Dir[] a = new Dir[]{Dir.E,Dir.E,Dir.S,Dir.W,Dir.W,Dir.E,Dir.S,Dir.W,
                Dir.E,Dir.N,Dir.E,Dir.S, Dir.E,Dir.S,Dir.W,Dir.W,Dir.W,Dir.S,
                Dir.E,Dir.W,Dir.N,Dir.E, Dir.E,Dir.S,Dir.N,Dir.E,Dir.S,Dir.S,
                Dir.W,Dir.W,Dir.W,Dir.E,Dir.E,Dir.E,Dir.E,Dir.E};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }


    /**
     * Extraphysical movements
     * @return the list containing the path.
     */
    public static LinkedList<Dir> SHEPMovements(){
        Dir[] a = new Dir[]{Dir.E,Dir.E,Dir.S,Dir.S,Dir.E,Dir.S,Dir.S,Dir.E,Dir.E,Dir.S};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }

    /**
     * Villain movements
     * @return the list containing the path.
     */
    public static LinkedList<Dir> VillainMovements(){
        Dir[] a = new Dir[]{Dir.S,Dir.S,Dir.N,Dir.W,Dir.S,Dir.S,Dir.W,Dir.S,Dir.E,Dir.E,Dir.N,Dir.S,Dir.S};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }

    /**
     * Method that shows the path. The list of directions.
     * @param dirs the path we want to show.
     * @return the String containing the path.
     */
    public static String showPath(LinkedList<Dir> dirs){
        String message = "";
        for(Dir dir : dirs){
            message+=" "+dir.toString();
        }
        return message;
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
