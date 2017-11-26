package characters.movements;
import characters.Characters;
import tools.Path;

public class TargaryensMovement extends Movement{

    /**
     *
     * puts the Targaryen's path to the Movement feature
     */
    public TargaryensMovement(Characters character){
        super(character, Path.TargaryenMovement(0));
    }
}
