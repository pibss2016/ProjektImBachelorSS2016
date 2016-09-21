package TrafficSimulation.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import TrafficSimulation.java.MainController;

/**
 * Launcher Class für Programm laufen
 * 
 * @author Nikita Maslov
 * @author Khac Dat Tran
 * @author Nina Trilck
 */

public class DesktopLauncher {
	
	public static void main (String[] args) {
		
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Trafficlight Simulation";
        
        int laneLength = 10;//Laenge der Lanes, muss >= 5 sein
        int waitingTime = 500;//Verzoegerung des Animationsablauf in Millisekunden; je kleiner waitingTime, desto höhere Geschwindigkeit von Autos
        int carSpawnRate = 3;// Anzahl der Iterationen der Simulation die zwischen dem Generieren von 2 Autos durchgefuert werden; je kleiner carSpawnRate, desto mehr Autos erscheinen, aber es darf nicht kleiner als 1 sein
        
        cfg.width = ((laneLength*2)+2)*32;
        cfg.height = ((laneLength*2)+2)*32;

        new LwjglApplication(new MainController(laneLength ,waitingTime,carSpawnRate), cfg);
        new Thread(new MainController(laneLength,waitingTime,carSpawnRate)).start();
        
        /*
        //Yaml-Datei wird eingelesen
        //rushHour.yml = viele Autos, hohe Geschwindigkeit
        //normalTraffic.yml = weniger Autos, geringe Geschwindigkeit
	YamlReader reader = null;
	try {
		reader = new YamlReader(new FileReader("ressources/rushHour.yml"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	Object object = reader.read();
	Map map = (Map)object;
	//Konfiguration aus Yaml-Datei wird gelesen
	int laneLength = (int)map.get("laneLength"); //Laenge der Lanes, muss >= 5 sein
	int waitingTime = (int)map.get("waitingTime");//Verzoegerung des Animationsablauf in Millisekunden; je kleiner waitingTime, desto höhere Geschwindigkeit von Autos
	int carSpawnRate = (int)map.get("carSpawnRate");// Anzahl der Iterationen der Simulation die zwischen dem Genrieren von 2 Autos durchgefuert werden; je kleiner carSpawnRate, desto mehr Autos erscheinen, aber es darf nicht kleiner als 1 sein
        */
        
	}
}
