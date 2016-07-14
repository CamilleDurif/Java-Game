package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener{
	
	private final int ICRAFT_X = 50;
	private final int ICRAFT_Y = 142;
	private final int DELAY = 15;
	private Timer timer;
	private Craft craft;
	private Boss boss;
	
	private Background back;
	
	private int score;
	private int life;
	private int spawned;
	
	private boolean ingame;
	private boolean inboss;
	private final int B_WIDTH = 480;
	private final int B_HEIGHT = 340;
		
	private ArrayList<Alien> aliens;
	private ArrayList<Wall> walls;
	private ArrayList<Life> lives;
	private ArrayList<Alien2> aliens2;
	private ArrayList<Bonus> bonus;
	
	private int[][] ennemies = new int[5][4];
	
	public Game(){
		
		
		initBoard();
		
	}
	
	private void initBoard(){
		
		this.addAncestorListener(new AncestorListener() {
		    @Override
		    public void ancestorRemoved(AncestorEvent pEvent) {
		    }

		    @Override
		    public void ancestorMoved(AncestorEvent pEvent) {
		    }

		    @Override
		    public void ancestorAdded(AncestorEvent pEvent) {
		        // TextField is added to its parent => request focus in Event Dispatch Thread
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                requestFocusInWindow();
		            }
		        });
		    }
		});
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		
		ingame = true;
		score = 0;
		life = 3;
		spawned = 0;
		inboss = false;
		
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
			
		craft = new Craft(ICRAFT_X, ICRAFT_Y);
		
		back = new Background();
		
		//initAliens();
		Alien.setSpeed(3);
		Life.setSpeed(5);
		Bonus.setSpeed(5);
		
		walls = new ArrayList<>();
		lives = new ArrayList<>();
		aliens = new ArrayList<>();
		aliens2 = new ArrayList<>();
		bonus = new ArrayList<>();
				
		timer = new Timer(DELAY, this);
		timer.start();
		
	}
	
	/*
	 * aliens that are visible are contained in an ArrayList
	 * they are all randomly generated by a simple system based on a random number
	 * at the beginning, 5 aliens are generated 
	 */
	public void initAliens(){
		
		aliens = new ArrayList<>();
		
		for(int i = 0; i<5; i++){
			Random rand = new Random();
			int posY = rand.nextInt(B_HEIGHT);
			int posX = rand.nextInt(B_WIDTH) + 500;
			
			if(posY < 76) posY = 20;
			else if(posY < 132) posY = 81;
			else if(posY < 188) posY = 142;
			else if(posY < 244) posY = 203;
			else posY = 264;
		
			aliens.add(new Alien(posX, posY));
		}
		
		Alien.setSpeed(3);
	}
	
	
	/*
	 * this function will paint every sprite that is visible at the moment and the background
	 * it takes the coordinates of every sprite and background to paint them at the right place
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(back.getImage(), -back.getPosX(), 0, this);
		
		if (back.getPosX() + 500 > back.getWidth()) {
            g.drawImage(back.getImage(), - back.getPosX() + back.getWidth(), 0, this);
        }
		
		Graphics2D g2d = (Graphics2D) g;
		if(craft.isVisible())
			g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
		
		if(inboss && boss.isVisible())
			g2d.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {
            if(m.isVisible())
            	g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }
        
        if(inboss && !boss.getMissiles().isEmpty()){
	        ArrayList<BossMissile> mb = boss.getMissiles();
	        
	        for (BossMissile m : mb){
	        	if(m.isVisible())
	        		g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	        }
        }
        
        for (Alien a : aliens){
        	if(a.isVisible())
        		g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        }
        
        for (Wall w : walls){
        	if(w.isVisible())
        		g2d.drawImage(w.getImage(), w.getX(), w.getY(), this);
        }
        
        for (Life l : lives){
        	if(l.isVisible())
        		g2d.drawImage(l.getImage(), l.getX(), l.getY(), this);
        }
        
        for (Bonus b : bonus){
        	if(b.isVisible())
        		g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);
        }
        
        for (Alien2 a : aliens2){
        	if(a.isVisible())
        		g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        }
        
        Font f = new Font("Dialog", Font.BOLD,20);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString("Score : " + score, 5, 20);
        g.drawString("Life left : " + life, 5, 40);
        g.drawString("Spawned : " + spawned, 5, 60);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		inGame();
		
		updateBackground();
		
		updateMissiles();
        updateCraft();
        
        if(!inboss){
	        updateAliens();
	        updateWalls();
	        updateLives();
	        updateBonus();
	        //if(craft.getShoot() > 0)
	        updateAliens2();
        }
        
        if(inboss)
        	updateBoss();
        
        updateSpeed();
        
        checkCollisions();
        
		repaint();
				
	}

	/*
	 * when the game is finished, the timer stop and the gameover screen will be generated and shown 
	 * by the Frame class
	 */
	private void inGame(){
		
		if(!ingame){
			timer.stop();
			Frame frame = Frame.getFrame();
			frame.gameOver(score, spawned, life);
			
		}
	}

	
	private void updateBackground(){
		
		if(ingame)
			back.move();		
	}
	
	@SuppressWarnings("unchecked")
	private void updateMissiles() {

	        ArrayList<Missile> ms = craft.getMissiles();

	        for (int i = 0; i < ms.size(); i++) {

	            Missile m = ms.get(i);

	            if (m.isVisible()) {
	                m.move();
	            } else {
	                ms.remove(i);
	            }
	        }
	        
	        if(inboss && !boss.getMissiles().isEmpty()){
	        	
		        ArrayList<BossMissile> mb = boss.getMissiles();
		        
		        for (int i = 0; i < mb.size(); i++){
		        	BossMissile n = mb.get(i);
		        	
		        	if (n.isVisible()) {
		                n.move();
		            } else {
		                mb.remove(i);
		            }
		        }
	        }
	    }
		
	private void updateCraft() {
		
		if(life < 0){
			craft.vis = false;
			ingame = false;
		}
		
		
		if(craft.isVisible())
			craft.move();
	}
	
	public void initBoss(){
		
		aliens.removeAll(aliens);
		aliens2.removeAll(aliens2);
		lives.removeAll(lives);
		walls.removeAll(walls);
		bonus.removeAll(bonus);
		
		boss = new Boss(450,142);
		
		Frame.getFrame().playBossTheme();
	}
	
	public void updateBoss(){
		
		if(boss.isVisible()){
			boss.move();
			boss.shoot();
		}
		
		if(boss.getLife() == 0){
			boss.vis = false;
    		score += 20;
    		boss.setLife(-1);
    		inboss = false;
    		initAliens();
    		
    		Frame.getFrame().playTheme();
		}
			
	}
	
	/*
	 * if there is no aliens left, the game stops and the player wins
	 * else, aliens are randomly generated by the same random function
	 * in order to not invade the screen by aliens, they have a little chance to spawn (spawn > 990) 
	 * and there can be 10 aliens at the same time in maximum
	 */
	private void updateAliens(){

		// � d�placer dans une fonction plus appropri�e, mais faire attention � ce que �a ne serelance pas tout le temps
		//if(aliens.isEmpty()){
		if(craft.getShoot()>0 && score > 50){
			inboss = true;
			initBoss();
			return;
		}
		
		for(int i = 0; i < aliens.size(); i++){
			
			Alien a = aliens.get(i);
			if(a.isVisible())
				a.move();
			else{
				aliens.remove(i);
				spawned++;
				}
		}
		
		Random rand = new Random();
		int spawn = rand.nextInt(1000);
		//int alienX =0;
		
		if(spawn > 980 && aliens.size() < 10){
			int posY = rand.nextInt(B_HEIGHT);
			int posX = rand.nextInt(B_WIDTH) + 400;
						
			if(posY < 76 && ennemies[0][2] == 0) aliens.add(new Alien(posX, 20));
			else if(posY > 76 && posY < 132 && ennemies[1][2] == 0) aliens.add(new Alien(posX, 81));
			else if(posY > 132 && posY < 188 && ennemies[2][2] == 0) aliens.add(new Alien(posX, 142));
			else if(posY > 188 && posY < 244 && ennemies[3][2] == 0) aliens.add(new Alien(posX, 203));
			else if(posY > 244 && posY < 305 && ennemies[4][2] == 0) aliens.add(new Alien(posX, 264));
			
			/*if(ennemies[4][2] == 0 && ennemies[0][2] == 0 && ennemies[1][2] == 0 && ennemies[2][2] == 0 && ennemies[3][2] == 0)
				aliens.add(new Alien(posX, posY));*/
			
			/*if(!aliens.isEmpty()){
				Alien a = aliens.get(0);
			if(Math.abs(posX-alienX) > a.width){
				aliens.add(new Alien(posX, posY));
				alienX = posX;
				}
			}
			else
				aliens.add(new Alien(posX, posY));
				*/

		}
	}
	
	public void updateWalls(){
		
		//int wallX = 0;
		
		Random rand = new Random();
		int spawn = rand.nextInt(1000);
		
		if(spawn > 990 && walls.size()<=2){
			int posY = rand.nextInt(B_HEIGHT);
			int posX = rand.nextInt(B_WIDTH) + 400;
			
			//if(posY > 300) posY -= 100 ;
			
			if(posY < 76 && ennemies[0][2] == 0) walls.add(new Wall(posX, 20));
			else if(posY > 76 && posY < 132 && ennemies[1][2] == 0) walls.add(new Wall(posX, 81));
			else if(posY > 132 && posY < 188 && ennemies[2][2] == 0) walls.add(new Wall(posX, 142));
			else if(posY > 188 && posY < 244 && ennemies[3][2] == 0) walls.add(new Wall(posX, 203));
			else if(posY > 244 && posY < 305 && ennemies[4][2] == 0) walls.add(new Wall(posX, 264));
				
				if(posY < 76) ennemies[0][2] = 1;
				else if(posY < 132) ennemies[1][2] = 1;
				else if(posY < 188) ennemies[2][2] = 1;
				else if(posY < 244) ennemies[3][2] = 1;
				else if(posY < 305) ennemies[4][2] = 1;
			}
		
		for (int i = 0; i <walls.size(); i++){
			Wall w = walls.get(i);
			if (w.isVisible())
				w.move();
			else{
				walls.remove(i);
				
				int posY = w.getY();
				if(posY == 20) ennemies[0][2] = 0;
				else if(posY == 81) ennemies[1][2] = 0;
				else if(posY == 142) ennemies[2][2] = 0;
				else if(posY == 203) ennemies[3][2] = 0;
				else if(posY == 264) ennemies[4][2] = 0;
			}
		}
		
	}
	
	public void updateLives(){
		
		Random rand = new Random();
		int spawn = rand.nextInt(1000);
		
		if(spawn > 990 && lives.size() == 0 && aliens2.size() == 0){
			int posY = rand.nextInt(B_HEIGHT);
			int posX = rand.nextInt(B_WIDTH) + 400;
			
			if(posY < 76) posY = 20;
			else if(posY < 132) posY = 81;
			else if(posY < 188) posY = 142;
			else if(posY < 244) posY = 203;
			else posY = 264;
		
			lives.add(new Life(posX, posY));}
		
		for (int i = 0; i <lives.size(); i++){
			Life l = lives.get(i);
			if (l.isVisible())
				l.move();
			else 
				lives.remove(i);
		}
		
	}
	
	public void updateBonus(){
		
		Random rand = new Random();
		int spawn = rand.nextInt(1000);
		
		if(spawn > 995 && bonus.size() == 0){
			int posY = rand.nextInt(B_HEIGHT);
		
			if(posY < 76) posY = 20;
			else if(posY < 132) posY = 81;
			else if(posY < 188) posY = 142;
			else if(posY < 244) posY = 203;
			else posY = 264;
			
			
			Random rand2 = new Random();
			int bonustype = rand2.nextInt(3);
			System.out.println("bonus n� " + bonustype + " vaisseau immune : " + craft.isImmune());
			if (bonustype == 1)
				bonus.add(new Bonus(B_WIDTH, posY, 1));
			else if(bonustype == 2)
				bonus.add(new Bonus(B_WIDTH, posY, 2));
			else if(bonustype == 0 && !craft.isImmune())
				bonus.add(new Bonus(B_WIDTH, posY, 3));
				
			
		}
		
		for(int i = 0; i < bonus.size(); i++){
			Bonus b = bonus.get(i);
			if(b.isVisible())
				b.move();
			else
				bonus.remove(i);
		}
		
	}
	
	public void updateAliens2(){
		
		Random rand = new Random();
		int spawn = rand.nextInt(1000);
		
		if(spawn > 990 && aliens2.size()==0 && lives.size()==0){
			int posY = rand.nextInt(B_HEIGHT);
			int posX = rand.nextInt(B_WIDTH) + 400;
			
			if(posY < 76) posY = 20;
			else if(posY < 132) posY = 81;
			else if(posY < 188) posY = 142;
			else if(posY < 244) posY = 203;
			else posY = 264;
		
			aliens2.add(new Alien2(posX, posY));}
		
		for (int i = 0; i <aliens2.size(); i++){
			Alien2 a = aliens2.get(i);
			if (a.isVisible())
				a.move();
			else
				aliens2.remove(i);
		}
		
	}
	
	public void updateSpeed(){
		
		if(spawned%20==0 && spawned != 0){
			back.setSpeed((back.getSpeed()+1)); ;
			Alien.setSpeed((Alien.getSpeed()+1));
			Life.setSpeed((Life.getSpeed()+1));
			spawned++; //triche, � modifier
			}
		
	}
	
	/*
	 * the collisions are verified by simple rectangles
	 * the detection is then not really good because the picture of the player is not a rectangle
	 */
	@SuppressWarnings("unchecked")
	public void checkCollisions(){
		
		if(life < 0){
			//Sound.play("gyarados.wav");
		}
		

        Rectangle rC = craft.getBounds();

        for (Alien alien : aliens){
            Rectangle rA = alien.getBounds();

            if (rC.intersects(rA)) {
            	alien.setVisible(false);
            	if(craft.isImmune())
            		craft.setImmune(false);
            	else{
            		life--;
            		craft.downShoot();
            		if(Craft.getCraft().getShoot() > 15)
                    	craft.setShoot(2);
            	}
                alien.playSound();
            }
        }
        
        for(Alien2 alien : aliens2){
        	Rectangle rA2 = alien.getBounds();
        	if(rC.intersects(rA2)){
        		alien.setVisible(false);
        		//alien.playSound();
        		if(craft.isImmune())
        			craft.setImmune(false);
        		else{
        			life -= 2;
        			craft.downShoot();
        			if(Craft.getCraft().getShoot() > 15)
                    	craft.setShoot(2);
        		}
        	}  
        	
        }
        
        for(Wall wall : walls){
        	Rectangle rW = wall.getBounds();
        	if(rC.intersects(rW)){
        		if(craft.isImmune()){
        			craft.setImmune(false);
        			wall.setVisible(false);
        		}
        		else{
        			craft.setVisible(false);
        			wall.playSound();
        			ingame = false;
        		}
        	}
        }
        for(Life l : lives){
        	Rectangle rL = l.getBounds();
        	if(rC.intersects(rL)){
        		life++;
        		l.setVisible(false);
        		l.playSound();
        	}
        }
        
        for(Bonus b : bonus){
        	Rectangle rB = b.getBounds();
        	if(rC.intersects(rB)){
        		b.setVisible(false);
        		if(b.getBonusType() == 2)
        			craft.setShoot(20);
        		else if(b.getBonusType() == 1){
        			craft.upShoot();
        			if(craft.getShoot() == 21)
        				craft.setShoot(3);}
        		else
        			craft.setImmune(true);
        	}
        }
       
	    if(inboss){
	        Rectangle rB = boss.getBounds();
	        
	        if(rB.intersects(rC))
	        	ingame = false;
	        
	        ArrayList<Missile> ms = craft.getMissiles();
	        ArrayList<BossMissile> mb = boss.getMissiles();

	        for (Missile m : ms) {

	            Rectangle rM = m.getBounds();
	            
	            if(rM.intersects(rB)){
	            	m.setVisible(false);
	            	boss.setLife(boss.getLife()-1);
	            }
	            
	            for (BossMissile n : mb) {

		            Rectangle rMB = n.getBounds();
		            
		            if(rM.intersects(rMB)){
		            	n.setVisible(false);
		            	m.setVisible(false);
		            }
	            }
	        }
	        
	        for (BossMissile n : mb) {

	            Rectangle rMB = n.getBounds();
	            
	            if(rC.intersects(rMB)){
	            	n.setVisible(false);
	            	life--;
	            	craft.downShoot();
	            }
            }
	    }
	        

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {

            Rectangle rM = m.getBounds();

            for (Alien alien : aliens) {

                Rectangle rA = alien.getBounds();

                if (rM.intersects(rA)) {
                    alien.setVisible(false);
                    score++;
                    alien.playSound();
                    if(craft.getShoot() != 20 ){
                		m.setVisible(false);
                	}
                }
            }
            
            for(Alien2 alien : aliens2){
            	Rectangle rA2 = alien.getBounds();
            	if(rM.intersects(rA2)){
            		if(craft.getShoot() != 20 ){
                		m.setVisible(false);
                	}
            		alien.count--; 
            		if(alien.count == 0){
            			alien.setVisible(false);
            			score+=2;
            			alien.playSound();
            		}
            	}
            }
            
            for(Wall wall : walls){
            	Rectangle rW = wall.getBounds();
            	if(rM.intersects(rW))
            		m.setVisible(false);
            }
        }
    }
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e){
			
			craft.keyReleased(e);
			
		}
		
		@Override
		public void keyPressed(KeyEvent e){
			
			craft.keyPressed(e);

		}
	}
}
