package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Options extends JPanel implements ActionListener{
	
	private JTextField nameField;
		
	public Options(){
		
		super(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		String playerName = Frame.getPlayerName();
		
		JLabel nameLabel = new JLabel("Set Player Name");
		Myfont.setFontSize(nameLabel, 15);
        c.gridy = 1;
        this.add(nameLabel, c);
		
		nameField = new JTextField(playerName, 15); 
		Myfont.setFontSize(nameField, 15);
        c.gridy = 2;
        this.add(nameField, c);
        
        Button skinButton = new Button("Change Skin");
        skinButton.setName("skinButton");
        c.gridy = 3;
        this.add(skinButton, c);

        Button deleteButton = new Button("Delete scores");
        deleteButton.setName("deleteButton");
        c.gridy = 4;
        this.add(deleteButton, c);
        
        Button okButton = new Button("OK");
        okButton.setName("okButton");
        c.gridy = 5;
        this.add(okButton, c);
		
        Frame frame = Frame.getFrame();
        skinButton.addActionListener(frame);
        deleteButton.addActionListener(frame);
        okButton.addActionListener(frame);
	}
	
	public void showMessage(){
		
		Button yes = new Button("Yes");
		yes.setName("Yes");
		yes.addActionListener(Frame.getFrame());
		yes.addActionListener(this);
		
		Button no = new Button("No");
		no.setName("No");
		no.addActionListener(Frame.getFrame());
		no.addActionListener(this);
		
		Button[] buttons = {yes, no};
		
		JLabel jl = new JLabel("Are you sure ?");
		Myfont.setMyfont(jl);
		
		UIManager.put("OptionPane.background", new Color(253,253,253));
		UIManager.put("Panel.background", new Color(253,253,253));
		
		JOptionPane.showOptionDialog(null, jl, "Delete Scores", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[1]);
		
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
	
	@Override
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		ImageIcon ii = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_back.png"));
        Image image = ii.getImage();
		g.drawImage(image, 0, 0, null);
		
		
		g.drawRect(0, 0, 500, 500);
		g.setColor(new Color(255,255,255,200));
		g.fillRect(0, 0, 500, 500);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		JOptionPane.getRootFrame().dispose();
	}
}
