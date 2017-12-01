package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import armas.Arma;
import contenedores.Arbol;

/**
 * aqui probamos que los metodos que he hecho 
 * por mi mismo en la clase arbol funcionen.
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 *
 */
public class P_Arbol {

	@Test
	public void testObtenerBorrando() {
		Arma a = new Arma("A",1);
		Arma b = new Arma("B",2);
		Arma c = new Arma("C",3);
		Arma d = new Arma("D",4);
		Arma e = new Arma("E",5);
		Arma f = new Arma("F",6);
		Arma g = new Arma("G",7);
		
		Arbol<Arma> arbol = new Arbol<Arma>();
		
		arbol.insertar(a);
		arbol.insertar(b);
		arbol.insertar(c);
		arbol.insertar(d);
		arbol.insertar(e);
		arbol.insertar(f);
		arbol.insertar(g);
		
		Arma prueba = new Arma("A",3);
	
		
		assertTrue(arbol.obtenerBorrando(prueba,true).equals(prueba)); //comprobamos si funciona al darle un elemento con solo el mismo nombre

		assertTrue(arbol.obtenerBorrando(prueba,true) == null); //comprobamos si se ha borrado el elemento
		
		prueba = new Arma("B",3);
		
		assertTrue(arbol.obtenerBorrando(prueba,true).equals(prueba)); //comprobamos si funciona al darle un elemento con solo el mismo nombre

		assertTrue(arbol.obtenerBorrando(prueba,true) == null); //comprobamos si se ha borrado el elemento
		
		prueba = new Arma("C",3);
		
		assertTrue(arbol.obtenerBorrando(prueba,true).equals(prueba)); //comprobamos si funciona al darle un elemento con solo el mismo nombre

		assertTrue(arbol.obtenerBorrando(prueba,true) == null); //comprobamos si se ha borrado el elemento

		 
	}
	
	
	@Test
	public void testBorrar() {
		
		
		Arma a = new Arma("A",1);
		Arma b = new Arma("B",2);
		Arma c = new Arma("C",3);
		Arma d = new Arma("D",4);
		Arma e = new Arma("E",5);
		Arma f = new Arma("F",6);
		Arma g = new Arma("G",7);
		
		Arbol<Arma> arbol = new Arbol<Arma>();
		
		arbol.insertar(a);
		arbol.insertar(e);
		
		arbol.insertar(c);
		arbol.insertar(g);
		arbol.insertar(d);
		
		arbol.insertar(f);
		
		arbol.insertar(b);
		
		arbol.inOrdenMostrar();System.out.println();
		
		
		arbol.borrar(new Arma("C",1));
		arbol.inOrdenMostrar();System.out.println();

		
		arbol.borrar(new Arma("A",1));
		arbol.inOrdenMostrar();System.out.println();
		
		arbol.borrar(new Arma("B",1));
		arbol.inOrdenMostrar();System.out.println();
		
		arbol.borrar(new Arma("F",1));
		arbol.inOrdenMostrar();System.out.println();
		
		arbol.borrar(new Arma("E",1));
		arbol.inOrdenMostrar();System.out.println();
		
		arbol.borrar(new Arma("D",1));
		arbol.inOrdenMostrar();System.out.println();
		
		
		System.out.println("---");
		arbol.borrar(new Arma("G",7));
		arbol.inOrdenMostrar();System.out.println();

		
		
		
		
	}
	

}
