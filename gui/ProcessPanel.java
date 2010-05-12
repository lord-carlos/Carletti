package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.IntermediateProduct;
import model.Process;
import model.ProcessLog;

public class ProcessPanel extends JPanel {
	private JLabel lblName;
	private JCheckBox cbxComplete;
	private Boolean selected;
	private Process process;
	private IntermediateProduct intermediateProduct;


	public ProcessPanel(IntermediateProduct intermediateProduct, Process process) {
		this.process = process;
		this.intermediateProduct = intermediateProduct;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));	
		this.setOpaque(true);
		{
			{
				cbxComplete = new JCheckBox();
				cbxComplete.setAlignmentX(Component.RIGHT_ALIGNMENT);
				this.add(cbxComplete);	
				cbxComplete.setEnabled(false);
			}
			{
				lblName = new JLabel();
				this.add(lblName);
				lblName.setText(process.toString());
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
			found = true;
		}
		//De processor der ikke er begyndt som ikke er færdige
		else if (found == false) {
			cbxComplete.setSelected(false);
		}
	}
}

