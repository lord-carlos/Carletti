package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import model.Depot;
import model.StoringSpace;

public class MainFrame extends JFrame {
	private JMenuBar jMenuBar1;
	private JPanel pnlWest;
	private JScrollPane jScrollPane1;
	private JMenu jMenu3;
	private JMenu jMenu2;
	private JMenu jMenu1;
	private JPanel pnlIntermediateProductMap;

	private ArrayList<IntermediateProductPanel> intermediateProductPanels = new ArrayList<IntermediateProductPanel>();
	private GridLayout IntermediateProductMapLayout = new GridLayout();



	public MainFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Carletti v0.0");
		BorderLayout thisLayout = new BorderLayout();
		getContentPane().setLayout(thisLayout);
		this.setResizable(true);
		this.setPreferredSize(new Dimension(600,600));	
		{
			pnlWest = new JPanel();
			getContentPane().add(pnlWest, BorderLayout.WEST);
			pnlWest.setPreferredSize(new Dimension(99, 253));
			pnlWest.setLayout(null);
			{
				jScrollPane1 = new JScrollPane();
				pnlWest.add(jScrollPane1);
				jScrollPane1.setBounds(12, 12, 69, 89);
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
	}

	public void setDepot(Depot depot) {
		intermediateProductPanels.clear();
		pnlIntermediateProductMap.removeAll();
		IntermediateProductMapLayout.setColumns(depot.getMaxX());
		IntermediateProductMapLayout.setRows(depot.getMaxY());
		for (StoringSpace storingSpace : depot.getStoringSpaces()) {
			IntermediateProductPanel intermediateProductPanel = new IntermediateProductPanel(storingSpace);
			intermediateProductPanels.add(intermediateProductPanel);
			pnlIntermediateProductMap.add(intermediateProductPanel);
		} 

	}
}