package GameCharacters.Movement;

import GameCharacters.GameCharacter;
import Map.Path;

/**
 * Implementation of the SHEPMovement class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the movement class. Performs the physical superhero movements.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class SHEPMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     * @param character the character attribute.
     */
    public SHEPMovement(GameCharacter character) {
        super(character, Path.firstPosiblePath());
    }
}
