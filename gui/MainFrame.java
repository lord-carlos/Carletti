package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JMenuBar mnbBar;
	private JPanel pnlWest;
	private JLabel lblIntermediateProduct;
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
	private JMenuItem mitCreateIntermediateProduct;
	private JMenuItem mitCreateProductType;
	private JTextField productTypeTextFiel;
	private JTextField positionLabelTextField;
	private JLabel positionLabel;
	private JTextField depotLabelTextField;
	private JLabel depotLabel;
	private JLabel productTypeLabel;
	private JTextField quantityTextField;
	private JLabel quantityLabel;
	private JTextField idTextFieldShower;
	private JLabel idLabel;
	private JLabel titleLabel;
	private JPanel pnlEast;
	private ArrayList<JMenuItem> mitDepots = new ArrayList<JMenuItem>();
	private IntermediateProductPanel intermediateProductPanelSelected = null;
	private CreateProductTypeFrame createProductTypeFrame;
	private CreateIntermediateProduct createIntermediateProduct;
	private Controller controller = new Controller();

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
			FlowLayout pnlWestLayout = new FlowLayout();
			pnlWest.setPreferredSize(new Dimension(140,400));
			pnlWestLayout.setAlignment(0);
			pnlWest.setLayout(pnlWestLayout);
			{	
				{
					lblIntermediateProduct = new JLabel("Mellemvarer:");
					pnlWest.add(lblIntermediateProduct);
				}
				{
					scpIntermediateProducts = new JScrollPane();
					pnlWest.add(scpIntermediateProducts);
					scpIntermediateProducts.setPreferredSize(new Dimension(130,300));

					{
						ListModel lstIntermediateProductsModel = new DefaultComboBoxModel();
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
			pnlEast.setPreferredSize(new java.awt.Dimension(141, 545));
			pnlEast.setLayout(null);
			{
				pnlInformation = new JPanel();
				GridLayout pnlInformationLayout = new GridLayout(20, 2);
				pnlInformationLayout.setHgap(5);
				pnlInformationLayout.setVgap(5);
				pnlInformationLayout.setRows(20);
				pnlInformationLayout.setColumns(2);
				pnlEast.add(pnlInformation);
				pnlInformation.setLayout(pnlInformationLayout);
				pnlInformation.setBounds(0, 0, 141, 438);
				{
					titleLabel = new JLabel();
					pnlInformation.add(titleLabel);
					//				Font font = titleLabel.getFont();
					//				titleLabel.setFont(font.deriveFont(font.getStyle() ^ Font.BOLD));
					titleLabel.setText("Information:");
					titleLabel.setBounds(0, 8, 141, 16);
				}
				{
					idLabel = new JLabel();
					pnlInformation.add(idLabel);
					idLabel.setText("ID:");
					idLabel.setBounds(0, 27, 141, 16);
				}
				{
					idTextFieldShower = new JTextField();
					pnlInformation.add(idTextFieldShower);
					idTextFieldShower.setText("<null>");
					idTextFieldShower.setBounds(0, 46, 141, 16);
					idTextFieldShower.setEditable(false);
				}
				{
					quantityLabel = new JLabel();
					pnlInformation.add(quantityLabel);
					quantityLabel.setText("Antal:");
					quantityLabel.setBounds(0, 65, 141, 16);
				}
				{
					quantityTextField = new JTextField();
					pnlInformation.add(quantityTextField);
					quantityTextField.setText("<null>");
					quantityTextField.setBounds(0, 84, 141, 16);
					quantityTextField.setEditable(false);
				}
				{
					productTypeLabel = new JLabel();
					pnlInformation.add(productTypeLabel);
					productTypeLabel.setText("Produkt type:");
					productTypeLabel.setBounds(0, 103, 141, 16);
				}
				{
					productTypeTextFiel = new JTextField();
					pnlInformation.add(productTypeTextFiel);
					productTypeTextFiel.setText("<null>");
					productTypeTextFiel.setBounds(0, 122, 141, 16);
					productTypeTextFiel.setEditable(false);
				}
				{
					depotLabel = new JLabel();
					pnlInformation.add(depotLabel);
					depotLabel.setText("Lager:");
					depotLabel.setBounds(0, 141, 141, 16);
				}
				{
					depotLabelTextField = new JTextField();
					pnlInformation.add(depotLabelTextField);
					depotLabelTextField.setText("<null>");
					depotLabelTextField.setBounds(0, 160, 141, 16);
					depotLabelTextField.setEditable(false);
				}
				{
					positionLabel = new JLabel();
					pnlInformation.add(positionLabel);
					positionLabel.setText("Position:");
					positionLabel.setBounds(0, 179, 141, 16);
				}
				{
					positionLabelTextField = new JTextField();
					pnlInformation.add(positionLabelTextField);
					positionLabelTextField.setBounds(0, 198, 141, 16);
					positionLabelTextField.setText("<null>");
					positionLabelTextField.setEditable(false);
				}
				{
					btnSendToNextProcess = new JButton();
					pnlInformation.add(btnSendToNextProcess);
					btnSendToNextProcess.setText("Viderbehandling");
					btnSendToNextProcess.setBounds(0, 236, 141, 16);
				}
				{
					btnDeleteIntermediateProduct = new JButton();
					pnlInformation.add(btnDeleteIntermediateProduct);
					btnDeleteIntermediateProduct.setText("Slet");
					btnDeleteIntermediateProduct.setBounds(0, 217, 141, 16);
				}
				{
					btnCreateDrying = new JButton();
					pnlInformation.add(btnCreateDrying);
					btnCreateDrying.setVisible(false);
					btnCreateDrying.setText("Opret Toering");
					btnCreateDrying.setBounds(0, 255, 141, 16);
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
		if (intermediateProductPanelSelected != null) { //unselecter den gamle storingspace
			intermediateProductPanelSelected.setSelected(false); 
		}
		intermediateProductPanel.setSelected(true);
		intermediateProductPanelSelected = intermediateProductPanel;
		StoringSpace storingSpace = intermediateProductPanel.getStoringSpace();
		// Tjekker om den selected storingspace indeholder en mellemvare
		positionLabelTextField.setText("( " + storingSpace.getPositionX()
				+ ":" + storingSpace.getPositionY() + " )");
		depotLabelTextField.setText(storingSpace.getDepot().getName());
		if(storingSpace.getIntermediateProduct() != null) {
			updateInfo(storingSpace.getIntermediateProduct());
		} else {
			btnDeleteIntermediateProduct.setVisible(false);//delete btn skal ikke vises hvis man drykker paa et tomt feld
			btnSendToNextProcess.setVisible(false);
//			btnCreateDrying.setVisible(true);
			idTextFieldShower.setText("<tomt>");
			productTypeTextFiel.setText(" - ");
			quantityTextField.setText("0");
		}
	}
	
	public void updateInfoFromList(IntermediateProduct intermediateProduct) {
		btnCreateDrying.setVisible(true);
		positionLabelTextField.setText("( - : - )");
		depotLabelTextField.setText(" - ");
		updateInfo(intermediateProduct);
	}
	
	private void updateInfo(IntermediateProduct intermediateProduct){
		btnDeleteIntermediateProduct.setVisible(true);
		btnSendToNextProcess.setVisible(true);
		btnCreateDrying.setVisible(false);
		idTextFieldShower.setText(intermediateProduct.getId());
		productTypeTextFiel.setText(intermediateProduct.getProductType()
				.getName());
		quantityTextField.setText(intermediateProduct.getQuantity()
				+ "");
	}
	private class Controller implements ActionListener, ListSelectionListener, MouseListener {

		public void fillLstIntermediateProducts() {
			lstIntermediateProducts.setListData(Service.getService().getAllIntermediateProducts().toArray());
		}

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
					this.updateView();
				}
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			updateInfo((IntermediateProductPanel)e.getSource());			
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
}