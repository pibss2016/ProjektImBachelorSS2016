package TrafficSimulation.java;

import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CentralModelController {
	
	private ConcurrentLinkedQueue<CarController> carQueueNorth;
	private ConcurrentLinkedQueue<CarController> carQueueSouth;
	private ConcurrentLinkedQueue<CarController> carQueueWest;
	private ConcurrentLinkedQueue<CarController> carQueueEast;
	
	private Vector<CarController> taskVector;
	private JunctionController Junction;
	private CarGeneratorController carGenerator;
	
	private int counter;
	private CentralModel model;
	private int gapBetweenCarSpawn;
	private ExecutorService taskExecutor;
	
	public CentralModelController(CentralModel model,int gapBetweenCarSpawn){
		counter =0;
		this.model = model;
		carQueueNorth = new ConcurrentLinkedQueue<CarController>(); 
		carQueueSouth = new ConcurrentLinkedQueue<CarController>(); 
		carQueueWest = new ConcurrentLinkedQueue<CarController>(); 
		carQueueEast = new ConcurrentLinkedQueue<CarController>(); 
		taskVector = new Vector<CarController>();
		
		this.gapBetweenCarSpawn = gapBetweenCarSpawn;
		Junction = new JunctionController(this.model.getJunction());
		carGenerator= new CarGeneratorController(this.model,carQueueNorth ,carQueueSouth,carQueueWest,carQueueEast );
	}

	public void runModel() {
		
		
		taskExecutor = Executors.newCachedThreadPool();
		Junction.runJunction();			
		
		if (counter%gapBetweenCarSpawn==0){
				carGenerator.generateCar();	
		}
			
		if(model.getViewEastIn()[0]=='o'){
			if(carQueueEast.peek()!= null){
				taskVector.add(carQueueEast.poll());
			}
		}
			
		if(model.getViewWestIn()[0]=='o'){
			if(carQueueWest.peek()!= null){
				taskVector.add(carQueueWest.poll());
			}
		}
			
		if(model.getViewNorthIn()[0]=='o'){
			if(carQueueNorth.peek()!= null){
				taskVector.add(carQueueNorth.poll());
			}
		}
			
		if(model.getViewSouthIn()[0]=='o'){
			if(carQueueSouth.peek()!= null){
				taskVector.add(carQueueSouth.poll());
			}
		}
			
			
		for (CarController tmp : taskVector) {
				taskExecutor.execute(tmp);
		}
		taskExecutor.shutdown();
		try {
			taskExecutor.awaitTermination(10,  TimeUnit.MILLISECONDS);
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		
		for (int i = 0; i < taskVector.size(); i++) {
			if (taskVector.get(i).getCarState() == CarState.DONE){
				taskVector.remove(i);
			}
		}
		counter++;
	}
}
