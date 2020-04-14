package actors;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Skeleton extends Actor {

	private int line;
	public Skeleton(Double startingPosition, int line) {
		super(startingPosition, Instance.getInstance().getHitBox(), Instance.getInstance().skeleton, 100, 17, 14, 12, -5, 20);
		this.line = line;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(getCurrentImg(), (int)this.getPosition().getX()-50, (int)this.getPosition().getY(), 
				(int)Instance.getInstance().getHitBox().getX(), 
				(int)Instance.getInstance().getHitBox().getY(), null);
		this.drawHealthBar(g);
	}
	
	@Override
	public void idle()
	{
		super.idle();
		super.shiftPosition(new Point2D.Double(super.getSpeed(),0));
		Actor[][] defenders = Instance.getInstance().getDefenders();
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(defenders[i][j] == null)
					continue;
				if(isCollidingOther(defenders[i][j]) && defenders[i][j].isAlive())
				{
					setTarget(defenders[i][j]);
					this.resetCoolDown();
					defenders[i][j].setTarget(this);
				}
			}
		}
	}
	
	@Override
	public void update()
	{
		//System.out.println("Skeleton called");
		//super.update();
		//check if Actor Dead
		if(this.state == DEAD)
		{
			Instance.getInstance().enemies.remove(this);
		}
		
		if(this.state == DEADING)
		{
			this.deading();
			return;
		}
//		//check if attacking
		if(this.target != null)
		{
			super.attack(target);
			return;
		}
			
		if(this.state == IDLE)
		{
			this.idle();
		}
	}
	
	
}
