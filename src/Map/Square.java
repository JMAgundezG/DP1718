package Map;
// TODO CLASS DIAGRAM 2nd
import GameCharacters.GameCharacter;

import java.util.LinkedList;

/**
 * Implementation of the Square.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * These are the squares that form the map.
 * In the version 2.0, we have added the graph related attributes with the
 * corresponding setters and getters.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class Square {

    /**
     * Attribute that stores the number of the square.
     */
    private int number;

    /**
     * Attribute that stores the number of the square's node.
     */
    private int nodeNumber;

    /**
     * List used to store the information of the weapons that the square contains.
     */
    private LinkedList<Weapon> weaponList;

    /**
     * List used to store all the game characters that are in a square.
     */
    private LinkedList<GameCharacter> gameCharacters;

    /**
     * Public constructor of the class Square.
     * @param number The attribute number of the square.
     */
    public Square(int number){

        this.number = number;
        this.weaponList = new LinkedList<>();
        this.gameCharacters = new LinkedList<>();

    }

    /**
     * Stores a weapon into the attribute weaponList.
     * @param w weapon to store.
     */
    public void saveWeapon(Weapon w){
        //TODO SAVE BY POWER
        this.weaponList.addLast(w);

    }

    /**
     * Drops a weapon of the list.
     * @return the value that the square has dropped.
     */
    public Weapon dropWeapon(){
        Weapon w = weaponList.getFirst();
        weaponList.removeFirst();
        return w;
    }

    /**
     * Method that adds a character to the list.
     * @param gc the character to add.
     */
    public void saveCharacter(GameCharacter gc){
        gameCharacters.addLast(gc);
    }

    /**
     * Method that drops a character from the list.
     * @param gc the character to remove.
     */
    public void dropCharacter(GameCharacter gc){
        gameCharacters.remove(gc);
    }

    /**
     * Getter method of the attribute nodeNumber.
     * @return the attribute nodeNumber.
     */
    public int getNodeNumber() {
        return nodeNumber;
    }

    /**
     * Setter of the attribute nodeNumber.
     * @param nodeNumber the attribute nodeNumber.
     */
    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    /**
     * Getter of the attribute number.
     * @return the attribute number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter of the attribute number.
     * @param number the attribute number.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Override toString method used to show the information of the square.
     * @return the String that contains the Square information.
     */
    @Override
    public String toString() {
        String message = "(square:"+Integer.toString(number)+":";
        for (Weapon w: weaponList) {
          message+= " "+w.toString()+",";
        }
        return message+")";
    }
}
