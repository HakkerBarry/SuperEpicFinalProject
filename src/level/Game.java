package level;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import actors.Actor;
import actors.Archer;
import actors.Arrow;
import actors.Grid;
import actors.Instance;
import actors.Knight;
import actors.Skeleton;

/**
 * @author Zixuan Zhang, Dhundup Tsering Uid: u1303111, u1257529
 */
public class Game extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	/**
	 * the main tick call the update of ever frame
	 */
	private Timer tick;
	static BufferedImage scene;
	Controller controller;
	public Actor[][] defender;
	public ArrayList<Actor> enemies;
	public ArrayList<Arrow> arrows;

	// test area
	private Knight k1;
	
	//test area
	private Archer a1,a2,a3;
	//----------------------

	/**
	 * Constructor
	 */
	public Game() {
		scene = Instance.getInstance().getScene();
		setPreferredSize(new Dimension(1440, 900));
		controller = new Controller();
		tick = new Timer(50, this);

		// test area------------------------
		k1 = new Knight(Grid.getCellPosition(1, 1));
		a1 = new Archer(Grid.getCellPosition(0, 1),1);
		a2 = new Archer(Grid.getCellPosition(0, 2),2);
		a3 = new Archer(Grid.getCellPosition(0, 3),3);
		// ---------------------------------

		defender = new Actor[5][9];
		enemies = new ArrayList<>();
		arrows = new ArrayList<>();
		Instance.getInstance().setDefenders(defender);
		Instance.getInstance().setEnemies(enemies);

		// test area--------------------
		defender[1][1] = k1;
		defender[0][1] = a1;
		defender[0][2] = a2;
		defender[0][3] = a3;
		// ---------------------------------
		tick.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Loop each reference in array draw from far to close defender

		g.drawImage(scene, 0, 0, null);

		// update and draw defender
		updateDefender(g);

		// update and draw arrow
		updateArrow(g);
		updateSkeleton(g);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	/**
	 * The main function for the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		Game epic = new Game();
		Instance.getInstance().setGame(epic);
		mainFrame.add(epic);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		controller.mouseClicked(e);
		System.out.println("mouseClicked: x position is " + e.getX() + " y position is " + e.getY());
	}

	public void updateDefender(Graphics g) {
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 9; x++) {
				if (defender[y][x] == null)
					continue;
				if (!defender[y][x].isAlive()) {
					defender[y][x] = null;
					continue;
				}
				defender[y][x].update();
				defender[y][x].draw(g);
			}
		}
	}

	public void updateArrow(Graphics g) {
		//update and draw Arrow
		ArrayList<Arrow> deadArrow = new ArrayList<>();
		for (Arrow arrow : arrows) {
			if (!arrow.isAlive()) {
				deadArrow.add(arrow);
				continue;
			}
			arrow.update();
			arrow.draw(g);
		}
		
		//remove dead arrow
		for (Arrow dead : deadArrow) {
			arrows.remove(dead);
		}
	}

	/**
	 * Spwn and update Skeleton array
	 * @param g
	 */
	public void updateSkeleton(Graphics g) {
		
		Random rand =new Random();
		
		int seed = rand.nextInt(100)+1;
		if(seed > 97)
		{
			int line = rand.nextInt(5);
			System.out.println(line);
			double xCell = Grid.getCellPosition(0, line).getY();
			Skeleton skeleton = new Skeleton(new Point2D.Double(1430, xCell),line);
			enemies.add(skeleton);
		}
		
		// update and draw enemies
		ArrayList<Actor> deadEnemies = new ArrayList<>();
		for (Actor enemy : enemies) {
			if (!enemy.isAlive()) {
				deadEnemies.add(enemy);
				continue;
			}
			enemy.update();
			enemy.draw(g);
		}
		// remove dead Enemies
		for (Actor dead : deadEnemies) {
			enemies.remove(dead);
		}
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
