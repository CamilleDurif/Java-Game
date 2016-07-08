package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Rules extends JPanel{
	
	
	public Rules(){
		
		super(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		JLabel jinst1 = new JLabel("Use UP and DOWN to move");
		c.gridy = 1;
		this.add(jinst1, c);
		
		JLabel jinst2 = new JLabel("Use SPACE to shoot");
		c.gridy = 2;
		this.add(jinst2, c);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus1.png"));
		JLabel jlab = new JLabel(i1);
		//c.fill = GridBagConstraints.VERTICAL;
		c.gridy = 3;
		c.gridx = 1;
		this.add(jlab, c);
		
		JLabel jlab2 = new JLabel("Upgrade missile shoot");
		c.anchor = GridBagConstraints.EAST;
		c.gridy = 3;
		c.gridx = 2;
		this.add(jlab2, c);
		
		ImageIcon i2 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus2.png"));
		JLabel jlab3 = new JLabel(i2);
		c.gridy = 4;
		c.gridx = 1;
		this.add(jlab3, c);
		
		JLabel jlab4 = new JLabel("Best missile shoot");
		c.anchor = GridBagConstraints.EAST;
		c.gridy = 4;
		c.gridx = 2;
		this.add(jlab4, c);
		
		ImageIcon i3 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus3.png"));
		JLabel jlab5 = new JLabel(i3);
		c.gridy = 5;
		c.gridx = 1;
		this.add(jlab5, c);
		
		JLabel jlab6 = new JLabel("Immune to next damage");
		c.anchor = GridBagConstraints.EAST;
		c.gridy = 5;
		c.gridx = 2;
		this.add(jlab6, c);
		
		
		JButton exitButton = new JButton("EXIT");
		exitButton.setName("exitButton");
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 6;
		
		this.add(exitButton, c);
		
		Frame frame = Frame.getFrame();
		exitButton.addActionListener(frame);
	}

}
