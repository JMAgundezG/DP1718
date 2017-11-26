package charger;
/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las líneas y se van creando todas las instancias de la simulación
 * 
 * @version 4.0 -  15/10/2014 
 * @author Profesores DP
 */
import characters.Lannister;
import Map.Game;
import sun.awt.image.ImageWatched;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class ClasePrincipal {
	public static void main(String[] args) {
		/**
		 instancia asociada al fichero de entrada inicio.txt
		 */

		Cargador cargador = new Cargador();
		try {
			/**
			 Método que procesa línea a línea el fichero de entrada inicio.txt
			 */

			if (args.length>0)
				FicheroCarga.procesarFichero(args[0], cargador);
			else
				FicheroCarga.procesarFichero("inicio.txt", cargador);
		} catch (FileNotFoundException valor) {
			System.err.println("Excepción capturada al procesar fichero: " + valor.getMessage());
		} catch (IOException valor) {
			System.err.println("Excepción capturada al procesar fichero: " + valor.getMessage());
		}
		Game.getSingletonInstance().simulateGame();

	}
}