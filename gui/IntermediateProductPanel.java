package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

public class IntermediateProductPanel extends JPanel{
	private JProgressBar progressBar = new JProgressBar(0,100);
	private JLabel label = new JLabel();
	
	public IntermediateProductPanel() {
		//this.setBackground(Color.g);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(label);
		this.add(progressBar);
		progressBar.setValue(100);
		progressBar.setForeground(Color.black);
		progressBar.setBackground(Color.black);
		//progressBar.setSize(new Dimension);
		progressBar.setBorder(BorderFactory.createLineBorder(Color.black));
		
		label.setText("ej");
			
		
	}

}
