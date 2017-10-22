package game;

import datastructures.List;
import game.Weapon;

public class Square {

    private int number;

    private List<Weapon> weaponList;

    public Square(int number){

        this.number = number;
        this.weaponList = new List<>();

    }

    public void saveWeapon(Weapon w){

        this.weaponList.sortedAddInversed(w);

    }

    public Weapon dropWeapon(){
        Weapon w = weaponList.first().get();
        weaponList.removeFirst();
        return w;
    }
}
