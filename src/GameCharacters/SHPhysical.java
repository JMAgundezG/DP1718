package GameCharacters;

import GameCharacters.Movement.Deliverable2Movement;
import GameCharacters.Movement.SHEPMovement;
import GameCharacters.WeaponFeatures.HeroesWFeature;
import GameCharacters.WeaponFeatures.WeaponFeature;

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
