package it.unical.igpe.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
	String music;
	String background;
	List<String> level;
	
	public LevelManager() {
		this.music = "";
		this.background = "";
		this.level = new ArrayList<String>();
	}
	
	public void saveLevel(final File filename, final String map) {
		try(FileWriter writer = new FileWriter(filename + ".mdlvl")) {
			writer.write(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadLevel(final File filename) {
		level.clear();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null) {
				String[] tmp = line.split(":");
				if(tmp[0].equals("Music")) {
					music = tmp[1];
				}
				if(tmp[0].equals("Background")) {
					background = tmp[1];
				}
				if(tmp.length == 1 && !line.equals("")) {
					level.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMusic() {
		return music;
	}
	
	public String getBackground() {
		return background;
	}
	
	public List<String> getLevel() {
		return level;
	}
}
