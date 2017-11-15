package characters.movements;

import characters.Characters;
import tools.Path;

public class StarksMovement extends Movement {
    /**
     *
     * puts the Stark's path to the Movement feature
     */
    public StarksMovement(Characters character){
        super(character, Path.StarkMovement()); //TODO CHANGE PATH
    }
}
