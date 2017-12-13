package comparadores;

import java.util.Comparator;
import salas.Sala;

public class OrdenarSalasPorFrecuencia implements Comparator<Sala> {

	@Override
	public int compare(Sala a, Sala b) {
		//1 si a>b -> frec(a)>frec(b)
		Integer frecA = a.getFrecuencia();
		Integer frecB = b.getFrecuencia();
		if(frecA < frecB){
			return 1;
		}else if (frecA > frecB){
			return -1;
		}else{
			return 0;
		}
	}

}
