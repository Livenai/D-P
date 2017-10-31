package personaje;

import armas.Arma;
import mapa.MapaS;

/**
 * Clase que representa el tipo de personaje
 * Villano
 * @author CARLOS MUÑOZ ZAPATA
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
	public Villano(String nombre,char id, Arma armaInicial,int dondeEstoy) {
		super(nombre,id, dondeEstoy);
		arma = armaInicial;
		System.out.println("[!] Villano " + nombre + " [" + id + "] creado con arma " + arma.getNombre() + "[" + arma.getPoder() + "].");
	}

	
	@Override
	public void interactuarSala() {
		
		/*Villanos. También recogerán de la sala el arma con mayor poder, pero dado que éstos 
		sólo podrán llevar consigo un arma (que será configurada para cada personaje al principio 
		de la simulación), sólo se quedarán con el arma de mayor poder entre las dos 
		(la recogida y la que llevaba previamente), dejando en la sala la de menor poder.*/
		
		//obtenemos el arma de mayor poder
		MapaS grand = new MapaS();
		int H = grand.getMapa().getAncho();
		
		Arma sacada = grand.getMapa().getMapita()[getDondeEstoy()%H][getDondeEstoy()/H].getArmasDentro().borrarPrimero();
		
		//ahora comparamos con la nuestra
		if(arma.getPoder() > sacada.getPoder()){ 
			//si es mas poderosa, nos la quedamos para sembrar el ¡CAOS!
			//pero antes tiramos la basuri-arma que teniamos antes :3
			grand.getMapa().getMapita()[getDondeEstoy()%H][getDondeEstoy()/H].insertarArmaEnOrdenDePoder(arma);
			arma = sacada;
		} else {
			//si la sacada no es mas potente o es igual :(
			//pues la dejamos donde estaba
			grand.getMapa().getMapita()[getDondeEstoy()%H][getDondeEstoy()/H].insertarArmaEnOrdenDePoder(sacada);

		}
		
	}


	@Override
	public void interactuarPuerta() {

		MapaS grand = new MapaS();
		
		//puesto que solo tenemos un arma...
		//...el primer paso es obtener el arma mas potente de ElHombrePuerta
		Arma candidataDeEHP = grand.getMapa().getSalaHombrePuerta().getHp().obtenerArmaMasPotente(true);

		//ahora... ¡SE ALZA LA BATALLA! ¿quien gana?
		if(candidataDeEHP.getPoder() > arma.getPoder()){
			//ElHombrePuerta gana asique recupera su arma y no la pierde.
			grand.getMapa().getSalaHombrePuerta().getHp().insertarArma(candidataDeEHP);
		} else {
			//gana el Villano asique ElHombrePuerta pierde su arma.
		}
		
		//por ultimo, comprobamos el estado de ElHombrePuerta para que se
		//anoten los cambios producidos en la batalla
		grand.getMapa().getSalaHombrePuerta().getHp().comprobarEstado();	
		
		System.out.println("Batalla: " + getNombre() + "["+ getID() +"] contra ElHombrePuerta." + "\nArmas en la batalla: " +
				arma.getNombre() + "[" + arma.getPoder() + "]" + "  vs  " 
				+ candidataDeEHP.getNombre() + "[" + candidataDeEHP.getPoder() + "]" 
				+ "\nResultado del portal -> " + grand.getMapa().getSalaHombrePuerta().getHp().isEstado());
		System.out.println("set de armas de ElHombrePuerta despues de la batalla: ");
		grand.getMapa().getSalaHombrePuerta().getHp().mostrarSetDeArmasActual();System.out.println("\n");
	}
}
