package textProcessing;

import java.awt.Font;
import java.util.Map;

import javax.swing.JTextArea;
import java.awt.font.TextAttribute;

/**
 * @author Pawel Szynal 226026
 */
public class FontReplacement {

	public JTextArea setOutput(JTextArea textArea) {

		textArea.setFont(new Font("Serif", Font.PLAIN, 20));

		return textArea;
	}

	@SuppressWarnings("unused")
	private void start() {

	}

	@SuppressWarnings("unused")
	private void srtop() {

	}
}
