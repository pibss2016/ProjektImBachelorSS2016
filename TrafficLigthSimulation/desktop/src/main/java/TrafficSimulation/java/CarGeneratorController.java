package TrafficSimulation.java;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Klasse fuer Autogenerierung.
 * Erzeugt Autos mit zufaelliger Farbe, zufaelliger Herkunft
 * und zufaelligem Reiseziel und zufaelliger Geschwindigkeit.
 * @author Nikita Maslov
 * @version 1.0
 */
public class CarGeneratorController {
	
	private CentralModel model; // Referenz auf zentralles Datenmodell mit dem das erzeugte Auto interagieren soll
	private ConcurrentLinkedQueue<CarController> carQueueNorth; //Referenz auf Schlange fuer Einfahrt auf noerdliche Lane
	private ConcurrentLinkedQueue<CarController> carQueueSouth;//Referenz auf Schlange fuer Einfahrt auf suedliche Lane
	private ConcurrentLinkedQueue<CarController> carQueueWest;//Referenz auf Schlange fuer Einfahrt auf westliche Lane
	private ConcurrentLinkedQueue<CarController> carQueueEast;//Referenz auf Schlange fuer Einfahrt auf oestliche Lane
		
	public static final int FIRST_THIRD=34; //1. Drittel von 100 (:= Wertebereich, aus dem Zufallswerte generiert werden)
	public static final int SEC_THIRD= 67; //2. Drittel
	public static final int THIRD_THIRD = 100; //3. Drittel
	/**
	 * Konstruktor für Klasse CarGeneratorController.
	 * @param model Referenz auf zentralles Datenmodell mit dem das erzeugte Auto interagieren soll
	 * @param carQueueNorth Schlange fuer Einfahrt auf noerdliche Lane
	 * @param carQueueSouth Schlange fuer Einfahrt auf suedliche Lane
	 * @param carQueueWest Schlange fuer Einfahrt auf westliche Lane
	 * @param carQueueEast Schlange fuer Einfahrt auf oestliche Lane
	 */
	public CarGeneratorController (CentralModel model,ConcurrentLinkedQueue<CarController> carQueueNorth,ConcurrentLinkedQueue<CarController> carQueueSouth,
			ConcurrentLinkedQueue<CarController> carQueueWest,ConcurrentLinkedQueue<CarController> carQueueEast){
	
		this.carQueueNorth= carQueueNorth;
		this.carQueueSouth= carQueueSouth;
		this.carQueueWest= carQueueWest;
		this.carQueueEast= carQueueEast;
		this.model = model;
	}
	
	//Erzeugt zufaellige Farbe
	private CarColor generateRandomCarColor(){
		
		long i = (Math.round(Math.random()*100));
		
		if(i<20){	
			return CarColor.BLUE;
		}
		if(i<40){
			return CarColor.GREEN;
		}
		if(i<60){
			return CarColor.RED;
		}
		if(i<80){
			return CarColor.YELLOW;
		}
		if(i<=100){
			return CarColor.GRAY;
		}
		return CarColor.BLUE;//wird nie erreicht, eingefuegt damit die Syntaxanalyse nicht rumheult
	}
	
	//Erzeugt zufaellige Herkunft
	private CarOriginDestination generateRandomOrigin(){
		
		long i = (Math.round(Math.random()*100));
		
		if(i<25){		
			return CarOriginDestination.NORTH;
		}
		if(i<50){
			return CarOriginDestination.SOUTH;
		}
		if(i<75){
			return CarOriginDestination.EAST;
		}
		if(i<=100){
			return CarOriginDestination.WEST;
		}
		return CarOriginDestination.NORTH;//wird nie erreicht, eingefuegt damit die Syntaxanalyse nicht rumheult
	}
	
	//Erzeugt zufaelliges Ziel, beruecksichtigt Herkunft
	private CarOriginDestination generateRandomDestination(CarOriginDestination  carOrigin){
		
		long i = (Math.round(Math.random()*100));
		
		switch(carOrigin){
			
		case WEST:
				if(i<FIRST_THIRD){
                    return CarOriginDestination.NORTH;
				}
				
				if(i<SEC_THIRD){
                    return CarOriginDestination.SOUTH;
				}
				
				if(i<=THIRD_THIRD){
                    return CarOriginDestination.EAST;
				}
				break;
			    
			    
		case EAST:
				if(i<FRIST_THIRD){
                    return CarOriginDestination.NORTH;
				}
				
				if(i<SEC_THIRD){
                    return CarOriginDestination.SOUTH;
				}
				
				if(i<=THIRD_THIRD){
                    return CarOriginDestination.WEST;
				}
				break;
			   
		case NORTH:
				
				if(i<FIRST_THIRD){
                    return CarOriginDestination.WEST;
				}
				
				if(i<SEC_THIRD){
                    return CarOriginDestination.SOUTH;
				}
				
				if(i<=THIRD_THIRD){
                    return CarOriginDestination.EAST;
				}
			    break;
			    
		case SOUTH:
				
				if(i<FIRST_THIRD){
                    return CarOriginDestination.NORTH;
				}
				
				if(i<SECOND_THIRD){
                    return CarOriginDestination.WEST;
				}
				
				if(i<=THIRD_THIRD){
                    return  CarOriginDestination.EAST;
				}
			    break; 
		}	
        return CarOriginDestination.NORTH;//wird nie erreicht, eingefuegt damit die Syntaxanalyse nicht rumheult
	}
	
	//Erzeugt zufaellige Geschwindigkeit
	private int generateRandomSpeed(){
		
		long i = (Math.round(Math.random()*100));
		if(i<FIRST_THIRD){
			return 1;
		}
		
		if(i<SEC_THIRD){
			return 2;
		}
		
		if(i<=THIRD_THIRD){

			return 3;
		}
		return 1;//wird nie erreicht, eingefuegt damit die Syntaxanalyse nicht rumheult
	}
		
	/**
	 * Methode um Autos zu generieren.
	 * Erzeugt Autos mit zufaelligen Attributen und weist sie
	 * der richtigen Warteschlange fuer Lanebeitritt zu.
	 */
	public void generateCar(){
		
			// Erzeugt Auto mit zufaeliigen Attributen
			CarColor  carColor= generateRandomCarColor();
			CarOriginDestination carOrigin=generateRandomOrigin();
			CarOriginDestination carDestination=generateRandomDestination(carOrigin);
			int carSpeed = generateRandomSpeed();	
			CarController newCarController = new CarController(carSpeed,model,carColor,carOrigin,carDestination); 
			
			switch(carOrigin){
			
			// Weist Auto abhaengig von der Herkunft der richtigen Warteschlange fuer Lanebeitritt zu.
			case NORTH:
				carQueueNorth.add(newCarController );
				break;
			
			case SOUTH:
				carQueueSouth.add(newCarController );
				break;
			case WEST:
				carQueueWest.add(newCarController );
				break;
			
			case EAST:
				carQueueEast.add(newCarController );
				break;
			}
	}
}
