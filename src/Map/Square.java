package Map;

import Game.Game;
import GameCharacters.GameCharacter;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Implementation of the Square.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 4.0
 * These are the squares that form the map.
 * In the version 2.0, we have added the graph related attributes with the
 * corresponding setters and getters.
 * In the version 3.0, we have added some getters and the needed methods to print the information
 * of the characters and the weapons.
 * In the version 4.0, we have added some more features useful to print the characters and their paths.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
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
        this.weaponList.add(w);
        this.weaponList.sort(Comparator.comparingInt(x -> -x.getPower()));
    }

    /**
     * Drops a weapon of the list.
     * @return the value that the square has dropped.
     */
    public Weapon dropWeapon(){
        Weapon w = null;
        if(weaponList.size() > 0) {
            w = weaponList.getFirst();
            weaponList.removeFirst();
        }
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
     * If a room has 2 or more characters, prints the number instead of the Chars.
     * @return the string containing the number of characters or a char if it is only one.
     */
    public String stringOfCharacters(){
            String CharacterIcon = "";
            if (gameCharacters.isEmpty()) {
                CharacterIcon += " ";
            } else if (gameCharacters.size() >= 2) {
                CharacterIcon = Integer.toString(gameCharacters.size());
            } else {
                CharacterIcon += gameCharacters.element().getId();
            }
            return CharacterIcon;
        
    }

    /**
     * Getter of the weaponList attribute.
     * @return the weaponList attribute.
     */
    public LinkedList<Weapon> getWeaponList() {
        return weaponList;
    }

    /**
     * Getter of the gameCharacters attribute.
     * @return the gameCharacters attribute.
     */
    public LinkedList<GameCharacter> getGameCharacters() {
        return gameCharacters;
    }

    /**
     * Method that turns the weapons of the square into strings.
     * @return the string that contains the information of the weapons.
     */
    public String showWeapons() {
        String message = "";
        if (weaponList.size() == 0) {
            return message;
        } else {
            message = getWeaponList().getFirst().toString();
            for (int i = 1; i < getWeaponList().size();i++) {
                message += getWeaponList().get(i).toString();
            }
            return ("square:" + number + ":" + message);
        }
    }

    /**
     * Method that simulates the turns of every character.
     */
    public void simulate(){
        LinkedList<GameCharacter> gcs = (LinkedList<GameCharacter>) gameCharacters.clone();
        for (GameCharacter c : gcs) {
            if (!Game.getSI().getCapturedCharacters().contains(c)) {
                c.actions();
            }
        }
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

    /**
     * Method that shows the initial information of the characters that are in the square.
     * @return a string containing all the information of the characters.
     */
    public String showInitialCharacters() {
        String message = "";
        for (GameCharacter c : getGameCharacters()) {
            message += showCreatedCharacter(c) + "\n";
        }
        return message;
    }

    /**
     * Method that shows the information of the path that each character has.
     * @param c the game character that has the path we want to show.
     * @return a string containing the information of the path of the character c.
     */
    private String showCreatedCharacter(GameCharacter c) {
        String message = "(path:" + c.getId() + ":";
        message += Path.showPath(c.getMovement().getMovements()) + ")";
        return message;
    }
}
