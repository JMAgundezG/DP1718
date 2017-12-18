package GameCharacters;

import Game.Game;
import GameCharacters.Movement.SHFMovement;
import GameCharacters.WeaponFeatures.HeroesWFeature;

/**
 * Implementation of the SHExtraFlight class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * This class inherits from the GameCharacter one. Provides the constructor for the flying heroes.
 * In version 2.0, we have modified the constructor.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class SHExtraFlight extends GameCharacter {
    /**
     * Public parametrized constructor of the class GameCharacter.
     * Generates the movement pattern and the weapon feature required.
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
