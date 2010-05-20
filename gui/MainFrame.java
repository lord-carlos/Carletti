package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Depot;
import model.Drying;
import model.IntermediateProduct;
import model.Process;
import model.StoringSpace;
import service.Service;
import sun.awt.WindowClosingListener;

/** 
 * @author Carl, Brian
 */

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1752000392799508369L;
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//MENU
	private JMenuBar mnbBar;
	private JMenu mnuView, mnuCreate;
	private JMenuItem mitCreateProductType, mitCreateIntermediateProduct, mitViewDepot;
	private ArrayList<JMenuItem> mitDepots = new ArrayList<JMenuItem>();
	// WEST
	private JPanel pnlWest;
	private JLabel lblIntermediateProduct;
	private JTextField txfSearch;
	private JScrollPane scpIntermediateProducts;
	private JList lstIntermediateProducts;
	private JButton btnCreateIntermediateProduct;
	//CENTER
	private JPanel pnlCenter;
	private JPanel pnlSelectedDepot;
	private JLabel lblSelectedDepot;
	private JPanel pnlIntermediateProductMap;
	private GridLayout lytIntermediateProductMap = new GridLayout();
	private ArrayList<IntermediateProductPanel> intermediateProductPanels = new ArrayList<IntermediateProductPanel>();
	//EAST
	private JPanel pnlEast;
	private JLabel lblInformation;
	private JLabel lblID, lblQuantity, lblProductType, lblDepot, lblCoordinates;
	private JTextField txfID, txfQuantity, txfProductType, txfDepot, txfCoordinates;
	private JPanel pnlProcessOverView;
	private ArrayList<ProcessPanel> processPanels = new ArrayList<ProcessPanel>();
	private JButton btnSendToNextProcess, btnDeleteIntermediateProduct;

	private IntermediateProductPanel selectedIntermediateProductPanel = null;
	private CreateProductTypeFrame createProductTypeFrame;
	private CreateIntermediateProductFrame createIntermediateProduct;
	private Controller controller = new Controller();
	private UpdateTimer updateTimer;

	public MainFrame() {
		this.addWindowListener(controller);
		this.setTitle("Carletti v1.1");
		BorderLayout thisLayout = new BorderLayout();
		getContentPane().setLayout(thisLayout);
		this.setResizable(true);
		this.setPreferredSize(new Dimension(600,600));	
		{
			pnlWest = new JPanel();
			getContentPane().add(pnlWest, BorderLayout.WEST);
			pnlWest.setPreferredSize(new Dimension(170,700));
			pnlWest.setLayout(new FlowLayout());
			{	
				{
					lblIntermediateProduct = new JLabel("Mellemvarer:");
					lblIntermediateProduct.setFont(lblIntermediateProduct.getFont().deriveFont(lblIntermediateProduct.getFont().getStyle() ^ Font.BOLD));
					lblIntermediateProduct.setPreferredSize(new Dimension(160,25));
					pnlWest.add(lblIntermediateProduct);
				}
				{
					txfSearch = new JTextField();
					txfSearch.setPreferredSize(new Dimension(160,25));
					txfSearch.getDocument().addDocumentListener(controller);
					pnlWest.add(txfSearch);
				}
				{
					scpIntermediateProducts = new JScrollPane();
					pnlWest.add(scpIntermediateProducts);
					scpIntermediateProducts.setPreferredSize(new Dimension(160,200));

					{
						ListModel lstIntermediateProductsModel = new DefaultListModel();
						lstIntermediateProducts = new JList();
						lstIntermediateProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						scpIntermediateProducts.setViewportView(lstIntermediateProducts);
						lstIntermediateProducts.setModel(lstIntermediateProductsModel);
						lstIntermediateProducts.addListSelectionListener(controller);
					}
				}
				{
					btnCreateIntermediateProduct = new JButton();
					btnCreateIntermediateProduct.setText("Opret Mellemvare");
					btnCreateIntermediateProduct.setPreferredSize(new Dimension(160,25));
					btnCreateIntermediateProduct.addActionListener(controller);
					pnlWest.add(btnCreateIntermediateProduct);	
					btnCreateIntermediateProduct.setMnemonic(KeyEvent.VK_M);
				}

			}
		}
		{
			pnlEast = new JPanel();
			getContentPane().add(pnlEast, BorderLayout.EAST);
			pnlEast.setPreferredSize(new Dimension(170, 700));
			pnlEast.setLayout(new FlowLayout());
			{
				{
					lblInformation = new JLabel();
					pnlEast.add(lblInformation);
					lblInformation.setFont(lblInformation.getFont().deriveFont(lblInformation.getFont().getStyle() ^ Font.BOLD));
					lblInformation.setText("Information:");
					lblInformation.setPreferredSize(new Dimension(160,25));
				}
				{
					lblID = new JLabel();
					pnlEast.add(lblID);
					lblID.setText("ID:");
					lblID.setPreferredSize(new Dimension(80,25));
				}
				{
					txfID = new JTextField();
					pnlEast.add(txfID);
					txfID.setPreferredSize(new Dimension(80,25));
					txfID.setEditable(false);
				}
				{
					lblQuantity = new JLabel();
					pnlEast.add(lblQuantity);
					lblQuantity.setText("Antal:");
					lblQuantity.setPreferredSize(new Dimension(80,25));
				}
				{
					txfQuantity = new JTextField();
					pnlEast.add(txfQuantity);
					txfQuantity.setPreferredSize(new Dimension(80,25));
					txfQuantity.setEditable(false);
				}
				{
					lblProductType = new JLabel();
					pnlEast.add(lblProductType);
					lblProductType.setText("Produkt type:");
					lblProductType.setPreferredSize(new Dimension(160,25));
				}
				{
					txfProductType = new JTextField();
					pnlEast.add(txfProductType);
					txfProductType.setPreferredSize(new Dimension(160,25));
					txfProductType.setEditable(false);
				}
				{
					lblDepot = new JLabel();
					pnlEast.add(lblDepot);
					lblDepot.setText("Lager:");
					lblDepot.setPreferredSize(new Dimension(80,25));
				}
				{
					txfDepot = new JTextField();
					pnlEast.add(txfDepot);
					txfDepot.setPreferredSize(new Dimension(80,25));
					txfDepot.setEditable(false);
				}
				{
					lblCoordinates = new JLabel();
					pnlEast.add(lblCoordinates);
					lblCoordinates.setText("Position:");
					lblCoordinates.setPreferredSize(new Dimension (80,25));
				}
				{
					txfCoordinates = new JTextField();
					pnlEast.add(txfCoordinates);
					txfCoordinates.setPreferredSize(new Dimension(80,25));
					txfCoordinates.setEditable(false);
				}
				{
					pnlProcessOverView = new JPanel();
					BoxLayout pnlProcessOverViewLayout = new BoxLayout(pnlProcessOverView, BoxLayout.Y_AXIS);
					pnlProcessOverView.setLayout(pnlProcessOverViewLayout);
					pnlEast.add(pnlProcessOverView);
				}
				{
					btnSendToNextProcess = new JButton();
					pnlEast.add(btnSendToNextProcess);
					btnSendToNextProcess.setText("Viderebehandle");
					btnSendToNextProcess.setPreferredSize(new Dimension(160,25));
					btnSendToNextProcess.addActionListener(controller);
					btnSendToNextProcess.setMnemonic(KeyEvent.VK_I);
				}
				{
					btnDeleteIntermediateProduct = new JButton();
					pnlEast.add(btnDeleteIntermediateProduct);
					btnDeleteIntermediateProduct.setText("Kassere mellemvare");
					btnDeleteIntermediateProduct.setPreferredSize(new Dimension(160,25));
					btnDeleteIntermediateProduct.addActionListener(controller);
					btnDeleteIntermediateProduct.setMnemonic(KeyEvent.VK_K);
				}
			}
		}
		{
			pnlCenter = new JPanel();
			getContentPane().add(pnlCenter, BorderLayout.CENTER);
			pnlCenter.setLayout(new BorderLayout());
			{
				pnlSelectedDepot = new JPanel();
				pnlCenter.add(pnlSelectedDepot, BorderLayout.NORTH);
				pnlSelectedDepot.setLayout(new FlowLayout());
				{
					lblSelectedDepot = new JLabel();
					pnlSelectedDepot.add(lblSelectedDepot, BorderLayout.NORTH);
					lblSelectedDepot.setPreferredSize(new Dimension(100,25));
					lblSelectedDepot.setFont(lblSelectedDepot.getFont().deriveFont(lblSelectedDepot.getFont().getStyle() ^ Font.BOLD));

				}
			}
			{
				pnlIntermediateProductMap = new JPanel();
				pnlCenter.add(pnlIntermediateProductMap, BorderLayout.CENTER);
				lytIntermediateProductMap.setHgap(4);
				lytIntermediateProductMap.setVgap(4);
				pnlIntermediateProductMap.setLayout(lytIntermediateProductMap);
				pnlIntermediateProductMap.setBorder(BorderFactory.createEtchedBorder());
			}
		}
		{
			mnbBar = new JMenuBar();
			setJMenuBar(mnbBar);
			{
				mnuCreate = new JMenu();
				mnbBar.add(mnuCreate);
				mnuCreate.setText("Opret");
				mnuCreate.setMnemonic(KeyEvent.VK_O);
				{
					mitCreateProductType = new JMenuItem();
					mnuCreate.add(mitCreateProductType);
					mitCreateProductType.setText("Opret Produkttype");
					mitCreateProductType.addActionListener(controller);
				}
				{
					mitCreateIntermediateProduct = new JMenuItem();
					mnuCreate.add(mitCreateIntermediateProduct);
					mitCreateIntermediateProduct.setText("Opret Mellemvare");
					mitCreateIntermediateProduct.addActionListener(controller);
				}
			}
			{
				mnuView = new JMenu();
				mnbBar.add(mnuView);
				mnuView.setText("Vis");
				mnuView.setMnemonic(KeyEvent.VK_V);
				{
					mitViewDepot = new JMenu();
					mnuView.add(mitViewDepot);
					mitViewDepot.setText("Vis lager");
					{
						fillChooseDepotMenu();

					}
				}
			}
		}
		pack();

		controller.fillLstIntermediateProducts();
		updateTimer = new UpdateTimer(2, intermediateProductPanels);

	}
	public void fillChooseDepotMenu() {
		mitViewDepot.removeAll();
		mitDepots.clear();
		mitViewDepot.updateUI();
		for (Depot depot : Service.getService().getAllDepots()) {
			JMenuItem mitDepot = new JMenuItem();
			mitDepot.setText(depot.getName());
			mitDepot.addActionListener(controller);
			mitDepots.add(mitDepot);
			mitViewDepot.add(mitDepot);
		}
		if(mitDepots.size()>0) {
			updateDepotMap(Service.getService().getAllDepots().get(0));
		}

	}
	// Denne metode kræver at arraylisten med StoringSpaces i depot er efter læse systemet
	public void updateDepotMap(Depot depot) {
		lblSelectedDepot.setText(depot.getName());
		pnlIntermediateProductMap.removeAll();
		intermediateProductPanels.clear();
		pnlIntermediateProductMap.updateUI();
		lytIntermediateProductMap.setColumns(depot.getMaxX());
		lytIntermediateProductMap.setRows(depot.getMaxY());

		for (StoringSpace storingSpace : depot.getStoringSpaces()) {
			IntermediateProductPanel intermediateProductPanel = new IntermediateProductPanel(storingSpace);
			intermediateProductPanel.addMouseListener(controller);
			intermediateProductPanels.add(intermediateProductPanel);
			pnlIntermediateProductMap.add(intermediateProductPanel);
		} 
	}

	/**
	 * Denne methode bliver udfoert ver gang man klicker paa en storingspace med musen i guien
	 * @param intermediateProductPanel
	 */
	public void updateInfoFromPanel(IntermediateProductPanel intermediateProductPanel) {
		if (selectedIntermediateProductPanel != intermediateProductPanel) {
			if (selectedIntermediateProductPanel != null) { //unselecter den gamle storingspace
				selectedIntermediateProductPanel.setSelected(false); 
			}
			intermediateProductPanel.setSelected(true);
			selectedIntermediateProductPanel = intermediateProductPanel;
			lstIntermediateProducts.setSelectedValue(intermediateProductPanel.getStoringSpace().getIntermediateProduct(), true);
			updateInfo();
		}
	}

	public void updateInfoFromList(IntermediateProduct intermediateProduct) {
		if(intermediateProduct != null) {
			if(intermediateProduct.getActivProcessLog() != null) {
				if(intermediateProduct.getActivProcessLog().getProcess().getClass().getName().equals("model.Drying")) {
					updateDepotMap(intermediateProduct.getStoringSpace().getDepot());
					for (IntermediateProductPanel intermediateProductPanel : intermediateProductPanels) {
						if (intermediateProductPanel.getStoringSpace().getIntermediateProduct() == intermediateProduct) {
							updateInfoFromPanel(intermediateProductPanel);
						}
					}
				}
				else {
					updateInfo();
				}
			}
			else {
				updateInfo();
			}
		}
	}

	private void updateProcessOverView(IntermediateProduct intermediateProduct) {
		if(intermediateProduct != null) {
			pnlProcessOverView.removeAll();
			processPanels.clear();
			pnlProcessOverView.updateUI();

			for (Process process : intermediateProduct.getProductType().getProcessLine().getProcesses()) {
				ProcessPanel processPanel = new ProcessPanel(intermediateProduct, process);
				processPanel.addMouseListener(controller);
				processPanels.add(processPanel);
				pnlProcessOverView.add(processPanel);
			}
		}
	}
	private void updateInfo() {
		if((IntermediateProduct)lstIntermediateProducts.getSelectedValue() != null) {
			IntermediateProduct intermediateProduct = (IntermediateProduct)lstIntermediateProducts.getSelectedValue();
			updateProcessOverView(intermediateProduct);

			btnDeleteIntermediateProduct.setVisible(true);
			btnSendToNextProcess.setVisible(true);
			txfID.setText(intermediateProduct.getId());
			txfProductType.setText(intermediateProduct.getProductType().getName());
			txfQuantity.setText(intermediateProduct.getQuantity()+ ""); 
			if(intermediateProduct.getStoringSpace() != null) {
				txfCoordinates.setText("( " + intermediateProduct.getStoringSpace().getPositionX()+ ":" + intermediateProduct.getStoringSpace().getPositionY() + " )");
				txfDepot.setText(intermediateProduct.getStoringSpace().getDepot().getName());
			}
			else {
				txfDepot.setText("N/A");
				txfCoordinates.setText("N/A");
			}
		}
		else {
			btnDeleteIntermediateProduct.setVisible(false);//delete btn skal ikke vises hvis man trykker paa et tomt feld
			btnSendToNextProcess.setVisible(false);
			txfID.setText("N/A");
			txfQuantity.setText("N/A");
			txfProductType.setText("N/A");
			txfQuantity.setText("N/A");
		}
	}
	private class Controller implements ActionListener, ListSelectionListener, MouseListener, DocumentListener, WindowListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mitCreateProductType) {
				createProductTypeFrame = new CreateProductTypeFrame();
				if (createProductTypeFrame.getProductType()!=null){
					Service.getService().storeProductType(createProductTypeFrame.getProductType());
				}
			}

			else if (e.getSource() == mitCreateIntermediateProduct || e.getSource() == btnCreateIntermediateProduct) {
				createIntermediateProduct = new CreateIntermediateProductFrame();
				if (createIntermediateProduct.getIntermediateProduct()!=null){
					Service.getService().StoreIntermediateProduct(createIntermediateProduct.getIntermediateProduct());
				}
				fillLstIntermediateProducts();
			} else if (e.getSource().equals(btnDeleteIntermediateProduct)){
				IntermediateProduct selectedIntermediateProduct=(IntermediateProduct)lstIntermediateProducts.getSelectedValue();
				if (selectedIntermediateProduct!=null){
					Depot selectedIntermediateProductDepot =null;
					if (selectedIntermediateProduct.getStoringSpace()!=null){
						selectedIntermediateProductDepot = selectedIntermediateProduct.getStoringSpace().getDepot();
					}
					selectedIntermediateProduct.discardThisIntermediateProduct();
					Service.getService().StoreIntermediateProduct(selectedIntermediateProduct);
					fillLstIntermediateProducts();
					if (selectedIntermediateProductDepot!=null){
						updateDepotMap(selectedIntermediateProductDepot);
					}
					updateInfo();
				} else {
					JOptionPane.showMessageDialog(null, "Ingen mellemvare valgt", "Fejl", JOptionPane.ERROR_MESSAGE);
				}
			} else if (e.getSource().equals(btnSendToNextProcess)){
				IntermediateProduct selectedIntermediateProduct=(IntermediateProduct)lstIntermediateProducts.getSelectedValue();

				if (selectedIntermediateProduct!=null){
					Depot currentDepot =null;
					if (selectedIntermediateProductPanel!=null){
						currentDepot = selectedIntermediateProductPanel.getStoringSpace().getDepot();
					}

					if (selectedIntermediateProduct.getNextProcess()==null || selectedIntermediateProduct.getNextProcess().getClass().equals(model.SubProcess.class)){
						if (selectedIntermediateProduct.getActivProcessLog()!=null && selectedIntermediateProduct.getActivProcessLog().getProcess().getClass().equals(model.Drying.class)){
							selectedIntermediateProductPanel=null;
						}
						selectedIntermediateProduct.sendToNextProcess(null);
						Service.getService().StoreIntermediateProduct(selectedIntermediateProduct);
					} else if (selectedIntermediateProduct.getNextProcess().getClass().equals(model.Drying.class)){
						if (selectedIntermediateProductPanel==null){
							JOptionPane.showMessageDialog(null, "Vælg et lagerplads hvor mellemvaren skal lægges", "Fejl", JOptionPane.ERROR_MESSAGE);
						} else if (selectedIntermediateProductPanel.getStoringSpace().getIntermediateProduct()!=null){
							JOptionPane.showMessageDialog(null, "Der ligger allerede en mellemvare paa den valgte placering", "Fejl", JOptionPane.ERROR_MESSAGE);
						} else if (!((Drying)selectedIntermediateProduct.getNextProcess()).getDepots().contains(selectedIntermediateProductPanel.getStoringSpace().getDepot())){
							JOptionPane.showMessageDialog(null, "Mellemvaren kan ikke ligge på det valgte lager,følgende lagre er gyldige "+((Drying)selectedIntermediateProduct.getNextProcess()).getDepots(), "Fejl", JOptionPane.ERROR_MESSAGE);
						} else {						
							selectedIntermediateProduct.sendToNextProcess(selectedIntermediateProductPanel.getStoringSpace());
							Service.getService().StoreIntermediateProduct(selectedIntermediateProduct);
						}

					}
					fillLstIntermediateProducts();
					if (currentDepot!=null){
						updateDepotMap(currentDepot);
					}
					lstIntermediateProducts.setSelectedValue(selectedIntermediateProduct, true);
					updateInfo();

				} else {
					JOptionPane.showMessageDialog(null, "Ingen mellemvare valgt", "Fejl", JOptionPane.ERROR_MESSAGE);
				}
			}

			for (int i = 0; i < mitDepots.size(); i++) {
				if (e.getSource() == mitDepots.get(i)) {
					updateDepotMap(Service.getService().getAllDepots().get(i));
				}
			}
		}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getSource() == lstIntermediateProducts) {
				if (!e.getValueIsAdjusting()) {
					updateInfoFromList((IntermediateProduct)lstIntermediateProducts.getSelectedValue());
				}
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().getClass().equals(IntermediateProductPanel.class)) {
				updateInfoFromPanel((IntermediateProductPanel)e.getSource());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void changedUpdate(DocumentEvent e) {
			fillLstIntermediateProducts();

		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			fillLstIntermediateProducts();

		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			fillLstIntermediateProducts();

		}

		public void fillLstIntermediateProducts() {

			List<IntermediateProduct> allIntermediateProducts = Service.getService().getActiveIntermediateProducts();
			List<IntermediateProduct> searchedIntermediateProducts = new ArrayList<IntermediateProduct>();

			for (int i = 0; i < allIntermediateProducts.size(); i++) {
				String idLower = allIntermediateProducts.get(i).getId().toLowerCase();
				String productTypeLower = allIntermediateProducts.get(i).getProductType().getName().toLowerCase();
				if (idLower.indexOf(txfSearch.getText().toLowerCase())!=-1 || productTypeLower.indexOf(txfSearch.getText().toLowerCase())!=-1){
					searchedIntermediateProducts.add(allIntermediateProducts.get(i));
				}
			}
			lstIntermediateProducts.setListData(searchedIntermediateProducts.toArray());
		}
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void windowClosing(WindowEvent e) {
			service.Service.getService().closeDao();
			System.exit(0);
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}
	}
}