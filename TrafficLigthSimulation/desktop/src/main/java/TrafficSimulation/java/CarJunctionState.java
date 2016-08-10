package TrafficSimulation.java;
/**
 * Enum fuer moegliche Zustaende des Autos
 * waehrend es auf der Kreuzung ist oder diese betreten will.
 * Nur im uebergeordneten Zustand ATJUNCTION relevant(siehe CarState).
 * 
 * @author Nikita Maslov
 * @version 1.0
 */

public enum CarJunctionState {
	ENTERJUNCTION,//Auto will Kreuzung betreten.
	LEAVELEFT,//Versucht Kreuzung nach links zu verlassen.
	LEAVERIGTH,// Versucht abhaengig vom Reiseziel die Kreuzung nach rechts zu verlassen oder nach vorne zu fahren
	LEAVEFORWARD, //Versucht abhaengig vom Reiseziel die Kreuzung nach vore zu verlassen oder nach liks zu fahren
}