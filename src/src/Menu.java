package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel{
	
	//private final int B_WIDTH = 500;
	//private final int B_HEIGHT = 380;
	
	public Menu(){
		
		super(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
        
       JLabel instructions = new JLabel("Use UP and DOWN to move");
       c.insets = new Insets(10,10,10,10); //marges autour du composant. valable pour TOUS les composants
       //c.gridx = 2; //position colonne
       c.gridy = 0; //position ligne
       this.add(instructions, c);
       
       JButton startButton = new JButton("New Game");
       startButton.setName("startButton");
       c.gridy = 2;
       this.add(startButton, c);
       
       JButton optionButton = new JButton("Options");
       optionButton.setName("optionsButton");
       c.gridy = 3;
       this.add(optionButton, c);
      
       Frame frame = Frame.getFrame();
       startButton.addActionListener(frame);
       optionButton.addActionListener(frame);
		/*ImageIcon ii = new ImageIcon(getClass().getResource("/fond4.png"));
        //Image image = ii.getImage();*/
       
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
