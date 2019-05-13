package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class Window {

	private JFrame frmSystemZarzdzajcyPrzychodni;
	private static Window window;

	private JTextField textField;

	private JLabel lblBegin;
	private JLabel lblEnd;
	private JLabel lblRoom;

	private JPanel panel_6;

	private JTextArea textArea;

	private JButton btnPrevPatient;
	private JButton btnNextPatient;
	private JButton button;

	///////////////////////

	private int ID_doctor = 1;

	private int visitIndex = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Window();
					window.frmSystemZarzdzajcyPrzychodni.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSystemZarzdzajcyPrzychodni = new JFrame();
		frmSystemZarzdzajcyPrzychodni.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Window.class.getResource("/lab07/iconfinder_medical-healthcare-hospital-25_4082088.png")));
		frmSystemZarzdzajcyPrzychodni.setResizable(false);
		frmSystemZarzdzajcyPrzychodni.setTitle("System zarz\u0105dzaj\u0105cy przychodni\u0105");
		frmSystemZarzdzajcyPrzychodni.setBounds(100, 100, 747, 420);
		frmSystemZarzdzajcyPrzychodni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (tabbedPane.getSelectedIndex() == 1) {
					String in = JOptionPane.showInputDialog(null, "Podaj swoje ID", "Logowanie",
							JOptionPane.PLAIN_MESSAGE);
					if (in != null) {
						ID_doctor = Integer.parseInt(in);
					}
				}
			}
		});
		frmSystemZarzdzajcyPrzychodni.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Rejestracja", null, panel, null);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wyszukaj", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(12, 36, 142, 43);
		panel.add(panel_2);
		panel_2.setLayout(null);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		textField.setBounds(6, 16, 128, 20);
		panel_2.add(textField);
		textField.setColumns(15);

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Podaj imi� pacjenta", "Edytor pacjenta",
						JOptionPane.PLAIN_MESSAGE);
				if (name != null) {
					String surname = JOptionPane.showInputDialog(null, "Podaj nazwisko pacjenta", "Edytor pacjenta",
							JOptionPane.PLAIN_MESSAGE);
					if (surname != null) {
						// Add patient
						// conn.addPatient(name, surname);
					}
				}
			}
		});
		btnDodaj.setBounds(151, 134, 89, 23);
		panel.add(btnDodaj);

		JButton btnUsun = new JButton("Usu\u0144");
		btnUsun.setEnabled(false);
		btnUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Patient patient = list.getSelectedValue();
				// conn.deletePatient(patient);
				;
			}
		});
		btnUsun.setBounds(151, 188, 89, 23);
		panel.add(btnUsun);

		JButton btnModyfikuj = new JButton("Modyfikuj");

		btnModyfikuj.setEnabled(false);
		btnModyfikuj.setBounds(151, 240, 89, 23);
		panel.add(btnModyfikuj);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 93, 128, 240);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rezerwacje pacjenta",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(252, 82, 474, 258);
		panel.add(panel_7);
		panel_7.setLayout(null);

		button = new JButton("Dodaj");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Podaj imi� lekarza", "Dodawanie nowej wizyty",
						JOptionPane.PLAIN_MESSAGE);
				if (name != null) {
				}
			}
		});
		button.setBounds(373, 93, 89, 23);
		panel_7.add(button);

		JButton btnUsun2 = new JButton("Usu\u0144");
		btnUsun2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// conn.deleteVisit(list_1.getSelectedValue());

			}
		});
		btnUsun2.setBounds(373, 146, 89, 23);
		panel_7.add(btnUsun2);
		btnUsun2.setEnabled(false);

		JButton btnConfirm = new JButton("Potwierd\u017A");
		btnConfirm.setBounds(117, 230, 126, 23);
		panel_7.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Visit visit = list_1.getSelectedValue();
				// visit.setConfirmed(true);
				// conn.modifyVisit(visit);
				// list_1.setSelectedIndex(-1);
				btnConfirm.setEnabled(false);
			}
		});
		btnConfirm.setEnabled(false);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Lekarz", null, tabbedPane_1, null);

		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("Dy\u017Cur", null, panel_4, null);
		panel_4.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dy\u017Cur",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 11, 711, 55);
		panel_4.add(panel_3);

		lblBegin = new JLabel("Pocz\u0105tek:");
		lblBegin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBegin.setBounds(6, 16, 219, 32);
		panel_3.add(lblBegin);

		lblEnd = new JLabel("Koniec:");
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnd.setBounds(524, 16, 175, 32);
		panel_3.add(lblEnd);

		lblRoom = new JLabel("Pok\u00F3j");
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoom.setBounds(310, 16, 99, 32);
		panel_3.add(lblRoom);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Imie Nazwisko",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(193, 87, 310, 244);
		panel_4.add(panel_6);
		panel_6.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(27, 16, 224, 190);
		panel_6.add(panel_1);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Zalecenia",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);

		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (visitIndex > 0) {

				}
			}
		});
		textArea.setBounds(6, 16, 253, 167);
		panel_1.add(textArea);

		JButton btnPrevVisit = new JButton("Poprzednia wizyta\r\n");
		btnPrevVisit.setBounds(6, 214, 141, 23);
		panel_6.add(btnPrevVisit);
		btnPrevVisit.setEnabled(false);

		JButton btnNextVisit = new JButton("Nast\u0119pna wizyta");
		btnNextVisit.setBounds(157, 214, 143, 23);
		panel_6.add(btnNextVisit);
		btnNextVisit.setEnabled(false);

		btnPrevPatient = new JButton("<");
		btnPrevPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				--visitIndex;

			}
		});
		btnPrevPatient.setEnabled(false);
		btnPrevPatient.setBounds(121, 182, 51, 49);
		panel_4.add(btnPrevPatient);

		btnNextPatient = new JButton(">");
		btnNextPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				++visitIndex;
				// refreshVisit();
			}
		});
		btnNextPatient.setEnabled(false);
		btnNextPatient.setBounds(521, 182, 51, 49);
		panel_4.add(btnNextPatient);

		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("Godziny przyj\u0119\u0107", null, panel_5, null);
		panel_5.setLayout(null);

		JButton btnDodaj_1 = new JButton("Dodaj");
		btnDodaj_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String day = JOptionPane.showInputDialog(null, "Podaj dzie� tygodnia (0 - nie, 1 - pon ... 6 - sob",
							"Edytor dy�uru", JOptionPane.PLAIN_MESSAGE);
					int dayOfTheWeek = Integer.parseInt(day);
					String begin = JOptionPane.showInputDialog(null, "Podaj pocz�tek dy�uru [HH:MM:SS]",
							"Edytor dy�uru", JOptionPane.PLAIN_MESSAGE);
					if (begin != null) {
						String end = JOptionPane.showInputDialog(null, "Podaj koniec dy�uru [HH:MM:SS]",
								"Edytor dy�uru", JOptionPane.PLAIN_MESSAGE);
						if (end != null) {
							String rm = JOptionPane.showInputDialog(null, "Podaj numer gabinetu", "Edytor dy�uru",
									JOptionPane.PLAIN_MESSAGE);
							int room = Integer.parseInt(rm);

							// refreshDoctor();
						}
					}
				} catch (NumberFormatException e) {
				}
			}
		});
		btnDodaj_1.setBounds(503, 95, 89, 23);
		panel_5.add(btnDodaj_1);

		JButton btnUsun_1 = new JButton("Usu\u0144");
		btnUsun_1.setEnabled(false);
		btnUsun_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnUsun_1.setBounds(503, 229, 89, 23);
		panel_5.add(btnUsun_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 41, 392, 254);
		panel_5.add(scrollPane_1);

	}

}
