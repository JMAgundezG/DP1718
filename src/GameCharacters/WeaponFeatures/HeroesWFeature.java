package GameCharacters.WeaponFeatures;

import Datastructures.BinaryTree;
import Game.Game;
import GameCharacters.GameCharacter;
import GameCharacters.Villain;
import Map.DoorMan;
import Map.Square;
import Map.Weapon;

import java.util.Comparator;

public class HeroesWFeature extends WeaponFeature{


    private BinaryTree<Weapon> wTree;

    public HeroesWFeature(GameCharacter gc){
        super(gc);
        wTree = new BinaryTree<>();
    }


    public void weaponAction() {

        DoorMan d = Game.getSI().getMap().getDoorMan();
        if (getGc().getPosition() == Game.getSI().getMap().getDailyPlanet()) {
            Weapon bestWeapon = wTree.mostValuedNode(Comparator.comparingInt(Weapon::getPower));
            if (bestWeapon != null && !d.tryWeapon(bestWeapon)) {
                wTree.delete(bestWeapon);
            }
        }
    }

    public void interact(){
        Square sq = Game.getSI().getMap().getSquare(getGc().getPosition());
        for (int i = 0; i < sq.getGameCharacters().size(); i++) {
            GameCharacter gc = sq.getGameCharacters().get(i);
            if (gc instanceof Villain) {
                Weapon villainWeapon = ((VillainsWFeature) gc.getWeaponFeature()).getWeapon();
                if (wTree.belongs(villainWeapon)) {
                    if (wTree.extract(villainWeapon).getPower() > villainWeapon.getPower()) {
                        Game.getSI().capture(gc);
                    }
                }
            break;
            }
        }
    }

    //TODO Revisar método, es de la D1
    public void takeWeapon(){

        Square s =Game.getSI().getMap().getSquare(getGc().getPosition());
        Weapon newWeapon = s.dropWeapon();
        if( newWeapon != null) {
            if (wTree.belongs(newWeapon)) {
                newWeapon = new Weapon(newWeapon.getName(), wTree.extract(newWeapon).getPower() + newWeapon.getPower());
            }
            this.wTree.insertData(newWeapon);
        }
    }

    public BinaryTree<Weapon> getwTree() {
        return wTree;
    }
}
