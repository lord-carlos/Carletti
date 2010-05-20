package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

@SuppressWarnings("serial")
/**
 * vores jdialog som gemmer og loader filer
 * @author M. C. Høj
 */
public class EditorFileHandler extends JFileChooser{

	public static int LOAD_FUNCTION=0;
	public static int SAVE_FUNCTION=1;
	private int isOkPressed;

	public boolean getIsOkPressed() {
		return isOkPressed == JFileChooser.APPROVE_OPTION;
	}

	/**
	 * 
	 * @param function save or load function
	 * @param f file to choose
	 * @throws RuntimeException
	 */
	public EditorFileHandler(int function, File f) throws RuntimeException{
		this.removeChoosableFileFilter(this.getChoosableFileFilters()[0]);
		this.setFileFilter(new EditorFileFilter());
		if (function == LOAD_FUNCTION){
			this.setCurrentDirectory(f);
			isOkPressed = this.showOpenDialog(new JFrame());	
		} else if (function == SAVE_FUNCTION){
			this.setCurrentDirectory(f);
			this.setSelectedFile(f);
			isOkPressed = this.showSaveDialog(new JFrame());
			
		} else {
			throw new RuntimeException("Function must be save og load");
		}
	}

}
