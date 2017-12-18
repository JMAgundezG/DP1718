package GameCharacters;

import GameCharacters.Movement.SHEPMovement;
import GameCharacters.WeaponFeatures.HeroesWFeature;

/**
 * Implementation of the SHPhysical class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * This class inherits from the GameCharacter one. Provides the constructor for the physical heroes.
 * In version 2.0, we have modified the constructor.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class SHPhysical extends GameCharacter{

    /**
     * Public constructor of the class SHPhysical.
     * Generates the movement pattern and the weapon feature required.
     *
     * @param name     the attribute name of the GameCharacter.
     * @param id       the attribute id of the GameCharacter.
     * @param turn     the attribute turn of the GameCharacter.
     */
    public SHPhysical(String name, String id, int turn) {
        super(name, "shphysical", id, 0, turn);
        super.setMovement(new SHEPMovement(this));
        setWeaponFeature(new HeroesWFeature(this));
    }
}
