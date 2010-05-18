package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;

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

			boolean found = false;
			long startTime = 0;
			for (ProcessLog processLog : process.getProcessLogs()) {
				if (processLog.getIntermediateProduct().equals(intermediateProduct)) {
					startTime = processLog.getStartTime().getTime();
					found = true;
				}
			}
			if (found)  {				
				this.setToolTipText("<html>"+process+":<br>"+
						"Minimums toerretid: "+longDateToString(((Drying)process).getMinTime()+startTime)+"<br>"+
						"Ideal torretid: "+longDateToString(((Drying)process).getIdealTime()+startTime)+"<br>"+
						"Maximal torretid: "+longDateToString(((Drying)process).getMaxTime()+startTime)+"<br>"+
				"</html>");
			}
			else {
				this.setToolTipText("<html>"+process+":<br>"+
						"Minimums toerretid: "+longPeriodToString(((Drying)process).getMinTime())+"<br>"+
						"Ideal torretid: "+longPeriodToString(((Drying)process).getIdealTime())+"<br>"+
						"Maximal torretid: "+longPeriodToString(((Drying)process).getMaxTime())+"<br>"+
				"</html>");
			}

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

	public String longDateToString(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar.get(Calendar.DAY_OF_MONTH)+"/"+
		calendar.get(Calendar.MONTH)+
		"/"+calendar.get(Calendar.YEAR)+" "+
		calendar.get(Calendar.HOUR_OF_DAY)+":"+
		calendar.get(Calendar.MINUTE); 
	}
	
	public String longPeriodToString(long date) {
		int dateInSeconds = (int) date/1000;
		int days = dateInSeconds/(60*60*24);
		int hours = (dateInSeconds % (60*60*24)) / (60*60);
		int minutes = ((dateInSeconds % (60*60*24)) % (60*60)) / 60;
		return days+" dage, "+hours+" timer,"+minutes+" minutter";	
	}
	
}

