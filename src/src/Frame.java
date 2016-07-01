package src;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
	
	//private JButton bouton;
	//private JButton boutonStart;
	
	private CardLayout cl = new CardLayout(); //this layout is used to switch from different JPanel
	private JPanel content = new JPanel(); //this JPanl will contain the panel for the game of the panel for the game over screen
	private Game game;
	private Menu menu;
	
	private BackgroundSound bg;
	
	private ScoreBoard scoreboard;
	private Options options;
	
	private static String playerName;
	
	private static String theme = "pokemon";
	
	public static boolean previousmenu = true;

	
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
				
		content.setLayout(cl);
		//content.add(game, "Game");
		content.add(menu, "Menu");
	    
	    this.getContentPane().add(content);
	    //this.add(boutonStart, BorderLayout.SOUTH);
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
		
		if(((JButton)e.getSource()).getName().equals("startButton"))
			doStart();
		else if(((JButton)e.getSource()).getName().equals("optionsButton"))
			doOptions();
		else if(((JButton)e.getSource()).getName().equals("okButton"))
			doValidate();
		else if(((JButton)e.getSource()).getName().equals("skinButton"))
			doSkinChange();
		else if(((JButton)e.getSource()).getName().equals("deleteButton"))
			doDelete();
			
	}
	
	public void doOptions(){
		
		//menu.setPlayerName();
		options = new Options();
		//options.setBackground(Color.BLACK);
		content.add(options, "Options");
		cl.show(content, "Options");
		
	}
	
	private void doSkinChange() {
		//options.setBackground(Color.white);
		options.setPlayerName();
		if(Frame.getTheme() == "pokemon")
			Frame.setTheme("theme2");
		else
			Frame.setTheme("pokemon");
		options = new Options();
		//content.removeAll();
		content.add(options, "Options2");
		cl.show(content, "Options2");
	}
	
	public void doDelete(){
		
		int i = options.showMessage();
		if(i==1)
			scoreboard.deleteScores();
	}
	
	public void doValidate(){
		
		options.setPlayerName();
		if(previousmenu){
			menu = new Menu();
			cl.show(content, "Menu");
		}
		else{
			//content.removeAll();
			scoreboard = new ScoreBoard();
			content.add(scoreboard, "ScoreBoard");
			cl.show(content, "ScoreBoard");
		}
			
	}
	
	public void doTryAgain(){
		content.removeAll();
		game = new Game();
		content.add(game, "Game");
		cl.show(content, "Game");
		//this.remove(bouton);
		bg.play("game");
		//game.requestFocus();
	}
	
	public void doStart(){
		
		//menu.setPlayerName();
	
		//Frame.setPlayerName(options.getPlayerName());
		//content.removeAll();
		
		game = new Game();
		content.add(game, "Game");
		content.remove(menu);
		cl.show(content, "Game");
		//this.remove(boutonStart);
		bg.play("game");
	}
	
	public static Frame getFrame(){
		
		return frame; 
		
	}
	
	public static void setPlayerName(String name){
		
		playerName = name;
		
	}
	
	public static String getPlayerName(){
		
		if(playerName == null)
			return "Anonyme";
		
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
		//this.add(bouton, BorderLayout.SOUTH);
		
		bg.play("gameover");
	}

	public static String getTheme() {
		return theme;
	}

	public static void setTheme(String theme) {
		Frame.theme = theme;
	}

}
