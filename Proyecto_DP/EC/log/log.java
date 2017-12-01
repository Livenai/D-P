package log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Clase que se dedica exclusivamente a escribir los datos que se le pasen en un registro .log
 * @author CARLOS MZ
 *
 */
public final class log {
	
	/**
	 * reinicia el archivo para no escribir a continuacion de un log antiguo
	 */
	public static void recreate() {
		FileOutputStream out;
		try {
			out = new FileOutputStream("registro.log");
			out.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error al crear fichero 'registro.log'.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que escribe un String en el archivo: "registro.log".
	 * La escritura es equivalente a System.out.print();
	 * @param linea -> String a escribir
	 */
	public static void write(String linea) {
		BufferedWriter out = null;
		try   {
		    FileWriter fstream = new FileWriter("registro.log", true);
		    out = new BufferedWriter(fstream);
		    out.write(linea);
		} catch (IOException e) {
		    System.err.println("Error: " + e.getMessage());
		} finally {
		    if(out != null) {
		        try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
	}
}
