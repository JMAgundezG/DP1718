package Tools;

import java.io.IOException;
import java.util.Random;

/**
* Generador de números aleatorios - proyecto12_13
* Implementacion de la clase que permite generar números aleatorios
* @version 1.0
* @author
* <b> Profesores DP </b><br>
* Asignatura Desarrollo de Programas<br/>
* Curso 16/17
*/
public class GenAleatorios {
	
	/** Instancia de la clase Random que permite generar los números aleatorios. */
	private static Random r;
	
	/** Constante con la semilla para inicializar la generación de números aleatorios. ¡¡No cambiar!! */
	private static final int SEMILLA=1987;		 
	
	/** Contador de números aleatorios generados */
	private static int numGenerados;			
	
	/** Instancia de la propia clase (sólo habrá una en el sistema). Ver PATRÓN DE DISEÑO SINGLETON */
	private static GenAleatorios instancia=null;	
	
	/**
	* Constructor por defecto. Inicializa un objeto de tipo GenAleatorio
	* @return Devuelve un objeto de tipo GenAleatorio inicializado
	*/
	private GenAleatorios(){
    	// Inicialización de la semilla para generar los números aleatorios
    	r = new Random(GenAleatorios.SEMILLA);
    	// Inicialización del atributo que cuenta cuántos números aleatorios se han generado
    	numGenerados = 0;
    	}
	
	/**
	 * Metodo generarNumero. Genera un número aleatorio entre 0 y (limiteRango-1)
	 * @param limiteRango El límite del rango en el que generar los aleatorios
	 * @return Devuelve el número aleatorio generado
	 */
	public static int generarNumero(int limiteRango){
		if (instancia == null) 
			instancia = new GenAleatorios();
		numGenerados++;
		return r.nextInt(limiteRango);
	}
	
	/**
	 * Devuelve el número de aleatorios generados
	 * @return Número de aleatorios generados
	 */
	public static int getNumGenerados(){
		return numGenerados;
	}
	
	public static void main(String[] args) {
		
		for (int i=20; i>10; i--) {
			System.out.println("Número generado (entre 0 y " + i + "): " + GenAleatorios.generarNumero(i));
		}
		System.out.println("Números generados: " + GenAleatorios.getNumGenerados());
	}	
}
