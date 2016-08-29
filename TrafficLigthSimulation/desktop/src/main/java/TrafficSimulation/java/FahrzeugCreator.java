package TrafficSimulation.java;

/**
 * 
 * 
 * @author Khac Dat Tran
 * 
 */

public class FahrzeugCreator {

	private CentralModel model;

	public FahrzeugCreator() {

	}

	public Fahrzeug createFahrzeug() {

		if (model.getViewEastIn()[0] == 'o') {
			return new QueueEast();
		}

		else if (model.getViewWestIn()[0] == 'o') {
			return new QueueWest();
		}

		else if (model.getViewNorthIn()[0] == 'o') {
			return new QueueNorth();
		}

		else if (model.getViewSouthIn()[0] == 'o') {
			return new QueueSouth();
		}

		return null;

	}

}
