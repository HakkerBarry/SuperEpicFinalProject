package actors;

import java.awt.Graphics;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Knight extends Actor {
	
	public Knight(Double startingPosition, Double initHitbox, ArrayList<ArrayList<BufferedImage>> imgs) {
		super(startingPosition, initHitbox);
		// TODO Auto-generated constructor stub
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(currentImage, (int)this.getPosition().getX(), (int)this.getPosition().getY(), null);
	}

}
