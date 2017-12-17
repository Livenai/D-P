package personaje;

import java.util.LinkedList;

import contenedores.Booleano;
import mapa.Mapa;

public class SHFisico extends SuperHeroe {

	public SHFisico(String nombre, char id, int dondeEstoy, int turno) {
		super(nombre, id, dondeEstoy, turno);
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SHFisico [Nombre=" + getNombre() + ", ID=" + getID() + ", Sala=" + getDondeEstoy()
				+ "]";
	}

	@Override
	public void calcularRuta() {
		//recorrido en profundidad sin entrarencallejones sin salida.
		//Escogera siempre la alternativa con menor identificacor
		Mapa uni = Mapa.obtenerUnico();
		int h = uni.getAncho();
		LinkedList<Character> ruta = new LinkedList<Character>();
		LinkedList<Integer> camino = new LinkedList<Integer>();

		//obtenemos el camino apropiado, explicado en el javaDoc del metodo
		LinkedList<LinkedList<Integer>> caminoConID = new LinkedList<LinkedList<Integer>>();
		Booleano b = new Booleano();
		b.b= false;
		caminoConID = obtenerRutaR(this.getDondeEstoy(), uni.getSalaHombrePuerta().getID(), camino, caminoConID, b);
		
		
		//y ahora tenemos que crear la ruta en formato {N,S,E,W}
		for (int donde,hacia,i = 1; i < caminoConID.getFirst().size(); i++) {
			donde = caminoConID.getFirst().get(i-1);
			hacia = caminoConID.getFirst().get(i);
			
			int diferencia = hacia-donde;
			if(diferencia == h){//S
				ruta.addLast('S');
			}else if(diferencia == -h){//N
				ruta.addLast('N');
			}else if(diferencia == 1){//E
				ruta.addLast('E');
			}else if(diferencia == -1){//W
				ruta.addLast('W');
			}else {
				System.err.println("[:(] Algo salio mal al calcularla ruta del SHFisico");
			}			
		}

		System.out.println("[SHF] Ruta de " + this.getID() + " : " + ruta.toString());
		this.establecerRuta(ruta);
	}
	
	/**
	 * Metodo auxiliar recursivo que realiza el proceso de obtencion del camino propio del SHFisico
	 * Este camino consiste en ir de la sala origen a la sala TheDailyPlanet por un camino directo (sin meterse encallejones sin salida)
	 * el cual coja siempre como alternativa la direccion con el ID mas bajo.
	 * @param actual donde estamos
	 * @param destino el nodo objetivo final del camino
	 * @param encontrado auxiliar para parar al encontrar el primer camino. poner siempre a false
	 * @return LinkedList de LinkedList con Integer
	 */
	private LinkedList<LinkedList<Integer>> obtenerRutaR(int actual, int destino, LinkedList<Integer> camino, LinkedList<LinkedList<Integer>> ret, Booleano encontrado) {
		Mapa uni = Mapa.obtenerUnico();
		int opcionActual = 0;
		char[] posiblesOpciones = {'N','W','E','S'}; //ordenadas por ID supuesto mas bajo
		
		//mientras haya opciones
		while(opcionActual < 4 && !encontrado.b){
			//System.err.println("actual: " + actual + "  Opcion: " + posiblesOpciones[opcionActual]);
			//¿es posible la opcion?
			if(!uni.getSalaConID(actual).hayMuroEn(posiblesOpciones[opcionActual])
					&& !camino.contains(actual)){//si no hay muro Y si no hemos pasado ya por hai
				//anotar opcion
				camino.addLast(actual);
				//¿es solucion final?
				if(actual == destino){
					//creaos una copia para no estropearlo despues
					LinkedList<Integer> copia = new LinkedList<Integer>();
					copia.addAll(camino);
					ret.addLast(copia);
					opcionActual = 5;
					encontrado.b = true;
					//System.out.println("camino-> " + camino.toString());

				}
				//si no es solucion final, continuamos
				else {
					switch(posiblesOpciones[opcionActual]){
					case 'N':
						ret = obtenerRutaR(actual-uni.getAncho(), destino, camino, ret, encontrado);
						break;
					case 'S':
						ret = obtenerRutaR(actual+uni.getAncho(), destino, camino, ret, encontrado);
						break;
					case 'E':
						ret = obtenerRutaR(actual+1, destino, camino, ret, encontrado);
						break;
					case 'W':
						ret = obtenerRutaR(actual-1, destino, camino, ret, encontrado);
						break;
					}
				}
				//una vez terminado, borramos la anotacion
				camino.removeLast();
			}
			//cambiamos de opcion(si no quedan opciones hay que salir del while)
			opcionActual++;
		}
		
		return ret;
	}
}
