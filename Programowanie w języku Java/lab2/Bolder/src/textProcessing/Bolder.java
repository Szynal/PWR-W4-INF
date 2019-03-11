package textProcessing;

import java.awt.Font;
import javax.swing.JTextArea;

/**
 * @author Pawel Szynal 226026
 */
public class Bolder {

	public JTextArea setOutput(JTextArea textArea) {

		Font font = new Font("Segoe Script", Font.BOLD, 20);
		textArea.setFont(font);

		return textArea;
	}

	@SuppressWarnings("unused")
	private void start() {

	}

	@SuppressWarnings("unused")
	private void srtop() {

	}
}
