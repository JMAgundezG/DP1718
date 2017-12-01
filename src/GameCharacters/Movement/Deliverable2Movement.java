package GameCharacters.Movement;

import GameCharacters.GameCharacter;
import Tools.Dir;
import Tools.Tools;

import java.util.LinkedList;

public class Deliverable2Movement extends Movement {

    /**
     * Public parametrized constructor of the class Movement.
     *
     * @param character the character attribute.
     */
    public Deliverable2Movement(GameCharacter character) {
        super(character, Tools.D2Movements());
    }

    @Override
    public void movementAction() {
        if(getMovements().size() == 0)
            setMovements(Tools.D2Movements());
            super.movementAction();
    }
}
