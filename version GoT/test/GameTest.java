import game.Game;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GameTest {
    Game game;
    @Before
    public void before(){
       game = Game.getSingletonInstance(6,6,35,4);
    }

    @Test
    public void testSimulation(){
        game.simulateGame();
        assertTrue(game.isFinished());
    }

    @Test
    public void testSimulateATurn(){
        int t = game.getTurn();
        game.simulateATurn();
        assertTrue(game.getTurn() == t++);
    }
}
