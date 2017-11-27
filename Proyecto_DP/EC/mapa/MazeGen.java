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

		@Override
		public String toString() {
			return ("("+ salaA + "," + salaB + ")["+ dirRespectoDeA + "," + dirRespectoDeB + "]\n");
		}
	}
	
	//atributos-------------------
	private LinkedList<Pared> listaParedes;
	Grafo grafoRet;

	
	
	//metodos----------------------

	
	/**
	 * Ctor
	 */
	public MazeGen() {
		grafoRet = new Grafo();
		listaParedes = new LinkedList<Pared>();
	}
	
	
	/**
	 * Metodo que genera el laberinto y lo aplica al mapa actual y unico
	 * realiza los cambios necesarios para generar el laberinto en el mapa, 
	 * EXCEPTO EN EL GRAFO -> el cual es devolvido y debe asignarse exteriormente.
	 * @return
	 */
	public void generarMapaAleatorio(){
		System.out.println("Comenzando construccion del laberinto...");
		
		//creamos los nodos del grafo que representan nuestras salas
		for (int i = 0; i < (Mapa.obtenerUnico().getAlto()*Mapa.obtenerUnico().getAncho()); i++) {
			grafoRet.nuevoNodo(i);
		}
		
		generarParedes();
		while(!listaParedes.isEmpty()){
			Pared candidata = seleccionarPared();//coge y borra de la lista
			tirarPared(candidata,false);//dentro va el propagarMarca()
		}
		//completamos el grafo
		grafoRet.floyd();
		grafoRet.warshall();
		Mapa.obtenerUnico().setGrafo(grafoRet);
		
		
		Mapa.obtenerUnico().mostrarMapa();
		
		//ahora hacemos los atajos

		tirarAtajos();
		
		//completamos el grafo de nuevo con los ultimos cambios de los atajos
		grafoRet.floyd();
		grafoRet.warshall();
		Mapa.obtenerUnico().setGrafo(grafoRet);
		
		
		System.out.println("Laberinto terminado.");
	}

	/**
	 * Metodo que crea los atajos del mapa.
	 * Tira tantos muros como el 5% de las salas del mapa.
	 */
	private void tirarAtajos() {
		System.out.println("-> atajos");
		Mapa uni = Mapa.obtenerUnico();
		int H = Mapa.obtenerUnico().getAncho();
		int A = Mapa.obtenerUnico().getAlto();
		int cincoPorciento = obtenerCincoPorciento(H,A);
		
		
		//para el 5% de las salas:
		for (int i = 0; i < cincoPorciento; i++) {
			
		
			//primero elegimos una sala aleatoria
			int IDsala = GenAleatorios.generarNumero(H*A);
			Sala salaA = uni.getSalaConID(IDsala);
			int idA = salaA.getID();
			
			//Se busca un vecino no accesible
			Sala salaB = null;
			char direccion = '.';
			char contraDireccion = '.';
			
			if((idA / H) > 0 && uni.hayMuroEntre(idA,idA-H)&& !grafoRet.disIgualATres(idA, idA-H)){//norte
				salaB = uni.getSalaConID(idA-H);
				direccion = 'N';
				contraDireccion = 'S';
				
			}else	if((idA / H) < A-1 && uni.hayMuroEntre(idA,idA+H)&& !grafoRet.disIgualATres(idA, idA+H)){//sur
				salaB = uni.getSalaConID(idA+H);
				direccion = 'S';
				contraDireccion = 'N';
				
			}else	if((idA % H) > 0 && uni.hayMuroEntre(idA,idA-1)&& !grafoRet.disIgualATres(idA, idA-1)){//oeste
				salaB = uni.getSalaConID(idA-1);
				direccion = 'O';
				contraDireccion = 'E';
				
			}else	if((idA % H) < H-1 && uni.hayMuroEntre(idA,idA+1)&& !grafoRet.disIgualATres(idA, idA+1)){//este
				salaB = uni.getSalaConID(idA+1);
				direccion = 'E';
				contraDireccion = 'O';
			} else {
				i--;//si no se tira ninguna pared esta ronda no se cuenta
			}
			
			//tiramos la pared
			if(salaB != null){ //si se ha encontrado al vecino inaccesible
				tirarPared(new Pared(salaA.getID(),salaB.getID(),direccion,contraDireccion),true);
				//actualizamos el grafo despues de tirar un muro para la siguiente iteraccion
				grafoRet.floyd();
				grafoRet.warshall();
				Mapa.obtenerUnico().setGrafo(grafoRet);
			}
			
			
			
		}//for
		
	}


	/**
	 * Metodo que devuelve el 5% del numero total de salas.
	 * Lo devuelve en un INT asique redondea segun su criterio.
	 * @param a 
	 * @param h 
	 * @return int
	 */
	private int obtenerCincoPorciento(int h, int a) {
		double ret = (h*a)*0.05;
		return (int) ret;
	}


	/**
	 * metodo que realiza los actos necesarios para determinar
	 *  que una pared "ha sido tirada".
	 *  1- cambiar los boolean de las salas.
	 *  2- definir el camino en el grafo.
	 *  
	 *  Tiene en cuenta y propaga la marca si ignorarMarca == True.
	 * @param candidata 
	 * @param ignorarMarca si== TRUE se ignora el proceso de comprobacion y expansion de marcas
	 */
	private void tirarPared(Pared candidata, boolean ignorarMarca) {
		Mapa uni = Mapa.obtenerUnico();
		Sala A = uni.getSalaConID(candidata.salaA);
		Sala B = uni.getSalaConID(candidata.salaB);
		
		if(ignorarMarca || A.getMarca() != B.getMarca()){//si las marcas son distintas
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
			grafoRet.nuevoArco(candidata.salaB, candidata.salaA, 1);
		}
		if(!ignorarMarca)propagarMarca(A.getMarca(),B.getMarca());
	}

	/**
	 * Metodo que propaga la marca de las salas quee esten conectadas.
	 * despues de utilizar este metodo todas las salas
	 * que esten conectadas quedan con la misma marca
	 * @param mB marca de la sala A lacual sustituira a las de B
	 * @param mA Marca de B para encontrar todas las salas con esta marca
	 */
	private void propagarMarca(int mA, int mB) {
		int idSala = 0;
		Mapa uni = Mapa.obtenerUnico();
		int H = Mapa.obtenerUnico().getAncho();
		int A = Mapa.obtenerUnico().getAlto();
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < H; j++) {
				//propagamos la marca de mayor peso
				Sala sA = uni.getSalaConID(idSala);
				
				
				if(sA.getMarca() == mB){//para todas las salas del mapa con la marca B , le ponemos la marca A
					sA.setMarca(mA);
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
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < H; j++) {
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
