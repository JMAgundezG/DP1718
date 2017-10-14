package characters.features;

import characters.Characters;
import game.Game;
import map.Key;
import map.Map;
import map.Room;

public class KeyPicker extends KeyUser{


    public KeyPicker(Characters character){
        super(character);
    }


    /**
     *  collects a key from the room if he/she can
     */
    private void collectAKey(){

        Map map = Game.getSingletonInstance().getMap();
        Room room = map.findRoom(getFeatured().getNumberOfRoom());

        if(room.keysInFloor()) {
            this.getKeySet().addFirst(room.dropKey());
        }
    }

    public Key dropKey(){
        return this.getKeySet().removeFirst();
    }

    @Override
    public void action(){
        collectAKey();
        }
    }

