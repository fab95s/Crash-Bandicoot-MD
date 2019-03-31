package it.unical.igpe.logic;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.SingleplayerGamePanel;
import it.unical.igpe.graphics.MenuPanel;
import it.unical.igpe.graphics.MultiplayerPanel;
import it.unical.igpe.logic.interfaces.ObjectChar;
import it.unical.igpe.logic.interfaces.StaticObject;
import it.unical.igpe.logic.objects.Coco;
import it.unical.igpe.logic.objects.Crash;
import it.unical.igpe.logic.objects.Crystal;
import it.unical.igpe.logic.objects.Turtle;
import it.unical.igpe.logic.objects.blocks.GrassBlock;
import it.unical.igpe.logic.objects.blocks.LandBlock;
import it.unical.igpe.logic.objects.blocks.SteelBlock;
import it.unical.igpe.logic.objects.blocks.WaterBlock;
import it.unical.igpe.logic.objects.blocks.WorldBlock;
import it.unical.igpe.logic.objects.boxs.LifeBox;
import it.unical.igpe.logic.objects.boxs.NitroBox;
import it.unical.igpe.logic.objects.boxs.WumpaBox;
import it.unical.igpe.logic.threadpools.ThreadPool;

public class World {
	private final static int width = 52;
	private final static int height = 29;
	
	private final int factor = EditorPanel.slotDimension;
	private char[][] matrix;
	private SoundManager sound = new SoundManager();
	private LevelManager levelManager = new LevelManager();
	private static File level;
	
	private Crystal crystal = new Crystal(this, 100, 100, factor, factor * 2);
	private Crash crash = new Crash(this, 100, 100, factor, factor * 2);
	private Coco coco = new Coco(this, 200, 20, factor, factor * 2);
	private ThreadPool enemys = new ThreadPool();
	
	private List<WumpaBox> wumpa;
	private List<LifeBox> lifes;
	private List<NitroBox> nitro;
	
	private List<GrassBlock> grassBlocks;
	private List<LandBlock> landBlocks;
	private List<WaterBlock> waterBlocks;
	private List<SteelBlock> steelBlocks;
	
	private List<WorldBlock> worldBlocks;
	
	public World() {
		this.matrix = getEmptyMatrix();
	}
	
	public static char[][] getEmptyMatrix(){
		final char[][] matrix = new char[width][height];
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				matrix[i][j] = ObjectChar.EMPTY.toChar();
			}
		}
		return matrix;
	}
	
	public static void selectLevel(final URL levelUrl) {
		level = new File(levelUrl.getPath().replace("%20", " ").replace("file:", ""));
	}
	
	public static void selectLevel(final File levelTmp) {
		level = levelTmp;
	}
	
	public void readLevelFile() {
		levelManager.loadLevel(level);
		int i = 0, j = 0;
		for(final String line : levelManager.getLevel()) {
			j = 0;
			for(final char c : line.toCharArray()) {
				matrix[j][i] = c;
				j++;
			}
			i++;
		}
	}
	
	public void place(final StaticObject o) {
		int x_m = o.getX() / factor;
		x_m = (x_m < 0) ? 0 : x_m;
		
		int y_m = o.getY() / factor;
		y_m = (y_m < 0) ? 0 : y_m;
		
		final ObjectChar oc = ObjectChar.toObjectChar(o);
		matrix[y_m][x_m] = oc.toChar();
	}
	
	public void world2matrix(){
		for(final Turtle tu : enemys.getTurtles()) {
			place(tu);
		}
		for(final LifeBox life : lifes) {
			place(life);
		}
		for(final GrassBlock terrain : grassBlocks) {
			place(terrain);
		}
		for(final LandBlock terrain : landBlocks) {
			place(terrain);
		}
		for(final WaterBlock terrain : waterBlocks) {
			place(terrain);
		}
		for(final SteelBlock terrain : steelBlocks) {
			place(terrain);
		}
		place(crash);
		if(MultiplayerPanel.isMultiplayer) {
			place(coco);
		}
	}
	
	public void matrix2world() {
		wumpa = new CopyOnWriteArrayList<WumpaBox>();
		lifes = new CopyOnWriteArrayList<LifeBox>();
		nitro = new CopyOnWriteArrayList<NitroBox>();
		
		grassBlocks = new ArrayList<GrassBlock>();
		landBlocks = new ArrayList<LandBlock>();
		waterBlocks = new ArrayList<WaterBlock>();
		steelBlocks = new ArrayList<SteelBlock>();
		
		worldBlocks = new ArrayList<WorldBlock>();
		
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				final int x = j * factor;
				final int y = i * factor;
				if(matrix[j][i] == ObjectChar.TARTLE.toChar()) {
					enemys.addTurtle(new Turtle(this, x, y, 30, 30));
				}
				if(matrix[j][i] == ObjectChar.WUMPABOX.toChar()) {
					wumpa.add(new WumpaBox(this, x, y));
				}
				if(matrix[j][i] == ObjectChar.LIFEBOX.toChar()) {
					lifes.add(new LifeBox(this, x, y));
				}
				if(matrix[j][i] == ObjectChar.NITROBOX.toChar()) {
					nitro.add(new NitroBox(this, x, y));
				}
				if(matrix[j][i] == ObjectChar.GRASSBLOCK.toChar()) {
					grassBlocks.add(new GrassBlock(this, x, y));
					worldBlocks.add(new GrassBlock(this, x, y));
				}
				if(matrix[j][i] == ObjectChar.LANDBLOCK.toChar()) {
					landBlocks.add(new LandBlock(this, x, y));
					worldBlocks.add(new LandBlock(this, x, y));
				}
				if(matrix[j][i] == ObjectChar.WATERBLOCK.toChar()) {
					waterBlocks.add(new WaterBlock(this, x, y));
					worldBlocks.add(new WaterBlock(this, x, y));
				}
				if(matrix[j][i] == ObjectChar.STEELBLOCK.toChar()) {
					steelBlocks.add(new SteelBlock(this, x, y));
					worldBlocks.add(new SteelBlock(this, x, y));
				}
				if(matrix[j][i] == ObjectChar.CRYSTAL.toChar()) {
					crystal = new Crystal(this, x, y - factor, factor, factor * 2);
				}
				if(matrix[j][i] == ObjectChar.CRASH.toChar()) {
					crash = new Crash(this, x, y - factor, factor, factor * 2);
				}
			}
		}
		if(MultiplayerPanel.isMultiplayer) {
			coco = new Coco(this, (crash.getX() + (factor * 2)), crash.getY(), crash.getWidth(), crash.getHeight());
		}
	}
	
	public synchronized void manageWorldCollision() {
		boolean collision = false;
		
		for(final WorldBlock block : worldBlocks) {
			if(!(block instanceof WaterBlock)) {
				if(crash.getBoundTop().intersects(block.getBound())) {
					crash.setVelY(2);
					crash.setFalling(true);
				}
				if(crash.getBoundBottom().intersects(block.getBound())) {
					crash.setVelY(0);
					crash.setFalling(false);
					crash.setJumping(false);
					collision = true;
				}
				if(crash.getBoundRight().intersects(block.getBound())) {
					crash.setVelX(-2);
				}
				if(crash.getBoundLeft().intersects(block.getBound())) {
					crash.setVelX(2);
				}
				for(Turtle tu : enemys.getTurtles()) {
					if(crash.getBoundBottom().intersects(tu.getBound())) {
						enemys.getTurtles().remove(tu);
					}
					if(crash.getBoundLeft().intersects(tu.getBound()) || crash.getBoundRight().intersects(tu.getBound())) {
						if(!crash.isRotation()) {
							crash.removeOneLife();
						}
						enemys.getTurtles().remove(tu);
					}
					if(tu.getBoundBottom().intersects(block.getBound())) {
						tu.setVelY(0);
						tu.setFalling(false);
						tu.setJumping(false);
					}
					if(tu.getBoundRight().intersects(block.getBound()) || tu.getBoundLeft().intersects(block.getBound())) {
						tu.changeDirection();
					}
				}
			}
		}
		if(!collision && crash.getVelY() == 0) {
			crash.setFalling(true);
		}
		
		
		for(final WumpaBox wum : wumpa) {
			if(crash.isRotation() && crash.getBound().intersects(wum.getBound())) {
				crash.addWumpaFruits(wum.getWumpaFruits());
				sound.startMusic(SoundManager.BOX_BREAK);
				SingleplayerGamePanel.brokenBoxes++;
				wumpa.remove(wum);
			}
			if(crash.getBoundTop().intersects(wum.getBound())) {
				crash.setVelY(2);
				crash.setFalling(true);
				crash.addWumpaFruits(wum.getWumpaFruits());
				sound.startMusic(SoundManager.BOX_BREAK);
				SingleplayerGamePanel.brokenBoxes++;
				wumpa.remove(wum);
			}
			if(crash.getBoundBottom().intersects(wum.getBound())) {
				crash.addWumpaFruits(wum.getWumpaFruits());
				sound.startMusic(SoundManager.BOX_BREAK);
				SingleplayerGamePanel.brokenBoxes++;
				wumpa.remove(wum);
			}
			if(crash.getBoundRight().intersects(wum.getBound())) {
				crash.setVelX(-2);
			}
			if(crash.getBoundLeft().intersects(wum.getBound())) {
				crash.setVelX(2);
			}
		}
		for(final LifeBox life : lifes) {
			if(crash.isRotation() && crash.getBound().intersects(life.getBound())) {
				crash.addOneLife();
				sound.startMusic(SoundManager.BOX_BREAK);
				SingleplayerGamePanel.brokenBoxes++;
				lifes.remove(life);
			}
			if(crash.getBoundTop().intersects(life.getBound())) {
				crash.setVelY(2);
				crash.setFalling(true);
				crash.addOneLife();
				sound.startMusic(SoundManager.BOX_BREAK);
				SingleplayerGamePanel.brokenBoxes++;
				lifes.remove(life);
			}
			if(crash.getBoundBottom().intersects(life.getBound())) {
				crash.addOneLife();
				sound.startMusic(SoundManager.BOX_BREAK);
				SingleplayerGamePanel.brokenBoxes++;
				lifes.remove(life);
			}
			if(crash.getBoundRight().intersects(life.getBound())) {
				crash.setVelX(-2);
			}
			if(crash.getBoundLeft().intersects(life.getBound())) {
				crash.setVelX(2);
			}
		}
		for(final NitroBox nit : nitro) {
			if(crash.getBound().intersects(nit.getBound())) {
				crash.removeOneLife();
				sound.startMusic(SoundManager.NITRO_EXPLOSION);
				nitro.remove(nit);
			}
		}
		
		SingleplayerGamePanel.onCrystal = crash.getBound().intersects(crystal.getBound());
	}
	
	public String toString() {
		String world = "";
		for(int j=0; j<height; j++) {
			for(int i=0; i<width; i++) {
				world += matrix[i][j];
			}
			world += "\r\n";
		}
		return world;
	}
	
	public char get(final int x, final int y) {
		int x_m = x / factor;
		x_m = (x_m < 0) ? 0 : x_m;
		
		int y_m = y / factor;
		y_m = (y_m < 0) ? 0 : y_m;
		
		return matrix[x_m][y_m];
	}
	
	public int getWidth() {
		return MenuPanel.screenSize.width;
	}
	
	public int getHeight() {
		return MenuPanel.screenSize.height;
	}
	
	public char[][] getMatrix() {
		return matrix;
	}
	
	public void setMatrix(char[][] matrix) {
		this.matrix = matrix;
	}
	
	public SoundManager getSounds() {
		return sound;
	}
	
	public LevelManager getLevel() {
		return levelManager;
	}
	
	public Crystal getCrystal() {
		return crystal;
	}
	
	public Crash getCrash() {
		return crash;
	}
	
	public Coco getCoco() {
		return coco;
	}
	
	public ThreadPool getEnemys() {
		return enemys;
	}
	
	public List<WumpaBox> getWumpa(){
		return wumpa;
	}
	
	public List<LifeBox> getLifes(){
		return lifes;
	}
	
	public List<NitroBox> getNitro(){
		return nitro;
	}
	
	public List<GrassBlock> getGrassBlocks(){
		return grassBlocks;
	}
	
	public List<LandBlock> getLandBlocks(){
		return landBlocks;
	}
	
	public List<WaterBlock> getWaterBlocks(){
		return waterBlocks;
	}
	
	public List<SteelBlock> getSteelBlocks(){
		return steelBlocks;
	}
	
	public List<WorldBlock> getWorldBlocks(){
		return worldBlocks;
	}
}
