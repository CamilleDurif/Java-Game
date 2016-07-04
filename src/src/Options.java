package src;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
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

        JButton deleteButton = new JButton("Delete scores");
        deleteButton.setName("deleteButton");
        c.gridy = 4;
        this.add(deleteButton, c);
        
        JButton okButton = new JButton("OK");
        okButton.setName("okButton");
        c.gridy = 5;
        this.add(okButton, c);
		
        Frame frame = Frame.getFrame();
        skinButton.addActionListener(frame);
        deleteButton.addActionListener(frame);
        okButton.addActionListener(frame);
	}
	
	public int showMessage(){
		new JOptionPane();
		int option = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Delete Scores", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(option == JOptionPane.OK_OPTION)
			return 1;
		else 
			return 0;
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

}
