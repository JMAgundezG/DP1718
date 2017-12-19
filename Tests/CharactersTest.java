import GameCharacters.Movement.SHESMovement;
import GameCharacters.SHExtraFlight;
import GameCharacters.SHExtraSensorial;
import GameCharacters.SHPhysical;
import GameCharacters.Villain;
import Game.*;
import Tools.Dir;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created by daniel on 19/12/17.
 */
public class CharactersTest {

    Game g;
    SHExtraFlight sf;
    SHExtraSensorial ss;
    SHPhysical sp;
    Villain v;

    @Before
    public void setUp() {
        g = Game.getSI(6, 6, 35, 4);
        sf = new SHExtraFlight("UFO", "U", 0);
        ss = new SHExtraSensorial("Freud", "F", 0);
        sp = new SHPhysical("Tarrako", "T", 0);
        v = new Villain("Flamenco", "N", 0);
        g.insertCharacter(sf);
        g.insertCharacter(ss);
        g.insertCharacter(sp);
        g.insertCharacter(v);
        System.out.println(g.toString());
    }

    @Test
    public void charactersTest() {
        g.simulateGame();

        System.out.println("You can graphically check the paths of the characters " +
                "the movements, and the actions they perform during the game simulation.");
    }

}
