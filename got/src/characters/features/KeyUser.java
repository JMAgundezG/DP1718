package characters.features;

//TODO VER LAS INTERFACES PARA MEJORAR LAS FEATURES DE KEYS

import characters.Characters;
import map.Key;

import java.awt.*;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.TreeSet;

public abstract class KeyUser extends Feature {

    /**
     * Key list to save them
     */
    private LinkedList<Key> keySet = new LinkedList<>();

    public KeyUser(Characters character){
        super(character);
    }

    public LinkedList<Key> getKeySet() {
        return keySet;
    }

    @Override
    public void action(){}


    public String showInfo(){
        String message = "";
        if(getKeySet().size()>0){
            Object[] keys = getKeySet().toArray();
            for (int i = 0; i < getKeySet().size(); i++) {
                message += " " + keys[i].toString();
            }
        }
        return message;
    }


}