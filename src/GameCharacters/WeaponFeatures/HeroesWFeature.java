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

    private int catchedVillains;

    public HeroesWFeature(GameCharacter gc){
        super(gc);
        wTree = new BinaryTree<>();
        catchedVillains = 0;
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
                if (villainWeapon == null || wTree.belongs(villainWeapon)) {
                    if(!wTree.empty()) {
                        if (villainWeapon == null || wTree.extract(villainWeapon).getPower() > villainWeapon.getPower()) {
                            Game.getSI().capture(gc);
                            catchedVillains++;
                        }
                    }
                }
            break;
            }
        }
    }
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

    @Override
    public String toString() {
        return wTree.StringInOrder();
    }
}
