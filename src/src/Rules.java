package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Rules extends JPanel{
	
	private JPanel tab1 = new JPanel();
	private JPanel tab2 = new JPanel();	
	
	public Rules(){
		
		super(new BorderLayout());
		
		initTab1();
		initTab2();
		
		UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
		JTabbedPane tab = new JTabbedPane();
		tab.setOpaque(false);
		Myfont.setMyfont(tab);
		tab.setBackground(new Color(255,255,255,120));
		
		tab.addTab("Commands", tab1);
		tab.addTab("Bonus effects", tab2);
		
		this.add(tab);

		JPanel pan = new JPanel();
		
		Button exitButton = new Button("Exit");
		exitButton.setName("exitButton");
		
		pan.add(exitButton);
		pan.setBackground(new Color(0,0,0,0));
		
		this.add(pan, BorderLayout.SOUTH);
		
		Frame frame = Frame.getFrame();
		exitButton.addActionListener(frame);
		
	}
	
	public void initTab1(){
		
		tab1.setLayout(new GridBagLayout());
		
		tab1.setBackground(new Color(255,255,255,120));
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		
		JLabel jinst1 = new JLabel("Use UP and DOWN to move");
		Myfont.setMyfont(jinst1);
		c.gridy = 1;
		tab1.add(jinst1, c);
		
		JLabel jinst2 = new JLabel("Use SPACE to shoot");
		Myfont.setMyfont(jinst2);
		c.gridy = 2;
		tab1.add(jinst2, c);
		
	}
		
	public void initTab2(){
		
		tab2.setLayout(new GridBagLayout());
		
		tab2.setBackground(new Color(255,255,255,120));
		
		GridBagConstraints c = new GridBagConstraints();
		//c.insets = new Insets(10,10,10,10);
		
		ImageIcon i1 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus1.png"));
		JLabel jlab = new JLabel(i1);
		//c.fill = GridBagConstraints.VERTICAL;
		c.gridy = 3;
		c.gridx = 1;
		tab2.add(jlab, c);
		
		JLabel jlab2 = new JLabel("Upgrade missile");
		Myfont.setMyfont(jlab2);
		//c.anchor = GridBagConstraints.EAST;
		c.gridy = 3;
		c.gridx = 2;
		tab2.add(jlab2, c);
		
		ArrayList<JLabel> rules = new ArrayList<JLabel>();
		
		rules.add(new JLabel("1: Fire up to 5 missiles"));
		rules.add(new JLabel("2: Fire infinite missiles"));
		rules.add(new JLabel("3: Fire missile on two rows"));
		rules.add(new JLabel("4: Fire on three rows"));
		rules.add(new JLabel("5: Fire on five rows"));
		
		int i = 0;
		
		for(JLabel j : rules){
			Myfont.setMyfont(j);
			c.gridx = 6;
			c.gridy = i;
			tab2.add(j, c);
			i++;
		}
		
		/*JLabel jlab21 = new JLabel("1: Up to 5 missiles");
		Myfont.setMyfont(jlab21);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 3;
		c.gridy = 3;
		tab2.add(jlab21, c);
		
		JLabel jlab22 = new JLabel("2: Infinite missiles");
		Myfont.setMyfont(jlab22);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 3;
		c.gridy = 4;
		tab2.add(jlab22, c);
		
		JLabel jlab23 = new JLabel("3: On two rows");
		Myfont.setMyfont(jlab23);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 3;
		c.gridy = 5;
		tab2.add(jlab23, c);
		
		JLabel jlab24 = new JLabel("4: On three rows");
		Myfont.setMyfont(jlab24);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 3;
		c.gridy = 6;
		tab2.add(jlab24, c);
		
		JLabel jlab25 = new JLabel("5: On five rows");
		Myfont.setMyfont(jlab25);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 3;
		c.gridy = 7;
		tab2.add(jlab25, c);*/
		
		
		ImageIcon i2 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus2.png"));
		JLabel jlab3 = new JLabel(i2);
		c.gridy = 8;
		c.gridx = 1;
		tab2.add(jlab3, c);
		
		JLabel jlab4 = new JLabel("Best missile shoot");
		Myfont.setMyfont(jlab4);
		c.gridy = 8;
		c.gridx = 2;
		tab2.add(jlab4, c);
		
		ImageIcon i3 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus3.png"));
		JLabel jlab5 = new JLabel(i3);
		c.gridy = 9;
		c.gridx = 1;
		tab2.add(jlab5, c);
		
		JLabel jlab6 = new JLabel("Immune to next damage");
		Myfont.setMyfont(jlab6);
		c.gridy = 9;
		c.gridx = 2;
		tab2.add(jlab6, c);
		
	}
	
	@Override
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		ImageIcon ii = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_back.png"));
        Image image = ii.getImage();
		g.drawImage(image, 0, 0, null);
		
		
		g.drawRect(0, 0, 500, 500);
		g.setColor(new Color(255,255,255,200));
		g.fillRect(0, 0, 500, 500);
		
	}

}
