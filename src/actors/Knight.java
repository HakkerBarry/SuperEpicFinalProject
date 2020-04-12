package actors;

import java.awt.Graphics;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Knight extends Actor {
	
	
	public Knight(Double startingPosition, Double initHitbox, ArrayList<ArrayList<BufferedImage>> img, int health,
			int coolDown, double speed, int attackDamage) {
		super(startingPosition, initHitbox, img, 200, 13, 0, 10);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		g.drawImage(getCurrentImg(), (int)this.getPosition().getX(), (int)this.getPosition().getY(), null);
	}

}
