package TrafficSimulation.java;

import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Zenrtale Stuerungsklasse fuer den logischen Teil der Simulation.
 * Steuert und erzeugt alle anderen im logischen Teil der Simulation genutzten Controller Klassen.
 * 
 * @author Nikita Maslov
 * @version 1.0
 */
public class CentralSimulationController {
	
	private ConcurrentLinkedQueue<CarController> carQueueNorth;  //Schlange fuer Einfahrt auf noerdliche Lane
	private ConcurrentLinkedQueue<CarController> carQueueSouth;  //Schlange fuer Einfahrt auf suedliche Lane
	private ConcurrentLinkedQueue<CarController> carQueueWest;   //Schlange fuer Einfahrt auf westliche Lane
	private ConcurrentLinkedQueue<CarController> carQueueEast;   //Schlange fuer Einfahrt auf oestliche Lane
	
	private Vector<CarController> taskVector; // Vector mit aktiven Auto tasks
	private JunctionController Junction; // JunctionController für das Steuern der Ampel
	private CarGeneratorController carGenerator; // GarGenerator für dad erzeugen von Autos
	
	private int counter; // Interner couter
	private CentralModel model; // Referenz auf zentrales Modell mit dem gearbeitet wird
	private int gapBetweenCarSpawn; //Anzahl Iterationen der Simulation die zwischen der Erzeugung von 2 Autos durchlaufen werden
	private ExecutorService taskExecutor; // Executor fuer das ausfueren der AutoTasks
	
	/**
	 * Konstruktor fuer die Klasse CentralModelController.
	 * @param model Referenz auf zentrales Modell mit dem gearbeitet wird
	 * @param gapBetweenCarSpawn Anzahl Iterationen der Simulation die zwischen der Erzeugung von 2 Autos durchlaufen werden
	 */
	public CentralSimulationController(CentralModel model,int gapBetweenCarSpawn){
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

	/**
	 * Methode die eine Iteration der Simualtion druchfuehrt.
	 * Dazu werden alle anderen an der Simuation beteiligen Methoden auf dem
	 * zenrtalen, per Referrenz uebergeben Model ausgefuert.
	 */
	public void runSimulation() {
		
		
		taskExecutor = Executors.newCachedThreadPool();
		
		Junction.runJunction();	// durchlaufe 1 Iteration der Schaltseuqnz der Ampel.		
		
		if (counter%gapBetweenCarSpawn==0){//Erzeuge Auto(nicht in jeder iteration aktiv, haengt von gapBetweenCarSpawn ab.
				carGenerator.generateCar();	
		}
			
		//nimmt das erste Elment der Warteschlange der oestlichen Lane und verucht es auf die Lane zu bringen
		if(model.getViewEastIn()[0]=='o'){
			if(carQueueEast.peek()!= null){
				taskVector.add(carQueueEast.poll());
			}
		}
		//nimmt das erste Elment der Warteschlange der westlichen Lane und verucht es auf die Lane zu bringen	
		if(model.getViewWestIn()[0]=='o'){
			if(carQueueWest.peek()!= null){
				taskVector.add(carQueueWest.poll());
			}
		}
		//nimmt das erste Elment der Warteschlange der noerdlichen Lane und verucht es auf die Lane zu bringen	
		if(model.getViewNorthIn()[0]=='o'){
			if(carQueueNorth.peek()!= null){
				taskVector.add(carQueueNorth.poll());
			}
		}
		//nimmt das erste Elment der Warteschlange der suedlichen Lane und verucht es auf die Lane zu bringen	
		if(model.getViewSouthIn()[0]=='o'){
			if(carQueueSouth.peek()!= null){
				taskVector.add(carQueueSouth.poll());
			}
		}
			
		//fuehrt alle AutoTaks paralell per Executor aus.	
		for (CarController tmp : taskVector) {
				taskExecutor.execute(tmp);
		}
		taskExecutor.shutdown();//Schaltet executor ab
		
		try {
			taskExecutor.awaitTermination(20,  TimeUnit.MILLISECONDS);//Wartet bis alle Taks erledigt sind oder 20 millisekunden vergehen.
		} catch (InterruptedException e1) {                           // btw wenn der Unirechner dafuer tatsaechlich 20 milisekunden braucht 
			                                                          // ist es Zeit den Schrotthaufen zu entsorgen, mein Rechner schaftt es in unter 1 milisekunde
			e1.printStackTrace();
		}
		
		//Sortiert alle Autos die am Ziel sind aus dem Taksvector aus
		for (int i = 0; i < taskVector.size(); i++) {
			if (taskVector.get(i).getCarState() == CarState.DONE){
				taskVector.remove(i);
			}
		}
		
		counter++;
	}
}
