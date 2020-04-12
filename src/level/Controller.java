package level;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import actors.Grid;

public class Controller {
	
	private boolean mousepressed;
	
	public Controller()
	{
		mousepressed = false;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		mousepressed = true;
		if(Grid.isColliding(new Point2D.Double(e.getX(),e.getY())))
			Grid.getCellNum(new Point2D.Double(e.getX(),e.getY()));
		System.out.println("mouseClicked: x position is " + e.getX() + " y position is "+ e.getY());
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mousepressed = false;
	}

	public boolean isMousepressed() {
		return mousepressed;
	}

}
