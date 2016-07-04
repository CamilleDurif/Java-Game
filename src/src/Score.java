package src;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Score implements Serializable{
	
	private String playerName;
	private int score;
	
	public Score(String playerName, int score){
		
		this.playerName = playerName;
		this.score = score;
		
	}
	
	public String toString(){
		
		return playerName + " with " + score + " points \n";
	}
	
	public int getScore(){
		return score;
	}
	
	public String getPlayerName(){
		return playerName;
	}

}
