import Map.Walls;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Implementation of the WallsTest.
 *
 * @author José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class tests the walls.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class WallsTest {

    @Test
    public void wallsTest() {
        Walls wall = new Walls(3, 4);
        assertEquals(wall.getSrc(), 3);
        assertFalse(wall.getDst() == 3);
    }
}
