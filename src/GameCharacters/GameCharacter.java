package GameCharacters;


import Game.Game;
import GameCharacters.Movement.Movement;
import GameCharacters.WeaponFeatures.WeaponFeature;

/**
 * Implementation of the GameCharacter.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * This is the superclass of the SuperHero and the Villain.
 * Contains the needed abstract methods.
 * It will also have the main constructor and the main toString.
 * In version 2.0 we have introduced new attributes
 * and we have also modified the constructor.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
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

    private int turn;

    private boolean action;

    private WeaponFeature weaponFeature;
    /**
     * Public constructor of the class GameCharacter.
     * @param name the attribute name of the GameCharacter.
     * @param type the attribute type of the GameCharacter.
     * @param id the attribute id of the GameCharacter.
     * @param pos the attribute position of the GameCharacter.
     * @param turn the attribute turn of the GameCharacter.
     */
    public GameCharacter(String name, String type, String id, int pos, int turn){
        this.name = name;
        this.id = id;
        this.position = pos;
        this.type = type;
        this.turn = turn;
        this.action = true;
    }



    /**
     * Getter method of the attribute name.
     * @return the name of the GameCharacter.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method of the attribute position.
     * @return the current position of the GameCharacter.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Getter method of the attribute id.
     * @return the id of the GameCharacter.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter method of the attribute movement.
     * @return the movement pattern of the GameCharacter.
     */
    public Movement getMovement() { return movement; }

    /**
     * Getter method of the attribute type.
     * @return the type of GameCharacter.
     */
    public String getType() { return type; }

    public int getTurn() {
        return turn;
    }

    public WeaponFeature getWeaponFeature() {
        return weaponFeature;
    }


    /**
     * Setter method of the attribute id.
     * @param id the attribute id of the GameCharacter.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Setter method of the attribute name.
     * @param name the attribute name of the GameCharacter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method of the attribute position.
     * @param position the attribute position of the GameCharacter.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Setter method of the attribute movement.
     * @param movement the attribute movement of the GameCharacter.
     */
    public void setMovement(Movement movement) { this.movement = movement; }

    /**
     * Setter method of the attribute type.
     * @param type the attribute type of the GameCharacter.
     */
    public void setType(String type) { this.type = type; }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setWeaponFeature(WeaponFeature weaponFeature) {
        this.weaponFeature = weaponFeature;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public boolean isAction() {
        return action;
    }

    /**
     * Override toString method used to show the information of the MetaHuman.
     * @return the String that contains the MetaHuman information.
     */
    @Override
    public String toString() {
        return "(" + type.toLowerCase() + ":" + id +":" +position+ ":"  + turn + ":" + weaponFeature.toString() + ")";
    }

    /**
     *NEED COMMENTS
     */
    public void actions(){
        if(Game.getSI().getTurn() >= turn && !Game.getSI().getCapturedCharacters().contains(this) && action) {
            weaponFeature.weaponAction();
            movement.movementAction();
            weaponFeature.takeWeapon();
            weaponFeature.interact();
            action = false;
        }
    }



}
