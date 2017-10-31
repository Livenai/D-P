package personaje;

/**
 * Clase generica de los personajes del juego.
 * Cualquier personaje (heroe villano u otro) 
 * pertenecera a esta clase (herencia).
 * @author CARLOS MUÑOZ ZAPATA 
 * GIIIC
 */
public abstract class Personaje implements Comparable <Personaje>{
	//Atributos que todos los PJ tienen
	private String nombre;
	private char ID;
	private int dondeEstoy; //ID de la sala donde se encuentra
	
	//---------
	
	
	/**
	 * Ctor patrametrizado
	 * @param nombre_
	 * @param ID_
	 */
	public Personaje(String nombre_, char ID_, int enQueSalaEmpieza) {
		nombre = nombre_;
		ID = ID_;
		dondeEstoy = enQueSalaEmpieza;
		
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
