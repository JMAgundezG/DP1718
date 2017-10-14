package map;

import datastructures.Tree;
import java.util.Arrays;

public class Door {
    /**
     * Boolean which says if the door is locked
     */
    private boolean locked;

    /**
     * Int that specifies the maximum depth of the lock tree
     */
    private int maxDepth;
    /**
     * Key tree of the configuration of the lock
     */
    private Tree<Key> lockConfigure;

    /**
     * Key tree of tried keys
     */
    private Tree<Key> llavesProbadas;

    /**
     * the Array that saves the array needed for "Configure" Algorithm
     */
    private int lockedArray[];

    /**
     *
     */
    private int numberOfRoom;

    /**
     * Default constructor
     */
    public Door() {
        locked = true;
        this.lockedArray = null;
        this.numberOfRoom = 0;
        this.maxDepth = 4;
    }


    /**
     * Parametrized constructor
     *
     * @param numberOfRoom,depth
     */

    public Door(int numberOfRoom, int depth) {

        locked = true;
        this.lockConfigure = new Tree<>();
        this.llavesProbadas = new Tree<>();
        this.lockedArray = null;
        this.numberOfRoom = numberOfRoom;
        this.maxDepth = depth;
        lockDoor();
    }

    /**
     * Lock boolean getter
     *
     * @return
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Opens the door
     */
    public void openDoor() {
        this.locked = false;
    }

    /**
     * Closes the door
     */
    public void closeDoor() {
        this.locked = true;

    }

    /**
     * Algorithm for make the "Lock Configure" key tree
     *
     * @param keyArrayList
     */

    public void configure(int[] keyArrayList) {

        if (keyArrayList.length == 1) {
            lockConfigure.insertar(new Key(keyArrayList[0]));
        } else {
            lockConfigure.insertar(new Key(keyArrayList[keyArrayList.length / 2]));
            configure(Arrays.copyOfRange(keyArrayList, 0, keyArrayList.length / 2));
            configure(Arrays.copyOfRange(keyArrayList, keyArrayList.length / 2 + 1, keyArrayList.length));
        }
    }


    public Tree<Key> getLockConfigureTree() {
        return this.lockConfigure;
    }

    /**
     * Method that tries the keys
     *
     * @param Llave
     */
    public boolean tryKey(Key Llave) {
        boolean unlock = false;
        if (!this.llavesProbadas.pertenece(Llave)) {
            this.llavesProbadas.insertar(Llave);
            if (this.lockConfigure.pertenece(Llave)) {
                this.lockConfigure.borrar(Llave);
                if (this.lockConfigure.profundidad() + 1 < maxDepth && this.lockConfigure.nodosPadres() >= this.lockConfigure.nodosHoja()) {
                    this.openDoor();
                    unlock = true;
                }
            }
        }

        return unlock;
    }

    /**
     * Change the door to a initial configuration
     */
    public void lockDoor() {
        this.closeDoor();
        int numLlaves = 15;
        int[] arrayConfiguration = new int[numLlaves];
        int j = 1;
        for (int i = 0; i < numLlaves; i++) {
            arrayConfiguration[i] = j;
            j = j + 2;
        }
        this.lockConfigure = new Tree<>();
        this.llavesProbadas = new Tree<>();
        this.configure(arrayConfiguration);
    }

    public int depth() {
        return lockConfigure.profundidad();
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    /**
     * @return the door's information
     */
    public String showDoor() {
        String message = "(door:";
        if (isLocked()) {
            message += "closed:";
        } else {
            message += "open:";
        }
        message += Integer.toString(this.maxDepth) + ":";
        message += lockConfigure.StringInOrden();
        message += ":";
        message += llavesProbadas.StringInOrden();
        message += ")";
        return message;
    }
}