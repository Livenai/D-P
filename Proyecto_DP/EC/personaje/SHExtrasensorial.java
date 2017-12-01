package personaje;

import java.util.LinkedList;

public class SHExtrasensorial extends SuperHeroe {

	public SHExtrasensorial(String nombre, char id, int dondeEstoy, int turno) {
		super(nombre, id, dondeEstoy, turno);
		calcularRuta();
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SHExtrasensorial [Nombre=" + getNombre() + ", ID=" + getID() + ", Sala=" + getDondeEstoy()
				+ "]";
	}

	@Override
	public void calcularRuta() {
		//de momento las rutas se pre-establecen
		
		LinkedList<Character> aux = new LinkedList<Character>();
		char[] c = {'E','E','S','W','W','E','S','W','E','N','E','S','E','S','W','W','W','S','E','W','N','E','E','S','N','E','S','S','W','W','W','E','E','E','E','E'};
		
		for (int i = 0; i < c.length; i++) {
			aux.add(c[i]);
		}
		this.establecerRuta(aux);
	}
}
