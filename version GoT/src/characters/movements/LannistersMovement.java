package characters.movements;

import characters.Characters;
import tools.Path;


public class LannistersMovement extends Movement {
    /**
     * Puts the Lannister's path to the character's movement
     *
     */
    public LannistersMovement(Characters character){
        super(character, Path.LannisterMovement());
    }

    @Override
    public void movementAction(){
        if(getMovements().size()==0){
            setMovements(Path.LannisterMovement());
        }
        movement();
    }


}
