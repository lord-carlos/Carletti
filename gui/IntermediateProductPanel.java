package gui;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Drying;
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
		this.setLayout(new BorderLayout());
		{	
			panel = new JPanel();		
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			this.add(panel);
			{	
				lblName = new JLabel();
				lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel.add(lblName);
			}
			{
				progressBar = new JProgressBar();
				panel.add(progressBar);
			}
			{
				panel.add(Box.createRigidArea(new Dimension(5,5)));
			}
			{
				lblIcon = new JLabel();
				lblIcon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				panel.add(lblIcon);		
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
		
		updateTime();
	}

	public void updateTime() {
		if(storingSpace.getIntermediateProduct() != null) {
			Drying drying = (Drying) storingSpace.getIntermediateProduct().getActivProcessLog().getProcess();		
			progressBar.setMinimum(0);
			progressBar.setMaximum((int) drying.getMaxTime()/1000);
			long currentTime = System.currentTimeMillis()-storingSpace.getIntermediateProduct().getActivProcessLog().getStartTime().getTime();
			progressBar.setValue((int) currentTime/1000);

			if (drying.getMinTime()>currentTime){
				progressBar.setForeground(new Color(210,210,210));
			} else if ((drying.getMinTime()+(drying.getIdealTime()-drying.getMinTime())/2)>currentTime){
				progressBar.setForeground(Color.yellow);
			} else if ((drying.getIdealTime()+(drying.getMaxTime()-drying.getIdealTime())/2)>currentTime){
				progressBar.setForeground(Color.green);
			} else if (drying.getMaxTime()>currentTime) {
				progressBar.setForeground(Color.red);
			} else {
				panel.setOpaque(true);
				panel.setBackground(Color.red);
				progressBar.setForeground(Color.red);
			}

		} else {
			panel.setOpaque(false);;
		}
	}

	public void setSelected(Boolean bool) {
		if (bool) {
			this.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
		}
		else {
			this.setBorder(BorderFactory.createLineBorder(Color.black));	
		}
	}

	public StoringSpace getStoringSpace() {
		return this.storingSpace;
	}

}
