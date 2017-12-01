package Tools;

import java.util.Collections;
import java.util.LinkedList;
public class Tools {

    public static LinkedList<Dir> D2Movements(){
        Dir[] a = new Dir[]{Dir.S, Dir.E, Dir.N, Dir.E, Dir.W};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }


    /**
     * ExtraFlight movements
     * @return b
     */
    public static LinkedList<Dir> SHFMovements(){
        Dir[] a = new Dir[]{Dir.E,Dir.E,Dir.E,Dir.N,Dir.E,Dir.E,Dir.S};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }


    /**
     * ExtraSensorial movements
     * @return b
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
     * @return b
     */
    public static LinkedList<Dir> SHEPMovements(){
        Dir[] a = new Dir[]{Dir.E,Dir.E,Dir.S,Dir.S,Dir.E,Dir.S,Dir.S,Dir.E,Dir.E,Dir.S};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }

    /**
     * Villain movements
     * @return b
     */
    public static LinkedList<Dir> VillainMovements(){
        Dir[] a = new Dir[]{Dir.S,Dir.S,Dir.N,Dir.W,Dir.S,Dir.S,Dir.W,Dir.S,Dir.E,Dir.E,Dir.N,Dir.S,Dir.S};
        LinkedList<Dir> b = new LinkedList<Dir>();
        Collections.addAll(b, a);
        return b;
    }

    public static int MAX_TURN= 50;

}
