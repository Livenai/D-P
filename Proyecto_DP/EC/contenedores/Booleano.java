package contenedores;

/**
 * Esta clase solo sirve para poder pasar un booleano por parametro realizando la copia del puntero y no del valor.
 * De esta menera se consigue que el valor se comparta en una llamada recursiva, asi como sus cambios.
 * @author CARLOS MZ
 *
 */
public class Booleano {
	public Boolean b;
}