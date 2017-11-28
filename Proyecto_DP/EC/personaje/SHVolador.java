package personaje;

import java.util.LinkedList;

public class SHVolador extends SuperHeroe {

	public SHVolador(String nombre, char id, int dondeEstoy, int turno) {
		super(nombre, id, dondeEstoy, turno);
		calcularRuta();
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SHVolador [Nombre=" + getNombre() + ", ID=" + getID() + ", Sala=" + getDondeEstoy()
				+ "]";
	}

	@Override
	public void calcularRuta() {
		//de momento las rutas se pre-establecen
		
		LinkedList<Character> aux = new LinkedList<Character>();
		char[] c = {'E','E','E','N','E','E','S'};
		
		for (int i = 0; i < c.length; i++) {
			aux.add(c[i]);
		}
		this.establecerRuta(aux);
	}
	
	

}
