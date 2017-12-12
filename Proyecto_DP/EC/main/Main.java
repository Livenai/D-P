package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import cargador.Cargador;
import cargador.FicheroCarga;
import mapa.Mapa;
import mapa.MazeGen;

/**
 * Clase con solo el MAIN del proyecto para tenerlo facilmente localizado
 * @author CARLOS MUÑOZ ZAPATA
 *
 */
public class Main {


	public static void main(String[] args) {//--------------------------------------------------------------------------------------		
		//cargamos las cosas del fichero ~
		log.log.recreate(); //esto reiniciael archivo para no escribir a continuacion de un log antiguo
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
		
		Mapa uni = Mapa.obtenerUnico();
		
		
		
		//generamos nuestro laberinto
		MazeGen generador = new MazeGen();
		generador.generarMapaAleatorio();
		
		uni.registrarCaminosDePJ();
		// insertamos las armas en el mapa
		uni.insertarArmas();		
		uni.mostrarMapa();
		uni.mostrarSalasConArmas();
		
		//iniciamos la simulacion
		uni.mostrarPJ();
		uni.iniciarSimulacion();

	}//fin del MAIN-----------------------------------------------------------------------------------------------------------------

	
	//TODO -> hay que arreglar el obtenerBorrando del arbol
	//TODO -> hay que tener un eclipse que te genere los UML
	//TODO -> hay que hacer lo de los caminos y los comparaores

}
