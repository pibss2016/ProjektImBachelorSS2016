package TrafficSimulation.java;

public class CarController implements Runnable{

    private int speed;
    private CentralModel model;
    private LaneModel lane;
    private int posLength;
    private CarJunctionState carJunctionState;
    private CarOriginDestination carOrigin;
    private CarOriginDestination carDestination;
    private LaneState laneState;
    private CarState carState;
    private int laneLength;
    private char symbol;
    
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
		}
	}
	
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
					model.getJunction().setBotSignal(LeftSignal.NONE);
					break;
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.NONE&&model.getJunction().getTopSignal()==LeftSignal.NONE){
					model.getJunction().setBotSignal(LeftSignal.LEFT);
					break;	
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.NONE && model.getJunction().getTopSignal()==LeftSignal.LEFT ){
					model.getJunction().setBotSignal(LeftSignal.WAIT);
					break;
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.LEFT&& model.getJunction().getTopSignal()==LeftSignal.WAIT){
					turnLeftStatus= true;
					model.getJunction().setBotSignal(LeftSignal.NONE);
					break;	
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.NONE&& model.getJunction().getTopSignal()==LeftSignal.WAIT){
					model.getJunction().setBotSignal(LeftSignal.WAIT);
					break;	
				}
				break;
					
			case SOUTH:
					
				if(model.getNorthIn().getLane()[laneLength-2]=='o'&&model.getNorthIn().getLane()[laneLength-1]=='o'&&model.getNorthIn().getLane()[laneLength]=='o'
				&&  model.getJunction().getJunctionField()[0][0]=='o' &&  model.getJunction().getJunctionField()[1][0]=='o'){
					turnLeftStatus= true;
					model.getJunction().setTopSignal(LeftSignal.NONE);
					break;
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.NONE&&model.getJunction().getTopSignal()==LeftSignal.NONE){
					model.getJunction().setTopSignal(LeftSignal.LEFT);
					break;	
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.LEFT && model.getJunction().getTopSignal()==LeftSignal.NONE ){
					model.getJunction().setTopSignal(LeftSignal.WAIT);
					break;
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.WAIT&& model.getJunction().getTopSignal()==LeftSignal.LEFT){
					turnLeftStatus= true;
					model.getJunction().setTopSignal(LeftSignal.NONE);
					break;	
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.WAIT&& model.getJunction().getTopSignal()==LeftSignal.NONE){
					model.getJunction().setTopSignal(LeftSignal.WAIT);
					break;	
				}
				break;
					
			case WEST:
	
				if(model.getEastIn().getLane()[laneLength-2]=='o'&&model.getEastIn().getLane()[laneLength-1]=='o'&&model.getEastIn().getLane()[laneLength]=='o'
				&&  model.getJunction().getJunctionField()[0][0]=='o' &&  model.getJunction().getJunctionField()[0][1]=='o'){
					turnLeftStatus= true;
					model.getJunction().setBotSignal(LeftSignal.NONE);
					break;
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.NONE&&model.getJunction().getTopSignal()==LeftSignal.NONE){
					model.getJunction().setBotSignal(LeftSignal.LEFT);
					break;	
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.NONE && model.getJunction().getTopSignal()==LeftSignal.LEFT ){
					model.getJunction().setBotSignal(LeftSignal.WAIT);
					break;
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.LEFT&& model.getJunction().getTopSignal()==LeftSignal.WAIT){
					turnLeftStatus= true;
					model.getJunction().setBotSignal(LeftSignal.NONE);
					break;	
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.NONE&& model.getJunction().getTopSignal()==LeftSignal.WAIT){
					model.getJunction().setBotSignal(LeftSignal.WAIT);
					break;	
				}
				break;
				
			case EAST:
					
				if(model.getWestIn().getLane()[laneLength-2]=='o'&&model.getWestIn().getLane()[laneLength-1]=='o'&&model.getWestIn().getLane()[laneLength]=='o'
				&&  model.getJunction().getJunctionField()[0][0]=='o' &&  model.getJunction().getJunctionField()[1][0]=='o'){
					turnLeftStatus= true;
					model.getJunction().setTopSignal(LeftSignal.NONE);
					break;
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.NONE&&model.getJunction().getTopSignal()==LeftSignal.NONE){
					model.getJunction().setTopSignal(LeftSignal.LEFT);
					break;	
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.LEFT && model.getJunction().getTopSignal()==LeftSignal.NONE ){
					model.getJunction().setTopSignal(LeftSignal.WAIT);
					break;
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.WAIT&& model.getJunction().getTopSignal()==LeftSignal.LEFT){
					turnLeftStatus= true;
					model.getJunction().setTopSignal(LeftSignal.NONE);
					break;	
				}
					
				if(model.getJunction().getBotSignal()==LeftSignal.WAIT&& model.getJunction().getTopSignal()==LeftSignal.NONE){
					model.getJunction().setTopSignal(LeftSignal.WAIT);
					break;	
				}
				break;
		}
		return  turnLeftStatus;	
		
	}
	
	private void checkStartState(){
		
		synchronized(lane.getLane()){
			
			if (lane.getLane()[0]=='o'){
				carState = CarState.ATLANE;
				laneState = LaneState.NOMOVE;
				posLength = 0;
				lane.getLane()[0]=symbol;	
			}
		}		
	}

	private void checkLaneSpace(){
		
		synchronized(lane.getLane()){
			int state;
			
			if (posLength == laneLength && lane.getJunctionPostion()== JunctionPosition.START&&carState!=CarState.ATJUNCTION){	
				laneState = LaneState.DONE;
                carState = CarState.DONE;
				return;
			}
			
			if (posLength == laneLength && lane.getJunctionPostion()== JunctionPosition.END){	
				laneState = LaneState.ATJUNCTION;
                carState = CarState.ATJUNCTION;
                carJunctionState = CarJunctionState.ENTERJUNCTION;
				return;
			}
			
			if(posLength + 1 <= laneLength && lane.getLane()[posLength+1]=='o'){
				
				if(posLength + 2 <= laneLength-3 && lane.getLane()[posLength+2]=='o'){
					
					if(posLength + 3 <= laneLength-3 && lane.getLane()[posLength+3]=='o'){
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
				laneState = LaneState.NOMOVE;
				return;
			}
			
			switch(state){
			
				case 3:
					laneState = LaneState.MOVETHREE;
					return;
			
				case 2:
					laneState = LaneState.MOVETWO;
					return;
			
				default:
					laneState = LaneState.MOVEONE;
					return;
			}
		}
	}

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
		
	private void handleLeaveLeft(){
		
		synchronized(model){
		
			switch(carDestination){
		
				case NORTH:
					if(model.getNorthOut().getLane()[0]=='o'){
						model.getJunction().getJunctionField()[0][1] = 'o';
						model.getNorthOut().getLane()[0] = symbol;
						lane = model.getNorthOut();
						posLength = 0;
						if(model.getJunction().getTopSignal()==LeftSignal.WAIT&& carOrigin == CarOriginDestination.WEST){
							model.getJunction().setTopSignal(LeftSignal.LEFT);
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
						if(model.getJunction().getBotSignal()==LeftSignal.WAIT&& carOrigin == CarOriginDestination.EAST){
							model.getJunction().setBotSignal(LeftSignal.LEFT);
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
						if(model.getJunction().getTopSignal()==LeftSignal.WAIT&& carOrigin == CarOriginDestination.NORTH){
							model.getJunction().setTopSignal(LeftSignal.LEFT);
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
						if(model.getJunction().getBotSignal()==LeftSignal.WAIT&& carOrigin == CarOriginDestination.SOUTH){
							model.getJunction().setBotSignal(LeftSignal.LEFT);
						}
						carState = CarState.ATLANE;
				
					}
					break;		
			}
		}
	}
	
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
	
	private void move(){
		synchronized(lane.getLane()){
			
			switch(laneState){
			
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