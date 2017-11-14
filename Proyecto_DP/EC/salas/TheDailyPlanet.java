package salas;

/**
 * Sala especial que contiene a ElHombrePuerta 
 * y desde la cual se puede ganar
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 */
public class TheDailyPlanet extends Sala {
	
	private ElHombrePuerta hp;

	public TheDailyPlanet(int ID_,int cteApertura) {
		super(ID_);
		hp = new ElHombrePuerta(cteApertura);
		
		System.out.println("[!] TheDailyPlanet creado en sala " + ID_ +"." );
	}

	
	/**
	 * Dice si el portal esta abierto o no
	 * true -> abierto
	 * @return
	 */
	public boolean portalAbierto(){
		return hp.isEstado();
	}
	
	
	public ElHombrePuerta getHp() {
		return hp;
	}



	
	

	

}
