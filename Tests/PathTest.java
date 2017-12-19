import Game.Game;
import Map.Path;
import Tools.Dir;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;


/**
 * Implementation of the PathTest.
 *
 * @author José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class tests the main methods of the Path class.
 * The rest of the methods can be seen in the CharactersTest or
 *  during the simulation of the game. You can visually check the paths of the characters.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class PathTest {

    Game g;

    Path p;

    LinkedList<Dir> dirL;
    @Before
    public void setUp() {
        g = Game.getSI(6, 6, 35, 4);
        dirL = new LinkedList<>();
        dirL.addLast(Dir.E);
        dirL.addLast(Dir.E);
        dirL.addLast(Dir.S);
        dirL.addLast(Dir.S);
        dirL.addLast(Dir.E);
        dirL.addLast(Dir.S);
        dirL.addLast(Dir.S);
        dirL.addLast(Dir.E);
        dirL.addLast(Dir.E);
        dirL.addLast(Dir.S);
    }

    @Test
    public void shortestPathTest(){
        assertEquals(dirL, p.shortestPath(0, 35));
    }

    @Test
    public void neighbourRoomTest() {
        assertEquals(p.neighbourRoomNumber(0, Dir.E), 1);
        assertEquals(p.neighbourRoomNumber(0, Dir.S), 6);
        assertEquals(p.neighbourRoomNumber(6, Dir.N), 0);
        assertEquals(p.neighbourRoomNumber(7, Dir.W), 6);
    }

    @Test
    public void leftRightHandFirstPathTest() {
        assertEquals(p.LeftHand(Dir.E), Dir.N);
        assertEquals(p.RightHand(Dir.E), Dir.S);
        assertEquals(p.firstPosiblePath(), p.shortestPath(0, 35));
    }
}
