package GameCharacters.Movement;

import Game.Game;
import GameCharacters.GameCharacter;
import Map.Map;
import Map.Path;
import Tools.Dir;

import java.util.LinkedList;

import static Map.Path.LeftHand;
import static Map.Path.RightHand;
import static Map.Path.neighbourRoomNumber;

/**
 * Implementation of the VillainMovement class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * This class inherits from the movement class. Performs the villain movements.
 * In version 2.0, we have modified the constructor and we have also
 * added the method that generates the path.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class VillainMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     *
     * @param character the character attribute.
     */
    public VillainMovement(GameCharacter character) {
        super(character, wallFollowerLeftHanded(character.getPosition()));
    }

    /**
     * Static method that generates the path of the Villain using
     * the left hand rule based on a wall following algorithm.
     * @param initialPosition the initial position of the villain.
     * @return the list with the proper path.
     */
    static private LinkedList<Dir> wallFollowerLeftHanded(int initialPosition) {
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
                if (!map.availableMovement(nRoom, LeftHand(lastDir))) {
                    if (map.availableMovement(nRoom, lastDir)) {
                        nRoom = neighbourRoomNumber(nRoom, lastDir);
                        flag = true;
                    } else {
                        lastDir = RightHand(lastDir);

                    }
                } else {
                    lastDir = LeftHand(lastDir);
                    nRoom = neighbourRoomNumber(nRoom, lastDir);
                    flag = true;
                }
            }
            movements.addLast(lastDir);
            flag = false;
        }
        return movements;
    }
}
