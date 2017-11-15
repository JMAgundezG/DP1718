package game;

import datastructures.List;
import game.Weapon;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Square {

    private int number;

    private int nodeNumber;

    private LinkedList<Weapon> weaponList;

    public Square(int number){

        this.number = number;
        this.weaponList = new LinkedList<>();

    }

    public void saveWeapon(Weapon w){

        this.weaponList.addLast(w);

    }

    public Weapon dropWeapon(){
        Weapon w = weaponList.getFirst();
        weaponList.removeFirst();
        return w;
    }


    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    @Override
    public String toString() {
        String message = "(square:"+Integer.toString(number)+":";
        for (Weapon w: weaponList) {
          message+= " "+w.toString()+",";
        }
        return message+")";
    }
}