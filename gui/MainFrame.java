package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Depot;
import model.IntermediateProduct;
import model.StoringSpace;
import service.Service;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1752000392799508369L;
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JMenuBar mnbBar;
	private JPanel pnlWest;
	private JLabel lblIntermediateProduct;
	private JTextField txfSearch;
	private JScrollPane scpIntermediateProducts;
	private JMenu mnuView;
	private JMenu mnuCreate;
	private JMenu jMenu1;
	private JPanel pnlIntermediateProductMap;
	private JList lstIntermediateProducts;
	private JButton btnCreateIntermediateProduct;
	private JMenuItem mnuViewDepot;
	private JButton btnCreateDrying;
	private JButton btnSendToNextProcess;
	private JButton btnDeleteIntermediateProduct;
	private ArrayList<IntermediateProductPanel> intermediateProductPanels = new ArrayList<IntermediateProductPanel>();
	private GridLayout intermediateProductMapLayout = new GridLayout();
	private JPanel pnlInformation;
	private JScrollPane scpProcesses;
	private JCheckBoxList cbxLstProcesses;	
	private JMenuItem mitCreateIntermediateProduct;
	private JMenuItem mitCreateProductType;
	private JTextField txfProductType;
	private JTextField txfCoordinates;
	private JLabel lblCoordinates;
	private JTextField txfDepot;
	private JLabel lblDepot;
	private JLabel lblProductType;
	private JTextField txfQuantity;
	private JLabel lblQuantity;
	private JTextField txfID;
	private JLabel lblID;
	private JLabel lblInformation;
	private JPanel pnlEast;
	private ArrayList<JMenuItem> mitDepots = new ArrayList<JMenuItem>();
	private IntermediateProductPanel intermediateProductPanelSelected = null;
	private CreateProductTypeFrame createProductTypeFrame;
	private CreateIntermediateProduct createIntermediateProduct;
	private Controller controller = new Controller();
	private UpdateTimer updateTimer;

	public MainFrame() {
		Service.getService().createTestData();


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Carletti v0.7");
		BorderLayout thisLayout = new BorderLayout();
		getContentPane().setLayout(thisLayout);
		this.setResizable(true);
		this.setPreferredSize(new Dimension(600,600));	
		{
			pnlWest = new JPanel();
			getContentPane().add(pnlWest, BorderLayout.WEST);
			pnlWest.setPreferredSize(new Dimension(140,500));
			pnlWest.setLayout(new FlowLayout());
			{	
				{
					lblIntermediateProduct = new JLabel("Mellemvarer:");
					lblIntermediateProduct.setFont(lblIntermediateProduct.getFont().deriveFont(lblIntermediateProduct.getFont().getStyle() ^ Font.BOLD));
					lblIntermediateProduct.setPreferredSize(new Dimension(130,25));
					pnlWest.add(lblIntermediateProduct);
				}
				{
					txfSearch = new JTextField();
					txfSearch.setPreferredSize(new Dimension(130,25));
					txfSearch.getDocument().addDocumentListener(controller);
					pnlWest.add(txfSearch);
				}
				{
					scpIntermediateProducts = new JScrollPane();
					pnlWest.add(scpIntermediateProducts);
					scpIntermediateProducts.setPreferredSize(new Dimension(130,300));

					{
						ListModel lstIntermediateProductsModel = new DefaultListModel();
						lstIntermediateProducts = new JList();
						scpIntermediateProducts.setViewportView(lstIntermediateProducts);
						lstIntermediateProducts.setModel(lstIntermediateProductsModel);
						lstIntermediateProducts.addListSelectionListener(controller);
					}
				}
				{
					btnCreateIntermediateProduct = new JButton();
					btnCreateIntermediateProduct.setText("Opret Mellemvare");
					btnCreateIntermediateProduct.setPreferredSize(new Dimension(130,30));
					btnCreateIntermediateProduct.addActionListener(controller);
					pnlWest.add(btnCreateIntermediateProduct);		
				}

			}
		}
		{
			pnlEast = new JPanel();
			getContentPane().add(pnlEast, BorderLayout.EAST);
			pnlEast.setPreferredSize(new Dimension(140, 700));
			pnlEast.setLayout(null);
			{
				pnlInformation = new JPanel();
				pnlEast.add(pnlInformation);
				pnlInformation.setLayout(new FlowLayout());
				pnlInformation.setBounds(0,0,140,500);
				{
					lblInformation = new JLabel();
					pnlInformation.add(lblInformation);
					lblInformation.setFont(lblInformation.getFont().deriveFont(lblInformation.getFont().getStyle() ^ Font.BOLD));
					lblInformation.setText("Information:");
					lblInformation.setPreferredSize(new Dimension(130,25));
				}
				{
					lblID = new JLabel();
					pnlInformation.add(lblID);
					lblID.setText("ID:");
					lblID.setPreferredSize(new Dimension(60,25));
				}
				{
					txfID = new JTextField();
					pnlInformation.add(txfID);
					txfID.setPreferredSize(new Dimension(70,25));
					txfID.setEditable(false);
				}
				{
					lblQuantity = new JLabel();
					pnlInformation.add(lblQuantity);
					lblQuantity.setText("Antal:");
					lblQuantity.setPreferredSize(new Dimension(60,25));
				}
				{
					txfQuantity = new JTextField();
					pnlInformation.add(txfQuantity);
					txfQuantity.setPreferredSize(new Dimension(70,25));
					txfQuantity.setEditable(false);
				}
				{
					lblProductType = new JLabel();
					pnlInformation.add(lblProductType);
					lblProductType.setText("Produkt type:");
					lblProductType.setPreferredSize(new Dimension(130,25));
				}
				{
					txfProductType = new JTextField();
					pnlInformation.add(txfProductType);
					txfProductType.setPreferredSize(new Dimension(130,25));
					txfProductType.setEditable(false);
				}
				{
					lblDepot = new JLabel();
					pnlInformation.add(lblDepot);
					lblDepot.setText("Lager:");
					lblDepot.setPreferredSize(new Dimension(60,25));
				}
				{
					txfDepot = new JTextField();
					pnlInformation.add(txfDepot);
					txfDepot.setPreferredSize(new Dimension(70,25));
					txfDepot.setEditable(false);
				}
				{
					lblCoordinates = new JLabel();
					pnlInformation.add(lblCoordinates);
					lblCoordinates.setText("Position:");
					lblCoordinates.setPreferredSize(new Dimension (60,25));
				}
				{
					txfCoordinates = new JTextField();
					pnlInformation.add(txfCoordinates);
					txfCoordinates.setPreferredSize(new Dimension(70,25));
					txfCoordinates.setEditable(false);
				}
				{
					btnSendToNextProcess = new JButton();
					pnlInformation.add(btnSendToNextProcess);
					btnSendToNextProcess.setText("Viderbehandle");
					btnSendToNextProcess.setPreferredSize(new Dimension(130,25));
					btnSendToNextProcess.addActionListener(controller);
				}
				{
					btnDeleteIntermediateProduct = new JButton();
					pnlInformation.add(btnDeleteIntermediateProduct);
					btnDeleteIntermediateProduct.setText("Kassere mellemvare");
					btnDeleteIntermediateProduct.setPreferredSize(new Dimension(130,25));
					btnDeleteIntermediateProduct.addActionListener(controller);
				}
				{
					btnCreateDrying = new JButton();
					pnlInformation.add(btnCreateDrying);
					btnCreateDrying.setVisible(false);
					btnCreateDrying.setText("Opret Toering");
				}
			}
			{
				scpProcesses = new JScrollPane();
				pnlInformation.add(scpProcesses);
				scpProcesses.setPreferredSize(new Dimension(130,100));
				{
					cbxLstProcesses = new JCheckBoxList();
					scpProcesses.setViewportView(cbxLstProcesses);
				}
			}
		}
		{
			pnlIntermediateProductMap = new JPanel();
			getContentPane().add(pnlIntermediateProductMap, BorderLayout.CENTER);
			intermediateProductMapLayout.setHgap(5);
			intermediateProductMapLayout.setVgap(5);
			pnlIntermediateProductMap.setLayout(intermediateProductMapLayout);
			pnlIntermediateProductMap.setBorder(BorderFactory.createEtchedBorder());
			pnlIntermediateProductMap.setPreferredSize(new java.awt.Dimension(452, 539));
		}
		{
			mnbBar = new JMenuBar();
			setJMenuBar(mnbBar);
			{
				jMenu1 = new JMenu();
				mnbBar.add(jMenu1);
				jMenu1.setText("jMenu1");
			}
			{
				mnuCreate = new JMenu();
				mnbBar.add(mnuCreate);
				mnuCreate.setText("Opret");
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
				{
					mnuViewDepot = new JMenu();
					mnuView.add(mnuViewDepot);
					mnuViewDepot.setText("Vis lager");
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
		mnuViewDepot.removeAll();
		mitDepots.clear();
		mnuViewDepot.updateUI();
		for (Depot depot : Service.getService().getAllDepots()) {
			JMenuItem mitDepot = new JMenuItem();
			mitDepot.setText(depot.getName());
			mitDepot.addActionListener(controller);
			mitDepots.add(mitDepot);
			mnuViewDepot.add(mitDepot);
		}
		if(mitDepots.size()>0) {
			updateDepotMap(Service.getService().getAllDepots().get(0));
		}

	}
	// Denne metode krÃ¦ver at arraylisten med StoringSpaces i depot er efter lÃ¦se systemet
	public void updateDepotMap(Depot depot) {
		pnlIntermediateProductMap.removeAll();
		intermediateProductPanels.clear();
		pnlIntermediateProductMap.updateUI();
		intermediateProductMapLayout.setColumns(depot.getMaxX());
		intermediateProductMapLayout.setRows(depot.getMaxY());

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
		if (intermediateProductPanelSelected != intermediateProductPanel) {
			if (intermediateProductPanelSelected != null) { //unselecter den gamle storingspace
				intermediateProductPanelSelected.setSelected(false); 
			}
			intermediateProductPanel.setSelected(true);
			intermediateProductPanelSelected = intermediateProductPanel;

			updateInfo(intermediateProductPanel.getStoringSpace().getIntermediateProduct());
			lstIntermediateProducts.setSelectedValue(intermediateProductPanel.getStoringSpace().getIntermediateProduct(), true);
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
					btnCreateDrying.setVisible(true);
					updateInfo(intermediateProduct);
				}
			}
			else {
				updateInfo(intermediateProduct);
			}
		}
	}

	private void updateInfo(IntermediateProduct intermediateProduct){
		if(intermediateProduct != null) {
			cbxLstProcesses.setListData(intermediateProduct.getProcessLogs().toArray());
			btnDeleteIntermediateProduct.setVisible(true);
			btnSendToNextProcess.setVisible(true);
			btnCreateDrying.setVisible(false);
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
	}
	private class Controller implements ActionListener, ListSelectionListener, MouseListener, DocumentListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mitCreateProductType) {
				createProductTypeFrame = new CreateProductTypeFrame();
				if (createProductTypeFrame.getProductType()!=null){
					Service.getService().storeProductType(createProductTypeFrame.getProductType());
				}
			}

			else if (e.getSource() == mitCreateIntermediateProduct || e.getSource() == btnCreateIntermediateProduct) {
				createIntermediateProduct = new CreateIntermediateProduct();
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
					fillLstIntermediateProducts();
					if (selectedIntermediateProductDepot!=null){
						updateDepotMap(selectedIntermediateProductDepot);
					}
					updateInfo(null);
				} else {
					JOptionPane.showMessageDialog(null, "Ingen mellemvare valgt", "Fejl", JOptionPane.ERROR_MESSAGE);
				}
			} else if (e.getSource().equals(btnSendToNextProcess)){
				IntermediateProduct selectedIntermediateProduct=(IntermediateProduct)lstIntermediateProducts.getSelectedValue();

				if (selectedIntermediateProduct!=null){
					Depot currentDepot =null;
					if (intermediateProductPanelSelected!=null){
						currentDepot = intermediateProductPanelSelected.getStoringSpace().getDepot();
					}

					if (selectedIntermediateProduct.getNextProcess()==null){
						if (selectedIntermediateProduct.getActivProcessLog().getProcess().getClass().equals(model.Drying.class)){
							intermediateProductPanelSelected=null;
						}
						selectedIntermediateProduct.sendToNextProcess(null);
					} else if (selectedIntermediateProduct.getNextProcess().getClass().equals(model.SubProcess.class)){
						if (selectedIntermediateProduct.getActivProcessLog().getProcess().getClass().equals(model.Drying.class)){
							intermediateProductPanelSelected=null;
						}
						selectedIntermediateProduct.sendToNextProcess(null);
					} else if (selectedIntermediateProduct.getNextProcess().getClass().equals(model.Drying.class)){
						if (intermediateProductPanelSelected==null){
							JOptionPane.showMessageDialog(null, "Vælg et lagerplads hvor mellemvaren skal lægges", "Fejl", JOptionPane.ERROR_MESSAGE);
						} else if (intermediateProductPanelSelected.getStoringSpace().getIntermediateProduct()!=null){
							JOptionPane.showMessageDialog(null, "Der ligger allerede en mellemvare på den valgte placering", "Fejl", JOptionPane.ERROR_MESSAGE);
						} else if (!((model.Drying)selectedIntermediateProduct.getNextProcess()).getDepots().contains(intermediateProductPanelSelected.getStoringSpace().getDepot())){
							JOptionPane.showMessageDialog(null, "Mellemvaren kan ikke ligge på det valgte lager", "Fejl", JOptionPane.ERROR_MESSAGE);
						} else {						
							selectedIntermediateProduct.sendToNextProcess(intermediateProductPanelSelected.getStoringSpace());
						}

					}
					fillLstIntermediateProducts();
					if (currentDepot!=null){
						updateDepotMap(currentDepot);
					}
					updateInfo(selectedIntermediateProduct);
					lstIntermediateProducts.setSelectedValue(selectedIntermediateProduct, true);
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
			updateInfoFromPanel((IntermediateProductPanel)e.getSource());
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
	}
}