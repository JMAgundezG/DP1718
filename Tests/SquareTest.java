import GameCharacters.GameCharacter;
import GameCharacters.SHExtraFlight;
import Game.Game;
import Map.Square;
import Map.Weapon;
import Map.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;


/**
 * Created by daniel on 18/12/17.
 */
public class SquareTest {

    Square sq;

    Weapon w, w2;

    LinkedList<Weapon> weaponL;

    LinkedList<GameCharacter> gameCL;

    Game g;

    SHExtraFlight sh;

    @Before
    public void setUp() {
        sq = new Square(3);
        w = new Weapon("test", 3);
        w2 = new Weapon("test", 4);
        weaponL = new LinkedList<>();
        gameCL = new LinkedList<>();
        g = Game.getSI(6, 6, 35, 4);
        sh = new SHExtraFlight("Pedro", "P", 0);

    }

    @Test
    public void squareTest() {

        assertEquals(sq.getNumber(), 3);
        assertEquals(sq.getWeaponList(), weaponL);
        assertEquals(sq.getGameCharacters(), gameCL);
        sq.setNodeNumber(3);
        assertEquals(sq.getNodeNumber(), 3);
        sq.setNumber(4);
        assertFalse(sq.getNumber() == 3);
    }

    @Test
    public void saveDropWeaponTest() {

        //Checking the saveWeapon method.

        weaponL.add(w);
        sq.saveWeapon(w);
        assertEquals(sq.getWeaponList(), weaponL);
        weaponL.addLast(w2);
        sq.saveWeapon(w2);
        assertTrue(sq.getWeaponList().get(0) != weaponL.get(0));
        assertFalse(sq.getWeaponList().get(1) == weaponL.get(1));
        weaponL = new LinkedList<>();
        weaponL.add(w2);
        weaponL.addLast(w);
        assertTrue(sq.getWeaponList().get(0) == weaponL.get(0));
        assertTrue(sq.getWeaponList().get(1) == weaponL.get(1));

        //Checking the dropWeapon method.
        //Checking if the dropped weapon is the desired one.
        assertEquals(w2.toString(), sq.dropWeapon().toString());
    }

    @Test
    public void saveDropCharacterTest() {
        //This method tests both the toString method of the game and the square interactions.
        //You can graphically see that the character has been added to the map to be later removed.
        g.getMap().getSquare(3).saveCharacter(sh);
        sq.saveCharacter(sh);
        gameCL.add(sh);
        assertEquals(gameCL.get(0).toString(), sq.getGameCharacters().get(0).toString());
        System.out.println(g.toString());
        sq.dropCharacter(sh);
        assertTrue(sq.getGameCharacters().isEmpty());
        g.getMap().getSquare(3).dropCharacter(sh);
        System.out.println(g.toString());

    }
}
