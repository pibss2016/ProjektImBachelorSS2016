package TrafficSimulation.java;

public class CarController implements Runnable{

	/**
	 * Zentrale ControllerKlasse für das Auto.
	 * Steuert die Bewegung des Autos innerhalb der 
	 * in der Klasse CentralModel spezifizierten Datenstruktur.
	 *
	 * @author Nikita Maslov
	 * @version 1.0
	 */
    private int speed;//Tempo des Autos
    private CentralModel model;//Referenz auf Zentrales Modell
    private LaneModel lane; // Refrenz auf Lane auf der sich das Auto bewegt
    private int posLength; // Postion des Autos auf der lane
    private CarJunctionState carJunctionState;//Zustand des Autos wenn es auf der Kreuzung ist
    private CarOriginDestination carOrigin;// Herkunft des Autos
    private CarOriginDestination carDestination; // Ziel des Autos
    private LaneState carLaneState; // Zustand des Autos wenn es auf der Lane ist
    private CarState carState; // Uebergeordenter Zustand des Autos
    private int laneLength; // Laenge der Lane auf der sich dad Auto befindet
    private char symbol; // symbol das Auto in die charArray represenation der Lane hineinschreibt
    
    /**
     * Konstruktor für Klasse CarController.
     * @param model Referenz auf Zentrales Modell mit dem das Auto interagiert
     * @param speed Tempo des Autos
     * @param color Farbe des Autos
     * @param carOrigin Herkunft des Autos
     * @param carDestination Ziel des Autos
	 */
    public CarController(int speed, CentralModel model,CarColor color, CarOriginDestination carOrigin, CarOriginDestination carDestination) {
		this.model = model;
		this.speed = speed; 
		this.carState = CarState.INQUEUE;
		this.carOrigin = carOrigin;
		this.carDestination = carDestination;
		this.laneLength = model.getLaneLength()-1;
		
		switch(carOrigin){
			
			case NORTH:
				this.lane = model.getNorthIn();
				break;
			
			case SOUTH:
				this.lane = model.getSouthIn();
				break;
			
			case WEST:
				this.lane = model.getWestIn();
				break;
		
			case EAST:
				this.lane = model.getEastIn();
				break;
		}
		
		switch(color){
		
			case BLUE:
				this.symbol = 'B';
				break;
		
			case YELLOW:
				this.symbol = 'Y';
				break;
		
			case RED:
				this.symbol = 'R';
				break;
	
			case GREEN:
				this.symbol = 'G';
				break;
				
			case GRAY:
				this.symbol = 'X';
				break;
		}
	}
	
    /**
     * Get Methode fuer uebergeordneten Zustand des Autos.
     * @return uebergeordneter Zustand des Autos.
	 */
    public CarState getCarState(){
		return carState;
	}
	
	private boolean handleLeftTurn(CarOriginDestination carOrigin){
			
		boolean turnLeftStatus= false;
		
		switch (carOrigin){
			
			case NORTH:
					
				if(model.getSouthIn().getLane()[laneLength-2]=='o'&&model.getSouthIn().getLane()[laneLength-1]=='o'&&model.getSouthIn().getLane()[laneLength]=='o'
				&&  model.getJunction().getJunctionField()[1][1]=='o' &&  model.getJunction().getJunctionField()[0][1]=='o'){
					turnLeftStatus= true;
					model.getJunction().setNorthSignal(LeftSignal.NONE);
					break;
				}
					
				if(model.getJunction().getNorthSignal()==LeftSignal.NONE&&model.getJunction().getSouthSignal()==LeftSignal.NONE){
					model.getJunction().setNorthSignal(LeftSignal.LEFT);
					break;	
				}
					
				if(model.getJunction().getNorthSignal()==LeftSignal.NONE && model.getJunction().getSouthSignal()==LeftSignal.LEFT ){
					model.getJunction().setNorthSignal(LeftSignal.WAIT);
					break;
				}
					
				if(model.getJunction().getNorthSignal()==LeftSignal.LEFT&& model.getJunction().getSouthSignal()==LeftSignal.WAIT){
					turnLeftStatus= true;
					model.getJunction().setNorthSignal(LeftSignal.NONE);
					break;	
				}
					
				if(model.getJunction().getNorthSignal()==LeftSignal.NONE&& model.getJunction().getSouthSignal()==LeftSignal.WAIT){
					model.getJunction().setNorthSignal(LeftSignal.WAIT);
					break;	
				}
				break;

			case SOUTH:
					
				if(model.getNorthIn().getLane()[laneLength-2]=='o'&&model.getNorthIn().getLane()[laneLength-1]=='o'&&model.getNorthIn().getLane()[laneLength]=='o'
				&&  model.getJunction().getJunctionField()[0][0]=='o' &&  model.getJunction().getJunctionField()[1][0]=='o'){
					turnLeftStatus= true;
					model.getJunction().setSouthSignal(LeftSignal.NONE);
					break;
				}
					
				if(model.getJunction().getNorthSignal()==LeftSignal.NONE&&model.getJunction().getSouthSignal()==LeftSignal.NONE){
					model.getJunction().setSouthSignal(LeftSignal.LEFT);
					break;	
				}
					
				if(model.getJunction().getNorthSignal()==LeftSignal.LEFT && model.getJunction().getSouthSignal()==LeftSignal.NONE ){
					model.getJunction().setSouthSignal(LeftSignal.WAIT);
					break;
				}
					
				if(model.getJunction().getNorthSignal()==LeftSignal.WAIT&& model.getJunction().getSouthSignal()==LeftSignal.LEFT){
					turnLeftStatus= true;
					model.getJunction().setSouthSignal(LeftSignal.NONE);
					break;	
				}
					
				if(model.getJunction().getNorthSignal()==LeftSignal.WAIT&& model.getJunction().getSouthSignal()==LeftSignal.NONE){
					model.getJunction().setSouthSignal(LeftSignal.WAIT);
					break;	
				}
				break;
					
			case WEST:
	
				if(model.getEastIn().getLane()[laneLength-2]=='o'&&model.getEastIn().getLane()[laneLength-1]=='o'&&model.getEastIn().getLane()[laneLength]=='o'
				&&  model.getJunction().getJunctionField()[0][0]=='o' &&  model.getJunction().getJunctionField()[0][1]=='o'){
					turnLeftStatus= true;
					model.getJunction().setWestSignal(LeftSignal.NONE);
					break;
				}
					
				if(model.getJunction().getWestSignal()==LeftSignal.NONE&&model.getJunction().getEastSignal()==LeftSignal.NONE){
					model.getJunction().setWestSignal(LeftSignal.LEFT);
					break;	
				}
					
				if(model.getJunction().getWestSignal()==LeftSignal.NONE && model.getJunction().getEastSignal()==LeftSignal.LEFT ){
					model.getJunction().setWestSignal(LeftSignal.WAIT);
					break;
				}
					
				if(model.getJunction().getWestSignal()==LeftSignal.LEFT&& model.getJunction().getEastSignal()==LeftSignal.WAIT){
					turnLeftStatus= true;
					model.getJunction().setWestSignal(LeftSignal.NONE);
					break;	
				}
					
				if(model.getJunction().getWestSignal()==LeftSignal.NONE&& model.getJunction().getEastSignal()==LeftSignal.WAIT){
					model.getJunction().setWestSignal(LeftSignal.WAIT);
					break;	
				}
				break;
				
			case EAST:
					
				if(model.getWestIn().getLane()[laneLength-2]=='o'&&model.getWestIn().getLane()[laneLength-1]=='o'&&model.getWestIn().getLane()[laneLength]=='o'
				&&  model.getJunction().getJunctionField()[0][0]=='o' &&  model.getJunction().getJunctionField()[1][0]=='o'){
					turnLeftStatus= true;
					model.getJunction().setEastSignal(LeftSignal.NONE);
					break;
				}
					
				if(model.getJunction().getWestSignal()==LeftSignal.NONE&&model.getJunction().getEastSignal()==LeftSignal.NONE){
					model.getJunction().setEastSignal(LeftSignal.LEFT);
					break;	
				}
					
				if(model.getJunction().getWestSignal()==LeftSignal.LEFT && model.getJunction().getEastSignal()==LeftSignal.NONE ){
					model.getJunction().setEastSignal(LeftSignal.WAIT);
					break;
				}
					
				if(model.getJunction().getWestSignal()==LeftSignal.WAIT&& model.getJunction().getEastSignal()==LeftSignal.LEFT){
					turnLeftStatus= true;
					model.getJunction().setEastSignal(LeftSignal.NONE);
					break;	
				}
					
				if(model.getJunction().getWestSignal()==LeftSignal.WAIT&& model.getJunction().getEastSignal()==LeftSignal.NONE){
					model.getJunction().setEastSignal(LeftSignal.WAIT);
					break;	
				}
				break;
				
		}
		return  turnLeftStatus;	
		
	}
	
	/* Prueft, solange das Auto in der Warteschlange fuer Lanebeitritt ist, ob das Auto die Lane betreten 
	kann und schreibt ggf den Zustand von Lane un Auto um*/
	private void checkStartState(){
		
		synchronized(lane.getLane()){
			
			if (lane.getLane()[0]=='o'){
				carState = CarState.ATLANE;
				carLaneState = LaneState.NOMOVE;
				posLength = 0;
				lane.getLane()[0]=symbol;	
			}
		}		
	}

	/*Prueft vor einer Bewegung des Autos auf der Lane ob und wie weit sich das Auto bewegen kann*/
	private void checkLaneSpace(){
		
		synchronized(lane.getLane()){
			int state;
			
			if (posLength == laneLength && lane.getJunctionPostion()== JunctionPosition.START){	
				carLaneState = LaneState.DONE;
                carState = CarState.DONE;
				return;
			}
			
			if (posLength == laneLength && lane.getJunctionPostion()== JunctionPosition.END){	
				carLaneState = LaneState.ATJUNCTION;
                carState = CarState.ATJUNCTION;
                carJunctionState = CarJunctionState.ENTERJUNCTION;
				return;
			}
			
			if(posLength + speed > laneLength &&lane.getJunctionPostion()== JunctionPosition.START){
				carLaneState = LaneState.DONE;
                carState = CarState.DONE;
				return;
			}
			
			
			
			if(lane.getJunctionPostion()== JunctionPosition.END &&posLength + speed-1 > laneLength-3 && lane.getLane()[posLength+1]=='o'){		
				carLaneState = LaneState.MOVEONE;
				return;
				
			}
			else{
				if(lane.getJunctionPostion()== JunctionPosition.END &&posLength + speed > laneLength-3 && lane.getLane()[posLength+1]=='o'){
					carLaneState = LaneState.MOVEONE;
					return;
				}
			}
			
			// 'o' := Feld ist leer/dort befindet sich KEIN Auto
			if(posLength + 1 <= laneLength && lane.getLane()[posLength+1]=='o'){
				
				if(posLength + 2 <= laneLength && lane.getLane()[posLength+2]=='o'){
					
					if(posLength + 3 <= laneLength && lane.getLane()[posLength+3]=='o'){
						state = Math.min(3,speed);
					}
					else{
						state=Math.min(2, speed);
					}	
				}
				else{
					state = Math.min(1, speed);
				}			
			}
			else{
				carLaneState = LaneState.NOMOVE;
				return;
			}
			
			switch(state){
			
				case 3:
					carLaneState = LaneState.MOVETHREE;
					return;
			
				case 2:
					carLaneState = LaneState.MOVETWO;
					return;
			
				default:
					carLaneState = LaneState.MOVEONE;
					return;
			}
		}
	}
    
	/*Regelt das Verhalten des Autos beim Einfahren auf die Kreuzung*/
	// 'o' := Feld ist leer/dort befindet sich KEIN Auto
	private void handleEnterJunction(){
		
		synchronized(model){
			
			switch(carOrigin){
		
				case NORTH:
					
					if (model.getJunction().getNorthTrafficLigthColor()== TrafficLigthColor.GREEN && model.getJunction().getJunctionField()[0][0]=='o'){						
			
						if(carDestination == CarOriginDestination.EAST && handleLeftTurn(CarOriginDestination.NORTH)==false ){
							break;
						}					
						model.getNorthIn().getLane()[posLength]= 'o';
						model.getJunction().getJunctionField()[0][0]= symbol;
						
						switch(carDestination){
						
							case WEST:
								model.getViewDirectionField()[0][0]=CarDirection.SOUTHWEST;
								break;
								
							default:
								model.getViewDirectionField()[0][0]=CarDirection.SOUTH;
								break;
						}
						carJunctionState = CarJunctionState.LEAVERIGTH;
					}
					break;

				case SOUTH:
				
					if (model.getJunction().getSouthTrafficLigthColor()== TrafficLigthColor.GREEN && model.getJunction().getJunctionField()[1][1]=='o'){
					
						if(carDestination == CarOriginDestination.WEST && handleLeftTurn(CarOriginDestination.SOUTH)==false ){
							break;
						}
						model.getSouthIn().getLane()[posLength]= 'o';
						model.getJunction().getJunctionField()[1][1]= symbol;
						
						switch(carDestination){
						
							case EAST:
								model.getViewDirectionField()[1][1]=CarDirection.NORTHEAST;
								break;
							
							default:
								model.getViewDirectionField()[1][1]=CarDirection.NORTH;
								break;
						}
						carJunctionState = CarJunctionState.LEAVERIGTH;
	
					}
					break;
			
				case WEST:
				
					if (model.getJunction().getWestTrafficLigthColor()== TrafficLigthColor.GREEN && model.getJunction().getJunctionField()[1][0]=='o'){
					
						if(carDestination == CarOriginDestination.NORTH && handleLeftTurn(CarOriginDestination.WEST)==false ){
							break;
						}					
						model.getWestIn().getLane()[posLength]= 'o';
						model.getJunction().getJunctionField()[1][0]= symbol;
						
						switch(carDestination){
						
							case SOUTH:
								model.getViewDirectionField()[1][0]=CarDirection.SOUTHEAST;
								break;
						
							default:
								model.getViewDirectionField()[1][0]=CarDirection.EAST;
								break;
						}
						carJunctionState = CarJunctionState.LEAVERIGTH;				
					}
					break;
					
				case EAST:
					
					if (model.getJunction().getEastTrafficLigthColor()== TrafficLigthColor.GREEN && model.getJunction().getJunctionField()[0][1]=='o'){
					
						if(carDestination == CarOriginDestination.SOUTH && handleLeftTurn(CarOriginDestination.EAST)==false ){
							break;
						}					
						model.getEastIn().getLane()[posLength]= 'o';
						model.getJunction().getJunctionField()[0][1]= symbol;
						
						switch(carDestination){
						
							case NORTH:
								model.getViewDirectionField()[0][1]=CarDirection.NORTHWEST;
								break;
					
							default:
								model.getViewDirectionField()[0][1]=CarDirection.WEST;
								break;
						}
						carJunctionState = CarJunctionState.LEAVERIGTH;
					}
					break;
			}		
		}
	}
	
	/*Regelt abhaengig von Herkunft und Ziel des Autos ob das Auto
	 *die Kreuzung nach rechts verlaesst oder geradeaus faehrt*/
	 // 'o' := Feld ist leer/dort befindet sich KEIN Auto
	private void handleLeaveRigth(){
		
		synchronized(model){
			
			switch(carOrigin){
		
				case NORTH:
			
					switch (carDestination){
				
						case WEST:
							
							if(model.getWestOut().getLane()[0]=='o'){
								model.getJunction().getJunctionField()[0][0] = 'o';
								model.getWestOut().getLane()[0] = symbol;
								lane = model.getWestOut();
								posLength=0;
								carState = CarState.ATLANE;
							}
							break;
							
						case EAST:
							
							if(model.getJunction().getJunctionField()[1][0] =='o'){
								model.getJunction().getJunctionField()[0][0] = 'o';
								model.getJunction().getJunctionField()[1][0] = symbol;
								model.getViewDirectionField()[1][0]=CarDirection.SOUTHEAST;	
								carJunctionState = CarJunctionState.LEAVEFORWARD;
							}
							break;
					
						
						default:
							
							if(model.getJunction().getJunctionField()[1][0] =='o'){
								model.getJunction().getJunctionField()[0][0] = 'o';
								model.getJunction().getJunctionField()[1][0] = symbol;
								model.getViewDirectionField()[1][0]=CarDirection.SOUTH;
								carJunctionState = CarJunctionState.LEAVEFORWARD;
							}
							break;
					}
					break;
				
				case SOUTH:	
				
					switch (carDestination){
						
						case EAST:
								
							if(model.getEastOut().getLane()[0]=='o'){
								model.getJunction().getJunctionField()[1][1] = 'o';
								model.getEastOut().getLane()[0] = symbol;				
								lane = model.getEastOut();
								posLength=0;
								carState = CarState.ATLANE;
							}
							break;
							
						case WEST:
							
							if(model.getJunction().getJunctionField()[0][1] =='o'){
								model.getJunction().getJunctionField()[1][1] = 'o';
								model.getJunction().getJunctionField()[0][1] = symbol;
								model.getViewDirectionField()[0][1]=CarDirection.NORTHWEST;	
								carJunctionState = CarJunctionState.LEAVEFORWARD;
							}
							break;	
							
						default:
								
							if(model.getJunction().getJunctionField()[0][1] =='o'){
								model.getJunction().getJunctionField()[1][1] = 'o';
								model.getJunction().getJunctionField()[0][1] = symbol;									
								model.getViewDirectionField()[0][1]=CarDirection.NORTH;
								carJunctionState = CarJunctionState.LEAVEFORWARD; 	
							}								
							break;
					}
					break;
			
				case EAST:	
				
					switch (carDestination){
						
						case NORTH:
								
							if(model.getNorthOut().getLane()[0]=='o'){
								model.getJunction().getJunctionField()[0][1] = 'o';
								model.getNorthOut().getLane()[0] = symbol;
								lane = model.getNorthOut();
								posLength=0;
								carState = CarState.ATLANE;
							}
							break;
							
						case SOUTH:
							
							if(model.getJunction().getJunctionField()[0][0] =='o'){
								model.getJunction().getJunctionField()[0][1] = 'o';
								model.getJunction().getJunctionField()[0][0] = symbol;
								model.getViewDirectionField()[0][0]=CarDirection.SOUTHWEST;	
								carJunctionState = CarJunctionState.LEAVEFORWARD;
							}
							break;
						
						default:
							
							if(model.getJunction().getJunctionField()[0][0] =='o'){
								model.getJunction().getJunctionField()[0][1] = 'o';
								model.getJunction().getJunctionField()[0][0] = symbol;
								carJunctionState = CarJunctionState.LEAVEFORWARD;
								model.getViewDirectionField()[0][0]=CarDirection.WEST;	
							}
							break;
					}
					break;
				
			case WEST:
				
				switch (carDestination){
				
					case SOUTH:
						
						if(model.getSouthOut().getLane()[0]=='o'){
							model.getJunction().getJunctionField()[1][0] = 'o';
							model.getSouthOut().getLane()[0] = symbol;
							lane = model.getSouthOut();
	                        posLength=0;
	                        carState = CarState.ATLANE;
						}
						break;
					
					case NORTH:
						
						if(model.getJunction().getJunctionField()[1][1] =='o'){
							model.getJunction().getJunctionField()[1][0] = 'o';
							model.getJunction().getJunctionField()[1][1] = symbol;
							model.getViewDirectionField()[1][1]=CarDirection.NORTHEAST;	
							carJunctionState = CarJunctionState.LEAVEFORWARD;
						}
						break;
					
					default:
						
						if(model.getJunction().getJunctionField()[1][1] =='o'){
							model.getJunction().getJunctionField()[1][0] = 'o';
							model.getJunction().getJunctionField()[1][1] = symbol;
							model.getViewDirectionField()[1][1]=CarDirection.EAST;	
							carJunctionState = CarJunctionState.LEAVEFORWARD; 
						}
						break;
				}
				break;
			}
		}	
	}
	
	/*Regelt abhaengig von Herkunft und Ziel des Autos ob das Auto
	 *die Kreuzung nach vorne verlaesst oder links abbiegt*/
	 // 'o' := Feld ist leer/dort befindet sich KEIN Auto
	private void handleLeaveForward(){
		
		synchronized(model){
			
			switch(carOrigin){
			
				case NORTH:
		
					switch (carDestination){
			
						case SOUTH:
								
							if(model.getSouthOut().getLane()[0]=='o'){
								model.getJunction().getJunctionField()[1][0] = 'o';
								model.getSouthOut().getLane()[0] = symbol;
								lane = model.getSouthOut();
								posLength=0;
								carState = CarState.ATLANE;
							}
							break;
					
						default:
					
							if(model.getJunction().getJunctionField()[1][1] =='o'){
								model.getJunction().getJunctionField()[1][0] = 'o';
								model.getJunction().getJunctionField()[1][1] = symbol;	
								model.getViewDirectionField()[1][1]=CarDirection.EAST;	
								carJunctionState = CarJunctionState.LEAVELEFT;
								
							}
							break;
					}
					break;
			
				case SOUTH:	
			
					switch (carDestination){
					
						case NORTH:
							
							if(model.getNorthOut().getLane()[0]=='o'){
								model.getJunction().getJunctionField()[0][1] = 'o';
								model.getNorthOut().getLane()[0] = symbol;
								lane = model.getNorthOut();
								posLength=0;
								carState = CarState.ATLANE;
							}
							break;
								
						default:
							if(model.getJunction().getJunctionField()[0][0] =='o'){
								model.getJunction().getJunctionField()[0][1] = 'o';
								model.getJunction().getJunctionField()[0][0] = symbol;
								model.getViewDirectionField()[0][0]=CarDirection.WEST;	
								carJunctionState = CarJunctionState.LEAVELEFT;
							}
							break;
					}
					break;
				
				case EAST:	
			
					switch (carDestination){
					
						case WEST:
							
							if(model.getWestOut().getLane()[0]=='o'){
								model.getJunction().getJunctionField()[0][0] = 'o';
								model.getWestOut().getLane()[0] = symbol;
								lane = model.getWestOut();
								posLength=0;
								carState = CarState.ATLANE;	 
							}
							break;
							
						default:
							if(model.getJunction().getJunctionField()[1][0] =='o'){
								model.getJunction().getJunctionField()[0][0] = 'o';
								model.getJunction().getJunctionField()[1][0] = symbol;
								model.getViewDirectionField()[1][0]=CarDirection.SOUTH;	
								carJunctionState = CarJunctionState.LEAVELEFT;		
							}
							break;
					}
					break;
			
				case WEST:
			
					switch (carDestination){
			
						case EAST:
					
							if(model.getEastOut().getLane()[0]=='o'){
								model.getJunction().getJunctionField()[1][1] = 'o';
								model.getEastOut().getLane()[0] = symbol;
								lane = model.getEastOut();
								posLength=0;
								carState = CarState.ATLANE;
							}
							break;
					
						default:
					
							if(model.getJunction().getJunctionField()[0][1] =='o'){
								model.getJunction().getJunctionField()[1][1] = 'o';
								model.getJunction().getJunctionField()[0][1] = symbol;
								model.getViewDirectionField()[0][1]=CarDirection.NORTH;	
								carJunctionState = CarJunctionState.LEAVELEFT;
							}
							break;
					}
					break;
			}
		}	
	}
	
	/*Regelt nach dem Abbiegen nach Links das Verlassen der Kreuzung*/
	// 'o' := Feld ist leer/dort befindet sich KEIN Auto
	private void handleLeaveLeft(){
		
		synchronized(model){
		
			switch(carDestination){
		
				case NORTH:
					if(model.getNorthOut().getLane()[0]=='o'){
						model.getJunction().getJunctionField()[0][1] = 'o';
						model.getNorthOut().getLane()[0] = symbol;
						lane = model.getNorthOut();
						posLength = 0;
						if(model.getJunction().getEastSignal()==LeftSignal.WAIT){
							model.getJunction().setEastSignal(LeftSignal.LEFT);
						}
						carState = CarState.ATLANE;
					}	
					break;
		
				case SOUTH:
					if(model.getSouthOut().getLane()[0]=='o'){
						model.getJunction().getJunctionField()[1][0] = 'o';
						model.getSouthOut().getLane()[0] = symbol;
						lane = model.getSouthOut();
						posLength = 0;
						if(model.getJunction().getWestSignal()==LeftSignal.WAIT){
							model.getJunction().setWestSignal(LeftSignal.LEFT);
						}
						carState = CarState.ATLANE;
					}
					break;
		
				case EAST:
					if(model.getEastOut().getLane()[0]=='o'){
						model.getJunction().getJunctionField()[1][1] = 'o';
						model.getEastOut().getLane()[0] = symbol;
						lane = model.getEastOut();
						posLength = 0;
						if(model.getJunction().getSouthSignal()==LeftSignal.WAIT){
							model.getJunction().setSouthSignal(LeftSignal.LEFT);
						}
						carState = CarState.ATLANE;
					}
					break;
		
				case WEST:
					if(model.getWestOut().getLane()[0]=='o'){
						model.getJunction().getJunctionField()[0][0] = 'o';
						model.getWestOut().getLane()[0] = symbol;
						lane = model.getWestOut();
						posLength = 0;
						if(model.getJunction().getNorthSignal()==LeftSignal.WAIT){
							model.getJunction().setNorthSignal(LeftSignal.LEFT);
						}
						carState = CarState.ATLANE;
				
					}
					break;		
			}
		}
	}
	
	/*Regelt das Verhalten an der Kreuzung indem abhaengig von 
	 * carJunctionState die richtigen Verhaltensmethoden aufgerufen werden*/
	private void passJunction(){
		
		switch(carJunctionState){
		
			case ENTERJUNCTION:
				handleEnterJunction();
				break;
				
			case LEAVEFORWARD:
				 handleLeaveForward();
				 break;
			
			case LEAVELEFT:
				handleLeaveLeft();
				break;
				
			case LEAVERIGTH:
				handleLeaveRigth();
				break;
		}
	}	
	
	/*Bewegt das Auto abhaengig des In der Methode checkLaneSpace angepassten Attributs carLaneState ueber die Lane,
	 * oder loescht die Markierung des Autos auf der Lane wenn der Zustand DONE ist*/
	 // 'o' := Feld ist leer/dort befindet sich KEIN Auto
	private void move(){
		synchronized(lane.getLane()){
			
			switch(carLaneState){
			
				case DONE:
					
					lane.getLane()[posLength] = 'o';
					break;
				
			
				case MOVETHREE:
					
					lane.getLane()[posLength]='o';
					lane.getLane()[posLength+3]=symbol;;
					posLength = posLength +3;
					break;
					
				case MOVETWO:
					
					lane.getLane()[posLength]='o';
					lane.getLane()[posLength+2]=symbol;;
					posLength = posLength +2;
					break;
					
				case MOVEONE:
					
					lane.getLane()[posLength]='o';
					lane.getLane()[posLength+1]=symbol;;
					posLength = posLength +1;
					break;
					
				default:	
					break;							
			}	
		}
	}
	
	/**
     * run() Methode regelt hier das gesamte Verhalten des Autos indem sie abhaengig vom uebergeordenetn Zustand des 
     * Autos die Methoden aufruft die das Verhalten des Autos in dem jeweiligen Zustand steuern.
	 */
	public void run(){
		
		switch(carState){ 
		
			case DONE:
				break;
		
			case INQUEUE:
				checkStartState();
				break;
		
			case ATLANE:
				checkLaneSpace();
				move();
				if (carState == CarState.ATJUNCTION){
					handleEnterJunction();			
				}
				break;
		
			case ATJUNCTION:
			
				passJunction();
				break;
		}	
	}
}
