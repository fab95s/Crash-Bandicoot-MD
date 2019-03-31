package it.unical.igpe.logic;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	public static final int MENUTHEME = 1;
	public static final int CRASH_WOAH = 2;
	public static final int DIGGIN_IT = 3;
	public static final int HANG_EIGHT = 4;
	public static final int HOG_WILD = 5;
	public static final int ROCK_IT = 6;
	public static final int SKULL_ROUTE = 7;
	public static final int NITRO_EXPLOSION = 8;
	public static final int BOX_BREAK = 9;
	public static final int SPIN = 10;
	
	// General Sounds
	URL menuTheme = SoundManager.class.getResource("/resources/audio/WarpRoom.wav");
	URL crashWoah = SoundManager.class.getResource("/resources/audio/Crash_Woah.wav");
	URL nitroExplosion = SoundManager.class.getResource("/resources/audio/Nitro_Explosion.wav");
	URL boxBreak = SoundManager.class.getResource("/resources/audio/Box_Break.wav");
	URL spin = SoundManager.class.getResource("/resources/audio/Spin.wav");
	
	// Game Level Sounds
	URL digginIt = SoundManager.class.getResource("/resources/audio/Diggin_It.wav");
	URL hangEight = SoundManager.class.getResource("/resources/audio/Hang_Eight.wav");
	URL hogWild = SoundManager.class.getResource("/resources/audio/Hog_Wild.wav");
	URL rockIt = SoundManager.class.getResource("/resources/audio/Rock_It.wav");
	URL skullRoute = SoundManager.class.getResource("/resources/audio/Skull_Route.wav");
	
	Clip clip;
	
	public SoundManager() {}
	
	public synchronized void startMusic(final int song) {
		URL music = null;
		int loop = Clip.LOOP_CONTINUOUSLY;
		switch(song) {
			case MENUTHEME:
				music = menuTheme;
				break;
			case CRASH_WOAH:
				music = crashWoah;
				loop = 0;
				break;
			case NITRO_EXPLOSION:
				music = nitroExplosion;
				loop = 0;
				break;
			case BOX_BREAK:
				music = boxBreak;
				loop = 0;
				break;
			case SPIN:
				music = spin;
				loop = 0;
				break;
			case DIGGIN_IT:
				music = digginIt;
				break;
			case HANG_EIGHT:
				music = hangEight;
				break;
			case HOG_WILD:
				music = hogWild;
				break;
			case ROCK_IT:
				music = rockIt;
				break;
			case SKULL_ROUTE:
				music = skullRoute;
				break;
		}
		try {
			AudioInputStream inStr = AudioSystem.getAudioInputStream(music);
			AudioFormat format = inStr.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(inStr);
			clip.loop(loop);
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	public void switchMusic(final String musicName) {
		clip.stop();
		switch(musicName) {
			case "Menu Theme":
				this.startMusic(MENUTHEME);
				break;
			case "Diggin It":
				this.startMusic(DIGGIN_IT);
				break;
			case "Hang Eight":
				this.startMusic(HANG_EIGHT);
				break;
			case "Hog Wild":
				this.startMusic(HOG_WILD);
				break;
			case "Rock It":
				this.startMusic(ROCK_IT);
				break;
			case "Skull Route":
				this.startMusic(SKULL_ROUTE);
				break;
		}
	}
	
	public void stopMusic() {
		if(clip.isActive()) {
			clip.stop();
		}
	}
	
	public boolean isActive() {
		return clip.isActive();
	}
}
