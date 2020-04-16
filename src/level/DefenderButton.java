package level;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import actors.Instance;

public class DefenderButton extends JButton implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefenderButton()
	{
		super();
		//Instance.getInstance();
		addActionListener(this);
		this.setIcon(new ImageIcon(Instance.knight.get(0).get(0)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("mouse Clicked called");
	}

}
