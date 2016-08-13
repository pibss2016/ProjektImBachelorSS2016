package TrafficSimulation.java;

import com.badlogic.gdx.utils.Disposable;

public interface Sound extends Disposable {
	
	public long play ();

	
	public long play (float volume);

	
	public long play (float volume, float pitch, float pan);

	
	public long loop ();

	
	public long loop (float volume);

	
	public long loop (float volume, float pitch, float pan);

	
	public void stop ();

	
	public void pause ();

	
	public void resume ();

	
	public void dispose ();

	
	public void stop (long soundId);

	
	public void pause (long soundId);

	
	public void resume (long soundId);

	
	public void setLooping (long soundId, boolean looping);

	
	public void setPitch (long soundId, float pitch);

	
	public void setVolume (long soundId, float volume);

	
	public void setPan (long soundId, float pan, float volume);	
}