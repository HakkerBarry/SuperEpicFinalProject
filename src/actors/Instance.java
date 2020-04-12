package actors;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Instance {
	
	static Instance instance = null;
	static BufferedImage scene;
	static Point2D.Double standardHitBox = new Point2D.Double(200,230);
	
	public ArrayList<ArrayList<BufferedImage>> knight;
	public ArrayList<ArrayList<BufferedImage>> skeleton;
	public ArrayList<ArrayList<BufferedImage>> archer;
	
	private Instance()
	{
		knight = Loader.loadKnight();
		skeleton = Loader.loadSkeleton();
		archer = Loader.loadArcher();
		scene = Loader.loadScene();
	}
	
	
	public BufferedImage getScene()
	{
		return scene;
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
