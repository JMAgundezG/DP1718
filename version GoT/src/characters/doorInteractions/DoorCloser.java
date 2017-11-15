package characters.doorInteractions;

import characters.Characters;
import Map.Game;
import map.Door;


public class DoorCloser extends DoorInteraction {

    public DoorCloser(Characters actor) {
        super(actor);
    }

    /**
     * Close the Throne's Door
     */
    @Override
    public void doorAction() {
        Door throneDoor = Game.getSingletonInstance().getMap().getThroneDoor();
        if (getActor().isInThroneRoom()) {
                throneDoor.lockDoor();
        }

    }
}
