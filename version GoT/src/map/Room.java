package map;

import characters.Characters;
import characters.WhiteWalkers;
import com.sun.org.apache.xpath.internal.operations.Bool;
import game.Game;

import java.util.*;

public class Room {

    private int numberOfRoom;
    private LinkedList<Characters> CharactersList = new LinkedList<>();
    private LinkedList<Key> keySet = new LinkedList<>();
    private int posX;
    private int posY;
    private int mark;


    public Room(int numberOfRoom, int posX, int posY) {
        this.numberOfRoom = numberOfRoom;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Insert a key in the keySet
     *
     * @param key the key
     */
    public void insertKey(Key key) {
        int i;
        Boolean ult = true;
        for(i = 0; i < keySet.size(); i++){
            if(keySet.get(i).getCode()> key.getCode()){
                ult = false;
                break;
            }
        }
        if(ult){
            keySet.addLast(key);
        }
        else {
        keySet.add(i,key);
        }
    }


    /**
     * Put a character in the room
     *
     * @param character the character we will insert
     */
    public void insertCharacter(Characters character) {
        this.CharactersList.add(character);
    }

    /**
     * Getter of the number of the room
     *
     * @return the number of the room
     */
    public int getNumberOfRoom() {
        return numberOfRoom;
    }


    /**
     * Remove the first character of the room
     *
     * @param character the character
     */
    public void getOutCharacter(Characters character) {

        CharactersList.remove(character);

    }


    /**
     * Make the simulation of a turn in the room
     */
    public void simulate() {
        if (!CharactersList.isEmpty()) {
            LinkedList<Characters> cl = (LinkedList<Characters>) CharactersList.clone();
            Characters Char;
            for (int i = 0; i < cl.size(); i++) {
                Char = getCharactersList().getFirst();//TODO SIGUE AQUI
                if (Char.getActionFlag()) {
                    Char.actions();
                    Char.setActionFlag(false);
                }
            }
        }
    }

    /**
     * getter of posX
     *
     * @return posx
     */
    public int getPosX() {
        return posX;
    }

    /**
     * getter of posY
     *
     * @return posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Method that returns the features of the room
     *
     * @return a string
     */
    public String WhoAreInTheRoom() {
        String CharacterIcon = "";
        if (getCharactersList().isEmpty()) {
            CharacterIcon += " ";
        } else if (getCharactersList().size() >= 2) {
            CharacterIcon = Integer.toString(getCharactersList().size());
        } else {
            CharacterIcon += getCharactersList().element().getId();
        }
        return CharacterIcon;
    }

    /**
     * verifies if it's the throne Room
     *
     * @return the verification
     */
    public boolean isThroneRoom() {

        Map map = Game.getSingletonInstance().getMap();
        return (map.getThroneDoor().getNumberOfRoom() == this.getNumberOfRoom());

    }

    /**
     * drop a key from the treeSet of keys
     *
     * @return the key dropped
     */
    public Key dropKey() {
        Key key = null;
        if (!this.keySet.isEmpty()) {
            key = this.keySet.removeFirst();
        }
        return key;
    }

    /**
     * verifies if there are any keys in the floor
     *
     * @return the verification
     */
    public boolean keysInFloor() {
        return !keySet.isEmpty();
    }

    /**
     * verifies if there are any characters in the room
     *
     * @return the verification
     */
    public boolean isEmpty() {
        return this.CharactersList.isEmpty();
    }

    /**
     * verifies if there are any humans in the room
     *
     * @return the verification
     */
    public boolean thereAreHumans() {
        for (Characters human : this.CharactersList) {
            if (!(human instanceof WhiteWalkers)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method that return the keys in a string
     *
     * @return a message string
     */
    public String showKeys() {
        String message = "";
        if (keySet.size() == 0) {
            return message;
        } else {
            message = Integer.toString(keySet.getFirst().getCode());
            for (int i = 1; i < keySet.size();i++) {
                message += " " + Integer.toString(keySet.get(i).getCode());
            }
            return ("square:" + getNumberOfRoom() + ": " + message);
        }
    }

    /**
     * @return the first human of the room
     */
    public Characters getHuman() {
        Characters character = null;
        if (thereAreHumans()) {
            for (Characters human : this.CharactersList) {
                if (!(human instanceof WhiteWalkers)) {
                    character = human;
                    break;
                }
            }
        }
        return character;
    }

    public LinkedList<Key> getKeySet() {
        return this.keySet;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public LinkedList<Characters> getCharactersList() {
        return CharactersList;
    }
}