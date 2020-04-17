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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import actors.Actor;
import actors.Arrow;
import actors.Grid;
import actors.Instance;
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
	private static BufferedImage scene;
	Controller controller;
	public Actor[][] defender;
	public ArrayList<Actor> enemies;
	public ArrayList<Arrow> arrows;
	
	private static DefenderButton knightButton;
	private static DefenderButton archerButton;
	
	//money; only be add when enemy be killed
	private int money;
	private JLabel moneyLabel;
	//A value control the difficulty of the game
	private int Threshold;

	/**
	 * Constructor
	 */
	public Game() {
		scene = Instance.getInstance().getScene();
		setPreferredSize(new Dimension(1440, 900));
		controller = new Controller();
		Instance.setController(controller);
		tick = new Timer(50, this);
		Threshold = 9990;
		
		//initialize money and label
		money = 3000;
		//moneyLabel.setIcon(icon);
		moneyLabel = new JLabel("money : " + money);
		
		defender = new Actor[5][9];
		enemies = new ArrayList<>();
		arrows = new ArrayList<>();
		Instance.getInstance().setDefenders(defender);
		Instance.getInstance().setEnemies(enemies);
		knightButton = new DefenderButton("knight");
		archerButton = new DefenderButton("archer");
		addMouseListener(this);
		
		add(archerButton);
		add(knightButton);
		add(moneyLabel);
		
		tick.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Loop each reference in array draw from far to close defender

		if(!tick.isRunning())
		{
			g.drawImage(Instance.getInstance().getGameOver(), 0,0,null);
			return;
		}
		g.drawImage(scene, 0, 0, null);

		// update and draw defender
		updateDefender(g);

		// update and draw arrow
		updateArrow(g);
		updateSkeleton(g);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		makeGameHarder();
		moneyLabel.setText("money : " + money);
		repaint();
	}

	/**
	 * The main function for the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
				JFrame mainFrame = new JFrame();
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Game epic = new Game();
				//epic.setLayout();
				Instance.getInstance().setGame(epic);
				mainFrame.add(epic);
				mainFrame.pack();
				mainFrame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		controller.mouseClicked(e);
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
		
		int seed = rand.nextInt(10000)+1;
		if(seed > Threshold)
		{
			int line = rand.nextInt(5);
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
			if(enemy.getPosition().getX() < 200)
				this.endGame();
		}
		// remove dead Enemies
		for (Actor dead : deadEnemies) {
			enemies.remove(dead);
			money += 50;
		}
	}

	public void endGame()
	{
		tick.stop();
		repaint();
	}
	/**
	 * decrease the threshold will make game harder
	 */
	private void makeGameHarder()
	{
		Threshold-- ;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public void setMoney(int moneyChange)
	{
		this.money += moneyChange;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		controller.mousePressed(e);
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
