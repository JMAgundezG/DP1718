package GameCharacters;

import datastructures.BinaryTree;
import Map.DoorMan;
import Map.Map;
import Map.Square;
import Map.Weapon;

import java.util.Comparator;

public class SuperHeroe extends GameCharacter {

    private BinaryTree<Weapon> wTree;

    public SuperHeroe(String name, String id, int pos) {

        super(name, id, pos);
        this.wTree = new BinaryTree<Weapon>();

    }

    public void saveWeapon(Weapon w){
        if (wTree.belongs(w)){
            Weapon oldWeapon = wTree.extract(w);
            wTree.insertData(new Weapon(w.getName(), w.getPower()+oldWeapon.getPower()));
        }
    }
    public void takeWeapon(){ // TODO MAKE TO 2nd D

        Square s = Map.getSingleton().getSquare(this.getPosition());
        this.wTree.insertData(s.dropWeapon());

    }

    public Weapon biggestWeapon(){
        return wTree.mostValuedNode(Comparator.comparingInt(Weapon::getPower));
    }

    public void useWeapon(){

        DoorMan d = Map.getSingleton().getDoorMan();
        if(this.getPosition() == Map.getSingleton().getDailyPlanet()) {
            Weapon bestWeapon = wTree.mostValuedNode(Comparator.comparingInt(Weapon::getPower));
            if (!d.tryWeapon(bestWeapon)){
                wTree.delete(bestWeapon);
            }
        }
    }

    /**
     * Method that searches the biggest weapon on the tree.
     * @return that weapon
     */
    public BinaryTree<Weapon> getwTree() {
        return wTree;
    }

    @Override
    public String toString() {
        return super.toString() + wTree.StringInOrder();
    }
}
