package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel{
		
	public Menu(){
		
		super(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
        
       JLabel instructions = new JLabel("Welcome");
       Myfont.setFontSize(instructions, 30);
       c.insets = new Insets(10,10,10,10); //marges autour du composant. valable pour TOUS les composants
       //c.gridx = 2; //position colonne
       c.gridy = 0; //position ligne
       this.add(instructions, c);
       
       Button startButton = new Button("New Game");
       startButton.setName("startButton");
       c.gridy = 2;
       this.add(startButton, c);
       
       Button optionButton = new Button("Options");
       optionButton.setName("optionsButton");
       c.gridy = 3;
       this.add(optionButton, c);
       
       Button rulesButton = new Button("Rules");
       rulesButton.setName("rulesButton");
       c.gridy = 4;
       this.add(rulesButton, c);
      
       Frame frame = Frame.getFrame();
       startButton.addActionListener(frame);
       optionButton.addActionListener(frame);
       rulesButton.addActionListener(frame);
       
       this.setBackground(new Color(0,0,0,20));
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

}
