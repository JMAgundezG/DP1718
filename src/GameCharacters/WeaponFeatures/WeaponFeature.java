package GameCharacters.WeaponFeatures;

import GameCharacters.GameCharacter;

/**
 * Implementation of the WeaponFeature class.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * This class contains all the needed methods to execute the actions of the weapons.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC2.
 */
public abstract class WeaponFeature {

    /**
     * The character that will perform the actions with the corresponding weapon
     */
    private GameCharacter gc;

    /**
     * Public parametrized constructor of the WeaponFeature.
     * @param gc the attribute gc.
     */
    public WeaponFeature(GameCharacter gc){
        this.gc = gc;
    }

    /**
     * Abstract method implemented in the subclasses. Performs the action of the weapon.
     */
    public abstract void weaponAction();

    /**
     * Abstract method implemented in the subclasses. Performs the action of taking a weapon from the square.
     */
    public abstract void takeWeapon();

    /**
     * Abstract method implemented in the subclasses. Performs the interaction of the weapon.
     */
    public abstract void interact();

    /**
     * Getter of the gc attribute.
     * @return the gc attribute.
     */
    public GameCharacter getGc() {
        return gc;
    }

}
