package personaje;

import java.util.LinkedList;

import mapa.Mapa;

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
	private int turnoDelPJ;
	
	
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
		turnoDelPJ = turnoEnElQueEmpiezan;
		calcularRuta();
		
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
	 *  @return dicesi se ha movido el pj. True=se ha movido
	 */
	public boolean procesarTurno(){
		//se procesa el turno en el siguiente orden
		//solo si te toca y si la simulación continua
		boolean movido = false;
		if(!Mapa.obtenerUnico().FINSIMULACION &&
				this.turnoDelPJ <= Mapa.obtenerUnico().getTurno()){
			interactuarPuerta();
			movido = mover();
			interactuarSala();
			interactuarConOtrosPJ();
			turnoDelPJ++;
		}
		return movido;
	}
	
	
	/**
	 * Metodo que realiza el movimiento propio del personaje.
	 * Este movimiento dependera del tipo de personaje (y de las rutas que este siga). 
	 * El PJ no se mueve si esta en TheDailyPlanet
	 * 
	 * (se mueve ciegamente, fiel a la ruta)
	 * @return true -> si el pj se ha movido
	 */
	public boolean mover(){
		boolean ret = false;
		if(this.dondeEstoy != Mapa.obtenerUnico().getSalaHombrePuerta().getID()){
			char dir = ruta.removeFirst();
	
			Mapa.obtenerUnico().moverPJconDir(this, this.dondeEstoy, dir);
			
			ruta.addLast(dir);
			ret = true;
		}
		
		return ret;
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
	
	
	
	


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personaje other = (Personaje) obj;
		if (ID != other.ID)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	/**
	 * Metodo que calcula la ruta del personaje.
	 *  La ruta obtenida depende del tipo de personaje que sea.
	 */
	public abstract void calcularRuta();
	
	
	public void establecerRuta(LinkedList<Character> ruta_){
		ruta = ruta_;
	}

	
	

	
}
