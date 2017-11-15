package characters.doorInteractions;

import characters.Characters;
import Map.Game;
import map.Door;
import map.Key;

import java.util.LinkedList;


public class DoorOpener extends DoorInteraction {
    private LinkedList<Key> keys;
    public DoorOpener(Characters actor, LinkedList keys) {
        super(actor);
        this.keys = keys;
    }

    /**
     * Tries to open the Throne's door
     */
    @Override
    public void doorAction() {
        if(getActor().isInThroneRoom()){
            Door door = Game.getSingletonInstance().getMap().getThroneDoor();
            if(door.isLocked()) {
                if (!this.keys.isEmpty()) {
                    door.tryKey(this.keys.removeFirst());
                    }
                }
            if(!door.isLocked()){
                Game.getSingletonInstance().getMap().moveToWinRoom();
            }
            //TODO Ver como se sabe si alguien ha ganado.
        }
    }
}
