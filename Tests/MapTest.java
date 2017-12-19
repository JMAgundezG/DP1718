import GameCharacters.GameCharacter;
import Tools.Dir;
import org.junit.*;
import Game.Game;
import Map.*;

import java.util.LinkedList;

import static org.junit.Assert.*;


/**
 * Created by daniel on 18/12/17.
 */
public class MapTest {

    Map m;
    int columns;
    int rows;
    DoorMan d;
    Square winnersSquare;
    Weapon[] weapons;

    @Before
    public void setUp() {
        System.out.println("You can already check that the map and the maze" +
                " have been initiated properly.");
        m = new Map(6, 6, 35, 4);
        columns = 6;
        rows = 6;
        d = new DoorMan(4);
        winnersSquare = new Square(1111);
        weapons = new Weapon[]{new Weapon("Mjolnir", 29), new Weapon("Anillo", 1),
                new Weapon("Garra", 27), new Weapon("Armadura", 3), new Weapon("Red", 25),
                new Weapon("Escudo", 5), new Weapon("Lucille", 23), new Weapon("Lawgiver", 7),
                new Weapon("GuanteInfinito", 21), new Weapon("LazoVerdad", 9),
                new Weapon("CadenaFuego", 19), new Weapon("Capa", 11),
                new Weapon("Flecha", 17), new Weapon("Tridente", 13),
                new Weapon("Antorcha", 15), new Weapon("Baston", 28),
                new Weapon("Latigo", 2), new Weapon("MazaOro", 26),
                new Weapon("CampoMagnetico", 4), new Weapon("Tentaculo", 24),
                new Weapon("CampoEnergia", 6), new Weapon("Cetro", 22),
                new Weapon("RayoEnergia", 8), new Weapon("Laser", 20), new Weapon("Bola", 10),
                new Weapon("Espada", 18), new Weapon("Sable", 12), new Weapon("Acido", 16),
                new Weapon("Gema", 14), new Weapon("Nullifier", 23), new Weapon("Mjolnir", 1),
                new Weapon("Anillo", 29), new Weapon("Garra", 3), new Weapon("Armadura", 27),
                new Weapon("Red", 5), new Weapon("Escudo", 25), new Weapon("Lucille", 7),
                new Weapon("Lawgiver", 23), new Weapon("GuanteInfinito", 9),
                new Weapon("LazoVerdad", 21), new Weapon("CadenaFuego", 11),
                new Weapon("Capa", 19), new Weapon("Flecha", 13), new Weapon("Tridente", 17),
                new Weapon("Antorcha", 28), new Weapon("Baston", 15), new Weapon("Latigo", 26),
                new Weapon("MazaOro", 2), new Weapon("CampoMagnetico", 24),
                new Weapon("Tentaculo", 4), new Weapon("CampoEnergia", 22),
                new Weapon("Cetro", 6), new Weapon("RayoEnergia", 20), new Weapon("Laser", 8),
                new Weapon("Bola", 18), new Weapon("Espada", 10), new Weapon("Sable", 16),
                new Weapon("Acido", 12), new Weapon("Gema", 1), new Weapon("Nullifier", 3)};

    }

    @Test
    public void mapTest() {

        //Checking the basic conditions of the map.
        assertEquals(columns, m.getColumns());
        assertEquals(rows, m.getRows());
        assertEquals(d.getDepth(), m.getDoorMan().getDepth());
        assertEquals(winnersSquare.getNumber(), m.getWinnersSquare().getNumber());
        m.setRows(5);
        assertEquals(5, m.getRows());
        m.setColumns(5);
        assertEquals(5, m.getColumns());
        assertEquals(0, m.getRowOfSquare(3));
        assertEquals(3, m.getColumnOfSquare(3));
        assertEquals(35, m.getDailyPlanet());
        assertEquals(weapons, m.spendWeaponsD1());


        //We also check the available movements.
        assertEquals(true, m.availableMovement(0, Dir.E));
        assertEquals(false, m.availableMovement(0, Dir.S));
    }

}
