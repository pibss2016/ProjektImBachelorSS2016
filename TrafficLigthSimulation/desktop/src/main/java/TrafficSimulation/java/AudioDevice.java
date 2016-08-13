package TrafficSimulation.java;

import com.badlogic.gdx.utils.Disposable;

public interface AudioDevice extends Disposable {
	
	public boolean isMono ();

	
	public void writeSamples (short[] samples, int offset, int numSamples);

	
	public void writeSamples (float[] samples, int offset, int numSamples);

	
	public int getLatency ();

	
	public void dispose ();

	public void setVolume (float volume);
}