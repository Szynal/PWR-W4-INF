package beans;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.Customizer;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class TimetableCustomizer extends JTabbedPane implements Customizer {

	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public TimetableCustomizer() {

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(51, 51, 51));
		addTab("Tytu\u0142 panelu", null, titlePanel, null);
		titlePanel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 241, 90);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ustaw tytu\u0142",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		titlePanel.add(panel_2);
		panel_2.setLayout(null);

		textField = new JTextField();
		textField.setBounds(12, 23, 205, 54);
		panel_2.add(textField);
		textField.setColumns(20);

		JButton btnSaveTitle = new JButton("Save");
		btnSaveTitle.setBackground(new Color(51, 51, 51));
		btnSaveTitle.setForeground(new Color(255, 255, 255));
		btnSaveTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSaveTitle.setBounds(12, 116, 241, 35);
		titlePanel.add(btnSaveTitle);

		JPanel sizeTextPanel = new JPanel();
		sizeTextPanel.setBackground(new Color(51, 51, 51));
		addTab("Rozmiar pola tekstowego", null, sizeTextPanel, null);
		sizeTextPanel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 13, 236, 93);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ustaw rozmiar",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sizeTextPanel.add(panel_3);
		panel_3.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setBounds(12, 23, 190, 22);
		panel_3.add(textField_1);
		textField_1.setColumns(5);

		textField_2 = new JTextField();
		textField_2.setBounds(12, 58, 190, 22);
		panel_3.add(textField_2);
		textField_2.setColumns(5);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setBounds(207, 26, 21, 19);
		panel_3.add(lblNewLabel);

		JLabel lblY = new JLabel("Y");
		lblY.setBounds(207, 61, 21, 19);
		panel_3.add(lblY);

		JButton btnSaveText = new JButton("Save");
		btnSaveText.setForeground(Color.WHITE);
		btnSaveText.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSaveText.setBackground(new Color(51, 51, 51));
		btnSaveText.setBounds(12, 119, 241, 35);
		sizeTextPanel.add(btnSaveText);
	}

	public void setData(String s) {
		StringTokenizer tokenizer = new StringTokenizer(s);

		int i = 0;
		Double[] values = new Double[tokenizer.countTokens()];
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			try {
				System.out.println(token);
				values[i] = Double.parseDouble(token);
				i++;
			} catch (NumberFormatException exception) {
			}
		}
		setValues(values);
	}

	public void setTitle(String newValue) {
	}

	public void setTitlePosition(int i) {

		Integer newValue = new Integer(i);

	}

	public void setInverse(boolean b) {

		Boolean newValue = new Boolean(b);
	}

	public void setValues(Double[] newValue) {
	}

	public void setGraphColor(Color newValue) {
	}

	public void setObject(Object obj) {
		bean = (Timetable) obj;
		textField.setText(bean.getTitle());
		int width = (int) bean.getSize().getWidth();
		textField_1.setText(String.valueOf(width));
		int height = (int) bean.getSize().getHeight();
		textField_2.setText(String.valueOf(height));
	}

	public Dimension getPreferredSize() {
		return new Dimension(XPREFSIZE, YPREFSIZE);
	}

	private static final int XPREFSIZE = 300;
	private static final int YPREFSIZE = 200;
	private Timetable bean;

}
