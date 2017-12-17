package personaje;

import java.util.LinkedList;

import mapa.Mapa;

public class SHExtrasensorial extends SuperHeroe {

	public SHExtrasensorial(String nombre, char id, int dondeEstoy, int turno) {
		super(nombre, id, dondeEstoy, turno);
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SHExtrasensorial [Nombre=" + getNombre() + ", ID=" + getID() + ", Sala=" + getDondeEstoy()
				+ "]";
	}

	@Override
	public void calcularRuta() {
		//regla de la mano derecha
		LinkedList<Character> ruta = new LinkedList<Character>();
		Mapa uni = Mapa.obtenerUnico();
		
		//primero necesitamos saber que direccion es nuestra derecha, nuestro frente
		char frente = 'S';
		char derecha = 'W';
		int candidata = getDondeEstoy()+uni.getAncho();//a la que puede que nos movamos
		int dondeEstoy = uni.getSalaConID(getDondeEstoy()).getID();//la sala actual
		
		//ahora iteramos hasta llegar a la sala TheDailyPlanet
		while(!(dondeEstoy == uni.getSalaHombrePuerta().getID())){
			//comprobamos nuestra derecha. ¿hay muro?
			if(uni.getSalaConID(dondeEstoy).hayMuroEn(derecha)){
				//hay muro, por lo que ahora comprobamos el frente
				if(uni.getSalaConID(dondeEstoy).hayMuroEn(frente)){
					//hay muro, por lo que nos toca "girar" a la izquierda

					switch(frente){
					case 'N':
						derecha = frente;
						frente = 'W';
						candidata = dondeEstoy - 1;
						break;
					case 'S':
						derecha = frente;
						frente = 'E';
						candidata = dondeEstoy + 1;
						break;
					case 'E':
						derecha = frente;
						frente = 'N';
						candidata = dondeEstoy - uni.getAncho();
						break;
					case 'W':
						derecha = frente;
						frente = 'S';
						candidata = dondeEstoy + uni.getAncho();
						break;
					}
					
				} else {
					//no hay muro, por lo que podemos avanzar
					ruta.addLast(frente);
					dondeEstoy = candidata;
					switch(frente){
					case 'N':
						candidata = dondeEstoy - uni.getAncho();
						break;
					case 'S':
						candidata = dondeEstoy + uni.getAncho();
						break;
					case 'E':
						candidata = dondeEstoy + 1;
						break;
					case 'W':
						candidata = dondeEstoy - 1;
						break;
					}
				}
			} else {
				//no hay muro a nuestra derecha, por lo que "giramos" a la derecha...
				switch(frente){
				case 'N':
					frente = derecha;
					derecha = 'S';
					candidata = dondeEstoy + 1;
					break;
				case 'S':
					frente = derecha;
					derecha = 'N';
					candidata = dondeEstoy - 1;
					break;
				case 'E':
					frente = derecha;
					derecha = 'W';
					candidata = dondeEstoy + uni.getAncho();
					break;
				case 'W':
					frente = derecha;
					derecha = 'E';
					candidata = dondeEstoy - uni.getAncho();
					break;
				}
				
				//...y avanzamos
				ruta.addLast(frente);
				dondeEstoy = candidata;
				switch(frente){
				case 'N':
					candidata = dondeEstoy - uni.getAncho();
					break;
				case 'S':
					candidata = dondeEstoy + uni.getAncho();
					break;
				case 'E':
					candidata = dondeEstoy + 1;
					break;
				case 'W':
					candidata = dondeEstoy - 1;
					break;
				}
			}	
		}
		
		
		
		
		System.out.println("[SHE] Ruta de " + this.getID() + " : " + ruta.toString());
		this.establecerRuta(ruta);
	}
}
