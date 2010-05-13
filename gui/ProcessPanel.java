package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Drying;
import model.IntermediateProduct;
import model.Process;
import model.ProcessLog;

public class ProcessPanel extends JPanel {
	private JLabel lblName;
	private JCheckBox cbxComplete;
	private Boolean selected;
	private Process process;
	private JPanel panel;
	private IntermediateProduct intermediateProduct;


	public ProcessPanel(IntermediateProduct intermediateProduct, Process process) {

		this.process = process;
		this.intermediateProduct = intermediateProduct;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));	
		this.setOpaque(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(130, 25));
		this.setAlignmentX(LEFT_ALIGNMENT);
		if (process.getClass().equals(Drying.class)) {
			Date minTime = new Date(((Drying)process).getMinTime()+System.currentTimeMillis());
			
			this.setToolTipText("<html>"+process+":<br>" + 
					"Minimums toerretid: "+minTime.toString()+"</html>");
		}
		else {
			
		}
		{
			panel = new JPanel();
			panel.setAlignmentX(LEFT_ALIGNMENT);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.setPreferredSize(new Dimension(130,25));
			panel.setOpaque(true);
			this.add(panel);
			{
				{
					cbxComplete = new JCheckBox();
					panel.add(cbxComplete);	
					cbxComplete.setEnabled(false);
				}
				{
					lblName = new JLabel();
					panel.add(lblName);
					lblName.setText(process.toString());
					lblName.setBounds(25, 0, 100, 25);
				}
			}

		}
		//De processor der er færdige
		boolean found = false;
		for (ProcessLog processLog : intermediateProduct.getProcessLogs()) {
			if (processLog.getProcess() == process && processLog.isActive() != true) {
				cbxComplete.setSelected(true);
				found = true;
				break;
			}
		}
		//Aktive processor
		if (found == false && intermediateProduct.getActivProcessLog().getProcess() == process) {
			cbxComplete.setSelected(false);
			cbxComplete.setBackground(Color.green);
			this.setBackground(Color.green);
			panel.setBackground(Color.green);
			found = true;
		}
		//De processor der ikke er begyndt som ikke er færdige
		else if (found == false) {
			cbxComplete.setSelected(false);
		}
	}
}

