package it.unical.igpe.logic.objects.boxs;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.AbstractStaticObject;
import it.unical.igpe.logic.World;

public class LifeBox extends AbstractStaticObject {
	private Image box = ImageManager.getLifeBox().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	
	public LifeBox(World world, int x, int y) {
		super(world, x, y, EditorPanel.slotDimension, EditorPanel.slotDimension);
	}
	
	public synchronized void draw(Graphics g) {
		if(!world.getLifes().isEmpty()) {
			Iterator<LifeBox> boxs = world.getLifes().iterator();
			while(boxs.hasNext()) {
				LifeBox life = boxs.next();
				g.drawImage(box, life.getX(), life.getY(), null);
			}
		}
	}
	
}
