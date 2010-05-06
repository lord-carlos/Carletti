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

import service.Service;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * cdompany or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainFrame extends JFrame {
	private JMenuBar jMenuBar1;
	private JPanel pnlWest;
	private JScrollPane jScrollPane1;
	private JMenu jMenu3;
	private JMenu jMenu2;
	private JMenu jMenu1;
	private JPanel pnlIntermediateProductMap;
<<<<<<< HEAD
	private ArrayList<IntermediateProductPanel> intermediateProductPanels = new ArrayList<IntermediateProductPanel>();
	private Depot selectedDepot = null;
	private GridLayout IntermediateProductMapLayout = new GridLayout();

=======
	private ArrayList<IntermediateProductPanel> pnlIntermediateProductPanels = new ArrayList<IntermediateProductPanel>();
	private MouseAdapter mouseAdapter = null;
	
>>>>>>> b19ee23a357cd8dab799243fc2cb733c094945a8
	public MainFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Carletti v0.0");
		BorderLayout thisLayout = new BorderLayout();
		getContentPane().setLayout(thisLayout);
		this.setResizable(true);
		this.setPreferredSize(new Dimension(600,600));
		
		mouseAdapter = new MouseAdapter() {
	        public void mouseClicked(MouseEvent me){
	        	System.out.println("foo!");
	        	System.out.println(me.getSource());
	        }
		};
		
		
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
<<<<<<< HEAD
=======
			{
				for (int i = 0; i < 42; i++) {
					IntermediateProductPanel ippTemp = new IntermediateProductPanel();
					ippTemp.addMouseListener(mouseAdapter);
					pnlIntermediateProductPanels.add(ippTemp);
					pnlIntermediateProductMap.add(pnlIntermediateProductPanels.get(pnlIntermediateProductPanels.size()-1));
				}
				
			}
>>>>>>> b19ee23a357cd8dab799243fc2cb733c094945a8
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