package pdfmerger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		File[] files;
		PDFMergerUtility util = new PDFMergerUtility();
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Pdf file(.pdf)", "pdf");
		chooser.setFileFilter(filter);
		chooser.setMultiSelectionEnabled(true);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			files = chooser.getSelectedFiles();
			for (File file : files) {
				try {
					util.addSource(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					util.setDestinationFileName("test.pdf");
					try {
						util.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}

	}
}
