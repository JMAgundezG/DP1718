package characters.features;

import characters.Characters;
import Map.Game;
import map.Door;
import map.Key;
import map.Map;
import map.Room;

import java.util.LinkedList;
import java.util.TreeSet;

public class KeyDropper extends KeyUser {


    public KeyDropper(Characters keyDropper){
        super(keyDropper);
        int j = 1;
        for (int i = 0; i < 15; i++) {
            getKeySet().addFirst(new Key(j));
            j += 2;
        }
    }

    /**
     * Drop a key from the keySet
     * @return the key
     */
    private Key dropKey(){
        Key key = this.getKeySet().removeFirst();
        return key;
    }

    /**
     * Put a key in the floor if the keyset isn't empty
     */
    private void loseAKey(){
        Map map = Game.getSingletonInstance().getMap();
        if(!getKeySet().isEmpty()) {
            if(getFeatured().getNumberOfRoom() % 2 == 0) {
                Key key = dropKey();
                Room room = map.findRoom(this.getFeatured().getNumberOfRoom());
                room.insertKey(key);
            }
        }
    }

    @Override
    public void action() {
        loseAKey();
    }


    public String showInfo(){
        String message = "";
        if(getKeySet().size()>0){
            for (int i = 0; i < getKeySet().size(); i++) {
                message += " " + getKeySet().get(i).toString();
            }
        }
        return message;
    }

}
