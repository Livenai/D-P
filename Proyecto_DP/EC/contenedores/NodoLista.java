package contenedores;

/**
 * Clase que representa cada uno de los nodos de 
 * la clase Lista (lista doblemente enlazada).
 * @author CARLOS MUÑOZ ZAPATA
 * GIIIC
 *
 * @param <T> parametro generico que puede ser 
 * de cualquier tipo que implemente los metodos 
 * minimos necesarios para poder utilizar la lista.
 */
public class NodoLista <T extends Comparable <T>>{
	//atributos
	private T dato;
	private NodoLista<T> siguiente;
	private NodoLista<T> anterior;

	
	
	//metodos
	
	/**
	 * Ctor parametrizao (solo el dato)
	 * recordar asignar los punteros 
	 * @param dato_
	 */
	public NodoLista(T dato_) {
		dato = dato_;
	}
	
	/**
	 * Ctor normal
	 */
	public NodoLista() {}

	public T getDato() {
		return dato;
	}
	public void setDato(T dato) {
		this.dato = dato;
	}
	public NodoLista<T> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoLista<T> siguiente) {
		this.siguiente = siguiente;
	}
	public NodoLista<T> getAnterior() {
		return anterior;
	}
	public void setAnterior(NodoLista<T> anterior) {
		this.anterior = anterior;
	}
	

	//especiales
	
	/**
	 * compara los elementos de los nodos, pues estos han de ser comparables
	 * utiliza el metodo compareTo()
	 * @param elOtro - segundo parametro
	 * @return	1,0,-1 si this >,=,< que elOtro
	 */
	public int compareTo(NodoLista<T> elOtro){
		Integer ret = this.dato.compareTo(elOtro.dato);
		return ret;
	}
}
