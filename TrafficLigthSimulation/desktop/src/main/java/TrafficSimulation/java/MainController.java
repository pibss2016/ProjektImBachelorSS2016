package TrafficSimulation.java;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.audio.Sound;

public class MainController extends InputAdapter  implements ApplicationListener {
    
	private int laneLength;
	private int waitingTime;
	private int initialWaitingTime;
	private int carSpawnRate;
    private CentralModel model;
    private CentralSimulationController modelController;
    
    private SimulationSpeed simulationSpeed;
    private BitmapFont font;
    
    private Sound sound;
    
    private SpriteBatch batch;
    private TextureAtlas atlas; 
    
    private TrafficLigthColor northTrafficLigthColor;
    private TrafficLigthColor southTrafficLigthColor;
    private TrafficLigthColor westTrafficLigthColor;
    
    private TextureRegion street;
    
    private TextureRegion blueCarNorth;
    private TextureRegion redCarNorth;
    private TextureRegion greenCarNorth;
    private TextureRegion yellowCarNorth;
    private TextureRegion grayCarNorth;
    
    private TextureRegion blueCarSouth;
    private TextureRegion redCarSouth;
    private TextureRegion greenCarSouth;
    private TextureRegion yellowCarSouth;
    private TextureRegion grayCarSouth;
    
    private TextureRegion blueCarEast;
    private TextureRegion redCarEast;
    private TextureRegion greenCarEast;
    private TextureRegion yellowCarEast;
    private TextureRegion grayCarEast;
    
    private TextureRegion blueCarWest;
    private TextureRegion redCarWest;
    private TextureRegion greenCarWest;
    private TextureRegion yellowCarWest;
    private TextureRegion grayCarWest;
    
    private TextureRegion blueCarNorthWest;
    private TextureRegion redCarNorthWest;
    private TextureRegion greenCarNorthWest;
    private TextureRegion yellowCarNorthWest;
    private TextureRegion grayCarNorthWest;
    
    private TextureRegion blueCarSouthWest;
    private TextureRegion redCarSouthWest;
    private TextureRegion greenCarSouthWest;
    private TextureRegion yellowCarSouthWest;
    private TextureRegion grayCarSouthWest;
    
    private TextureRegion blueCarNorthEast;
    private TextureRegion redCarNorthEast;
    private TextureRegion greenCarNorthEast;
    private TextureRegion yellowCarNorthEast;
    private TextureRegion grayCarNorthEast;
    
    private TextureRegion blueCarSouthEast;
    private TextureRegion redCarSouthEast;
    private TextureRegion greenCarSouthEast;
    private TextureRegion yellowCarSouthEast;
    private TextureRegion grayCarSouthEast;
    
    private TextureRegion northTrafficGreen;
    private TextureRegion northTrafficYellow;
    private TextureRegion northTrafficRed;
    
    private TextureRegion southTrafficGreen;
    private TextureRegion southTrafficYellow;
    private TextureRegion southTrafficRed;
    
    private TextureRegion eastTrafficGreen;
    private TextureRegion eastTrafficYellow;
    private TextureRegion eastTrafficRed;
    
    private TextureRegion westTrafficGreen;
    private TextureRegion westTrafficYellow;
    private TextureRegion westTrafficRed;
    
    public MainController(int laneLength,int waitingTime, int carSpawnRate){
    	this.laneLength = laneLength;
    	this.initialWaitingTime = waitingTime;
    	this.waitingTime = this.initialWaitingTime;
    	this.carSpawnRate = carSpawnRate;	
    	this.simulationSpeed = SimulationSpeed.INITIAL;
    }
    
    private void drawNorthIn(char[]lane){
    	
    	for (int i = 1;i<=laneLength;i++){
    		
    		switch(lane[i-1]){
    		
    		case 'R': batch.draw(redCarSouth, laneLength*32, ((laneLength*2)+2)*32-i*32);
    			  break;
    			  
    		case 'B': batch.draw(blueCarSouth, laneLength*32, ((laneLength*2)+2)*32-i*32);
    			  break;
    			  
    		case 'G': batch.draw(greenCarSouth, laneLength*32, ((laneLength*2)+2)*32-i*32);
			  break;
			  
		case 'Y': batch.draw(yellowCarSouth, laneLength*32, ((laneLength*2)+2)*32-i*32);
			  break;
			  
		case 'X': batch.draw(grayCarSouth, laneLength*32, ((laneLength*2)+2)*32-i*32);
			  break;
			  
		case 'o': batch.draw(street, laneLength*32, ((laneLength*2)+2)*32-i*32);
			  break;
    		}
    		
      	}	
    }

    private void drawSouthIn(char[]lane){
    	
    	for (int i = 0;i<laneLength;i++){
    		
    		if (lane[i]=='R'){ 	
    			batch.draw(redCarNorth, (laneLength+1)*32, 32*i);
    		}		
    		
    		if (lane[i]=='B'){	
    			batch.draw(blueCarNorth, (laneLength+1)*32, 32*i);
    		}

    		if (lane[i]=='G'){ 	
    			batch.draw(greenCarNorth, (laneLength+1)*32, 32*i);
    		}
	
    		if (lane[i]=='Y'){
    			batch.draw(yellowCarNorth, (laneLength+1)*32, 32*i);
    		}
    		
    		if (lane[i]=='X'){
    			batch.draw(grayCarNorth, (laneLength+1)*32, 32*i);
    		}
    		
    		if (lane[i]=='o'){	 	
    			batch.draw(street, (laneLength+1)*32, 32*i);
    		}	
    	}
    }

    private void drawWestIn(char[]lane){
    	
    	for (int i = 0;i<laneLength;i++){
    		
    		if (lane[i]=='R'){ 	
    			batch.draw(redCarEast, i*32, laneLength*32);
    		}

    		if (lane[i]=='B'){ 	
    			batch.draw(blueCarEast, i*32, laneLength*32);
    		}
	
    		if (lane[i]=='G'){ 	
    			batch.draw(greenCarEast, i*32, laneLength*32);
    		}
	
    		if (lane[i]=='Y'){ 	
    			batch.draw(yellowCarEast, i*32, laneLength*32);
    		}
    		
    		if (lane[i]=='X'){ 	
    			batch.draw(grayCarEast, i*32, laneLength*32);
    		}
	
    		if (lane[i]=='o'){ 	
    			batch.draw(street, i*32, laneLength*32);
    		}
    	}
    }

    private void drawEastIn(char[]lane){
    	
    	for (int i = 1;i<=laneLength;i++){
    		
    		if (lane[i-1]=='R'){ 	
    			batch.draw(redCarWest,((laneLength*2)+2-i)*32, (laneLength+1)*32);
    		}

    		if (lane[i-1]=='G'){ 	
    			batch.draw(greenCarWest,((laneLength*2)+2-i)*32, (laneLength+1)*32);
    		}

    		if (lane[i-1]=='Y'){ 	
    			batch.draw(yellowCarWest,((laneLength*2)+2-i)*32, (laneLength+1)*32);
    		}
 
    		if (lane[i-1]=='B'){ 	
    			batch.draw(blueCarWest,((laneLength*2)+2-i)*32, (laneLength+1)*32);
    		}
    		
    		if (lane[i-1]=='X'){ 	
    			batch.draw(grayCarWest,((laneLength*2)+2-i)*32, (laneLength+1)*32);
    		}

    		if (lane[i-1]=='o'){ 	
    			batch.draw(street,((laneLength*2)+2-i)*32, (laneLength+1)*32);
    		}
    	}
    }

    private void drawNorthOut(char[]lane){
    	
    	for (int i = 0;i<laneLength;i++){
    		
    		if (lane[i]=='Y'){ 	
    			batch.draw(yellowCarNorth,(laneLength+1)*32,((laneLength+2)*32)+i*32);
    		}

    		if (lane[i]=='G'){ 	
    			batch.draw(greenCarNorth,(laneLength+1)*32,((laneLength+2)*32)+i*32);
    		}

    		if (lane[i]=='B'){ 	
    			batch.draw(blueCarNorth, (laneLength+1)*32,((laneLength+2)*32)+i*32);
    		}

    		if (lane[i]=='R'){ 	
    			batch.draw(redCarNorth, (laneLength+1)*32,((laneLength+2)*32)+i*32);	
    		}
    		
    		if (lane[i]=='X'){ 	
    			batch.draw(grayCarNorth, (laneLength+1)*32,((laneLength+2)*32)+i*32);	
    		}

    		if (lane[i]=='o'){ 	
    			batch.draw(street, (laneLength+1)*32,((laneLength+2)*32)+i*32);
    		}
    	}
    }
    
    private void drawSouthOut(char[]lane){
    	
    	for (int i = 0;i<laneLength;i++){
    		
    		if (lane[i]=='Y'){ 	
    			batch.draw(yellowCarSouth,(laneLength)*32,((laneLength-1)-i)*32);
    		}

    		if (lane[i]=='G'){ 	
    			batch.draw(greenCarSouth,(laneLength)*32,((laneLength-1)-i)*32);
    		}
	
    		if (lane[i]=='B'){ 	
    			batch.draw(blueCarSouth,(laneLength)*32,((laneLength-1)-i)*32);
    		}
	
    		if (lane[i]=='R'){ 	
    			batch.draw(redCarSouth,(laneLength)*32,((laneLength-1)-i)*32);
    		}
    		
    		if (lane[i]=='X'){ 	
    			batch.draw(grayCarSouth,(laneLength)*32,((laneLength-1)-i)*32);
    		}

    		if (lane[i]=='o'){ 	
    			batch.draw(street,(laneLength)*32,((laneLength-1)-i)*32);
    		}
    	}
    }
    
    private void drawWestOut(char[]lane){
    	
    	for (int i = 0;i<laneLength;i++){
    		
    		if (lane[i]=='Y'){ 	
    			batch.draw(yellowCarWest,(((laneLength-1)-i)*32),(laneLength+1)*32);
    		}

    		if (lane[i]=='B'){ 	
    			batch.draw(blueCarWest,(((laneLength-1)-i)*32),(laneLength+1)*32);
    		}
    		
    		if (lane[i]=='G'){ 	
    			batch.draw(greenCarWest,(((laneLength-1)-i)*32),(laneLength+1)*32);
    		}

    		if (lane[i]=='R'){ 	
    			batch.draw(redCarWest,(((laneLength-1)-i)*32),(laneLength+1)*32);
    		}
    		
    		if (lane[i]=='X'){ 	
    			batch.draw(grayCarWest,(((laneLength-1)-i)*32),(laneLength+1)*32);
    		}
	
    		if (lane[i]=='o'){ 	
    			batch.draw(street,(((laneLength-1)-i)*32),(laneLength+1)*32);
    		}
    	}
    	
    }

	private void drawEastOut(char[]lane){
		
		for (int i = 0;i<laneLength;i++){
    		
    		if (lane[i]=='Y'){ 	
    			batch.draw(yellowCarEast,(laneLength+2+i)*32,laneLength*32);
    		}
    		
    		if (lane[i]=='B'){ 	
    			batch.draw(blueCarEast,(laneLength+2+i)*32,laneLength*32);
    		}
	
    		if (lane[i]=='G'){ 	
    			batch.draw(greenCarEast,(laneLength+2+i)*32,laneLength*32);
    		}

    		if (lane[i]=='R'){ 	
    			batch.draw(redCarEast,(laneLength+2+i)*32,laneLength*32);
    		}
    		
    		if (lane[i]=='X'){ 	
    			batch.draw(grayCarEast,(laneLength+2+i)*32,laneLength*32);
    		}
 
    		if (lane[i]=='o'){ 	
    			batch.draw(street,(laneLength+2+i)*32,laneLength*32);
    		}
    	}
	
	}
    
	private void drawJunctionField(char[][] Junction,CarDirection[][] Direction,int h, int v){
				
		int x;
		int y;
		
		if(h == 1 && v==1){
			x= 1;
			y = 0;
		}
		else{
			if(h==1 && v == 0){
				x=0;
				y=0;
			}
			else{
				if(h==0 && v == 1){
					x= 1;
					y= 1;
				}
				else{
					if(h==0 && v ==0){
						x=0;
						y=1;
					}
					else{
						return;
					}
				}
			}
		}
		
		
		
		switch(Direction[h][v]){
			
			case WEST:
				
				if(Junction [h][v]=='B'){
					batch.draw(blueCarWest,(laneLength+x)*32,(laneLength+y)*32);	
					break;
				}
				
				if(Junction [h][v]=='R'){
					batch.draw(redCarWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='G'){
					batch.draw(greenCarWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
											
				if(Junction [h][v]=='Y'){
					batch.draw(yellowCarWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='X'){
					batch.draw(grayCarWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='o'){
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				break;
				
			case EAST:
				
				if(Junction [h][v]=='B'){
					batch.draw(blueCarEast,(laneLength+x)*32,(laneLength+y)*32);	
					break;
				}
				
				if(Junction [h][v]=='R'){
					batch.draw(redCarEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='G'){
					batch.draw(greenCarEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
											
				if(Junction [h][v]=='Y'){
					batch.draw(yellowCarEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='X'){
					batch.draw(grayCarEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='o'){
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				break;
				
			case NORTH:
				
				if(Junction [h][v]=='B'){
					batch.draw(blueCarNorth,(laneLength+x)*32,(laneLength+y)*32);	
					break;
				}
				
				if(Junction [h][v]=='R'){
					batch.draw(redCarNorth,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='G'){
					batch.draw(greenCarNorth,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
											
				if(Junction [h][v]=='Y'){
					batch.draw(yellowCarNorth,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='X'){
					batch.draw(grayCarNorth,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='o'){
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				break;
				
			case SOUTH:
				
				if(Junction [h][v]=='B'){
					batch.draw(blueCarSouth,(laneLength+x)*32,(laneLength+y)*32);	
					break;
				}
				
				if(Junction [h][v]=='R'){
					batch.draw(redCarSouth,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='G'){
					batch.draw(greenCarSouth,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
											
				if(Junction [h][v]=='Y'){
					batch.draw(yellowCarSouth,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='X'){
					batch.draw(grayCarSouth,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='o'){
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				break;
				
				
			case NORTHEAST:
				
				if(Junction [h][v]=='B'){
					batch.draw(blueCarNorthEast,(laneLength+x)*32,(laneLength+y)*32);	
					break;
				}
				
				if(Junction [h][v]=='R'){
					batch.draw(redCarNorthEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='G'){
					batch.draw(greenCarNorthEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
											
				if(Junction [h][v]=='Y'){
					batch.draw(yellowCarNorthEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='X'){
					batch.draw(grayCarNorthEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='o'){
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				break;
				
			case SOUTHEAST:
				
				if(Junction [h][v]=='B'){
					batch.draw(blueCarSouthEast,(laneLength+x)*32,(laneLength+y)*32);	
					break;
				}
				
				if(Junction [h][v]=='R'){
					batch.draw(redCarSouthEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='G'){
					batch.draw(greenCarSouthEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
											
				if(Junction [h][v]=='Y'){
					batch.draw(yellowCarSouthEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='X'){
					batch.draw(grayCarSouthEast,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='o'){
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				break;
				
			case NORTHWEST:
				
				if(Junction [h][v]=='B'){
					batch.draw(blueCarNorthWest,(laneLength+x)*32,(laneLength+y)*32);	
					break;
				}
				
				if(Junction [h][v]=='R'){
					batch.draw(redCarNorthWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='G'){
					batch.draw(greenCarNorthWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
											
				if(Junction [h][v]=='Y'){
					batch.draw(yellowCarNorthWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='X'){
					batch.draw(grayCarNorthWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='o'){
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				break;
				
			case SOUTHWEST:
				
				if(Junction [h][v]=='B'){
					batch.draw(blueCarSouthWest,(laneLength+x)*32,(laneLength+y)*32);	
					break;
				}
				
				if(Junction [h][v]=='R'){
					batch.draw(redCarSouthWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='G'){
					batch.draw(greenCarSouthWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
											
				if(Junction [h][v]=='Y'){
					batch.draw(yellowCarSouthWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='X'){
					batch.draw(grayCarSouthWest,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				
				if(Junction [h][v]=='o'){
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;
				}
				break;	
				
				default:
					batch.draw(street,(laneLength+x)*32,(laneLength+y)*32);
					break;				
		}
	}
   
	private void drawJunction(){
		
		drawJunctionField(model.getViewJunctionField(),model.getViewDirectionField(),0,0);
		drawJunctionField(model.getViewJunctionField(),model.getViewDirectionField(),1,0);
		drawJunctionField(model.getViewJunctionField(),model.getViewDirectionField(),0,1);
		drawJunctionField(model.getViewJunctionField(),model.getViewDirectionField(),1,1);
	}
	
	private void drawTrafficLights(){
		
		northTrafficLigthColor=model.getViewNorthTrafficLigthColor();
		southTrafficLigthColor=model.getViewSouthTrafficLigthColor();
		westTrafficLigthColor=model.getViewWestTrafficLigthColor();
		
		switch(northTrafficLigthColor){
			
			case RED:
				batch.draw(northTrafficRed,(laneLength-1)*32,(laneLength+3)*32);
				batch.draw(northTrafficRed,(laneLength+2)*32,(laneLength+3)*32);
				break;
			
			case GREEN:
				batch.draw(northTrafficGreen,(laneLength-1)*32,(laneLength+3)*32);
				batch.draw(northTrafficGreen,(laneLength+2)*32,(laneLength+3)*32);
				break;
			
			case YELLOW:
				batch.draw(northTrafficYellow,(laneLength-1)*32,(laneLength+3)*32);
				batch.draw(northTrafficYellow,(laneLength+2)*32,(laneLength+3)*32);
				break;		
		}
		
		switch(southTrafficLigthColor){
		
			case RED:
				batch.draw(southTrafficRed,(laneLength-1)*32,(laneLength-5)*32);
				batch.draw(southTrafficRed,(laneLength+2)*32,(laneLength-5)*32);
				break;
		
			case GREEN:
				batch.draw(southTrafficGreen,(laneLength-1)*32,(laneLength-5)*32);
				batch.draw(southTrafficGreen,(laneLength+2)*32,(laneLength-5)*32);
				break;
		
			case YELLOW:
				batch.draw(southTrafficYellow,(laneLength-1)*32,(laneLength-5)*32);
				batch.draw(southTrafficYellow,(laneLength+2)*32,(laneLength-5)*32);
				break;		
		}
		
		switch(westTrafficLigthColor){
		
			case RED:
				batch.draw(westTrafficRed,(laneLength-5)*32,(laneLength+2)*32);
				batch.draw(westTrafficRed,(laneLength-5)*32,(laneLength-1)*32);
				break;
	
			case GREEN:
				batch.draw(westTrafficGreen,(laneLength-5)*32,(laneLength+2)*32);
				batch.draw(westTrafficGreen,(laneLength-5)*32,(laneLength-1)*32);
				break;
	
			case YELLOW:
				batch.draw(westTrafficYellow,(laneLength-5)*32,(laneLength+2)*32);
				batch.draw(westTrafficYellow,(laneLength-5)*32,(laneLength-1)*32);
				break;		
		}
		
		switch(westTrafficLigthColor){
		
			case RED:
				batch.draw(eastTrafficRed,(laneLength+3)*32,(laneLength+2)*32);
				batch.draw(eastTrafficRed,(laneLength+3)*32,(laneLength-1)*32);
				break;

			case GREEN:
				batch.draw(eastTrafficGreen,(laneLength+3)*32,(laneLength+2)*32);
				batch.draw(eastTrafficGreen,(laneLength+3)*32,(laneLength-1)*32);
				break;

			case YELLOW:
				batch.draw(eastTrafficYellow,(laneLength+3)*32,(laneLength+2)*32);
				batch.draw(eastTrafficYellow,(laneLength+3)*32,(laneLength-1)*32);
				break;		
		}
		

	}
	
	private void drawFont(){
		
		switch(simulationSpeed){
		
		case INITIAL:
			font.draw(batch, "Simulation Speed: Initial", 8, ((laneLength*2)+2)*32);
			break;
		
		case SLOW:
			font.draw(batch, "Simulation Speed: Slow", 8, ((laneLength*2)+2)*32);
			break;
		
			
		case NORMAL:
			font.draw(batch, "Simulation Speed: Normal", 8, ((laneLength*2)+2)*32);
			break;
		
			
		case FAST:
			font.draw(batch, "Simulation Speed: Fast", 8, ((laneLength*2)+2)*32);
			break;
		
			
		case VERYFAST:
			font.draw(batch, "Simulation Speed: Very Fast", 8, ((laneLength*2)+2)*32);
			break;
		
    	}
	}
	
	@Override
    public boolean keyDown(int keycode) {
       
		if (keycode == Input.Keys.STAR) {
        	
        	switch(simulationSpeed){
        		
        		case INITIAL:
        			simulationSpeed = SimulationSpeed.SLOW;
        			waitingTime = 500;
        			break;
        			
        		default:
        			simulationSpeed = SimulationSpeed.INITIAL;
        			waitingTime = initialWaitingTime;
        			break;
        	}
        }
        
        if (keycode == Input.Keys.PLUS) {
        	
        	switch(simulationSpeed){
    				
    		case SLOW:
    			simulationSpeed = SimulationSpeed.NORMAL;
    			waitingTime = 200;
    			break;
    			
    		case NORMAL:
    			simulationSpeed = SimulationSpeed.FAST;
    			waitingTime = 100;
    			break;
    			
    		case FAST:
    			simulationSpeed = SimulationSpeed.VERYFAST;
    			waitingTime = 0;
    			break;
    			
    		default:
    			break;
        	}
        }
        	
        if (keycode == Input.Keys.MINUS) {
             	
        	switch(simulationSpeed){
         		     			
         	case VERYFAST:
         		simulationSpeed = SimulationSpeed.FAST;
         		waitingTime = 100;
         		break;
         			
         	case FAST:
         		simulationSpeed = SimulationSpeed.NORMAL;
         		waitingTime = 200;
         		break;
         			
         	case NORMAL:
         		simulationSpeed = SimulationSpeed.SLOW;
         		waitingTime = 500;
         		break;
         			
         	default:
         		break;
            }
        }
        return false;
    }
		
	@Override
    public void create() { 
    	
    	model = new CentralModel(laneLength); 
    	modelController = new CentralSimulationController(model,carSpawnRate);
    	laneLength = model.getLaneLength(); 
    	
    	batch = new SpriteBatch();
        atlas = new TextureAtlas("NewSimPics.atlas");
       
        street = atlas.findRegion("street");
        
        northTrafficGreen= atlas.findRegion("NorthTrafficGreen");
        northTrafficYellow = atlas.findRegion("NorthTrafficYellow");
        northTrafficRed = atlas.findRegion("NorthTrafficRed");
        
        southTrafficGreen= atlas.findRegion("SouthTrafficGreen");
        southTrafficYellow = atlas.findRegion("SouthTrafficYellow");
        southTrafficRed = atlas.findRegion("SouthTrafficRed");
        
        eastTrafficGreen= atlas.findRegion("EastTrafficGreen");
        eastTrafficYellow = atlas.findRegion("EastTrafficYellow");
        eastTrafficRed = atlas.findRegion("EastTrafficRed");
        
        westTrafficGreen= atlas.findRegion("WestTrafficGreen");
        westTrafficYellow = atlas.findRegion("WestTrafficYellow");
        westTrafficRed = atlas.findRegion("WestTrafficRed");

        
        blueCarNorth = atlas.findRegion("BlueCarNorth");
        redCarNorth = atlas.findRegion("RedCarNorth");
        greenCarNorth = atlas.findRegion("GreenCarNorth");
        yellowCarNorth = atlas.findRegion("YellowCarNorth");
        grayCarNorth = atlas.findRegion("GrayCarNorth");
        
        blueCarSouth = atlas.findRegion("BlueCarSouth");
        redCarSouth = atlas.findRegion("RedCarSouth");
        greenCarSouth = atlas.findRegion("GreenCarSouth");
        yellowCarSouth = atlas.findRegion("YellowCarSouth");
        grayCarSouth = atlas.findRegion("GrayCarSouth");
        
        blueCarWest = atlas.findRegion("BlueCarWest");
        redCarWest = atlas.findRegion("RedCarWest");
        greenCarWest = atlas.findRegion("GreenCarWest");
        yellowCarWest = atlas.findRegion("YellowCarWest");
        grayCarWest = atlas.findRegion("GrayCarWest");
        
        blueCarEast = atlas.findRegion("BlueCarEast");
        redCarEast = atlas.findRegion("RedCarEast");
        greenCarEast = atlas.findRegion("GreenCarEast");
        yellowCarEast= atlas.findRegion("YellowCarEast");
        grayCarEast= atlas.findRegion("GrayCarEast");
        
        blueCarNorthWest = atlas.findRegion("BlueCarNorthWest");
        redCarNorthWest = atlas.findRegion("RedCarNorthWest");
        greenCarNorthWest = atlas.findRegion("GreenCarNorthWest");
        yellowCarNorthWest = atlas.findRegion("YellowCarNorthWest");
        grayCarNorthWest = atlas.findRegion("GrayCarNorthWest");
        
        blueCarSouthWest = atlas.findRegion("BlueCarSouthWest");
        redCarSouthWest = atlas.findRegion("RedCarSouthWest");
        greenCarSouthWest= atlas.findRegion("GreenCarSouthWest");
        yellowCarSouthWest = atlas.findRegion("YellowCarSouthWest");
        grayCarSouthWest = atlas.findRegion("GrayCarSouthWest");
        
        blueCarNorthEast = atlas.findRegion("BlueCarNorthEast");
        redCarNorthEast = atlas.findRegion("RedCarNorthEast");
        greenCarNorthEast = atlas.findRegion("GreenCarNorthEast");
        yellowCarNorthEast = atlas.findRegion("YellowCarNorthEast");
        grayCarNorthEast = atlas.findRegion("GrayCarNorthEast");
        
        blueCarSouthEast = atlas.findRegion("BlueCarSouthEast");
        redCarSouthEast = atlas.findRegion("RedCarSouthEast");
        greenCarSouthEast = atlas.findRegion("GreenCarSouthEast");
        yellowCarSouthEast= atlas.findRegion("YellowCarSouthEast");
        grayCarSouthEast= atlas.findRegion("GrayCarSouthEast");
        
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        Gdx.input.setInputProcessor(this);
        
        sound = Gdx.audio.newSound(Gdx.files.internal("trafficsound.mp3"));
	sound.play();
    
        
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
        sound.dispose();
    }

    @Override
    public void render() {        
    	
    	Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        modelController.runSimulation();
        
        batch.begin();
        
        drawNorthIn(model.getViewNorthIn());
        drawSouthIn(model.getViewSouthIn());
        drawWestIn(model.getViewWestIn());
        drawEastIn(model.getViewEastIn());
        
        drawNorthOut(model.getViewNorthOut());
        drawSouthOut(model.getViewSouthOut());
        drawWestOut(model.getViewWestOut());
        drawEastOut(model.getViewEastOut());  
        
        drawTrafficLights();
        drawJunction();
        drawFont();
        
        batch.end();
        
        try {
			Thread.sleep(waitingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    	
    }  
}
