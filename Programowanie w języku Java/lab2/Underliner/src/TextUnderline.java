import java.awt.Font;
import java.util.Map;

import javax.swing.JTextArea;
import java.awt.font.TextAttribute;

/**
 * @author Pawel Szynal 226026
 */
public class TextUnderline {

	@SuppressWarnings("unchecked")
	public JTextArea TextProcces(JTextArea textArea) {

		Font font = textArea.getFont();
		@SuppressWarnings("rawtypes")
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		textArea.setFont(font.deriveFont(attributes));

		return textArea;
	}

}
