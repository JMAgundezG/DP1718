package characters;
import characters.doorInteractions.DoorInteraction;
import characters.features.Feature;
import characters.movements.Movement;
import com.sun.org.apache.xalan.internal.utils.FeatureManager;
import game.Game;
import map.Map;

import java.util.*;

import tools.Dir;
import map.Room;


public abstract class Characters {


    private String name;
    private String id;
    private String family;
    private int numberOfRoom;

    /**
     * Saves if the character's can move
     */
    private Boolean actionFlag = true;

    /**
     * Saves the turn of the character
     */
    private int turn;

    /**
     * It will save the feature of the character
     */
    private Feature feature = null;

    /**
     * It says the type of interactions with the door of the character
     */
    private DoorInteraction doorInteraction= null;

    /**
     * It saves the movement feature of the character
     */
    private Movement movement = null;
    /**
     * Parametrized constructor of Characters class
     * @param name name of the character
     * @param family family of the character
     * @param id id of the character
     * @param numberOfRoom number of room that the character is
     * @param turn turn of the character
     */

    protected Characters(String name, String family, String id, int numberOfRoom, int turn){

        this.name = name;
        this.family = family;
        this.id = id;
        this.numberOfRoom = numberOfRoom;
        this.turn = turn;

    }

    /**
     * Setter of movement type
     * @param movement a movement object
     */
    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    /**
     * Getter of ID
     * @return id of the character
     */
    public String getId() {
        return id;
    }

    /**
     * Getter of the family
     * @return the family of the character
     */
    public String getFamily() {
        return family;
    }

    /**
     * Getter of the turn
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Actions method of the characters
     */
    public void actions() {
        if (getActionFlag()) {

            this.getDoorInteraction().doorAction();
            if(numberOfRoom != 1111) {
                this.getMovement().movementAction();
                this.getFeature().action();
            }
            this.setActionFlag(false);
        }
    }

    /**
     * Getter of the number of the room
     * @return number of room
     */
    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    /**
     * Setter of the number of the room
     * @param RoomNumber number of room
     */
    public void setNumberOfRoom(int RoomNumber){
        this.numberOfRoom = RoomNumber;
    }

    /**
     * Abstract class to get the features of each character
     * @return string with the features
     */
    public String showCharacter(){

        String message = "(" + this.family + ":" + this.id + ":" + this.getNumberOfRoom() +
                ":" + getTurn();
        if (feature != null)
            message += ":" + feature.showInfo();
        return message + ")";
    }

    /**
     * Setter of the Action boolean
     */
    public void setActionFlag(Boolean flag){
        this.actionFlag = flag;
    }

    /**
     * Getter of the Action boolean
     * @return Action
     */
    public boolean getActionFlag() {
        return actionFlag;
    }

    /**
     * Method that says if a character is in the throne Room
     * @return a boolean
     */
    public boolean isInThroneRoom() {
        Game game = Game.getSingletonInstance();
        return this.numberOfRoom == game.getMap().getThroneRoom();
    }

    /**
     * Add a turn to the character
     */
    public void addTurn(){
        this.turn++;
    }

    /**
     * getter of the name
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * getter of the movement object
     * @return the movement object
     */
    public Movement getMovement() {
        return movement;
    }

    /**
     * getter of the feature
     * @return the feature
     */
    public Feature getFeature() {
        return feature;
    }

    /**
     * setter of the feature
     * @param feature new feature
     */
    protected void setFeature(Feature feature) {
        this.feature = feature;
    }

    /**
     * getter of the door interaction
     * @return door interaction
     */
    public DoorInteraction getDoorInteraction() {
        return doorInteraction;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * setter of the door interaction
     * @param doorInteraction the door interaction type
     */
    protected void setDoorInteraction(DoorInteraction doorInteraction) {
        this.doorInteraction = doorInteraction;
    }
}
