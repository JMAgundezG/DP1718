package GameCharacters;


import Game.Game;
import GameCharacters.Movement.Movement;
import GameCharacters.WeaponFeatures.WeaponFeature;
import Map.Square;

/**
 * Implementation of the GameCharacter.
 *
 * @author José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 3.0
 * This is the superclass of the SuperHero and the Villain.
 * Contains the needed abstract methods.
 * It will also have the main constructor and the main toString.
 * In version 2.0, we have introduced new attributes
 * and we have also modified the constructor.
 * In version 3.0, we have added some "security methods" like the actions one.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public abstract class GameCharacter {

    /**
     * Attribute that contains the name of the GameCharacter.
     */
    private String name;

    /**
     * Attribute that contains the id of the GameCharacter.
     */
    private String id;

    /**
     * Attribute that contains the current position of the GameCharacter.
     */
    private int position;

    /**
     * Attribute that contains the movement pattern of the GameCharacter.
     */
    private Movement movement;

    /**
     * Attribute that contains the type of the GameCharacter.
     * In this case, it is either a SuperHero or a Villain.
     */
    private String type;

    /**
     * Attribute that contains the information of the current turn of the GameCharacter
     */
    private int turn;

    /**
     * Boolean that contains the information about the character. If he has performed an action the current turn or not.
     */
    private boolean action;

    /**
     * Auxiliar attribute able to perform every weapon feature.
     */
    private WeaponFeature weaponFeature;

    /**
     * Public constructor of the class GameCharacter.
     *
     * @param name the attribute name of the GameCharacter.
     * @param type the attribute type of the GameCharacter.
     * @param id   the attribute id of the GameCharacter.
     * @param pos  the attribute position of the GameCharacter.
     * @param turn the attribute turn of the GameCharacter.
     */
    public GameCharacter(String name, String type, String id, int pos, int turn) {
        this.name = name;
        this.id = id;
        this.position = pos;
        this.type = type;
        this.turn = turn;
        this.action = true;
    }


    /**
     * Getter method of the attribute name.
     *
     * @return the name of the GameCharacter.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method of the attribute name.
     *
     * @param name the attribute name of the GameCharacter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method of the attribute position.
     *
     * @return the current position of the GameCharacter.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setter method of the attribute position.
     *
     * @param position the attribute position of the GameCharacter.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Getter method of the attribute id.
     *
     * @return the id of the GameCharacter.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method of the attribute id.
     *
     * @param id the attribute id of the GameCharacter.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method of the attribute movement.
     *
     * @return the movement pattern of the GameCharacter.
     */
    public Movement getMovement() {
        return movement;
    }

    /**
     * Setter method of the attribute movement.
     *
     * @param movement the attribute movement of the GameCharacter.
     */
    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    /**
     * Getter method of the attribute type.
     *
     * @return the type of GameCharacter.
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method of the attribute type.
     *
     * @param type the attribute type of the GameCharacter.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter of the turn attribute.
     *
     * @return the turn attribute.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Setter of the turn attribute.
     *
     * @param turn the turn attribute.
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Getter of the weaponFeature attribute.
     *
     * @return the weaponFeature attribute.
     */
    public WeaponFeature getWeaponFeature() {
        return weaponFeature;
    }

    /**
     * Setter of the weaponFeature attribute.
     *
     * @param weaponFeature the weaponFeature attribute.
     */
    public void setWeaponFeature(WeaponFeature weaponFeature) {
        this.weaponFeature = weaponFeature;
    }

    /**
     * Getter of the action attribute.
     *
     * @return the action attribute.
     */
    public boolean isAction() {
        return action;
    }

    /**
     * Setter of the action attribute.
     *
     * @param action the action attribute.
     */
    public void setAction(boolean action) {
        this.action = action;
    }

    /**
     * Override toString method used to show the information of the GameCharacter.
     *
     * @return the String that contains the GameCharacter information.
     */
    @Override
    public String toString() {
        return "(" + type.toLowerCase() + ":" + id + ":" + position + ":" + turn + ":" + weaponFeature.toString() + ")";
    }

    /**
     * Method that allows the Game characters to perform actions.
     * If they are any turn behind the current map turn, they have not been captured
     * and they have still an action left to perform, they will perform it.
     * If they enter the winning room, they stop. Otherwise, they perform the normal actions.
     */
    public void actions() {
        if (Game.getSI().getTurn() >= turn && !Game.getSI().getCapturedCharacters().contains(this) && action) {
            weaponFeature.dailyPlanetAction();
            if(position != 1111) {
                movement.movementAction();
                weaponFeature.takeWeapon();
                weaponFeature.interact();
            }
            action = false;
        }

    }

    /**
     * Introduces the Game character into the winning room.
     * Drops the character from the square and places him into the winning room (1111).
     */
    public void insertIntoWinningRoom() {
        Square s = Game.getSI().getMap().getSquare(getPosition());
        s.dropCharacter(this);
        Game.getSI().getMap().getWinnersSquare().saveCharacter(this);
        setPosition(1111);

    }
}
