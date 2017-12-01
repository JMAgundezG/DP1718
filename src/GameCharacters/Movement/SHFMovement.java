package GameCharacters.Movement;

import GameCharacters.GameCharacter;

public class SHFMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     *
     * @param character the character attribute.
     *
     */
    public SHFMovement(GameCharacter character) {
        super(character, Tools.Tools.SHFMovements());
    }
}
