package actors;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Loader {
	
	/**
	 * This class should not be instant
	 */
	private Loader() {}
	
	/**
	 * Load the assets of Knight
	 * @return the ArrayList storage the knight 
	 * 0 element is Idle
	 * 1 element is attack
	 * 2 element is Dead
	 */
	public static ArrayList<ArrayList<BufferedImage>> loadKnight()
	{
		/**the ArrayList storage the knight
		 * 0 element is Idle
		 * 1 element is attack
		 * 3 element is Dead
		 */
		ArrayList<ArrayList<BufferedImage>> knight = new ArrayList<>();
		
		
		ArrayList<BufferedImage> IdleImages = new ArrayList<>();
		for(int i = 0; i <=14; i++)
		{
			try {
				String num = String.format("%02d", i);
				BufferedImage temp = ImageIO.read(new File("assets/Knight/Idle/Idle_"+ num + ".png"));
				IdleImages.add(temp);
			}
			catch(IOException e)
			{
				System.out.print("knight idle load wrong");
			}
		}
		knight.add(IdleImages);
		
		
		//load knight attack
		ArrayList<BufferedImage> attackImages = new ArrayList<>();
		for(int i = 0; i<=8; i++)
		{
			try {
			BufferedImage temp = ImageIO.read(new File("assets/Knight/Attack/Attack_"+ i + ".png"));
			attackImages.add(temp);}
			catch(IOException e)
			{
				System.out.println("knight attack load wrong");
			}
		}
		knight.add(attackImages);
		
		//Load Knight Dead
		ArrayList<BufferedImage> DeadImages = new ArrayList<>();
		for(int i = 0; i <=14; i++)
		{
			try {
				String num = String.format("%02d", i);
				BufferedImage temp = ImageIO.read(new File("assets/Knight/Dead/Dead_"+ num + ".png"));
				DeadImages.add(temp);
			}
			catch(IOException e)
			{
				System.out.print("knight idle load wrong");
			}
		}
		knight.add(DeadImages);
		return knight;
	}

	/**
	 * * Load the assets of Skeleton
	 * @return the ArrayList storage the skeleton
	 * 0 element is Walk
	 * 1 element is attack
	 * 2 element is Dead
	 */
	public static ArrayList<ArrayList<BufferedImage>> loadSkeleton()
	{
		ArrayList<ArrayList<BufferedImage>> skeleton = new ArrayList<>();
		ArrayList<BufferedImage> IdleImages = new ArrayList<>();
		for(int i = 0; i <=12; i++)
		{
			try {
				String num = String.format("%02d", i);
				BufferedImage temp = ImageIO.read(new File("assets/Skeleton/Walk/Walk_"+ num + ".png"));
				IdleImages.add(temp);
			}
			catch(IOException e)
			{
				System.out.print("skeleton idle load wrong");
			}
		}
		skeleton.add(IdleImages);
	
		ArrayList<BufferedImage> attackImages = new ArrayList<>();
		for(int i = 0; i <=17; i++)
		{
			try {
				String num = String.format("%02d", i);
				BufferedImage temp = ImageIO.read(new File("assets/Skeleton/Attack/Attack_"+ num + ".png"));
				attackImages.add(temp);
			}
			catch(IOException e)
			{
				System.out.print("skeleton attack load wrong");
			}
		}
		skeleton.add(attackImages);
		
		ArrayList<BufferedImage> deadImages = new ArrayList<>();
		for(int i = 0; i <=14; i++)
		{
			try {
				String num = String.format("%02d", i);
				BufferedImage temp = ImageIO.read(new File("assets/Skeleton/Dead/Dead_"+ num + ".png"));
				deadImages.add(temp);
			}
			catch(IOException e)
			{
				System.out.print("skeleton dead load wrong");
			}
		}
		skeleton.add(deadImages);
		
		return skeleton;
	}

	public static ArrayList<ArrayList<BufferedImage>> loadArcher()
	{
		/**the ArrayList storage the knight
		 * 0 element is Idle
		 * 1 element is attack
		 * 2 element is Dead
		 */
		ArrayList<ArrayList<BufferedImage>> knight = new ArrayList<>();
		
		
		ArrayList<BufferedImage> IdleImages = new ArrayList<>();
		for(int i = 0; i <=7; i++)
		{
			try {
				BufferedImage temp = ImageIO.read(new File("assets/Archer/Idle/Idle_"+ i + ".png"));
				IdleImages.add(temp);
			}
			catch(IOException e)
			{
				System.out.print("knight idle load wrong");
			}
		}
		knight.add(IdleImages);
		
		
		//load knight attack
		ArrayList<BufferedImage> attackImages = new ArrayList<>();
		for(int i = 0; i<=13; i++)
		{
			try {
				String num = String.format("%02d", i);
				BufferedImage temp = ImageIO.read(new File("assets/Archer/Attack/sprite_"+ num + ".png"));
				attackImages.add(temp);}
			catch(IOException e)
			{
				System.out.println("knight attack load wrong");
			}
		}
		knight.add(attackImages);
		
		//Load Knight Dead
		ArrayList<BufferedImage> DeadImages = new ArrayList<>();
		for(int i = 0; i <=23; i++)
		{
			try {
				String num = String.format("%02d", i);
				BufferedImage temp = ImageIO.read(new File("assets/Archer/Dead/Dead_"+ num + ".png"));
				DeadImages.add(temp);
			}
			catch(IOException e)
			{
				System.out.print("knight idle load wrong");
			}
		}
		knight.add(DeadImages);
		return knight;
	}

	public static BufferedImage loadScene()
	{
		BufferedImage scene;
		try {
			scene = ImageIO.read(new File("assets/Scenes/Scenes.jpg"));
			return scene;
		}
		catch(IOException e)
		{
			System.out.println("Scene not found");
		}
		return null;
	}
	
	public static ArrayList<ArrayList<BufferedImage>> loadArrow()
	{
		ArrayList<ArrayList<BufferedImage>> arrow = new ArrayList<>();
		try {
			ArrayList<BufferedImage> temp = new ArrayList<>();
			temp.add(ImageIO.read(new File("assets/Arrow/Arrow.png")));
			arrow.add(temp);
			
			return arrow;
		}
		catch(IOException e)
		{
			System.out.println("Arrow not found");
		}
		return null;
	}
	
	public static BufferedImage loadGameOver()
	{
		BufferedImage GameOver;
		try {
			GameOver = ImageIO.read(new File("assets/Scenes/GameOver.png"));
			return GameOver;
		}
		catch(IOException e)
		{
			System.out.println("GameOver not found");
		}
		return null;
	}
}


