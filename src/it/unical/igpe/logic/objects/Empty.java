package it.unical.igpe.logic.objects;

import it.unical.igpe.graphics.EditorPanel;
import it.unical.igpe.logic.interfaces.StaticObject;

public class Empty implements StaticObject {
	private final int x;
	private final int y;
	
	public Empty(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean intersection(final StaticObject other) {
		return false;
	}
	
	public int getWidth() {
		return EditorPanel.slotDimension;
	}
	
	public int getHeight() {
		return EditorPanel.slotDimension;
	}
}
