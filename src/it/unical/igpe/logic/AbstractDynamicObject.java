package it.unical.igpe.logic;

import it.unical.igpe.logic.interfaces.DynamicObject;

public abstract class AbstractDynamicObject extends AbstractStaticObject implements DynamicObject {
	private boolean falling = true;
	private boolean jumping = false;
	private float velX = 0;
	private float velY = 0;
	private float gravity = 2.0f;
	
	public AbstractDynamicObject(final World world, final int x, final int y, final int width, final int height) {
		super(world, x, y, width, height);
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	public boolean isJumping() {
		return jumping;
	}
	
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	public float getVelY() {
		return velY;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public void setVelocity(final float velX, final float velY) {
		this.velX = velX;
		this.velY = velY;
	}
	
	public float getGravity() {
		return gravity;
	}
	
	public void setGravity(final float gravity) {
		this.gravity = gravity;
	}
	
	public void update() {
		if(this.isFalling()) {
			this.setVelY(this.getGravity());
			gravity += 0.05f;
			if(velY >= 10) {
				velY = 10;
			}
		} else {
			gravity = 2.0f;
		}
		this.setX(this.getX() + (int) this.getVelX());
		this.setY(this.getY() + (int) this.getVelY());
	}
}
