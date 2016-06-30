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
	private JButton boutonStart;
	
	private CardLayout cl = new CardLayout(); //this layout is used to switch from different JPanel
	private JPanel content = new JPanel(); //this JPanl will contain the panel for the game of the panel for the game over screen
	private Game game;
	private Menu menu;
	
	private BackgroundSound bg;
	
	private ScoreBoard scoreboard;
	
	private static String playerName;

	
	public Frame(){
		
		Frame.frame = this;
		
		setTitle("GameTest");
		setSize(new Dimension(500, 379));
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		ImageIcon img = new ImageIcon(getClass().getResource("/logo.png"));
		this.setIconImage(img.getImage());
		
		menu = new Menu();
		//Game game = new Game();
		//ScoreBoard menu = new ScoreBoard();
		
		bouton = new JButton("Try Again");
		bouton.addActionListener(this);
		
		boutonStart = new JButton("New Game");
		boutonStart.addActionListener(this);
		
		content.setLayout(cl);
		//content.add(game, "Game");
		content.add(menu, "Menu");
	    
	    this.getContentPane().add(content, BorderLayout.CENTER);
	    this.add(boutonStart, BorderLayout.SOUTH);
	    //cl.show(content, "Menu");
	    
	    bg = new BackgroundSound();
	    bg.play("fdf");
	    
	    scoreboard = new ScoreBoard();
	    
	    //Sound.play("heal.wav");
	
	}
	
	/*
	 * this function defines what happen when a player click on the "Try Again" button
	 * every panel is removed from content and a new Game panel is add to start from the beginning of the game
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == bouton)
			doTryAgain();
		else
			doStart();
			
	}
	
	public void doTryAgain(){
		content.removeAll();
		game = new Game();
		content.add(game, "Game");
		cl.show(content, "Game");
		this.remove(bouton);
		bg.play("game");
	}
	
	public void doStart(){
		
		menu.setPlayerName();
		
		game = new Game();
		content.add(game, "Game");
		cl.show(content, "Game");
		this.remove(boutonStart);
		bg.play("game");
	}
	
	public static Frame getFrame(){
		
		return frame; 
		
	}
	
	public static void setPlayerName(String name){
		
		playerName = name;
		
	}
	
	public static String getPlayerName(){
		
		return playerName;
		
	}
	
	/*
	 * this function is called by the game panel when the game is finished
	 * it adds a new gameover panel to the content, which is the one shown by the content
	 */
	public void gameOver(int pscore, int result, int life){
		
		/*GameOver gameover = new GameOver(score, result, life);
		content.add(gameover, "GameOver");
		cl.show(content, "GameOver");
		this.add(bouton, BorderLayout.SOUTH);*/
		
		scoreboard.addScore(Frame.getPlayerName(), pscore);
		content.add(scoreboard, "ScoreBoard");
		cl.show(content, "ScoreBoard");
		this.add(bouton, BorderLayout.SOUTH);
		
		bg.play("gameover");
	}

}
