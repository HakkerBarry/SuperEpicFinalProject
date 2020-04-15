package actors;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Archer extends Actor {

	private int line;
	public Archer(Double startingPosition,int line) {
		super(startingPosition, Instance.getInstance().getActorBox(), Instance.getInstance().archer, 50, 13, 23, 7, 0, 20);
		this.line = line;
	}

	public void update()
	{	
		super.update();
		for(Actor temp :(Instance.getInstance().getGame().enemies))
		{
			Skeleton skeleton = (Skeleton)temp;
			if(skeleton.getLine() == this.line)
				this.setTarget(temp);
		}
		if(state == IDLE)
		{
			this.idle();
		}
	}
	
	@Override
	public void attack(Actor other) 
	{
		if(!other.isAlive())
		{
			state = IDLE;
			target = null;
			this.coolDownCounter = 0;
			return;
		}
		if(state == ATTACK)
		{
			if(coolDownCounter == this.coolDown) {//the final frame of attack anime
				this.setCurrentIgm(this.get(ATTACK, coolDown));
				Arrow arrow = new Arrow(new Point2D.Double(this.getPosition().getX(),this.getPosition().getY()));
				//add arrow to list in game
				Instance.getInstance().getGame().arrows.add(arrow);
				this.resetCoolDown();
				state = IDLE;
				}
			else if(coolDownCounter < this.coolDown)
			{
				addCooldown();
				this.setCurrentIgm(this.get(1, coolDownCounter));
			}
		}
		
		
	}
}
