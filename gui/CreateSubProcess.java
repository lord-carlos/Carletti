package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ProcessLine;
import model.SubProcess;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class CreateSubProcess extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblName;
	private JTextField txfName;
	private JLabel lblTemp;
	private JTextField txfTemp;
	private JLabel lblTime;
	private JTextField txfTime;
	private JLabel lblDescr;
	private JButton btnCreate;
	private JButton btnChancel;
	private JTextArea txaDescr;
	private JScrollPane scpDescr;
	private ProcessLine pl;
	private SubProcess thisSubProcess = null;
	private BtnController btnController = new BtnController();
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public CreateSubProcess(ProcessLine pl) {
		initComponents();

		this.pl=pl;
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setPreferredSize(this.getSize());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents() {
		setTitle("Opret behandling");
		setLayout(new GroupLayout());
		add(getLblName(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getLblTemp(), new Constraints(new Leading(12, 12, 12), new Leading(60, 12, 12)));
		add(getLblTime(), new Constraints(new Leading(12, 12, 12), new Leading(108, 12, 12)));
		add(getLblDescr(), new Constraints(new Leading(12, 12, 12), new Leading(156, 12, 12)));
		add(getBtnCreate(), new Constraints(new Leading(12, 198, 10, 10), new Trailing(12, 184, 184)));
		add(getBtnChancel(), new Constraints(new Bilateral(228, 12, 81), new Trailing(12, 12, 12)));
		add(getScpDescr(), new Constraints(new Bilateral(12, 12, 22), new Bilateral(178, 56, 22)));
		add(getTxfTime(), new Constraints(new Leading(12, 198, 12, 12), new Leading(130, 12, 12)));
		add(getTxfTemp(), new Constraints(new Leading(12, 198, 12, 12), new Leading(82, 12, 12)));
		add(getTxfName(), new Constraints(new Leading(12, 198, 12, 12), new Leading(34, 12, 12)));
		setSize(425, 324);
	}

	private JScrollPane getScpDescr() {
		if (scpDescr == null) {
			scpDescr = new JScrollPane();
			scpDescr.setViewportView(getTxaDescr());
		}
		return scpDescr;
	}

	private JTextArea getTxaDescr() {
		if (txaDescr == null) {
			txaDescr = new JTextArea();
		}
		return txaDescr;
	}

	private JButton getBtnChancel() {
		if (btnChancel == null) {
			btnChancel = new JButton();
			btnChancel.setText("Annuller");
			btnChancel.addActionListener(btnController);
		}
		return btnChancel;
	}

	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton();
			btnCreate.setText("Opret behandling");
			btnCreate.addActionListener(btnController);
		}
		return btnCreate;
	}

	private JLabel getLblDescr() {
		if (lblDescr == null) {
			lblDescr = new JLabel();
			lblDescr.setText("Beskrivelse:");
		}
		return lblDescr;
	}

	private JTextField getTxfTime() {
		if (txfTime == null) {
			txfTime = new JTextField("24");
		}
		return txfTime;
	}

	private JLabel getLblTime() {
		if (lblTime == null) {
			lblTime = new JLabel();
			lblTime.setText("Behandlingens tid (timer):");
		}
		return lblTime;
	}

	private JTextField getTxfTemp() {
		if (txfTemp == null) {
			txfTemp = new JTextField("15");
		}
		return txfTemp;
	}

	private JLabel getLblTemp() {
		if (lblTemp == null) {
			lblTemp = new JLabel();
			lblTemp.setText("Behandlingengs temperatur (C):");
		}
		return lblTemp;
	}

	private JTextField getTxfName() {
		if (txfName == null) {
			txfName = new JTextField();
		}
		return txfName;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel();
			lblName.setText("Behandlingsens navn:");
		}
		return lblName;
	}

	public SubProcess getSubProcess(){
		return thisSubProcess;
	}

	private class BtnController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(btnChancel)){
				CreateSubProcess.this.setVisible(false);
			} else if (e.getSource().equals(btnCreate)){
			
				if (getTxfName().getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Behandlingen skal have et navn!!!","Fejl!!!",JOptionPane.ERROR_MESSAGE);

				} else {
					long time;

					try {
						time = Long.valueOf(getTxfTime().getText())*60*60*1000;
					}
					catch (NumberFormatException exception){
						time = 24*60*60*1000;
					}

					double temp;

					try {
						temp = Double.valueOf(getTxfTemp().getText());
					}
					catch (NumberFormatException exception){
						temp = 15.0;
					}


					thisSubProcess = pl.createSubProcess(0, getTxfName().getText(), getTxaDescr().getText(),time , temp);

					CreateSubProcess.this.setVisible(false);
				}
			}

		}
	}
	
	
}
