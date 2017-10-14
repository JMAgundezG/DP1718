public class Weapon {

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

        Object o = new Object();
        return power - w.getPower();

    }


    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

}
