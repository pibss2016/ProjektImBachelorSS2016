package TrafficSimulation.java;
/**
 * Enum fuer moegliche Zustaende des Autos
 * waehrend es auf der Lane ist.
 * Nur im uebergeordneten Zustand ATLANE relevant(siehe CarState).
 * 
 * @author Nikita Maslov
 * @version 1.0
 */
public enum LaneState {
	DONE, // hat seine Aufgaben erledigt, wird in den uebergeordneten Zustand DONE wechseln(siehe CarState)
	MOVETHREE,//wird sich um 3 Einheiten nach vorne bewegen
	MOVETWO,//wird sich um 2 Einheiten nach vorne bewegen
	MOVEONE,//wird sich um 1 Einheit nach vorne bewegen
	NOMOVE, //wird sich nicht bewegen
	ATJUNCTION //will Kreuzung betreten
}