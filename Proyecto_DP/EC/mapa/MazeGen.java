package mapa;

//TODO -> MazeGen terminar
/*TODO -> el generar atajos es igual que el de el año pasado (orden por cada sala N,S,O,E)
usar para los atajos el si(camino minimo entre dos salas == 3)then -> no tirar el muro que se genera elmapa*/
import java.util.LinkedList;
import contenedores.Grafo;

/**
 * Esta clase se encarga de generar el laberinto del mapa y el
 * grafo equivalente.
 * @author CARLOS MUÑOZ ZAPATA
 *
 */
public class MazeGen {
	/**
	 * Clase que representa la existencia de un muro entre dos 
	 * salas del mapa. Se utilizar solo en la clase GenMaze como elementos auxiliares.
	 * @author CARLOS MUÑOZ ZAPATA
	 *
	 */
	public class Pared {

		//TODO -> DIOS, arreglar GRAFO
		public Pared() {
			// TODO Auto-generated constructor stub
		}

	}
	
	//atributos-------------------
	private LinkedList<Pared> listaParedes;

	
	
	//metodos----------------------

	public MazeGen() {
		// TODO Auto-generated constructor stub
		Comparator a; //TODO -> como se usa?
	}
	
	public Grafo generarMapaAleatorio(){
		Grafo grafoRet = new Grafo();
		generarParedes();
		while(!listaParedes.isEmpty()){
			Pared candidata = seleccionarPared();//y la borramos de la lista
			tirarPared(candidata);//dentro va el propagarMarca()
			
			
		}
		return grafoRet;
	}

	
	/**
	 * metodo que realiza los actos necesarios para determinar
	 *  que una pared "ha sido tirada".
	 *  1- cambiar los boolean de las salas.
	 *  2- definir el camino en el grafo
	 *  
	 *  despues propaga la marca
	 * @param candidata 
	 */
	private void tirarPared(Pared candidata) {
		
		if(si las marcas son distintas){
			tirar
		}
		
		//TODO -> tener un metodo en mapa que te devuelva la sala con el id y 
		//otro con las coor x,y; asi encapsulamos (intentar no usar los getter)
		
		propagarMarca();
	}

	/**
	 * Metodo que propaga la marca de las salas quee sten conectadas.
	 * despues de utilizar este metodo todas las salas
	 * que esten conectadas quedan con la misma marca
	 */
	private void propagarMarca() {
		// TODO marca
		
	}

	/**
	 * selecciona un nº aleatorio con gen aleatorios y devolver y borrar
	 */
	private Pared seleccionarPared() {
		int i = 0;
		i = GenAleatorios.generarNumero(listaParedes.size());
		
		return listaParedes.remove(i);
	}

	private void generarParedes() {
		int idSala = 0;
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				//añadir la pared en orden de N,E,S,O
				idSala++;
			}
		}		
	}

}
