package Game;

import GameCharacters.GameCharacter;

import Map.Map;
import Map.Square;

import java.util.LinkedList;

import Tools.Tools;

public class Game {

    private Map map;

    private int turn;

    private static Game game = null;

    private boolean finished;

    private LinkedList<GameCharacter> characters;

    private LinkedList<GameCharacter> capturedCharacters;


    private Game(int row, int col, int doorManSquare, int depth) {
        this.map = new Map(row, col, doorManSquare, depth);
        this.turn = 0;
        this.characters = new LinkedList<>();
        this.capturedCharacters = new LinkedList<>();
        this.finished = false;
    }


    public static Game getSI(int row, int col, int doorManSquare, int depth) {
        if (game == null) {
            game = new Game(row, col, doorManSquare, depth);
        }
        return game;
    }

    public static Game getSI() {
        return game;
    }

    public Map getMap() {
        return map;
    }


    public void insertCharacter(GameCharacter gc){
        characters.addLast(gc);
        map.getSquare(gc.getPosition()).saveCharacter(gc);



    }

    public void capture(GameCharacter gc){
        this.capturedCharacters.add(gc); // TODO comprobar si hay que quitar al personaje en este momento del juego o antes de mostrar por pantalla
    }
    /**
     * Simulate a turn of the Map
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


    //TODO
    public void simulateGame() {
        String message = "";// = map.getInitialMap();
        for (int i = 0; i < map.getColumns() * map.getRows(); i++) {
            Square s = map.getSquare(i);
            if (s.getGameCharacters().size() > 0) {
                for (GameCharacter c : s.getGameCharacters()) {
                    //message += showCreatedCharacter(c) + "\n";
                    //System.out.println(showCreatedCharacter(c));
                }
            }
        }
        while (turn < Tools.MAX_TURN) {
            simulateATurn();
            message.concat(toString());
            System.out.print(toString());
            turn++;
        }
    }

//        message += winMessage();
//        System.out.println(winMessage());
//        Tools.writeGame(message);
//        finished = true;
//    }

//    /**
//     *
//     * @return the final message of the Map
//     */
//    public String winMessage() {
//        String message = "(thronemembers)";
//        if (!map.getWinRoom().getCharactersList().isEmpty()) {
//            Characters newking = map.getWinRoom().getCharactersList().getFirst();
//            message += "\n(newking:" + newking.getFamily() + ":" + newking.getId() +
//                    ":" + "1111:" + (newking.getTurn() - 1);
//            KeyPicker kingF = (KeyPicker) newking.getFeature();
//            message += ":" + kingF.showInfo() + ")";
//            for (int i = 1; i < map.getWinRoom().getCharactersList().size(); i++) {
//                Characters c = map.getWinRoom().getCharactersList().get(i);
//
//                message += "\n" + "(" + c.getFamily() + ":" + c.getId() +
//                        ":" + "1111:" + (c.getTurn() - 1) + ":" +
//                        c.getFeature().showInfo() + ")";
//
//            }
//        }
//        return message;
//    }

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


//    /**
//     * Put a character in the Map
//     *
//     * @param character
//     */
//    public void putACharacterInTheGame(Characters character) {
//        AllCharacters.addLast(character);
//        Room room = this.getMap().findRoom(character.getNumberOfRoom());
//        room.insertCharacter(character);
//    }
//
//    /**
//     * Verifies if the characters that isn't in the Map should start playing and
//     * inserts in the Map if it's necessary
//     */
//    private void addPlayersToMap() {
//        for (Characters character : AllCharacters) {
//
//            if (character.getTurn() == this.turn && !CharactersInGame.contains(character)) {
//                addCharacter(character);
//            }
//        }
//    }
//
//    /**
//     * Remove the dead players of the Map
//     */
//    private void removePlayersFromMap() {
//        for (Characters c : DeadCharacters) {
//            if (this.CharactersInGame.contains(c)) {
//                this.CharactersInGame.remove(c);
//                this.map.findRoom(c.getNumberOfRoom()).getCharactersList().remove(c);
//                this.map.killHuman(c);
//                this.AllCharacters.remove(c);
//            }
//            if (this.AllCharacters.contains(c)) {
//                this.AllCharacters.remove(c);
//            }
//        }
//    }
//
//    /**
//     *Kills a human
//     */
//    public void killHuman(Characters human) {
//        this.DeadCharacters.add(human);
//    }
//
//    /**
//     * Show the initial message of a character
//     */
//    private String showCreatedCharacter(Characters c) {
//        String message = "(path:" + c.getId() + ":";
//        message += Path.showPath(c.getMovement().getMovements()) + ")";
//        return message;
//    }
//
//    public boolean isFinished() {
//        return finished;
//    }


    public LinkedList<GameCharacter> getCapturedCharacters() {
        return capturedCharacters;
    }

    static public void main(String[] args){

    }
}
