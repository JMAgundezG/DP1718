package GameCharacters.Movement;

import GameCharacters.GameCharacter;

public class SHEPMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     *
     * @param character the character attribute.
     *
     */
    public SHEPMovement(GameCharacter character) {
        super(character, Tools.Tools.SHEPMovements() );
    }
}
