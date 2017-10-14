package datastructures;

/**
 * Implementacion de tree binario de busqueda.
 *
 * @author Asignatura Desarrollo de Programas<br/>
 *         <b> Profesores DP </b><br>
 *         Curso 15/16
 * @version 1.0
 */

public class Tree<TYPE extends Comparable<TYPE>> {

    /**
     * Dato almacenado en cada nodo del árbol.
     */
    private TYPE root;

    /**
     * Atributo que indica si el árbol está vacío.
     */
    boolean isEmpty;

    /**
     * Hijo izquierdo del nodo actual
     */
    private Tree<TYPE> left;

    /**
     * Hijo derecho del nodo actual
     */
    private Tree<TYPE> right;

    /**
     * Constructor por defecto de la clase. Inicializa un árbol vacío.
     */
    public Tree() {
        this.isEmpty = true;
        this.left = null;
        this.right = null;
    }

    /**
     * Crea un nuevo árbol a partir de los datums pasados por parámetro.
     *
     * @param left  El hijo izquierdo del árbol que se está creando
     * @param root  Raíz del árbol que se está creando
     * @param right El hijo derecho del árbol que se está creando
     */
    public Tree(Tree<TYPE> left, TYPE root, Tree<TYPE> right) {
        this.isEmpty = false;
        this.root = root;
        this.left = left;
        this.right = right;
    }

    /**
     * Devuelve el hijo izquierdo del árbol
     *
     * @return El hijo izquierdo
     */
    public Tree<TYPE> getLeftTree() {
        return left;
    }

    /**
     * Devuelve el hijo derecho del árbol
     *
     * @return Hijo derecho del árbol
     */
    public Tree<TYPE> getRightTree() {
        return right;
    }

    /**
     * Devuelve la raíz del árbol
     *
     * @return La raíz del árbol
     */
    public TYPE getRoot() {
        return root;
    }

    /**
     * Comprueba si el árbol está vacío.
     *
     * @return verdadero si el árbol está vacío, falso en caso contrario
     */
    public boolean empty() {
        return isEmpty;
    }

    /**
     * Inserta un nuevo datum en el árbol.
     *
     * @param datum El datum a insertar
     * @return verdadero si el datum se ha insertado correctamente, falso en caso contrario
     */

    public boolean insertar(TYPE datum) {
        boolean resultado = true;
        if (isEmpty) {
            root = datum;
            isEmpty = false;
        } else {
            if (!(this.root.equals(datum))) {
                Tree aux;
                if (datum.compareTo(this.getRoot()) < 0) { //datum < root
                    if ((aux = getLeftTree()) == null)
                        left = aux = new Tree<TYPE>();
                } else {                                    //datum > root
                    if ((aux = getRightTree()) == null)
                        right = aux = new Tree<TYPE>();
                }
                resultado = aux.insertar(datum);
            } else
                resultado = false;
        }
        return resultado;
    }

    /**
     * Comprueba si un datum se encuentra almacenado en el árbol
     *
     * @param datum El datum a buscar
     * @return verdadero si el datum se encuentra en el árbol, falso en caso contrario
     */
    public boolean pertenece(TYPE datum) {
        Tree aux = null;
        boolean encontrado = false;
        if (!empty()) {
            if (this.root.compareTo(datum) == 0)
                encontrado = true;
            else {
                if (datum.compareTo(this.getRoot()) < 0)    //datum < root
                    aux = getLeftTree();
                else                                    //datum > root
                    aux = getRightTree();
                if (aux != null)
                    encontrado = aux.pertenece(datum);
            }
        }
        return encontrado;
    }

    /**
     * Borrar un dato del árbol.
     *
     * @param datum El dato que se quiere borrar
     */
    public void borrar(TYPE datum) {
        if (!empty()) {
            if (datum.compareTo(this.getRoot()) < 0) {            //datum<root
                if (left != null) left = left.borrarOrden(datum);
            } else if (datum.compareTo(this.getRoot()) > 0) {        //datum>root
                if (right != null) right = right.borrarOrden(datum);
            } else //En este caso el datum es root
            {
                if (left == null && right == null) {
                    isEmpty = true;
                } else
                    borrarOrden(datum);
            }
        }
    }


    /**
     * Borrar un dato. Este método es utilizado por el método borrar anterior.
     *
     * @param datum El dato a borrar
     * @return Devuelve el árbol resultante después de haber realizado el borrado
     */

    private Tree borrarOrden(TYPE datum) {
        TYPE datumaux;
        Tree<TYPE> retorno = this;
        Tree<TYPE> aborrar, candidato, antecesor;

        if (!empty()) {
            if (datum.compareTo(this.root) < 0) {        // datum<root
                left = left.borrarOrden(datum);
            } else if (datum.compareTo(this.root) > 0) {    // datum>root
                right = right.borrarOrden(datum);
            } else {
                aborrar = this;
                if ((right == null) && (left == null)) { /*si es hoja*/
                    retorno = null;
                } else {
                    if (right == null) { /*Solo hijo izquierdo*/
                        aborrar = left;
                        datumaux = this.root;
                        root = left.getRoot();
                        left.root = datumaux;
                        left = left.getLeftTree();
                        right = aborrar.getRightTree();

                        retorno = this;
                    } else if (left == null) { /*Solo hijo derecho*/
                        aborrar = right;
                        datumaux = root;
                        root = right.getRoot();
                        right.root = datumaux;
                        right = right.getRightTree();
                        left = aborrar.getLeftTree();

                        retorno = this;
                    } else { /* Tiene dos hijos */
                        candidato = this.getLeftTree();
                        antecesor = this;
                        while (candidato.getRightTree() != null) {
                            antecesor = candidato;
                            candidato = candidato.getRightTree();
                        }

	                            /*Intercambio de datums de candidato*/
                        datumaux = root;
                        root = candidato.getRoot();
                        candidato.root = datumaux;
                        aborrar = candidato;
                        if (antecesor == this)
                            left = candidato.getLeftTree();
                        else
                            antecesor.right = candidato.getLeftTree();
                    }
                    aborrar.left = null;
                    aborrar.right = null;
                }
            }
        }
        return retorno;
    }

    public Integer nodosHoja() {
        Integer x = 0;
        Tree aux;
        if (!empty()) {

            if ((aux = getLeftTree()) != null) {

                x = x + aux.nodosHoja();
            }

            if (this.getRightTree() == null && this.getLeftTree() == null) {

                x = x + 1;
            }

            if ((aux = getRightTree()) != null) {

                x = x + aux.nodosHoja();
            }
        }
        return x;
    }

    public Integer nodosPadres() {
        Integer x = 0;
        Tree aux;
        if (!empty()) {
            aux = getLeftTree();
            if (aux != null) {

                x = x + aux.nodosPadres();
            }

            aux = getRightTree();
            if (aux != null) {

                x = x + aux.nodosPadres();
            }

            if (this.getRightTree() != null || this.getLeftTree() != null) {

                x = x + 1;
            }
        }
        return x;
    }

    /**
     * Recorrido inOrden del árbol.
     */

    public void inOrden() {
        Tree aux = null;
        if (!empty()) {
            if ((aux = getLeftTree()) != null) {
                aux.inOrden();
            }

            System.out.print(getRoot().toString()+" ");

            if (getRightTree() != null) {
                getRightTree().inOrden();
            }
        }
    }

    public String StringInOrden() {
        Tree aux = null;
        String message = "";
        if (!empty()) {
            if ((aux = getLeftTree()) != null) {
                message += aux.StringInOrden();
            }

            message += " " + getRoot().toString();

            if (getRightTree() != null) {
                message += getRightTree().StringInOrden();
            }
        }
        return message;
    }



    public int profundidad() {
        int altura = 0;
        int profDer = 0;
        int profIzq = 0;
        Tree aux = null;
        if (!empty()) {
            if ((aux = getLeftTree()) != null) {
                profIzq = aux.profundidad() + 1;
            }

            if ((aux = getRightTree()) != null) {
                profDer = aux.profundidad() + 1;
            }

        }

        if (profDer > profIzq) {
            altura = profDer;
        } else {
            altura = profIzq;
        }
        return altura;
    }


    public boolean isALeaf() {
        return (this.left == null && this.right == null);
    }
}