package GameCharacters;

import Game.Game;
import GameCharacters.Movement.VillainMovement;
import GameCharacters.WeaponFeatures.VillainsWFeature;

/**
 * Implementation of the Villain.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 3.0
 * Inherits from the GameCharacter class and provides the constructor for the villains.
 * In version 2.0, the constructor has been modified.
 * In version 3.0, we have modified the constructor once again.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class Villain extends GameCharacter {


    /**
     * Public parametrized constructor of the class Villain.
     * Generates the movement pattern and the weapon feature required.
     *
     * @param name the attribute name of the Villain.
     * @param id the attribute id of the Villain.
     * @param turn the attribute turn of the Villain.
     */
    public Villain(String name, String id, int turn) {
        super(name, "villain", id, Game.getSI().getMap().getNE(), turn);
        setWeaponFeature(new VillainsWFeature(this));
        setMovement(new VillainMovement(this));
    }

}
