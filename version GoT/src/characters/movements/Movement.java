package characters.movements;
import characters.Characters;
import game.Game;
import map.Map;
import map.Room;
import tools.Dir;
import java.util.LinkedList;


public abstract class Movement {
    /**
     * Character that will move
     */
    private Characters character;

    /**
     * The list of movements of the character
     */
    private LinkedList<Dir> movements;

    public Movement(Characters character, LinkedList<Dir> movements){

        this.character = character;
        this.movements = movements;

    }

    /**
     * Method that make each character moves on the map
     */
    protected void movement(){

        Map map = Game.getSingletonInstance().getMap();

        int roomNumber = this.character.getNumberOfRoom();
        Room room  = map.findRoom(this.character.getNumberOfRoom());
        if(!movements.isEmpty()) {
            Dir aux = movements.removeFirst();
            switch (aux) {
                case N:
                    if(map.availableMovement(roomNumber,Dir.N)) {
                        room.getOutCharacter(this.character);
                        room = map.findRoom(room.getPosX() - 1, room.getPosY());
                        room.insertCharacter(this.character);
                    }
                    break;

                case S:
                    if (map.availableMovement(roomNumber,Dir.S)){
                        room.getOutCharacter(this.character);
                        room = map.findRoom(room.getPosX() + 1, room.getPosY());
                        room.insertCharacter(this.character);
                    }
                    break;

                case E:
                    if(map.availableMovement(roomNumber,Dir.E)){
                        room.getOutCharacter(this.character);
                        room = map.findRoom(room.getPosX(), room.getPosY() + 1);
                        room.insertCharacter(this.character);
                    }
                    break;

                case O:
                    if (map.availableMovement(roomNumber,Dir.O)){
                        room.getOutCharacter(this.character);
                        room = map.findRoom(room.getPosX(), room.getPosY() - 1);
                        room.insertCharacter(this.character);
                    }
                    break;
            }
            this.character.setNumberOfRoom(room.getNumberOfRoom());
        }
    }

    /**
     * That method will be useful when the character will have a cyclic path.
     */
    public void movementAction(){
        movement();
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
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
