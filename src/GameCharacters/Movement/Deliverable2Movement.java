package GameCharacters.Movement;

import GameCharacters.GameCharacter;
import Tools.Dir;
import Tools.Tools;

import java.util.LinkedList;

/**
 * Implementation of the Deliverable2Movement class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the Movement class.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class Deliverable2Movement extends Movement {

    /**
     * Public parametrized constructor of the class Movement.
     *
     * @param character the character attribute.
     */
    public Deliverable2Movement(GameCharacter character) {
        super(character, Tools.D2Movements());
    }

    /**
     * Sets the movement and performs it.
     */
    @Override
    public void movementAction() {
        if(getMovements().size() == 0)
            setMovements(Tools.D2Movements());
            super.movementAction();
    }
}
