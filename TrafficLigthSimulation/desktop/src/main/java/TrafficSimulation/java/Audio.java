package TrafficSimulation.java;

import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public interface Audio {
	
	public AudioDevice newAudioDevice (int samplingRate, boolean isMono);

	
	public AudioRecorder newAudioRecorder (int samplingRate, boolean isMono);

	
	public Sound newSound (FileHandle fileHandle);

	
	public Music newMusic (FileHandle file);
}