package Map;

/**
 * Implementation of the Walls.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This is the class that will simulate the walls of the map.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class Walls {

    /**
     * The room that we will consider as source
     */
    private int src;

    /**
     * The room that we will consider as destiny.
     */
    private int dst;

    /**
     * Public constructor of the class Walls.
     * @param src the attribute src.
     * @param dst the attribute dst.
     */
    public Walls(int src, int dst){
        this.src = src;
        this.dst = dst;
    }

    /**
     * Getter of the attribute dst.
     * @return the dst attribute.
     */
    public int getDst() {
        return dst;
    }

    /**
     * Getter of the attribute src.
     * @return the src attribute.
     */
    public int getSrc() {
        return src;
    }

}
