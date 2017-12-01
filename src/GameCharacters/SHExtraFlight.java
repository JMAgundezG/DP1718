package GameCharacters;

import Game.Game;
import GameCharacters.Movement.Deliverable2Movement;
import GameCharacters.Movement.SHFMovement;
import GameCharacters.WeaponFeatures.HeroesWFeature;
import GameCharacters.WeaponFeatures.WeaponFeature;

public class SHExtraFlight extends GameCharacter {
    /**
     * Public constructor of the class GameCharacter.
     *
     * @param name     the attribute name of the GameCharacter.
     * @param id       the attribute id of the GameCharacter.
     * @param turn     the attribute turn of the GameCharacter.
     */
    public SHExtraFlight(String name, String id, int turn) {
        super(name, "SHExtraFlight", id,Game.getSI().getMap().getSW(), turn);
        setMovement(new SHFMovement(this));
        setWeaponFeature(new HeroesWFeature(this));
    }

}
