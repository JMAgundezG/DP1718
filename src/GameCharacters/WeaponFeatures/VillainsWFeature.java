package GameCharacters.WeaponFeatures;

import Datastructures.BinaryTree;
import Game.Game;
import GameCharacters.GameCharacter;
import GameCharacters.Villain;
import Map.Square;
import Map.Weapon;

public class VillainsWFeature extends WeaponFeature {

    Weapon weapon;

    public VillainsWFeature(GameCharacter gc) {
        super(gc);
    }


    @Override
    public void takeWeapon() {
        Square s = Game.getSI().getMap().getSquare(getGc().getPosition());
        Weapon sqWeapon = s.dropWeapon();
        if(sqWeapon != null) {
            if (sqWeapon.getPower() > weapon.getPower()) {
                s.saveWeapon(weapon);
                weapon = sqWeapon;
            } else {
                s.saveWeapon(sqWeapon);
            }
        }
    }

    @Override
    public void interact() {
        Square sq = Game.getSI().getMap().getSquare(getGc().getPosition());
        for (int i = 0; i < sq.getGameCharacters().size(); i++) {
            GameCharacter gc = sq.getGameCharacters().get(i);
            if (!(gc instanceof Villain)) {
                BinaryTree<Weapon> wTree = ((HeroesWFeature) gc.getWeaponFeature()).getwTree();
                if (wTree.belongs(weapon)) {
                    if (wTree.extract(weapon).getPower() < weapon.getPower()) {
                        wTree.delete(weapon);
                    }
                }
                break;
            }
        }
    }

    @Override
    public void weaponAction() {
        if (this.getGc().getPosition() == Game.getSI().getMap().getDailyPlanet() && weapon != null) {
            Game.getSI().getMap().getDoorMan().tryWeapon(weapon);
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
