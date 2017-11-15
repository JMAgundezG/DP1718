package Map;

public class Weapon implements Comparable<Weapon>{

    private int power;
    private String name;

    public Weapon(String name, int power){
        this.name = name;
        this.power = power;
    }


//    public int compareTo(Weapon w){
//        int val = power - w.getPower();
//        if(val>0)
//            return 1; //First value is greater
//        else if(val==0) return 0; // both values are equals
//            else return -1; //Second value is greater
//    }



    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Weapon && ((Weapon) o).getName().equals(this.name);
    }

    @Override
    public String toString() {
        return "{" + name + ", "+ Integer.toString(power) + "}";
    }

    @Override
    public int compareTo(Weapon w){
        return name.compareTo(w.getName());
    }
}
