package mapa;

import personaje.Personaje;
import salas.Sala;
import salas.TheDailyPlanet;

/**
 * clase mapa, la cual es Unica y accesible
 * desde cualquier punto
 * @author CARLOS MUÑOZ ZAPATA
 * GIIIC
 */
public class Mapa {
	
	// Atributos
	//[alto][ancho]   [ID/ancho][ID%ancho] so GOOD
	private Sala[][] mapita; // tablero con todas las salas
	private TheDailyPlanet SalaHombrePuerta; // puntero a la sala de ElHombrePuerta
	private int ancho;
	private int alto;
	//---------

	/**
	 * Ctor generico.
	 * crea el mapa con los siguientes atributos estandarizados
	 * mapa [6][6]
	 * SalaHombrePuerta(DailyPlanet) -> 35 (en la ultima)
	 * constante de apertura -> 4
	 */
	public Mapa() {
		mapita = new Sala[6][6];
		alto = mapita.length; 
		ancho = mapita[0].length;
		for (int i = 0; i < alto; i++) {//alto
			for (int j = 0; j < ancho; j++) {//ancho
				mapita[i][j] = new Sala((i*ancho)+j);
			}
		}
		//creamos la sala especial y la metemos en la posicion dicha
		SalaHombrePuerta = new TheDailyPlanet(35, 4);
		mapita[alto-1][ancho-1] = SalaHombrePuerta;
		System.out.println("[!] Mapa creado");
	}
	
	/**
	 * Ctor. parametrizado
	 * @param alto
	 * @param ancho
	 * @param TheDailyPlanet
	 */
	public Mapa(int alto_, int ancho_, int TheDailyPlanet,int cteApertura) {
		mapita = new Sala[alto_][ancho_];
		alto = mapita.length;
		ancho = mapita[0].length;
		for (int i = 0; i < alto; i++) {//alto
			for (int j = 0; j < ancho; j++) {//ancho
				mapita[i][j] = new Sala((i*ancho)+j);
			}
		}
		//creamos la sala especial y la metemos en la posicion dicha
		SalaHombrePuerta = new TheDailyPlanet(TheDailyPlanet, cteApertura);
		mapita[TheDailyPlanet/ancho][ TheDailyPlanet%ancho] = SalaHombrePuerta;
		System.out.println("[!] Mapa creado con medidas ANCHO: " + ancho + " ;ALTO: " + alto + " (" + ancho*alto + " salas)");
	}

	
	
	public Sala[][] getMapita() {
		return mapita;
	}

	public TheDailyPlanet getSalaHombrePuerta() {
		return SalaHombrePuerta;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	/**
	 * Muestra el mapa por pantalla segun 
	 * el formato dado
	 */
	public void mostrarMapa(){
		System.out.println("Mostrando el mapa:");
		MapaS grand = new MapaS();
		 for (int i = 0; i < grand.getMapa().getAncho(); i++) {
		        System.out.print(" _");
		    }
		    System.out.println();
		    
		    for(int j = 0; j < grand.getMapa().getAlto(); j++) {
		        System.out.print("|");
		        for(int i = 0; i < grand.getMapa().getAncho(); i++) {
		            if(grand.getMapa().getMapita()[i][j].getPJDentro().obtenerTam() > 1) {
		                System.out.print(""+grand.getMapa().getMapita()[i][j].getPJDentro().obtenerTam());
		            } else if(grand.getMapa().getMapita()[i][j].getPJDentro().obtenerTam() == 1) {
		                System.out.print(""+grand.getMapa().getMapita()[i][j].getPJDentro().obtenerElemento(0).getID());
		            } else {
		                if(j == grand.getMapa().getAlto() - 1) {
		                    System.out.print("_");
		                } else
		                	if(/*hayMuroS(i, j)*/false) {
		                    System.out.print("_");
		                } else {
		                    System.out.print(" ");
		                }
		            }
		            
		            if(i == grand.getMapa().getAncho() - 1) {
		                System.out.print("|");
		            } else if(/*hayMuroD(i, j)*/false) {
		                System.out.print("|");
		            } else {
		                System.out.print(" ");
		            }
		        }
		        
		        System.out.println();
		    }
	}//--------
	
	
	
    /**
     * Muestra las salas que tienen armas y cuales son estas
     */
    public void mostrarSalasConArmas(){
    	System.out.println("Mostrando Salas con Armas:");
    	for (int i = 0; i < ancho*alto; i++) {
			if(!mapita[i/ancho][i%ancho].getArmasDentro().estaVacia()){
				mapita[i/ancho][i%ancho].mostrarArmas();
			}
		}
    }

    /**
     * metodo que inserta un pj en la sala con dicho id
     * si ID no esta en el rango 0-alto*ancho-1; no lo inserta y avisa
     * @param id
     * @param pj
     */
	public void insertarPJ(int id, Personaje pj) {
		if(0 <= id && id < (alto*ancho) ) {
			mapita[id/ancho][id%ancho].insertarPJ(pj);	
		} else {
			System.err.println("Se ha intentado insertar el pj " + pj.getNombre() + ", " + pj.getID() + " en la sala " + id + "\n rango (0 <= id <= " + (ancho*alto) +")");
		}
		
	}
}
