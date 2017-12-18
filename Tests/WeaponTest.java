import Map.Weapon;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by daniel on 18/12/17.
 */
public class WeaponTest {

    Weapon weapon, weapon2, weapon3, weapon4;

    @Before
    public void setUp() {
        weapon = new Weapon("test", 0);
        weapon2 = new Weapon("test", 1);
        weapon3 = new Weapon("test", 2);
        weapon4 = new Weapon("test2", 0);
    }

    @Test
    public void weaponTest() {

        //Power tests
        assertEquals(0, weapon.getPower());
        weapon.setPower(3);
        assertEquals(3, weapon.getPower());
        assertFalse(4 == weapon.getPower());
        assertTrue(4 != weapon.getPower());

        //Name tests
        assertEquals("test", weapon.getName());
        weapon.setName("test2");
        assertEquals("test2", weapon.getName());
        assertFalse("test3" == weapon.getName());
        assertTrue("test3" != weapon.getName());
    }

    @Test
    public void equalTest() {

        //For the equals method, we check if the weapons have the same name.
        assertEquals(weapon, weapon2);
        assertEquals(weapon, weapon3);
        assertEquals(weapon2, weapon3);
        assertFalse(weapon.equals(weapon4));
    }

    @Test
    public void toStringTest() {

        //Checking if the toString method prints what we want
        assertEquals(weapon.toString(), "(test,0)");
    }

    @Test
    public void compareToTest() {

        //The compareTo method returns 0 if they are the same.
        assertEquals(weapon.compareTo(weapon2), 0);
        assertFalse(weapon.compareTo(weapon2) != 0);
        weapon2.setName("test2");
        //-1 if they are different
        assertTrue(weapon.compareTo(weapon2) == -1);
    }
}
