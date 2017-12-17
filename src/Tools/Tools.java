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
