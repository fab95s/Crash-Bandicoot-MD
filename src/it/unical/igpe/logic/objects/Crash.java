package it.unical.igpe.logic.objects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.GamePanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.AbstractDynamicObject;
import it.unical.igpe.logic.World;

public class Crash extends AbstractDynamicObject {
	private int lives;
	private int wumpaFruits = 0;
	private boolean rotation = false;
	private ImageIcon crashStopSx = new ImageIcon(ImageManager.getCrashStopSx().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension * 2, Image.SCALE_FAST));
	private ImageIcon crashStopDx = new ImageIcon(ImageManager.getCrashStopDx().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension * 2, Image.SCALE_FAST));
	private ImageIcon crashSpin = new ImageIcon(ImageManager.getCrashSpin().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension * 2, Image.SCALE_FAST));
	private ImageIcon crashSx = new ImageIcon(ImageManager.getCrashSx().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension * 2, Image.SCALE_FAST));
	private ImageIcon crashDx = new ImageIcon(ImageManager.getCrashDx().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension * 2, Image.SCALE_FAST));
	private int lastVel = 0;
	
	public Crash(World world, int x, int y, int width, int height) {
		super(world, x, y, width, height);
		lives = 4;
	}
	
	public synchronized void draw(Graphics g, GamePanel game) {
		if(getWorld().getCrash().getLives() > 0) {
			if(!world.getCrash().isRotation()) {
				if(world.getCrash().getVelX() != 0) {
					if(world.getCrash().getVelX() > 0) {
						crashDx.paintIcon(game, g, this.getX(), this.getY());
					} else {
						crashSx.paintIcon(game, g, this.getX(), this.getY());
					}
					lastVel = (int) world.getCrash().getVelX();
				} else {
					if(lastVel > 0) {
						crashStopDx.paintIcon(game, g, this.getX(), this.getY());
					} else {
						crashStopSx.paintIcon(game, g, this.getX(), this.getY());
					}
				}
			} else {
				crashSpin.paintIcon(game, g, this.getX(), this.getY());
			}
		}
	}
	
	public int getLives() {
		return lives;
	}
	
	public void addOneLife() {
		lives++;
	}
	
	public void removeOneLife() {
		lives--;
	}
	
	public int getWumpaFruits() {
		return wumpaFruits;
	}
	
	public void addWumpaFruits(final int wumpa) {
		if((wumpaFruits + wumpa) >= 100) {
			int tmp = (wumpaFruits + wumpa) - 100;
			wumpaFruits = tmp;
			this.addOneLife();
		} else {
			wumpaFruits += wumpa;
		}
	}
	
	public boolean isRotation() {
		return rotation;
	}
	
	public void setRotation(final boolean rotation) {
		this.rotation = rotation;
	}
	
}
