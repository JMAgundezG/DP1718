package Map;
// TODO CLASS DIAGRAM 2nd
import GameCharacters.GameCharacter;

import java.util.LinkedList;

public class Square {

    private int number;

    private int nodeNumber;

    private LinkedList<Weapon> weaponList;

    private LinkedList<GameCharacter> gameCharacters;

    public Square(int number){

        this.number = number;
        this.weaponList = new LinkedList<>();
        this.gameCharacters = new LinkedList<>();

    }

    public void saveWeapon(Weapon w){
        //TODO SAVE BY POWER
        this.weaponList.addLast(w);

    }

    public Weapon dropWeapon(){
        Weapon w = weaponList.getFirst();
        weaponList.removeFirst();
        return w;
    }

    public void saveCharacter(GameCharacter gc){
        gameCharacters.addLast(gc);
    }

    public void dropCharacter(GameCharacter gc){
        gameCharacters.remove(gc);
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
