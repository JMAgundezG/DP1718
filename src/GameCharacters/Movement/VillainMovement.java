package GameCharacters.Movement;

import GameCharacters.GameCharacter;
import Map.Path;
import Tools.Dir;

import java.util.LinkedList;

/**
 * Implementation of the VillainMovement class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the movement class. Performs the villain movements.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class VillainMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     *
     * @param character the character attribute.
     */
    public VillainMovement(GameCharacter character) {
        super(character, Path.wallFollowerLeftHanded(character.getPosition()));
    }
}
