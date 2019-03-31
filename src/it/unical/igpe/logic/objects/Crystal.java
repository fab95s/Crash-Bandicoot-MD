package it.unical.igpe.logic.objects;

import java.awt.Graphics;
import java.awt.Image;

import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.AbstractStaticObject;
import it.unical.igpe.logic.World;

public class Crystal extends AbstractStaticObject {
	private Image crystal = ImageManager.getCrystal();
	
	public Crystal(World world, int x, int y, int width, int height) {
		super(world, x, y, width, height);
	}
	
	public void draw(Graphics g) {
		g.drawImage(crystal, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
	}
	
}
