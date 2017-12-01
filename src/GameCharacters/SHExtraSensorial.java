package GameCharacters;

import GameCharacters.Movement.Deliverable2Movement;
import GameCharacters.Movement.SHESMovement;
import GameCharacters.WeaponFeatures.HeroesWFeature;
import GameCharacters.WeaponFeatures.WeaponFeature;

public class SHExtraSensorial extends GameCharacter{
    /**
     * Public constructor of the class SHExtraSensorial.
     *
     * @param name     the attribute name of the GameCharacter.
     * @param id       the attribute id of the GameCharacter.
     * @param turn     the attribute turn of the GameCharacter
     * */
    public SHExtraSensorial(String name, String id, int turn) {
        super(name, "SHEXtraSensorial", id, 0, turn);
        super.setMovement(new SHESMovement(this));
        setWeaponFeature(new HeroesWFeature(this));
    }
}
