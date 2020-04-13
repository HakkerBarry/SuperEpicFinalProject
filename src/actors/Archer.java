package actors;

import java.awt.geom.Point2D.Double;

public class Archer extends Actor {

	public Archer(Double startingPosition) {
		super(startingPosition, Instance.getInstance().getHitBox(), Instance.getInstance().archer, 50, 13, 0, 20);
	}

}
