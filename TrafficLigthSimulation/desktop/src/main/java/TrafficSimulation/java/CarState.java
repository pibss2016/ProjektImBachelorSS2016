package TrafficSimulation.java;
/**
 * Enum fuer moegliche uebergeordnete Zustande des Autos.
 * Bestimmt, wie sich das Auto im Moment verhaelt.
 * 
 * @author Nikita Maslov
 * @version 1.0
 */
public enum CarState {
	INQUEUE,//In Schlange fuer Lanebeitritt
	ATLANE,//Faehrt auf einer Lane
	DONE,//hat sein Ziel erreicht und wird aus Liste der zu Bearbeitenden Threads entfernt.
	ATJUNCTION // Befindet sich auf der Kreuzung.
}