package actors;

import java.awt.geom.Point2D;

public class Grid {
	
	private static int row = 5;
	private static int col = 9;
	private static Point2D.Double startPoint = new Point2D.Double(350,250);
	private static Point2D.Double cellDimension = new Point2D.Double(100,115);
	
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
	{
		return (int)((position.getY()-startPoint.getY())/cellDimension.getY())*9
		+(int)((position.getX()-startPoint.getX())/cellDimension.getX());
	}
	
	public static Point2D.Double getCellPosition(int x, int y)
	{
		if(x < 0|| x > col -1|| y < 0|| y > row -1 )
			return new Point2D.Double(-1,-1);
		Point2D.Double result = new Point2D.Double(startPoint.getX()+ x*cellDimension.getX(),startPoint.getY()+y*cellDimension.getY());
		return result;
	}
}
