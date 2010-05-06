package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
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
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public CreateProductTypeFrame() {
		initComponents();

		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setPreferredSize(this.getSize());
		this.pack();
		this.setLocationRelativeTo(null);

		thisProductType = new ProductType("");
		ProcessLine pL = new ProcessLine("", "", thisProductType);

		this.setProcessBtns(false);
		this.setVisible(true);
	}

	private void initComponents() {
		setTitle("Opret produkttype");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(new Color(240, 240, 240));
		setModal(true);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getLblName(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getLblDescription(), new Constraints(new Leading(12, 12, 12), new Leading(66, 50, 50)));
		add(getBtnChancel(), new Constraints(new Trailing(12, 246, 161, 234), new Trailing(12, 174, 283)));
		add(getTxfName(), new Constraints(new Bilateral(12, 274, 4), new Leading(34, 12, 12)));
		add(getBtnCreateDrying(), new Constraints(new Trailing(12, 246, 302, 283), new Trailing(50, 40, 40)));
		add(getBtnCreateSubProcess(), new Constraints(new Trailing(13, 244, 302, 283), new Trailing(82, 40, 40)));
		add(getBtnDeleteProcess(), new Constraints(new Trailing(12, 246, 302, 283), new Trailing(114, 40, 40)));
		add(getBtnMoveDown(), new Constraints(new Trailing(12, 302, 283), new Leading(94, 152, 152)));
		add(getBtnMoveUp(), new Constraints(new Trailing(12, 80, 284, 284), new Leading(50, 152, 152)));
		add(getScpProcesses(), new Constraints(new Trailing(109, 147, 302, 284), new Bilateral(34, 146, 22)));
		add(getLblProcesses(), new Constraints(new Leading(304, 219, 10, 10), new Leading(12, 174, 283)));
		add(getBtnCreateProductType(), new Constraints(new Bilateral(12, 274, 137), new Trailing(12, 66, 66)));
		add(getBtnPicture(), new Constraints(new Bilateral(12, 274, 81), new Trailing(50, 122, 192)));
		add(getScpDescription(), new Constraints(new Bilateral(12, 274, 22), new Bilateral(88, 82, 22)));
		setSize(560, 286);
	}

	private JButton getBtnPicture() {
		if (btnPicture == null) {
			btnPicture = new JButton();
			btnPicture.setText("Tilknyt et billede");
			btnPicture.addActionListener(btnController);
		}
		return btnPicture;
	}

	private JScrollPane getScpProcesses() {
		if (scpProcesses == null) {
			scpProcesses = new JScrollPane();
			scpProcesses.setViewportView(getListProcesses());
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
		}
		return btnDeleteProcess;
	}

	private JButton getBtnMoveUp() {
		if (btnMoveUp == null) {
			btnMoveUp = new JButton();
			btnMoveUp.setText("Ryk op");
			btnMoveUp.addActionListener(btnController);
		}
		return btnMoveUp;
	}

	private JButton getBtnMoveDown() {
		if (btnMoveDown == null) {
			btnMoveDown = new JButton();
			btnMoveDown.setText("Ryk ned");
			btnMoveDown.addActionListener(btnController);
		}
		return btnMoveDown;
	}

	private JButton getBtnChancel() {
		if (btnChancel == null) {
			btnChancel = new JButton();
			btnChancel.setText("Annuller");
			btnChancel.addActionListener(btnController);
		}
		return btnChancel;
	}

	private JButton getBtnCreateSubProcess() {
		if (btnCreateSubProcess == null) {
			btnCreateSubProcess = new JButton();
			btnCreateSubProcess.setText("Opret behandling");
			btnCreateSubProcess.addActionListener(btnController);
		}
		return btnCreateSubProcess;
	}

	private JButton getBtnCreateDrying() {
		if (btnCreateDrying == null) {
			btnCreateDrying = new JButton();
			btnCreateDrying.setText("Opret tørring");
			btnCreateDrying.addActionListener(btnController);
		}
		return btnCreateDrying;
	}

	private JLabel getLblProcesses() {
		if (lblProcesses == null) {
			lblProcesses = new JLabel();
			lblProcesses.setText("Produkttypens delbehandlinger:");
		}
		return lblProcesses;
	}

	private JScrollPane getScpDescription() {
		if (scpDescription == null) {
			scpDescription = new JScrollPane();
			scpDescription.setViewportView(getTxaDescription());
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
		}
		return lblDescription;
	}

	private JButton getBtnCreateProductType() {
		if (btnCreateProductType == null) {
			btnCreateProductType = new JButton();
			btnCreateProductType.setText("Opret produkttype");
			btnCreateProductType.addActionListener(btnController);
		}
		return btnCreateProductType;
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
			lblName.setText("Produkttypens navn:");
		}
		return lblName;
	}



	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {

		CreateProductTypeFrame frame = new CreateProductTypeFrame();

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
				
				File activeFile;
				
				EditorFileHandler choosenfil = new EditorFileHandler(EditorFileHandler.LOAD_FUNCTION, new File(System.getProperty("user.dir")));
				if (choosenfil.getIsOkPressed()){
					activeFile = choosenfil.getSelectedFile();
					
					thisProductType.setPicture(new ImageIcon(activeFile.getPath()));
					btnPicture.setText("Tilknyt et billede ("+activeFile.getPath()+")");
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

	public ProductType getProductType(){
		return productTypeToReturn;
	}

	private void setProcessBtns(boolean b){
		btnDeleteProcess.setEnabled(b);
		btnMoveUp.setEnabled(b);
		btnMoveDown.setEnabled(b);
	}

}
