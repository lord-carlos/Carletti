package gui;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainFrameApp {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			System.out.println("Error setting look and feel: " + e.getMessage());
		}

		MainFrame frame = new MainFrame();
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH); //Maximer framet hved starten

		frame.setVisible(true);
	}
}
