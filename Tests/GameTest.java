import org.junit.*;
import Game.Game;
import Map.*;
import GameCharacters.*;
import Tools.*;
import java.util.*;


import static Game.Game.getSI;
import static org.junit.Assert.*;


/**
 * Created by daniel on 18/12/17.
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
