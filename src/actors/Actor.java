package actors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Actor extends Sprite implements Attack{
	
	protected static final int IDLE = 0;
	protected static final int ATTACK = 1;
	protected static final int DEADING = 2;
	protected static final int DEAD = 3;

	public int state;
	private int health; 		// Current health of an Actor object
	private int fullHealth;		// The max health if healed. Used in the drawn health bar.
	protected int attackDamage;  	// Damage this Actor does to another Actor.
	protected int coolDownCounter;// Current count of the cooldown.
	protected int coolDown;		// Starting cool down value 
	private double speed;		// The speed at which it moves
	private int deadCoolDown;
	private int idleCoolDown;
	protected Actor target;
	
	public Actor(Point2D.Double startingPosition, Point2D.Double initHitbox, ArrayList<ArrayList<BufferedImage>> img, int health, int coolDown,int deadCoolDown, int idleCoolDown, double speed, int attackDamage) {
		super(startingPosition, initHitbox, img);
		this.health = health;
		this.fullHealth = health;
		this.coolDownCounter = 0;
		this.coolDown = coolDown;
		this.speed = speed;
		this.attackDamage = attackDamage;
		this.deadCoolDown = deadCoolDown;
		this.idleCoolDown = idleCoolDown;
		this.state = IDLE;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void addCooldown() {
		coolDownCounter++;		
	}
	
	/**
	 * Update the internal state of the Actor.
	 */
	public void update() {
//		//check if Actor Dead
		switch(this.state)
		{
		case DEADING:
			this.deading();
			break;
		case IDLE:
			this.idle();
			break;
		case ATTACK:
			this.attack(this.target);
			break;
		}
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
		coolDownCounter = 0;
	}
	
	public void changeHealth(int change) {
		if(state == DEADING || state == DEAD)
			return;
		health += change;
		if(health <= 0) {
			health = 0;
			this.coolDownCounter = 0;
			state = DEADING;
			}
	}
	
	public boolean isAlive() {
		return (state != DEAD) ;
	}
	
	public boolean isNotDeaing()
	{
		return (state != DEADING);
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
//		if(!other.isAlive()&&!other.isNotDeaing())
//		{
//			state = IDLE;
//			target = null;
//			this.coolDownCounter = 0;
//			return;
//		}
//		if(state == IDLE)
//		{
//			state = ATTACK;
//			this.resetCoolDown();
//			this.setCurrentIgm(this.get(1, 0));
//		}
//		if(state == ATTACK)
//		{
		if(coolDownCounter == this.coolDown) {
			//the final frame of attack anime
		this.setCurrentIgm(this.get(ATTACK, coolDown));
		if(!(this instanceof Archer)) {
			other.changeHealth(-attackDamage);}
			this.resetCoolDown();
			if(target.state == DEADING || target.state == DEAD) {
				state = IDLE;
				this.target = null;
			}
			}
		else if(coolDownCounter < this.coolDown)
		{
			addCooldown();
			this.setCurrentIgm(this.get(1, coolDownCounter));
		}
//		}
	}
	
	public void deading()
	{
		this.setCurrentIgm(this.get(DEADING, coolDownCounter));
		coolDownCounter++;
		if(coolDownCounter >= deadCoolDown-1)
			this.state = DEAD;
//		{
//			this.setCurrentIgm(this.get(DEADING, coolDownCounter));
//			coolDownCounter++;
//		}
//		else
//		{
//			this.setCurrentIgm(this.get(DEADING, coolDownCounter));
//			state = DEAD;
//		}
	}
	
	public void idle()
	{
//		if(target!=null)
//		{
		this.setCurrentIgm(this.get(IDLE, coolDownCounter));
		coolDownCounter++;
		if(coolDownCounter >= idleCoolDown-1)
		{
			coolDownCounter = 0;
		}
//			coolDownCounter = 0;
//		}
//		if(coolDownCounter < idelCoolDown-1)
//		{
//			this.setCurrentIgm(this.get(IDLE, coolDownCounter));
//			coolDownCounter++;
//		}
//		else
//		{
//			this.setCurrentIgm(this.get(IDLE, idelCoolDown));
//			coolDownCounter = 0;
//		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(getCurrentImg(), (int)this.getPosition().getX(), (int)this.getPosition().getY(), 
				(int)Instance.getInstance().getHitBox().getX(), 
				(int)Instance.getInstance().getHitBox().getY(), null);
		this.drawHealthBar(g);
	}
	
	public void setTarget(Actor other)
	{
		this.target = other;
		coolDownCounter = 0;
	}
}
