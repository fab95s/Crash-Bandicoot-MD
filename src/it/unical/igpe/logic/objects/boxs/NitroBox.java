package it.unical.igpe.logic.objects.boxs;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.AbstractStaticObject;
import it.unical.igpe.logic.World;

public class NitroBox extends AbstractStaticObject {
	private Image box = ImageManager.getNitroBox().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	
	public NitroBox(World world, int x, int y) {
		super(world, x, y, EditorPanel.slotDimension, EditorPanel.slotDimension);
	}
	
	public synchronized void draw(Graphics g) {
		if(!world.getNitro().isEmpty()) {
			Iterator<NitroBox> boxs = world.getNitro().iterator();
			while(boxs.hasNext()) {
				NitroBox nitro = boxs.next();
				g.drawImage(box, nitro.getX(), nitro.getY(), null);
			}
		}
	}
	
}
