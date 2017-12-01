package GameCharacters.Movement;

import GameCharacters.GameCharacter;
import Tools.Dir;

import java.util.LinkedList;

public class VillainMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     *
     * @param character the character attribute.
     */
    public VillainMovement(GameCharacter character) {
        super(character, Tools.Tools.VillainMovements());
    }
}
