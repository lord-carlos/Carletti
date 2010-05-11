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
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainFrame extends JFrame {

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
	private JPanel pnlProcessOverView;
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
	private TxfChangeListener TxfChangeListener = new TxfChangeListener();

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
					txfSearch.getDocument().addDocumentListener(TxfChangeListener);
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
					btnSendToNextProcess.setText("Viderbehandling");
					btnSendToNextProcess.setPreferredSize(new Dimension(130,25));
				}
				{
					btnDeleteIntermediateProduct = new JButton();
					pnlInformation.add(btnDeleteIntermediateProduct);
					btnDeleteIntermediateProduct.setText("Slet");
					btnDeleteIntermediateProduct.setPreferredSize(new Dimension(130,25));
				}
				{
					btnCreateDrying = new JButton();
					pnlInformation.add(btnCreateDrying);
					btnCreateDrying.setVisible(false);
					btnCreateDrying.setText("Opret Toering");
					btnCreateDrying.setBounds(0, 255, 141, 16);
				}
			}
			{
				pnlProcessOverView = new JPanel();
				pnlEast.add(pnlProcessOverView);
				GridLayout pnlProcessOverViewLayout = new GridLayout();
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
		
		TxfChangeListener.fillLstIntermediateProducts();
		
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
			setDepot(Service.getService().getAllDepots().get(0));
		}

	}
	// Denne metode kræver at arraylisten med StoringSpaces i depot er efter læse systemet
	public void setDepot(Depot depot) {
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
			StoringSpace storingSpace = intermediateProductPanel.getStoringSpace();
			// Tjekker om den selected storingspace indeholder en mellemvare
			txfCoordinates.setText("( " + storingSpace.getPositionX()+ ":" + storingSpace.getPositionY() + " )");
			txfDepot.setText(storingSpace.getDepot().getName());
			updateInfo(storingSpace.getIntermediateProduct());
			lstIntermediateProducts.setSelectedValue(storingSpace.getIntermediateProduct(), true);
		}
	}

	public void updateInfoFromList(IntermediateProduct intermediateProduct) {
		if(intermediateProduct.getActivProcessLog().getProcess().getClass().getName().equals("model.Drying")) {
			setDepot(intermediateProduct.getStoringSpace().getDepot());
			for (IntermediateProductPanel intermediateProductPanel : intermediateProductPanels) {
				if (intermediateProductPanel.getStoringSpace().getIntermediateProduct() == intermediateProduct) {
					updateInfoFromPanel(intermediateProductPanel);
				}
			}
		}
		else {
			btnCreateDrying.setVisible(true);
			txfCoordinates.setText("( - : - )");
			txfDepot.setText(" - ");
			updateInfo(intermediateProduct);
		}


	}

	private void updateInfo(IntermediateProduct intermediateProduct){
		if(intermediateProduct != null) {
			btnDeleteIntermediateProduct.setVisible(true);
			btnSendToNextProcess.setVisible(true);
			btnCreateDrying.setVisible(false);
			txfID.setText(intermediateProduct.getId());
			txfProductType.setText(intermediateProduct.getProductType().getName());
			txfQuantity.setText(intermediateProduct.getQuantity()+ ""); 
		}
		else {
			btnDeleteIntermediateProduct.setVisible(false);//delete btn skal ikke vises hvis man drykker paa et tomt feld
			btnSendToNextProcess.setVisible(false);
			txfID.setText("N/A");
			txfQuantity.setText("N/A");
			txfProductType.setText("N/A");
			txfQuantity.setText("N/A");
		}
	}
	private class Controller implements ActionListener, ListSelectionListener, MouseListener {

		public void updateView() {
			// TODO venstre menu info opdateres og der laves markering på panel i map

		}


		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mitCreateProductType) {
				System.out.println("menu laver produkttype");
				createProductTypeFrame = new CreateProductTypeFrame();
				Service.getService().storeProductType(createProductTypeFrame.getProductType());
			}

			else if (e.getSource() == mitCreateIntermediateProduct) {
				createIntermediateProduct = new CreateIntermediateProduct();
				Service.getService().StoreIntermediateProduct(createIntermediateProduct.getIntermediateProduct());
			}
			else if (e.getSource() == btnCreateIntermediateProduct) {
				createIntermediateProduct = new CreateIntermediateProduct();
				Service.getService().StoreIntermediateProduct(createIntermediateProduct.getIntermediateProduct());
			}
			for (int i = 0; i < mitDepots.size(); i++) {
				if (e.getSource() == mitDepots.get(i)) {
					setDepot(Service.getService().getAllDepots().get(i));
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
	}
	
	public class TxfChangeListener implements DocumentListener{

		public void changedUpdate(DocumentEvent e) {
			// text was changed
			fillLstIntermediateProducts();
		}
		public void removeUpdate(DocumentEvent e) {
			// text was deleted
			fillLstIntermediateProducts();
		}
		public void insertUpdate(DocumentEvent e) {
			// text was inserted
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