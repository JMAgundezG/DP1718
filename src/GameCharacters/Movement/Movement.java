package GameCharacters.Movement;


import GameCharacters.GameCharacter;
import Map.Map;
import Tools.Dir;
import Map.Square;

import java.util.LinkedList;

public abstract class Movement {
    /**
     * Character that will move
     */
    private GameCharacter character;

    /**
     * The list of movements of the character
     */
    private LinkedList<Dir> movements;

    public Movement(GameCharacter character, LinkedList<Dir> movements){

        this.character = character;
        this.movements = movements;

    }

    /**
     * Method that make each character moves on the map
     */
    protected void movement(){

        Map map = Map.getSingleton();

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
                        sq = map.getSquare(map.getRowOfSquare(roomNumber) + 1, map.getColumnOfSquare(roomNumber) + 1);
                        sq.saveCharacter(this.character);
                    }
                    break;

                case O:
                    if (map.availableMovement(roomNumber,Dir.O)){
                        sq.dropCharacter(this.character);
                        sq = map.getSquare(map.getRowOfSquare(roomNumber) + 1, map.getColumnOfSquare(roomNumber) - 1);
                        sq.saveCharacter(this.character);
                    }
                    break;
            }
            this.character.setPosition(sq.getNumber());
        }
    }

    /**
     * That method will be useful when the character will have a cyclic path.
     */
    public void movementAction(){
        movement();
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    /**
     * Setter of path
     * @param movements list of movements of each character
     */
    public void setMovements(LinkedList<Dir> movements) {
        this.movements = movements;
    }

    /**
     * Getter of movements
     * @return the movements list
     */

    public LinkedList<Dir> getMovements() {
        return movements;
    }
}
