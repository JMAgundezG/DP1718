package Tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Implementation of the Tools Class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * In version 2.0, we have modified the functioning of this class.
 * Still contains constants. Prints the .log file.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class Tools {

    /**
     * The maximum of turns of the game.
     */
    public static int MAX_TURN= 50;






    /**
     * Method to create the .log file and print all the information in it.
     * @param message The message containing all the information of the game in order to write it.
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
