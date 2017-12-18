package Tools;

/**
 * Created by daniel on 18/12/17.
 */
public class MapException extends Exception {

    private static final long serialVersionUID = 1L;

    public void printStackTrace() {
        System.err.println("Any of the parameters of the map is wrong." +
                " Creating a default 6x6_4 map");
    }
}
