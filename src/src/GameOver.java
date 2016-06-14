package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GameOver extends JPanel{
	
	private final int B_WIDTH = 400;
	private final int B_HEIGHT = 400;
	
	private String result;
	private boolean win = false;

	/*
	 * this function is called by Frame
	 * the two parameters are given by the Game class
	 * the score is the number of aliens defeated
	 * the result is the number of aliens left 
	 * if there is no more aliens, the player win the game and the screen is different
	 */
	public GameOver(int score, int result){
			
		this.result = "Your score : " + score ;
		
		if(result==0)
			win = true;
		
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		
		String msg = "Game Over";
		String gg = "You win !";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2 -40);
        if(win){
        	g.drawString(gg, (B_WIDTH - fm.stringWidth(gg)) / 2, B_HEIGHT / 2 );
        	g.drawString(result, (B_WIDTH - fm.stringWidth(result)) / 2, B_HEIGHT / 2  -20);
        	}
        else
        	g.drawString(result, (B_WIDTH - fm.stringWidth(result)) / 2, B_HEIGHT / 2 - 20);
        
	}
	
}
