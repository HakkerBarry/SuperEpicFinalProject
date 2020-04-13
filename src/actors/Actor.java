package actors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Actor extends Sprite implements Attack{
	
	private static final int IDLE = 0;
	private static final int ATTACK = 1;
	private static final int DEAD = 2;

	public int state;
	private int health; 		// Current health of an Actor object
	private int fullHealth;		// The max health if healed. Used in the drawn health bar.
	protected int attackDamage;  	// Damage this Actor does to another Actor.
	private int coolDownCounter;// Current count of the cooldown.
	private int coolDown;		// Starting cool down value 
	private double speed;		// The speed at which it moves
	
	public Actor(Point2D.Double startingPosition, Point2D.Double initHitbox, ArrayList<ArrayList<BufferedImage>> img, int health, int coolDown, double speed, int attackDamage) {
		super(startingPosition, initHitbox, img);
		this.health = health;
		this.fullHealth = health;
		this.coolDownCounter = coolDown;
		this.coolDown = coolDown;
		this.speed = speed;
		this.attackDamage = attackDamage;
		this.state = IDLE;
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
		
	}
	
	/**
	 * If the cooldown counter hits 0, the Actor is ready to do something.
	 * @return
	 */
	public boolean readyForAction() {
		if (coolDownCounter < 0)
			return true;
		return false;
	}
	
	public void resetCoolDown() {
		coolDownCounter = coolDown;
	}
	
	public void changeHealth(int change) {
		health += change;
		if(health <= 0)
			state = DEAD;
	}
	
	public boolean isAlive() {
		return state!=DEAD ;
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

	@Override
	public void attack(Actor other) {
		if(state == DEAD)
			return;
		if(state == IDLE)
		{
			state = ATTACK;
			this.resetCoolDown();
			this.setCurrentIgm(this.get(1, 0));
		}
		if(state == ATTACK)
		{
			if(coolDownCounter == 0) {
				this.setCurrentIgm(this.get(1, coolDown));
				other.changeHealth(-attackDamage);
				decrementCooldown();
				state = IDLE;
				}
			else if(coolDownCounter > 0)
			{
				decrementCooldown();
				this.setCurrentIgm(this.get(1, coolDown - coolDownCounter));
			}
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(getCurrentImg(), (int)this.getPosition().getX(), (int)this.getPosition().getY(), 
				(int)Instance.getInstance().getHitBox().getX(), 
				(int)Instance.getInstance().getHitBox().getY(), null);
	}
}
