package Tools;

/**
 * Implementation of the NoArgumentsException.
 * @author  José Manuel Agúndez García && Daniel Sagrado Iglesias
 * @version 1.0
 * Exception dropped when no arguments have been specified.
 * Year: 2017/2018.
 * Group: Rubber Duck.
 * Delivery: EC3.
 */

public class NoArgumentsException extends Exception {

    private static final long serialVersionUID = 1L;

    public void printStackTrace() {
        System.err.println("Excepción capturada al leer parámetros de entrada." +
                " No se ha especificado un fichero. Se procede a crear la simulación" +
                " con el fichero init.txt por defecto");
    }
}
