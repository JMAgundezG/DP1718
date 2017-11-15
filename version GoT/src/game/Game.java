package Map;

import characters.Characters;
import characters.Lannister;
import characters.WhiteWalkers;
import characters.features.KeyPicker;
import map.Map;
import map.Room;
import sun.awt.image.ImageWatched;
import tools.Path;
import tools.Tools;

import java.io.*;
import java.util.LinkedList;


public class Game {
    private Map map;
    private LinkedList<Characters> CharactersInGame;
    private LinkedList<Characters> AllCharacters;
    private LinkedList<Characters> DeadCharacters;
    private int turn;
    private static Game game = null;
    private boolean finished = false;

    private Game(int dimX, int dimY, int throneRoom, int depth) {
        this.map = new Map(dimX, dimY, throneRoom, depth);
        this.CharactersInGame = new LinkedList<>();
        this.AllCharacters = new LinkedList<>();
        this.DeadCharacters = new LinkedList<>();
        this.turn = 0;
    }


    public static Game getSingletonInstance(int dimX, int dimY, int throneRoom, int depth) {
        if (game == null) {
            game = new Game(dimX, dimY, throneRoom, depth);
        }
        return game;
    }

    public static Game getSingletonInstance() {
        return game;
    }

    public Map getMap() {
        return map;
    }

    /**
     * Add a character to the map
     *
     * @param character the character we will add
     */
    public void addCharacter(Characters character) {
        CharactersInGame.addLast(character);
    }

    /**
     * Simulate a turn of the Map
     */
    public void simulateATurn() {
        addPlayersToMap();
        for (int i = 0; i < map.getNumberOfRooms(); i++) {
            Room room = map.findRoom(i);
            if (!room.isEmpty()) {
                LinkedList<Characters> players = (LinkedList) room.getCharactersList().clone();
                for (Characters c : players) {
                    if (CharactersInGame.contains(c)) {
                        c.actions();
                    }
                }
            }
        }
        removePlayersFromMap();
    }


    /**
     * Sets all the characters' action bool as true
     */
    public void setActionFlagTrue() {
        for (Characters character : this.CharactersInGame) {
            character.setActionFlag(true);
            character.setTurn(this.turn);
        }
    }

    /**
     * Simulate the Map
     */

    public void simulateGame() {
        String message = map.getInitialMap();
        for (int i = 0; i < map.getNumberOfRooms(); i++) {
            Room r = map.findRoom(i);
            if (!r.isEmpty()) {
                for (Characters c : r.getCharactersList()) {
                    message += showCreatedCharacter(c) + "\n";
                    System.out.println(showCreatedCharacter(c));
                }
            }
        }
        while (turn < Tools.MaxTurn && map.getWinRoom().isEmpty()) {
            simulateATurn();
            message += showGame();
            System.out.print(showGame());
            turn++;
            setActionFlagTrue();

        }

        message += winMessage();
        System.out.println(winMessage());
        Tools.writeGame(message);
        finished = true;
    }

    /**
     *
     * @return the final message of the Map
     */
    public String winMessage() {
        String message = "(thronemembers)";
        if (!map.getWinRoom().getCharactersList().isEmpty()) {
            Characters newking = map.getWinRoom().getCharactersList().getFirst();
            message += "\n(newking:" + newking.getFamily() + ":" + newking.getId() +
                    ":" + "1111:" + (newking.getTurn() - 1);
            KeyPicker kingF = (KeyPicker) newking.getFeature();
            message += ":" + kingF.showInfo() + ")";
            for (int i = 1; i < map.getWinRoom().getCharactersList().size(); i++) {
                Characters c = map.getWinRoom().getCharactersList().get(i);

                message += "\n" + "(" + c.getFamily() + ":" + c.getId() +
                        ":" + "1111:" + (c.getTurn() - 1) + ":" +
                        c.getFeature().showInfo() + ")";

            }
        }
        return message;
    }

    /**
     * Getter of the turn
     *
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Method that makes a message with all the features of the Map in that moment
     *
     * @return
     */
    private String showGame() {

        String message = "";
        message += "(turn:" + Integer.toString(getTurn()) + ")\n";
        message += "(map:" + Integer.toString(map.getThroneDoor().getNumberOfRoom()) + ")\n";
        message += this.map.getThroneDoor().showDoor() + "\n";
        message += this.map.showMap();

        Room r;
        for (int i = 0; i < this.map.getNumberOfRooms(); i++) {
            r = this.map.findRoom(i);
            if (!r.isEmpty()) {
                for (Characters ch : r.getCharactersList()) {
                    message += ch.showCharacter() + "\n";
                }
            }
        }

        return message;
    }


    /**
     * Put a character in the Map
     *
     * @param character
     */
    public void putACharacterInTheGame(Characters character) {
        AllCharacters.addLast(character);
        Room room = this.getMap().findRoom(character.getNumberOfRoom());
        room.insertCharacter(character);
    }

    /**
     * Verifies if the characters that isn't in the Map should start playing and
     * inserts in the Map if it's necessary
     */
    private void addPlayersToMap() {
        for (Characters character : AllCharacters) {

            if (character.getTurn() == this.turn && !CharactersInGame.contains(character)) {
                addCharacter(character);
            }
        }
    }

    /**
     * Remove the dead players of the Map
     */
    private void removePlayersFromMap() {
        for (Characters c : DeadCharacters) {
            if (this.CharactersInGame.contains(c)) {
                this.CharactersInGame.remove(c);
                this.map.findRoom(c.getNumberOfRoom()).getCharactersList().remove(c);
                this.map.killHuman(c);
                this.AllCharacters.remove(c);
            }
            if (this.AllCharacters.contains(c)) {
                this.AllCharacters.remove(c);
            }
        }
    }

    /**
     *Kills a human
     */
    public void killHuman(Characters human) {
        this.DeadCharacters.add(human);
    }

    /**
     * Show the initial message of a character
     */
    private String showCreatedCharacter(Characters c) {
        String message = "(path:" + c.getId() + ":";
        message += Path.showPath(c.getMovement().getMovements()) + ")";
        return message;
    }

    public boolean isFinished() {
        return finished;
    }
}
