package src;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel{
	
	//private File fscore;
	
	private ArrayList<Score> scorelist;
	
	private static final String fscore = "score.dat";
	
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	
	public ScoreBoard(){
		
		super(new GridBagLayout());
		
		scorelist = new ArrayList<Score>();
		
		if(!(new File(fscore).exists())){
			this.addScore("Winner", 100);
		}
	}
	
	public ArrayList<Score> getScores(){
		
		loadScoreFile();
		sort();
		return scorelist;
		
	}
	
	public void sort(){
		System.out.print("tri des scores");
	}
	
	public void addScore(String playerName, int score){
		
		loadScoreFile();
		scorelist.add(new Score(playerName, score));
		updateScoreFile();
		
	}
	
	public void loadScoreFile(){
		
		try{
			ois = new ObjectInputStream(new FileInputStream(fscore));
			scorelist = (ArrayList<Score>) ois.readObject();
		} catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
        	try{
        		if(oos != null){
        			oos.flush();
        			oos.close();
        		}
        	}catch(IOException e){
        		System.out.println("[Laad] IO Error: " + e.getMessage());
        	}
        }
		
	}
	
	public void updateScoreFile(){
		
		try{
			oos = new ObjectOutputStream(new FileOutputStream(fscore));
			oos.writeObject(scorelist);
		}catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
        	try{
        		if(oos != null){
        			oos.flush();
        			oos.close();
        		}
        	}catch(IOException e){
        		System.out.println("[Laad] IO Error: " + e.getMessage());
        	}
        }
		
		afficherScore();
		
	}
	

	
	public void afficherScore(){
		
		
		this.removeAll();
		
		String score;
		ArrayList<Score> scores;
		scores = getScores();
		
		int i = 0;
		int x = scores.size();
		
		GridBagConstraints c = new GridBagConstraints();
		
		//System.out.println("avant la boucle");
		
		while(i < x && i < 5){
			score = scores.get(i).toString();
			//System.out.println(score);
			JLabel jl = new JLabel(score);
			//c.fill = GridBagConstraints.HORIZONTAL;
	        c.gridx = 1;
	        c.gridy = i;
			this.add(jl, c);
			i++;
		}
	}
}
