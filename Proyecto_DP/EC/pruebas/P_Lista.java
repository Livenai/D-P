package pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import contenedores.Lista;

/**
 * Clase que prueba los metodos y el correcto
 *  funcionamiento de la clase Lista de implementacion propia.
 * @author CARLOS MUÑOZ ZAPATA
 * GIIIC
 *
 */
public class P_Lista {
	//datos de la prueba
	Lista<String> A;
	Lista<String> B;
	String dato1;
	String dato2;
	String dato3;
	
	@Before
	public void preparativos(){
		//lo que hacemos antes de cada test
		A = new Lista<String>();
		B = new Lista<String>();
		dato1 = "dato_1";
		dato2 = "dato_2";
		dato3 = "dato_3";
	}


	@Test
	public void testInsertarPrimero() {
		A.insertarPrimero(dato1);
		assertTrue(A.obtenerPrimero().toString() == "dato_1");
		A.insertarPrimero(dato2);
		assertTrue(A.obtenerPrimero().toString() == "dato_2");
		assertTrue(A.obtenerUltimo().toString() == "dato_1");
		A.insertarPrimero(dato3);
		assertTrue(A.obtenerPrimero().toString() == "dato_3");
	}

	
	@Test
	public void testInsertarUltimo() {
		A.insertarUltimo(dato1);
		assertTrue(A.obtenerUltimo().toString() == "dato_1");
		A.insertarUltimo(dato2);
		assertTrue(A.obtenerUltimo().toString() == "dato_2");
		assertTrue(A.obtenerPrimero().toString() == "dato_1");
		A.insertarUltimo(dato3);
		assertTrue(A.obtenerUltimo().toString() == "dato_3");
	}

	@Test
	public void testObtenerPrimero() {
		A.insertarPrimero(dato1);
		assertTrue(A.obtenerPrimero().toString() == "dato_1");
		A.insertarPrimero(dato2);
		assertTrue(A.obtenerPrimero().toString() == "dato_2");
		A.insertarPrimero(dato3);
		assertTrue(A.obtenerPrimero().toString() == "dato_3");
	}

	@Test
	public void testObtenerUltimo() {
		A.insertarUltimo(dato1);
		assertTrue(A.obtenerUltimo().toString() == "dato_1");
		A.insertarUltimo(dato2);
		assertTrue(A.obtenerUltimo().toString() == "dato_2");
		A.insertarUltimo(dato3);
		assertTrue(A.obtenerUltimo().toString() == "dato_3");
	}

	@Test
	public void testBorrarPrimero() {
		A.insertarPrimero(dato1);
		A.insertarPrimero(dato2);
		A.insertarPrimero(dato3);
		//borramos
		assertTrue(A.borrarPrimero().toString() == "dato_3");
		assertTrue(A.obtenerPrimero().toString() != "dato_3");
		
		assertTrue(A.borrarPrimero().toString() == "dato_2");
		assertTrue(A.obtenerPrimero().toString() != "dato_2");
		
		assertTrue(A.borrarPrimero().toString() == "dato_1");
		assertTrue(A.obtenerPrimero() == null);
	}

	@Test
	public void testBorrarUltimo() {
		A.insertarPrimero(dato1);
		A.insertarPrimero(dato2);
		A.insertarPrimero(dato3);
		//borramos
		assertTrue(A.borrarUltimo().toString() == "dato_1");
		assertTrue(A.obtenerUltimo().toString() != "dato_1");
		
		assertTrue(A.borrarUltimo().toString() == "dato_2");
		assertTrue(A.obtenerUltimo().toString() != "dato_2");
		
		assertTrue(A.borrarUltimo().toString() == "dato_3");
		assertTrue(A.obtenerUltimo() == null);
	}

	@Test
	public void testObtenerElemento() {
		A.insertarPrimero(dato1);
		A.insertarPrimero(dato2);
		A.insertarPrimero(dato3);
		
		assertTrue(A.obtenerElemento(0).toString() == "dato_3");
		assertTrue(A.obtenerElemento(1).toString() == "dato_2");
		assertTrue(A.obtenerElemento(2).toString() == "dato_1");
		
		A.borrarPrimero();
		A.borrarPrimero();
		A.borrarPrimero();
		
		assertTrue(A.obtenerElemento(0) == null);
		assertTrue(A.obtenerElemento(123) == null);
		assertTrue(A.obtenerElemento(-30) == null);
	}

	@Test
	public void testBorrarElemento() {
		A.insertarPrimero(dato1);
		A.insertarPrimero(dato2);
		A.insertarPrimero(dato3);
		
		assertTrue(A.borrarElemento(1).toString() == "dato_2");
		assertTrue(A.borrarElemento(0).toString() == "dato_3");
		assertTrue(A.borrarElemento(0).toString() == "dato_1");
	}

	@Test
	public void testEstaVacia() {
		assertTrue(A.estaVacia() == true);
		A.insertarPrimero(dato1);
		assertTrue(A.estaVacia() == false);
		A.insertarPrimero(dato2);
		A.insertarPrimero(dato3);
		assertTrue(A.estaVacia() == false);
		
		A.borrarPrimero();
		A.borrarPrimero();
		A.borrarUltimo();		
		assertTrue(A.estaVacia() == true);
	}
	
	
	@Test
	public void testOrdenarLista() {
		
		Lista<Integer> B = new Lista<Integer>();		
		Lista<Integer> C;
		B.insertarPrimero(1);
		B.insertarPrimero(4);
		B.insertarPrimero(56);
		B.insertarPrimero(3);
		B.insertarPrimero(-9);
		
		C = B.ordenarLista(true);
		assertTrue(C.borrarPrimero() == 56);
		assertTrue(C.borrarPrimero() == 4);
		assertTrue(C.borrarPrimero() == 3);
		assertTrue(C.borrarPrimero() == 1);
		assertTrue(C.borrarPrimero() == -9);
		
		C = B.ordenarLista(false);
		assertTrue(C.borrarPrimero() == -9);
		assertTrue(C.borrarPrimero() == 1);
		assertTrue(C.borrarPrimero() == 3);
		assertTrue(C.borrarPrimero() == 4);
		assertTrue(C.borrarPrimero() == 56);
	}
	
	
	@Test
	public void testInsertarObtenerElemento(){
		A.insertarUltimo("0");
		A.insertarUltimo("1");
		A.insertarUltimo("2");
		A.insertarUltimo("3");
		A.insertarUltimo("4");
		A.insertarUltimo("5");
		A.insertarUltimo("6");
		
		
		A.insertarElemento("X1", 0);
		A.insertarElemento("X2", 8);
		A.insertarElemento("X3", 3);
		A.insertarElemento("X4", 7);
		
		
		assertTrue(A.obtenerElemento(0) == "X1");
		assertTrue(A.obtenerElemento(1) == "0");
		assertTrue(A.obtenerElemento(2) == "1");
		assertTrue(A.obtenerElemento(3) == "X3"); // pos 3
		assertTrue(A.obtenerElemento(4) == "2");
		assertTrue(A.obtenerElemento(5) == "3");
		assertTrue(A.obtenerElemento(6) == "4");
		assertTrue(A.obtenerElemento(7) == "X4");
		assertTrue(A.obtenerElemento(8) == "5");
		assertTrue(A.obtenerElemento(9) == "6");
		assertTrue(A.obtenerElemento(10) == "X2");
		
		
		
	}

}
