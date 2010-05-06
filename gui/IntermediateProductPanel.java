package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	private IntermediateProduct intermediateProduct;
	private JLabel idealTimeLine = new JLabel();
	
	public IntermediateProductPanel(StoringSpace storingSpace) {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(lblName);
		this.add(progressBar);
		progressBar.setValue(0);
		progressBar.setForeground(Color.black);
		progressBar.setBackground(Color.black);
		progressBar.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setFocusable(true);
		lblName.setText("Tom");	
		this.add(idealTimeLine);
		
		idealTimeLine.setBorder(BorderFactory.createLineBorder(Color.orange));
		idealTimeLine.setPreferredSize(new Dimension(4,30));
		idealTimeLine.setLocation(0,0);		
		
		if(storingSpace.getIntermediateProduct() != null) {
			
		}
	}
	
	public IntermediateProduct getIntermediateProduct() {
		return intermediateProduct;
	}

	public void setIntermediateProduct(IntermediateProduct intermediateProduct) {
		this.intermediateProduct = intermediateProduct;
		lblName.setText(intermediateProduct.getProductType().getName());
		
	}
	
	public void updateTime() {
		Drying drying = (Drying) intermediateProduct.getProcessLogs().get(intermediateProduct.getProcessLogs().size()-1).getProcess();		
		progressBar.setMinimum(0);
		progressBar.setMaximum((int) drying.getMaxTime()/1000);
		long currentTime = System.currentTimeMillis()-intermediateProduct.getProcessLogs().get(intermediateProduct.getProcessLogs().size()-1).getStartTime().getTime();
		progressBar.setValue((int) currentTime/1000);
	}
	
	

}
