package mapa;

//TODO -> MazeGen terminar
/*TODO -> el generar atajos es igual que el de el año pasado (orden por cada sala N,S,O,E)
usar para los atajos el si(camino minimo entre dos salas == 3)then -> no tirar el muro que se genera elmapa*/
import java.util.LinkedList;

import org.hamcrest.core.Is;

import contenedores.Grafo;
import salas.Sala;

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
		
		//id de las salas
		int salaA;
		int salaB;
		
		//direccion a la que se encuentra el
		//muro respecto de la sala
		char dirRespectoDeA;
		char dirRespectoDeB;
		
		public Pared(int a, int b, char dirA, char dirB) {
			salaA = a;
			salaB = b;
			dirRespectoDeA = dirA;
			dirRespectoDeB = dirB;
		}

	}
	
	//atributos-------------------
	private LinkedList<Pared> listaParedes;
	Grafo grafoRet;

	
	
	//metodos----------------------

	
	/**
	 * Ctor
	 */
	public MazeGen() {}
	
	
	/**
	 * Metodo que genera el laberinto y lo aplica al mapa actual y unico
	 * realiza los cambios necesarios para generar el laberinto en el mapa, 
	 * EXCEPTO EN EL GRAFO -> el cual es devolvido y debe asignarse exteriormente.
	 * @return
	 */
	public Grafo generarMapaAleatorio(){
		grafoRet = new Grafo();
		//creamos los nodos del grafo que representan nuestras salas
		for (int i = 0; i < (Mapa.obtenerUnico().getAlto()*Mapa.obtenerUnico().getAncho()); i++) {
			grafoRet.nuevoNodo(i);
		}
		
		generarParedes();
		while(!listaParedes.isEmpty()){
			Pared candidata = seleccionarPared();//coge y borra de la lista
			tirarPared(candidata);//dentro va el propagarMarca()
			
			
		}
		return grafoRet;
	}

	
	/**
	 * metodo que realiza los actos necesarios para determinar
	 *  que una pared "ha sido tirada".
	 *  1- cambiar los boolean de las salas.
	 *  2- definir el camino en el grafo.
	 *  
	 *  Despues propaga la marca.
	 * @param candidata 
	 */
	private void tirarPared(Pared candidata) {
		Mapa uni = Mapa.obtenerUnico();
		Sala A = uni.getSalaConID(candidata.salaA);
		Sala B = uni.getSalaConID(candidata.salaB);
		
		if(A.getMarca() != B.getMarca()){//si las marcas son distintas
			//tiramos la pared
			//1-> cambiando los bool de las salas
			if(candidata.dirRespectoDeA == 'N'){
				A.setN(false);
				B.setS(false);
			} else
			if(candidata.dirRespectoDeA == 'S'){
				A.setS(false);
				B.setN(false);
			} else
			if(candidata.dirRespectoDeA == 'E'){
				A.setE(false);
				B.setO(false);
			} else
			if(candidata.dirRespectoDeA == 'O'){
				A.setO(false);
				B.setE(false);
			}
			
			//2-> creando el camino en el grafo
			grafoRet.nuevoArco(candidata.salaA, candidata.salaB, 1);
		}
		propagarMarca();
	}

	/**
	 * Metodo que propaga la marca de las salas quee esten conectadas.
	 * despues de utilizar este metodo todas las salas
	 * que esten conectadas quedan con la misma marca
	 */
	private void propagarMarca() {
		int idSala = 0;
		Mapa uni = Mapa.obtenerUnico();
		int H = Mapa.obtenerUnico().getAncho();
		int A = Mapa.obtenerUnico().getAlto();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < A; j++) {
				//propagamos la marca de mayor peso
				Sala sA = uni.getSalaConID(idSala);
				
				//norte
				if(sA.isN() == false){ //suponemos que un muro no puede estar a "false" si es un muro exterior
					Sala B = uni.getSalaConID(idSala-H);
					if(sA.getMarca() < B.getMarca())
					Mapa.obtenerUnico().getSalaConID(idSala).setMarca(B.getMarca());
				}
				
				//sur
				if(sA.isS() == false){ 
					Sala B = uni.getSalaConID(idSala+H);
					if(sA.getMarca() < B.getMarca())
					Mapa.obtenerUnico().getSalaConID(idSala).setMarca(B.getMarca());
				}
				
				//este
				if(sA.isE() == false){ 
					Sala B = uni.getSalaConID(idSala+1);
					if(sA.getMarca() < B.getMarca())
					Mapa.obtenerUnico().getSalaConID(idSala).setMarca(B.getMarca());
				}
				
				//oeste
				if(sA.isO() == false){ 
					Sala B = uni.getSalaConID(idSala-1);
					if(sA.getMarca() < B.getMarca())
					Mapa.obtenerUnico().getSalaConID(idSala).setMarca(B.getMarca());
				}

				idSala++;
			}
		}		
	}

	/**
	 * selecciona un nº aleatorio con gen aleatorios, devuelve 
	 * y borra la pared de la lista.
	 */
	private Pared seleccionarPared() {
		int i = 0;
		i = GenAleatorios.generarNumero(listaParedes.size());
		return listaParedes.remove(i);
	}

	
	
	/**
	 * Metodo que rellena listaParedes con las distintas paredes 
	 * del mapa en su entorno inicial, es decir, cuando todas las
	 *  paredes existen y aun no se ha tirado ninguna.
	 */
	private void generarParedes() {
		int idSala = 0;
		int H = Mapa.obtenerUnico().getAncho();
		int A = Mapa.obtenerUnico().getAlto();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < A; j++) {
				//añadir la pared en orden de N,E,S,O
				if((idSala / H) > 0){listaParedes.add(new Pared(idSala,idSala-H,'N','S'));}//N
				if((idSala % H) < H-1){listaParedes.add(new Pared(idSala,idSala+1,'E','O'));}//E
				if((idSala / H) < A-1){listaParedes.add(new Pared(idSala,idSala+H,'S','N'));}//S
				if((idSala % H) > 0){listaParedes.add(new Pared(idSala,idSala-1,'O','E'));}//O

				idSala++;
			}
		}		
	}

}
