package pck3;

public class Lane{

	private char[] lane;
	private int length;
	private int junctionAt;
	private Junction junction;

	public Lane(int length){
		this.lane = new char[length];	
		this.length = length;
		for(int i =0;i<length;i++){
			this.lane[i]='o';
		}
		this.junctionAt = 1;
		
	}
	
	public void setJunction(Junction j){
		this.junction = j;
	}
	
	public Junction getJunction(){
		return this.junction;
	}
	
	
	public char[] getLane(){
		return this.lane;	
	}
	
	public void setJunctionAt(int j){
		this.junctionAt = j;			
	}
	public int getJunctionAt(){
		return this.junctionAt;		
	}
	public int getLength(){
		return length;
	}

}