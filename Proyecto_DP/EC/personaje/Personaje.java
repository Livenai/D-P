package personaje;

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
	
}
