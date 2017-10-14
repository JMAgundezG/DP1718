package map;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author  Created by J.M. Agúndez G. and R.Gómez R.
 */

public class Key implements Comparable<Key> {
    /**
     *  Int that specifies the code of the key
     */
    private int code;

    /**
     * Default constructor
     */
    public Key() {
        this.code = 0;
    }

    /**
     * Parametrized constructor
     * @param code
     */
    public Key(int code) {
        this.code = code;
    }

    /**
     * Code setter
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Code getter
     * @return code key
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Shows the code key
     */
    public void showKey() {
        System.out.println(this.code);
    }

    /**
     * Key comparator
     * @param key2
     * @return
     */

    @Override
    public int compareTo(Key key2) {
        if (this == key2 || this.getCode() == key2.getCode()) {
            return 0;
        } else {
            if (this.getCode() > key2.getCode()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        return this.toString() == toString();
    }

    @Override
    public String toString(){
        return Integer.toString(getCode());
    }
}