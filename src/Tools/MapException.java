package Tools;

/**
 * Implementation of the MapException.
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * Exception when any of the map parameters is wrong.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class MapException extends Exception {

    private static final long serialVersionUID = 1L;

    public void printStackTrace() {
        System.err.println("Any of the parameters of the map is wrong." +
                " Creating a default 6x6_4 map");
    }
}
