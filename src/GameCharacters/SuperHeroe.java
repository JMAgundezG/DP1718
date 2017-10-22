package GameCharacters;

import datastructures.List;
import datastructures.WeaponTree;
import game.DoorMan;
import game.Map;
import game.Square;
import game.Weapon;

public class SuperHeroe extends MetaHuman{

    private WeaponTree wTree;

    public SuperHeroe(String name, String id, int pos) {

        super(name, id, pos);
        this.wTree = new WeaponTree();

    }

    public void takeWeapon(){

        Square s = Map.getSingleton().findSquare(this.getPosition());
        this.wTree.insertData(s.dropWeapon());

    }

    public void useWeapon(){

        DoorMan d = Map.getSingleton().getDoorMan();
        if(this.getPosition() == Map.getSingleton().getDailyPlanet()) {
            if (!d.tryWeapon(wTree.biggestWeapon())){
                wTree.delete(wTree.biggestWeapon());
            }
        }
    }
}
