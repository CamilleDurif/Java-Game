package src;

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
	
	private static Frame frame;
	
	private CardLayout cl = new CardLayout(); //this layout is used to switch from different JPanel
	private JPanel content = new JPanel(); //this JPanel will contain the panel for the game of the panel for the game over screen
	private Game game;
	private Menu menu;
	
	private BackgroundSound bg;
	
	private ScoreBoard scoreboard;
	private Options options;
	
	private Rules rules;
	
	private static String playerName;
	
	private static String theme = "pokemon";
	
	public static boolean previousmenu = true;
	
	private boolean delete = false;

	
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
		content.setLayout(cl);
		content.add(menu, "Menu");
	    this.getContentPane().add(content);
	    
	    bg = new BackgroundSound();
	    bg.play("fdf");
	    
	    scoreboard = new ScoreBoard();
	    
	    options = new Options();
		content.add(options, "Options");
	
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
		else if(((JButton)e.getSource()).getName().equals("rulesButton"))
			doRules();
		else if(((JButton)e.getSource()).getName().equals("exitButton"))
			doExitRules();
		else if(((JButton)e.getSource()).getName().equals("Yes"))
			delete = true;
		else if(((JButton)e.getSource()).getName().equals("No"))
			delete = false;
			
	}
	
	public void doOptions(){
		
		cl.show(content, "Options");
		
	}
	
	public void doRules(){
		
		rules = new Rules();
		content.add(rules,"Rules");
		cl.show(content, "Rules");
		
	}
	
	public void doExitRules(){
		
		cl.show(content,"Menu");
		
	}
	
	private void doSkinChange() {
		
		options.setPlayerName();
		
		if(Frame.getTheme() == "pokemon")
			Frame.setTheme("theme2");
		else
			Frame.setTheme("pokemon");
		
		options = new Options();
		content.add(options, "Options");
		cl.show(content, "Options");
	}
	
	public void doDelete(){
		
		options.showMessage();
		
		if(delete){
			scoreboard.deleteScores();
			delete = false;
		}
	}
	
	public void doValidate(){
		
		options.setPlayerName();
		if(previousmenu){
			cl.show(content, "Menu");
		}
		else{
			scoreboard = new ScoreBoard();
			content.add(scoreboard, "ScoreBoard");
			cl.show(content, "ScoreBoard");
		}
			
	}
	
	public void doTryAgain(){
		game = new Game();
		content.add(game, "Game");
		cl.show(content, "Game");
		bg.play("game");
	}
	
	public void doStart(){
		
		game = new Game();
		content.add(game, "Game");
		cl.show(content, "Game");
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
			return "Anonymous";
		
		return playerName;
		
	}
	
	/*
	 * this function is called by the game panel when the game is finished
	 * it adds a new gameover panel to the content, which is the one shown by the content
	 */
	public void gameOver(int pscore, int result, int life){
		
		scoreboard.addScore(Frame.getPlayerName(), pscore);
		content.add(scoreboard, "ScoreBoard");
		cl.show(content, "ScoreBoard");
		
		bg.play("gameover");
	}

	public static String getTheme() {
		return theme;
	}

	public static void setTheme(String theme) {
		Frame.theme = theme;
	}
	
	public void playBossTheme(){
	
			bg.play("boss");

	}
	
	public void playTheme(){
		bg.play("game");
	}
}
