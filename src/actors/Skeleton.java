package actors;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Skeleton extends Actor {

	private int line;
	public Skeleton(Double startingPosition, int line) {
		super(startingPosition, Instance.getInstance().getHitBox(), Instance.getInstance().skeleton, 100, 17, 14, 12, -1, 20);
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
	}
	
}
