package armas;


/**
 * Clase que representa las armas utilizadas en la simulacion.
 * 
 * Esta clase define que dos armas son iguales si se llaman 
 * igual, aunque tengan distinto poder.
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 *
 */
public class Arma implements Comparable<Arma>{
	// Atributos
	private String Nombre;
	private Integer Poder;
	//----------
	
	
	/**
	 * getter
	 * @return Nombre (String)
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * getter
	 * @return Poder (int)
	 */
	public int getPoder() {
		return Poder;
	}
	/**
	 * ctor.
	 * @param nombre
	 * @param poder
	 */
	public Arma(String nombre, int poder) {
		Nombre = nombre;
		Poder = poder;
	}
	
	/**
	 * muestra el arma con formato "(nombre,poder)"
	 * sin \n
	 */
	public void mostrarArma(){
		System.out.print("("+ Nombre + "," + Poder + ")");
	}

	//especiales:

	@Override
	public String toString() {
		return ("("+ Nombre + "," + Poder + ")");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arma other = (Arma) obj;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
//		if (Poder != other.Poder)
//			return false; //ignoramos el poder, pues solo asi podemos decir que dos armas son la misma aunque tengan distinto poder
		return true;
}
	

	@Override
	/**
	 * ordenadas por nombre (alfabetico)
	 */
	public int compareTo(Arma elOtro) {
		return this.Nombre.compareTo(elOtro.Nombre);
	}
	
	
	
	
}
