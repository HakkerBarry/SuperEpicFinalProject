package actors;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class Arrow extends Sprite{

	private static int attackDamage = 20;
	private int state;
	private static final int IDLE = 0;
	private static final int DEAD = 1;
	
	public Arrow(Double startingPosition) {
		super(startingPosition,Instance.getInstance().getActorBox(), Instance.getInstance().getArrow());
		state = IDLE;
	}

	//the default state of arrow is to attack
	
	
	public void update()
	{
		if(this.state == DEAD)
			return;
		ArrayList<Actor> enemies = Instance.getInstance().getEnemies();
		for(Actor enemy: enemies)
		{
			if(this.isCollidingOther(enemy) && enemy.isAlive() && enemy.isNotDeaing())
			{
				//cause damege
				state = DEAD;
				enemy.changeHealth(-attackDamage);
				break;
			}
		}
		//System.out.

		this.shiftPosition(new Point2D.Double(15,0));
	}
	
	public void draw(Graphics g) {
		g.drawImage(getCurrentImg(), (int)this.getPosition().getX(), (int)this.getPosition().getY(), 
				(int)Instance.getInstance().getHitBox().getX(), 
				(int)Instance.getInstance().getHitBox().getY(), null);
	}
	
	public boolean isAlive() {
		return (state != DEAD) ;
	}
}
