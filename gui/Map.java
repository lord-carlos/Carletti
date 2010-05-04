package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Map extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		
		final int MID = 150;
		final int TOP = 50;
		
		g.setColor(Color.orange);
		g.fillRect(0, 175, 200, 50); // ground
		
		g.setColor(Color.blue);
		g.fillOval(MID,TOP, 40, 50); // ballon1
		g.setColor(Color.red);
		g.fillOval(MID-50,TOP+20, 40, 50); // ballon2
		
		
		
		g.setColor(Color.black);
		g.drawLine(MID+20, TOP+50, MID+20, TOP+100); // string1
		g.drawLine(MID-30, TOP+70, MID-30, TOP+120); // string2
		
	}
}
