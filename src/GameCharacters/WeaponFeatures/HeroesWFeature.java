package GameCharacters.WeaponFeatures;

import Datastructures.BinaryTree;
import Game.Game;
import GameCharacters.GameCharacter;
import GameCharacters.Villain;
import Map.DoorMan;
import Map.Square;
import Map.Weapon;

import java.util.Comparator;

/**
 * Implementation of the HeroesWFeature class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the WeaponFeature class. Implements all the abstract methods.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class HeroesWFeature extends WeaponFeature{

    /**
     * BinaryTree that contains all the weapons of the SuperHeroes
     */
    private BinaryTree<Weapon> wTree;

    /**
     * Integer that will contain the information of the villains that the superhero has caught.
     */
    private int caughtVillains;

    /**
     * Public parametrized constructor of the HeroesWFeature class.
     * @param gc the attribute gc of the WeaponFeature.
     */
    public HeroesWFeature(GameCharacter gc){
        super(gc);
        wTree = new BinaryTree<>();
        caughtVillains = 0;
    }

    /**
     * Implementation of the weaponAction method.
     * If the character using the weapon is in the last room, he uses it.
     */
    public void weaponAction() {

        DoorMan d = Game.getSI().getMap().getDoorMan();
        if (getGc().getPosition() == Game.getSI().getMap().getDailyPlanet()) {
            Weapon bestWeapon = wTree.mostValuedNode(Comparator.comparingInt(Weapon::getPower));
            if (bestWeapon != null && !d.tryWeapon(bestWeapon)) {
                wTree.delete(bestWeapon);
            }
        }
    }

    /**
     * Implementation of the interact method.
     * If the SuperHero shares the room with a Villain, both have the same weapon and the superhero's
     * one has bigger power, he catches the villain.
     */
    public void interact(){
        Square sq = Game.getSI().getMap().getSquare(getGc().getPosition());
        for (int i = 0; i < sq.getGameCharacters().size(); i++) {
            GameCharacter gc = sq.getGameCharacters().get(i);
            if (gc instanceof Villain) {
                Weapon villainWeapon = ((VillainsWFeature) gc.getWeaponFeature()).getWeapon();
                if (villainWeapon == null || wTree.belongs(villainWeapon)) {
                    if(!wTree.empty()) {
                        if (villainWeapon == null || wTree.extract(villainWeapon).getPower() > villainWeapon.getPower()) {
                            Game.getSI().capture(gc);
                            caughtVillains++;
                        }
                    }
                }
            break;
            }
        }
    }

    /**
     * Implementation of the takeWeapon method.
     * Takes the weapon from the current square.
     */
    public void takeWeapon(){

        Square s = Game.getSI().getMap().getSquare(getGc().getPosition());
        Weapon newWeapon = s.dropWeapon();
        if( newWeapon != null) {
            if (wTree.belongs(newWeapon)) {
                newWeapon = new Weapon(newWeapon.getName(), wTree.extract(newWeapon).getPower() + newWeapon.getPower());
            }
            this.wTree.insertData(newWeapon);
        }
    }

    /**
     * Getter of the wTree attribute.
     * @return the wTree attribute.
     */
    public BinaryTree<Weapon> getwTree() {
        return wTree;
    }

    /**
     * Override toString method used to show the information of the HeroesWFeature.
     * @return the String that contains the HeroesWFeature information.
     */
    @Override
    public String toString() {
        return wTree.StringInOrder();
    }
}
