package TrafficSimulation.java;

public class LaneModel{

	private char[] lane;
	private int length;
	private JunctionPosition junctionPosition;

	public LaneModel(int length){
		this.lane = new char[length];	
		this.length = length;
		for(int i =0;i<length;i++){
			this.lane[i]='o';
		}
		this.junctionPosition = JunctionPosition.END;
		
	}
		
	public char[] getLane(){
		return this.lane;	
	}
	
	public int getLength(){
		return length;
	}
	
	public JunctionPosition getJunctionPostion(){
		return junctionPosition;
	}
	
	public void setJunctionPosition(JunctionPosition JunctionPosition){
		this.junctionPosition = JunctionPosition;
	}
}