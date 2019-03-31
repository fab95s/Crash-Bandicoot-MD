package it.unical.igpe.logic.objects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.GamePanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.AbstractDynamicObject;
import it.unical.igpe.logic.World;

public class Turtle extends AbstractDynamicObject {
	private int steps = 0;
	private boolean dir = true;
	private ImageIcon turtleSx = new ImageIcon(ImageManager.getTurtleSx().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST));
	private ImageIcon turtleDx = new ImageIcon(ImageManager.getTurtleDx().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST));
	
	public Turtle(World world, int x, int y, int width, int height) {
		super(world, x, y, width, height);
	}
	
	public synchronized void draw(Graphics g, GamePanel game) {
		if(!world.getEnemys().getTurtles().isEmpty()) {
			for(final Turtle tu : world.getEnemys().getTurtles()) {
				if(tu.getVelX() > 0) {
					turtleDx.paintIcon(game, g, tu.getX(), tu.getY());
				} else {
					turtleSx.paintIcon(game, g, tu.getX(), tu.getY());
				}
			}
		}
	}
	
	public void changeDirection() {
		dir = !dir;
		steps = 0;
	}
	
	public void update() {
		if(steps >= 50) {
			dir = !dir;
			steps = 0;
		}
		if(dir) {
			this.setVelX(-2);
		} else {
			this.setVelX(2);
		}
		steps++;
		super.update();
	}
	
}
