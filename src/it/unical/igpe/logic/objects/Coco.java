package it.unical.igpe.logic.objects;

import java.awt.Graphics;
import java.awt.Image;

import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.graphics.MultiplayerPanel;
import it.unical.igpe.logic.AbstractDynamicObject;
import it.unical.igpe.logic.World;

public class Coco extends AbstractDynamicObject {
	private int lives;
	private int wumpaFruits = 0;
	private boolean rotation = false;
	private Image coco = ImageManager.getCrashBandicoot();
	
	public Coco(World world, int x, int y, int width, int height) {
		super(world, x, y, width, height);
		lives = 4;
	}
	
	public void draw(Graphics g) {
		if(MultiplayerPanel.isMultiplayer) {
			g.drawString("Crash Lifes: " + getWorld().getCrash().getLives(), 100, 100);
			if(getWorld().getCrash().getLives() > 0) {
				g.drawImage(coco, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
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
