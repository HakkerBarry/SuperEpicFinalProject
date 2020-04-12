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

}
