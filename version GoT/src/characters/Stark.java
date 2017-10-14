package characters;

import characters.doorInteractions.DoorOpener;
import characters.features.KeyPicker;
import characters.movements.StarksMovement;
import characters.movements.TargaryensMovement;
import game.Game;
import map.Map;


public class Stark extends Characters {
    /**
     * Create a character with a Stark configuiration
     */
    public Stark(String name, String id, int turn) {
        super(name, "stark",id,0, turn);

        setMovement(new StarksMovement(this));
        setFeature(new KeyPicker(this));
        KeyPicker k = (KeyPicker) getFeature();
        setDoorInteraction(new DoorOpener(this, k.getKeySet()));

    }


}

