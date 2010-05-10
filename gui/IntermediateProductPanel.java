package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.plaf.ProgressBarUI;



import model.Drying;
import model.IntermediateProduct;
import model.StoringSpace;

public class IntermediateProductPanel extends JPanel{
	private JPanel panel;
	private JProgressBar progressBar;
	private JLabel lblName, lblIcon;


	private StoringSpace storingSpace;
	private Boolean selected;



	public IntermediateProductPanel(StoringSpace storingSpace) {
		this.storingSpace = storingSpace;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new BorderLayout());
		{	
			panel = new JPanel();		
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			this.add(panel);
			{	
				lblName = new JLabel();
				lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel.add(lblName, BorderLayout.CENTER);
			}
			{
				progressBar = new JProgressBar();
				progressBar.setForeground(Color.green);
				panel.add(progressBar, BorderLayout.CENTER);
			}
			{
				panel.add(Box.createRigidArea(new Dimension(5,5)));
			}
			{
				lblIcon = new JLabel();
				lblIcon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				panel.add(lblIcon, BorderLayout.CENTER);		
			}
		}

		if(storingSpace.getIntermediateProduct() != null) {
			lblName.setText(storingSpace.getIntermediateProduct().getProductType().getName());
			lblIcon.setIcon(storingSpace.getIntermediateProduct().getProductType().getPicture());
		}
		else {
			progressBar.setVisible(false);
			lblName.setVisible(false);
			lblIcon.setVisible(false);
		}
	}

	public void updateTime() {
		if(storingSpace.getIntermediateProduct() != null) {
			Drying drying = (Drying) storingSpace.getIntermediateProduct().getActivProcessLog().getProcess();		
			progressBar.setMinimum(0);
			progressBar.setMaximum((int) drying.getMaxTime()/1000);
			long currentTime = System.currentTimeMillis()-storingSpace.getIntermediateProduct().getActivProcessLog().getStartTime().getTime();
			progressBar.setValue((int) currentTime/1000);
		}
	}

	public void setSelected(Boolean bool) {
		if(bool) {
			this.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
		}
		else {
			this.setBorder(BorderFactory.createLineBorder(Color.black));	
		}
	}

	public Boolean isSelected() {
		return this.selected;
	}

	public StoringSpace getStoringSpace() {
		return this.storingSpace;
	}
}
