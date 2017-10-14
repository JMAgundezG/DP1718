package characters;

import characters.doorInteractions.DoorCloser;
import characters.features.KillerInstinct;
import characters.movements.WhiteWalkersMovement;
import tools.Path;

public class WhiteWalkers extends Characters {

    /**
     * Create a character with a White Walker configuiration
     */
    public WhiteWalkers(String name, String id, int numberOfRoom, int turn) {
        super(name, "walker", id, numberOfRoom, turn);
        setFeature(new KillerInstinct(this));
        setMovement(new WhiteWalkersMovement(this));
        setDoorInteraction(new DoorCloser(this));

    }

}
