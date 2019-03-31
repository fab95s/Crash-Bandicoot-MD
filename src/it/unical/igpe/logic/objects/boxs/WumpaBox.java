package it.unical.igpe.logic.objects.boxs;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.Random;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.AbstractStaticObject;
import it.unical.igpe.logic.World;

public class WumpaBox extends AbstractStaticObject {
	private Image box = ImageManager.getWumpaBox().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	
	public WumpaBox(World world, int x, int y) {
		super(world, x, y, EditorPanel.slotDimension, EditorPanel.slotDimension);
	}
	
	public synchronized void draw(Graphics g) {
		if(!world.getWumpa().isEmpty()) {
			Iterator<WumpaBox> boxs = world.getWumpa().iterator();
			while(boxs.hasNext()) {
				WumpaBox wumpa = boxs.next();
				g.drawImage(box, wumpa.getX(), wumpa.getY(), null);
			}
		}
	}
	
	public int getWumpaFruits() {
		Random rand = new Random();
		return (rand.nextInt(16) + 5);
	}
	
}
