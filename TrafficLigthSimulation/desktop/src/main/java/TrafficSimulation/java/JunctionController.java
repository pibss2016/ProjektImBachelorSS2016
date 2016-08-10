package TrafficSimulation.java;
/**
 * Controllerklasse fuer die Kreuzung.
 * Aendert abhaenging von einem internen Counter die
 * Farbe der Ampeln.
 * @author Nikita Maslov
 * @version 1.0
 */
public class JunctionController{
	
	private int counter; // Interner Counter
	int vYellow; // Zeitpunkt zu dem die Ampeln der vertikalen Lanes auf Gelb schalten.
	int vRedANDhGreen;//Zeitpunkt zu dem die Ampeln der vertikalen Lanes auf Rot und die der horizontalen auf Gruen schalten.
	int hYellow;// Zeitpunkt zu dem die Ampeln der horizontalen Lanes auf Gelb schalten.
	int hRedANDvGreen;// Zeitpunkt zu dem die Ampeln der horizontalen Lanes auf Rot und die der vertikalen auf Gruen schalten.
	private JunctionModel Junction;
	
	/**
	 * Konstruktor fuer Klasse JunctionController.
	 * @param Junction Kreuzung die von JunctionController gesteuert wird.
	 */
	public JunctionController(JunctionModel Junction){
		
		this.Junction = Junction;
		counter =1;
		vYellow = 20;
		vRedANDhGreen = 23;
		hYellow = 43;
		hRedANDvGreen = 46;	
	}
	
	/**
	 * Alternativer Konstruktor fuer Klasse JunctionController.
	 * Ermoeglicht individuelle Einstellung der Schaltsequenz der Ampeln.
	 * @param vYellow Zeitpunkt zu dem die Ampeln der vertikalen Lanes auf Gelb schalten.
	 * @param vRedANDhGreen Zeitpunkt zu dem die Ampeln der vertikalen Lanes auf Rot und die der horizontalen auf Gruen schalten.
	 * @param hYellow Zeitpunkt zu dem die Ampeln der horizontalen Lanes auf Gelb schalten.
	 * @param hRedANDvGreen Zeitpunkt zu dem die Ampeln der horizontalen Lanes auf Rot und die der vertikalen auf Gruen schalten.
	 * @param Junction Kreuzung die von JunctionController gesteuert wird.
	 */
	public JunctionController(JunctionModel Junction,int vYellow,int vRedANDhGreen,int hYellow,int hRedANDvGreen){
		
		this.Junction = Junction;
		counter =1;
		
		this.vYellow = vYellow;
        this.vRedANDhGreen = vRedANDhGreen;
        this.hYellow = hYellow;
        this.hRedANDvGreen = hRedANDvGreen; 
	}
			
	/**
	 * Methode um JunctionController 1 Iteration der Schaltsequenz durchlaufen zu lassen.
	 * Dabei werden je nach Wert des internen Counters die Ampeln der Kreuzung umgeschaltet.
	 */
	public void runJunction(){
		// counter nach Durchlauf der Schaltsequnz zuruecksetzen
		if (counter  == hRedANDvGreen +1){
			counter = 1;
		}
		//Ampeln der horizontalen Lanes auf Rot und die der vertikalen auf Gruen schalten.
		if (counter == 1){
			this.Junction.setNorthTrafficLigthColor(TrafficLigthColor.GREEN);
			this.Junction.setSouthTrafficLigthColor(TrafficLigthColor.GREEN);
			this.Junction.setWestTrafficLigthColor(TrafficLigthColor.RED);
			this.Junction.setEastTrafficLigthColor(TrafficLigthColor.RED);
			counter++;
			return;
		}
		
		 //Ampeln der vertikalen Lanes auf Gelb schalten
		if (counter == vYellow +1){
			this.Junction.setNorthTrafficLigthColor(TrafficLigthColor.YELLOW);
			this.Junction.setSouthTrafficLigthColor(TrafficLigthColor.YELLOW);	
			counter++;
			return;
		}
		
		//Ampeln der vertikalen Lanes auf Rot und die der horizontalen auf Gruen schalten.
		if (counter == vRedANDhGreen +1){
			this.Junction.setNorthTrafficLigthColor(TrafficLigthColor.RED);
			this.Junction.setSouthTrafficLigthColor(TrafficLigthColor.RED);
			this.Junction.setWestTrafficLigthColor(TrafficLigthColor.GREEN);
			this.Junction.setEastTrafficLigthColor(TrafficLigthColor.GREEN);	
			counter++;
			return;
		}
		//Ampeln der horizontalen Lanes auf Gelb schalten
		if (counter == hYellow +1){
			this.Junction.setWestTrafficLigthColor(TrafficLigthColor.YELLOW);
			this.Junction.setEastTrafficLigthColor(TrafficLigthColor.YELLOW);
			counter++;
			return;	
		}	
		counter++;								
	}	
}