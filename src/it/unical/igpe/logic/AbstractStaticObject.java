package it.unical.igpe.logic;

import java.awt.Rectangle;

import it.unical.igpe.logic.interfaces.StaticObject;

public abstract class AbstractStaticObject implements StaticObject {
	private int x;
	private int y;
	private int width;
	private int height;
	protected final World world;
	
	public AbstractStaticObject(final World world, final int x, final int y, final int width, final int height) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(final int x) {
		if(x < 0) {
			this.x = 0;
		} else if(x > world.getWidth() - 1) {
			this.x = world.getWidth() - 1;
		} else {
			this.x = x;
		}
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(final int y) {
		if(y < 0) {
			this.y = 0;
		} else if(y > world.getHeight() - 1) {
			this.y = world.getHeight() - 1;
		} else {
			this.y = y;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(final int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(final int height) {
		this.height = height;
	}
	
	protected World getWorld() {
		return world;
	}
	
	public Rectangle getBound() {
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBoundTop() {
		return new Rectangle(x + 10, y, width - 20, 1);
	}
	
	public Rectangle getBoundBottom() {
		return new Rectangle(x + 10, y + height, width - 20, 1);
	}
	
	public Rectangle getBoundLeft() {
		return new Rectangle(x, y + 10, 1, height - 20);
	}
	
	public Rectangle getBoundRight() {
		return new Rectangle(x + width, y + 10, 1, height - 20);
	}
	
	public boolean intersection(final StaticObject other) {
		return intersectionInternal(other) || other instanceof AbstractStaticObject ? ((AbstractStaticObject) other).intersectionInternal(this) : false;
	}
	
	private boolean intersectionInternal(final StaticObject other) {
		if(other.getX() >= x && other.getX() <= x && other.getY() >= y && other.getY() <= y) {
			return true;
		}
		if(other.getX() >= x && other.getX() <= x && other.getY() <= y && other.getY() >= y) {
			return true;
		}
		if(other.getX() <= x && other.getX() >= x && other.getY() >= y && other.getY() <= y) {
			return true;
		}
		return false;
	}
}