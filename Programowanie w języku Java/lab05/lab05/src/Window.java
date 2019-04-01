
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Window {

	private JFrame app;

	private BufferedImage appIcon;

	private static GcdJNI gcd = new GcdJNI();

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private JTextField textField_vectorA;
	private JTextField textField_vectorB;
	private JTextField textField_Result;

	private JButton btnVectorA;
	private JButton btnVectorB;
	private JButton btnCalculate;

	private JSpinner spinnerDimension;
	private JPanel panel_multi02;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void JFrameInitialize() {
		try {
			app = new JFrame();
			appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			app.setIconImage(appIcon);
			app.getContentPane().setBackground(new Color(0, 51, 51));
			app.setTitle("JNI");
			app.setSize(420, 403);
			app.setLocationRelativeTo(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void JMenuInitialize() {
		try {
			menuBar = new JMenuBar();
			app.setJMenuBar(menuBar);
			mnNewMenu = new JMenu("About");
			menuBar.add(mnNewMenu);
			mntmTask = new JMenuItem("Program description");
			mnNewMenu.add(mntmTask);
			mntmTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Napisz program z wykorzystaniem JNI.\r\n" + "\r\n"
							+ "Niech w klasie bÄdÄ trzy metody natywne sĹuĹźÄce do obliczania\r\n"
							+ "iloczynu skalarnego dwĂłch wektorĂłw.\r\n" + "\r\n"
							+ "Schemat implementacji klasy Java:\r\n" + "\r\n" + "class .....{\r\n" + ".....\r\n"
							+ "public Double[] a;\r\n" + "public Double[] b;\r\n" + "public Double c;\r\n" + "\r\n"
							+ "public native Double multi01(Dobuble[] a, Double[] b);\r\n"
							+ "// zakĹadamy, Ĺźe po stronie kodu natywnego wyliczony zostanie iloczyn skalarny dwĂłch wektorĂłw\r\n"
							+ "public native Double multi02(Dobuble[] a); \r\n"
							+ "// zakĹadamy, Ĺźe drugi atrybut bÄdzie pobrany z obiektu przekazanego do metody natywnej \r\n"
							+ "public native void multi03();\r\n"
							+ "// zakĹadamy, Ĺźe po stronie natywnej utworzone zostanie okienko na atrybuty,\r\n"
							+ "// a po ich wczytaniu i przepisaniu do a,b obliczony zostanie wynik. \r\n"
							+ "// Wynik powinna wyliczaÄ metoda Javy multi04 \r\n"
							+ "// (korzystajÄca z parametrĂłw a,b i wpisujÄca wynik do c).\r\n" + "\r\n"
							+ "public void multi04(){\r\n" + " ..... // mnoĹźy a i b, wynik wpisuje do c\r\n" + " }\r\n"
							+ "\r\n" + "}");
				}
			});
			mntmAuthor = new JMenuItem("Author");
			mnNewMenu.add(mntmAuthor);
			mntmAuthor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Pawel‚ Szynal\n226026");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Window() {
		JFrameInitialize();
		JMenuInitialize();
		initialize();
	}

	private void initialize() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		app.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_multi01 = new JPanel();
		panel_multi01.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("multi01", null, panel_multi01, null);
		panel_multi01.setLayout(null);

		JPanel panel_dimension = new JPanel();
		panel_dimension.setBackground(new Color(51, 51, 51));
		panel_dimension.setForeground(new Color(255, 255, 255));
		panel_dimension.setBounds(10, 11, 127, 123);
		panel_dimension.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dimension",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_multi01.add(panel_dimension);
		panel_dimension.setLayout(null);

		spinnerDimension = new JSpinner();
		spinnerDimension.setFont(new Font("Tahoma", Font.BOLD, 15));
		spinnerDimension.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		spinnerDimension.setBounds(10, 39, 107, 32);
		panel_dimension.add(spinnerDimension);

		JPanel panel_vectorA = new JPanel();
		panel_vectorA.setBackground(new Color(51, 51, 51));
		panel_vectorA.setForeground(new Color(255, 255, 255));
		panel_vectorA.setBounds(147, 11, 242, 56);
		panel_vectorA.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vector  \"A\"",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_multi01.add(panel_vectorA);
		panel_vectorA.setLayout(null);

		textField_vectorA = new JTextField();
		textField_vectorA.setBounds(10, 22, 78, 20);
		textField_vectorA.setEditable(false);
		panel_vectorA.add(textField_vectorA);
		textField_vectorA.setColumns(10);

		btnVectorA = new JButton("Enter coordinates");
		btnVectorA.setBackground(new Color(51, 51, 51));
		btnVectorA.setForeground(new Color(255, 255, 255));
		btnVectorA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVectorA.setBounds(98, 21, 134, 23);
		btnVectorA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterVectorACordinates();
			}
		});
		panel_vectorA.add(btnVectorA);

		JPanel panel_vectorB = new JPanel();
		panel_vectorB.setBackground(new Color(51, 51, 51));
		panel_vectorB.setForeground(new Color(255, 255, 255));
		panel_vectorB.setBounds(147, 78, 242, 56);
		panel_vectorB.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vector  \"B\"",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_multi01.add(panel_vectorB);
		panel_vectorB.setLayout(null);

		textField_vectorB = new JTextField();
		textField_vectorB.setBounds(10, 22, 78, 20);
		textField_vectorB.setEditable(false);
		textField_vectorB.setColumns(10);
		panel_vectorB.add(textField_vectorB);

		btnVectorB = new JButton("Enter coordinates");
		btnVectorB.setForeground(new Color(255, 255, 255));
		btnVectorB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVectorB.setBackground(new Color(51, 51, 51));
		btnVectorB.setBounds(98, 21, 134, 23);
		btnVectorB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterVectorBCordinates();
			}
		});
		btnVectorB.setEnabled(false);
		panel_vectorB.add(btnVectorB);

		JPanel panel_result = new JPanel();
		panel_result.setBackground(new Color(51, 51, 51));
		panel_result.setBounds(10, 145, 379, 158);
		panel_result.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Result",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_multi01.add(panel_result);

		btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCalculate.setBackground(new Color(51, 51, 51));
		btnCalculate.setForeground(new Color(255, 255, 255));
		btnCalculate.setBounds(10, 21, 359, 38);
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double result = gcd.multi01(gcd.a, gcd.b);
				textField_Result.setText(Double.toString(result));
			}
		});
		panel_result.setLayout(null);
		btnCalculate.setEnabled(false);
		panel_result.add(btnCalculate);

		textField_Result = new JTextField();
		textField_Result.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField_Result.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Result.setBounds(10, 70, 359, 77);
		textField_Result.setEditable(false);
		textField_Result.setColumns(15);
		panel_result.add(textField_Result);

		panel_multi02 = new JPanel();
		panel_multi02.setForeground(new Color(255, 255, 255));
		panel_multi02.setBackground(new Color(51, 51, 51));
		panel_multi02.setToolTipText("");
		tabbedPane.addTab("multi02", null, panel_multi02, null);

		JPanel panel_multi03 = new JPanel();
		panel_multi03.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("multi03", null, panel_multi03, null);
		panel_multi03.setLayout(null);
	}

	private void EnterVectorACordinates() {
		try {
			int dimension = (int) spinnerDimension.getValue();
			gcd.Dimension = dimension;
			btnVectorA.setEnabled(true);
		} catch (NumberFormatException e1) {
		}

		gcd.a = new Double[gcd.Dimension];
		String text = "[";
		for (int i = 0; i < gcd.Dimension; ++i) {
			Double val = 0.0;
			String input = "";
			while (true) {
				try {
					input = JOptionPane.showInputDialog(null, "Podaj " + String.valueOf(i + 1) + ". współrzędną: ");
					val = Double.parseDouble(input);
					break;
				} catch (NumberFormatException e1) {
				}
			}
			gcd.a[i] = val;
			text += input + ',';
		}
		text = text.substring(0, text.length() - 1) + ']';
		textField_vectorA.setText(text);
		btnVectorB.setEnabled(true);
	}

	private void EnterVectorBCordinates() {
		gcd.b = new Double[gcd.Dimension];
		String text = "[";
		for (int i = 0; i < gcd.Dimension; ++i) {
			Double val = 0.0;
			String input = "";
			while (true) {
				try {
					input = JOptionPane.showInputDialog(null, "Podaj " + String.valueOf(i + 1) + ". współrzędną: ");
					val = Double.parseDouble(input);
					break;
				} catch (NumberFormatException e1) {
				}
			}
			gcd.b[i] = val;
			text += input + ',';
		}
		text = text.substring(0, text.length() - 1) + ']';
		textField_vectorB.setText(text);
		btnCalculate.setEnabled(true);
	}

}
