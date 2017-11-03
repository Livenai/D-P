package salas;

import java.util.LinkedList;

import armas.Arma;
import contenedores.Arbol;

/**
 * Clase que representa a ElHombrePuerta
 * Este elemento es quien se pone entre la sala en si
 * y el teseracto (victoria) por lo que puede considerarse una
 * PUERTA. 
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 */
public class ElHombrePuerta {
	//Atributos
	
	private boolean estado; //true -> abierto
	private int constanteDeApertura;
	
	private Arbol<Arma> SetDeArmasIniciales;// TODO quitar. no va a hacer falta
	private Arbol<Arma> ArmasActuales;
	


	/**
	 * Ctor del hombre puerta
	 */
	public ElHombrePuerta(int cteApertura){
		constanteDeApertura = cteApertura;
		estado = false;
		
		 SetDeArmasIniciales = new Arbol<Arma>();
		 ArmasActuales = new Arbol<Arma>();	
		
	    Arma[] setDeArmas = {new Arma("CampoEnergia", 5), new Arma("Armadura",13), new 
	            Arma("Anillo",11), new Arma("Acido",1), new Arma("Antorcha",5), new 
	            Arma("Bola",3), new Arma("Baston",22), new Arma("CadenaFuego",11), new 
	            Arma("Espada",11), new Arma("Cetro",20), new Arma("Capa",10), new 
	            Arma("CampoMagnetico",5), new Arma("Escudo",3), new Arma("Garra",22), new  
	            Arma("Flecha",12), new Arma("Gema",4)};
	    
	    for (int j = 0; j < setDeArmas.length; j++) {
	    	SetDeArmasIniciales.insertar(setDeArmas[j]);
	    	ArmasActuales.insertar(setDeArmas[j]);
		}
	    
	    
	    System.out.println("[!] ElHombrePuerta ha sido creado con un set de " + setDeArmas.length + " armas.");
	    System.out.println("     set de armas ordenadas:");
	    mostrarSetDeArmasIniciales();
	}
	


	//getter
	public boolean isEstado() {
		return estado;
	}
	
	
	/**
	 * Comprueba el estado del arbol BB de armas.
	 * Cambia el estado de ElHombrePuerta en funcion de:
	 * 
	 * 1 -> la profundidad del arbol debe ser menor que una constante dada
	 */
	public void comprobarEstado(){
		if(ArmasActuales.alturaArbol() < constanteDeApertura) {
			estado = true;
			System.out.println("-> El Hombre Puerta ha abierto el portal <-");
		} else {
			estado = false;
		}
	}



	public int getConstanteDeApertura() {
		return constanteDeApertura;
	}



	public void setConstanteDeApertura(int constanteDeApertura) {
		this.constanteDeApertura = constanteDeApertura;
	}
	
	/**
	 * muestra por pantalla el set de armas
	 * iniciales en su orden correspondiente
	 */
	public void mostrarSetDeArmasIniciales(){
		SetDeArmasIniciales.inOrdenMostrar();
	}
	
	
	/**
	 * muestra por pantalla el set de armas
	 * actual en su orden correspondiente
	 */
	public void mostrarSetDeArmasActual(){
		ArmasActuales.inOrdenMostrar();
	}


	
	/**
	 * metodo que devuelve el arma del arsenal del 
	 * hombre puerta equiparable a @arma
	 * @return el arma del arsenal del hombre puerta
	 */
	public Arma obtenerBorrando(Arma arma) {
		return ArmasActuales.obtenerBorrando(arma);
	}


	/**
	 * Metodo que introduce un arma en el set de armas de ElHombrePuerta
	 * @param arma
	 */
	public void insertarArma(Arma arma) {
		ArmasActuales.insertar(arma);
	}



	/**
	 * metodo que devuelve el arma mas potente 
	 * del arsenal de ElHombrePuerta.
	 * 
	 * CUIDADO -> se entiende que es imposible que el arsenal del 
	 * 				hombrePuerta este vacio y que no se haya abierto este dando 
	 * 				paso a un ganador y terminando la simulacion. (caso de cte = 0)
	 * 				
	 * @param borrar -> true: borra el elemento de la lista;
	 * 					 False: lo mantiene (solo lo devuelve)
	 * @return arma mas potente (null si no le quedan)
	 */
	public Arma obtenerArmaMasPotente(boolean borrar) {
		Arma ret = new Arma("a pu�etazo limpio",-1);
		//primero obtenemos las armas que tenemos disponibles
		LinkedList<Arma> ll = ArmasActuales.inOrden();
		
		//ahora obtenemos el arma mas potente
		for (int i = 0; i < ll.size(); i++) {
			if(ret.getPoder() < ll.get(i).getPoder()){
				ret = ll.get(i);
			}
		}
		//ahora ret posee el arma mas potente
		
		
		if(borrar){
			ArmasActuales.borrar(ret);
		}
		

		
		return ret;
	}





	
	

}
