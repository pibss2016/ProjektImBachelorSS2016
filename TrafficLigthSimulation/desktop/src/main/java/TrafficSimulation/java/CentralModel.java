package TrafficSimulation.java;

/**
 * Zentrale Modellklasse.
 * Alle fuer die Visualisierung relevanten
 * Daten sind in dieser Klasse enthalten und
 * koennen mit Get Methoden abgefragt werden.
 * Funtionale Klassen veraendern diese 
 * Klasse.Somit sind die daten immer aktuell.
 *
 * @author Nikita Maslov
 * @version 1.0
 */
public class CentralModel {
	
	private LaneModel southIn; //Lane die aus dem Sueden kommt und in die Kreuzung reinfuehrt.
	private LaneModel northIn; //Lane die aus dem Norden kommt und in die Kreuzung reinfuehrt.
	private LaneModel westIn;  //Lane die aus dem Westen kommt und in die Kreuzung reinfuehrt.
	private LaneModel eastIn;  //Lane die aus dem Osten kommt und in die Kreuzung reinfuehrt.
	private LaneModel southOut;//Lane die die Kreuzung in Richtung Sueden verlaesst.
	private LaneModel northOut;//Lane die die Kreuzung in Richtung Norden verlaesst.
	private LaneModel westOut; //Lane die die Kreuzung in Richtung Westen verlaesst.
	private LaneModel eastOut; //Lane die die Kreuzung in Richtung Osten verlaesst.
	private int laneLength;    //Laenge der Lanes.
	private JunctionModel Junction; // Modell der Kreuzung
	
	
	/**
     * Konstruktor fuer die Klasse CentralModel.
     * @param LaneLength Leange der Ein und Ausgehenden Lanes.
     */
	public CentralModel(int LaneLength){
		
		this.laneLength = LaneLength;
		southIn = new LaneModel(laneLength);
		northIn = new LaneModel(laneLength);
		eastIn = new LaneModel(laneLength);
		westIn = new LaneModel(laneLength);
		southOut = new LaneModel(laneLength);
		northOut = new LaneModel(laneLength);
		eastOut = new LaneModel(laneLength);
		westOut= new LaneModel(laneLength);
		
		eastOut.setJunctionPosition(JunctionPosition.START);
		westOut.setJunctionPosition(JunctionPosition.START);
		southOut.setJunctionPosition(JunctionPosition.START);
		northOut.setJunctionPosition(JunctionPosition.START);
		
		Junction = new JunctionModel();
		
	}
		
	/**
     * Methode die das Modell der Kreuzung ausgibt.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Modell der Kreuzung
     */
	public JunctionModel getJunction(){
		return Junction;
	}
	
	/**
     * Get Methode fuer Lane die aus dem Norden kommt und in die Kreuzung reinfuehrt.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Lane die aus dem Norden kommt und in die Kreuzung reinfuehrt
     */
	public LaneModel getNorthIn(){
		return northIn;
	}
	
	/**
     * Get Methode fuer Lane die aus dem Sueden kommt und in die Kreuzung reinfuehrt.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Lane die aus dem Sueden kommt und in die Kreuzung reinfuehrt
     */
	public LaneModel getSouthIn(){
		return southIn;
	}
	
	/**
     * Get Methode fuer Lane die aus dem Osten kommt und in die Kreuzung reinfuehrt.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Lane die aus dem Osten kommt und in die Kreuzung reinfuehrt
     */
	public LaneModel getEastIn(){
		return eastIn;
	}
	
	/**
     * Get Methode fuer Lane die aus dem Westen kommt und in die Kreuzung reinfuehrt.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Lane die aus dem Westen kommt und in die Kreuzung reinfuehrt
     */
	public LaneModel getWestIn(){
		return westIn;
	}
	
	/**
     * Get Methode fuer Lane die die Kreuzung in Richtung Norden verlaesst.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Lane die die Kreuzung in Richtung Norden verlaesst
     */
	public LaneModel getNorthOut(){
		return northOut;
	}
	
	/**
     * Get Methode fuer Lane die die Kreuzung in Richtung Sueden verlaesst.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Lane die die Kreuzung in Richtung Sueden verlaesst
     */
	public LaneModel getSouthOut(){
		return southOut;
	}
	
	/**
     * Get Methode fuer Lane die die Kreuzung in Richtung Osten verlaesst.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Lane die die Kreuzung in Richtung Osten verlaesst
     */
	public LaneModel getEastOut(){
		return eastOut;
	}
	
	/**
     * Get Methode fuer Lane die die Kreuzung in Richtung Westen verlaesst.
     * Nicht relevant fuer Visualisierung.
     * 
     * @return Lane die die Kreuzung inr Richtung Westen verlaesst
     */
	public LaneModel getWestOut(){
		return westOut;
	}
	
	/**
     * Get Methode fuer Laenge der Lanes.
     * Relevant für Visualisierung.
     * 
     * @return Laenge der Lanes
     */
	public int getLaneLength(){
		return laneLength;
	}
	
	
	/**
     * Gibt die 4 Felder der Kreuzng als char[1][1] Matrix aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Laenge der Lanes
     */
	public char[][] getViewJunctionField(){
		return Junction.getJunctionField();
	}
	
	/**
	 * Gibt Feld mit Ausrichtungen der Autos an der Kreuzung aus. 
     * @return Laenge der Lanes
     */
	public CarDirection[][] getViewDirectionField(){
		return Junction.getDirectionField();
	}
	
	/**
     * Gibt Lane die aus dem Norden kommt und in die Kreuzung reinfuehrt aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Lane die aus dem Norden kommt und in die Kreuzung reinfuehrt
     */
	public char[] getViewNorthIn(){
		return northIn.getLane();
	}
	
	/**
     * Gibt Lane die aus dem Sueden kommt und in die Kreuzung reinfuehrt aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Lane die aus dem Sueden kommt und in die Kreuzung reinfuehrt
     */
	public char[] getViewSouthIn(){
		return southIn.getLane();
	}
	
	/**
     * Gibt Lane die aus dem Osten kommt und in die Kreuzung reinfuehrt aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Lane die aus dem Osten kommt und in die Kreuzung reinfuehrt
     */
	public char[] getViewEastIn(){
		return eastIn.getLane();
	}
	
	/**
     * Gibt Lane die aus dem Westen kommt und in die Kreuzung reinfuehrt aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Lane die aus dem Westen kommt und in die Kreuzung reinfuehrt
     */
	public char[] getViewWestIn(){
		return westIn.getLane();
	}
	
	/**
     * Gibt Lane die die Kreuzung in Richtung Norden verlaesst aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Lane die die Kreuzung in Richtung Norden verlaesst
     */
	public char[] getViewNorthOut(){
		return northOut.getLane();
	}
	
	/**
     * Gibt Lane die die Kreuzung in Richtung Sueden verlaesst aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Lane die die Kreuzung in Richtung Sueden verlaesst
     */
	public char[] getViewSouthOut(){
		return southOut.getLane();
	}
	
	/**
     * Gibt Lane die die Kreuzung in Richtung Osten verlaesst aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Lane die die Kreuzung in Richtung Osten verlaesst
     */
	public char[] getViewEastOut(){
		return eastOut.getLane();
	}
	
	/**
     * Gibt Lane die die Kreuzung in Richtung Westen verlaesst aus.
     * Moegliche Werte der Felder: R = Rotes Auto, G = Gruenes Auto,
     * Y = Gelbes Auto, B = Blaues Auto, o = Kein Auto auf Feld.
     * Relevant für Visualisierung.
     * 
     * @return Lane die die Kreuzung in Richtung Westen verlaesst
     */
	public char[] getViewWestOut(){
		return westOut.getLane();
	}
	
	/**
     * Gibt aktuelle Farbe der Noerdlichen Ampel aus.
     * 
     * @return Farbe der Noerdlichen Ampel
     */
	public TrafficLigthColor getViewNorthTrafficLigthColor(){
		return Junction.getNorthTrafficLigthColor();
	}
	
	/**
     * Gibt aktuelle Farbe der Suedlichen Ampel aus.
     * 
     * @return Farbe der Suedlichen Ampel
     */
	public TrafficLigthColor getViewSouthTrafficLigthColor(){
		return Junction.getSouthTrafficLigthColor();
	}
	
	/**
     * Gibt aktuelle Farbe der Oestlichen Ampel aus.
     * 
     * @return Farbe der Oestlichen Ampel
     */
	public TrafficLigthColor getViewEastTrafficLigthColor(){
		return Junction.getEastTrafficLigthColor();
	}
	
	/**
     * Gibt aktuelle Farbe der Westlichen Ampel aus.
     * 
     * @return Farbe der Westlichen Ampel
     */
	public TrafficLigthColor getViewWestTrafficLigthColor(){
		return Junction.getWestTrafficLigthColor();
	}
}