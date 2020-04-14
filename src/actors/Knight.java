package actors;

import java.awt.geom.Point2D.Double;

public class Knight extends Actor {
	
	
	public Knight(Double startingPosition) {
		super(startingPosition, Instance.getInstance().getHitBox(), Instance.getInstance().knight, 200, 8, 14, 14, 0, 10);
	}

	@Override
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
