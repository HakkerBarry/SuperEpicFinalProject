package actors;

import java.awt.geom.Point2D.Double;

public class Knight extends Actor {
	
	
	public Knight(Double startingPosition) {
		super(startingPosition, Instance.getInstance().getHitBox(), Instance.getInstance().knight, 200, 8, 0, 10);
	}

	

}
