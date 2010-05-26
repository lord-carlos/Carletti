package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.IntermediateProduct;
import model.ProductType;
import service.Service;

/** 
 * @author M. C. Høj
 */

public class CreateIntermediateProductFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblProduktType;
	private JComboBox cmbProductType;
	private JLabel lblQuantity;
	private JTextField txfQuantity;
	private JLabel lblId;
	private JTextField txfId;
	private JLabel image;
	private JScrollPane scpInfo;
	private JTextArea txaInfo;
	private JButton btnCreate;
	private JButton btnChancel;
	private BtnController btnController = new BtnController();	
	private IntermediateProduct thisProduct = null;
	
	public CreateIntermediateProductFrame() {
		initComponents();

		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setInfo();

		this.setVisible(true);
	}

	private void initComponents() {
		setTitle("Opret mellemvare");
		setLayout(null);
		add(getLblProductType());
		add(getCmbProductType());
		add(getLblQuantity());
		add(getTxfQuantity());
		add(getLblId());
		add(getTxfId());
		add(getscpInfo());
		add(getBtnCreate());
		add(getBtnChancel());
		add(getImg());
		setSize(442, 273);
	}

	/**
	 * sætter infoen som er tilknyttet den valgte produkttype.
	 * sætter desuden størrelsen på tekstfeltet alt efter om producttypen er tilknyttet et billed
	 */
	private void setInfo(){
		ProductType thisProductType = (ProductType) cmbProductType.getSelectedItem();

		if (thisProductType.getPicture()==null){

			image.setVisible(false);
			scpInfo.setBounds(174, 12, 250, 226);

		} else {

			image.setVisible(true);
			scpInfo.setBounds(174, 124, 250, 114);

		}

		image.setIcon(new ImageIcon(thisProductType.getPicture()));

	String pInfo = "Beskrivelse:\n";
	pInfo = pInfo+thisProductType.getProcessLine().getDescription();
	pInfo = pInfo+"\n\nBehandlinger:";
	
	List<model.Process> processes = thisProductType.getProcessLine().getProcesses();
	for (int i = 0; i < processes.size(); i++) {
		pInfo=pInfo+"\n"+processes.get(i);
	}
	
	txaInfo.setText(pInfo);
	
	

	}

	private JButton getBtnChancel() {
		if (btnChancel == null) {
			btnChancel = new JButton();
			btnChancel.setText("Annuller");
			btnChancel.setBounds(12, 212, 150, 25);
			btnChancel.addActionListener(btnController);
		}
		return btnChancel;
	}

	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton();
			btnCreate.setText("Opret mellemvare");
			btnCreate.setBounds(12, 180, 150, 25);
			btnCreate.addActionListener(btnController);
		}
		return btnCreate;
	}

	private JTextArea getTxaInfo() {
		if (txaInfo == null) {
			txaInfo = new JTextArea();
			txaInfo.setEditable(false);
		}
		return txaInfo;
	}

	private JScrollPane getscpInfo() {
		if (scpInfo == null) {
			scpInfo = new JScrollPane(getTxaInfo());
			scpInfo.setBounds(174, 124, 250, 114);
		}
		return scpInfo;
	}

	private JLabel getImg() {
		if (image == null) {
			image = new JLabel();
			image.setBounds(229, 12, 140, 100);
		}
		return image;
	}
	
	private JTextField getTxfQuantity() {
		if (txfQuantity == null) {
			txfQuantity = new JTextField();
			txfQuantity.setBounds(12, 148, 150, 20);
		}
		return txfQuantity;
	}

	private JLabel getLblQuantity() {
		if (lblQuantity == null) {
			lblQuantity = new JLabel();
			lblQuantity.setText("Mellemvare maengde:");
			lblQuantity.setBounds(12, 125, 150, 20);
		}
		return lblQuantity;
	}

	private JTextField getTxfId() {
		if (txfId == null) {
			txfId = new JTextField();
			txfId.setBounds(12, 93, 150, 20);
		}
		return txfId;
	}

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel();
			lblId.setText("Mellemvarens id:");
			lblId.setBounds(12, 71, 150, 20);
		}
		return lblId;
	}

	private JComboBox getCmbProductType() {
		if (cmbProductType == null) {
			cmbProductType = new JComboBox();
			cmbProductType.setModel(new DefaultComboBoxModel(new Object[] {}));
			cmbProductType.setDoubleBuffered(false);
			cmbProductType.setBorder(null);
			cmbProductType.setBounds(12, 34, 150, 25);

			List<ProductType> productTypes= Service.getService().getAllProductTypes();

			for (int i = 0; i < productTypes.size(); i++) {
				cmbProductType.addItem(productTypes.get(i));
			}

			cmbProductType.setSelectedIndex(0);

			cmbProductType.addActionListener(btnController);

		}
		return cmbProductType;
	}

	private JLabel getLblProductType() {
		if (lblProduktType == null) {
			lblProduktType = new JLabel();
			lblProduktType.setText("Vaelg produkttype:");
			lblProduktType.setBounds(12, 12, 150, 20);
		}
		return lblProduktType;
	}

	private class BtnController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(btnChancel)){
				CreateIntermediateProductFrame.this.setVisible(false);

			} else if (e.getSource().equals(btnCreate)){

				try {
					
					if (txfId.getText().isEmpty()){
						JOptionPane.showMessageDialog(null,"Mellemvaren skal have en id","Fejl!!!",JOptionPane.ERROR_MESSAGE);
					} else {
						thisProduct = new IntermediateProduct(txfId.getText(), (ProductType) cmbProductType.getSelectedItem(), Double.valueOf(txfQuantity.getText()));
						CreateIntermediateProductFrame.this.setVisible(false);
					}
					
				}catch (NumberFormatException exception){
					JOptionPane.showMessageDialog(null,"Mængden skal være et tal","Fejl!!!",JOptionPane.ERROR_MESSAGE);
				}
				catch (RuntimeException exception){
					JOptionPane.showMessageDialog(null,"Mængden skal være større end 0","Fejl!!!",JOptionPane.ERROR_MESSAGE);
				}

			} else if (e.getSource().equals(cmbProductType)){

				setInfo();

			}

		}
	}
	
	/**
	 * returnere den mellemvare som vi laver. Vil returnere null, hvis vi annullere
	 * @return
	 */
	public IntermediateProduct getIntermediateProduct() {
		return thisProduct;
	}

}
