package TrafficSimulation.java;

import com.badlogic.gdx.utils.Disposable;

/**
 * 
 * 
 * @author Khac Dat Tran
 * 
 */

public interface Music extends Disposable {
	
	public void play ();

	
	public void pause ();

	
	public void stop ();

	
	public boolean isPlaying ();

	
	public void setLooping (boolean isLooping);

	
	public boolean isLooping ();

	
	public void setVolume (float volume);

	
	public float getVolume ();

	
	public void setPan (float pan, float volume);

	
	public void setPosition (float position);
	
	
	public float getPosition ();

	
	public void dispose ();

	
	public void setOnCompletionListener (OnCompletionListener listener);

	
	public interface OnCompletionListener {
		
	public abstract void onCompletion (Music music);
	}
}
