package GameCharacters;

import GameCharacters.Movement.Movement;
import Game.Game;
import Map.Weapon;

/**
 * Implementation of the Villain.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * Extends from the MetaHuman class and uses its methods.
 * In version 2.0, The constructor has been modified.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class Villain extends GameCharacter {

    /**
     * Attribute that contains the weapon of the villain.
     */
    private Weapon weapon;

    /**
     * Public parametrized constructor of the class Villain.
     * @param name the attribute name of the Villain.
     * @param type the attribute type of the Villain.
     * @param id the attribute id of the Villain.
     * @param pos the attribute position of the Villain.
     * @param movement the attribute movement of the Villain.
     */
    public Villain(String name, String type, String id, int pos, Movement movement) {
        super(name, type, id, pos, movement);
        this.weapon = null;
    }

    /**
     * Public parametrized constructor of the class Villain.
     * @param name the attribute name of the Villain.
     * @param type the type of GameCharacter. In this case, Villain.
     * @param id the attribute id of the Villain.
     * @param pos the attribute position of the Villain.
     * @param movement the Movement pattern of the Villain.
     * @param w the attribute weapon of the Villain.
     */
    public Villain(String name, String type, String id, int pos, Movement movement, Weapon w) {
        super(name, type, id, pos, movement);
        this.weapon = w;
    }

    /**
     * Implementation of the abstract method useWeapon of the MetaHuman class.
     */
    public void useWeapon(){
        if(this.getPosition() == Game.getSingletonInstance().getMap().getDailyPlanet()) {
           Game.getSingletonInstance().getMap().getDoorMan().tryWeapon(weapon);
        }
    }
}
