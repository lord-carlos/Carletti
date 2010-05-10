package gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * gør os i stand til kun at se .java filer
 * @author M. C. Høj
 *
 */
public class EditorFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		return f.getName().toLowerCase().endsWith(".jpg")||f.getName().toLowerCase().endsWith(".gif")||f.getName().toLowerCase().endsWith(".jpeg")||f.getName().toLowerCase().endsWith(".png") || f.isDirectory();
	}

	@Override
	public String getDescription() {
		return "Billed filer";
	}

}
