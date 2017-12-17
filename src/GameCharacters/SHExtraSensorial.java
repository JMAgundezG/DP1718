package GameCharacters;

import GameCharacters.Movement.SHESMovement;
import GameCharacters.WeaponFeatures.HeroesWFeature;

/**
 * Implementation of the SHExtraSensorial class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the GameCharacter one. Provides the constructor for the sensitive heroes.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class SHExtraSensorial extends GameCharacter{
    /**
     * Public constructor of the class SHExtraSensorial.
     *
     * @param name     the attribute name of the GameCharacter.
     * @param id       the attribute id of the GameCharacter.
     * @param turn     the attribute turn of the GameCharacter
     * */
    public SHExtraSensorial(String name, String id, int turn) {
        super(name, "shextrasensorial", id, 0, turn);
        super.setMovement(new SHESMovement(this));
        setWeaponFeature(new HeroesWFeature(this));
    }
}
