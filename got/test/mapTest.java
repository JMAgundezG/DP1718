import Map.Game;
import map.Key;
import org.junit.Before;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by jmagundezg on 23/06/17.
 */
public class mapTest {
    Game game = Game.getSingletonInstance(6,6,36,4);

    @Before
    public void before(){
        game = Game.getSingletonInstance();
    }
    @Test
    public void testMostFreqRooms(){
        LinkedList<Integer> a = game.getMap().getPosiblePaths().getFirst();
        LinkedList<Integer> c = new LinkedList<>();
        c.addLast(0);
        c.addLast(1);
        c.addLast(2);
        c.addLast(8);
        c.addLast(14);
        c.addLast(15);
        c.addLast(21);
        c.addLast(27);
        c.addLast(28);
        c.addLast(29);
        c.addLast(35);


        assertEquals(a.equals(c),true);
    }

    @Test
    public void tryCreateKeys(){
        LinkedList<Key> k = Game.getSingletonInstance().getMap().generateKeys();
        LinkedList<Key> b = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 1) {
                b.addLast(new Key(i));
                b.addLast(new Key(i));
            } else {
                b.addLast(new Key(i));
            }
        }
        assertEquals(k.equals(b),true);
    }

}
