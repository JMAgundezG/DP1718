package GameCharacters;

import GameCharacters.Movement.Deliverable2Movement;
import GameCharacters.Movement.Movement;
import Game.Game;
import GameCharacters.Movement.VillainMovement;
import GameCharacters.WeaponFeatures.VillainsWFeature;
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
     * Public parametrized constructor of the class Villain.
     * @param name the attribute name of the Villain.
     * @param id the attribute id of the Villain.
     * @param turn the attribute turn of the Villain.
     */
    public Villain(String name, String id, int turn) {
        super(name, "Villain", id, Game.getSI().getMap().getNE(), turn);
        setWeaponFeature(new VillainsWFeature(this));
        setMovement(new VillainMovement(this));
    }

}
