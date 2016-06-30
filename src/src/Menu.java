package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		nameField = new JTextField("default name", 20); // Here 20 gives a hint on the width of the textfield
        //nameField.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        this.add(nameField, c);
        
        JLabel nameLabel = new JLabel("Player Name : ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        this.add(nameLabel, c);

        
       JLabel instructions = new JLabel("Use UP and DOWN to move");
       c.fill = GridBagConstraints.HORIZONTAL;
       c.insets = new Insets(10,10,10,10); //marges autour du composant
       c.gridx = 3; //position colonne
       c.gridy = 0; //position ligne
       this.add(instructions, c);
      
       
		/*ImageIcon ii = new ImageIcon(getClass().getResource("/fond4.png"));
        //Image image = ii.getImage();*/
	}
	
	  /*@Override
	    public void actionPerformed(ActionEvent e) {
	        Frame.setPlayerName(nameField.getText());
	        System.out.println("actionevent sur le nom");
	    }*/
	
	public void setPlayerName(){
		
		if(Frame.getPlayerName() == "default name")
			Frame.setPlayerName("Anonyme");
		else
			Frame.setPlayerName(nameField.getText());
		
	}

}
