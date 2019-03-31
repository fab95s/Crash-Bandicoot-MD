package it.unical.igpe.logic.interfaces;

public interface DynamicObject extends StaticObject, Moovable {
	boolean isFalling();
	boolean isJumping();
	
	void update();
}
