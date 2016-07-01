package src;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Options extends JPanel{
	
	private JTextField nameField;
	
	public Options(){
		
		super(new GridBagLayout());
		
		this.setBackground(Color.black);
		
		if(Frame.getTheme() == "theme2")
			this.setBackground(Color.white);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		String playerName = Frame.getPlayerName();
		
		JLabel nameLabel = new JLabel("Change Name : ");
        c.gridy = 1;
        this.add(nameLabel, c);
		
		nameField = new JTextField(playerName, 15); 
        c.gridy = 2;
        this.add(nameField, c);
        
        JButton skinButton = new JButton("Change Skin");
        skinButton.setName("skinButton");
        c.gridy = 3;
        this.add(skinButton, c);
        
        JButton okButton = new JButton("OK");
        okButton.setName("okButton");
        c.gridy = 4;
        this.add(okButton, c);
		
        Frame frame = Frame.getFrame();
        skinButton.addActionListener(frame);
        okButton.addActionListener(frame);
	}
	
	public void setPlayerName(){
		
		if(Frame.getPlayerName() == "default name")
			Frame.setPlayerName("Anonyme");
		else
			Frame.setPlayerName(nameField.getText());
		
	}
	
	public String getPlayerName(){
		return nameField.getText();
	}
	
	
	/*@Override
	public void actionPerformed(ActionEvent e){
		
		//Frame.getFrame().
		
	}*/
	

}
