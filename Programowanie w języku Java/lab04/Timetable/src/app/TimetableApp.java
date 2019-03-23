package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextField;
import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.DropMode;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import core.Note.NoteLabel;

public class TimetableApp {

	private JFrame app;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	URLClassLoader classLoader = null;
	private JTextField textFieldTitle;

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
		JFrameInitialize();
		JMenuInitialize();
		initialize();
	}

	private void JFrameInitialize() {
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
					JOptionPane.showMessageDialog(null,
							"http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/Zadania/ZadRMI_01.txt");
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

		JLabel lblNoteContent = new JLabel("Note Content");
		lblNoteContent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNoteContent.setForeground(new Color(255, 255, 255));
		lblNoteContent.setBackground(new Color(51, 51, 51));
		lblNoteContent.setBounds(232, 11, 457, 42);
		panelNewNote.add(lblNoteContent);

		JLabel lblLabel = new JLabel("Label");
		lblLabel.setForeground(Color.WHITE);
		lblLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLabel.setBackground(new Color(51, 51, 51));
		lblLabel.setBounds(10, 108, 212, 25);
		panelNewNote.add(lblLabel);

		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBackground(new Color(51, 51, 51));
		lblDate.setBounds(10, 196, 212, 25);
		panelNewNote.add(lblDate);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBackground(new Color(51, 51, 51));
		lblTitle.setBounds(10, 20, 212, 25);
		panelNewNote.add(lblTitle);
		
		JLabel lblTheSizeOf = new JLabel("Size of the note text area");
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

		JComboBox comboBoxLabel = new JComboBox();
		comboBoxLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxLabel.setModel(new DefaultComboBoxModel(NoteLabel.values()));
		comboBoxLabel.setBounds(10, 144, 212, 41);
		panelNewNote.add(comboBoxLabel);

		JSpinner spinnerDate = new JSpinner();
		spinnerDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerDate.setBackground(SystemColor.menu);
		spinnerDate.setModel(new SpinnerDateModel(new Date(1553295600000L), null, null, Calendar.DAY_OF_YEAR));
		spinnerDate.setBounds(10, 232, 212, 41);
		panelNewNote.add(spinnerDate);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(232, 56, 457, 354);
		panelNewNote.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textArea.setDoubleBuffered(true);
		textArea.setDragEnabled(true);
		textArea.setText("  ");
		textArea.setDropMode(DropMode.INSERT);
		scrollPane.setViewportView(textArea);
		
		JSpinner spinnerX = new JSpinner();
		spinnerX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerX.setBounds(10, 320, 70, 42);
		panelNewNote.add(spinnerX);
		
		JSpinner spinnerY = new JSpinner();
		spinnerY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerY.setBounds(121, 320, 70, 42);
		panelNewNote.add(spinnerY);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblX.setBackground(new Color(51, 51, 51));
		lblX.setBounds(90, 320, 21, 42);
		panelNewNote.add(lblX);
		
		JLabel lblY = new JLabel("Y");
		lblY.setForeground(Color.WHITE);
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblY.setBackground(new Color(51, 51, 51));
		lblY.setBounds(201, 320, 21, 42);
		panelNewNote.add(lblY);
		
				JButton btnNewButton = new JButton("Add a note");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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

	private boolean CheckForbiddenPattern(File file) {
		Pattern pattern01 = Pattern.compile("Application", Pattern.CASE_INSENSITIVE);
		Pattern pattern02 = Pattern.compile("LoadText", Pattern.CASE_INSENSITIVE);
		Pattern pattern03 = Pattern.compile("Reflection", Pattern.CASE_INSENSITIVE);

		Matcher matcher01 = pattern01.matcher(file.getName());
		Matcher matcher02 = pattern02.matcher(file.getName());
		Matcher matcher03 = pattern03.matcher(file.getName());
		if (!matcher01.find() && !matcher02.find() && !matcher03.find()) {
			return true;
		} else {
			return false;
		}
	}

	private void TableUpdate() {

	}
}
