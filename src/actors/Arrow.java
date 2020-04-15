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
		super(startingPosition,Instance.getInstance().getHitBox(), Instance.getInstance().getArrow());
		// TODO Auto-generated constructor stub
		state = IDLE;
	}

	//the default state of arrow is to attack
	
	
	public void update()
	{
		ArrayList<Actor> enemies = Instance.getInstance().getEnemies();
		for(Actor enemy: enemies)
		{
			if(isCollidingOther(enemy) && enemy.isAlive())
			{
				//cause damege
				state = DEAD;
				enemy.changeHealth(-attackDamage);
			}
		}
		//System.out.
<<<<<<< Updated upstream
		this.shiftPosition(new Point2D.Double(5,0));
=======
		this.shiftPosition(new Point2D.Double(15,0));
>>>>>>> Stashed changes
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
