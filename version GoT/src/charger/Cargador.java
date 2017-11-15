package charger;

import characters.*;
import tools.Dir;
import Map.Game;
import tools.Path;

import java.util.*;


/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Estacion, una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las líneas y se van creando todas las instancias de la simulación
 * 
 * @version 5.0 -  27/10/2016 
 * @author Profesores DP
 */
public class Cargador {
	/**  
	número de elementos distintos que tendrá la simulación - Mapa, Stark, Lannister, Baratheon, Targaryen
	*/
	static final int NUMELTOSCONF  = 5;
	/**  
	atributo para almacenar el mapeo de los distintos elementos
	*/
	static private DatoMapeo [] mapeo;
	
	/**
	 *  constructor parametrizado 
	 *  //@param e referencia a la instancia del patrón Singleton
	 */
	Cargador()  {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0]= new DatoMapeo("MAP", 5);
		mapeo[1]= new DatoMapeo("STARK", 4);
		mapeo[2]= new DatoMapeo("TARGARYEN", 4);
		mapeo[3]= new DatoMapeo("LANNISTER", 4);
		mapeo[4]= new DatoMapeo("CAMINANTE", 4);
		
	}
	
	/**
	 *  busca en mapeo el elemento leído del fichero inicio.txt y devuelve la posición en la que está 
	 *  @param elto elemento a buscar en el array
	 *  @return res posición en mapeo de dicho elemento
	 */
	private int queElemento(String elto)  {
	    int res=-1;
	    boolean enc=false;

	    for (int i=0; (i < NUMELTOSCONF && !enc); i++)  {
	        if (mapeo[i].getNombre().equals(elto))      {
	            res=i;
	            enc=true;
	        }
	    }
	    return res;
	}
	
	/**
	 *  método que crea las distintas instancias de la simulación 
	 *  @param elto nombre de la instancia que se pretende crear
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo de la instancia
	 */
	public void crear(String elto, int numCampos, List<String> vCampos)	{
	    //Si existe elemento y el n�mero de campos es correcto, procesarlo... si no, error
	    int numElto = queElemento(elto);

	    //Comprobaci�n de datos básicos correctos
	    if ((numElto!=-1) && (mapeo[numElto].getCampos() == numCampos))   {
	        //procesar
	        switch(numElto){
	        case 0:	   
	            crearMapa(numCampos,vCampos);
	            break;
	        case 1:
	            crearStark(numCampos,vCampos);
	            break;
	        case 2:
	            crearTargaryen(numCampos,vCampos);
	            break;
	        case 3:
	            crearLannister(numCampos,vCampos);
	            break;
	        case 4:
	            crearCaminante(numCampos,vCampos);
	            break;
	     	}
	    }
	    else
	        System.out.println("ERROR Cargador::crear: Datos de configuración incorrectos... " + elto + "," + numCampos+"\n");
	}

	/**
	 *  método que crea una instancia de la clase Planta
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearMapa(int numCampos, List<String> vCampos)
	{
	   int dimX = Integer.parseInt(vCampos.get(1));
		int dimY = Integer.parseInt(vCampos.get(2));
		int numberOfRoom = Integer.parseInt(vCampos.get(3));
		int depth = Integer.parseInt(vCampos.get(4));
		if(dimX < 1 || dimY < 1 || numberOfRoom < 0 || numberOfRoom > dimX * dimY || depth < 0){
			System.err.println("ERROR: THE MAP HAS INVALID VARIABLES");
			System.out.println("Charging a default map");
			dimX = dimY = 6;
			numberOfRoom = dimX*dimY;
			depth = 4;
		}
		Game game = Game.getSingletonInstance(dimX,dimY,numberOfRoom,depth);
	}

	/**
	 *  método que crea una instancia de la clase Stark
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearStark(int numCampos, List<String> vCampos)
	{
	   String name = vCampos.get(1);
		String ID = vCampos.get(2);
		int turn = Integer.parseInt(vCampos.get(3));
		Stark character = new Stark(name, ID, turn);
		Game game = Game.getSingletonInstance();
		game.putACharacterInTheGame(character);
		}

	/**
	 *  método que crea una instancia de la clase Targaryen
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearTargaryen(int numCampos, List<String> vCampos)
	{
	    //System.out.println("Creado Targaryen: " + vCampos.get(1) + "\n");

		String name = vCampos.get(1);
		String ID = vCampos.get(2);
		int turn = Integer.parseInt(vCampos.get(3));

		Targaryen character = new Targaryen(name, ID, turn);
		Game game = Game.getSingletonInstance();
		game.putACharacterInTheGame(character);
		}

	/**
	 *  método que crea una instancia de la clase Lannister
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearLannister(int numCampos, List<String> vCampos)
	{
	    //System.out.println("Creado Lannister: " + vCampos.get(1) + "\n");

		String name = vCampos.get(1);
		String ID = vCampos.get(2);
		int turn = Integer.parseInt(vCampos.get(3));

		Lannister character = new Lannister(name, ID, turn);
		Game game = Game.getSingletonInstance();
		game.putACharacterInTheGame(character);
		}

	/**
	 *  método que crea una instancia de la clase White Walker
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearCaminante(int numCampos, List<String> vCampos)
	{
	    //System.out.println("Creado Caminante Blanco: " + vCampos.get(1) + "\n");

		String name = vCampos.get(1);
		String ID = vCampos.get(2);
		int turn = Integer.parseInt(vCampos.get(3));
		int initialPosition = Game.getSingletonInstance().getMap().getSW();
		WhiteWalkers character = new WhiteWalkers(name, ID, initialPosition, turn);
		Game game = Game.getSingletonInstance();
		game.putACharacterInTheGame(character);

	}



}
