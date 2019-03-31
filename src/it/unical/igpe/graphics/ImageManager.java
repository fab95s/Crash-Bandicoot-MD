package it.unical.igpe.graphics;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManager {
	// General Images
	private static Image background = null;
	private static Image keyboard = null;
	private static Image pause = null;
	private static ImageIcon akuAku;
	private static Image crystal = null;
	private static Image box = null;
	private static Image wumpaFruit = null;
	private static Image crashLife = null;
	private static Image waitForAnotherPlayer = null;
	
	// Levels Images
	private static Image endLevel = null;
	private static Image selectLevel = null;
	private static ImageIcon level1 = null;
	private static ImageIcon level2 = null;
	private static ImageIcon level3 = null;
	private static ImageIcon selectYourLevel = null;
	
	// Game Backgrounds Image
	private static Image game_background_1 = null;
	private static Image game_background_2 = null;
	private static Image game_background_3 = null;
	
	// Blocks Images
	private static Image land = null;
	private static Image grass = null;
	private static ImageIcon water;
	private static Image steel = null;
	// Boxs Images
	private static Image wumpa = null;
	private static Image life = null;
	private static Image nitro = null;
	
	// Characters Images
	private static Image crash = null;
	private static ImageIcon crashWoah;
	private static ImageIcon crashStopSx;
	private static ImageIcon crashStopDx;
	private static ImageIcon crashSpin;
	private static ImageIcon crashSx;
	private static ImageIcon crashDx;
	private static ImageIcon turtleSx;
	private static ImageIcon turtleDx;
	
	// Buttons Images
	private final static ImageIcon close;
	private final static ImageIcon singlePlayer;
	private final static ImageIcon multiPlayer;
	private final static ImageIcon mapEditor;
	private final static ImageIcon controls;
	private final static ImageIcon exit;
	private final static ImageIcon arrowBack;
	private final static ImageIcon save;
	private final static ImageIcon load;
	private final static ImageIcon resume;
	private final static ImageIcon backToMenu;
	
	public ImageManager() {}
	
	static {
		try {
			// General Images
			background = ImageIO.read(ImageManager.class.getResource("/resources/images/Background.jpg"));
			keyboard = ImageIO.read(ImageManager.class.getResource("/resources/images/Keyboard.png"));
			pause = ImageIO.read(ImageManager.class.getResource("/resources/images/levels/Pause.png"));
			akuAku = new ImageIcon(ImageManager.class.getResource("/resources/images/levels/AkuAku.gif"));
			crystal = ImageIO.read(ImageManager.class.getResource("/resources/images/levels/Crystal.png"));
			box = ImageIO.read(ImageManager.class.getResource("/resources/images/levels/Box.png"));
			wumpaFruit = ImageIO.read(ImageManager.class.getResource("/resources/images/levels/WumpaFruit.png"));
			crashLife = ImageIO.read(ImageManager.class.getResource("/resources/images/levels/CrashLife.png"));
			waitForAnotherPlayer = ImageIO.read(ImageManager.class.getResource("/resources/images/WaitForAnotherPlayer.png"));
			
			// Levels Images
			endLevel = ImageIO.read(ImageManager.class.getResource("/resources/images/levels/EndLevel.png"));
			selectLevel = ImageIO.read(ImageManager.class.getResource("/resources/images/levels/SelectLevel.png"));
			level1 = new ImageIcon(ImageManager.class.getResource("/resources/images/levels/Level1.png"));
			level2 = new ImageIcon(ImageManager.class.getResource("/resources/images/levels/Level2.png"));
			level3 = new ImageIcon(ImageManager.class.getResource("/resources/images/levels/Level3.png"));
			selectYourLevel = new ImageIcon(ImageManager.class.getResource("/resources/images/levels/SelectYourLevel.png"));
			
			// Game Backgrounds Images
			game_background_1 = ImageIO.read(ImageManager.class.getResource("/resources/images/backgrounds/game_background_1.jpg"));
			game_background_2 = ImageIO.read(ImageManager.class.getResource("/resources/images/backgrounds/game_background_2.jpg"));
			game_background_3 = ImageIO.read(ImageManager.class.getResource("/resources/images/backgrounds/game_background_3.jpg"));
			
			// Blocks Images
			land = ImageIO.read(ImageManager.class.getResource("/resources/images/blocks/land.png"));
			grass = ImageIO.read(ImageManager.class.getResource("/resources/images/blocks/grass.png"));
			water = new ImageIcon(ImageManager.class.getResource("/resources/images/blocks/water.gif"));
			steel = ImageIO.read(ImageManager.class.getResource("/resources/images/blocks/steel.png"));
			// Boxs Images
			wumpa = ImageIO.read(ImageManager.class.getResource("/resources/images/blocks/box.png"));
			life = ImageIO.read(ImageManager.class.getResource("/resources/images/blocks/life.png"));
			nitro = ImageIO.read(ImageManager.class.getResource("/resources/images/blocks/nitro.png"));
			
			// Characters Images
			crash = ImageIO.read(ImageManager.class.getResource("/resources/images/characters/Crash_Bandicoot.png"));
			crashWoah = new ImageIcon(ImageManager.class.getResource("/resources/images/characters/Crash_Woah.gif"));
			crashStopSx = new ImageIcon(ImageManager.class.getResource("/resources/images/characters/CrashStopSx.gif"));
			crashStopDx = new ImageIcon(ImageManager.class.getResource("/resources/images/characters/CrashStopDx.gif"));
			crashSpin = new ImageIcon(ImageManager.class.getResource("/resources/images/characters/CrashSpin.gif"));
			crashSx = new ImageIcon(ImageManager.class.getResource("/resources/images/characters/CrashSx.gif"));
			crashDx = new ImageIcon(ImageManager.class.getResource("/resources/images/characters/CrashDx.gif"));
			turtleSx = new ImageIcon(ImageManager.class.getResource("/resources/images/characters/TurtleSx.gif"));
			turtleDx = new ImageIcon(ImageManager.class.getResource("/resources/images/characters/TurtleDx.gif"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Buttons Images
		close = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/Close.png"));
		singlePlayer = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/SinglePlayer.png"));
		multiPlayer = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/MultiPlayer.png"));
		mapEditor = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/MapEditor.png"));
		controls = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/Controls.png"));
		exit = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/Exit.png"));
		arrowBack = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/ArrowBack.png"));
		save = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/Save.png"));
		load = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/Load.png"));
		resume = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/Resume.png"));
		backToMenu = new ImageIcon(ImageManager.class.getResource("/resources/images/buttons/BackToMenu.png"));
	}
	
	// General Images
	
	public static Image getBackground() {
		return background;
	}
	
	public static Image getKeyboard() {
		return keyboard;
	}
	
	public static Image getPause() {
		return pause;
	}
	
	public static ImageIcon getAkuAku() {
		return akuAku;
	}
	
	public static Image getCrystal() {
		return crystal;
	}
	
	public static Image getBox() {
		return box;
	}
	
	public static Image getWumpaFruit() {
		return wumpaFruit;
	}
	
	public static Image getCrashLife() {
		return crashLife;
	}
	
	public static Image getWaitForAnotherPlayer() {
		return waitForAnotherPlayer;
	}
	
	// Levels Images
	
	public static Image getEndLevel() {
		return endLevel;
	}
	
	public static Image getSelectLevel() {
		return selectLevel;
	}
	
	public static ImageIcon getLevel1() {
		return level1;
	}
	
	public static ImageIcon getLevel2() {
		return level2;
	}
	
	public static ImageIcon getLevel3() {
		return level3;
	}
	
	public static ImageIcon getSelectYourLevel() {
		return selectYourLevel;
	}
	
	// Game Backgrounds Images
	
	public static Image getGameBackground1() {
		return game_background_1;
	}
	
	public static Image getGameBackground2() {
		return game_background_2;
	}
	
	public static Image getGameBackground3() {
		return game_background_3;
	}
	
	// Blocks Images
	
	public static Image getLandBlock() {
		return land;
	}
	
	public static Image getGrassBlock() {
		return grass;
	}
	
	public static ImageIcon getWaterBlock() {
		return water;
	}
	
	public static Image getSteelBlock() {
		return steel;
	}
	
	// Boxs Images
	
	public static Image getWumpaBox() {
		return wumpa;
	}
	
	public static Image getLifeBox() {
		return life;
	}
	
	public static Image getNitroBox() {
		return nitro;
	}
	
	// Characters Images
	
	public static Image getCrashBandicoot() {
		return crash;
	}
	
	public static ImageIcon getCrashWoah() {
		return crashWoah;
	}
	
	public static ImageIcon getCrashStopSx() {
		return crashStopSx;
	}
	
	public static ImageIcon getCrashStopDx() {
		return crashStopDx;
	}
	
	public static ImageIcon getCrashSpin() {
		return crashSpin;
	}
	
	public static ImageIcon getCrashSx() {
		return crashSx;
	}
	
	public static ImageIcon getCrashDx() {
		return crashDx;
	}
	
	public static ImageIcon getTurtleSx() {
		return turtleSx;
	}
	
	public static ImageIcon getTurtleDx() {
		return turtleDx;
	}
	
	// Buttons Images
	
	public static ImageIcon getClose() {
		return close;
	}
	
	public static ImageIcon getMapEditor() {
		return mapEditor;
	}
	
	public static ImageIcon getSinglePlayer() {
		return singlePlayer;
	}
	
	public static ImageIcon getMultiPlayer() {
		return multiPlayer;
	}
	
	public static ImageIcon getControls() {
		return controls;
	}
	
	public static ImageIcon getExit() {
		return exit;
	}
	
	public static ImageIcon getArrowBack() {
		return arrowBack;
	}
	
	public static ImageIcon getSave() {
		return save;
	}
	
	public static ImageIcon getLoad() {
		return load;
	}
	
	public static ImageIcon getResume() {
		return resume;
	}
	
	public static ImageIcon getBackToMenu() {
		return backToMenu;
	}
}
