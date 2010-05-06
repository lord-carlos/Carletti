package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

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
	private ArrayList<IntermediateProductPanel> pnlIntermediateProductPanels = new ArrayList<IntermediateProductPanel>();
	
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
			pnlIntermediateProductMap.setPreferredSize(new Dimension(200,200));
			GridLayout IntermediateProductMapLayout = new GridLayout(8, 8, 5, 5);
			IntermediateProductMapLayout.setColumns(8);
			pnlIntermediateProductMap.setLayout(IntermediateProductMapLayout);
			{
				for (int i = 0; i < 64; i++) {
					pnlIntermediateProductPanels.add(new IntermediateProductPanel());
					pnlIntermediateProductMap.add(pnlIntermediateProductPanels.get(pnlIntermediateProductPanels.size()-1));
				}
				
			}
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
}