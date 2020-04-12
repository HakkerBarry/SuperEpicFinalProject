package actors;

import java.awt.geom.Point2D;

public class Grid {
	
	private static Point2D.Double startPoint = new Point2D.Double(350,250);
	private static Point2D.Double cellDimension = new Point2D.Double(200,215);
	
	public static boolean isColliding(Point2D.Double position)
	{
		if(position.getY() < startPoint.getY())
			return false;
		if(position.getX() < startPoint.getX())
			return false;
		if(position.getY() > startPoint.getY() + cellDimension.getY()*5)
			return false;
		if(position.getX() > startPoint.getX() + cellDimension.getX()*9)
			return false;
		return true;
	}
	
	public static int getCellNum(Point2D.Double position)
	{			//com * n
		return (int)((position.getY()-startPoint.getY())/cellDimension.getY()) 
				//add the colNum
				+ (int)((position.getX()-startPoint.getX())/cellDimension.getX());
	}
}