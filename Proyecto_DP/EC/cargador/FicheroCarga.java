package cargador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase rescatada del anterior proyecto.
 * ha sido editada y adaptada a las funcionalidades requerida:
 * Esta clase lee del fichero y trocea las lineas, obteniendo 
 * la informacion del fichero.
 * Despues llama a la funcion del cargador apropiada y lepasa los datos
 * para continuar con la carga de datos y la inicializacion de elementos.
 * @author Profesores DP (editado y adaptado por CARLOS MU�OZ ZAPATA)
 * @version 1.0 -  02/11/2011 
 *
 */
public class FicheroCarga {
	/**  
	atributo de la clase que indica el numero máximo de campos que se pueden leer
	*/
	public static final int MAXCAMPOS  = 10;

	/**  
	buffer para almacenar el flujo de entrada
	*/
	private static BufferedReader bufferIn;
	
	/**
	 * Metodo para procesar el fichero. Sin excepciones
	 * @return codigoError con el error que se ha producido (void¿?)
	 * @throws Exception. Puede lanzar una excepcion en la apertura del buffer de lectura
	 */
	 public static void procesarFichero(String nombreFichero, Cargador cargador) throws  FileNotFoundException, IOException {
		 //**String ListaDeTroceados[]=new String[MAXCAMPOS];
		 List<String> ListaDeTroceados = new ArrayList<String>();
	     String S=new String();
	     int numCampos=0;

	     System.out.println( "[!]Procensando el fichero..." );
	     
	
	     bufferIn = new BufferedReader(new FileReader(nombreFichero));//creacion del filtro asociado al flujo de datos
	     
	     while((S=bufferIn.readLine())!= null) {
	     	 System.out.println( "S: "+S );
  	 		 if (!S.startsWith("--"))  {
  	 			 ListaDeTroceados.clear();
  	 			 numCampos = trocearLinea(S, ListaDeTroceados);

  	 			 cargador.crear(ListaDeTroceados.get(0), numCampos, ListaDeTroceados);//crea el objeto correspondiente a cada linea

	 		}
	     }
         
 	
	     bufferIn.close();//cerramos el filtro
	 }

	 /**
	  * Metodo para trocear cada linea y separarla por campos
	  * @param LineaEntera cadena con la l�nea completa le�da
	  * @param vCampos. Array de String que contiene en cada posici�n un campo distinto
	  * @return numCampos. N�mero campos encontrados
	 */
	 private static int trocearLinea(String LineaEntera,List<String> lineaTroceada) {
		 boolean eol = false;
		 int pos=0,posini=0, numCampos=0;

   	     while (!eol)
   	     {
	    			pos = LineaEntera.indexOf("#");
	     	    	if(pos!=-1) {
	     	    		lineaTroceada.add(new String(LineaEntera.substring(posini,pos)));
	     	    		LineaEntera=LineaEntera.substring(pos+1, LineaEntera.length());
	     	    		numCampos++;
	     	    	}
	     	    	else eol = true;
		  }
		  return numCampos;
	 }

}
