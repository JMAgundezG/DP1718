package datastructures;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Implementation of the Binary Search Tree.
 *
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * The original version was given to us by the professors of the subject, we made the necessary changes, including the template,
 * the new methods that we had to use in order to complete the project and the translation of the ADS to English. <br/>
 * Year: 2017/2018 <br/>
 * Group: Rubber Duck <br/>
 */
public class BinaryTree<TYPE extends Comparable<TYPE>>{

    /**
     * Stored data in each node of the Tree.
     */
    private TYPE rootData;

    /**
     * Indicates whether the tree is empty or not.
     */
    boolean isEmpty;

    /**
     * Left child of the current node.
     */
    private BinaryTree<TYPE> leftC;

    /**
     * Right child of the current node.
     */
    private BinaryTree<TYPE> rightC;

    /**
     * Default constructor of the class. Initializes an empty tree.
     */
    public BinaryTree() {
        this.isEmpty = true;
        this.leftC = null;
        this.rightC = null;
    }

    /**
     * Creates a new tree from the data passed as parameters.
     *
     * @param leftC     The left child of the tree that is being created.
     * @param rootData Root of the tree that is being created.
     * @param rightC     The right child of the tree that is being created.
     */
    public BinaryTree(BinaryTree leftC, TYPE rootData, BinaryTree rightC) {
        this.isEmpty = false;
        this.rootData = rootData;
        this.leftC = leftC;
        this.rightC = rightC;
    }

    /**
     * Returns the left child of the tree.
     *
     * @return Left child.
     */
    public BinaryTree<TYPE> getLeftChild() {
        return leftC;
    }

    /**
     * Returns the right child of the tree.
     *
     * @return Right child.
     */
    public BinaryTree<TYPE> getRightChild() {
        return rightC;
    }

    /**
     * Returns the root of the tree.
     *
     * @return The root of the tree.
     */
    public TYPE getRoot() {
        return rootData;
    }

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree is empty. False on the opposite case.
     */
    public boolean empty() {
        return isEmpty;
    }

    /**
     * Inserts new data in the tree.
     *
     * @param data the data to insert.
     * @return true if the data has been succesfully inserted. False on the opposite case.
     */
    public boolean insertData(TYPE data) {
        boolean resultado = true;
        if (empty()) {
            rootData = data;
            isEmpty = false;
        } else {
            if (!(this.rootData.equals(data))) {
                BinaryTree<TYPE> aux;
                if (data.compareTo(this.rootData) < 0) { //data < rootData
                    if ((aux = getLeftChild()) == null)
                        leftC = aux = new BinaryTree();
                } else {                                    //data > rootData
                    if ((aux = getRightChild()) == null)
                        rightC = aux = new BinaryTree();
                }
                resultado = aux.insertData(data);
            } else
                resultado = false;
        }
        return resultado;
    }

    /**
     * Checks if the data is stored in the tree.
     *
     * @param data the data to look for.
     * @return True if the data is inside the tree. False on the opposite case.
     */
    public boolean belongs(TYPE data) {
        BinaryTree aux = null;
        boolean encontrado = false;
        if (!empty()) {
            if (this.rootData.equals(data))
                encontrado = true;
            else {
                if (data.compareTo(this.rootData) < 0)    //data < rootData
                    aux = getLeftChild();
                else                                    //data > rootData
                    aux = getRightChild();
                if (aux != null)
                    encontrado = aux.belongs(data);
            }
        }
        return encontrado;
    }

    /**
     * Deletes data stored in the tree.
     *
     * @param data The data you want to delete.
     */
    public void delete(TYPE data) {
        if (!empty()) {
            if (data.compareTo(this.rootData) < 0) {            //data<rootData
                if (leftC != null) leftC = leftC.deleteOrdered(data);
            } else if (data.compareTo(this.rootData) > 0) {        //data>rootData
                if (rightC != null) rightC = rightC.deleteOrdered(data);
            } else //En este caso el data es rootData
            {
                if (leftC == null && rightC == null) {
                    isEmpty = true;
                } else
                    deleteOrdered(data);
            }
        }
    }


    /**
     * Deletes data. This method is used by the Delete method.
     *
     * @param data data to delete
     * @return Returns the resulting tree after deleting the data.
     */
    private BinaryTree<TYPE> deleteOrdered(TYPE data) {
        TYPE datoaux;
        BinaryTree<TYPE> retorno = this;
        BinaryTree<TYPE> aborrar, candidato, antecesor;

        if (!empty()) {
            if (data.compareTo(this.rootData) < 0) {        // dato<rootData
                if (leftC != null) leftC = leftC.deleteOrdered(data);
                else retorno=null;
            } else if (data.compareTo(this.rootData) > 0) {    // dato>rootData
                if (rightC != null) rightC = rightC.deleteOrdered(data);
                else retorno=null;
            } else {
                if ((rightC == null) && (leftC == null)) { /*si es hoja*/
                    retorno = null;
                } else {
                    if (rightC == null) { /*Solo hijo izquierdo*/
                        aborrar = leftC;
                        datoaux = this.rootData;
                        rootData = leftC.getRoot();
                        leftC.rootData = datoaux;
                        leftC = leftC.getLeftChild();
                        rightC = aborrar.getRightChild();

                        retorno = this;
                    } else if (leftC == null) { /*Solo hijo derecho*/
                        aborrar = rightC;
                        datoaux = rootData;
                        rootData = rightC.getRoot();
                        rightC.rootData = datoaux;
                        rightC = rightC.getRightChild();
                        leftC = aborrar.getLeftChild();

                        retorno = this;
                    } else { /* Tiene dos hijos */
                        candidato = this.getLeftChild();
                        antecesor = this;
                        while (candidato.getRightChild() != null) {
                            antecesor = candidato;
                            candidato = candidato.getRightChild();
                        }

	                            /*Intercambio de datos de candidato*/
                        datoaux = rootData;
                        rootData = candidato.getRoot();
                        candidato.rootData = datoaux;
                        aborrar = candidato;
                        if (antecesor == this)
                            leftC = candidato.getLeftChild();
                        else
                            antecesor.rightC = candidato.getLeftChild();
                    } //Eliminar solo ese nodo, no todo el subarbol
                    aborrar.leftC = null;
                    aborrar.rightC = null;
                }
            }
        }
        return retorno;
    }

    /**
     * Method that deletes all the elements from the tree.
     */
    public void deleteTree(){
        if(!this.empty()){
            this.delete(this.getRoot());
            this.deleteTree();
        }
    }


    /**
     * inOrder traverse of the tree.
     */
    public void inOrder(BufferedWriter writing) {

        String s = "";
        BinaryTree<TYPE> aux;
        if (!empty()) {
            if ((aux = getLeftChild()) != null) {
                aux.inOrder(writing);
            }

            System.out.print(this.rootData.toString());
            s += (this.rootData.toString());

            try {
                writing.write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if ((aux = getRightChild()) != null) {
                aux.inOrder(writing);
            }
        }
    }

    /**
     * Method that returns the depth of the DataStructures.datastructures.BinaryTree.
     *
     * @return the depth of the tree.
     */
    public int depth(){
        int totaldepth = 0, leftDepth = 0, rightDepth = 0;
        BinaryTree<TYPE> aux;
        if (!empty()) {
            if ((aux = getLeftChild()) != null)
                leftDepth = aux.depth() + 1;
            if ((aux=getRightChild()) != null)
                rightDepth = aux.depth() + 1;
        }
        if(rightDepth > leftDepth)
            totaldepth = rightDepth;
        else
            totaldepth = leftDepth;
        return totaldepth;
    }

    /**
     * Method that looks for the data and says if it is a leaf or not.
     * @param data the data to find.
     * @return true if the data is found. False on the opposite case.
     */
    public boolean isLeaf(TYPE data){
    boolean leafyIsHere = false;
    if (!empty()){
        if (leftC == null && rightC ==null)
            leafyIsHere = true;
        if (data.compareTo(rootData)<0 && leftC !=null)
            leafyIsHere= leftC.isLeaf(data);
        if (data.compareTo(rootData)>0 && rightC !=null)
            leafyIsHere= rightC.isLeaf(data);
    }
    return leafyIsHere;
}

/**
 * Method that calculates the number of leaves of the tree.
 * @return returns the number of leaves of the tree.
 */

    public int countLeaves(){
        int leavesNumber=0;
        if (!this.empty()){
            if(this.isLeaf(getRoot())){
                leavesNumber++;
            }
            if (this.leftC !=null){
                    leavesNumber= leavesNumber + this.getLeftChild().countLeaves();
                }
                if (this.rightC !=null){
                    leavesNumber= leavesNumber + this.getRightChild().countLeaves();
                }
            }
        return leavesNumber;
    }
    /**
     * Method that calculates the number of inner nodes of the tree.
     * @return the number of inner nodes of the tree.
     */
    public int countInners(){
        int innersNumber=0;
        if(!this.empty()) {
            if(!this.isLeaf(getRoot())) {
                innersNumber++;
            }
            if (this.leftC != null) {
                innersNumber = innersNumber + this.getLeftChild().countInners();
            }
            if (this.rightC != null) {
                innersNumber = innersNumber + this.getRightChild().countInners();
            }
        }
        return innersNumber;
    }
}
