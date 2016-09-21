package TrafficSimulation.java;

/**
 * Modellklasse fuer die Kreuzung.
 * Enthaelt Informationen ueber die Farbe der 4 Ampeln,
 * den Zustand der LinksAbbiegen Signale, ein Feld das 
 * angibt, wo sich gerade welches Auto auf der Kreuzung befindet und
 * ein Feld das die Ausrichtung aller Autos auf der Kreuzung enthaelt.
 * @author Nikita Maslov
 * @version 1.0
 */
public class JunctionModel {
	
	private TrafficLigthColor northTrafficLigthColor;//Farbe noerdliche Ampel
	private TrafficLigthColor southTrafficLigthColor;//Farbe suedliche Ampel
	private TrafficLigthColor westTrafficLigthColor;//Farbe westliche Ampel
	private TrafficLigthColor eastTrafficLigthColor;//Farbe oestliche Ampel
	
	private LeftSignal northLeftSignal; // Links abbiegen Signal für obere lane
	private LeftSignal southLeftSignal; // Links abbiegen Signale für untere lane
	private LeftSignal westLeftSignal; // Links abbiegen Signal für obere lane
	private LeftSignal eastLeftSignal; // Links abbiegen Signale für untere lane
	
	private char[][] JunctionField= {{'o','o'},{'o','o'}}; // Feld das angibt, welche Autos sich auf der Kreuzung befinden
	private CarDirection[][] directionField= {{CarDirection.NONE,CarDirection.NONE},{CarDirection.NONE,CarDirection.NONE}}; // Feld das die Ausrichtung aller Autos auf der Kreuzung angibt
    
	/**
     * Konstruktor fuer die Klasse JunctionModel.
     */
	public JunctionModel(){
		
		northLeftSignal = LeftSignal.NONE;
		southLeftSignal = LeftSignal.NONE;
		westLeftSignal = LeftSignal.NONE;
		eastLeftSignal = LeftSignal.NONE;
		
		northTrafficLigthColor = TrafficLigthColor.GREEN;
		southTrafficLigthColor = TrafficLigthColor.GREEN;
		eastTrafficLigthColor = TrafficLigthColor.RED;
		westTrafficLigthColor = TrafficLigthColor.RED;
		
	}
	
	/**
     * Get Methode für noerdliches LinksAbbiegen Signal.
     * @return noerdliches LinksAbbiegen Signal
     */
	public LeftSignal getNorthSignal(){
		return northLeftSignal;
	}
	
	/**
     * Get Methode für suedliches LinksAbbiegen Signal.
     * @return suedliches LinksAbbiegen Signal
     */
	public LeftSignal getSouthSignal(){
		return southLeftSignal;
	}
	
	/**
     * Get Methode für westliches LinksAbbiegen Signal.
     * @return westliches LinksAbbiegen Signal
     */
	public LeftSignal getWestSignal(){
		return westLeftSignal;
	}
	
	/**
     * Get Methode für oestliches LinksAbbiegen Signal.
     * @return oestliches LinksAbbiegen Signal
     */
	public LeftSignal getEastSignal(){
		return eastLeftSignal;
	}
	
	/**
     * Set Methode für noerdliches LinksAbbiegen Signal.
     * @param northSignal Signal mit dem noerdliches LinksAbbiegen Signal belegt wird.
     */
	public void setNorthSignal(LeftSignal northSignal ){
		this.northLeftSignal = northSignal;	
	}
	
	/**
     * Set Methode für suedliches LinksAbbiegen Signal.
     * @param southSignal Signal mit dem suedliches LinksAbbiegen Signal belegt wird.
     */
	public void setSouthSignal(LeftSignal southSignal ){
		this.southLeftSignal = southSignal;	
	}
	
	/**
     * Set Methode für westliches LinksAbbiegen Signal.
     * @param westSignal Signal mit dem westliches LinksAbbiegen Signal belegt wird.
     */
	public void setWestSignal(LeftSignal westSignal ){
		this.westLeftSignal = westSignal;	
	}
	
	/**
     * Set Methode für oestliches LinksAbbiegen Signal.
     * @param eastSignal Signal mit dem oestliches LinksAbbiegen Signal belegt wird.
     */
	public void setEastSignal(LeftSignal eastSignal ){
		this.eastLeftSignal = eastSignal;	
	}
	
	
		
	/**
     * Get Methode für das Feld das angibt, welche Autos sich auf der Kreuzung befinden.
     * Bedeutung der Elemnte: B = Blaues Auto, G = Grünes Auto,
     * R = Rotes Auto, Y = Gelbes Auto, X = Graues Auto, o = Kein Auto
     * @return Feld mit Autos auf der Kreuzung
     */
	public char[][] getJunctionField(){
		return JunctionField;
	}
	
	/**
     * Get Methode für Feld, das die Ausrichtung aller Autos auf der Kreuzung angibt.
     * @return Feld mit Ausrichtung der Autos auf der Kreuzung
     */
	public CarDirection[][] getDirectionField(){
		return directionField;
	}
		
	/**
     * Get Methode für Farbe noerdlichen Ampel.
     * @return Farbe noerdlichen Ampel
     */
	public TrafficLigthColor getNorthTrafficLigthColor(){
		return northTrafficLigthColor;
	}
	
	/**
     * Get Methode für Farbe suedlichen Ampel.
     * @return Farbe suedlichen Ampel
     */
	public TrafficLigthColor getSouthTrafficLigthColor(){
		return southTrafficLigthColor;
	}
	
	/**
     * Get Methode für Farbe westlichen Ampel.
     * @return Farbe westlichen Ampel
     */
	public TrafficLigthColor getWestTrafficLigthColor(){
		return westTrafficLigthColor;
	}
	
	/**
     * Get Methode für Farbe oestlichen Ampel.
     * @return Farbe oestlichen Ampel
     */
	public TrafficLigthColor getEastTrafficLigthColor(){
		return eastTrafficLigthColor;
	}
	
	/**
     * Set Methode für Farbe noerdlichen Ampel.
     * @param color Farbe der Ampel
     */
	public void setNorthTrafficLigthColor(TrafficLigthColor color){
		this.northTrafficLigthColor = color;
	}
	
	/**
     * Set Methode für Farbe suedlichen Ampel.
     * @param color Farbe der Ampel
     */
	public void setSouthTrafficLigthColor(TrafficLigthColor color){
		this.southTrafficLigthColor = color;
	}
	
	/**
     * Set Methode für Farbe westlichen Ampel.
     * @param color Farbe der Ampel
     */
	public void setWestTrafficLigthColor(TrafficLigthColor color){
		this.westTrafficLigthColor = color;
	}
	
	/**
     * Set Methode für Farbe oestlichen Ampel.
     * @param color Farbe der Ampel
     */
	public void setEastTrafficLigthColor(TrafficLigthColor color){
		this.eastTrafficLigthColor = color;
	}
}
