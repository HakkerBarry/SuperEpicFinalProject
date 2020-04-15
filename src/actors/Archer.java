package actors;

import java.awt.geom.Point2D.Double;

public class Archer extends Actor {

	public Archer(Double startingPosition) {
		super(startingPosition, Instance.getInstance().getActorBox(), Instance.getInstance().archer, 50, 13, 23, 7, 0, 20);
	}

	public void update()
	{
		super.update();
			
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
				System.out.println("a attack called");
				this.setCurrentIgm(this.get(ATTACK, coolDown));
				Arrow arrow = new Arrow(this.getPosition());
				//add arrow to list in game
				System.out.println(this.getPosition());
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
