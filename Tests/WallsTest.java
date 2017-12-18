import Map.Walls;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daniel on 18/12/17.
 */
public class WallsTest {

    @Test
    public void wallsTest() {
        Walls wall = new Walls(3, 4);
        assertEquals(wall.getSrc(), 3);
        assertFalse(wall.getDst() == 3);
    }
}
