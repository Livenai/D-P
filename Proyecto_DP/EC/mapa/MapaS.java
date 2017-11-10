package mapa;



import armas.Arma;
import personaje.SuperHeroe;
import personaje.Villano;

/**
 * Clase principal que se encarga de inicializar 
 * el sistema y de gestionar la simulacion.
 * @author CARLOS MU�OZ ZAPATA
 * GIIIC
 *
 */
public class MapaS {

	// Atributos
	private static Mapa Tablero; //unico
	//----------
	
	//TODO encapsular el singeltone y hacerlo puro para que no haya posibilidad de crear otro mapa
	//para esto el constructor tiene que ser privado y fusionar mapa y mapaS

	/**
	 * Crea el mapa si este no ha sido creado. Posteriormente lo devuelve.
	 * Default
	 * @return Tablero. (Mapa)
	 */
	public Mapa getMapa(){
		if(Tablero == null){//Creacion del mapa si no esta creado ya.
			Tablero = new Mapa(); 
		}
		return Tablero;
	}
	
	/**
	 * Crea el mapa si este no ha sido creado. Posteriormente lo devuelve.
	 * Parametrizado
	 * @return Tablero. (Mapa)
	 */
	public Mapa getMapa(int x, int y, int shp,int cteApertura){
		if(Tablero == null){//Creacion del mapa si no esta creado ya.
			Tablero = new Mapa(x,y,shp,cteApertura); 
		}
		return Tablero;
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {//--------------------------------------------------------------------------------------
		MapaS grand = new MapaS();
		
		Mapa accesoMapa = grand.getMapa(6,6,35,2);
		
		// insertamos las armas en el mapa
		insertarArmas();
		
		//creamos unos cuantos personajes:
        //con armas potentes que si no no hacen nada en la simulacion :p

        SuperHeroe thor = new SuperHeroe("Thor", 'T',35);
        thor.insertarArma(new Arma("Capa",11));

        SuperHeroe ironMan = new SuperHeroe("IronMan", 'I',35);
        ironMan.insertarArma(new Arma("Armadura",15));


        SuperHeroe storm = new SuperHeroe("Storm", 'S',35);
        storm.insertarArma(new Arma("CampoMagnetico",20));

        SuperHeroe captainAmerica = new SuperHeroe("Capitan Am�rica", 'C',35);
        captainAmerica.insertarArma(new Arma("Escudo",18));
        
        SuperHeroe cadenaDeFuegoSaltarina = new SuperHeroe("Cadena De Fuego Saltarina", 'F',35);
        cadenaDeFuegoSaltarina.insertarArma(new Arma("CadenaFuego",14));


        Villano deadPool = new Villano("Dead Pool", 'D', new Arma ("Sable",23),35);
    

        Villano kurtConnors = new Villano("Kurt Connors", 'K', new Arma ("CampoEnergia",24),35);
     

        Villano nebula = new Villano("Nebula", 'N', new Arma ("RayoEnergia",30),35);
        
        Villano octopus = new Villano("Octopus", 'O', new Arma ("Garra",15),35);
        
        Villano duendeVerde = new Villano("DuendeVerde", 'O', new Arma ("Acido",19),35);
        
        //set de villanos estandar para que abran el portal de una vez por todas :/
        
        Villano V1 = new Villano("V1", '1', new Arma ("Acido",19),35);
        Villano V2 = new Villano("V2", '2', new Arma ("Acido",19),35);
        Villano V3 = new Villano("V3", '3', new Arma ("Acido",19),35);
        Villano V4 = new Villano("V4", '4', new Arma ("Acido",19),35);
        Villano V5 = new Villano("V5", '5', new Arma ("Acido",19),35);
        Villano V6 = new Villano("V6", '6', new Arma ("Acido",19),35);
        Villano V7 = new Villano("V7", '7', new Arma ("Acido",19),35);


     

		
		//los insertamos
		accesoMapa.insertarPJ(35,thor);
		accesoMapa.insertarPJ(35,ironMan);
		accesoMapa.insertarPJ(35,storm);
		accesoMapa.insertarPJ(35,captainAmerica);
		accesoMapa.insertarPJ(35,cadenaDeFuegoSaltarina);
		accesoMapa.insertarPJ(35,deadPool);
		accesoMapa.insertarPJ(35,kurtConnors);
		accesoMapa.insertarPJ(35,nebula);
		accesoMapa.insertarPJ(35,octopus);
		accesoMapa.insertarPJ(35,duendeVerde);
		
		accesoMapa.insertarPJ(35,V1);
		accesoMapa.insertarPJ(35,V2);
		accesoMapa.insertarPJ(35,V3);
		accesoMapa.insertarPJ(35,V4);
		accesoMapa.insertarPJ(35,V5);
		accesoMapa.insertarPJ(35,V6);
		accesoMapa.insertarPJ(35,V7);
		
		
		accesoMapa.mostrarMapa();
		accesoMapa.mostrarSalasConArmas();
		
		//iniciamos la simulacion
		
		iniciarSimulacion();

	}//fin del MAIN-----------------------------------------------------------------------------------------------------------------


	// metodos auxiliares de la clase:
	
	
	/**
	 * Metodo que genera e inserta las llaves en las salas
	 */
	private static void insertarArmas() {
		// Al principio se generan estas armas y se insertan en las salas:
		
		int[] idSalasConArmas = {1, 2, 8, 14, 15, 21, 27, 35, 28, 29, 33, 34};
		
        Arma [] armasSalas = {new Arma("Mjolnir",29), new Arma("Anillo",1), new Arma("Garra",27), 
                new Arma("Armadura",3), new Arma("Red",25), new Arma("Escudo",5), 
                new Arma("Lucille",23), new Arma("Lawgiver",7), new Arma("GuanteInfinito",21), 
                new Arma("LazoVerdad",9), new Arma("CadenaFuego",19), new Arma("Capa",11), 
                new Arma("Flecha",17), new Arma("Tridente",13), new  Arma("Antorcha",15), 
                new Arma("Baston",28), new Arma("Latigo",2), new  Arma("MazaOro",26), 
                new Arma("CampoMagnetico",4), new Arma("Tentaculo",24), 
                new Arma("CampoEnergia",6), new Arma("Cetro",22), new Arma("RayoEnergia",8), 
                new Arma("Laser",20), new Arma("Bola",10), new Arma("Espada",18), 
                new Arma("Sable",12),  new Arma("Acido",16), new Arma("Gema",14), 
                new Arma("Nullifier",23), new Arma("Mjolnir",1), new Arma("Anillo",29), 
                new Arma("Garra",3), new Arma("Armadura",27),  new Arma("Red",5), 
                new Arma("Escudo",25), new Arma("Lucille",7), new  Arma("Lawgiver",23), 
                new Arma("GuanteInfinito",9), new Arma("LazoVerdad",21), 
                new Arma("CadenaFuego",11), new Arma("Capa",19), new Arma("Flecha",13), 
                new Arma("Tridente",17), new Arma("Antorcha",28), new Arma("Baston",15), 
                new Arma("Latigo",26), new Arma("MazaOro",2), new Arma("CampoMagnetico",24), 
                new Arma("Tentaculo",4), new Arma("CampoEnergia",22), new Arma("Cetro",6), 
                new Arma("RayoEnergia",20), new Arma("Laser",8), new Arma("Bola",18), 
                new Arma("Espada",10), new Arma("Sable",16), new Arma("Acido",12), 
                new Arma("Gema",1), new Arma("Nullifier",3)};

		
		for (int i = 0; i < idSalasConArmas.length; i++) {
			int idSala = idSalasConArmas[i];
			int H = Tablero.getAncho();
			for (int j=i*5 ; j < (i*5)+5; j++) {
				Tablero.getMapita()[idSala/H][idSala%H].insertarArmaEnOrdenDePoder(armasSalas[j]);
			}
		}
	}//-----
	
	
	/**
	 * Metodo que inicia la simulacion del programa.
	 * 
	 * CUIDADO -> este metodo inicia la simulacion, pero noprepara todo
	 * lo que hay que hacer antes de que esta se inicie. Si la preparacion previa
	 *  no se ha hecho correctamente, este metodo no deberia ser utiizado, pues 
	 *  no se puede asegurar su correcto funcionamiento. 
	 */
	private static void iniciarSimulacion() {
		System.out.println("\n----Iniciando Simulacion----\n");
		System.out.println("esta simulacion es de prueba y solo realiza una interaccion \n"
				+ "de lo que seria un turno con todos los pj en TheDailyPlanet \n\n");
		
		
		for (int i = 0; i < Tablero.getSalaHombrePuerta().getPJDentro().obtenerTam(); i++) {
			Tablero.getSalaHombrePuerta().getPJDentro().obtenerElemento(i).interactuarPuerta();
		}
	}
	
	
	
	
	
}
