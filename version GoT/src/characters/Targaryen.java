package characters;

import characters.doorInteractions.DoorOpener;
import characters.features.KeyPicker;
import characters.movements.TargaryensMovement;
import Map.Game;
import map.Map;
import tools.Path;

public class Targaryen extends Characters {

    /**
     * Create a character with a Targaryen configuiration
     */
    public Targaryen(String name, String id,  int turn) {
        super(name, "targaryen", id, 0, turn);

        setMovement(new TargaryensMovement(this));
        setFeature(new KeyPicker(this));
        KeyPicker k = (KeyPicker) getFeature();
        setDoorInteraction(new DoorOpener(this, k.getKeySet()));
    }

    @Override
    public void actions() {
        Map map = Game.getSingletonInstance().getMap();
        if (getNumberOfRoom() != map.getThroneRoom() || map.getThroneDoor().isLocked())
            super.actions();
    }
}
