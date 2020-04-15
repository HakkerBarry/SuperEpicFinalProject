package level;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import actors.Actor;
import actors.Archer;
import actors.Grid;
import actors.Instance;
import actors.Knight;
import actors.Skeleton;


/**
 * @author Zixuan, Dhundup Tsering
 * Uid: u1303111, u1257529
 */
public class Game extends JPanel implements ActionListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	/**
	 * the main tick call the update of ever frame
	 */
	private Timer tick;
	static BufferedImage scene;
	Controller controller;
	public Actor[][] defender;
	public ArrayList<Actor> enemies;
	
	private Knight k1,k2,k3,k4,k5;
	private Archer a1,a2,a3,a4,a5;
	
	/**
	 * Constructor
	 */
	public Game()
	{
		scene = Instance.getInstance().getScene();
		setPreferredSize(new Dimension(1440,900));
		controller = new Controller();
		tick = new Timer(50,this);
		k1 = new Knight(Grid.getCellPosition(1, 1));
		a1 = new Archer(Grid.getCellPosition(0, 1));
		
		
		defender = new Actor[5][9];
		enemies = new ArrayList<>();
		Instance.getInstance().setDefenders(defender);
		Instance.getInstance().setEnemies(enemies);


		// test area--------------------
		defender[1][0] = k1;
		defender[1][1] = k2;
		defender[1][2] = k3;
		defender[1][3] = k4;
		defender[1][4] = k5;		
		defender[0][0] = a1;
		defender[0][1] = a2;
		defender[0][2] = a3;
		defender[0][3] = a4;
		defender[0][4] = a5;
		// ---------------------------------
		tick.start();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		//Loop each reference in array draw from far to close defender
		
		g.drawImage(scene, 0, 0, null);
		
		for(int y = 0; y < 5; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(defender[y][x] == null)
					continue;
				if(!defender[y][x].isAlive())
				{
					defender[y][x] = null;
					continue;
				}
				defender[y][x].update();
				defender[y][x].draw(g);
			}
		}

		ArrayList<Actor> deadEnemies = new ArrayList<>();
		for(Actor enemy: enemies)
		{
			if(!enemy.isAlive())
			{
				deadEnemies.add(enemy);
				continue;
			}
			enemy.update();
			enemy.draw(g);
		}
		//remove dead Enemies
		for(Actor dead :deadEnemies)
		{
			enemies.remove(dead);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	
	
	/**
	 * The main function for the game
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame mainFrame = new JFrame();
		Game epic = new Game();
		mainFrame.add(epic);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		controller.mouseClicked(e);
		System.out.println("mouseClicked: x position is " + e.getX() + " y position is "+ e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		controller.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
