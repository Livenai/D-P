package salas;
import armas.Arma;
import contenedores.Lista;
import personaje.Personaje;

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
	
	//---------
	
	/**
	 * Ctor  parametrizado
	 * @param ID_
	 */
	public Sala(int ID_){
		ID = ID_;
		ArmasDentro = new Lista<Arma>();
		PJDentro = new Lista<Personaje>();
		N = false;
		S = false;
		E = false;
		O = false;
		
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
	
}
