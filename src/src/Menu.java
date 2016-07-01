package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Menu extends JPanel{
	
	//private final int B_WIDTH = 500;
	//private final int B_HEIGHT = 380;
	
	private JTextField nameField;
	
	public Menu(){
		
		super(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
        
       JLabel instructions = new JLabel("Use UP and DOWN to move");
       //c.fill = GridBagConstraints.HORIZONTAL;
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
	}
	
	 

}
