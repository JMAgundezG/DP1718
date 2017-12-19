package Tools;

/**
 * Created by daniel on 18/12/17.
 */
public class NoArgumentsException extends Exception {

    private static final long serialVersionUID = 1L;

    public void printStackTrace() {
        System.err.println("Excepción capturada al leer parámetros de entrada." +
                " No se ha especificado un fichero. Se procede a crear la simulación" +
                " con el fichero init.txt por defecto");
    }
}
