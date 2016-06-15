package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel{
	
	private final int B_WIDTH = 400;
	private final int B_HEIGHT = 400;
	
	@Override
	public void paintComponent(Graphics g){
		
		String s1 = "Bienvenue";
		String s2 = "Use UP and DOWN to move";
		String s3 = "Use SPACE to shoot missiles";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(s1, (B_WIDTH - fm.stringWidth(s1)) / 2, B_HEIGHT / 2 -40);
        g.drawString(s2, (B_WIDTH - fm.stringWidth(s2)) / 2, B_HEIGHT / 2 );
        g.drawString(s3, (B_WIDTH - fm.stringWidth(s3)) / 2, B_HEIGHT / 2  -20);
		
	}

}
