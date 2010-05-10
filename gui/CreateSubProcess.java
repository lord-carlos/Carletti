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
	public CreateSubProcess(ProcessLine pl) {
		initComponents();

		this.pl=pl;
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents() {
		setTitle("Opret behandling");
		setLayout(null);
		add(getLblName());
		add(getLblTemp());
		add(getLblTime());
		add(getLblDescr());
		add(getBtnCreate());
		add(getBtnChancel());
		add(getScpDescr());
		add(getTxfTime());
		add(getTxfTemp());
		add(getTxfName());
		setSize(312, 378);
	}

	private JScrollPane getScpDescr() {
		if (scpDescr == null) {
			scpDescr = new JScrollPane();
			scpDescr.setViewportView(getTxaDescr());
			scpDescr.setLocation(12, 192);
			scpDescr.setSize(282, 120);
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
			btnChancel.setLocation(159, 316);
			btnChancel.setSize(135, 25);
		}
		return btnChancel;
	}

	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton();
			btnCreate.setText("Opret behandling");
			btnCreate.addActionListener(btnController);
			btnCreate.setLocation(12, 316);
			btnCreate.setSize(135, 25);

		}
		return btnCreate;
	}

	private JLabel getLblDescr() {
		if (lblDescr == null) {
			lblDescr = new JLabel();
			lblDescr.setText("Beskrivelse:");
			lblDescr.setLocation(12, 174);
			lblDescr.setSize(282, 20);
		}
		return lblDescr;
	}

	private JTextField getTxfTime() {
		if (txfTime == null) {
			txfTime = new JTextField("24");
			txfTime.setLocation(12, 142);
			txfTime.setSize(282, 20);
		}
		return txfTime;
	}

	private JLabel getLblTime() {
		if (lblTime == null) {
			lblTime = new JLabel();
			lblTime.setText("Behandlingens tid (timer):");
			lblTime.setLocation(12, 120);
			lblTime.setSize(282, 20);
		}
		return lblTime;
	}

	private JTextField getTxfTemp() {
		if (txfTemp == null) {
			txfTemp = new JTextField("15");
			txfTemp.setLocation(12, 88);
			txfTemp.setSize(282, 20);
		}
		return txfTemp;
	}

	private JLabel getLblTemp() {
		if (lblTemp == null) {
			lblTemp = new JLabel();
			lblTemp.setText("Behandlingengs temperatur (C):");
			lblTemp.setLocation(12, 66);
			lblTemp.setSize(282, 20);
		}
		return lblTemp;
	}

	private JTextField getTxfName() {
		if (txfName == null) {
			txfName = new JTextField();
			txfName.setLocation(12, 34);
			txfName.setSize(282, 20);
		}
		return txfName;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel();
			lblName.setText("Behandlingsens navn:");
			lblName.setLocation(12, 12);
			lblName.setSize(282, 20);
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
