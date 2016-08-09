package pck3;

public class Car implements Runnable{

    private int speed;
    private Lane lane;
    private char[] laneField;
    private int posLength;
    private int laneState;
    private int laneLength;
    private char symbol;
    

	public Car(int speed, Lane lane,char symbol) {
		this.lane = lane;
		this.speed = speed; 
		this.symbol = symbol;
		this.laneState = 0;
		this.laneLength = lane.getLength()-1;
		this.laneField = lane.getLane();
	}
	
	private void checkStartState(){		
		
		synchronized(laneField){
			
			if (laneField[0]=='o'){
				laneState = 1;
				posLength = 0;
				laneField[0]=symbol;	
			}
		}
				
	}

	private void checkSpace(){
		
		synchronized(laneField){
			
			if (posLength == laneLength && lane.getJunctionAt()== 1){	
				laneState = 9;
				return;
			}
			
			if(posLength + 1 <= laneLength && laneField[posLength+1]=='o'){
				
				if(posLength + 2 <= laneLength && laneField[posLength+2]=='o'){
					
					if(posLength + 3 <= laneLength && laneField[posLength+3]=='o'){
						laneState = Math.min(4,speed+1);
					}
					else{
						laneState=Math.min(3, speed+1);
					}	
				}
				else{
					laneState = Math.min(2, speed+1);
				}			
			}
			else{
				laneState = 1;
			}
		}
	}
	
	private void move(){
		synchronized(laneField){
			
			if (laneState == 9){
				laneField[posLength] = 'o';
			}
			
			if (laneState==4){	

				laneField[posLength]='o';
				laneField[posLength+3]=symbol;;
				posLength = posLength +3;
				return;
			}
		
			if (laneState==3){	
				laneField[posLength]='o';
				laneField[posLength+2]=symbol;;
				posLength = posLength +2;
				return;
			}
		
			if (laneState==2){
				laneField[posLength]='o';
				posLength = posLength +1;
				laneField[posLength]=symbol;
				return;
			}
		
			if (laneState==1){
				return;
			}
		}
	}
	

	@Override public void run(){
		
		if (laneState == 9){
			return;
		}
		if (laneState == 0){
			checkStartState();
		}
		else{	
			checkSpace();
			move();
		}	
	}
}