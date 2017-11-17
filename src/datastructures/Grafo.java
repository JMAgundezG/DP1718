package datastructures;


import java.util.*;

/**
* @file grafo.h
* Declaracion de la clase grafo
* @author
* Profesores DP.
* Asignatura Desarrollo de Programas.
* Curso 11/12.
*/
public class Grafo {
	public static final int MAXVERT=115;
	public static final int INFINITO = 9999;
	public static final int NOVALOR = -1;
	
	/** Numero de nodos del grafo */
    private int numNodos;        

    /** Vector que almacena los nodos del grafo */
    private int[] nodos = new int[MAXVERT];           

    /** Matriz de adyacencia, para almacenar los arcos del grafo */
    private int[][] arcos = new int[MAXVERT][MAXVERT];    

	/** Matriz de Camino (Warshall - Path) */
    private boolean [][] warshallC = new boolean[MAXVERT][MAXVERT];    

    /** Matriz de Costes (Floyd - Cost) */
    private int[][] floydC = new int[MAXVERT][MAXVERT];    
	
    /** Matriz de Camino (Floyd - Path) */
    private int[][] floydP = new int[MAXVERT][MAXVERT];	

    /**
    * Metodo constructor por defecto de la clase grafo
    * @param "" No recibe parametros
    * @return No retorna ningun valor
    */
    public Grafo() {
        int x,y;
        numNodos=0;

        for (x=0;x<MAXVERT;x++)
            nodos[x]= NOVALOR;

        for (x=0;x<MAXVERT;x++)
            for (y=0;y<MAXVERT;y++){
                arcos[x][y]=INFINITO;
                warshallC[x][y]=false;
                floydC[x][y]=INFINITO;
                floydP[x][y]=NOVALOR;
            }
      
        // Diagonales
        for (x=0;x<MAXVERT;x++){
            arcos[x][x]=0;
            warshallC[x][x]=false;
            floydC[x][x]=0;
            //floydP[x][x]=NO_VALOR;
        }
    }
    
    /**
    * Metodo que comprueba si el grafo esta vacio
    * @param "" No recibe parametros
    * @return Retorna un valor booleano que indica si el grafo esta o no vacio
    */
    public boolean esVacio () {
        return (numNodos==0);
    }

    /**
    * Metodo que inserta un nuevo arco en el grafo
    * @param origen es el nodo de origen del arco nuevo
    * @param destino es el nodo de destino del arco nuevo
    * @param valor es el peso del arco nuevo 
    * @return true si se pudo insertar
    */
    public boolean nuevoArco(int origen, int destino, int valor) {
        boolean resultado= false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos))	{
            arcos[origen][destino]=valor; 
            resultado=true;
        }
        return resultado;
    }

    /**
    * Metodo que borra un arco del grafo
    * @param origen es el nodo de origen del arco nuevo
    * @param destino es el nodo de destino del arco nuevo
    * @return true si se pudo borrar
    */
    public boolean borraArco(int origen, int destino) {
        boolean resultado = false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
        	arcos[origen][destino]=INFINITO;	
            resultado=true;
        }
        return resultado;
    }

    /**
    * Metodo que comprueba si dos nodos son adyacentes
    * @param origen es el primer nodo
    * @param destino es el segundo nodo
    * @return Retorna un valor booleano que indica si los dos nodos son adyacentes
    */
    public boolean adyacente (int origen, int destino) {
        boolean resultado= false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos))      
    		resultado = (arcos[origen][destino]!=INFINITO); 
        return resultado;
    }

    /**
    * Metodo que retorna el peso de un arco
    * @param origen es el primer nodo del arco
    * @param destino es el segundo nodo del arco
    * @return Retorna un valor entero que contiene el peso del arco
    */
    public int getArco (int origen, int destino) {
        int arco=NOVALOR;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) 	
    		arco=arcos[origen][destino];				     
        return arco;
    }

    /**
    * Metodo que inserta un nuevo nodo en el grafo
    * @param n es el nodo que se desea insertar
    * @return true si se pudo insertar
    */
    public boolean nuevoNodo(int n) {
        boolean resultado=false;

        if (numNodos<MAXVERT){
            nodos[numNodos]=n;
            numNodos++;
            resultado=true;
        }
        return resultado;
    }

    /**
    * Metodo que elimina un nodo del grafo
    * @param nodo nodo que se desea eliminar
    * @return true si se pudo borrar
    */
    public boolean borraNodo(int nodo) {
        boolean resultado=false;
    	int pos = nodo; 

        if ((numNodos>0) && (pos <= numNodos)) {
            int x,y;
            // Borrar el nodo
            for (x=pos; x<numNodos-1; x++){		
                nodos[x]=nodos[x+1];
    			System.out.println(nodos[x+1]);
    		}
            // Borrar en la Matriz de Adyacencia
            // Borra la fila
            for (x=pos; x<numNodos-1; x++)		
                for (y=0;y<numNodos; y++)
                    arcos[x][y]=arcos[x+1][y];
            // Borra la columna
            for (x=0; x<numNodos; x++)
                for (y=pos;y<numNodos-1; y++)	
                    arcos[x][y]=arcos[x][y+1];
            // Decrementa el numero de nodos
            numNodos--;
            resultado=true;
        }
        return resultado;
    }

    /**
    * Metodo que muestra el vector de nodos del grafo
    * @param "" No recibe parametros
    * @return No retorna ningun valor
    */
    public void mostrarNodos() {
        System.out.println("NODOS:");
        for (int x=0;x<numNodos;x++)
            System.out.print(nodos[x] + " ");
        System.out.println();
    }

    /**
    * Metodo que muestra los arcos del grafo (la matriz de adyacencia)
    * @param "" No recibe parametros
    * @return No retorna ningun valor
    */
    public void mostrarArcos()
    {
        int x,y;

        System.out.println("ARCOS:");
        for (x=0;x<numNodos;x++) {
            for (y=0;y<numNodos;y++) {
                //cout.width(3);
                if (arcos[x][y]!=INFINITO)
                    System.out.format("%4d",arcos[x][y]);
                else
                    System.out.format("%4s","#");
            }
            System.out.println();
        }
    }


    /**
    * Metodo que devuelve el conjunto de nodos adyacentes al nodo actual
    * @param origen es el nodo actual
    * @param ady En este conjunto se almacenarán los nodos adyacentes al nodo origen
    * @return No retorna ningun valor
    */
    public void adyacentes(int origen, Set<Integer> ady){
       if ((origen >= 0) && (origen < numNodos)) {
    		for (int i=0;i<numNodos;i++) {
           	 	if ((arcos[origen][i]!=INFINITO) && (arcos[origen][i]!=0))	
              		ady.add(i);
            }
    	}
    }

    
    /**
     * Metodo que muestra la matriz de Warshall
     * @param "" No recibe parametros
     * @return No retorna ningun valor
     */
     public void mostrarPW()
     {
         int x,y;

         System.out.println("warshallC:");
         for (x=0;x<numNodos;x++) {
             for (y=0;y<numNodos;y++)
                 System.out.format("%6b",warshallC[x][y]);
             System.out.println();
         }
     }

     /**
     * Metodo que muestra las matrices de coste y camino de Floyd
     * @param "" No recibe parametros
     * @return No retorna ningun valor
     */
     public void mostrarFloydC()
     {
         int x,y;
         System.out.println("floydC:");
         for (y=0;y<numNodos;y++) {
             for (x=0;x<numNodos;x++) {
                 //cout.width(3);
                 System.out.format("%4d",floydC[x][y]);
             }
             System.out.println();
         }

         System.out.println("floydP:");
         for (x=0;x<numNodos;x++) {
             for (y=0;y<numNodos;y++) {
                 //cout.width(2);
                 System.out.format("%4d",floydP[x][y]);
             }
             System.out.println();
         }
     }

     /**
     * Metodo que realiza el algoritmo de Warshall sobre el grafo
     * @param "" No recibe parametros
     * @return No retorna ningun valor
     */
     public void warshall() {
         int i,j,k;

         // Obtener la matriz de adyacencia en P
         for (i=0;i<numNodos;i++)
             for (j=0;j<numNodos;j++)
                 warshallC[i][j]=(arcos[i][j]!=INFINITO);

         // Iterar
         for (k=0;k<numNodos;k++)
             for (i=0;i<numNodos;i++)
                 for (j=0;j<numNodos;j++)
                     warshallC[i][j]=(warshallC[i][j] || (warshallC[i][k] && warshallC[k][j]));
     }

     /**
     * Metodo que realiza el algoritmo de Floyd sobre el grafo
     */

     public void floyd (){
         int i,j,k;

         // Obtener la matriz de adyacencia en P
         for (i=0;i<numNodos;i++)
             for (j=0;j<numNodos;j++){
                 floydC[i][j]=arcos[i][j];
     			floydP[i][j]=NOVALOR; 	
     		}

         // Iterar
         for (k=0;k<numNodos;k++)
             for (i=0;i<numNodos;i++)
                 for (j=0;j<numNodos;j++)
                     if (i!=j)
                         if ((floydC[i][k]+floydC[k][j] < floydC[i][j])) {
                             floydC[i][j]=floydC[i][k]+floydC[k][j];		
                             floydP[i][j]=k;
                         }
     }

     /**
      * Metodo que devuelve el siguiente nodo en la ruta entre un origen y un destino
      * @param origen es el primer nodo
      * @param destino es el segundo nodo
      * @param origen es el primer nodo
      * @param //sig parametro de entrada salida que devuelve el siguiente nodo en la ruta entre origen y destino
      * @return No retorna ningun valor
      */
      public int siguiente(int origen, int destino){
      	int sig=-1; // Si no hay camino posible
          if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
      		if (warshallC[origen][destino]){ // Para comprobar que haya camino
      	    	if (floydP[origen][destino]!=NOVALOR)
      				sig = siguiente(origen, floydP[origen][destino]);	
          		else
          			sig=destino;
      		}
      	}
          return sig;
      }

      public LinkedList<Integer> path(int sourcePosition, int destinationPosition){

          LinkedList<Integer> path = new LinkedList<>();
          int current = sourcePosition;

          while(current!= destinationPosition){
              current = siguiente(current, destinationPosition);
              if(current == -1){
                  return null;
              }
              path.addLast(current);
          }
          return path;
      }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    //Datos ejemplo para creación grafo
		Grafo g = new Grafo();
	    int [] nodos = new int[10];
	    int [][] arcos = {{0,97,Grafo.INFINITO,Grafo.INFINITO,119,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO},
	                      {97,0,10,Grafo.INFINITO,Grafo.INFINITO,57,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO},
	                      {Grafo.INFINITO,10,0,17,Grafo.INFINITO,Grafo.INFINITO,67,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO},
	                      {Grafo.INFINITO,Grafo.INFINITO,17,0,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,122},
	                      {119,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,0,Grafo.INFINITO,Grafo.INFINITO,37,Grafo.INFINITO,Grafo.INFINITO},
	                      {Grafo.INFINITO,57,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,0,63,Grafo.INFINITO,50,Grafo.INFINITO},
	                      {Grafo.INFINITO,Grafo.INFINITO,67,Grafo.INFINITO,Grafo.INFINITO,63,0,Grafo.INFINITO,61,Grafo.INFINITO},
	                      {Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,37,Grafo.INFINITO,Grafo.INFINITO,0,72,Grafo.INFINITO},
	                      {Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,50,61,72,0,153},
	                      {Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,122,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,153,0}};

	    for(int i=0;i<10;i++){
			nodos[i]=i;
	        if (!g.nuevoNodo(nodos[i]))
				System.out.println("Error en la insercion del nodo " + i);
		}

	    for(int i=0;i<10;i++)
	        for(int j=0;j<10;j++)
	            if (!g.nuevoArco(nodos[i],nodos[j],arcos[i][j]))
	            	System.out.println("Error en la insercion del arco("+i+","+j+")");

	    g.mostrarNodos();
	    g.mostrarArcos();
	    
	    System.out.println("-------------------------------------");

	    g.warshall();
	    g.mostrarPW();

	    g.floyd();
	    g.mostrarFloydC();

	    System.out.println("-------------------------------------");

	   // ------------------------------------------------------------------------------
   	   // Probando la función adyacentes:
	   int nodo = nodos[8];
	   Set<Integer> ady = new LinkedHashSet<Integer>();
	   System.out.print("Nodos adyacentes al nodo : " + nodo + ":");
 	   g.adyacentes(nodo,ady);
 	   for (Integer n : ady){
		      System.out.print (" " + n);
	   }
	   // ------------------------------------------------------------------------------


	}
}

