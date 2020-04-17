package level;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import actors.Archer;
import actors.Grid;
import actors.Instance;
import actors.Knight;

public class Controller {
	
	private int state;
	private String type;
	
	private static final int DEFAULT = 0;
	private static final int SETTING = 1;
	
	public Controller()
	{
		type = null;
		state = DEFAULT;
	}
	
	public void stateToSetting(String type)
	{
		this.state = SETTING;
		this.type = type;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		//if not in grid do nothing
		if(!Grid.isColliding(new Point2D.Double(e.getX(),e.getY()))) {
			state = DEFAULT;
			this.type = null;
			System.out.println("not in grid");
			return;			
		}
		
		//get the cell num
		int gridNum = Grid.getCellNum(new Point2D.Double(e.getX(),e.getY()));
		System.out.println(gridNum);
		if(type == null)
			return;
		if(Instance.getInstance().getDefenders()[gridNum/9][gridNum%9]!=null)
			return;
		
		//if type is knight create night at the Cell
		if(type.equals("knight"))
		{
			if(Instance.getInstance().getGame().getMoney() >= 100) {
				Instance.getInstance().getDefenders()[gridNum/9][gridNum%9] = new Knight(Grid.getCellPosition(gridNum%9, gridNum/9));
				Instance.getInstance().getGame().setMoney(-100);
			}
		}
		
		//Create archer
		if(type.equals("archer"))
		{
			if(Instance.getInstance().getGame().getMoney() >= 500) {
				Instance.getInstance().getDefenders()[gridNum/9][gridNum%9] = new Archer(Grid.getCellPosition(gridNum%9, gridNum/9),gridNum/9);
				Instance.getInstance().getGame().setMoney(-500);
			}
		}
		System.out.println("mouseClicked: x position is " + e.getX() + " y position is "+ e.getY());
		state = DEFAULT;
	}
	
	public void mousePressed(MouseEvent e) {
		this.mouseClicked(e);
		
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	
	public boolean isSetting()
	{
		return state == SETTING;
	}

}
