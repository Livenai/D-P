package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import armas.Arma;
import personaje.SuperHeroe;


/**
 * juego de pruebas que probara los metodos mas 
 * conflictivos y complicados de la clase SuperHeroe
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 *
 */
public class P_SuperHeroe {

	@Test
	public void testObtenerArmaMasPotente() {
		SuperHeroe superH = new SuperHeroe("superheroe", 'S', 0, 4);
		
		Arma a = new Arma("A",1);
		Arma b = new Arma("B",2);
		Arma c = new Arma("C",3);
		Arma d = new Arma("D",4);
		Arma e = new Arma("E",5);
		Arma f = new Arma("F",6);
		Arma g = new Arma("G",7);
		
		superH.getArmasDelPJ().insertar(a);
		superH.getArmasDelPJ().insertar(b);
		superH.getArmasDelPJ().insertar(c);
		superH.getArmasDelPJ().insertar(d);
		superH.getArmasDelPJ().insertar(e);
		superH.getArmasDelPJ().insertar(f);
		superH.getArmasDelPJ().insertar(g);
		
		superH.getArmasDelPJ().inOrdenMostrar();
		System.out.println();
		
		assertTrue(superH.obtenerArmaMasPotente(true).getPoder() == 7);
		superH.getArmasDelPJ().inOrdenMostrar();
		System.out.println();
		
		assertTrue(superH.obtenerArmaMasPotente(true).getPoder() == 6);
		superH.getArmasDelPJ().inOrdenMostrar();
		System.out.println();
		
		assertTrue(superH.obtenerArmaMasPotente(true).getPoder() == 5);
		superH.getArmasDelPJ().inOrdenMostrar();
		System.out.println();
		
		assertTrue(superH.obtenerArmaMasPotente(true).getPoder() == 4);
		superH.getArmasDelPJ().inOrdenMostrar();
		System.out.println();
		
		assertTrue(superH.obtenerArmaMasPotente(false).getPoder() == 3);
		superH.getArmasDelPJ().inOrdenMostrar();
		System.out.println();
		
		assertTrue(superH.obtenerArmaMasPotente(true).getPoder() == 3);
		superH.getArmasDelPJ().inOrdenMostrar();
		System.out.println();

		assertTrue(superH.obtenerArmaMasPotente(false).getPoder() == 2);
		superH.getArmasDelPJ().inOrdenMostrar();
		System.out.println();
	}

}
