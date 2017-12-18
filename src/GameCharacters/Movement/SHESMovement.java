package GameCharacters.Movement;
import Game.Game;
import Map.Map;
import Map.Path;
import GameCharacters.GameCharacter;
import Tools.Dir;

import java.util.LinkedList;

/**
 * Implementation of the SHESMovement class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * This class inherits from the movement class. Performs the extrasensorial superhero movements.
 * In version 2.0, we have modified the constructor and we have also added
 * the method that calculates the path for the extrasensorial superheroes.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class SHESMovement extends Movement{

    /**
     * Public parametrized constructor of the class Movement.
     * @param character the character attribute.
     */
    public SHESMovement(GameCharacter character) {
        super(character, wallFollower(character.getPosition()));
    }

    /**
     * Method that creates the path of the extra sensorial superhero
     * using a wall following algorithm.
     * @param initialPosition the initial position of the character.
     * @return the list with the proper path.
     */
    private static LinkedList<Dir> wallFollower(int initialPosition) {
        LinkedList<Dir> movements = new LinkedList<>();
        int nRoom = initialPosition;
        Map map = Game.getSI().getMap();
        Dir lastDir = Dir.S;
        boolean flag = false;
        /*
         *Starting the WallFollower Algorithm
         */
        while (nRoom != map.getDailyPlanet()) {

            while (!flag) {
                if (!map.availableMovement(nRoom, Path.RightHand(lastDir))) {
                    if (map.availableMovement(nRoom, lastDir)) {
                        nRoom = Path.neighbourRoomNumber(nRoom, lastDir);
                        flag = true;
                    } else {
                        lastDir = Path.LeftHand(lastDir);

                    }
                } else {
                    lastDir = Path.RightHand(lastDir);
                    nRoom = Path.neighbourRoomNumber(nRoom, lastDir);
                    flag = true;
                }
            }
            movements.addLast(lastDir);
            flag = false;
        }
        return movements;
    }
}

