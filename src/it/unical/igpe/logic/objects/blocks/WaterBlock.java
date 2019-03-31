package it.unical.igpe.logic.objects.blocks;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import javax.swing.ImageIcon;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.graphics.GamePanel;
import it.unical.igpe.graphics.ImageManager;
import it.unical.igpe.logic.World;

public class WaterBlock extends WorldBlock {
	ImageIcon block = new ImageIcon(ImageManager.getWaterBlock().getImage().getScaledInstance(EditorPanel.slotDimension, EditorPanel.slotDimension, Image.SCALE_FAST));
	
	public WaterBlock(World world, int x, int y) {
		super(world, x, y, EditorPanel.slotDimension, EditorPanel.slotDimension);
	}
	
	public synchronized void draw(Graphics g, GamePanel game) {
		if(!world.getWaterBlocks().isEmpty()) {
			Iterator<WaterBlock> items = getWorld().getWaterBlocks().iterator();
			while(items.hasNext()) {
				WaterBlock ter = items.next();
				block.paintIcon(game, g, ter.getX(), ter.getY());
			}
		}
	}
	
}
