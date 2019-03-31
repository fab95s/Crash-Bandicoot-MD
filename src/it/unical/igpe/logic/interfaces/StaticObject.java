package it.unical.igpe.logic.interfaces;

public interface StaticObject {
	int getX();
	int getY();
	int getWidth();
	int getHeight();
	
	boolean intersection(final StaticObject other);
}
