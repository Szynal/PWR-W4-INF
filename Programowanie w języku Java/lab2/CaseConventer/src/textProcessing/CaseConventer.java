package textProcessing;

import javax.swing.JTextArea;

/**
 * @author Pawel Szynal 226026
 */
public class CaseConventer {

	public JTextArea setOutput(JTextArea textArea) {

		String outputString = textArea.getText();
		textArea.setText(outputString.toUpperCase());

		return textArea;
	}

	@SuppressWarnings("unused")
	private void start() {

	}

	@SuppressWarnings("unused")
	private void srtop() {

	}
}
