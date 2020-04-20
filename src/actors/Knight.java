package actors;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class Knight extends Actor {
	
	
	public Knight(Double startingPosition) {
		super(startingPosition, Instance.getInstance().getActorBox(), Instance.getInstance().knight, 400, 8, 14, 14, 0, 10);
	}
	
	public void idle()
	{
		super.idle();
		ArrayList<Actor> enemies = Instance.getInstance().getEnemies();
		for(Actor enemy: enemies)
		{
			if(this.isCollidingOther(enemy) && enemy.isAlive() && enemy.isNotDeaing())
			{
				this.target = enemy;
				this.resetCoolDown();
				state = ATTACK;
				break;
			}
		}
	}

}
