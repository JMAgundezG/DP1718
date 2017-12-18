package Game;

import GameCharacters.GameCharacter;

import Map.Map;
import Map.Square;
import Map.Path;
import java.util.LinkedList;

import Tools.Tools;

/**
 * Implementation of the Game Class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * Class used to simulate the game.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class Game {

    /**
     * Attribute that contains the map
     */
    private Map map;

    /**
     * Attribute that contains the current turn
     */
    private int turn;

    /**
     * Attribute that contains the singleton static instance of the game.
     */
    private static Game game = null;

    /**
     * Boolean that contains the information of the game, whether it's finished or not.
     */
    private boolean finished;

    /**
     * Attribute that contains all the information of the characters.
     */
    private LinkedList<GameCharacter> characters;

    /**
     * Attribute that contains all the information of the characters that have been captured.
     */
    private LinkedList<GameCharacter> capturedCharacters;

    /**
     * Public parametrized constructor of the game class
     * @param row the attribute row of the map.
     * @param col the attribute column of the map.
     * @param doorManSquare the last room of the map.
     * @param depth the depth of the doorman's tree.
     */
    private Game(int row, int col, int doorManSquare, int depth) {
        this.map = new Map(row, col, doorManSquare, depth);
        this.turn = 0;
        this.characters = new LinkedList<>();
        this.capturedCharacters = new LinkedList<>();
        this.finished = false;
    }

    /**
     * Getter of the singleton instance.
     * @param row the row attribute of the map.
     * @param col the column attribute of the map.
     * @param doorManSquare the last room of the map.
     * @param depth the depth of the doorman's tree.
     * @return the singleton instance of the game.
     */
    public static Game getSI(int row, int col, int doorManSquare, int depth) {
        if (game == null) {
            game = new Game(row, col, doorManSquare, depth);
        }
        return game;
    }

    /**
     * Getter of the game attribute.
     * @return the game attribute.
     */
    public static Game getSI() {
        return game;
    }

    /**
     * Getter of the map attribute.
     * @return the map attribute.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Method that inserts a character in the map.
     * @param gc the game character.
     */
    public void insertCharacter(GameCharacter gc){
        characters.addLast(gc);
        map.getSquare(gc.getPosition()).saveCharacter(gc);

    }

    /**
     * Method that introduces a new captured character.
     * @param gc the game character that has been captured.
     */
    public void capture(GameCharacter gc){
        this.capturedCharacters.add(gc);
        gc.setAction(false);
    }

    /**
     * Simulates a turn of the Map
     */
    public void simulateATurn() {

        for (int i = 0; i < map.getColumns()*map.getRows(); i++) {
            Square sq = map.getSquare(i);
            if (sq.getGameCharacters().size()>0) {
                sq.simulate();
            }
        }
        removePlayersFromMap();
    }

    /**
     * Removes all the captured characters of the map.
     */
    public void removePlayersFromMap(){
        for (GameCharacter gc : capturedCharacters) {
            if(gc.getPosition() != -1){
                map.getSquare(gc.getPosition()).dropCharacter(gc);
                gc.setPosition(-1);
            }
        }
    }
//
//
//    /**
//     * Sets all the characters' action bool as true
//     */
//    public void setActionFlagTrue() {
//        for (Characters character : this.CharactersInGame) {
//            character.setActionFlag(true);
//            character.setTurn(this.turn);
//        }
//    }
//
    /**
     * Simulate the Map
     */


    /**
     * Simulates the whole game.
     */
    public void simulateGame() {
        String message = map.getMapWithPercentajeOfWalls();
        message += map.createdGameCharacters();
        boolean winningCharacters = false;
        while (turn < Tools.MAX_TURN && !winningCharacters) {
            setActionsTrue();
            simulateATurn();
            message += toString();
            System.out.print(toString());
            turn++;
            winningCharacters = map.getWinnersSquare().getGameCharacters().size() > 0;
        }
        message += winMessage();
        System.out.println(winMessage());
        Tools.writeGame(message);
    }

    /**
     * The characters have already performed an action this turn.
     */
    public void setActionsTrue(){
        for (GameCharacter c: this.characters) {
            if (!c.isAction()) {
                c.setAction(true);
                c.setTurn((c.getTurn() + 1));
            }
        }
    }


    public String winMessage() {
        String message = "(teseractomembers)";
        if (!map.getWinnersSquare().getGameCharacters().isEmpty()) {
            GameCharacter owner = map.getWinnersSquare().getGameCharacters().getFirst();

            message += "\n(owneroftheworld:" + owner.getType() + ":" + owner.getId() +
                    ":" + owner.getPosition() +":" + owner.getTurn() + ":" +
                    owner.getWeaponFeature().toString() + ")";

            for (int i = 1; i < map.getWinnersSquare().getGameCharacters().size(); i++) {
                GameCharacter gc = map.getWinnersSquare().getGameCharacters().get(i);

                message +="\n(" + gc.getType() + ":" + gc.getId() +
                        ":" + gc.getPosition() +":" + gc.getTurn() +
                        ":" + gc.getWeaponFeature().toString() + ")";

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
//
//    /**
//     * Method that makes a message with all the features of the Map in that moment
//     *
//     * @return
//     */
    public String toString() {

        String message = "";
        message += "(turn:" + Integer.toString(getTurn()) + ")\n";
        message += "(map:" + Integer.toString(map.getDailyPlanet())+ ")\n";
        message += this.map.getDoorMan().toString() + "\n";
        message += this.map.toString();

        for (int i = 0; i < this.map.getSize(); i++) {
            Square s = this.map.getSquare(i);
            if (s.getGameCharacters().size() > 0) {
                for (GameCharacter ch : s.getGameCharacters()) {
                    message += ch.toString() + "\n";
                }
            }
        }

        return message;
    }



    /**
     * Show the initial message of a character
     */


    /**
     * Getter of the capturedCharacters attribute
     * @return the capturedCharacters attribute.
     */
    public LinkedList<GameCharacter> getCapturedCharacters() {
        return capturedCharacters;
    }


}
