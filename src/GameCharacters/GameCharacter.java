package GameCharacters;


import GameCharacters.Movement.Movement;

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

    /**
     * Public constructor of the class GameCharacter.
     * @param name the attribute name of the GameCharacter.
     * @param type the attribute type of the GameCharacter.
     * @param id the attribute id of the GameCharacter.
     * @param pos the attribute position of the GameCharacter.
     * @param movement the attribute movement of the GameCharacter.
     */
    public GameCharacter(String name, String type, String id, int pos, Movement movement){
        this.name = name;
        this.id = id;
        this.position = pos;
        this.movement = movement;
        this.type = type;
    }

    /**
     * Abstract method that will enable the actions of using a weapon.
     */
    public abstract void useWeapon();


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

    /**
     * Override toString method used to show the information of the MetaHuman.
     * @return the String that contains the MetaHuman information.
     */
    @Override
    public String toString() {
        return name + ": " +  position+ ": ";
    }
}
