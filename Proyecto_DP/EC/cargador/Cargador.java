package cargador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Clase encargada de leer los datos del fichero y
 * de inicializar los elementos del mapa que representen esos datos
 * @author CARLOS MU�OZ ZAPATA
 *
 */
public class Cargador {

	File f;
	FileReader fr;
	BufferedReader br;
	
	public Cargador() {
		f = new File("cargador.txt");
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			System.err.println("[!!] No se ha podido LEER el fichero. \n     Asegurate de que esta en la ruta: " + f.getAbsolutePath());
			e.printStackTrace();
		} 
	}
	
	
	public void cargar(){
		try {
			FicheroCarga.procesarFichero("cargador.txt", this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void crear(String string, int numCampos, List<String> listaDeTroceados) {
		// TODO Auto-generated method stub
		
	}

}
