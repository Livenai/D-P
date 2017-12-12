package contenedores;

import java.util.LinkedList;

import armas.Arma;

/**
 * Implementacion de arbol binario de busqueda.
 * Algunos metodos han sido creados o editaodos 
 * por @CarlosMu�ozZapata
 * @version 1.0
 * @author ?
*/
public class Arbol <T extends Comparable <T>> {
	/** Dato almacenado en cada nodo del Arbol. */
	private T datoRaiz;
	
	/** Atributo que indica si el Arbol esta vacio. */
	boolean esVacio;
	
	/** Hijo izquierdo del nodo actual. */
	private Arbol<T> hIzq;

	/** Hijo derecho del nodo actual. */
	private Arbol<T> hDer;
	
	/**
	 * Constructor por defecto de la clase. 
	 * Inicializa un Arbol vacio.
	*/
	public Arbol(){
	    this.esVacio = true;
	    this.hIzq = null;
	    this.hDer = null;
	}

	/**
	 * Crea un nuevo Arbol a partir de los datos pasados por parametros.
	 *
	 * @param hIzq El hijo izquierdo del Arbol que se esta creando.
	 * @param datoRaiz Raiz del Arbol que se esta creando.
	 * @param hDer El hijo derecho del Arbol que se esta creando.
	*/
	public Arbol(Arbol<T> hIzq, T datoRaiz, Arbol<T> hDer) {
		this.esVacio = false;
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer = hDer;
	}
	
	/**
	 * Vacia un arbol por completo.
	 * @return El arbol vacio.
	*/
	public Arbol<T> vaciar() {
	    this.esVacio = true;
	    this.hIzq = null;
	    this.hDer = null;
	    return this;
	}
	
	/**
	 * Devuelve el hijo izquierdo del Arbol.
	 *
	 * @return El hijo izquierdo.
	*/
	public Arbol<T> getHijoIzq() {
		return hIzq;
	}
	
	/**
	 * Devuelve el hijo derecho del Arbol.
	 *
	 * @return Hijo derecho del Arbol.
	*/
	public Arbol<T> getHijoDer() {
		return hDer;
	}
	
	/**
	 * Devuelve la raiz del Arbol.
	 *
	 * @return La raiz del Arbol.
	*/
	public T getRaiz() {
		return datoRaiz;
	}
	
	/**
	 * Comprueba si el Arbol esta vacio.
	 * @return 
	 * <b>TRUE</b> si el Arbol esta vacio.<br>
	 * <b>FALSE</b> en caso contrario.
	*/
	public boolean vacio() {
		return esVacio;
	}
	
	/**
	 * Inserta un nuevo dato en el Arbol.
	 *
	 * @param dato El dato a insertar.
	 * @return 
	 * <b>TRUE</b> si el dato se ha insertado correctamente.<br>
	 * <b>FALSE</b> en caso contrario.
	*/
	public boolean insertar(T dato) {
	    boolean resultado = true;
	    if(vacio()) {
	        datoRaiz = dato;
			esVacio = false;
		} else {
			if(!(this.datoRaiz.equals(dato))) {
				Arbol<T> aux;
	            if(dato.compareTo(this.datoRaiz) < 0) {
	                if((aux = getHijoIzq()) == null) {
	                    hIzq = aux = new Arbol<T>();
	                }
	            } else {
	                if((aux = getHijoDer()) == null) {
	                    hDer = aux = new Arbol<T>();
	                }
	            }
	            
	            resultado = aux.insertar(dato);
	        } else {
	            resultado = false;
	        }
	    }
	    
	    return resultado;
	}
	
	/**
	 * Comprueba si un dato se encuentra almacenado en el Arbol.
	 *
	 * @param dato El dato a buscar.
	 * @return 
	 * <b>TRUE</b> si el dato se encuentra en el Arbol.<br>
	 * <b>FALSE</b> en caso contrario.
	*/
	public boolean pertenece(T dato) {
	    Arbol<T> aux = null;
	    boolean encontrado = false;
	    
	    if(!vacio()) {
	        if(this.datoRaiz.equals(dato)) {
	            encontrado = true;
	        } else {
	            if(dato.compareTo(this.datoRaiz) < 0) {
	                aux=getHijoIzq();
	            } else {
	                aux = getHijoDer();
	            }
	            
	            if(aux != null) {
	                encontrado = aux.pertenece(dato);
	            }
	        }
	    }
	    
	    return encontrado;
	}
	
	
	
	
	
	
	
	/**
	 * Borrar un dato del árbol.
	 *
	 * @param dato El dato que se quiere borrar
	 */
	public void borrar(T dato){
	    if (!vacio()) {
	        if (dato.compareTo(this.datoRaiz)<0){			//dato<datoRaiz
					hIzq = hIzq.borrarOrden(dato);
			}	
	        else if (dato.compareTo(this.datoRaiz)>0) {		//dato>datoRaiz 
	            		hDer = hDer.borrarOrden(dato);
				}
	            else //En este caso el dato es datoRaiz
	            {
	                if (hIzq==null && hDer==null){
	                    esVacio=true;
	                }
	                else
	                    borrarOrden(dato);
	            }
	    }
	}
	

	/**
	 * Borrar un dato. Este método es utilizado por el método borrar anterior.
	 *
	 * @param dato El dato a borrar
	 * @return Devuelve el árbol resultante después de haber realizado el borrado
	 */
	private Arbol<T> borrarOrden(T dato)
	{
	    T datoaux;
	    Arbol<T> retorno=this;
	    Arbol<T> aborrar, candidato, antecesor;

	    if (!vacio()) {
	        if (dato.compareTo(this.datoRaiz)<0 && hIzq != null){		// dato<datoRaiz
		    	        hIzq = hIzq.borrarOrden(dato);
	        }
			else
	            if (dato.compareTo(this.datoRaiz)>0 && hDer != null) {	// dato>datoRaiz
	    	           hDer = hDer.borrarOrden(dato);
	            }
				else {
	                aborrar=this;
	                if ((hDer==null)&&(hIzq==null)) { /*si es hoja*/
	                	if(datoRaiz.equals(dato)){//si es este el nodo a borrar
	                		retorno=null;
	                		/*¿Hola? si borramos un elemento hoja de un arbol con mas elementos genial, ¿no? pero
	                		 * ¿y siborramos el ULTIMO nodo del arbol. no le hacemos nada y este sigue existiendo 
	                		 * porque ni siquiera lo marcamos como vacio ¬¬?*/
	                		
	                		
	                	} else {//si el nodo a borrar parece que no existe
	                		retorno = this;
	                	}
	                }
	                else {
	                    if (hDer==null) { /*Solo hijo izquierdo*/
	                        aborrar=hIzq;
	                        datoaux=this.datoRaiz;
	                        datoRaiz=hIzq.getRaiz();
	                        hIzq.datoRaiz = datoaux;
	                        hIzq=hIzq.getHijoIzq();
	                        hDer=aborrar.getHijoDer();

	                        retorno=this;
	                    }
	                    else
	                        if (hIzq==null) { /*Solo hijo derecho*/
	                            aborrar=hDer;
	                            datoaux=datoRaiz;
	                            datoRaiz=hDer.getRaiz();
	                            hDer.datoRaiz = datoaux;
	                            hDer=hDer.getHijoDer();
	                            hIzq=aborrar.getHijoIzq();

	                            retorno=this;
	                        }
	                        else { /* Tiene dos hijos */
	                        	if(this.datoRaiz.equals(new Arma("Anillo",11))){
	                        		System.out.println("->");
	                        	}
	                        	
	                        	
	                            candidato = this.getHijoIzq();
	                            antecesor = this;
	                            while (candidato.getHijoDer()!=null) {
	                                antecesor = candidato;
	                                candidato = candidato.getHijoDer();
	                            }

	                            /*Intercambio de datos de candidato*/
	                            datoaux = datoRaiz;
	                            datoRaiz = candidato.getRaiz();
	                            candidato.datoRaiz=datoaux;
	                            aborrar = candidato;
	                            if (antecesor==this){
	                                hIzq=candidato.getHijoIzq();
	                            }
	                            else{
	                                antecesor.hDer=candidato.getHijoIzq();
	                            }
	                        } //Eliminar solo ese nodo, no todo el subarbol
	                    aborrar.hIzq=null;
	                    aborrar.hDer=null;
	                }
	            }
	    }
	    return retorno;
	}
	
	
	
	
	
	
	
	
	/**
	 * Recorrido inOrden del Arbol.
	 * @return LinkedList<T> devuelve una linked list con los 
	 * elementos ordenados tal cual estan el el arbol (inOrden)
	 * usa el CompareTo()
	*/
	public LinkedList<T> inOrden() {
		LinkedList<T> ret = new LinkedList<T>();
	    Arbol<T> aux = null;
	    if(!vacio()) {
	        if((aux = getHijoIzq()) != null) {
	            ret = aux.inOrden();
	        }    
	      
	        ret.add(this.datoRaiz);
	        
	        if((aux = getHijoDer()) != null){
	            ret.addAll(aux.inOrden());
	        }    
	    }
	    ret.sort(null);//natural ordering
	    return ret;
	}

	/**
	 * Obtiene la altura del arbol.
	 *
	 * @return Altura del arbol.
	*/
    public int alturaArbol() {
       int altura = 0;
       altura = alturaArbolR();
       return altura;
    }

    /**
     * Usado por el metodo anterior.
     * 
     * @return Altura del arbol.
     */
    private int alturaArbolR() {
    	int i = 0;
    	int d = 0;
      	int salida = 1;
      	
      	if(hIzq != null){
      		i = hIzq.alturaArbol();
      	}
    
    	if(hDer != null){
    		d = hDer.alturaArbol();
    	}
    	
  
       if(i<d){
    	   salida = salida + d;
       }else{
    	   salida = salida + i;

       }
       return salida;
    }

    /**
	 * Obtiene el numero de hojas del Arbol.
	 *
	 * @return Numero de hojas.
	*/
	public int hojas() {
		int hojas = 0;
		
		if(!vacio()) {
		    if(hIzq != null) {
		     	hojas = hojas + hIzq.hojas();
		    }    
		        
		    if(hIzq == null && hDer == null) {
		     	hojas++;
		    }
		        
		    if(hDer != null){
		    	hojas = hojas + hDer.hojas();
		    }    
		}
		
		return hojas;
	}
	
	/**
	 * Obtiene el numero de elementos que no son hojas del Arbol.
	 *
	 * @return Numero de no hojas.
	*/
	public int noHojas() {
		int nohojas = 0;
		
		if(!vacio()) {
		    if(hIzq != null) {
		    	nohojas = nohojas + hIzq.noHojas();
		    }    
		        
		    if(!(hIzq == null && hDer == null)) {
		    	nohojas++;
		    }
		        
		    if(hDer != null) {
		    	nohojas = nohojas + hDer.noHojas();
		    }    
		}
		
		return nohojas;
	}
	
	//metodos propios
	
	
	/**
	 * busca el dato @dato y si lo encuentra lo devuelve y lo borra del arbol.
	 *
	 * @param dato El dato a buscar.
	 * @param borrar True -> borra el dato al terminar,
	 * @return el dato si existe y borrar==true. NULL en otro caso
	*/
	public T obtenerBorrando(T dato, boolean borrar) {
	    T encontrado = null;
	    
	    encontrado = obtenerEquivalente(dato);
	    
	    if(encontrado != null && borrar){
	    	this.borrar(encontrado);
	    	} //lo borra
	    
	    return encontrado;
	}
	
	/**
	 * busca el dato @dato y si lo encuentra lo devuelve(recursivo).
	 *
	 * @param dato El dato a buscar.
	 * @return el dato si existe. NULL en otro caso
	*/
	private T obtenerEquivalente(T dato) {
	    Arbol<T> aux = null;
	    T encontrado = null;
	    
	    if(!vacio()) {
	        if(this.datoRaiz.equals(dato)) {
	            encontrado = datoRaiz;
	        } else {
	            if(dato.compareTo(this.datoRaiz) < 0) {
	                aux = getHijoIzq();
	            } else {
	                aux = getHijoDer();
	            }
	            
	            if(aux != null) {
	                encontrado = aux.obtenerEquivalente(dato);
	            }
	        }
	    }
	    

	    
	    return encontrado;
	}

	/**
	 * muestra por pantalla los elementos InOrden 
	 * usando el toString()
	 */
	public void inOrdenMostrar() {
	    if(!vacio()) {
	        if((getHijoIzq()) != null) {
	            this.getHijoIzq().inOrdenMostrar();
	        }    
	      
	        System.out.println(this.datoRaiz.toString());
	        
	        if((getHijoDer()) != null){
	            this.getHijoDer().inOrdenMostrar();
	        }    
	    }
	}
}

