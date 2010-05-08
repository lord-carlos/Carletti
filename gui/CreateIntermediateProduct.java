package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class CreateIntermediateProduct extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblProductType;
	private JComboBox jComboBox0;
	private JLabel lblProductTypeimg;
	private JTextArea txaProcess;
	private JScrollPane scpProcess;
	private JLabel lblProcess;
	private JButton btnChancel;
	private JButton btnCreate;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public CreateIntermediateProduct() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Opret mellemvare");
		setLayout(new GroupLayout());
		add(getLblProductType(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getLblProcess(), new Constraints(new Trailing(124, 127, 127), new Leading(12, 50, 50)));
		add(getBtnChancel(), new Constraints(new Trailing(12, 232, 160, 164), new Trailing(12, 68, 139)));
		add(getBtnCreate(), new Constraints(new Bilateral(12, 262, 136), new Trailing(12, 77, 166)));
		add(getCmbProductType(), new Constraints(new Bilateral(12, 263, 31), new Leading(34, 12, 12)));
		add(getLblProductTypeImg(), new Constraints(new Bilateral(12, 263, 0), new Bilateral(65, 50, 0)));
		add(getScpProcess(), new Constraints(new Trailing(12, 232, 10, 10), new Bilateral(34, 50, 22)));
		setSize(483, 240);
	}

	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton();
			btnCreate.setText("Opret mellemvare");
		}
		return btnCreate;
	}

	private JButton getBtnChancel() {
		if (btnChancel == null) {
			btnChancel = new JButton();
			btnChancel.setText("Annuller");
		}
		return btnChancel;
	}

	private JLabel getLblProcess() {
		if (lblProcess == null) {
			lblProcess = new JLabel();
			lblProcess.setText("Process beskrivelse:");
		}
		return lblProcess;
	}

	private JScrollPane getScpProcess() {
		if (scpProcess == null) {
			scpProcess = new JScrollPane();
			scpProcess.setViewportView(getTxaProcess());
		}
		return scpProcess;
	}

	private JTextArea getTxaProcess() {
		if (txaProcess == null) {
			txaProcess = new JTextArea();
		}
		return txaProcess;
	}

	private JLabel getLblProductTypeImg() {
		if (lblProductTypeimg == null) {
			lblProductTypeimg = new JLabel();
			lblProductTypeimg.setIcon(new ImageIcon(getClass().getResource("")));
		}
		return lblProductTypeimg;
	}

	private JComboBox getCmbProductType() {
		if (jComboBox0 == null) {
			jComboBox0 = new JComboBox();
			jComboBox0.setModel(new DefaultComboBoxModel(new Object[] {}));
			jComboBox0.setDoubleBuffered(false);
			jComboBox0.setBorder(null);
		}
		return jComboBox0;
	}

	private JLabel getLblProductType() {
		if (lblProductType == null) {
			lblProductType = new JLabel();
			lblProductType.setText("Vælg produkttype:");
		}
		return lblProductType;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CreateIntermediateProduct frame = new CreateIntermediateProduct();
				frame
						.setDefaultCloseOperation(CreateIntermediateProduct.EXIT_ON_CLOSE);
				frame.setTitle("CreateIntermediateProduct");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
