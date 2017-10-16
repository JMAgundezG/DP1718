

public class Weapon implements Comparable<Weapon> {

    private int power;
    private String name;

    public Weapon(String name, int power){
        this.name = name;
        this.power = power;
        spendWeapons();
    }


    private void spendWeapons(){

    }

    public int compareTo(Weapon w){
        int val = power - w.getPower();
        if(val>0)
            return 1; //First value is greater
        else if(val==0) return 0; // both values are equals
            else return -1; //Second value is greater
    }


    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

}
