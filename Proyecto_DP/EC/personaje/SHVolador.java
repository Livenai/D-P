package personaje;

import java.util.LinkedList;

import contenedores.Grafo;
import mapa.Mapa;

public class SHVolador extends SuperHeroe {

	public SHVolador(String nombre, char id, int dondeEstoy, int turno) {
		super(nombre, id, dondeEstoy, turno);
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SHVolador [Nombre=" + getNombre() + ", ID=" + getID() + ", Sala=" + getDondeEstoy()
				+ "]";
	}

	@Override
	public void calcularRuta() {
		//camino mascorto del origen a TheDailyPlanet
		LinkedList<Character> ruta = new LinkedList<Character>();

		Mapa uni = Mapa.obtenerUnico();
		int h = uni.getAncho();
		Grafo g = uni.getGrafo();
		
		int actual = getDondeEstoy();
		int destino = uni.getSalaHombrePuerta().getID();
		int siguiente = g.siguiente(actual, destino);
		
		//iteramoshasta que lleguemos al destino
		while(actual != destino){
			//anotamos el movimiento en la ruta
			int diferencia = siguiente-actual;
			if(diferencia == h){//S
				ruta.addLast('S');
			}else if(diferencia == -h){//N
				ruta.addLast('N');
			}else if(diferencia == 1){//E
				ruta.addLast('E');
			}else if(diferencia == -1){//W
				ruta.addLast('W');
			}else {
				System.err.println("[:(] Algo salio mal al calcularla ruta del SHVolador");
			}	
			
			//actualizamos los parametros
			actual = siguiente;
			siguiente = g.siguiente(actual, destino);
		}

		
		
		
		
		
		System.out.println("[SHV] Ruta de " + this.getID() + " : " + ruta.toString());
		this.establecerRuta(ruta);
	}
	
	

}
