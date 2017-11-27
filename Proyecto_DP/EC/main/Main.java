package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import armas.Arma;
import cargador.Cargador;
import cargador.FicheroCarga;
import mapa.Mapa;
import mapa.MazeGen;
import personaje.SuperHeroe;
import personaje.Villano;

/**
 * Clase con solo el MAIN del proyecto para tenerlo facilmente localizado
 * @author CARLOS MUÑOZ ZAPATA
 *
 */
public class Main {


	public static void main(String[] args) {//--------------------------------------------------------------------------------------		
		//cargamos las cosas del fichero ~
		Cargador cargador = new Cargador();
		try { 
			//Método que procesa línea a línea el fichero de entrada inicio.txt
		     FicheroCarga.procesarFichero("init.txt", cargador);
		}
		catch (FileNotFoundException valor)  {
			System.err.println ("Excepción capturada al procesar fichero: "+valor.getMessage());
		}
		catch (IOException valor)  {
			System.err.println ("Excepción capturada al procesar fichero: "+valor.getMessage());
		}
		
		//TODO IMPORTANTE -> hay que poner un atributo con la sala inicio, final y las 4 esquinas y usarlos con metodos para que al cambiar esta parte solo sea cambiarel emtodo
		
		
		//generamos nuestro laberinto
		MazeGen generador = new MazeGen();
		generador.generarMapaAleatorio();
		// insertamos las armas en el mapa
		Mapa.obtenerUnico().insertarArmas();		
		Mapa.obtenerUnico().mostrarMapa();
		Mapa.obtenerUnico().mostrarSalasConArmas();
		
		//iniciamos la simulacion
		
		//Mapa.obtenerUnico().iniciarSimulacion();

	}//fin del MAIN-----------------------------------------------------------------------------------------------------------------


}
