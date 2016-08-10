package TrafficSimulation.java;

/**
 * Modellklasse fuer Lane.
 * Enthaelt Informationen ueber Laenge der Lanes,
 * Die Postion der Autos auf der Lane und
 * die Position der Ampel.
 * 
 * @author Nikita Maslov
 * @version 1.0
 */
public class LaneModel{

	private char[] lane; // Array mit Position der Autos
	private int length; // Laenge der Lane
	private JunctionPosition junctionPosition; // Position der Kreuzung

	/**
	 * Konstruktor fuer die Klasse LaneModel.
	 * @param length Laenge der Lane
	 */
	public LaneModel(int length){
		this.lane = new char[length];	
		this.length = length;
		for(int i =0;i<length;i++){
			this.lane[i]='o';
		}
		this.junctionPosition = JunctionPosition.END;//Kreuzung standartmaessig am ende der Lane.
		
	}
	
	/**
	 * Get Methode fuer Array mit Positionen der Autos. Bedeutung
	 * der Elemente des Arrays: B = Blaues Auto, G = gruenes Auto,
	 * R = Rotes Auto, y = Gelbes Auto, o = kein Auto.
	 * 
	 * @return Array mit Positionen der Autos.
	 */
	public char[] getLane(){
		return this.lane;	
	}
	
	/**
	 * Get Methode fuer Laenge der Lanes.
	 * 
	 * @return Laenge der Lanes
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * Get Methode fuer Position der Kreuzung.
	 * 
	 * @return Position der Kreuzung
	 */
	public JunctionPosition getJunctionPostion(){
		return junctionPosition;
	}
	
	/**
	 * Set Methode fuer Position der Kreuzung.
	 * 
	 * @param JunctionPosition Position der Kreuzung
	 */
	public void setJunctionPosition(JunctionPosition JunctionPosition){
		this.junctionPosition = JunctionPosition;
	}
}