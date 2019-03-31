package it.unical.igpe.logic.objects.blocks;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.World;

public class SteelBlock extends WorldBlock {
	Image block = ImageManager.getSteelBlock().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	
	public SteelBlock(World world, int x, int y) {
		super(world, x, y, EditorPanel.slotDimension, EditorPanel.slotDimension);
	}
	
	public synchronized void draw(Graphics g) {
		if(!world.getSteelBlocks().isEmpty()) {
			Iterator<SteelBlock> items = getWorld().getSteelBlocks().iterator();
			while(items.hasNext()) {
				SteelBlock ter = items.next();
				g.drawImage(block, ter.getX(), ter.getY(), null);
			}
		}
	}
	
}
