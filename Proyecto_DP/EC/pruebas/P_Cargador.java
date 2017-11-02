package pruebas;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;


/**
 * Clase creada para probar elementos que pudieran resultar 
 * conflictivos en el proyecto si no funcionan correctamente
 * 
 * (esta clase no esta pensada para probar los metodos de la 
 * clase a ala que representa, sino para probar elementos o 
 * fragmentos de codigo concretos)
 * @author CARLOS MU�OS ZAPATA
 *
 */
public class P_Cargador {

	@Test
	public void testFicheros() {
		//a probar si la carga y cerrado de ficheros es correcta
		File f;
		FileReader fr;
		BufferedReader br;
		try{
			//inicializar
			f = new File("cargador.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			//usamos
			
			String linea = br.readLine();
			int inicio = 0;
			int fin = linea.indexOf('#');
			
			linea.substring(inicio, fin);
			
			//y cerramos
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e){
			System.err.println("archivo no encontrado");
			assertTrue(false);
		} catch (IOException e) {
			System.err.println("error al cerrar el buffer y el lector de archivos");
			assertTrue(false);
		}
	}

}
