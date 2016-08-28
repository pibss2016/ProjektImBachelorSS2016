package TrafficSimulation.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import TrafficSimulation.java.MainController;


public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Trafficlight Simulation";
        
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
	int laneLength = map.get("laneLength"); //Laenge der Lanes, muss >= 5 sein
	int waitingTime = map.get("waitingTime");//Verzoegerung des Animationsablauf in Millisekunden; je kleiner waitingTime, desto höhere Geschwindigkeit von Autos
	int carSpawnRate = map.get("carSpawnRate");// Anzahl der Iterationen der Simulation die zwischen dem Genrieren von 2 Autos durchgefuert werden; je kleiner carSpawnRate, desto mehr Autos erscheinen, aber es darf nicht kleiner als 1 sein
        
        /**
        int laneLength = 10;//Laenge der Lanes, muss >= 5 sein
        int waitingTime = 500;//Verzoegerung des Animationsablauf in Millisekunden; je kleiner waitingTime, desto höhere Geschwindigkeit von Autos
        int carSpawnRate = 3;// Anzahl der Iterationen der Simulation die zwischen dem Genrieren von 2 Autos durchgefuert werden; je kleiner carSpawnRate, desto mehr Autos erscheinen, aber es darf nicht kleiner als 1 sein
        */
        cfg.width = ((laneLength*2)+2)*32;
        cfg.height = ((laneLength*2)+2)*32;

        new LwjglApplication(new MainController(laneLength ,waitingTime,carSpawnRate), cfg);
	}
}
