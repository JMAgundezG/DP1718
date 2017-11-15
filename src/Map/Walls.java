package map;

/**
 * Walls' class
 */
public class Walls {
    /**
     * the two rooms that the wall separate
     */
    private int src;
    private int dst;

    public Walls(int src, int dst){
        this.src = src;
        this.dst = dst;
    }

    public int getDst() {
        return dst;
    }

    public int getSrc() {
        return src;
    }

}
