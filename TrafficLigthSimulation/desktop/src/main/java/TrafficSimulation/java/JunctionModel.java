package TrafficSimulation.java;

public class JunctionModel {
	
	private TrafficLigthColor northTrafficLigthColor;
	private TrafficLigthColor southTrafficLigthColor;
	private TrafficLigthColor westTrafficLigthColor;
	private TrafficLigthColor eastTrafficLigthColor;
	
	private LeftSignal topSignal;
	private LeftSignal botSignal;
	private char[][] JunctionField= {{'o','o'},{'o','o'}};
	private CarDirection[][] directionField= {{CarDirection.NONE,CarDirection.NONE},{CarDirection.NONE,CarDirection.NONE}};
    
	public JunctionModel(int laneLength){
		
		botSignal = LeftSignal.NONE;
		topSignal = LeftSignal.NONE;
		
		northTrafficLigthColor = TrafficLigthColor.GREEN;
		southTrafficLigthColor = TrafficLigthColor.GREEN;
		eastTrafficLigthColor = TrafficLigthColor.RED;
		westTrafficLigthColor = TrafficLigthColor.RED;
		
	}
	
	public LeftSignal getTopSignal(){
		return topSignal;
	}
	
	public LeftSignal getBotSignal(){
		return botSignal;
	}
	
	public void setTopSignal(LeftSignal northSignal ){
		this.topSignal = northSignal;	
	}
	
	public void setBotSignal(LeftSignal southSignal ){
		this.botSignal = southSignal;	
	}
		
	public char[][] getJunctionField(){
		return JunctionField;
	}
	
	public CarDirection[][] getDirectionField(){
		return directionField;
	}
	
	public TrafficLigthColor getNorthTrafficLigthColor(){
		return northTrafficLigthColor;
	}
	
	public TrafficLigthColor getSouthTrafficLigthColor(){
		return southTrafficLigthColor;
	}
	
	public TrafficLigthColor getWestTrafficLigthColor(){
		return westTrafficLigthColor;
	}
	
	public TrafficLigthColor getEastTrafficLigthColor(){
		return eastTrafficLigthColor;
	}
	
	public void setNorthTrafficLigthColor(TrafficLigthColor color){
		this.northTrafficLigthColor = color;
	}
	
	public void setSouthTrafficLigthColor(TrafficLigthColor color){
		this.southTrafficLigthColor = color;
	}
	
	public void setWestTrafficLigthColor(TrafficLigthColor color){
		this.westTrafficLigthColor = color;
	}
	
	public void setEastTrafficLigthColor(TrafficLigthColor color){
		this.eastTrafficLigthColor = color;
	}
}