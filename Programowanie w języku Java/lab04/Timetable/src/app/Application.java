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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import beans.Note;
import core.NoteLabel;
import dao.DbConnection;
import dao.NoteDAO;

public class Application {

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
	private JLabel lblNotes;
	private JTable tableOfNotes;
	private JButton btnShowInThe;
	private JButton btnShowAllNotes;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JSpinner spinnerStartDate;
	private JSpinner spinnerEndDate;
	private JButton btnRemoveNote;
	private JButton btnOpenNote;

	private List<Note> notes;
	private JTable tableNotesKeys;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Application() {
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
		spinnerDate.setModel(new SpinnerDateModel(new Date(1553554800000L), null, null, Calendar.DAY_OF_YEAR));
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

			lblNotes = new JLabel("Notes");
			lblNotes.setForeground(Color.WHITE);
			lblNotes.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblNotes.setBackground(new Color(51, 51, 51));
			lblNotes.setBounds(10, 11, 679, 25);
			panelTimetable.add(lblNotes);

			lblFrom = new JLabel("From");
			lblFrom.setForeground(Color.WHITE);
			lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblFrom.setBackground(new Color(51, 51, 51));
			lblFrom.setBounds(468, 105, 212, 25);
			panelTimetable.add(lblFrom);

			lblTo = new JLabel("To");
			lblTo.setForeground(Color.WHITE);
			lblTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblTo.setBackground(new Color(51, 51, 51));
			lblTo.setBounds(468, 172, 212, 25);
			panelTimetable.add(lblTo);

			tableNotesKeys = new JTable();
			tableNotesKeys.setFont(new Font("Tahoma", Font.BOLD, 15));
			tableNotesKeys.setModel(new DefaultTableModel(new Object[][] { { "ID", "Title", "Label", "Date" }, },
					new String[] { "New column", "New column", "New column", "New column" }));
			tableNotesKeys.getColumnModel().getColumn(0).setPreferredWidth(73);
			tableNotesKeys.setBounds(10, 47, 445, 16);
			panelTimetable.add(tableNotesKeys);

			tableOfNotes = new JTable();
			tableOfNotes.setBounds(10, 65, 445, 345);
			panelTimetable.add(tableOfNotes);

			btnShowInThe = new JButton("Show in time range");
			btnShowInThe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showNotesInSpecyficDates();
				}
			});
			btnShowInThe.setForeground(Color.WHITE);
			btnShowInThe.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnShowInThe.setFocusable(false);
			btnShowInThe.setFocusTraversalKeysEnabled(false);
			btnShowInThe.setFocusPainted(false);
			btnShowInThe.setBackground(new Color(51, 51, 51));
			btnShowInThe.setBounds(465, 248, 212, 37);
			panelTimetable.add(btnShowInThe);

			btnShowAllNotes = new JButton("Show all notes");
			btnShowAllNotes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAllNotes();
				}
			});
			btnShowAllNotes.setForeground(Color.WHITE);
			btnShowAllNotes.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnShowAllNotes.setFocusable(false);
			btnShowAllNotes.setFocusTraversalKeysEnabled(false);
			btnShowAllNotes.setFocusPainted(false);
			btnShowAllNotes.setBackground(new Color(51, 51, 51));
			btnShowAllNotes.setBounds(465, 47, 212, 47);
			panelTimetable.add(btnShowAllNotes);

			btnRemoveNote = new JButton("Remove note");
			btnRemoveNote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					removeNote();
					showAllNotes();
				}
			});

			btnRemoveNote.setForeground(Color.WHITE);
			btnRemoveNote.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnRemoveNote.setFocusable(false);
			btnRemoveNote.setFocusTraversalKeysEnabled(false);
			btnRemoveNote.setFocusPainted(false);
			btnRemoveNote.setBackground(new Color(51, 51, 51));
			btnRemoveNote.setBounds(465, 300, 215, 37);
			panelTimetable.add(btnRemoveNote);
			BufferedImage openNoteImage = ImageIO.read((getClass().getClassLoader().getResource("openNote.png")));
			btnOpenNote = new JButton("    Open", new ImageIcon(openNoteImage));
			btnOpenNote.setHorizontalAlignment(SwingConstants.LEFT);
			btnOpenNote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openNote();
				}
			});
			btnOpenNote.setForeground(Color.WHITE);
			btnOpenNote.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnOpenNote.setFocusable(false);
			btnOpenNote.setFocusTraversalKeysEnabled(false);
			btnOpenNote.setFocusPainted(false);
			btnOpenNote.setBackground(new Color(51, 51, 51));
			btnOpenNote.setBounds(465, 348, 215, 62);
			panelTimetable.add(btnOpenNote);

			spinnerStartDate = new JSpinner();
			spinnerStartDate.setModel(new SpinnerDateModel(new Date(1553209200000L), null, null, Calendar.DAY_OF_YEAR));
			spinnerStartDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			spinnerStartDate.setBackground(SystemColor.menu);
			spinnerStartDate.setBounds(468, 130, 212, 41);
			panelTimetable.add(spinnerStartDate);

			spinnerEndDate = new JSpinner();
			spinnerEndDate.setModel(new SpinnerDateModel(new Date(1553295600000L), null, null, Calendar.DAY_OF_YEAR));
			spinnerEndDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
			spinnerEndDate.setBackground(SystemColor.menu);
			spinnerEndDate.setBounds(468, 196, 212, 41);
			panelTimetable.add(spinnerEndDate);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void addNewNote() {

		if (textFieldTitle.getText().equals("")) {
			JOptionPane.showMessageDialog(null, " The Title field is empty");
			return;
		}

		if (textArea.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Note is empty");
			return;
		}

		DbConnection dbConnection;
		try {
			dbConnection = new DbConnection();
			NoteDAO noteDAO = new NoteDAO(dbConnection);
			int newId = noteDAO.getCount() + 1;
			Note newNote = new Note(newId, textFieldTitle.getText(), (NoteLabel) comboBoxLabel.getSelectedItem(),
					spinnerDate.getValue().toString(), (Integer) spinnerX.getValue(), (Integer) spinnerX.getValue(),
					textArea.getText());
			noteDAO.AddNote(newNote);
			JOptionPane.showMessageDialog(null, textFieldTitle.getText() + " Note has been created.");
			dbConnection.closeConnection();
			clearTextFields();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void clearTextFields() {
		textFieldTitle.setText(null);
		comboBoxLabel.setSelectedIndex(0);
		spinnerX.setValue(0);
		spinnerY.setValue(0);
		textArea.setText(null);
	}

	private void showAllNotes() {

		try {
			String column[] = { "Id", "Title", "Label", "Date" };
			DefaultTableModel model = new DefaultTableModel(column, 0);
			DbConnection dbConnection;
			try {
				dbConnection = new DbConnection();
				NoteDAO noteDAO = new NoteDAO(dbConnection);
				notes = noteDAO.GetNotes();
				dbConnection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (notes.equals(null)) {
				JOptionPane.showMessageDialog(null, "The list is empty");
				return;
			}
			for (Note note : notes) {
				Object[] content = { note.getID(), note.getTitle(), note.getLabel(), note.getDate() };
				model.addRow(content);
			}
			tableOfNotes.setModel(model);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	private void showNotesInSpecyficDates() {

		try {
			String column[] = { "Id", "Title", "Label", "Date" };
			DefaultTableModel model = new DefaultTableModel(column, 0);
			DbConnection dbConnection;
			try {
				dbConnection = new DbConnection();
				NoteDAO noteDAO = new NoteDAO(dbConnection);
				notes = noteDAO.GetNotes();
				dbConnection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (notes.equals(null)) {
				JOptionPane.showMessageDialog(null, "The list is empty");
				return;
			}

			for (Note note : notes) {

				String dateInString = note.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
				try {
					Date date = sdf.parse(dateInString);
					Date startDate = (Date) spinnerStartDate.getValue();
					Date endDate = (Date) spinnerEndDate.getValue();

					if (date.after(startDate) && date.before(endDate)) {
						Object[] content = { note.getID(), note.getTitle(), note.getLabel(), note.getDate() };
						model.addRow(content);
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			tableOfNotes.setModel(model);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	private void removeNote() {

		if (tableOfNotes != null) {
			if (!tableOfNotes.getSelectionModel().isSelectionEmpty()) {

				DbConnection dbConnection;
				try {
					dbConnection = new DbConnection();
					NoteDAO noteDAO = new NoteDAO(dbConnection);

					int row = tableOfNotes.getSelectedRow();
					int id = (int) tableOfNotes.getModel().getValueAt(row, 0);
					noteDAO.DeleteNotesById(id);

					JOptionPane.showMessageDialog(null, textFieldTitle.getText() + " Note has been removed.");
					dbConnection.closeConnection();
					clearTextFields();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void openNote() {
		if (tableOfNotes != null) {
			if (!tableOfNotes.getSelectionModel().isSelectionEmpty()) {

				DbConnection dbConnection;
				try {
					dbConnection = new DbConnection();
					NoteDAO noteDAO = new NoteDAO(dbConnection);
					int row = tableOfNotes.getSelectedRow();
					int id = (int) tableOfNotes.getModel().getValueAt(row, 0);
					Note note = noteDAO.GetNoteById(id);
					dbConnection.closeConnection();
					new NotesView(note.getTitle(), note.getLabel().getValue(), note.getDate(), note.getNoteContent());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
