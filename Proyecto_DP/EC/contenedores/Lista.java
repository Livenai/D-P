package contenedores;

import personaje.Personaje;

/**
 * Clase que implementa una lista doblemente enlazada con los metodos mas comunes
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 *
 * @param <T>
 */
public class Lista <T extends Comparable <T>>{
	//atributos
	private NodoLista<T> PPrimero;
	private NodoLista<T> PUltimo;
	private int tam; // numero de elementos de la lista
	
	/**
	 * ctor.
	 */
	public Lista() {
		PPrimero = null;
		PUltimo = null;
		tam = 0;
	}
	
	//metodos
	
	/**
	 * Inserta el elemento dato en la primera posicion
	 * (el resto de elementos se desplazan)
	 * @param dato
	 */
	public void insertarPrimero(T dato){
		//creamos el nodo
		NodoLista<T> nuevo = new NodoLista<T>();
		nuevo.setDato(dato);
		nuevo.setAnterior(null);
		nuevo.setSiguiente(PPrimero);
		//ponemos el nuevo nodo el primero
		if(PPrimero != null){
			PPrimero.setAnterior(nuevo);
		}
		PPrimero = nuevo;
		tam++;
		//chequeo de primer elemento:
		if(tam == 1){
			PUltimo = PPrimero;
		}
	}
	
	/**
	 * Inserta el elemento dato en la ultima posicion
	 * @param dato
	 */
	public void insertarUltimo(T dato){
		//creamos el nodo
		NodoLista<T> nuevo = new NodoLista<T>();
		nuevo.setDato(dato);
		nuevo.setAnterior(PUltimo);
		nuevo.setSiguiente(null);
		//ponemos el nuevo nodo el primero
		if(PUltimo != null){
			PUltimo.setSiguiente(nuevo);
		}
		PUltimo = nuevo;
		tam++;
		//chequeo de primer elemento:
		if(tam == 1){
			PPrimero = PUltimo;
		}
	}
	
	/**
	 * devuelve el elemento en la primera posicion.
	 * @return
	 */
	public T obtenerPrimero(){
		if(PPrimero != null){
			return PPrimero.getDato();
		}
		return null;
		
	}
	
	/**
	 * devuelve el elemento en la ultima posicion.
	 * @return
	 */
	public T obtenerUltimo(){
		if(PUltimo != null)
		return PUltimo.getDato();
		return null;
	}
	
	/**
	 * Devuelve el primer elemento de la lista
	 *  y borra dicho elemento.
	 *  Si la lista esta vacia, no hace nada (devuelve null)
	 * @return
	 */
	public T borrarPrimero(){
		if(tam > 0){
			NodoLista<T> ret = PPrimero;
			PPrimero = PPrimero.getSiguiente();
			tam--;
			return ret.getDato();
		}
		return null;
	}
	
	/**
	 * Devuelve el ultimo elemento de la lista
	 *  y borra dicho elemento.
	 *  Si la lista esta vacia, no hace nada (devuelve null)
	 * @return
	 */
	public T borrarUltimo(){
		if(tam > 0){
			NodoLista<T> ret = PUltimo;
			PUltimo = PUltimo.getAnterior();
			tam--;
			return ret.getDato();
		}
		return null;
	}
	
	/**
	 * Devuelve el elemento en la posicion i
	 * (se entiende que el primer elemento es el i=0
	 * y el ultimo el i=tam-1)
	 * SI i < 0 || i >= tam   -> devuelve null
	 * @param i
	 * @return
	 */
	public T obtenerElemento(int i){
		if(!(i < 0 || i >= tam)){
			//caso del primero
			if(i == 0){
				return PPrimero.getDato();
			}
			//caso del ultimo
			if(i == tam-1){
				return PUltimo.getDato();
			}
			//caso intermedio O(n/2)
			//si esta en la primera mitad
			if(i <= (tam-1)/2){
				NodoLista<T> ret = PPrimero;
				for (int j = 0; j < i; j++) {
					ret = ret.getSiguiente();
				}
				return ret.getDato();
			}
			//si esta en la segunda mitad
			if(i > (tam-1)/2){
				NodoLista<T> ret = PUltimo;
				for (int j = tam-1; j > i; j--) {
					ret = ret.getAnterior();
				}
				return ret.getDato();
			}
		}
		return null;
	}
	
	/**
	 * Borra de la lista y devuelve el elemento en la posicion i
	 * (se entiende que el primer elemento es el i=0
	 * y el ultimo el i=tam-1)
	 * @param i
	 * @return
	 */
	public T borrarElemento(int i){
		if(!(i < 0 || i >= tam)){//si hay algo e @i esta bien
			//caso del primero
			if(i == 0){
				NodoLista<T> ret = PPrimero;
				if(tam>1)PPrimero.getSiguiente().setAnterior(null);
				PPrimero = PPrimero.getSiguiente();
				tam--;
				return ret.getDato();
			}
			//caso del ultimo
			if(i == tam-1){
				NodoLista<T> ret = PUltimo;
				if(tam>1)PUltimo.getAnterior().setSiguiente(null);
				PUltimo = PUltimo.getAnterior();
				tam--;
				return ret.getDato();
			}
			//si esta en el medio
			if(i <= (tam-1)){
				NodoLista<T> ret = PPrimero;
				for (int j = 0; j < i; j++) {
					ret = ret.getSiguiente();
				}
				ret.getAnterior().setSiguiente(ret.getSiguiente());
				ret.getSiguiente().setAnterior(ret.getAnterior());
				tam--;
				return ret.getDato();
			}

		}
		return null;
	}
	
	/**
	 * Ordena la lista COPIANDOLA (aunque no de la mejor manera :/)
	 * (orden:
	 * True -> ascendente (1)
	 * False -> Descendente (-1))
	 * @param orden
	 * @return devuelve una lista copia ordenada
	 */
	public Lista<T> ordenarLista(boolean orden){
		Lista<T> ret = new Lista<T>();
		for (int i = 0; i < this.tam; i++) {
			ret.insertarEnOrden(this.obtenerElemento(i), orden);
		}
		return ret;	
	}

	/**
	 * Dice si esta vacia la lista
	 * True -> Vacia
	 * @return
	 */
	public Boolean estaVacia(){
		return tam == 0;
	}
	
	

	/**
	 * Inserta el elemento en onden el la lista
	 * PRE: la lista debe estar orenada.
	 * @param orden true - ascendente(1)
	 * 				false - descendente(-1)
	 * @param dato
	 */
	public void insertarEnOrden(T dato,boolean orden) {
		NodoLista<T> aux = new NodoLista<T>(dato);
		if(estaVacia()) {
			insertarPrimero(aux.getDato());
		} else {

			if(orden) {//ascendente
				int donde = 0; // id del nodo que te tocara tener delante 
				NodoLista<T> punteroA = PPrimero;
				while( !(punteroA == null) && 0 <  punteroA.compareTo(aux)){//buscando el hueco
						punteroA = punteroA.getSiguiente();
						donde++;
					}
				insertarElemento(aux.getDato(),donde);
			}  else {//descendente
				int donde = 0; // id del nodo que te tocara tener delante 
				NodoLista<T> punteroA = PPrimero;
				while( !(punteroA == null) && 0 >  punteroA.compareTo(aux)){//buscando el hueco
						punteroA = punteroA.getSiguiente();
						donde++;
					}
				insertarElemento(aux.getDato(),donde);	
			}
		
		}
	}

	
	/**
	 * Inserta un elemento en la pos @donde.
	 * el resto de elementos por delante de el quedan desplazados
	 * (los elementos de mayor numeracion)
	 * @param dato
	 * @param donde (0 <= @donde <= tam). 0 = insertarPrimero(), tam = insertarUltimo()
	 */
	public void insertarElemento(T dato, int donde) {
		NodoLista<T> aux = new NodoLista<T>(dato);
		if(donde<=tam && donde >= 0){// dentro del rango
			if(estaVacia()) {
				insertarPrimero(aux.getDato());
			} else {
				if(donde == 0){//si es la primera pos
					insertarPrimero(dato);
				} else 
				if(donde == tam){//si es en la ultima pos
					insertarUltimo(dato);
				} else if(donde > 0 && donde < tam){ // si es por el medio
					aux.setSiguiente(PPrimero);
					for (int i = 0; i < donde; i++) {
						aux.setSiguiente(aux.getSiguiente().getSiguiente()); // avanzamos
					}
					aux.setAnterior(aux.getSiguiente().getAnterior());
					aux.getAnterior().setSiguiente(aux);
					aux.getSiguiente().setAnterior(aux);
					tam++;
				} else {
					System.err.println("no se ha insertado elemento " + dato + " en la pos " + donde + " :(");
				}
			}
		} else {
			System.err.println("se esta intentando insertar en un elemento en la"
					+ " pos " + donde + " cuando la lista es de tama�o " + tam + ".\n(0 <= @donde <= tam)");
		}
	}
	
	/**
	 * Musetra el contenido de la lista por pantalla
	 */
	public void mostrarLista(){
		System.out.print("Lista: [");
		for (int i = 0; i < tam; i++) {
			System.out.print(" " + this.obtenerElemento(i) + " ,");
		}
		System.out.println("]");
	}
	
	/**
	 * devuelve el tamano de la lista (int)
	 * @return
	 */
	public int obtenerTam(){
		return tam;
	}

	
	/**
	 * Devuelve una copia de la lista para que existan dos iguales y no dos punteros a la misma)
	 * @return Lista<T> opiada
	 */
	public Lista<T> copia() {
		Lista<T> ret = new Lista<T>();
		for (int i = 0; i < tam; i++) {
			ret.insertarElemento(obtenerElemento(i),i);
		}
		return ret;
	}

}
