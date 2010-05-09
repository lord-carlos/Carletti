package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.plaf.ProgressBarUI;



import model.Drying;
import model.IntermediateProduct;
import model.StoringSpace;

public class IntermediateProductPanel extends JPanel{
	private JProgressBar progressBar = new JProgressBar(0,100);
	private JLabel lblName = new JLabel();
	private JLabel lblIcon = new JLabel();
	private JLabel idealTimeLine = new JLabel();
	private StoringSpace storingSpace;
	
	
	public IntermediateProductPanel(StoringSpace storingSpace) {
		this.storingSpace = storingSpace;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(lblName);
		this.add(progressBar);
		this.add(lblIcon);
		progressBar.setValue(0);
		progressBar.setForeground(Color.green);
		progressBar.setBorder(BorderFactory.createLineBorder(Color.black));
//		this.add(idealTimeLine);
//		idealTimeLine.setBorder(BorderFactory.createLineBorder(Color.orange));
//		idealTimeLine.setPreferredSize(new Dimension(4,30));
//		idealTimeLine.setLocation(0,0);		

		if(storingSpace.getIntermediateProduct() != null) {
			lblName.setText(storingSpace.getIntermediateProduct().getProductType().getName());
			ImageIcon imageIcon = storingSpace.getIntermediateProduct().getProductType().getPicture();
			Image img = imageIcon.getImage();
			BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			System.out.println();
			g.drawImage(img, 0, 0, 20, 70, null);
			
			lblIcon.setIcon(new ImageIcon(bi));
		}
		else {
			this.setVisible(false);
		}
	}
	
	public void updateTime() {
		if(storingSpace.getIntermediateProduct() != null) {
		Drying drying = (Drying) storingSpace.getIntermediateProduct().getProcessLogs().get(storingSpace.getIntermediateProduct().getProcessLogs().size()-1).getProcess();		
		progressBar.setMinimum(0);
		progressBar.setMaximum((int) drying.getMaxTime()/1000);
		long currentTime = System.currentTimeMillis()-storingSpace.getIntermediateProduct().getProcessLogs().get(storingSpace.getIntermediateProduct().getProcessLogs().size()-1).getStartTime().getTime();
		progressBar.setValue((int) currentTime/1000);
		}
	}
	
	public StoringSpace getStoringSpace() {
		return storingSpace;
	}
	
	

}
