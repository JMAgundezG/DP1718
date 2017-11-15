package Map;

import datastructures.BinaryTree;

import java.util.Comparator;

public class DoorMan {

    private BinaryTree tree;

    private Weapon [] doorManWeapons;

    private boolean gateOpened;

    private int depth;

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


    public void configure(){

        for (Weapon w:doorManWeapons) {
            tree.insertData(w);
        }
        closeGate();
    }

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



    public void closeGate(){
        gateOpened = false;
    }

    public BinaryTree getTree() {
        return tree;
    }

    public int getDepth() {
        return depth;
    }

    public Weapon[] getDoorManWeapons() {
        return doorManWeapons;
    }

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

    public boolean isGateOpened() {
        return gateOpened;
    }
}
