package GameCharacters.Movement;


import Game.Game;
import GameCharacters.GameCharacter;
import Map.Map;
import Tools.Dir;
import Map.Square;

import java.util.LinkedList;

/**
 * Implementation of the Movement.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This is the class that will recreate every movement of every character in the game <br/>
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public abstract class Movement {

    /**
     * The character that will move through the map.
     */
    private GameCharacter character;

    /**
     * The list of movements of the character.
     */
    private LinkedList<Dir> movements;

    /**
     * Public parametrized constructor of the class Movement.
     * @param character the character attribute.
     * @param movements the movements linked list attribute.
     */
    public Movement(GameCharacter character, LinkedList<Dir> movements){

        this.character = character;
        this.movements = movements;

    }

    /**
     * Method that recreates the move of every character on the map.
     */
    protected void movement(){

        Map map = Game.getSI().getMap();

        int roomNumber = this.character.getPosition();
        Square sq  = map.getSquare(this.character.getPosition());
        if(!movements.isEmpty()) {
            Dir aux = movements.removeFirst();
            switch (aux) {
                case N:
                    if(map.availableMovement(roomNumber,Dir.N)) {
                        sq.dropCharacter(this.character);
                        sq = map.getSquare(map.getRowOfSquare(roomNumber) - 1, map.getColumnOfSquare(roomNumber));
                        sq.saveCharacter(this.character);
                    }
                    break;

                case S:
                    if (map.availableMovement(roomNumber,Dir.S)){
                        sq.dropCharacter(this.character);
                        sq = map.getSquare(map.getRowOfSquare(roomNumber) + 1, map.getColumnOfSquare(roomNumber));
                        sq.saveCharacter(this.character);

                    }
                    break;

                case E:
                    if(map.availableMovement(roomNumber,Dir.E)){
                        sq.dropCharacter(this.character);
                        sq = map.getSquare(map.getRowOfSquare(roomNumber), map.getColumnOfSquare(roomNumber) + 1);
                        sq.saveCharacter(this.character);
                    }
                    break;

                case W:
                    if (map.availableMovement(roomNumber,Dir.W)){
                        sq.dropCharacter(this.character);
                        sq = map.getSquare(map.getRowOfSquare(roomNumber), map.getColumnOfSquare(roomNumber) - 1);
                        sq.saveCharacter(this.character);
                    }
                    break;
            }
            this.character.setPosition(sq.getNumber());
        }
    }

    /**
     * Useful method when the character has a cyclic path.
     */
    public void movementAction(){
        movement();
    }

    /**
     * Getter of the attribute character.
     * @return the character attribute.
     */
    public GameCharacter getCharacter() {
        return character;
    }

    /**
     * Setter of the attribute character.
     * @param character the character attribute.
     */
    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    /**
     * Setter of the attribute movements
     * @param movements the movements attribute
     */
    public void setMovements(LinkedList<Dir> movements) {
        this.movements = movements;
    }

    /**
     * Getter of the attribute movements
     * @return the movements attribute
     */
    public LinkedList<Dir> getMovements() {
        return movements;
    }
}
