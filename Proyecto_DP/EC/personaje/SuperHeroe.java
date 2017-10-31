package personaje;

import java.util.LinkedList;

import armas.Arma;
import contenedores.Arbol;
import mapa.MapaS;

/**
 * Clase que representa el tipo de personaje
 * SuperHeroe
 * @author CARLOS MUÑOZ ZAPATA
 * GIIIC
 *
 */
public class SuperHeroe extends Personaje {
	
	private Arbol<Arma> ArmasDelPJ;


	
	/**
	 * Ctor parametrizado (super())
	 * @param nombre
	 * @param id
	 */
	public SuperHeroe(String nombre,char id,int dondeEstoy) {
		super(nombre,id, dondeEstoy);		
		ArmasDelPJ = new Arbol<Arma>();
		System.out.println("[!] SuperHeroe " + nombre + " [" + id + "] creado.");

	}
	
	/**
	 * getter
	 * @return
	 */
	public Arbol<Arma> getArmasDelPJ() {
		return ArmasDelPJ;
	}

	@Override
	public void interactuarSala() {
		/* Superhéroes. Si no tenía previamente este arma, será almacenada en su contenedor de armas 
		(que debe permitir búsquedas de la forma más eficiente posible), mientras que si ya la tenía, se le sumará 
		al poder del arma que tenía el poder del arma que ha recogido. */

		//obtenemos el arma mas potente de la sala
		MapaS grand = new MapaS();
		int H = grand.getMapa().getAncho();
		
		Arma sacada = grand.getMapa().getMapita()[getDondeEstoy()%H][getDondeEstoy()/H].getArmasDentro().borrarPrimero();
		
		//nos la quedamos :3
		if(ArmasDelPJ.pertenece(sacada)){
			//si ya tenemos un arma igual, ¡JUNTAMOS SU PODER! pero desenvainamos primero la nuestra ~
			Arma laMia = ArmasDelPJ.obtenerBorrando(sacada);
			//juntamos su poder y nos la guardamos
			ArmasDelPJ.insertar(new Arma(laMia.getNombre(),(laMia.getPoder()+sacada.getPoder()))); 
		} else {
			//si no poseiamos ya ese arma, pues nos la quedamos ~
			ArmasDelPJ.insertar(sacada);
		}
		
	}

	@Override
	public void interactuarPuerta() {

		MapaS grand = new MapaS();
		//primero obtenemos el arma mas potente de nuestro arsenal
		Arma miCandidata = obtenerArmaMasPotente(true);
		//ahora ElHombrePuerta saca su arma del mismo tipo (si la tiene)
		Arma candidataDeEHP = grand.getMapa().getSalaHombrePuerta().getHp().obtenerBorrando(miCandidata);
		
		//ahora... ¡SE ALZA LA BATALLA! ¿quien gana? 
		//si el hombre puerta no tiene un arma equiparable... pues nada, no hay batalla :/
		if(candidataDeEHP != null){ 
			if(miCandidata.getPoder() > candidataDeEHP.getPoder()){ // si gana el SuperHeroe
				//pues el super heroe ha ganado y ElHombrePuerta se queda sin arma
			} else {
				//pues ElHombrePuerta ha ganado asique conserva su arma.
				grand.getMapa().getSalaHombrePuerta().getHp().insertarArma(candidataDeEHP);
			}
		}
		//el SuperHeroe siempre pierde su arma en la batalla (o el intento de...) :(
		
		//por ultimo, comprobamos el estado de ElHombrePuerta para que se
		//anoten los cambios producidos en la batalla
		grand.getMapa().getSalaHombrePuerta().getHp().comprobarEstado();
		
		System.out.println("Batalla: " + getNombre() + "["+ getID() +"] contra ElHombrePuerta." + "\nArmas en la batalla: " +
				miCandidata.getNombre() + "[" + miCandidata.getPoder() + "]" + "  vs  " 
				+ candidataDeEHP.getNombre() + "[" + candidataDeEHP.getPoder() + "]" 
				+ "\nResultado del portal -> " + grand.getMapa().getSalaHombrePuerta().getHp().isEstado());
		System.out.println("set de armas de ElHombrePuerta despues de la batalla: ");
		grand.getMapa().getSalaHombrePuerta().getHp().mostrarSetDeArmasActual();System.out.println("\n");
	}
	
	
	/**
	 * devuelve el arma mas potente del 
	 * arsenal del SuperHeroe y la borra a gusto.
	 * @param borrar -> true: borra el elemento de la lista;
	 * 					 False: lo mantiene (solo lo devuelve)
	 * @return el arma mas potente
	 */
	public Arma obtenerArmaMasPotente(boolean borrar){
		Arma ret = new Arma("a puñetazo limpio",-1);
		//primaro obtenemos las armas que que tenemos disponibles
		LinkedList<Arma> ll = ArmasDelPJ.inOrden();
		
		//ahora obtenemos el arma mas potente
		for (int i = 0; i < ll.size(); i++) {
			if(ret.getPoder() < ll.get(i).getPoder()){
				ret = ll.get(i);
			}
		}
		//ahora ret posee el arma mas potente
		
		
		if(borrar){
			ArmasDelPJ.borrar(ret);
		}
		
		
		return ret;
	}

	/**
	 * metodo que inserta un arma en el arbol de armas del SuperHeroe
	 * @param arma
	 */
	public void insertarArma(Arma arma) {
		ArmasDelPJ.insertar(arma);
	}

	
}
