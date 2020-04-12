package actors;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Instance {
	
	static Instance instance = null;
	static Point2D.Double standardHitBox = new Point2D.Double(200,230);
	
	public ArrayList<ArrayList<BufferedImage>> knight;
	public ArrayList<ArrayList<BufferedImage>> Skeleton;
	public ArrayList<ArrayList<BufferedImage>> Archer;
	
	private Instance()
	{
		knight = Loader.loadKnight();
		knight = Loader.loadSkeleton();
		knight = Loader.loadArcher();
	}
	
	static public Instance getInstance()
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
