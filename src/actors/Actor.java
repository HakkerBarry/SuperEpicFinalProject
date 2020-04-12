package actors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Actor extends Sprite {
	
	private static final int IDLE = 0;
	private static final int ATTACK = 1;
	private static final int DEAD = 2;

	private int health; 		// Current health of an Actor object
	private int fullHealth;		// The max health if healed. Used in the drawn health bar.
	protected int attackDamage;  	// Damage this Actor does to another Actor.
	private int coolDownCounter;// Current count of the cooldown.
	private int coolDown;		// Starting cool down value 
	private double speed;		// The speed at which it moves
	
	public Actor(Point2D.Double startingPosition, Point2D.Double initHitbox) {
		super(startingPosition, initHitbox);
		// TODO Auto-generated constructor stub
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void decrementCooldown() {
		coolDownCounter--;		
	}
	
	/**
	 * Update the internal state of the Actor. This means decrement the cool down counter.
	 */
	public void update() {
		decrementCooldown();
	}
	
	/**
	 * If the cooldown counter hits 0, the Actor is ready to do something.
	 * @return
	 */
	public boolean readyForAction() {
		if (coolDownCounter <= 0)
			return true;
		return false;
	}
	
	public void resetCoolDown() {
		coolDownCounter = coolDown;
	}
	
	public void changeHealth(int change) {
		health += change;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	/**
	 * Since we don't have an easy way of showing health using a nice series of images,
	 * provide health feedback with a health status bar.
	 * @param g
	 */
	public void drawHealthBar(Graphics g) {
		Point2D.Double pos = this.getPosition();
		Point2D.Double box = this.getHitbox();
	    g.setColor(Color.BLACK);  
		g.drawRect((int)pos.getX(),(int) pos.getY() , (int)box.getX(), 5);  
	    g.setColor(Color.RED);  
		g.fillRect((int)pos.getX(),(int) pos.getY() , (int)(box.getX() * this.health / (double)this.fullHealth), 5);  
	}
	
	public void removeAction(ArrayList<Actor> others) {
		
	}

	/**
	 * An Actor doesn't need to set collision status, but a Zombie can override this.
	 * @param other
	 */
	public void setCollisionStatus(Actor other) {
	}
}
