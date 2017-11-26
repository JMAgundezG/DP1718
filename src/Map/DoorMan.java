package Map;

import Datastructures.BinaryTree;

import java.util.Comparator;

/**
 * Implementation of the DoorMan.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This is the class that controls the portal for the end-game.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class DoorMan {

    /**
     * Tree used to store weapon data.
     * It is used to compare the weapons of the Superheroes that try to go through the portal.
     */
    private BinaryTree tree;

    /**
     * Array used to store the weapons of the doorman.
     */
    private Weapon [] doorManWeapons;

    /**
     * Boolean that stores the information of the portal.
     * True if it is open. False on the opposite case.
     */
    private boolean gateOpened;

    /**
     * The depth required to open the portal.
     */
    private int depth;

    /**
     * Public constructor of the Doorman.
     * @param depth the depth required to open the portal.
     */
    public DoorMan(int depth){

        this.depth = depth;

        tree = new BinaryTree();

        doorManWeapons = new Weapon[]{new Weapon("CampoEnergia", 5), new Weapon("Weapondura", 13),
                new Weapon("Anillo", 11), new Weapon("Acido", 1), new Weapon("Antorcha", 5),
                new Weapon("Bola", 3), new Weapon("Baston", 22),
                new Weapon("CadenaFuego", 11), new Weapon("Espada", 11),
                new Weapon("Cetro", 20), new Weapon("Capa", 10),
                new Weapon("CampoMagnetico", 5), new Weapon("Escudo", 3),
                new Weapon("Garra", 22), new Weapon("Flecha", 12), new Weapon("Gema", 4)};

        gateOpened = true;
        configure();
    }

    /**
     * Method that inserts the information of the array into the tree, to enable more efficient searches.
     */
    public void configure(){

        for (Weapon w:doorManWeapons) {
            tree.insertData(w);
        }
        closeGate();
    }

    /**
     * Method that allows other Game.Game Characters to interact with the portal and try to open the portal.
     * @param w The weapon that the Characters are using to try and open the portal.
     * @return true if the portal has been opened. False on the opposite case.
     */
    public boolean tryWeapon(Weapon w){
        boolean done = false;
        Weapon bw = (Weapon) this.tree.mostValuedNode(Comparator.comparingInt(Weapon::getPower));
        if (bw.getPower() < w.getPower()){
            tree.delete(bw);
            done = true;
            if(tree.depth()<depth){
                gateOpened = true;
            }
        }
        return done;
    }

    /**
     * Closes the portal.
     */
    public void closeGate(){
        gateOpened = false;
    }

    /**
     * Returns the Binary Tree.
     * @return the weapon tree.
     */
    public BinaryTree getTree() {
        return tree;
    }

    /**
     * Returns the depth.
     * @return The depth to open the portal.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Returns the array of the doorman.
     * @return the weapon array.
     */
    public Weapon[] getDoorManWeapons() {
        return doorManWeapons;
    }

    /**
     * Method used to show all the elements related to the state of the portal.
     * @return a String containing all the information about the portal.
     */
    public String toString(){
        String message = "(hombrepuerta:";
        if (gateOpened)
            message += "abierta:";
        else
            message += "cerrada:";
        message += Integer.toString(depth);
        message += tree.StringInOrder();
        return message;
    }

    /**
     * Checks the state of the portal.
     * @return true if it is open. False on the opposite case.
     */
    public boolean isGateOpened() {
        return gateOpened;
    }
}
