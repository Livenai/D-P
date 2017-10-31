package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import armas.Arma;
import salas.Sala;

/**
 * aqui probaremos que funcionan todos
 *  los metodos conflictivos y complicados que ademas no se deberian 
 *  de probar en la simulacion, pues pueden ofrecer resultados inconcluyentes 
 *  si se relacionan con los aciertos/fallos de otras clases.
 * @author CARLOS MUÑOZ ZAPATA
 * GIIIC
 *
 */
public class P_Sala {

	@Test
	public void testInsertarArmaEnOrdenDePoder() {
		//el primer metodo conflictivo de esta clase
		
		Sala sala = new Sala(0);
		
		Arma a = new Arma("A",1);
		Arma b = new Arma("B",2);
		Arma c = new Arma("C",3);
		Arma d = new Arma("D",4);
		Arma e = new Arma("E",5);
		Arma f = new Arma("F",6);
		Arma g = new Arma("G",7);
		
		sala.insertarArmaEnOrdenDePoder(e);
		sala.insertarArmaEnOrdenDePoder(g);
		sala.insertarArmaEnOrdenDePoder(c);
		sala.insertarArmaEnOrdenDePoder(f);
		sala.insertarArmaEnOrdenDePoder(b);
		sala.insertarArmaEnOrdenDePoder(d);
		sala.insertarArmaEnOrdenDePoder(a);
		
		//estado en el que deberia estar la lista de armas de la sala:
		//g, f, e, d, c, b, 
		
		
		assertTrue(sala.getArmasDentro().borrarPrimero().getNombre() == "G");
		assertTrue(sala.getArmasDentro().borrarPrimero().getNombre() == "F");
		assertTrue(sala.getArmasDentro().borrarPrimero().getNombre() == "E");
		assertTrue(sala.getArmasDentro().borrarPrimero().getNombre() == "D");
		assertTrue(sala.getArmasDentro().borrarPrimero().getNombre() == "C");
		assertTrue(sala.getArmasDentro().borrarPrimero().getNombre() == "B");
		assertTrue(sala.getArmasDentro().borrarPrimero().getNombre() == "A");
		
	}

}
