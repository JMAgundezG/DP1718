package GameCharacters.Movement;
import Map.Path;
import GameCharacters.GameCharacter;

/**
 * Implementation of the SHESMovement class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the movement class. Performs the extrasensorial superhero movements.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class SHESMovement extends Movement{

    /**
     * Public parametrized constructor of the class Movement.
     * @param character the character attribute.
     */
    public SHESMovement(GameCharacter character) {
        super(character, Path.wallFollower(character.getPosition()));
    }
}
