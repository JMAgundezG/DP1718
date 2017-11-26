package characters;

import characters.doorInteractions.DoorCloser;
import characters.features.KeyDropper;
import characters.movements.LannistersMovement;
import Map.Game;
import tools.Path;


public class Lannister extends Characters {


    /**
     * Create a character with a Lannister's configuration
     */
    public Lannister(String name, String id, int turn) {

        super(name, "lannister", id, Game.getSingletonInstance().getMap().getSE(), turn);
        setFeature(new KeyDropper(this));
        setDoorInteraction(new DoorCloser(this));
        setMovement((new LannistersMovement(this)));
    }

}
