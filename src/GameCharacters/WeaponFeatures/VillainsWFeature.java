package GameCharacters.WeaponFeatures;

import Datastructures.BinaryTree;
import Game.Game;
import GameCharacters.GameCharacter;
import GameCharacters.Villain;
import Map.Square;
import Map.Weapon;

/**
 * Implementation of the VillainsWFeature class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the WeaponFeature class. Implements all the abstract methods.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class VillainsWFeature extends WeaponFeature {

    /**
     * Attribute that contains the weapon of the villain
     */
    Weapon weapon;

    /**
     * Public parametrized constructor of the class VillainsWFeature.
     * @param gc
     */
    public VillainsWFeature(GameCharacter gc) {
        super(gc);
        weapon = null;
    }

    /**
     * Implementation of the takeWeapon method.
     * Takes the weapon from the current square.
     * If a new coming weapon is better than the current one, replaces it.
     * If the villain has no weapon, he takes it.
     */
    @Override
    public void takeWeapon() {
        Square s = Game.getSI().getMap().getSquare(getGc().getPosition());
        Weapon sqWeapon = s.dropWeapon();
        if(sqWeapon != null) {
            if(weapon != null)
                if (sqWeapon.getPower() > weapon.getPower()) {
                   s.saveWeapon(weapon);
                   weapon = sqWeapon;
                } else {
                s.saveWeapon(sqWeapon);
            }
            else {
                weapon = sqWeapon;
            }
        }
    }

    /**
     * Implementation of the interact method.
     * If the SuperHero shares the room with a Villain, both have the same weapon and the superhero's
     * one has bigger power, he catches the villain.
     */
    @Override
    public void interact() {
        if(weapon != null) {
            Square sq = Game.getSI().getMap().getSquare(getGc().getPosition());
            for (int i = 0; i < sq.getGameCharacters().size(); i++) {
                GameCharacter gc = sq.getGameCharacters().get(i);
                if (!(gc instanceof Villain)) {
                    BinaryTree<Weapon> wTree = ((HeroesWFeature) gc.getWeaponFeature()).getwTree();
                    if (wTree.belongs(weapon)) {
                        if (wTree.extract(weapon).getPower() < weapon.getPower()) {
                            wTree.delete(weapon);
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Implementation of the weaponAction method.
     * If the character using the weapon is in the last room, he uses it.
     */
    @Override
    public void weaponAction() {
        if (this.getGc().getPosition() == Game.getSI().getMap().getDailyPlanet() && weapon != null) {
            Game.getSI().getMap().getDoorMan().tryWeapon(weapon);
        }
    }

    /**
     * Getter of the weapon attribute.
     * @return the weapon attribute.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Setter of the weapon attribute.
     * @param weapon the weapon attribute.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Override toString method used to show the information of the VillainsWFeature.
     * @return the String that contains the VillainsWFeature information.
     */
    public String toString(){
        if(weapon != null) {
            return weapon.toString();
        }
        return "";
    }
}
