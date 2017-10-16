import datastructures.BinaryTree;

public class DoorMan {

    private BinaryTree<Weapon> tree;

    Weapon [] doorManWeapons;

    boolean gateOpened;

    public DoorMan(){

        tree = new BinaryTree<>();

        doorManWeapons = new Weapon[]{new Weapon("CampoEnergia", 5), new Weapon("Weapondura", 13),
                new Weapon("Anillo", 11), new Weapon("Acido", 1), new Weapon("Antorcha", 5),
                new Weapon("Bola", 3), new Weapon("Baston", 22),
                new Weapon("CadenaFuego", 11), new Weapon("Espada", 11),
                new Weapon("Cetro", 20), new Weapon("Capa", 10),
                new Weapon("CampoMagnetico", 5), new Weapon("Escudo", 3),
                new Weapon("Garra", 22), new Weapon("Flecha", 12), new Weapon("Gema", 4)};

        gateOpened = true;

    }


    public void configure(){

        for (Weapon w:doorManWeapons) {
            tree.insertData(w);
        }

    }

    public void closeGate(){
        gateOpened = false;
    }
}
