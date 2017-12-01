package Tools.cargador;

import Game.Game;
import GameCharacters.*;

import java.util.List;

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
	static final int NUMELTOSCONF = 5;
	/**  
	atributo para almacenar el mapeo de los distintos elementos
	*/
	static private DatoMapeo [] mapeo;
	
	/**
	 *  constructor parametrizado 
	 * // @param e referencia a la instancia del patrón Singleton
	 */
	Cargador()  {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0]= new DatoMapeo("MAP", 5);
		mapeo[1]= new DatoMapeo("SHPHYSICAL", 4);
		mapeo[2]= new DatoMapeo("SHEXTRASENSORIAL", 4);
		mapeo[3]= new DatoMapeo("SHFLIGHT", 4);
		mapeo[4]= new DatoMapeo("VILLAIN", 4);
		
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
	    //Si existe elemento y el número de campos es correcto, procesarlo... si no, error
	    int numElto = queElemento(elto);

	    //Comprobación de datos básicos correctos
	    if ((numElto!=-1) && (mapeo[numElto].getCampos() == numCampos))   {
	        //procesar
	        switch(numElto){
	        case 0:	   
	            crearMap(numCampos,vCampos);
	            break;
	        case 1:
	            crearSHPhysical(numCampos,vCampos);
	            break;
	        case 2:
	            crearSHExtraSensorial(numCampos,vCampos);
	            break;
	        case 3:
	            crearSHFlight(numCampos,vCampos);
	            break;
	        case 4:
	            crearVillain(numCampos,vCampos);
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
	private void crearMap(int numCampos, List<String> vCampos){
	 //   System.out.println("Creado Map: " + vCampos.get(1) + "\n");
		int row = Integer.parseInt(vCampos.get(1));
		int col = Integer.parseInt(vCampos.get(2));
		int doorManSq = Integer.parseInt(vCampos.get(3));
		int depth = Integer.parseInt(vCampos.get(4));
		if(row < 1 || col < 1 || doorManSq < 0 || doorManSq > row * col || depth < 0){
			System.err.println("[ERROR] THE MAP HAS INVALID VARIABLES");
			System.err.println("[ERROR] Charging a default map");
			row = col = 6;
			doorManSq = row*col;
			depth = 4;
		}
		Game.getSI(row, col, doorManSq, depth);

	}

	/**
	 *  método que crea una instancia de la clase SHPhysical
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearSHPhysical(int numCampos, List<String> vCampos){
	  //  System.out.println("Creado SHPhysical: " + vCampos.get(1) + "\n");
		SHPhysical s = new SHPhysical(vCampos.get(1), vCampos.get(2), Integer.parseInt(vCampos.get(3)));
	 	Game.getSI().insertCharacter(s);
	       //TODO Registrar SHPhysical en el mapa
	}

	/**
	 *  método que crea una instancia de la clase SHExtraSensorial
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearSHExtraSensorial(int numCampos, List<String> vCampos){
	//    System.out.println("Creado SHExtraSensorial: " + vCampos.get(1) + "\n");
		SHExtraSensorial s = new SHExtraSensorial(vCampos.get(1), vCampos.get(2), Integer.parseInt(vCampos.get(3)));
		Game.getSI().insertCharacter(s);

		//TODO Registrar SHExtraSensorial en el mapa
	}	

	/**
	 *  método que crea una instancia de la clase SHFlight
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearSHFlight(int numCampos, List<String> vCampos){
	    //System.out.println("Creado SHFlight: " + vCampos.get(1) + "\n");
		SHExtraFlight s = new SHExtraFlight(vCampos.get(1), vCampos.get(2), Integer.parseInt(vCampos.get(3)));
		Game.getSI().insertCharacter(s);

		//TODO Registrar SHFlight en el mapa
	}	

	/**
	 *  método que crea una instancia de la clase Villain
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearVillain(int numCampos, List<String> vCampos){
	    //System.out.println("Creado Villain: " + vCampos.get(1) + "\n");
		Villain s = new Villain(vCampos.get(1), vCampos.get(2), Integer.parseInt(vCampos.get(3)));
		Game.getSI().insertCharacter(s);
	    //TODO Registrar Villain en el mapa
	}

}
