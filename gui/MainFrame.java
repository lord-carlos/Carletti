package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	private GridLayout IntermediateProductMapLayout = new GridLayout();
	private JMenuItem mitCreateIntermediateProduct;
	private JMenuItem mitCreateProductType;
	
	private CreateProductTypeFrame createProductTypeFrame;

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
			BoxLayout pnlWestLayout = new BoxLayout(pnlWest, BoxLayout.Y_AXIS);
			pnlWest.setLayout(pnlWestLayout);
			{					
				scpIntermediateProducts = new JScrollPane();
				pnlWest.add(scpIntermediateProducts);
				{
					ListModel lstIntermediateProductsModel = new DefaultComboBoxModel();
					lstIntermediateProducts = new JList();
					scpIntermediateProducts.setViewportView(lstIntermediateProducts);
					lstIntermediateProducts.setModel(lstIntermediateProductsModel);
				}

				//filler
				pnlWest.add(Box.createRigidArea(new Dimension(5,10)));
				
				btnNewIntermediateProduct = new JButton();
				btnNewIntermediateProduct.setText("Ny Mellemvare");
				pnlWest.add(btnNewIntermediateProduct);
				
				//filler
				pnlWest.add(Box.createRigidArea(new Dimension(5,10)));
				
				cbxDepot = new JComboBox();
				cbxDepot.addActionListener(controller);
				pnlWest.add(cbxDepot);
			}
		}
		{
			pnlIntermediateProductMap = new JPanel();
			getContentPane().add(pnlIntermediateProductMap, BorderLayout.CENTER);
			IntermediateProductMapLayout.setHgap(5);
			IntermediateProductMapLayout.setVgap(5);
			pnlIntermediateProductMap.setLayout(IntermediateProductMapLayout);
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
		
		createProductTypeFrame = new CreateProductTypeFrame();
		controller.fillCbxDepot();
		controller.fillLstIntermediateProducts();
	}
	
	// Denne metode kræver at arraylisten med StoringSpaces i depot er efter læse systemet
	public void setDepot(Depot depot) {
		pnlIntermediateProductMap.removeAll();
		intermediateProductPanels.clear();
		pnlIntermediateProductMap.updateUI();
		IntermediateProductMapLayout.setColumns(depot.getMaxX());
		IntermediateProductMapLayout.setRows(depot.getMaxY());
		for (StoringSpace storingSpace : depot.getStoringSpaces()) {
			IntermediateProductPanel intermediateProductPanel = new IntermediateProductPanel(storingSpace);
			intermediateProductPanels.add(intermediateProductPanel);
			pnlIntermediateProductMap.add(intermediateProductPanel);
		} 
		
	}
	private class Controller implements ActionListener, ListSelectionListener {
		
		public void fillLstIntermediateProducts() {
			lstIntermediateProducts.setListData(Service.getService().getAllIntermediateProducts().toArray());
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
				createProductTypeFrame.setVisible(true);
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