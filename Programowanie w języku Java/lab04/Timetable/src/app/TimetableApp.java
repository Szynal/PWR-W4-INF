package app;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLClassLoader;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;

import core.Note;
import core.NoteLabel;
import dao.DbConnection;
import dao.NoteDAO;

public class TimetableApp {

	private JFrame app;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	URLClassLoader classLoader = null;

	private JLabel lblLabel;
	private JLabel lblNoteContent;
	private JLabel lblDate;
	private JLabel lblTitle;
	private JLabel lblTheSizeOf;
	private JLabel lblX;
	private JLabel lblY;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JSpinner spinnerDate;
	private JSpinner spinnerX;
	private JSpinner spinnerY;
	private JButton btnNewButton;
	private JTextField textFieldTitle;

	private JComboBox<Object> comboBoxLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimetableApp window = new TimetableApp();
					window.app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TimetableApp() {
		initializeJFrame();
		initializeJMenu();
		initialize();
	}

	private void initializeJFrame() {
		try {

			app = new JFrame();
			app.setBackground(new Color(51, 51, 51));
			app.setTitle("Timetable App");
			app.setForeground(new Color(0, 0, 0));
			app.getContentPane().setBackground(new Color(51, 51, 51));
			app.setResizable(false);
			BufferedImage appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			app.setIconImage(appIcon);
			app.setBounds(100, 100, 730, 532);
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeJMenu() {
		try {
			menuBar = new JMenuBar();
			app.setJMenuBar(menuBar);
			mnNewMenu = new JMenu("About");
			menuBar.add(mnNewMenu);
			mntmTask = new JMenuItem("Program description");
			mnNewMenu.add(mntmTask);
			mntmTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null,
							"@link: http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/Zadania/ZadJBeans1.txt");
				}
			});
			mntmAuthor = new JMenuItem("Author");
			mnNewMenu.add(mntmAuthor);
			mntmAuthor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Pawe³ Szynal\n226026");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		app.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 704, 460);
		app.getContentPane().add(tabbedPane);

		JPanel panelNewNote = new JPanel();
		try {
			BufferedImage addIcon = ImageIO.read((getClass().getClassLoader().getResource("newNote.png")));
			tabbedPane.addTab("Create a new note", (new ImageIcon(addIcon)), panelNewNote, null);
			panelNewNote.setBackground(new Color(51, 51, 51));
			panelNewNote.setLayout(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		lblNoteContent = new JLabel("Note Content");
		lblNoteContent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNoteContent.setForeground(new Color(255, 255, 255));
		lblNoteContent.setBackground(new Color(51, 51, 51));
		lblNoteContent.setBounds(232, 11, 457, 42);
		panelNewNote.add(lblNoteContent);

		lblLabel = new JLabel("Label");
		lblLabel.setForeground(Color.WHITE);
		lblLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLabel.setBackground(new Color(51, 51, 51));
		lblLabel.setBounds(10, 108, 212, 25);
		panelNewNote.add(lblLabel);

		lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBackground(new Color(51, 51, 51));
		lblDate.setBounds(10, 196, 212, 25);
		panelNewNote.add(lblDate);

		lblTitle = new JLabel("Title");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBackground(new Color(51, 51, 51));
		lblTitle.setBounds(10, 20, 212, 25);
		panelNewNote.add(lblTitle);

		lblTheSizeOf = new JLabel("Size of the note text area");
		lblTheSizeOf.setForeground(Color.WHITE);
		lblTheSizeOf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTheSizeOf.setBackground(new Color(51, 51, 51));
		lblTheSizeOf.setBounds(10, 284, 212, 25);
		panelNewNote.add(lblTheSizeOf);

		textFieldTitle = new JTextField();
		textFieldTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldTitle.setBackground(SystemColor.text);
		textFieldTitle.setBounds(10, 56, 212, 41);
		panelNewNote.add(textFieldTitle);
		textFieldTitle.setColumns(10);

		comboBoxLabel = new JComboBox<Object>();
		comboBoxLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxLabel.setModel(new DefaultComboBoxModel<Object>(NoteLabel.values()));
		comboBoxLabel.setBounds(10, 144, 212, 41);
		panelNewNote.add(comboBoxLabel);

		spinnerDate = new JSpinner();
		spinnerDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerDate.setBackground(SystemColor.menu);
		spinnerDate.setModel(new SpinnerDateModel(new Date(1553295600000L), null, null, Calendar.DAY_OF_YEAR));
		spinnerDate.setBounds(10, 232, 212, 41);
		panelNewNote.add(spinnerDate);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(232, 56, 457, 354);
		panelNewNote.add(scrollPane);

		textArea = new JTextArea();
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textArea.setDoubleBuffered(true);
		textArea.setDragEnabled(true);
		textArea.setText("  ");
		textArea.setDropMode(DropMode.INSERT);
		scrollPane.setViewportView(textArea);

		spinnerX = new JSpinner();
		spinnerX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerX.setBounds(10, 320, 70, 42);
		panelNewNote.add(spinnerX);

		spinnerY = new JSpinner();
		spinnerY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerY.setBounds(121, 320, 70, 42);
		panelNewNote.add(spinnerY);

		lblX = new JLabel("X");
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblX.setBackground(new Color(51, 51, 51));
		lblX.setBounds(90, 320, 21, 42);
		panelNewNote.add(lblX);

		lblY = new JLabel("Y");
		lblY.setForeground(Color.WHITE);
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblY.setBackground(new Color(51, 51, 51));
		lblY.setBounds(201, 320, 21, 42);
		panelNewNote.add(lblY);

		btnNewButton = new JButton("Add a note");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewNote();
			}
		});
		btnNewButton.setBackground(new Color(51, 51, 51));
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 373, 212, 37);
		panelNewNote.add(btnNewButton);

		JPanel panelTimetable = new JPanel();

		try {
			BufferedImage addNoteIcon = ImageIO.read((getClass().getClassLoader().getResource("timetable.png")));
			tabbedPane.addTab("List of notes", (new ImageIcon(addNoteIcon)), panelTimetable, null);
			panelTimetable.setBackground(new Color(51, 51, 51));
			panelTimetable.setLayout(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void addNewNote() {
		DbConnection dbConnection;
		try {
			dbConnection = new DbConnection();
			NoteDAO noteDAO = new NoteDAO(dbConnection);
			int newId = noteDAO.getCount() + 1;
			Note newNote = new Note(newId, textFieldTitle.getText(), (NoteLabel) comboBoxLabel.getSelectedItem(),
					spinnerDate.getValue().toString(), (Integer) spinnerX.getValue(), (Integer) spinnerX.getValue(),
					textArea.getText());
			noteDAO.AddNote(newNote);
			dbConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
