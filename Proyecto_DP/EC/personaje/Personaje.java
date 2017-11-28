package personaje;

import java.util.LinkedList;

/**
 * Clase generica de los personajes del juego.
 * Cualquier personaje (heroe villano u otro) 
 * pertenecera a esta clase (herencia).
 * @author CARLOS MU�OZ ZAPATA 
 * GIIIC
 */
public abstract class Personaje implements Comparable <Personaje>{
	//Atributos que todos los PJ tienen
	private String nombre;
	private char ID;
	private int dondeEstoy; //ID de la sala donde se encuentra
	/**
	 *  turno en el que se encuentra el pj y es donde deberia realizar la siguiente accion
	 */
	private int turnoActual;
	
	/**
	 * Ruta que el personaje seguira.
	 * se define como la direccion a la que debe moverse en el turno indicado.
	 * N - Norte
	 * S - Sur
	 * E - Este
	 * O - Oeste
	 */
	private LinkedList<Character> ruta;
	
	//TODO las rutas van en linked list. para las rutas ciclicas usar un paso e insertarlo al final :p
	//TODO el moverse no mira si hay paredes, eso lo mira el calcular ruta. El mover se hace ciegamente (no hay porque usar el Dir.E de los profes)
	
	//----------
	
	
	/**
	 * Ctor patrametrizado
	 * @param nombre_
	 * @param ID_
	 */
	public Personaje(String nombre_, char ID_, int enQueSalaEmpieza, int turnoEnElQueEmpiezan) {
		nombre = nombre_;
		ID = ID_;
		dondeEstoy = enQueSalaEmpieza;
		turnoActual = turnoEnElQueEmpiezan;
		
		System.out.println("[!] Personaje " + nombre + " [" + ID + "] creado.");
	}
	
	
	//especiales
	@Override
	public int compareTo(Personaje o) {
		//TODO: terminar compareTo ( aun no se necesita )
		int ret = 0;
		return ret;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public char getID() {
		return ID;
	}




	public void setID(char iD) {
		ID = iD;
	}




	public int getDondeEstoy() {
		return dondeEstoy;
	}


	public void setDondeEstoy(int dondeEstoy) {
		this.dondeEstoy = dondeEstoy;
	}


	/**
	 * accion que realiza el personaje con la sala.
	 * cada tipo de personaje tiene su actuacion.
	 */
	public abstract void interactuarSala();
	
	
	/**
	 * accion que el personaje hara al estar en contacto 
	 * con ElHombrePuerta
	 * cada tipo de personaje tiene su actuacion.
	 */
	public abstract void interactuarPuerta();


	/**
	 * Metodo que procesa el turno de un pj,
	 *  solo si el pj no ha sido procesado antes.
	 */
	public void procesarTurno(){
		//se procesa el turno en el siguiente orden
		interactuarPuerta();
		mover();
		interactuarSala();
		interactuarConOtrosPJ();
	}
	
	
	/**
	 * Metodo que realiza el movimiento propio del persinaje.
	 * Este movimiento dependera del tipo de personaje. 
	 * (y de las rutas que este siga)
	 */
	public void mover(){
		System.out.println("interactuarConOtrosPJ Villano -> " + toString());	
		//TODO -> el mover de los pj (leer la ruta)
	}
	
	
	/**
	 * Metodo que realiza las acciones pertinentes que cada personaje 
	 * realiza al interactuar con otro personaje de la sala
	 */
	public abstract void interactuarConOtrosPJ();


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
	
	
	/**
	 * Metodo que calcula la ruta del personaje.
	 *  La ruta obtenida depende del tipo de personaje que sea.
	 */
	public abstract void calcularRuta();
	
	
	public void establecerRuta(LinkedList<Character> ruta_){
		ruta = ruta_;
	}

	
	

	
}
