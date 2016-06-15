package src;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener{
	
	public static Frame frame;
	
	private JButton bouton;
	
	private CardLayout cl = new CardLayout(); //this layout is used to switch from different JPanel
	private JPanel content = new JPanel(); //this JPanl will contain the panel for the game of the panel for the game over screen
	private Game game;
	
	public Frame(){
		
		Frame.frame = this;
		
		setTitle("GameTest");
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		ImageIcon img = new ImageIcon("logo.png");
		this.setIconImage(img.getImage());
		
		Game game = new Game();
		
		bouton = new JButton("Try Again");
		bouton.addActionListener(this);
		
		content.setLayout(cl);
		content.add(game, "Game");
	    
	    this.getContentPane().add(content, BorderLayout.CENTER); 
	    
	
	}
	
	/*
	 * this function defines what happen when a player click on the "Try Again" button
	 * every panel is removed from content and a new Game panel is add to start from the beginning of the game
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		
		content.removeAll();
		game = new Game();
		content.add(game, "Game");
		cl.show(content, "Game");
		this.remove(bouton);
			
	}
	
	public static Frame getFrame(){
		
		return frame; 
		
	}
	
	/*
	 * this function is called by the game panel when the game is finished
	 * it adds a new gameover panel to the content, which is the one shown by the content
	 */
	public void gameOver(int score, int result){
		
		GameOver gameover = new GameOver(score, result);
		content.add(gameover, "GameOver");
		cl.show(content, "GameOver");
		this.add(bouton, BorderLayout.SOUTH);
	}

}
