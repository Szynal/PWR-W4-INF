package app;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;

public class LoadText {

	private JFileChooser fileChooser = new JFileChooser();
	private StringBuilder stringBuilder = new StringBuilder();
	private BufferedReader bufferedReader = null;

	public void TextLoading() throws Exception {
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			java.io.File file = fileChooser.getSelectedFile();
			bufferedReader = new BufferedReader(new FileReader(file));

			String line = null;
			String lineSeparator = System.getProperty("line.separator");

			try {
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
					stringBuilder.append(lineSeparator);
				}

				stringBuilder.toString();
			} finally {
				bufferedReader.close();
			}

		} else {

			stringBuilder.append("No file was selected");
		}
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public StringBuilder getStringBuilder() {
		return stringBuilder;
	}

	public void setStringBuilder(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}
}
