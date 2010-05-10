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
	private JLabel productTypeLabelShower;
	private JLabel positionLabelShower;
	private JLabel positionLabel;
	private JLabel depotLabelShower;
	private JLabel depotLabel;
	private JLabel productTypeLabel;
	private JLabel quantityLabelShower;
	private JLabel quantityLabel;
	private JLabel idLabelShower;
	private JLabel idLabel;
	private JLabel titleLabel;
	private JPanel pnlEast;
	private IntermediateProductPanel intermediateProductPanelSelected = null;

	private Controller controller = new Controller();

	public MainFrame() {
		//Test Data
		Service.getService().createTestData();
		
		
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
			getContentPane().add(pnlEast, BorderLayout.EAST);
			pnlEast.setPreferredSize(new java.awt.Dimension(141, 545));
			{
				titleLabel = new JLabel();
//				Font font = titleLabel.getFont();
//				titleLabel.setFont(font.deriveFont(font.getStyle() ^ Font.BOLD));
				pnlEast.add(titleLabel);
				titleLabel.setText("Information:");
			}
			{
				idLabel = new JLabel();
				pnlEast.add(idLabel);
				idLabel.setText("ID:");
				idLabel.setPreferredSize(new java.awt.Dimension(20, 10));
			}
			{
				idLabelShower = new JLabel();
				pnlEast.add(idLabelShower);
				idLabelShower.setText("<null>");
			}
			{
				quantityLabel = new JLabel();
				pnlEast.add(quantityLabel);
				quantityLabel.setText("Antal:");
			}
			{
				quantityLabelShower = new JLabel();
				pnlEast.add(quantityLabelShower);
				quantityLabelShower.setText("<null>");
			}
			{
				productTypeLabel = new JLabel();
				pnlEast.add(productTypeLabel);
				productTypeLabel.setText("Produkt type:");
			}
			{
				productTypeLabelShower = new JLabel();
				pnlEast.add(productTypeLabelShower);
				productTypeLabelShower.setText("<null>");
			}
			{
				depotLabel = new JLabel();
				pnlEast.add(depotLabel);
				depotLabel.setText("Lager:");
			}
			{
				depotLabelShower = new JLabel();
				pnlEast.add(depotLabelShower);
				depotLabelShower.setText("<null>");
			}
			{
				positionLabel = new JLabel();
				pnlEast.add(positionLabel);
				positionLabel.setText("Position:");
			}
			{
				positionLabelShower = new JLabel();
				pnlEast.add(positionLabelShower);
				positionLabelShower.setText("<null>");
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
			intermediateProductPanel.addMouseListener(controller);
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
			productTypeLabelShower.setText(storingSpace
					.getIntermediateProduct().getProductType().getName());
			positionLabelShower.setText("( " + storingSpace.getPositionX()
					+ ":" + storingSpace.getPositionY() + " )");
			quantityLabelShower.setText(storingSpace.getIntermediateProduct()
					.getQuantity()
					+ "");
			depotLabelShower.setText(storingSpace.getDepot().getName());
		} else {
			idLabelShower.setText("<tomt>");
			productTypeLabelShower.setText(" - ");
			positionLabelShower.setText("( " + storingSpace.getPositionX()
					+ ":" + storingSpace.getPositionY() + " )");
			quantityLabelShower.setText("0");
			depotLabelShower.setText(storingSpace.getDepot().getName());
		}

	}
	private class Controller implements ActionListener, ListSelectionListener, MouseListener {
		
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
				System.out.println("hej");
			}
			
			else if (e.getSource() == mitCreateIntermediateProduct) {
				
			}
			else if (e.getSource() == btnNewIntermediateProduct) {
				
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