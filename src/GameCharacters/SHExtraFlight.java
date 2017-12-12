package GameCharacters;

import Game.Game;
import GameCharacters.Movement.Deliverable2Movement;
import GameCharacters.Movement.SHFMovement;
import GameCharacters.WeaponFeatures.HeroesWFeature;
import GameCharacters.WeaponFeatures.WeaponFeature;

/**
 * Implementation of the SHExtraFlight class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the GameCharacter one. Provides the constructor for the flying heroes.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class SHExtraFlight extends GameCharacter {
    /**
     * Public constructor of the class GameCharacter.
     *
     * @param name     the attribute name of the GameCharacter.
     * @param id       the attribute id of the GameCharacter.
     * @param turn     the attribute turn of the GameCharacter.
     */
    public SHExtraFlight(String name, String id, int turn) {
        super(name, "shflight", id,Game.getSI().getMap().getSW(), turn);
        setMovement(new SHFMovement(this));
        setWeaponFeature(new HeroesWFeature(this));
    }

}
