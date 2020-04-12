package level;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * @author Zixuan, Dhundup
 * Uid: u1303111,
 */
public class Game extends JPanel implements ActionListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	/**
	 * the main tick call the update of ever frame
	 */
	private Timer tick;
	BufferedImage scene;
	Controller controller;
	
	public Game()
	{
		try {
			scene = ImageIO.read(new File("assets/Scenes/Scenes.jpg"));
		}
		catch(IOException e)
		{
			System.out.println("Scene not found");
		}
		setPreferredSize(new Dimension(1440,900));
		controller = new Controller();
		tick = new Timer(30,this);
		tick.start();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(scene, 0, 0, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(controller.isMousepressed());
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