package actors;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import level.Game;

public class Instance {
	
	static Instance instance = null;
	static Game game = null;
	static BufferedImage scene;
	private Point2D.Double standardHitBox = new Point2D.Double(100,115);
	private Point2D.Double actorHitBox = new Point2D.Double(100,80);
	public Actor[][] defenders;
	public ArrayList<Actor> enemies;
	
	private ArrayList<ArrayList<BufferedImage>> arrow;
	public ArrayList<ArrayList<BufferedImage>> knight;
	public ArrayList<ArrayList<BufferedImage>> skeleton;
	public ArrayList<ArrayList<BufferedImage>> archer;
	
	private Instance()
	{
		/**
		 * 0 Idle
		 * 1 Attack
		 * 2 Dead
		 */
		knight = Loader.loadKnight();
		skeleton = Loader.loadSkeleton();
		archer = Loader.loadArcher();
		scene = Loader.loadScene();
		arrow = Loader.loadArrow();
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}
	
	public Game getGame()
	{
		return this.game;
	}
	
	public void setEnemies(ArrayList<Actor> enemies)
	{
		this.enemies = enemies;
	}
	
	public ArrayList<Actor> getEnemies()
	{
		return this.enemies;
	}
	
	public void setDefenders(Actor[][] defenders)
	{
		this.defenders = defenders;
	}
	
	public Actor[][]  getDefenders()
	{
		return this.defenders;
	}
	
	
	public Point2D.Double getHitBox()
	{
		return standardHitBox;
	}
	
	public Point2D.Double getActorBox()
	{
		return actorHitBox;
	}
	
	public BufferedImage getScene()
	{
		return scene;
	}
	

	public ArrayList<ArrayList<BufferedImage>> getArrow()
	{
		return arrow;
	}
	
	public static Instance getInstance()
	{
		if(instance != null)
			return instance;
		else
		{
			instance = new Instance();
			return instance;
		}
	}

}
