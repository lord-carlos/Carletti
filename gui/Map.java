package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Map extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		
		final int MID = 150;
		final int TOP = 50;
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		g.setColor(Color.darkGray);
		g.fillRect(this.getWidth()/100*1, this.getHeight()/100*1, this.getWidth()-this.getWidth()/100*2, this.getHeight()-this.getHeight()/100*2); // ground
	
		g.setColor(Color.black);
		
		
		int x = this.getWidth()/100*2;
		int y = this.getHeight()/100*2;
		int width = this.getWidth()/100*20;
		int height = this.getHeight()/100*10;
		for (int i = 0; i <= 5; i++) {	
			
			g.drawRect(x, y, width, height);
			y+=height;

		}
		
		
	}
}
