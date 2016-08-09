package pck3;

public class CarGenerator implements Runnable{
	private int counter;
	private int limit;
	Lane lane;
	
	CarGenerator(int limit, Lane lane){
		this.limit = limit;
		this.counter = 0;
		this.lane = lane;
		
	}
	
	@Override public void run(){
		while(counter<=limit){
			Thread c = new Thread(new Car(3, lane,'z'));
			c.start();
			counter = counter +1;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
