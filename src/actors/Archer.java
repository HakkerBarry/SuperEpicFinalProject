package actors;

import java.awt.geom.Point2D.Double;

public class Archer extends Actor {

	public Archer(Double startingPosition) {
		super(startingPosition, Instance.getInstance().getHitBox(), Instance.getInstance().archer, 50, 13, 23, 7, 0, 20);
	}

	public void update()
	{
		if(this.state == DEADING)
		{
			this.deading();
			return;
		}
		//check if attacking
		if(this.target != null)
		{
			this.attack(target);
			return;
		}
		
			
		if(state == IDLE)
		{
			this.idle();
		}
	}
}
