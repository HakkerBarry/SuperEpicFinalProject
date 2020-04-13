package actors;

import java.awt.geom.Point2D.Double;

public class Skeleton extends Actor {

	private int line;
	public Skeleton(Double startingPosition, int line) {
		super(startingPosition, Instance.getInstance().getHitBox(), Instance.getInstance().skeleton, 100, 17, 5, 20);
		this.line = line;
	}
}
