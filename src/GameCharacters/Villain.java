package GameCharacters;

import Map.Map;
import Map.Weapon;

public class Villain extends GameCharacter {

    private Weapon weapon;

    public Villain(String name, String id, int pos) {
        super(name, id, pos);
        this.weapon = null;
    }

    public Villain(String name, String id, int pos, Weapon w) {
        super(name, id, pos);
        this.weapon = w;
    }

    public void useWeapon(){
        if(this.getPosition() == Map.getSingleton().getDailyPlanet()) {
            Map.getSingleton().getDoorMan().tryWeapon(weapon);
        }
    }
}
