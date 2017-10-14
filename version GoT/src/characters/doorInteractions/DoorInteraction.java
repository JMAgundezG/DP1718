package characters.doorInteractions;

import characters.Characters;
import map.Door;

/**
 * Created by spassky on 11/02/17.
 */
public abstract class DoorInteraction {

    private Characters actor;

    public DoorInteraction(Characters actor){
        this.actor = actor;
    }

    public Characters getActor() {
        return actor;
    }

    /**
     * Interacts with the door if it's on the throne's room
     */
    public abstract void doorAction();

}
