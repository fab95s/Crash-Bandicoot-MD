package it.unical.igpe.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KeyController {
	public static int UP;
	public static int DOWN;
	public static int RIGHT;
	public static int LEFT;
	public static int JUMP;
	public static int WHEEL;
	public static int CRAWL;
	public static int RUN;
	
	private String path;
	private List<Integer> controls = new ArrayList<Integer>();
	private BufferedReader reader = null;
	private BufferedWriter writer = null;
	
	public KeyController() {
		try {
			path = KeyController.class.getResource("/resources/key.cfg").toString().replace("file:", "");
			path = path.replace("%20", " ");
			reader = new BufferedReader(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}
	
	private void init() {
		try {
			String tmp;
			while((tmp = reader.readLine()) != null) {
				controls.add(Integer.parseInt(tmp));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UP = controls.get(0);
		DOWN = controls.get(1);
		RIGHT = controls.get(2);
		LEFT = controls.get(3);
		JUMP = controls.get(4);
		WHEEL = controls.get(5);
		CRAWL = controls.get(6);
		RUN = controls.get(7);
	}
	
	public void updateControls(){
		try {
			writer = new BufferedWriter(new FileWriter(path));
			for(final int key : controls) {
				writer.append(key + "\r\n");
			}
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		UP = controls.get(0);
		DOWN = controls.get(1);
		RIGHT = controls.get(2);
		LEFT = controls.get(3);
		JUMP = controls.get(4);
		WHEEL = controls.get(5);
		CRAWL = controls.get(6);
		RUN = controls.get(7);
	}
	
	public void setUp(final int keyCode) {
		controls.set(0, keyCode);
	}
	
	public void setDown(final int keyCode) {
		controls.set(1, keyCode);
	}
	
	public void setRight(final int keyCode) {
		controls.set(2, keyCode);
	}
	
	public void setLeft(final int keyCode) {
		controls.set(3, keyCode);
	}
	
	public void setJump(final int keyCode) {
		controls.set(4, keyCode);
	}
	
	public void setRuota(final int keyCode) {
		controls.set(5, keyCode);
	}
	
	public void setStriscia(final int keyCode) {
		controls.set(6, keyCode);
	}
	
	public void setCorri(final int keyCode) {
		controls.set(7, keyCode);
	}
}
