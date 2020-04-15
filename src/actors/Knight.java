package actors;

import java.awt.geom.Point2D.Double;

public class Knight extends Actor {
	
	
	public Knight(Double startingPosition) {
		super(startingPosition, Instance.getInstance().getActorBox(), Instance.getInstance().knight, 200, 8, 14, 14, 0, 10);
	}

	@Override
	public void update()
	{
		super.update();
		
			
		if(state == IDLE)
		{
			this.idle();
		}
	}

}
