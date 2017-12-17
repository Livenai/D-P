package personaje;

import java.util.LinkedList;

import armas.Arma;
import mapa.Mapa;
import salas.ElHombrePuerta;
import salas.Sala;

/**
 * Clase que representa el tipo de personaje
 * Villano
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 *
 */
public class Villano extends Personaje {

	private Arma arma;
	
	/**
	 * Ctor parametrizado (super())
	 * @param nombre
	 * @param id
	 */
	public Villano(String nombre,char id,int dondeEstoy, int turno) {
		super(nombre,id, dondeEstoy, turno);
		System.out.println("[!] Villano " + nombre + " [" + id + "] creado");
	}
	
	public Arma getArma(){
		return arma;
	}

	
	@Override
	public void interactuarSala() {
		//Villanos. Tambi�n recoger�n de la sala el arma con mayor poder, pero dado que estos 
		//s�lo podr�n llevar consigo un arma (que sera configurada para cada personaje al principio 
		//de la simulaci�n), s�lo se quedar�n con el arma de mayor poder entre las dos 
		//(la recogida y la que llevaba previamente), dejando en la sala la de menor poder.
		//solo comprueban cual es la mejor si poseen una, si no cogen la mejor directamente
		Mapa uni = Mapa.obtenerUnico();
		
		//obtenemos el arma de mayor poder
		Arma sacada = uni.getMejorArmaDeSala(getDondeEstoy());
		
		//ahora comparamos con la nuestra
		if(sacada != null){//si encontramos algun arma
			if(arma != null){//si tenemos algun arma
				if(arma.getPoder() < sacada.getPoder()){ 
					//si es mas poderosa, nos la quedamos para sembrar el CAOS!
					//pero antes tiramos la basuri-arma que teniamos antes :3
					uni.insertarArmaEnOrdenEn(arma, getDondeEstoy());
					
					System.out.println("[A] El villano " + this.getNombre() + "[" + this.getID() + "] ha cambiado su arma " +
							arma.getNombre() + " " + arma.getPoder() + " por " + sacada.getNombre() + " " + sacada.getPoder() + ".");
					
					arma = sacada;

				} else {
					//si la sacada no es mas potente o es igual :(
					//pues la dejamos donde estaba
					uni.insertarArmaEnOrdenEn(sacada, getDondeEstoy());
				}
			} else {// si no tenemos ningun arma
				//pues la cogemos directamente
				
				System.out.println("[A] El villano " + this.getNombre() + "[" + this.getID() + "] ha obtenido el arma " +
						sacada.toString());
				
				arma = sacada;
				
			}
		}
		
	}


	@Override
	public void interactuarPuerta() {
		
		if(getDondeEstoy()==Mapa.obtenerUnico().getSalaHombrePuerta().getID()){
			//puesto que solo tenemos un arma...
			//...el primer paso es obtener el arma mas potente de ElHombrePuerta
			ElHombrePuerta hp = Mapa.obtenerUnico().getEHP();
			Arma candidataDeEHP = hp.obtenerArmaMasPotente(true);
			
			//ahora... �SE ALZA LA BATALLA! �quien gana?
			//antes de nada...¿tenemos nosotros un arma?
			if(arma != null){
				if(candidataDeEHP.getPoder() > arma.getPoder()){
					//ElHombrePuerta gana asique recupera su arma y no la pierde.
					hp.insertarArma(candidataDeEHP);
				} else {
					//gana el Villano asique ElHombrePuerta pierde su arma.
				}
			//por ultimo, comprobamos el estado de ElHombrePuerta para que se
			//anoten los cambios producidos en la batalla
			hp.comprobarEstado();
			
			System.out.println("[B] Batalla: " + getNombre() + "["+ getID() +"] contra ElHombrePuerta." + "\n\tArmas en la batalla: " +
					arma.getNombre() + "[" + arma.getPoder() + "]" + "  vs  " 
					+ candidataDeEHP.getNombre() + "[" + candidataDeEHP.getPoder() + "]" 
					+ "\n\tResultado del portal -> " + hp.isEstado());
			System.out.println("set de armas de ElHombrePuerta despues de la batalla: ");
			hp.mostrarSetDeArmasActual();System.out.println("\n");
			}
		}
		
	}



	@Override
	public void interactuarConOtrosPJ() {
		//Quitara su arma al primer SH que se encuentre en la sala sólo 
		//si este personaje posee un arma igual a la que posee el SH y tiene mayor o igual poder.
		Mapa uni = Mapa.obtenerUnico();
		Sala salaActual = uni.getSalaConID(this.getDondeEstoy());
		
		//1º) bucamos al primer SH (si no hay ninguno no se hace nada)
		SuperHeroe elPrimero = salaActual.sacarPrimerSH(false);
		if(elPrimero != null){//si existe
			//el SH savca su arma
			Arma laDelSH = elPrimero.getArmaIgualA(this.arma);

			System.err.println(laDelSH.toString());
			//comprobamos
			if(this.arma.getPoder()>=laDelSH.getPoder()){
				//ganamos, asique el SH es derrotado
				System.out.println("[X] el SuperHeroe " + elPrimero.toString() + " ha sido derrotado por " + this.toString());
				//y pierde su arma, por lo que no se la devolvemos
			} else {
				//perdemos, asique todo queda como estaba
				//el SH obtiene de nuevo su arma
				elPrimero.insertarArma(laDelSH);
			}
		}//if
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Villano [arma=" + arma + ", Nombre=" + getNombre() + ", ID=" + getID() + ", Sala="
				+ getDondeEstoy() + "]";
	}


	@Override
	public void calcularRuta() {
		//regla de la mano izquierda
		LinkedList<Character> ruta = new LinkedList<Character>();
		Mapa uni = Mapa.obtenerUnico();
		
		//primero necesitamos saber que direccion es nuestra derecha, nuestro frente
		char frente = 'S';
		char izquierda = 'E';
		int candidata = getDondeEstoy()+uni.getAncho();//a la que puede que nos movamos
		int dondeEstoy = uni.getSalaConID(getDondeEstoy()).getID();//la sala actual
		
		//ahora iteramos hasta llegar a la sala TheDailyPlanet
		while(!(dondeEstoy == uni.getSalaHombrePuerta().getID())){
			//comprobamos nuestra izquierda. ¿hay muro?
			if(uni.getSalaConID(dondeEstoy).hayMuroEn(izquierda)){
				//hay muro, por lo que ahora comprobamos el frente
				if(uni.getSalaConID(dondeEstoy).hayMuroEn(frente)){
					//hay muro, por lo que nos toca "girar" a la derecha

					switch(frente){
					case 'N':
						izquierda = frente;
						frente = 'E';
						candidata = dondeEstoy + 1;
						break;
					case 'S':
						izquierda = frente;
						frente = 'W';
						candidata = dondeEstoy - 1;
						break;
					case 'E':
						izquierda = frente;
						frente = 'S';
						candidata = dondeEstoy + uni.getAncho();
						break;
					case 'W':
						izquierda = frente;
						frente = 'N';
						candidata = dondeEstoy - uni.getAncho();
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
				//no hay muro a nuestra izquierda, por lo que "giramos" a la izquierda...

				switch(frente){
				case 'N':
					frente = izquierda;
					izquierda = 'S';
					candidata = dondeEstoy - 1;
					break;
				case 'S':
					frente = izquierda;
					izquierda = 'N';
					candidata = dondeEstoy + 1;
					break;
				case 'E':
					frente = izquierda;
					izquierda = 'W';
					candidata = dondeEstoy - uni.getAncho();
					break;
				case 'W':
					frente = izquierda;
					izquierda = 'E';
					candidata = dondeEstoy + uni.getAncho();
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
		
		
		
		
		System.out.println("[V] Ruta de " + this.getID() + " : " + ruta.toString());
		this.establecerRuta(ruta);
	}

	
	/**
	 * Dice si el Villano posee un arma o no
	 * @return True -> tiene arma; False -> notiene arma (null)
	 */
	public boolean tieneArma() {

		return (arma != null);
	}



}
