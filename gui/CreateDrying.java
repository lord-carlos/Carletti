package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Depot;
import model.Drying;
import model.ProcessLine;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import service.Service;

//VS4E -- DO NOT REMOVE THIS LINE!
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
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public CreateDrying(ProcessLine pl) {
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
		setTitle("Opret Tørretid");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(new Color(240, 240, 240));
		setModal(true);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getLblMinTime(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getLblIdealTime(), new Constraints(new Leading(12, 12, 12), new Leading(66, 12, 12)));
		add(getLblMaxTime(), new Constraints(new Leading(12, 12, 12), new Leading(120, 12, 12)));
		add(getTxfMaxTime(), new Constraints(new Leading(12, 170, 10, 10), new Leading(142, 12, 12)));
		add(getTxfIdealTime(), new Constraints(new Leading(12, 170, 12, 12), new Leading(88, 12, 12)));
		add(getTxfMinTime(), new Constraints(new Leading(12, 170, 12, 12), new Leading(34, 12, 12)));
		add(getLblDepot(), new Constraints(new Leading(209, 10, 10), new Leading(12, 12, 12)));
		add(getBtnChancel(), new Constraints(new Bilateral(209, 12, 81), new Trailing(12, 174, 174)));
		add(getBtnCreate(), new Constraints(new Leading(12, 170, 12, 12), new Trailing(12, 174, 174)));
		add(getMsl(), new Constraints(new Bilateral(209, 12, 0), new Bilateral(34, 44, 0)));
		setSize(405, 212);
	}

	private MultiSelectebleList getMsl() {
		if (msl == null) {
			msl = new MultiSelectebleList("Lagre");
			msl.setSize(184, 127);
			
		//	List<Depot> depoter= Service.getService().getAllDepots();
			//TODO fjern test depoter
			List<Depot> depoter= new ArrayList<Depot>();
			depoter.add(new Depot("asdf1", ""));
			depoter.add(new Depot("asdf2", ""));
			depoter.add(new Depot("asdf3", ""));
			depoter.add(new Depot("asdf4", ""));
			
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
		}
		return btnChancel;
	}

	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton();
			btnCreate.setText("Opret tørring");
			btnCreate.addActionListener(btnController);
		}
		return btnCreate;
	}

	private JLabel getLblDepot() {
		if (lblDepot == null) {
			lblDepot = new JLabel();
			lblDepot.setText("Lagre hvor tørringen skal foregå:");
		}
		return lblDepot;
	}

	private JTextField getTxfMaxTime() {
		if (txfMaxTime == null) {
			txfMaxTime = new JTextField();
		}
		return txfMaxTime;
	}

	private JLabel getLblMaxTime() {
		if (lblMaxTime == null) {
			lblMaxTime = new JLabel();
			lblMaxTime.setText("Maximum tørretid (timer):");
		}
		return lblMaxTime;
	}

	private JTextField getTxfIdealTime() {
		if (txfIdealTime == null) {
			txfIdealTime = new JTextField();
		}
		return txfIdealTime;
	}

	private JLabel getLblIdealTime() {
		if (lblIdealTime == null) {
			lblIdealTime = new JLabel();
			lblIdealTime.setText("Ideal tørretid (timer):");
		}
		return lblIdealTime;
	}

	private JTextField getTxfMinTime() {
		if (txfMinTime == null) {
			txfMinTime = new JTextField();
		}
		return txfMinTime;
	}

	private JLabel getLblMinTime() {
		if (lblMinTime == null) {
			lblMinTime = new JLabel();
			lblMinTime.setText("Minimums tørretid (timer):");
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
