package it.unical.igpe.logic.objects.blocks;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.World;

public class LandBlock extends WorldBlock {
	Image block = ImageManager.getLandBlock().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	
	public LandBlock(World world, int x, int y) {
		super(world, x, y, EditorPanel.slotDimension, EditorPanel.slotDimension);
	}
	
	public synchronized void draw(Graphics g) {
		if(!world.getLandBlocks().isEmpty()) {
			Iterator<LandBlock> items = getWorld().getLandBlocks().iterator();
			while(items.hasNext()) {
				LandBlock ter = items.next();
				g.drawImage(block, ter.getX(), ter.getY(), null);
			}
		}
	}
	
}
