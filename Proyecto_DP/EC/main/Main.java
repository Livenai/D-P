package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import armas.Arma;
import cargador.Cargador;
import cargador.FicheroCarga;
import mapa.Mapa;
import personaje.SuperHeroe;
import personaje.Villano;

/**
 * Clase con solo el MAIN del proyecto para tenerlo facilmente localizado
 * @author CARLOS MUÑOZ ZAPATA
 *
 */
public class Main {


	public static void main(String[] args) {//--------------------------------------------------------------------------------------		
		//TODO -> SACAR EL MAIN A UNA CLASEA PARTEPARA QUE LO ENCUENTREN LOS PROFES
		//cargamos las cosas del fichero ~
		Cargador cargador = new Cargador();
		try {
			/**  
			Método que procesa línea a línea el fichero de entrada inicio.txt
			*/
		     FicheroCarga.procesarFichero("init.txt", cargador);
		}
		catch (FileNotFoundException valor)  {
			System.err.println ("Excepción capturada al procesar fichero: "+valor.getMessage());
		}
		catch (IOException valor)  {
			System.err.println ("Excepción capturada al procesar fichero: "+valor.getMessage());
		}
		//TODO quitar esta inicializacion y usar la del cargador
		//TODO IMPORTANTE -> hay que poner un atributo con la sala inicio, final y las 4 esquinas y usarlos con metodos para que al cambiar esta parte solo sea cambiarel emtodo
		// insertamos las armas en el mapa
		Mapa.obtenerUnico().insertarArmas();
		
		//creamos unos cuantos personajes:
        //con armas potentes que si no no hacen nada en la simulacion :p

		/* PARA COPIAR A LOS METODOS DEL CARGADOR
        SuperHeroe thor = new SuperHeroe("Thor", 'T',35);
        thor.insertarArma(new Arma("Capa",11));

        SuperHeroe ironMan = new SuperHeroe("IronMan", 'I',35);
        ironMan.insertarArma(new Arma("Armadura",15));


        SuperHeroe storm = new SuperHeroe("Storm", 'S',35);
        storm.insertarArma(new Arma("CampoMagnetico",20));

        SuperHeroe captainAmerica = new SuperHeroe("Capitan Am�rica", 'C',35);
        captainAmerica.insertarArma(new Arma("Escudo",18));
        
        SuperHeroe cadenaDeFuegoSaltarina = new SuperHeroe("Cadena De Fuego Saltarina", 'F',35);
        cadenaDeFuegoSaltarina.insertarArma(new Arma("CadenaFuego",14));


        Villano deadPool = new Villano("Dead Pool", 'D', new Arma ("Sable",23),35);
    

        Villano kurtConnors = new Villano("Kurt Connors", 'K', new Arma ("CampoEnergia",24),35);
     

        Villano nebula = new Villano("Nebula", 'N', new Arma ("RayoEnergia",30),35);
        
        Villano octopus = new Villano("Octopus", 'O', new Arma ("Garra",15),35);
        
        Villano duendeVerde = new Villano("DuendeVerde", 'O', new Arma ("Acido",19),35);
	*/
		
		
		Mapa.obtenerUnico().mostrarMapa();
		Mapa.obtenerUnico().mostrarSalasConArmas();
		
		//iniciamos la simulacion
		
		Mapa.obtenerUnico().iniciarSimulacion();

	}//fin del MAIN-----------------------------------------------------------------------------------------------------------------


}
