package TrafficSimulation.java;

import com.badlogic.gdx.utils.Disposable;

/**
 * 
 * 
 * @author Khac Dat Tran
 * 
 */

public interface AudioRecorder extends Disposable {
	
	public void read (short[] samples, int offset, int numSamples);

	public void dispose ();
}
