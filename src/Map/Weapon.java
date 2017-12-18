package Map;

/**
 * Implementation of the Weapon.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 2.0
 * This is the class that will contain the information of the weapons
 * used by the GameCharacters.
 * In the version 2.0, we have added some getters and setters.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */
public class Weapon implements Comparable<Weapon>{

    /**
     * Attribute that stores the power of the weapon.
     */
    private int power;

    /**
     * Attribute that stores the name of the weapon.
     */
    private String name;

    /**
     * Public parametrized constructor of the class Weapon.
     * @param name the attribute name that the weapon will have.
     * @param power the attribute power that the weapon will have.
     */
    public Weapon(String name, int power){
        this.name = name;
        this.power = power;
    }

    /**
     * Getter of the attribute power.
     * @return the power of the weapon.
     */
    public int getPower() {
        return power;
    }

    /**
     * Getter of the attribute name.
     * @return the name of the weapon.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the power attribute.
     * @param power the power attribute.
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Setter of the name attribute.
     * @param name the name attribute.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Override method used to compare if two weapons are equal or not according to our criterion.
     * @param o the second weapon to compare. The first one is the "this." instance.
     * @return true if the weapons are equal. False on the opposite case.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof Weapon && ((Weapon) o).getName().equals(this.name);
    }

    /**
     * Override toString method used to show the Weapon information.
     * @return the String that contains the Weapon information.
     */
    @Override
    public String toString() {
        return "(" + name + ","+ Integer.toString(power) + ")";
    }

    /**
     * Override of the compareTo method used to compare two weapons.
     * @param w the second weapon to compare.
     * @return the corresponding int of the compareTo method according to the method criterion.
     */
    @Override
    public int compareTo(Weapon w){
        return name.compareTo(w.getName());
    }
}
