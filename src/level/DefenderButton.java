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
	
	private String type;

	public DefenderButton(String type)
	{
		super();
		//Instance.getInstance();
		this.type = type;
		addActionListener(this);
		if(type.equals("knight")) {
			this.setIcon(new ImageIcon(Instance.getInstance().knight.get(0).get(0)));
			this.setText("100");
		}
		if(type.equals("archer")) {
			this.setIcon(new ImageIcon(Instance.getInstance().archer.get(0).get(0)));
			this.setText("500");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Instance.getInstance().getController().stateToSetting(type);
	}

	public String getType() {
		return type;
	}

}
