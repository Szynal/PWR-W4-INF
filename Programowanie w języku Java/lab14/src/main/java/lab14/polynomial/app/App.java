package lab14.polynomial.app;

import javax.swing.*;

import lab14.polynomial.core.Polynomial;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JTextField textB;
	private JTextField textA;
	private JTextField textC;
	private JTextField textD;
	private JTextArea output;
	private JButton obliczButton;
	private JLabel wynikLabel;

	public App(String title) throws HeadlessException {
		super(title);

		textA.setToolTipText("Wspó³czynnik A - input field");
		textB.setToolTipText("Wspó³czynnik B - input field");
		textC.setToolTipText("Wspó³czynnik C - input field");
		textD.setToolTipText("Wspó³czynnik D - input field");
		output.setToolTipText("Wynik");

		wynikLabel.getAccessibleContext().setAccessibleDescription("sadasfsafas");

		setResizable(false);
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		obliczButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calcAction();
			}
		});
	}

	private void calcAction() {
		Polynomial st3 = new Polynomial();

		double a, b, c, d;

		try {
			a = Double.parseDouble(textA.getText());
			b = Double.parseDouble(textB.getText());
			c = Double.parseDouble(textC.getText());
			d = Double.parseDouble(textD.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Wrong input numbers format");
			return;
		}

		st3.solve(a, b, c, d);

		output.setText(st3.getResultString());
	}

	public static void main(String[] args) {
		App service = new App("Java Big Numbers");
	}
}
