package TrafficSimulation.java;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CarGeneratorController {
	
	private CentralModel model;
	private ConcurrentLinkedQueue<CarController> carQueueNorth;
	private ConcurrentLinkedQueue<CarController> carQueueSouth;
	private ConcurrentLinkedQueue<CarController> carQueueWest;
	private ConcurrentLinkedQueue<CarController> carQueueEast;
	
	public CarGeneratorController (CentralModel model,ConcurrentLinkedQueue<CarController> carQueueNorth,ConcurrentLinkedQueue<CarController> carQueueSouth,
			ConcurrentLinkedQueue<CarController> carQueueWest,ConcurrentLinkedQueue<CarController> carQueueEast){
	
		this.carQueueNorth= carQueueNorth;
		this.carQueueSouth= carQueueSouth;
		this.carQueueWest= carQueueWest;
		this.carQueueEast= carQueueEast;
		this.model = model;
	}
	
	private CarColor generateRandomCarColor(){
		long i = (Math.round(Math.random()*100));
		if(i<25){	
			return CarColor.BLUE;
		}
		if(i<50){
			return CarColor.GREEN;
		}
		if(i<75){
			return CarColor.RED;
		}
		if(i<=100){
			return CarColor.YELLOW;
		}
		return CarColor.BLUE;
	}
	
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
		return CarOriginDestination.NORTH;
	}
	
	private CarOriginDestination generateRandomDestination(CarOriginDestination  carOrigin){
		
		long i = (Math.round(Math.random()*100));
		
		switch(carOrigin){
			
		case WEST:
				if(i<34){
                    return CarOriginDestination.NORTH;
				}
				
				if(i<67){
                    return CarOriginDestination.SOUTH;
				}
				
				if(i<=100){
                    return CarOriginDestination.EAST;
				}
				break;
			    
			    
		case EAST:
				if(i<34){
                    return CarOriginDestination.NORTH;
				}
				
				if(i<67){
                    return CarOriginDestination.SOUTH;
				}
				
				if(i<=100){
                    return CarOriginDestination.WEST;
				}
				break;
			   
		case NORTH:
				
				if(i<34){
                    return CarOriginDestination.WEST;
				}
				
				if(i<67){
                    return CarOriginDestination.SOUTH;
				}
				
				if(i<=100){
                    return CarOriginDestination.EAST;
				}
			    break;
			    
		case SOUTH:
				
				if(i<34){
                    return CarOriginDestination.NORTH;
				}
				
				if(i<67){
                    return CarOriginDestination.WEST;
				}
				
				if(i<=100){
                    return  CarOriginDestination.EAST;
				}
			    break; 
		}	
        return CarOriginDestination.NORTH;
	}
	
	private int generateRandomSpeed(){
		
		long i = (Math.round(Math.random()*100));
		if(i<34){
			return 1;
		}
		
		if(i<67){
			return 2;
		}
		
		if(i<=100){

			return 3;
		}
		return 1;
	}
		
	public void generateCar(){
		
			CarColor  carColor= generateRandomCarColor();
			CarOriginDestination carOrigin=generateRandomOrigin();
			CarOriginDestination carDestination=generateRandomDestination(carOrigin);
			int carSpeed = generateRandomSpeed();	
			CarController newCarController = new CarController(carSpeed,model,carColor,carOrigin,carDestination); 
			
			switch(carOrigin){
				
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