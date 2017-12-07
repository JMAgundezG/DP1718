package GameCharacters;

import GameCharacters.Movement.Deliverable2Movement;
import GameCharacters.Movement.SHEPMovement;
import GameCharacters.WeaponFeatures.HeroesWFeature;
import GameCharacters.WeaponFeatures.WeaponFeature;

/**
 * Implementation of the SHPhysical class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the GameCharacter one. Provides the constructor for the physical heroes.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class SHPhysical extends GameCharacter{
    /**
     * Public constructor of the class SHPhysical.
     *
     * @param name     the attribute name of the GameCharacter.
     * @param id       the attribute id of the GameCharacter.
     * @param turn     the attribute turn of the GameCharacter.
     */
    public SHPhysical(String name, String id, int turn) {
        super(name, "SHPhysical", id, 0, turn);
        super.setMovement(new SHEPMovement(this));
        setWeaponFeature(new HeroesWFeature(this));
    }
}
