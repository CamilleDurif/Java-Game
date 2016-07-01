package src;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel implements ActionListener{
	
	private ArrayList<Score> scorelist; //list of different scores
	
	private static final String fscore = "score.dat"; //the name of the file.dat wich contain the list
	
	private boolean newRecord = false; //set to true if a new record is set at the end of a game
	private int index = 0; //to know where the new score is (for red label)
	
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	
	public ScoreBoard(){
		
		super(new GridBagLayout());
		
		scorelist = new ArrayList<Score>();
		
		File scoreFile = new File(fscore);
		if(!scoreFile.exists()){
			try{
				scoreFile.createNewFile();
				scorelist.add(new Score("winner", 100));
				scorelist.add(new Score("Looser", 0));
				updateScoreFile();
			}catch(IOException e){
				System.out.print("impossible de créer le fichier");
			}
		}
		
		afficherScore();
	}
	
	public ArrayList<Score> getScores(){
		
		loadScoreFile();
		return scorelist;
		
	}
	
	public void sort(){
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scorelist,comparator);
	}
	
	public void addScore(String playerName, int score){
		
		loadScoreFile();
		sort();
		Iterator<Score> i = scorelist.iterator();
		int j =0;
			while(i.hasNext()){
				Score s = i.next();
				if(score >= s.getScore()){
					Score newScore = new Score(playerName, score);
					scorelist.add(newScore);
					sort();
					updateScoreFile();
					newRecord = true;
					index = getIndex(newScore);
					if(index>5)
						newRecord = false;
					break;
				}
			}	
		
		afficherScore();
	}
	
	public int getIndex(Score score){
		
		int i =0;
		
		for(i=0; i<scorelist.size(); i++){
			if(scorelist.get(i).equals(score))
				return i;
		}
		
		return 0;
		
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
	}
	

	
	public void afficherScore(){
		
		
		this.removeAll();
		
		String score;
		ArrayList<Score> scores;
		scores = getScores();
		
		int i = 0;
		int x = scores.size();
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(10,10,10,10);
		
		while(i < x && i < 5){
			score = scores.get(i).toString();
			JLabel jl = new JLabel(score);
			if(i == index && i!=0 && newRecord)
				jl.setForeground(Color.RED);
	        c.gridx = 1;
	        c.gridy = i+5;
			this.add(jl, c);
			i++;
		}
		
		JLabel js = new JLabel("Best Scores");
		c.gridx = 1;
		c.gridy = 1;
		this.add(js, c);
		
		/*JLabel j = new JLabel(" ");
		c.gridx = 1;
		c.gridy = 2;
		this.add(j, c);*/
		
		if(newRecord && index < 5){
			JLabel jr = new JLabel("NEW RECORD");
			jr.setForeground(Color.BLUE);
			c.insets = new Insets(0,0,0,0);
			c.gridx = 1;
			c.gridy = 3;
			this.add(jr, c);
			
			/*JLabel j2 = new JLabel(" ");
			c.gridx = 1;
			c.gridy = 4;
			this.add(j2, c);*/
			
			newRecord = false;
		}
		
		/*JLabel j3 = new JLabel(" ");
		c.gridx = 1;
		c.gridy = 10;
		this.add(j3, c);*/
		
		c.insets = new Insets(10,10,10,10);
		
		JButton tryagainB = new JButton("Try Again ?");
		tryagainB.setName("tryagainB");
		tryagainB.addActionListener(this);
		c.gridy = 11;
		this.add(tryagainB, c);
		
		JButton optionsButton = new JButton("Options");
		optionsButton.setName("optionsButton");
		optionsButton.addActionListener(this);
		c.gridy = 12;
		this.add(optionsButton, c);
	}
	

	public void deleteScores() {
		
		scorelist.removeAll(scorelist);
		scorelist.add(new Score("winner", 100));
		scorelist.add(new Score("Looser", 0));
		updateScoreFile();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e){
		
		if(((JButton)e.getSource()).getName().equals("tryagainB"))
			Frame.getFrame().doTryAgain();
		else if(((JButton)e.getSource()).getName().equals("optionsButton")){
			Frame.getFrame().doOptions();
			Frame.previousmenu = false;
		}
		
	}
	
	
	class ScoreComparator implements Comparator<Score>{
		
		public int compare(Score s1, Score s2){
			int score1 = s1.getScore();
			int score2 = s2.getScore();
			
			if(score1>score2)
				return -1;
			else if(score2>score1)
				return 1;
			else
				return 0;
		}
		
	}
	

}
