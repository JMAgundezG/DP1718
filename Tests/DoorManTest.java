import Map.DoorMan;
import Map.Weapon;
import org.junit.Before;
import org.junit.Test;

import javax.security.auth.kerberos.KerberosKey;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by daniel on 18/12/17.
 */
public class DoorManTest {

    DoorMan d;
    Weapon w;
    Weapon[] doorManWeapons;

    @Before
    public void setUp() {
        d = new DoorMan(4);
        d.configure();
        w = new Weapon("Anillo", 12);
        doorManWeapons = new Weapon[]{new Weapon("CampoEnergia", 5),new Weapon("Armadura", 13),
                new Weapon("Anillo", 11), new Weapon("Acido", 1), new Weapon("Antorcha", 5),
                new Weapon("Bola", 3), new Weapon("Baston", 22),
                new Weapon("CadenaFuego", 11), new Weapon("Espada", 11),
                new Weapon("Cetro", 20), new Weapon("Capa", 10),
                new Weapon("CampoMagnetico", 5), new Weapon("Escudo", 3),
                new Weapon("Garra", 22), new Weapon("Flecha", 12), new Weapon("Gema", 4)};


    }

    @Test
    public void doorManTest() {
        d.getTree().showInOrder();
        assertEquals(d.getDepth(), 4);
        assertEquals(d.getDoorManWeapons(), doorManWeapons);
        assertFalse(d.getDepth() == 3);
        assertFalse(d.isGateOpened());
        assertTrue(d.tryWeapon(w) == false);
        d.getTree().showInOrder();
        System.out.println("\nAs you can see, the anillo, 11 weapon" +
                " has been removed as we tried to open the Teseract " +
                "using the same weapon but with higher power");


    }

}
