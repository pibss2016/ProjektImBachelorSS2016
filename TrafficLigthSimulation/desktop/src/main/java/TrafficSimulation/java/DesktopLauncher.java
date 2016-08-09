package TrafficSimulation.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import TrafficSimulation.java.MainController;


public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Trafficligth Simulation";
        
        int laneLength = 15;//Laenge der Lanes, muss >= 5 sein
        int waitingTime = 0;//Verzoegerung des Animationsablauf in Millisekunden
        int carSpawnRate = 3;// Anzahl der Iterationen der Simulation die zwischen dem Genrieren von 2 Autos durchgefuert werden
        
        cfg.width = ((laneLength*2)+2)*32;
        cfg.height = ((laneLength*2)+2)*32;

        new LwjglApplication(new MainController(laneLength ,waitingTime,carSpawnRate), cfg);
	}
}
