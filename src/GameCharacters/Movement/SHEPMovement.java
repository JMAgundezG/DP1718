package GameCharacters.Movement;

import GameCharacters.GameCharacter;
import Map.Path;

/**
 * Implementation of the SHEPMovement class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * This class inherits from the movement class. Performs the physical superhero movements.
 * In version 2.0 we have modified the constructor.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class SHEPMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     * Creates the path of the character using the firstPosiblePath.
     * @param character the character attribute of the Movement class.
     */
    public SHEPMovement(GameCharacter character) {
        super(character, Path.firstPosiblePath());
    }
}
