package GameCharacters.WeaponFeatures;

import GameCharacters.GameCharacter;

public abstract class WeaponFeature {

    private GameCharacter gc;

    public WeaponFeature(GameCharacter gc){
        this.gc = gc;
    }


    public abstract void weaponAction();

    public abstract void takeWeapon();

    public abstract void interact();

    public GameCharacter getGc() {
        return gc;
    }

}
