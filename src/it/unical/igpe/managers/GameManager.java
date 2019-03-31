package it.unical.igpe.managers;

import java.util.Scanner;

import it.unical.igpe.logic.World;
import it.unical.igpe.logic.objects.Turtle;
import it.unical.igpe.logic.objects.blocks.GrassBlock;
import it.unical.igpe.logic.objects.blocks.LandBlock;
import it.unical.igpe.logic.objects.blocks.SteelBlock;
import it.unical.igpe.logic.objects.blocks.WaterBlock;
import it.unical.igpe.logic.objects.boxs.LifeBox;
import it.unical.igpe.logic.objects.boxs.NitroBox;
import it.unical.igpe.logic.objects.boxs.WumpaBox;

public class GameManager implements Runnable {
	private World world = new World();
	
	private Turtle turtle = new Turtle(world, 0, 0, 0, 0);
	
	private WumpaBox wumpaBox = new WumpaBox(world, 0, 0);
	private LifeBox lifeBox = new LifeBox(world, 0, 0);
	private NitroBox nitroBox = new NitroBox(world, 0, 0);
	
	private GrassBlock grassBlock = new GrassBlock(world, 0, 0);
	private LandBlock landBlock = new LandBlock(world, 0, 0);
	private WaterBlock waterBlock = new WaterBlock(world, 0, 0);
	private SteelBlock steelBlock = new SteelBlock(world, 0, 0);
	
	private boolean end = false;
	
	public GameManager() {}
	
	public World getWorld() {
		return world;
	}
	
	public Turtle getTurtle() {
		return turtle;
	}
	
	public WumpaBox getWumpaBox() {
		return wumpaBox;
	}
	
	public LifeBox getLifeBox() {
		return lifeBox;
	}
	
	public NitroBox getNitroBox() {
		return nitroBox;
	}
	
	public GrassBlock getGrassBlock() {
		return grassBlock;
	}
	
	public LandBlock getLandBlock() {
		return landBlock;
	}
	
	public WaterBlock getWaterBlock() {
		return waterBlock;
	}
	
	public SteelBlock getSteelBlock() {
		return steelBlock;
	}
	
	public void setEnd(final boolean end) {
		this.end = end;
	}
	
	public void startGame() {
		end = false;
		world.readLevelFile();
		world.matrix2world();
	}
	
	public boolean gameOver() {
		return world.getCrash().getLives() <= 0 || end;
	}
	
	public void update() {
		for(Turtle en : world.getEnemys().getTurtles()) {
			if(en.intersection(world.getCrash())) {
				world.getCrash().removeOneLife();
				world.getEnemys().getTurtles().remove(en);
			}
		}
		for(LifeBox life : world.getLifes()) {
			if(life.intersection(world.getCrash())) {
				world.getCrash().addOneLife();
				world.getLifes().remove(life);
			}
		}
		world.manageWorldCollision();
		world.getCrash().update();
	}
	
	public void printWorld() {
		System.out.println(world.toString());
		System.out.println("Vite: " + world.getCrash().getLives());
	}
	
	public static void main(String[] args) {
		final GameManager game = new GameManager();
		game.startGame();
		(new Thread(game)).start();
		game.printWorld();
		System.out.println("Press WASD to go move Crash");
		try(Scanner scanner = new Scanner(System.in)) {
			String line = scanner.nextLine();
			while(line.equals("e") || !game.gameOver()) {
				switch(line) {
					case "a":
						game.world.getCrash().setVelX(-1);
						break;
					case "d":
						game.world.getCrash().setVelX(1);
						break;
				}
				game.update();
				game.printWorld();
				line = scanner.nextLine();
			}
			game.end = true;
		}
	}

	@Override
	public void run() {
		while(!gameOver()) {
			update();
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
