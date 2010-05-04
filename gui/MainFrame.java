package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
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
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JMenu jMenu3;
	private JMenu jMenu2;
	private JMenu jMenu1;
	private Map pnlMap;

	public MainFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Carletti v0.0");
		BorderLayout thisLayout = new BorderLayout();
		getContentPane().setLayout(thisLayout);
		this.setResizable(true);
		this.setPreferredSize(new Dimension(400,400));
		{
			jPanel1 = new JPanel();
			getContentPane().add(jPanel1, BorderLayout.WEST);
			jPanel1.setPreferredSize(new java.awt.Dimension(99, 253));
			jPanel1.setLayout(null);
			{
				jScrollPane1 = new JScrollPane();
				jPanel1.add(jScrollPane1);
				jScrollPane1.setBounds(12, 12, 69, 89);
			}
		}
		{
			pnlMap = new Map();
			this.add(pnlMap);
//			pnlTegning.setLocation(5, 5);
//			pnlTegning.setSize(300,260);
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