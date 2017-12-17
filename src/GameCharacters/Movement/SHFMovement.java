package GameCharacters.Movement;

import Game.Game;
import GameCharacters.GameCharacter;
import Map.Path;
import Tools.Tools;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * Implementation of the SHFMovement class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class inherits from the movement class. Performs the flying superhero movements.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public class SHFMovement extends Movement{
    /**
     * Public parametrized constructor of the class Movement.
     * @param character the character attribute.
     */
    public SHFMovement(GameCharacter character) {
        super(character, Path.shortestPath(character.getPosition(), Game.
                getSI().getMap().getDailyPlanet()));
    }
}
