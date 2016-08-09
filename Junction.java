package pck3;
@SuppressWarnings("unused")
public class Junction implements Runnable{
	
	
	private Lane topLane;
	private Lane botLane;
	private Lane leftLane;
	private Lane rightLane;
	private int state;
	char junctionSpace[][] = new char[2][2];
	
	public Junction(Lane topLane,Lane botLane,Lane leftLane,Lane rigthLane ){
		this.topLane = topLane;
		this.botLane = botLane;
		this.leftLane = leftLane;
		this.rightLane = rigthLane;
		topLane.setJunctionAt(1);
		botLane.setJunctionAt(0);
		leftLane.setJunctionAt(0);
		rigthLane.setJunctionAt(1);
		
		state = 0;
		
	}
	
	private synchronized void changeState(){
		
		try {
			if (state==0){
				state =1;
				Thread.sleep(4000);
			}
			else{
				if(state==1){
					state =2;
					Thread.sleep(30000);
				}
				else{
					if(state ==2){
						state = 3;
						Thread.sleep(4000);
					}
					else{
						state = 0;
						Thread.sleep(30000);
					}
				}
			}
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	@Override public void run(){
		while(true){
			changeState();	
		}
	}
}
