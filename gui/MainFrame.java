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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Depot;
import model.StoringSpace;
import service.Service;

public class MainFrame extends JFrame {
	private JMenuBar jMenuBar1;
	private JPanel pnlWest;
	private JScrollPane scpIntermediateProducts;
	private JMenu jMenu3;
	private JMenu jMenu2;
	private JMenu jMenu1;
	private JPanel pnlIntermediateProductMap;
	private JComboBox cbxDepot;
	private JList lstIntermediateProducts;
	private JButton btnNewIntermediateProduct;
	private ArrayList<IntermediateProductPanel> intermediateProductPanels = new ArrayList<IntermediateProductPanel>();
	private GridLayout IntermediateProductMapLayout = new GridLayout();

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
			jMenuBar1 = new JMenuBar();
			setJMenuBar(jMenuBar1);
			{
				jMenu1 = new JMenu();
				jMenuBar1.add(jMenu1);
				jMenu1.setText("jMenu1");
			}
			{
				jMenu2 = new JMenu();
				jMenuBar1.add(jMenu2);
				jMenu2.setText("Vis");
			}
			{
				jMenu3 = new JMenu();
				jMenuBar1.add(jMenu3);
				jMenu3.setText("jMenu3");
			}
		}
		pack();
		controller.fillCbxDepot();
		controller.fillLstIntermediateProducts();
	}
	
	// Denne metode kræver at arraylisten med StoringSpaces i depot er efter læse systemet
	public void setDepot(Depot depot) {
		System.out.println("SetDepot method was called with "+depot);
		intermediateProductPanels.clear();
		pnlIntermediateProductMap.removeAll();
		IntermediateProductMapLayout.setColumns(depot.getMaxX());
		IntermediateProductMapLayout.setRows(depot.getMaxY());
		System.out.println(depot.getStoringSpaces());
		for (StoringSpace storingSpace : depot.getStoringSpaces()) {
			IntermediateProductPanel intermediateProductPanel = new IntermediateProductPanel(storingSpace);
			intermediateProductPanels.add(intermediateProductPanel);
			pnlIntermediateProductMap.add(intermediateProductPanel);
			intermediateProductPanel.setVisible(true);
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
			if(e.getSource() == cbxDepot) {
				setDepot((Depot) cbxDepot.getSelectedItem());
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