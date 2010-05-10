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
	private JScrollPane scpIntermediateProducts;
	private JMenu jMenu3;
	private JMenu mnuView;
	private JMenu jMenu1;
	private JPanel pnlIntermediateProductMap;
	private JComboBox cbxDepot;
	private JList lstIntermediateProducts;
	private JButton btnNewIntermediateProduct;
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
	private JTextField idLabelShower;
	private JLabel idLabel;
	private JLabel titleLabel;
	private JPanel pnlEast;
	private MouseAdapter mouseAdapter = null;
	private IntermediateProductPanel intermediateProductPanelSelected = null;

	//private CreateProductTypeFrame createProductTypeFrame;

	private Controller controller = new Controller();

	public MainFrame() {
		//Test Data
		Service.getService().createTestData();
		Service.getService().getFinishedIntermediateProducts();
		
		mouseAdapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				updateInfo((IntermediateProductPanel) me.getSource());
			}
		};
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Carletti v0.1");
		BorderLayout thisLayout = new BorderLayout();
		getContentPane().setLayout(thisLayout);
		this.setResizable(true);
		this.setPreferredSize(new Dimension(600,600));	
		{
			pnlWest = new JPanel();
			getContentPane().add(pnlWest, BorderLayout.WEST);
			FlowLayout pnlWestLayout = new FlowLayout();
			pnlWest.setPreferredSize(new Dimension(110,400));
			pnlWestLayout.setAlignment(0);
			pnlWest.setLayout(pnlWestLayout);
			{	
				cbxDepot = new JComboBox();
				cbxDepot.addActionListener(controller);
				pnlWest.add(cbxDepot);
				
				
				
				scpIntermediateProducts = new JScrollPane();
				pnlWest.add(scpIntermediateProducts);
				{
					ListModel lstIntermediateProductsModel = new DefaultComboBoxModel();
					lstIntermediateProducts = new JList();
					scpIntermediateProducts.setViewportView(lstIntermediateProducts);
					lstIntermediateProducts.setModel(lstIntermediateProductsModel);
				}

				
				
				btnNewIntermediateProduct = new JButton();
				btnNewIntermediateProduct.setText("Ny Mellemvare");
				pnlWest.add(btnNewIntermediateProduct);				
				
			}
		}
		{
			pnlEast = new JPanel();
			GridLayout pnlEastLayout = new GridLayout(28, 2);
			pnlEastLayout.setHgap(5);
			pnlEastLayout.setVgap(3);
			pnlEastLayout.setColumns(1);
			getContentPane().add(pnlEast, BorderLayout.EAST);
			pnlEast.setPreferredSize(new java.awt.Dimension(141, 545));
			pnlEast.setLayout(pnlEastLayout);
			{
				titleLabel = new JLabel();
//				Font font = titleLabel.getFont();
//				titleLabel.setFont(font.deriveFont(font.getStyle() ^ Font.BOLD));
				pnlEast.add(titleLabel);
				titleLabel.setText("Information:");
				titleLabel.setBounds(20, 5, 75, 14);
			}
			{
				idLabel = new JLabel();
				pnlEast.add(idLabel);
				idLabel.setText("ID:");
				idLabel.setPreferredSize(new java.awt.Dimension(20, 10));
				idLabel.setBounds(100, 7, 20, 10);
			}
			{
				idLabelShower = new JTextField();
				pnlEast.add(idLabelShower);
				idLabelShower.setText("<null>");
				idLabelShower.setBounds(26, 24, 48, 21);
			}
			{
				quantityLabel = new JLabel();
				pnlEast.add(quantityLabel);
				quantityLabel.setText("Antal:");
				quantityLabel.setBounds(79, 27, 36, 14);
			}
			{
				quantityTextField = new JTextField();
				pnlEast.add(quantityTextField);
				quantityTextField.setText("<null>");
				quantityTextField.setBounds(5, 74, 48, 21);
			}
			{
				productTypeLabel = new JLabel();
				pnlEast.add(productTypeLabel);
				productTypeLabel.setText("Produkt type:");
				productTypeLabel.setBounds(37, 102, 84, 14);
			}
			{
				productTypeTextFiel = new JTextField();
				pnlEast.add(productTypeTextFiel);
				productTypeTextFiel.setText("<null>");
				productTypeTextFiel.setBounds(24, 95, 48, 21);
			}
			{
				depotLabel = new JLabel();
				pnlEast.add(depotLabel);
				depotLabel.setText("Lager:");
				depotLabel.setBounds(77, 98, 39, 14);
			}
			{
				depotLabelTextField = new JTextField();
				pnlEast.add(depotLabelTextField);
				depotLabelTextField.setText("<null>");
				depotLabelTextField.setBounds(17, 121, 48, 21);
			}
			{
				positionLabel = new JLabel();
				pnlEast.add(positionLabel);
				positionLabel.setText("Position:");
				positionLabel.setBounds(70, 124, 54, 14);
			}
			{
				positionLabelTextField = new JTextField();
				pnlEast.add(positionLabelTextField);
				positionLabelTextField.setBounds(-31, 147, 203, 21);
				positionLabelTextField.setText("<null>");
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
				mnuView = new JMenu();
				mnbBar.add(mnuView);
				mnuView.setText("Vis");
				{
					mitCreateProductType = new JMenuItem();
					mnuView.add(mitCreateProductType);
					mitCreateProductType.setText("Opret Produkttype");
					mitCreateProductType.addActionListener(controller);
				}
				{
					mitCreateIntermediateProduct = new JMenuItem();
					mnuView.add(mitCreateIntermediateProduct);
					mitCreateIntermediateProduct.setText("Opret Mellemvare");
					mitCreateIntermediateProduct.addActionListener(controller);
				}
			}
			{
				jMenu3 = new JMenu();
				mnbBar.add(jMenu3);
				jMenu3.setText("jMenu3");
			}
		}
		pack();
		
		//createProductTypeFrame = new CreateProductTypeFrame();
		controller.fillCbxDepot();
		controller.fillLstIntermediateProducts();
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
			intermediateProductPanel.addMouseListener(mouseAdapter);
			intermediateProductPanels.add(intermediateProductPanel);
			pnlIntermediateProductMap.add(intermediateProductPanel);
		} 
		
	}
	
	/**
	 * Denne methode bliver udfoert ver gang man klicker paa en storingspace med musen i guien
	 * @param intermediateProductPanel
	 */
	public void updateInfo(IntermediateProductPanel intermediateProductPanel) {
		if (intermediateProductPanelSelected != null) { //unselecter den gamle storingspace
			intermediateProductPanelSelected.setSelected(false); 
		}
		intermediateProductPanel.setSelected(true);
		intermediateProductPanelSelected = intermediateProductPanel;
		StoringSpace storingSpace = intermediateProductPanel.getStoringSpace();
		// Tjekker om den selected storingspace indeholder en mellemvare
		if(storingSpace.getIntermediateProduct() != null) {
			idLabelShower
					.setText(storingSpace.getIntermediateProduct().getId());
			productTypeTextFiel.setText(storingSpace
					.getIntermediateProduct().getProductType().getName());
			positionLabelTextField.setText("( " + storingSpace.getPositionX()
					+ ":" + storingSpace.getPositionY() + " )");
			quantityTextField.setText(storingSpace.getIntermediateProduct()
					.getQuantity()
					+ "");
			depotLabelTextField.setText(storingSpace.getDepot().getName());
		} else {
			idLabelShower.setText("<tomt>");
			productTypeTextFiel.setText(" - ");
			positionLabelTextField.setText("( " + storingSpace.getPositionX()
					+ ":" + storingSpace.getPositionY() + " )");
			quantityTextField.setText("0");
			depotLabelTextField.setText(storingSpace.getDepot().getName());
		}

	}
	private class Controller implements ActionListener, ListSelectionListener {
		
		public void fillLstIntermediateProducts() {
			Depot selectedDepot = (Depot) cbxDepot.getSelectedItem();
			ArrayList<IntermediateProduct> intermediateProductList = new ArrayList<IntermediateProduct>();
			
			for (StoringSpace storingSpace : selectedDepot.getStoringSpaces()) {
				if(storingSpace.getIntermediateProduct()!=null) {
					intermediateProductList.add(storingSpace.getIntermediateProduct());
				}
				
			}
			
			lstIntermediateProducts.setListData(intermediateProductList.toArray());
		}
		
		public void fillCbxDepot() {
			DefaultComboBoxModel cbxDepotModel = new DefaultComboBoxModel(Service.getService().getAllDepots().toArray());
			cbxDepot.setModel(cbxDepotModel);
			if(cbxDepot.getItemCount()>0)
				cbxDepot.setSelectedIndex(0);
		}
		
		
		public void updateView() {
			// TODO venstre menu info opdateres og der laves markering på panel i map
			
		}
		
				
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cbxDepot) {
				setDepot((Depot) cbxDepot.getSelectedItem());
				System.out.println(cbxDepot.getSelectedItem());
			}
			
			else if (e.getSource() == mitCreateProductType) {
				//createProductTypeFrame.setVisible(true);
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
	}

}