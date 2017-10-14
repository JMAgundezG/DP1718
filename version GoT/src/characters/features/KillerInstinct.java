package characters.features;

import characters.Characters;
import game.Game;
import map.Map;
import map.Room;

import java.util.LinkedList;


public class KillerInstinct extends Feature {

    /**
     * datastructures.List to save the human's that the killer hunted
     */
    private LinkedList<Characters> killedHumans;
    public KillerInstinct(Characters character){
        super(character);
        killedHumans = new LinkedList<>();
    }

    /**
     * If a human is in the same room with the killer, it will be assassinated
     */
    private void killHumans(){

        Map map = Game.getSingletonInstance().getMap();
        Room room = map.findRoom(this.getFeatured().getNumberOfRoom());
        Characters human;
        if(room.thereAreHumans()){
            human = room.getHuman();
            killedHumans.addLast(human);
            human.setActionFlag(false);
            Game.getSingletonInstance().killHuman(human);
        }
    }

    public LinkedList<Characters> getKilledHumans() {
        return killedHumans;
    }

    @Override
    public void action() {
        killHumans();
    }


    public String showInfo(){
        String message = "";
        if(getKilledHumans().size()>0){
            for (int i = 0; i < getKilledHumans().size(); i++) {
                message += " " + killedHumans.get(i).getId();
            }
        }
        return message;
    }
}
