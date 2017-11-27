package personaje;

import armas.Arma;
import mapa.Mapa;
import salas.ElHombrePuerta;

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

	
	@Override
	public void interactuarSala() {
		
		/*Villanos. Tambi�n recoger�n de la sala el arma con mayor poder, pero dado que estos 
		s�lo podr�n llevar consigo un arma (que sera configurada para cada personaje al principio 
		de la simulaci�n), s�lo se quedar�n con el arma de mayor poder entre las dos 
		(la recogida y la que llevaba previamente), dejando en la sala la de menor poder.*/
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
					arma = sacada;
				} else {
					//si la sacada no es mas potente o es igual :(
					//pues la dejamos donde estaba
					uni.insertarArmaEnOrdenEn(sacada, getDondeEstoy());
				}
			} else {// si no tenemos ningun arma
				//pues la cogemos directamente
				arma = sacada;
				
			}
		}
		
	}


	@Override
	public void interactuarPuerta() {
		//puesto que solo tenemos un arma...
		//...el primer paso es obtener el arma mas potente de ElHombrePuerta
		ElHombrePuerta hp = Mapa.obtenerUnico().getEHP();
		Arma candidataDeEHP = hp.obtenerArmaMasPotente(true);
		
		//ahora... �SE ALZA LA BATALLA! �quien gana?
		if(candidataDeEHP.getPoder() > arma.getPoder()){
			//ElHombrePuerta gana asique recupera su arma y no la pierde.
			hp.insertarArma(candidataDeEHP);
		} else {
			//gana el Villano asique ElHombrePuerta pierde su arma.
		}
		
		//por ultimo, comprobamos el estado de ElHombrePuerta para que se
		//anoten los cambios producidos en la batalla
		hp.comprobarEstado();	
		
		System.out.println("Batalla: " + getNombre() + "["+ getID() +"] contra ElHombrePuerta." + "\nArmas en la batalla: " +
				arma.getNombre() + "[" + arma.getPoder() + "]" + "  vs  " 
				+ candidataDeEHP.getNombre() + "[" + candidataDeEHP.getPoder() + "]" 
				+ "\nResultado del portal -> " + hp.isEstado());
		System.out.println("set de armas de ElHombrePuerta despues de la batalla: ");
		hp.mostrarSetDeArmasActual();System.out.println("\n");
	}
}
