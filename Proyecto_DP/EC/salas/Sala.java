package salas;
import armas.Arma;
import contenedores.Lista;
import personaje.Personaje;
import personaje.Villano;

/**
 * Clase que representa acada una de las salas 
 * del mapa en un contexto general.
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 *
 */
public class Sala {

	//Atributos
	private int ID; // id de la sala
	private Lista<Arma> ArmasDentro; // armas en el interior de la sala (ordenadas por poder)
	private Lista<Personaje> PJDentro; //Personajes en el interior. (ordenados por llegada)
	
	// paredes (true -> hay pared)
	private boolean N; //arriba
	private boolean S; //abajo
	private boolean E; //derecha
	private boolean O; //izquierda
	

	private int marca; //marcapara la creacion del laberinto
	
	//---------
	
	/**
	 * Ctor  parametrizado
	 * @param ID_
	 */
	public Sala(int ID_){
		ID = ID_;
		ArmasDentro = new Lista<Arma>();
		PJDentro = new Lista<Personaje>();
		N = true;
		S = true;
		E = true;
		O = true;
		marca = ID_;
		
		System.out.println("[!] Sala " + ID + " creada.");
	}
	
	/**
	 * inserta un arma en la sala, manteniendolas ordenadas por poder(+ a -)
	 * NO usa el compareTo
	 * @param arma
	 */
	public void insertarArmaEnOrdenDePoder(Arma arma) {
		int donde = 0; // dice donde insertamos el arma para que quede ordenada
		
		if(ArmasDentro.estaVacia()){ //si no hay elementos
			ArmasDentro.insertarPrimero(arma);
		} else{ 
			//si ya hay elementos
			while(donde < ArmasDentro.obtenerTam() && arma.getPoder() < ArmasDentro.obtenerElemento(donde).getPoder()){
				//buscando el lugar perfecto ~
				donde++;
			}
			
			//ahora insertamos
			ArmasDentro.insertarElemento(arma, donde);
		}

	}

	public Lista<Arma> getArmasDentro() {
		return ArmasDentro;
	}

	public Lista<Personaje> getPJDentro() {
		return PJDentro;
	}

	
	/**
	 * muestra las armas dentro 
	 */
	public void mostrarArmas() {
		System.out.print("Sala " + ID + ", armas: ");
		for (int i = 0; i < ArmasDentro.obtenerTam(); i++) {
			ArmasDentro.obtenerElemento(i).mostrarArma();
		}
		System.out.println();
		
	}

	/**
	 * inserta un personaje en la ultima posicion (cola)
	 * de esta manera se conserva el orden de llegada del PJ a la sala
	 * @param pj
	 */
	public void insertarPJ(Personaje pj) {
		PJDentro.insertarUltimo(pj);
	}

	
	/**
	 * Método que devuelve el mejor arma que se encuentre en la sala tirada.
	 * @return Arma si existe al menos una; null en otr caso
	 */
	public Arma obtenerMejorArmaDeLaSala() {
		return ArmasDentro.borrarPrimero();
	}

	
	
	public int getMarca() {
		return marca;
	}

	public void setMarca(int marca) {
		this.marca = marca;
	}

	/**
	 * @return the n
	 */
	public boolean isN() {
		return N;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(boolean n) {
		N = n;
	}

	/**
	 * @return the s
	 */
	public boolean isS() {
		return S;
	}

	/**
	 * @param s the s to set
	 */
	public void setS(boolean s) {
		S = s;
	}

	/**
	 * @return the e
	 */
	public boolean isE() {
		return E;
	}

	/**
	 * @param e the e to set
	 */
	public void setE(boolean e) {
		E = e;
	}

	/**
	 * @return the o
	 */
	public boolean isO() {
		return O;
	}

	/**
	 * @param o the o to set
	 */
	public void setO(boolean o) {
		O = o;
	}

	public int getID() {
		return this.ID;
	}
	
	@Override
	public String toString(){
		return ("Sala[" + this.ID + "]. pj´s: " + this.PJDentro.obtenerTam() + " ,armas: " + this.ArmasDentro.obtenerTam() + ".");
		
	}

	/**
	 * Metodo que procesa el turno de todos los personajes dentro de la sala.
	 * Mo tiene en cuenta si el pj ha sido procesado antes o no.
	 */
	public void procesarTurnos() {
		for (int i = 0; i < PJDentro.obtenerTam(); i++) {
			PJDentro.obtenerElemento(i).procesarTurno();
		}
		
	}

	/**
	 * Devuelve el primer Villano que encuentra en la sala.
	 * TRUE -> borra al Villano de la sala
	 * FALSE -> Solo devuelve al villano
	 * @return Villano, null si no hay
	 */
	public Villano sacarPrimerVillano(boolean borrar) {
		Personaje ret = null;
		boolean encontrado = false;
		int i;
		for (i = 0; encontrado && i < PJDentro.obtenerTam(); i++) {
			ret = PJDentro.borrarElemento(i);
			if(ret instanceof Villano){
				encontrado = true;
			} else {
				PJDentro.insertarElemento(ret, i);
			}
		}
		if(borrar){
			PJDentro.insertarElemento(ret, i);
		}
		
		return (Villano) ret;
	}
	
	
	
}
