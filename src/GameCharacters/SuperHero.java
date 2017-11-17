package GameCharacters;

import GameCharacters.Movement.Movement;
import datastructures.BinaryTree;
import Map.DoorMan;
import Map.Map;
import Map.Square;
import Map.Weapon;

import java.util.Comparator;

/**
 * Implementation of the SuperHero.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * Extends from the MetaHuman class and uses its methods.
 * In version 2.0, The constructor has been modified. <br/>
 * Year: 2017/2018 <br/>
 * Group: Rubber Duck <br/>
 * Delivery: EC2 <br/>
 */
public class SuperHero extends GameCharacter {

    /**
     * Attribute that contains the weaponTree of the SuperHero.
     */
    private BinaryTree<Weapon> wTree;

    /**
     * Public constructor of the class SuperHero.
     * @param name the name attribute of the SuperHero.
     * @param type the type attribute of the SuperHero.
     * @param id the id attribute of the SuperHero.
     * @param pos the position attribute of the SuperHero.
     * @param movement the movement attribute of the SuperHero.
     */
    public SuperHero(String name, String type, String id, int pos, Movement movement) {

        super(name, type, id, pos, movement);
        this.wTree = new BinaryTree<Weapon>();

    }

    /**
     * Loads a weapon into the weaponTree if it is not already in.
     * @param w the weapon we want to insert.
     */
    public void saveWeapon(Weapon w){
        if (wTree.belongs(w)){
            Weapon oldWeapon = wTree.extract(w);
            wTree.insertData(new Weapon(w.getName(), w.getPower()+oldWeapon.getPower()));
        }
    }

    /**
     * Takes a weapon from the current square of the map.
     */
    public void takeWeapon(){ // TODO MAKE TO 2nd D

        Square s = Map.getSingleton().getSquare(this.getPosition());
        this.wTree.insertData(s.dropWeapon());

    }

    /**
     * Checks the tree and looks for the biggest value of all weapons.
     * @return The value of the weapon with the highest power.
     */
    public Weapon biggestWeapon(){
        return wTree.mostValuedNode(Comparator.comparingInt(Weapon::getPower));
    }

    /**
     * Implementation of the abstract method useWeapon of the MetaHuman class.
     */
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
     * Getter method of the attribute wTree.
     * @return the binary weapon tree.
     */
    public BinaryTree<Weapon> getwTree() {
        return wTree;
    }

    /**
     * Override toString method used to show the information of the SuperHero.
     * @return the String that contains the SuperHero information.
     */
    @Override
    public String toString() {
        return super.toString() + wTree.StringInOrder();
    }
}
