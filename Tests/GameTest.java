import org.junit.*;
import Game.Game;
import Map.*;
import GameCharacters.*;
import Tools.*;
import java.util.*;


import static Game.Game.getSI;
import static org.junit.Assert.*;


/**
 * Implementation of the GameTest.
 *
 * @author José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class tests the creation of the Game. The proper functioning
 *  of the game itself can be seen in the simulation (or in the CharactersTest,
 *  which simulates the game).
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class GameTest {

    Game g = null;
    LinkedList<GameCharacter> capturedL;

    @Before
    public void setUp() {
        g = getSI(6, 6, 35, 4);
        capturedL = new LinkedList<>();
    }

    @Test
    public void gameTest() {
        System.out.println("Most of the tests of the game are performed graphically" +
                " when the program is executed (the characters instantiation, " +
                "the different interactions between them and the weapons...).");
        assertEquals(6, g.getMap().getRows());
        assertEquals(6, g.getMap().getColumns());
        assertEquals(35, g.getMap().getDailyPlanet());
        assertEquals(4, g.getMap().getDoorMan().getDepth());
        assertEquals(capturedL, g.getCapturedCharacters());

    }
}
