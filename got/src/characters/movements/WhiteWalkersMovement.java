package characters.movements;

import characters.Characters;
import tools.Path;

public class WhiteWalkersMovement extends Movement {
    /**
     *
     * puts the White Walker's path to the Movement feature
     */
    public WhiteWalkersMovement(Characters character){
        super(character, Path.whiteWalkerMovement());
    }

    @Override
    public void movementAction(){
        if(getMovements().size()==0){
            setMovements(Path.whiteWalkerMovement());
        }
        movement();
    }

}
