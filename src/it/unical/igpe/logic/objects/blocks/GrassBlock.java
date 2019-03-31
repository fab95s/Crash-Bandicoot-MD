package it.unical.igpe.logic.objects.blocks;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.World;

public class GrassBlock extends WorldBlock {
	private Image block = ImageManager.getGrassBlock().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST);
	
	public GrassBlock(World world, int x, int y) {
		super(world, x, y, EditorPanel.slotDimension, EditorPanel.slotDimension);
	}
	
	public synchronized void draw(Graphics g) {
		if(!world.getGrassBlocks().isEmpty()) {
			Iterator<GrassBlock> items = world.getGrassBlocks().iterator();
			while(items.hasNext()) {
				GrassBlock ter = items.next();
				g.drawImage(block, ter.getX(), ter.getY(), null);
			}
		}
	}
	
}
