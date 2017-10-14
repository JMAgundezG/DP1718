import datastructures.Tree;
import map.Door;
import map.Key;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class DoorTest {
    Door puertaPrueba;
    Door puertaPruebaPar;


    @Before
    public void before() throws Exception {
        puertaPrueba = new Door();
        puertaPruebaPar = new Door(35, 4);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: isLocked()
     */
    @Test
    public void testIsLocked() throws Exception {
        assertTrue("Testing if the door is locked", puertaPrueba.isLocked() == true);
    }


    /**
     * Method: openDoor()
     */
    @Test
    public void testOpenDoor() throws Exception {
        puertaPrueba.openDoor();
        assertEquals("Testing if the door has been opened", puertaPrueba.isLocked(), false);
    }

    /**
     * Method: closeDoor()
     */
    @Test
    public void testCloseDoor() throws Exception {
        puertaPrueba.closeDoor();
        assertEquals("Testing if the door has been closed", puertaPrueba.isLocked(), true);
    }

    /**
     * Method: tryKey(Key Llave)
     */
    @Test
    public void testTryKey() throws Exception {
        int numLLaves = 15;
        boolean flag = true;
        boolean locked = true;
        int[] auxConfiguration = new int[numLLaves];
        int j = 1;
        for (int i = 0; i < numLLaves; i++) {
            auxConfiguration[i] = j;
            j = j + 2;
        }
        for (int i = 0; i < numLLaves; i++) {
            flag = puertaPrueba.tryKey(new Key(auxConfiguration[i]));
            if (flag == true) {
                locked = true;
            }
        }
        assertTrue("Testing if the door will open with the correct configuration", locked == true);
    }

    /**
     * Method: lockDoor()
     */
    @Test
    public void testLockDoor() throws Exception {
        puertaPrueba.lockDoor();
        assertTrue("Testing if the door is locked after being opened", puertaPrueba.isLocked() == true);

    }

    /**
     * Method: depth()
     */
    @Test
    public void testDepth() throws Exception {
        assertTrue("Testing if the depth is the correct one", puertaPruebaPar.getMaxDepth() == 4);

    }

    /**
     * Method: getNumberOfRoom()
     */
    @Test
    public void testGetNumberOfRoom() throws Exception {
        assertTrue("Testing if the Number of Room of the door is the correct one", puertaPruebaPar.getNumberOfRoom() == 35);
    }


    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
        Tree<Key> auxTree = new Tree<>();
        auxTree = puertaPruebaPar.getLockConfigureTree();
        int numLlaves = 15;
        // {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29}
        int[] Llaves = new int[15];
        int k = 1;
        for (int i = 0; i < numLlaves; i++) {
            Llaves[i] = k;
            k += 2;
        }
        //inserction of exactly correct Keys for the checking
        for (int i = 0; i < numLlaves; i++) {
            auxTree.insertar(new Key(Llaves[i]));
        }
        for (int i = 0; i < numLlaves; i++) {
            Key llave = new Key(Llaves[i]);
            if (auxTree.pertenece(llave)){
                auxTree.borrar(llave);
            }
        }
        assertEquals("Testing if the Tree of the locked configure have the correct things",auxTree.empty(),true);
    }


} 
