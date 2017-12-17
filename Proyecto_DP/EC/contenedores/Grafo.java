package contenedores;

import java.util.LinkedList;
import java.util.Set;

import mapa.Mapa;


/**
 * @file grafo.h Declaracion de la clase grafo
 * @author <b> Profesores DP </b><br>
 *         <b> Asignatura Desarrollo de Programas</b><br>
 *         <b> Curso 11/12 </b>
 */

public class Grafo {
	public static final int MAXVERT = 1100;// 115
	public static final int INFINITO = 9999;
	public static final int NOVALOR = -1;

	/** Numero de nodos del grafo */
	private int numNodos;

	/** Vector que almacena los nodos del grafo */
	private int[] nodos = new int[MAXVERT];

	/** Matriz de adyacencia, para almacenar los arcos del grafo */
	private int[][] arcos = new int[MAXVERT][MAXVERT];

	/** Matriz de Camino (Warshall - Path) */
	private boolean[][] warshallC = new boolean[MAXVERT][MAXVERT];

	/** Matriz de Costes (Floyd - Cost) */
	private int[][] floydC = new int[MAXVERT][MAXVERT];

	/** Matriz de Camino (Floyd - Path) */
	private int[][] floydP = new int[MAXVERT][MAXVERT];

	/**
	 * Metodo constructor por defecto de la clase grafo
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public Grafo() {
		int x, y;
		numNodos = 0;

		for (x = 0; x < MAXVERT; x++)
			nodos[x] = NOVALOR;

		for (x = 0; x < MAXVERT; x++)
			for (y = 0; y < MAXVERT; y++) {
				arcos[x][y] = INFINITO;
				warshallC[x][y] = false;
				floydC[x][y] = INFINITO;
				floydP[x][y] = NOVALOR;
			}

		// Diagonales
		for (x = 0; x < MAXVERT; x++) {
			arcos[x][x] = 0;
			warshallC[x][x] = false;
			floydC[x][x] = 0;
			// floydP[x][x]=NO_VALOR;
		}
	}

	/**
	 * Metodo que comprueba si el grafo esta vacio
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return Retorna un valor booleano que indica si el grafo esta o no vacio
	 */
	public boolean esVacio() {
		return (numNodos == 0);
	}

	/**
	 * Metodo que inserta un nuevo arco en el grafo
	 * 
	 * @param origen
	 *            es el nodo de origen del arco nuevo
	 * @param destino
	 *            es el nodo de destino del arco nuevo
	 * @param valor
	 *            es el peso del arco nuevo
	 * @return true si se pudo insertar
	 */
	public boolean nuevoArco(int origen, int destino, int valor) {
		boolean resultado = false;
		if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
			arcos[origen][destino] = valor;
			resultado = true;
		}
		return resultado;
	}

	/**
	 * Metodo que borra un arco del grafo
	 * 
	 * @param origen
	 *            es el nodo de origen del arco nuevo
	 * @param destino
	 *            es el nodo de destino del arco nuevo
	 * @return true si se pudo borrar
	 */
	public boolean borraArco(int origen, int destino) {
		boolean resultado = false;
		if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
			arcos[origen][destino] = INFINITO;
			resultado = true;
		}
		return resultado;
	}

	/**
	 * Metodo que comprueba si dos nodos son adyacentes
	 * 
	 * @param origen
	 *            es el primer nodo
	 * @param destino
	 *            es el segundo nodo
	 * @return Retorna un valor booleano que indica si los dos nodos son
	 *         adyacentes
	 */
	public boolean adyacente(int origen, int destino) {
		boolean resultado = false;
		if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos))
			resultado = (arcos[origen][destino] != INFINITO);
		return resultado;
	}

	/**
	 * Metodo que retorna el peso de un arco
	 * 
	 * @param origen
	 *            es el primer nodo del arco
	 * @param destino
	 *            es el segundo nodo del arco
	 * @return Retorna un valor entero que contiene el peso del arco
	 */
	public int getArco(int origen, int destino) {
		int arco = NOVALOR;
		if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos))
			arco = arcos[origen][destino];
		return arco;
	}

	/**
	 * Metodo que inserta un nuevo nodo en el grafo.
	 * @param n
	 *            es el nodo que se desea insertar
	 * @return true si se pudo insertar
	 */
	public boolean nuevoNodo(int n) {
		boolean resultado = false;

		if (numNodos < MAXVERT) {
			nodos[numNodos] = n;
			numNodos++;
			resultado = true;
		}
		return resultado;
	}

	/**
	 * Metodo que elimina un nodo del grafo
	 * 
	 * @param nodo
	 *            nodo que se desea eliminar
	 * @return true si se pudo borrar
	 */
	public boolean borraNodo(int nodo) {
		boolean resultado = false;
		int pos = nodo;

		if ((numNodos > 0) && (pos <= numNodos)) {
			int x, y;
			// Borrar el nodo
			for (x = pos; x < numNodos - 1; x++) {
				nodos[x] = nodos[x + 1];
				System.out.println(nodos[x + 1]);
			}
			// Borrar en la Matriz de Adyacencia
			// Borra la fila
			for (x = pos; x < numNodos - 1; x++)
				for (y = 0; y < numNodos; y++)
					arcos[x][y] = arcos[x + 1][y];
			// Borra la columna
			for (x = 0; x < numNodos; x++)
				for (y = pos; y < numNodos - 1; y++)
					arcos[x][y] = arcos[x][y + 1];
			// Decrementa el numero de nodos
			numNodos--;
			resultado = true;
		}
		return resultado;
	}

	/**
	 * Metodo que muestra el vector de nodos del grafo
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void mostrarNodos() {
		System.out.println("NODOS:");
		for (int x = 0; x < numNodos; x++)
			System.out.print(nodos[x] + " ");
		System.out.println();
	}

	/**
	 * Metodo que muestra los arcos del grafo (la matriz de adyacencia)
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void mostrarArcos() {
		int x, y;

		System.out.println("ARCOS:");
		for (x = 0; x < numNodos; x++) {
			for (y = 0; y < numNodos; y++) {
				// cout.width(3);
				if (arcos[x][y] != INFINITO)
					System.out.format("%4d", arcos[x][y]);
				else
					System.out.format("%4s", "#");
			}
			System.out.println();
		}
	}

	/**
	 * Metodo que devuelve el conjunto de nodos adyacentes al nodo actual
	 * 
	 * @param origen
	 *            es el nodo actual
	 * @param ady
	 *            En este conjunto se almacenarán los nodos adyacentes al nodo
	 *            origen
	 * @return No retorna ningun valor
	 */
	public void adyacentes(int origen, Set<Integer> ady) {
		if ((origen >= 0) && (origen < numNodos)) {
			for (int i = 0; i < numNodos; i++) {
				if ((arcos[origen][i] != INFINITO) && (arcos[origen][i] != 0))
					ady.add(i);
			}
		}
	}

	/**
	 * Metodo que muestra la matriz de Warshall
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void mostrarPW() {
		int x, y;

		System.out.println("warshallC:");
		for (x = 0; x < numNodos; x++) {
			for (y = 0; y < numNodos; y++)
				System.out.format("%6b", warshallC[x][y]);
			System.out.println();
		}
	}

	/**
	 * Metodo que muestra las matrices de coste y camino de Floyd
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void mostrarFloydC() {
		int x, y;
		System.out.println("floydC:");
		for (y = 0; y < numNodos; y++) {
			for (x = 0; x < numNodos; x++) {
				// cout.width(3);
				if(floydC[x][y] == INFINITO){
					System.out.format("%4d", -1);
					//System.out.print('.');

				} else {
					System.out.format("%4d", floydC[x][y]);
				}
			}
			System.out.println();
		}

		System.out.println("floydP:");
		for (x = 0; x < numNodos; x++) {
			for (y = 0; y < numNodos; y++) {
				// cout.width(2);
				System.out.format("%4d", floydP[x][y]);
			}
			System.out.println();
		}
	}

	/**
	 * Metodo que realiza el algoritmo de Warshall sobre el grafo
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void warshall() {
		int i, j, k;

		// Obtener la matriz de adyacencia en P
		for (i = 0; i < numNodos; i++)
			for (j = 0; j < numNodos; j++)
				warshallC[i][j] = (arcos[i][j] != INFINITO);

		// Iterar
		for (k = 0; k < numNodos; k++)
			for (i = 0; i < numNodos; i++)
				for (j = 0; j < numNodos; j++)
					warshallC[i][j] = (warshallC[i][j] || (warshallC[i][k] && warshallC[k][j]));
	}

	/**
	 * Metodo que realiza el algoritmo de Floyd sobre el grafo
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void floyd() {
		int i, j, k;

		// Obtener la matriz de adyacencia en P
		for (i = 0; i < numNodos; i++)
			for (j = 0; j < numNodos; j++) {
				floydC[i][j] = arcos[i][j];
				floydP[i][j] = NOVALOR;
			}

		// Iterar
		for (k = 0; k < numNodos; k++)
			for (i = 0; i < numNodos; i++)
				for (j = 0; j < numNodos; j++)
					if (i != j)
						if ((floydC[i][k] + floydC[k][j] < floydC[i][j])) {
							floydC[i][j] = floydC[i][k] + floydC[k][j];
							floydP[i][j] = k;
						}
	}

	/**
	 * Metodo que devuelve el siguiente nodo en la ruta entre un origen y un
	 * destino
	 * 
	 * @param origen
	 *            es el primer nodo
	 * @param destino
	 *            es el segundo nodo
	 * @return (int) devuelve el siguiente nodo en
	 *            la ruta entre origen y destino
	 */
	public int siguiente(int origen, int destino) {
		int sig = -1; // Si no hay camino posible
		if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
			if (warshallC[origen][destino]) { // Para comprobar que haya camino
				if (floydP[origen][destino] != NOVALOR)
					sig = siguiente(origen, floydP[origen][destino]);
				else
					sig = destino;
			}
		}
		return sig;
	}

	/**
	 * Metodo que devuelve la distancia minima en NODOS entre A y B
	 * no tiene en cuenta el peso de los arcos.
	 * @param A -> origen
	 * @param B -> destino
	 * @return int
	 */
	public int distanciaMinimaEntre(int A, int B) {
		int ret = 1;
		int aux = A;
		int sig = siguiente(aux, B);
		while(sig != B){
			
			ret++;
			aux = sig;
			sig = siguiente(aux, B);
		}
		return ret;
	}
	
	/**
	 * Dice si la distancia entre A y B es de 3 o no
	 * @param A origen
	 * @param B destino
	 * @return bool. True -> dir==3
	 */
	public boolean disIgualATres(int A, int B){
		boolean ret = false;
		int dis = distanciaMinimaEntre(A, B);
		if(dis == 3){
			ret = true;
		}
		return ret;
	}

	/**
	 * Metodo que devuelve todos los caminos posibles desde el origen al destino 
	 * en forma de LinkedList de caminos de ID (Integer)
	 * @param origen donde empezamos
	 * @param destino el nodo objetivo final del camino
	 * @return LinkedList de LinkedList con Integer
	 */
	public LinkedList<LinkedList<Integer>> obtenerPosiblesCaminos(int origen, int destino) {
		LinkedList<LinkedList<Integer>> ret = new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer> camino = new LinkedList<Integer>();
		
		ret = obtenerPosiblesCaminosR(origen,destino,camino,ret);
		
		return ret;
	}
	
	
	/**
	 * Metodo auxiliar recursivo que realiza el proceso de obtencion de los posibles caminos(rellena ret)
	 * @param actual donde estamos
	 * @param destino el nodo objetivo final del camino
	 * @return LinkedList de LinkedList con Integer
	 */
	private LinkedList<LinkedList<Integer>> obtenerPosiblesCaminosR(int actual, int destino, LinkedList<Integer> camino, LinkedList<LinkedList<Integer>> ret) {
		Mapa uni = Mapa.obtenerUnico();
		int opcionActual = 0;
		char[] posiblesOpciones = {'N','S','E','W'};
		
		//mientras haya opciones
		while(opcionActual < 4){
			//System.err.println("actual: " + actual + "  Opcion: " + posiblesOpciones[opcionActual]);
			//¿es posible la opcion?
			if(!uni.getSalaConID(actual).hayMuroEn(posiblesOpciones[opcionActual])
					&& !camino.contains(actual)){//si no hay muro Y si no hemos pasado ya por hai
				//anotar opcion
				camino.addLast(actual);
				//¿es solucion final?
				if(actual == destino){
					//creaos una copia para no estropearlo despues
					LinkedList<Integer> copia = new LinkedList<Integer>();
					copia.addAll(camino);
					ret.addLast(copia);
					opcionActual = 5;
					//System.err.println("camino-> " + camino.toString());
				}
				//si no es solucion final, continuamos
				else {
					switch(posiblesOpciones[opcionActual]){
					case 'N':
						ret = obtenerPosiblesCaminosR(actual-uni.getAncho(), destino, camino, ret);
						break;
					case 'S':
						ret = obtenerPosiblesCaminosR(actual+uni.getAncho(), destino, camino, ret);
						break;
					case 'E':
						ret = obtenerPosiblesCaminosR(actual+1, destino, camino, ret);
						break;
					case 'W':
						ret = obtenerPosiblesCaminosR(actual-1, destino, camino, ret);
						break;
					}
				}
				//una vez terminado, borramos la anotacion
				camino.removeLast();
			}
			//cambiamos de opcion(si no quedan opciones hay que salir del while)
			opcionActual++;
		}
		
		return ret;
	}


}
