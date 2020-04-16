package level;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class scoreBoard extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<BufferedImage> images;
	private int imageIndex;
	private Timer timer;

	public scoreBoard(BufferedImage img, BufferedImage img2) {
		images = new ArrayList<>();
		images.add(img);
		images.add(img2);
		imageIndex = 0;
		
		timer = new Timer(20, this);
		timer.start();
		
		setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(images.get(imageIndex), 0, 0, this);
		imageIndex = (imageIndex + 1) % 2;
	}
	
	public static void main(String[] args) {
		// Create an overall app
		JFrame frame = new JFrame();
		// Exit on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BufferedImage image = null;
		BufferedImage image2 = null;
		try {
			image = ImageIO.read(new File("src/lec31/me.png"));
			image2 = ImageIO.read(new File("src/lec31/me2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel panel = new scoreBoard(image, image2);
		// Make the frame fit the window
		frame.add(panel);
		frame.pack();
		// Show it
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

}
