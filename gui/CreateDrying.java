package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Depot;
import model.Drying;
import model.ProcessLine;

import service.Service;

public class CreateDrying extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblMinTime;
	private JTextField txfMinTime;
	private JLabel lblIdealTime;
	private JTextField txfIdealTime;
	private JLabel lblMaxTime;
	private JTextField txfMaxTime;
	private JLabel lblDepot;

	private JButton btnCreate;
	private JButton btnChancel;
	private ProcessLine pl;
	private Drying thisDrying;
	private BtnController btnController = new BtnController();
	private MultiSelectebleList msl;
	
	public CreateDrying(ProcessLine pl) {
		initComponents();

		this.pl=pl;
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	private void initComponents() {
		setTitle("Opret Tørreing");
		setLayout(null);
		add(getLblMinTime());
		add(getLblIdealTime());
		add(getLblMaxTime());
		add(getTxfMaxTime());
		add(getTxfIdealTime());
		add(getTxfMinTime());
		add(getLblDepot());
		add(getBtnChancel());
		add(getBtnCreate());
		add(getMsl());
		setSize(400, 235);
	}

	private MultiSelectebleList getMsl() {
		if (msl == null) {
			msl = new MultiSelectebleList("Lagre");
			msl.setLocation(194, 34);
			msl.setSize(190, 127);

			List<Depot> depoter= Service.getService().getAllDepots();

			for (int i = 0; i < depoter.size(); i++) {
				msl.add(depoter.get(i), false);
			}

		}
		return msl;
	}

	private JButton getBtnChancel() {
		if (btnChancel == null) {
			btnChancel = new JButton();
			btnChancel.setText("Annuller");
			btnChancel.addActionListener(btnController);
			btnChancel.setLocation(194, 174);
			btnChancel.setSize(190, 25);
		}
		return btnChancel;
	}

	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton();
			btnCreate.setText("Opret tørring");
			btnCreate.addActionListener(btnController);
			btnCreate.setLocation(12, 174);
			btnCreate.setSize(170, 25);
		}
		return btnCreate;
	}

	private JLabel getLblDepot() {
		if (lblDepot == null) {
			lblDepot = new JLabel();
			lblDepot.setText("Lagre hvor tørringen skal foregå:");
			lblDepot.setLocation(194, 12);
			lblDepot.setSize(190, 20);
		}
		return lblDepot;
	}

	private JTextField getTxfMaxTime() {
		if (txfMaxTime == null) {
			txfMaxTime = new JTextField();
			txfMaxTime.setLocation(12, 142);
			txfMaxTime.setSize(170, 20);
		}
		return txfMaxTime;
	}

	private JLabel getLblMaxTime() {
		if (lblMaxTime == null) {
			lblMaxTime = new JLabel();
			lblMaxTime.setText("Maximum tørretid (timer):");
			lblMaxTime.setLocation(12, 120);
			lblMaxTime.setSize(170, 20);
		}
		return lblMaxTime;
	}

	private JTextField getTxfIdealTime() {
		if (txfIdealTime == null) {
			txfIdealTime = new JTextField();
			txfIdealTime.setLocation(12, 88);
			txfIdealTime.setSize(170, 20);
		}
		return txfIdealTime;
	}

	private JLabel getLblIdealTime() {
		if (lblIdealTime == null) {
			lblIdealTime = new JLabel();
			lblIdealTime.setText("Ideal tørretid (timer):");
			lblIdealTime.setLocation(12, 66);
			lblIdealTime.setSize(170, 20);
		}
		return lblIdealTime;
	}

	private JTextField getTxfMinTime() {
		if (txfMinTime == null) {
			txfMinTime = new JTextField();
			txfMinTime.setLocation(12, 34);
			txfMinTime.setSize(170, 20);
		}
		return txfMinTime;
	}

	private JLabel getLblMinTime() {
		if (lblMinTime == null) {
			lblMinTime = new JLabel();
			lblMinTime.setText("Minimums tørretid (timer):");
			lblMinTime.setLocation(12, 12);
			lblMinTime.setSize(170, 20);
		}
		return lblMinTime;
	}

	public Drying getDrying(){
		return thisDrying;
	}

	private class BtnController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(btnChancel)){
				CreateDrying.this.setVisible(false);
			} else if (e.getSource().equals(btnCreate)){

				if (msl.getSelectedElements().isEmpty()){
					JOptionPane.showMessageDialog(null,"Tørringsprocessen skal være tilknyttet mindst et lager!!!","Fejl!!!",JOptionPane.ERROR_MESSAGE);
				} else {
					long minTime;
					long idealTime;
					long maxTime;
					try {
						minTime = Long.valueOf(getTxfMinTime().getText())*60*60*1000;
						idealTime = Long.valueOf(getTxfIdealTime().getText())*60*60*1000;
						maxTime = Long.valueOf(getTxfMaxTime().getText())*60*60*1000;

						thisDrying = pl.createDrying(0, minTime, idealTime, maxTime);

						for (int i = 0; i < msl.getSelectedElements().size(); i++) {
							thisDrying.addDepot((Depot)msl.getSelectedElements().get(i));
						}

						System.out.println(thisDrying.getDepots());

						CreateDrying.this.setVisible(false);
					}
					catch (NumberFormatException exception){
						JOptionPane.showMessageDialog(null,"Tørringentiderne kan ikke godtages!!!","Fejl!!!",JOptionPane.ERROR_MESSAGE);
					}
					catch (RuntimeException exception){
						JOptionPane.showMessageDialog(null,"Tørringentiderne kan ikke godtages!!!","Fejl!!!",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}
