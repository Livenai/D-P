package personaje;

import java.util.LinkedList;

import armas.Arma;
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
	
	
	private int turnoEnElQueEmpieza;
	
	
	/**
	 * Ruta que el personaje seguira.
	 * se define como la direccion a la que debe moverse en el turno indicado.
	 * N - Norte
	 * S - Sur
	 * E - Este
	 * O - Oeste
	 */
	private LinkedList<Character> ruta;
	
	
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
		turnoEnElQueEmpieza = turnoEnElQueEmpiezan;
		//la ruta no se calcula aqui pues aun no habriasido creado el laberinto y todo el mapa serian paredes.
		//se calcula despues del genMaze
		
		System.out.println("[!] Personaje " + nombre + " [" + ID + "] creado.");
	}
	
	
	//especiales
	@Override
	public int compareTo(Personaje o) {
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
		if(this.turnoDelPJ <= Mapa.obtenerUnico().getTurno()){
			
			interactuarPuerta();
			movido = mover();
			if(dondeEstoy != 1111){//control de pj en sala del Teseracto
				interactuarSala();
				interactuarConOtrosPJ();
			}
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
		} else {
			//y si se ha abierto el portal, nos colamos :P
			if(Mapa.obtenerUnico().getEHP().isEstado() == true){
				//hemos ganado y se termina la simulacion ~ (el turno terminará de procesarse)
				
				System.out.println("[WIN] ¡¡¡El personaje " + this.getNombre() + "[" + this.getID() + "] ha cruzado el portal!!!");
				Mapa.obtenerUnico().ganar(this);
				ret = true;
			}	
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


	/**
	 *  Metodo que registra en el log el caminos del pj siguiendo
	 *  la siguiente estructura:  
	 *  path:marca del personaje:secuencia de orientaciones
	 */
	public void registrarRuta() {
		log.log.write("(path:" + ID + ":");
		for (int i = 0; i < ruta.size(); i++) {
			log.log.write(" " + ruta.get(i));
		}
		log.log.write(")\n");
	}

	/**
	 * Metodo que registra al pj en el log con la siguiente estructura
	 * (tipo de personaje:marca:id sala actual:turno:armas:villanos capturados (sólo superhéores))
	 */
	public void registrarPJ(boolean parentesis){
		if(parentesis)log.log.write("(");
		
		if(this instanceof SHFisico){
			log.log.write("shphysical");
		}else if(this instanceof SHVolador){
			log.log.write("shflight");
		}else if(this instanceof SHExtrasensorial){
			log.log.write("shextrasensorial");
		} else if(this instanceof Villano){
			log.log.write("villain");
		}
	
		//control de turnos
		if(turnoDelPJ == turnoEnElQueEmpieza){
			log.log.write(":" + ID + ":" + dondeEstoy + ":" + turnoEnElQueEmpieza + ":" + armasDelPJToString());
		} else {
			log.log.write(":" + ID + ":" + dondeEstoy + ":" + (turnoDelPJ-1) + ":" + armasDelPJToString());
		}
		
		if(this instanceof SuperHeroe){
			String v = ((SuperHeroe)this).VillanosCapturadosToString();
			if(!v.isEmpty())
			log.log.write(":" + v);
		}
		
		if(parentesis)
			log.log.write(")\n");

	}


	
	/**
	 * Metodo que devuelve el set de armas del pj como un String.
	 * @patron
	 * SuperHeroe -> (arma,pow)(arma,pow)...
	 * Villano -> (arma,pow)
	 * @return String
	 */
	private String armasDelPJToString() {
		String ret = "";
		
		if(this instanceof Villano){
			//controlamos que tenga arma
			if(((Villano)this).tieneArma()){
				ret = ((Villano)this).getArma().toString();
			}
		} else if (this instanceof SuperHeroe){
			//controlamos que tenga armas
			if(((SuperHeroe)this).tieneArma()){
				LinkedList<Arma> aux = ((SuperHeroe)this).getArmasDelPJ().inOrden();
				for (int i = 0; i < aux.size(); i++) {
					ret = ret+aux.get(i).toString();
				}	
			}
		}
		return ret;
	}
	

	
}
