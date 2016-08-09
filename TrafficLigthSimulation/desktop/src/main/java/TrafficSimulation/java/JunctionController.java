package TrafficSimulation.java;

public class JunctionController{
	
	private int counter;
	int vYellow;
	int vRedANDhGreen;
	int hYellow;
	int hRedANDvGreen;
	private JunctionModel Junction;
	
	public JunctionController(JunctionModel Junction){
		
		this.Junction = Junction;
		counter =1;
		vYellow = 20;
		vRedANDhGreen = 23;
		hYellow = 43;
		hRedANDvGreen = 46;	
	}
	
	public JunctionController(JunctionModel Junction,int vYellow,int vRedANDhGreen,int hYellow,int hRedANDvGreen){
		
		this.Junction = Junction;
		counter =1;
		
		this.vYellow = vYellow;
        this.vRedANDhGreen = vRedANDhGreen;
        this.hYellow = hYellow;
        this.hRedANDvGreen = hRedANDvGreen; 
	}
			
	public void runJunction(){
		
		if (counter  == hRedANDvGreen +1){
			counter = 1;
		}
		
		if (counter == 1){
			this.Junction.setNorthTrafficLigthColor(TrafficLigthColor.GREEN);
			this.Junction.setSouthTrafficLigthColor(TrafficLigthColor.GREEN);
			this.Junction.setWestTrafficLigthColor(TrafficLigthColor.RED);
			this.Junction.setEastTrafficLigthColor(TrafficLigthColor.RED);
			Junction.setBotSignal(LeftSignal.NONE);
			Junction.setTopSignal(LeftSignal.NONE);
			counter++;
			return;
		}
		
		if (counter == vYellow +1){
			this.Junction.setNorthTrafficLigthColor(TrafficLigthColor.YELLOW);
			this.Junction.setSouthTrafficLigthColor(TrafficLigthColor.YELLOW);	
			counter++;
			return;
		}
		
		if (counter == vRedANDhGreen +1){
			this.Junction.setNorthTrafficLigthColor(TrafficLigthColor.RED);
			this.Junction.setSouthTrafficLigthColor(TrafficLigthColor.RED);
			this.Junction.setWestTrafficLigthColor(TrafficLigthColor.GREEN);
			this.Junction.setEastTrafficLigthColor(TrafficLigthColor.GREEN);	
			Junction.setBotSignal(LeftSignal.NONE);
			Junction.setTopSignal(LeftSignal.NONE);
			counter++;
			return;
		}
		
		if (counter == hYellow +1){
			this.Junction.setWestTrafficLigthColor(TrafficLigthColor.YELLOW);
			this.Junction.setEastTrafficLigthColor(TrafficLigthColor.YELLOW);
			counter++;
			return;	
		}	
		counter++;	
	}	
}