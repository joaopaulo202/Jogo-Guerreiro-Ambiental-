package jogo2d;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/ambiente.wav"); 
		soundURL[1] = getClass().getResource("/sound/danoplayer.wav"); 
		soundURL[2] = getClass().getResource("/sound/danofogo.wav"); 
	}
	
	public void SetFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream (soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			}
	}
	
	public void Play() {
		clip.start();
	}
	
	public void Loop () {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void Stop () {
		clip.stop();
	}
	

}
