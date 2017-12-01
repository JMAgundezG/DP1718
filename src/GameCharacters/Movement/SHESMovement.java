package GameCharacters.Movement;

import GameCharacters.GameCharacter;


public class SHESMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     *
     * @param character the character attribute.
     */
    public SHESMovement(GameCharacter character) {
        super(character, Tools.Tools.SHESMovements());
    }
}
