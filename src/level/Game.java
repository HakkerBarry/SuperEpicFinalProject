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
	Actor[][] defender;
	ArrayList<Actor> enemies;
	
	private Knight k1, k2;
	private Archer a1;
	private Skeleton s1;
	
	/**
	 * Constructor
	 */
	public Game()
	{
		scene = Instance.getInstance().getScene();
		setPreferredSize(new Dimension(1440,900));
		controller = new Controller();
		tick = new Timer(30,this);
		k1 = new Knight(Grid.getCellPosition(1, 1));
		k2 = new Knight(Grid.getCellPosition(1,	0));
		a1 = new Archer(Grid.getCellPosition(2, 2));
		s1 = new Skeleton(Grid.getCellPosition(3, 3),3);
		
		defender = new Actor[5][9];
		enemies = new ArrayList<>();
		
		tick.start();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		//Loop each reference in array draw from far to close defender
		
		for(int y = 0; y < 5; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(defender[y][x] == null)
					continue;
				defender[y][x].update();
				defender[y][x].draw(g);
			}
		}
		
		for(Actor enemy: enemies)
		{
			enemy.update();
			enemy.draw(g);
		}
		g.drawImage(scene, 0, 0, null);
		
		k1.draw(g);
		k2.draw(g);
		a1.draw(g);
		s1.draw(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		k1.attack(k2);
		a1.attack(k2);
		s1.attack(s1);
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
