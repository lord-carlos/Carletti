package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Process;
import model.ProcessLine;
import model.ProductType;

/** 
 * @author M. C. Høj
 */

public class CreateProductTypeFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblName;
	private JTextField txfName;
	private JButton btnCreateProductType;
	private JLabel lblDescription;
	private JScrollPane scpDescription;
	private JTextArea txaDescription;
	private JLabel lblProcesses;
	private JButton btnCreateDrying;
	private JButton btnCreateSubProcess;
	private JButton btnDeleteProcess;
	private JButton btnMoveUp;
	private JButton btnMoveDown;
	private JButton btnChancel;
	private JList listProcesses;
	private JScrollPane scpProcesses;
	private BtnController btnController = new BtnController();
	private ProductType thisProductType = null;
	private ProductType productTypeToReturn;
	private DefaultListModel listProcessesModel = new DefaultListModel();
	private JButton btnPicture;


	public CreateProductTypeFrame() {
		initComponents();

		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		thisProductType = new ProductType("");
		ProcessLine pL = new ProcessLine("", "", thisProductType);

		this.setProcessBtns(false);
		this.setVisible(true);
	}

	private void initComponents() {
		setTitle("Opret produkttype");
		setLayout(null);
		add(getLblName());
		add(getLblDescription());
		add(getBtnChancel());
		add(getTxfName());
		add(getBtnCreateDrying());
		add(getBtnCreateSubProcess());
		add(getBtnDeleteProcess());
		add(getBtnMoveDown());
		add(getBtnMoveUp());
		add(getScpProcesses());
		add(getLblProcesses());
		add(getBtnCreateProductType());
		add(getBtnPicture());
		add(getScpDescription());
		setSize(548, 312);
	}

	private JButton getBtnPicture() {
		if (btnPicture == null) {
			btnPicture = new JButton();
			btnPicture.setText("Tilknyt et billede");
			btnPicture.addActionListener(btnController);
			btnPicture.setLocation(12, 212);
			btnPicture.setSize(250, 25);
		}
		return btnPicture;
	}

	private JScrollPane getScpProcesses() {
		if (scpProcesses == null) {
			scpProcesses = new JScrollPane();
			scpProcesses.setViewportView(getListProcesses());
			scpProcesses.setLocation(280, 34);
			scpProcesses.setSize(158, 113);
		}
		return scpProcesses;
	}

	private JList getListProcesses() {
		if (listProcesses == null) {
			listProcesses = new JList();
			listProcesses.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
			listProcesses.setModel(listProcessesModel);
		}
		return listProcesses;
	}

	private JButton getBtnDeleteProcess() {
		if (btnDeleteProcess == null) {
			btnDeleteProcess = new JButton();
			btnDeleteProcess.setText("Slet delbehandling");
			btnDeleteProcess.addActionListener(btnController);
			btnDeleteProcess.setLocation(280, 152);
			btnDeleteProcess.setSize(250, 25);
		}
		return btnDeleteProcess;
	}

	private JButton getBtnMoveUp() {
		if (btnMoveUp == null) {
			btnMoveUp = new JButton();
			btnMoveUp.setText("Ryk op");
			btnMoveUp.addActionListener(btnController);
			btnMoveUp.setLocation(450, 58);
			btnMoveUp.setSize(80, 25);
		}
		return btnMoveUp;
	}

	private JButton getBtnMoveDown() {
		if (btnMoveDown == null) {
			btnMoveDown = new JButton();
			btnMoveDown.setText("Ryk ned");
			btnMoveDown.addActionListener(btnController);
			btnMoveDown.setLocation(450, 98);
			btnMoveDown.setSize(80, 25);
		}
		return btnMoveDown;
	}

	private JButton getBtnChancel() {
		if (btnChancel == null) {
			btnChancel = new JButton();
			btnChancel.setText("Annuller");
			btnChancel.addActionListener(btnController);
			btnChancel.setLocation(280, 249);
			btnChancel.setSize(250, 25);
		}
		return btnChancel;
	}

	private JButton getBtnCreateSubProcess() {
		if (btnCreateSubProcess == null) {
			btnCreateSubProcess = new JButton();
			btnCreateSubProcess.setText("Opret behandling");
			btnCreateSubProcess.addActionListener(btnController);
			btnCreateSubProcess.setLocation(280, 182);
			btnCreateSubProcess.setSize(250, 25);
		}
		return btnCreateSubProcess;
	}

	private JButton getBtnCreateDrying() {
		if (btnCreateDrying == null) {
			btnCreateDrying = new JButton();
			btnCreateDrying.setText("Opret toerring");
			btnCreateDrying.addActionListener(btnController);
			btnCreateDrying.setLocation(280, 212);
			btnCreateDrying.setSize(250, 25);
		}
		return btnCreateDrying;
	}

	private JLabel getLblProcesses() {
		if (lblProcesses == null) {
			lblProcesses = new JLabel();
			lblProcesses.setText("Produkttypens delbehandlinger:");
			lblProcesses.setLocation(280, 12);
			lblProcesses.setSize(200, 20);
		}
		return lblProcesses;
	}

	private JScrollPane getScpDescription() {
		if (scpDescription == null) {
			scpDescription = new JScrollPane();
			scpDescription.setViewportView(getTxaDescription());
			scpDescription.setLocation(12, 88);
			scpDescription.setSize(250, 120);
		}
		return scpDescription;
	}

	private JTextArea getTxaDescription() {
		if (txaDescription == null) {
			txaDescription = new JTextArea();
		}
		return txaDescription;
	}

	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel();
			lblDescription.setText("Beskrivelse:");
			lblDescription.setLocation(12, 66);
			lblDescription.setSize(250, 20);
		}
		return lblDescription;
	}

	private JButton getBtnCreateProductType() {
		if (btnCreateProductType == null) {
			btnCreateProductType = new JButton();
			btnCreateProductType.setText("Opret produkttype");
			btnCreateProductType.addActionListener(btnController);
			btnCreateProductType.setLocation(12, 249);
			btnCreateProductType.setSize(250, 25);
		}
		return btnCreateProductType;
	}

	private JTextField getTxfName() {
		if (txfName == null) {
			txfName = new JTextField();
			txfName.setLocation(12, 34);
			txfName.setSize(250, 20);
		}
		return txfName;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel();
			lblName.setText("Produkttypens navn:");
			lblName.setLocation(12, 12);
			lblName.setSize(250, 20);
		}
		return lblName;
	}

	private class BtnController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(btnCreateDrying)){
				CreateDrying cd = new CreateDrying(thisProductType.getProcessLine());
				if (cd.getDrying()!=null){
					listProcessesModel.addElement(cd.getDrying());
					CreateProductTypeFrame.this.setProcessBtns(true);
				}
			} else if (e.getSource().equals(btnCreateSubProcess)){
				CreateSubProcess csp = new CreateSubProcess(thisProductType.getProcessLine());
				if (csp.getSubProcess()!=null){
					listProcessesModel.addElement(csp.getSubProcess());
					CreateProductTypeFrame.this.setProcessBtns(true);
				}
			} else if (e.getSource().equals(btnPicture)){


				if (thisProductType.getPicture()==null){
					File activeFile;

					EditorFileHandler choosenfil = new EditorFileHandler(EditorFileHandler.LOAD_FUNCTION, new File(System.getProperty("user.dir")+"\\gui\\icons"));
					if (choosenfil.getIsOkPressed()){
						activeFile = choosenfil.getSelectedFile();

						thisProductType.setPicture(new ImageIcon(activeFile.getPath()));
						btnPicture.setText("Fjern billede ("+activeFile.getPath()+")");
					}
				} else {
					thisProductType.setPicture(null);
					btnPicture.setText("Tilknyt et billede");
				}


			} else if (e.getSource().equals(btnDeleteProcess)){

				int index = listProcesses.getSelectedIndex();

				if (index>=0){


					listProcessesModel.remove(index);
					thisProductType.getProcessLine().getProcesses().remove(index);

					if (listProcessesModel.size()<1){
						CreateProductTypeFrame.this.setProcessBtns(false);
					}

				}



			} else if (e.getSource().equals(btnMoveDown)){
				int index = listProcesses.getSelectedIndex();

				if (index>=0 && index<listProcessesModel.size()-1){
					Process elementTop = thisProductType.getProcessLine().getProcesses().get(index);
					Process elementBottom = thisProductType.getProcessLine().getProcesses().get(index+1);

					thisProductType.getProcessLine().getProcesses().set(index+1, elementTop);
					listProcessesModel.set(index+1, elementTop);

					thisProductType.getProcessLine().getProcesses().set(index, elementBottom);
					listProcessesModel.set(index, elementBottom);

					listProcesses.setSelectedIndex(index+1);
				}

			} else if (e.getSource().equals(btnMoveUp)){

				int index = listProcesses.getSelectedIndex();

				if (index>0){
					Process elementTop = thisProductType.getProcessLine().getProcesses().get(index-1);
					Process elementBottom = thisProductType.getProcessLine().getProcesses().get(index);

					thisProductType.getProcessLine().getProcesses().set(index, elementTop);
					listProcessesModel.set(index, elementTop);

					thisProductType.getProcessLine().getProcesses().set(index-1, elementBottom);
					listProcessesModel.set(index-1, elementBottom);
					listProcesses.setSelectedIndex(index-1);
				}

			} else if (e.getSource().equals(btnCreateProductType)){

				if (getTxfName().getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Produkttypen skal have et navn!!!","Fejl!!!",JOptionPane.ERROR_MESSAGE);

				} else {


					for (int i=0; listProcessesModel.size()>i;i++){
						Process p = (Process)listProcessesModel.get(0);
						p.setProcessStep(i+1);
					}

					thisProductType.setName(txfName.getText());
					ProcessLine pl = thisProductType.getProcessLine();
					pl.setName(txfName.getText());
					pl.setDescription(txaDescription.getText());

					productTypeToReturn=thisProductType;

					CreateProductTypeFrame.this.setVisible(false);
				}
			} else if (e.getSource().equals(btnChancel)){
				CreateProductTypeFrame.this.setVisible(false);
			}

		}

	}

	/**
	 * returnere den producttype som vi laver. Vil returnere null, hvis vi annullere
	 * @return
	 */
	public ProductType getProductType(){
		return productTypeToReturn;
	}

	private void setProcessBtns(boolean b){
		btnDeleteProcess.setEnabled(b);
		btnMoveUp.setEnabled(b);
		btnMoveDown.setEnabled(b);
	}

}
