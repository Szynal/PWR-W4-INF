package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.App;
import core.Clinic;
import core.Doctor;
import core.Patient;
import core.Visit;
import dao.DbConnection;
import dao.DoctorDAO;
import dao.PatientDAO;
import dao.VisitDAO;
import dao.ClinicDAO;

import javax.swing.UIManager;
import javax.swing.AbstractListModel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8255412204648907508L;

	DbConnection dbConnection = null;

	private JList<Patient> list;
	private JList<Visit> list_Bookings;
	private JList<Clinic> list_2;
	private DefaultListModel<Patient> model;
	private DefaultListModel<Visit> model_1;
	private DefaultListModel<Clinic> model_2;
	private Patient selectedPatient;

	private JPanel panelDoctor;
	private JPanel panelRegistration;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	Thread pluginWorkingThread;
	MouseAdapter thumbnailClickedAdapter;
	private JTabbedPane tabbedPaneRegistration;
	private JPanel panelNewPatient;
	private JTextField textField_Forename;
	private JTextField textField_Surname;
	private JTextField textField_Date;
	private JTextField textField_ID;
	private JPanel panel_PatientBookings;
	private JButton btnApprove;
	private JButton btnAdd;
	private JLabel lblDoctorID;
	private JTextField txtDoctorID;
	private JButton btnNewButton_1;
	private JPanel panel;
	private JButton btnAddAdmission;
	private JButton btnRemove;
	private JScrollPane scrollPane_Admission;
	private JList list_admission;
	private JPanel panel_1;
	private JLabel lblStartDuty;
	private JLabel lblEnd;
	private JLabel lblRoomNumber;
	private JPanel panel_2;
	private JTextField textField;
	private JButton btnRemoveBooking;
	private JPanel panel_DoctorLogin;
	private JLabel lblDrName;
	private JTextField txtDrName;
	private JLabel lvlDrSurName;
	private JTextField txtDrSurName;
	private JButton btnAddANew;

	public MainFrame() {

		try {
			dbConnection = new DbConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initialize();
		JMenuInitialize();

		try {
			refreshReceptionist();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void JFrameInitialize() {
		try {

			this.setBackground(new Color(51, 51, 51));
			this.setTitle("Medical Clinic App");
			this.setForeground(new Color(0, 0, 0));
			this.getContentPane().setBackground(new Color(51, 51, 51));
			this.setResizable(false);
			BufferedImage appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			this.setIconImage(appIcon);
			this.setBounds(100, 100, 750, 550);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void JMenuInitialize() {
		try {
			menuBar = new JMenuBar();
			this.setJMenuBar(menuBar);
			mnNewMenu = new JMenu("About");
			menuBar.add(mnNewMenu);
			mntmTask = new JMenuItem("Program description");
			mnNewMenu.add(mntmTask);
			mntmTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "");
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

	private void initTabbedPaneRegistration() {
		tabbedPaneRegistration = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneRegistration.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPaneRegistration.setBackground(new Color(255, 255, 255));
		tabbedPaneRegistration.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabbedPaneRegistration.setBounds(10, 11, 719, 378);
		panelRegistration.add(tabbedPaneRegistration);
	}

	private void initialize() {
		JFrameInitialize();
		setBounds(100, 100, 750, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.BOLD, 15));
		tabbedPane.setBounds(0, 0, 744, 430);
		getContentPane().add(tabbedPane);

		panelRegistration = new JPanel();
		panelRegistration.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Registration", null, panelRegistration, null);
		panelRegistration.setLayout(null);

		initTabbedPaneRegistration();

		panelNewPatient = new JPanel();
		panelNewPatient.setBackground(new Color(51, 51, 51));
		tabbedPaneRegistration.addTab("Register a new patient", null, panelNewPatient, null);
		panelNewPatient.setLayout(null);

		JLabel lblPatientName = new JLabel("Forename");
		lblPatientName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatientName.setForeground(new Color(255, 255, 255));
		lblPatientName.setBounds(10, 11, 225, 36);
		panelNewPatient.add(lblPatientName);

		textField_Forename = new JTextField();
		textField_Forename.setBounds(10, 40, 225, 36);
		panelNewPatient.add(textField_Forename);
		textField_Forename.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setForeground(Color.WHITE);
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(10, 77, 225, 36);
		panelNewPatient.add(lblSurname);

		textField_Surname = new JTextField();
		textField_Surname.setColumns(10);
		textField_Surname.setBounds(10, 108, 225, 36);
		panelNewPatient.add(textField_Surname);

		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setForeground(Color.WHITE);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(10, 155, 225, 36);
		panelNewPatient.add(lblDateOfBirth);

		textField_Date = new JTextField();
		textField_Date.setColumns(10);
		textField_Date.setBounds(10, 187, 225, 36);
		panelNewPatient.add(textField_Date);

		JLabel lblPersonalIdentityNumber = new JLabel("Personal identity number");
		lblPersonalIdentityNumber.setForeground(Color.WHITE);
		lblPersonalIdentityNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPersonalIdentityNumber.setBounds(10, 234, 225, 36);
		panelNewPatient.add(lblPersonalIdentityNumber);

		textField_ID = new JTextField();
		textField_ID.setColumns(10);
		textField_ID.setBounds(10, 265, 225, 36);
		panelNewPatient.add(textField_ID);

		JButton btnNewButton = new JButton("Add to base");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registerNewPatient();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(51, 51, 51));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(256, 265, 225, 36);
		panelNewPatient.add(btnNewButton);

		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setForeground(Color.WHITE);
		lblPhoto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoto.setBounds(256, 11, 225, 36);
		panelNewPatient.add(lblPhoto);

		JPanel panel_Image = new JPanel();
		panel_Image.setBounds(256, 40, 225, 214);
		panelNewPatient.add(panel_Image);

		JPanel registrationPanel = new JPanel();
		registrationPanel.setBackground(new Color(51, 51, 51));
		tabbedPaneRegistration.addTab("RegistrationPanel", null, registrationPanel, null);
		registrationPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 216, 209);
		registrationPanel.add(scrollPane);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					editPatient();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBackground(new Color(51, 51, 51));
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(10, 282, 216, 23);
		registrationPanel.add(btnEdit);

		JButton btnRemovePatient = new JButton("Remove patient");
		btnRemovePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removePatient();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRemovePatient.setEnabled(false);
		btnRemovePatient.setForeground(new Color(255, 255, 255));
		btnRemovePatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemovePatient.setBackground(new Color(51, 51, 51));
		btnRemovePatient.setBounds(10, 310, 216, 23);
		registrationPanel.add(btnRemovePatient);

		list = new JList<Patient>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultListModel<>();
		list.setModel(model);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (list.getSelectedIndex() == -1) {
					btnEdit.setEnabled(false);
					btnRemovePatient.setEnabled(false);
					btnAdd.setEnabled(false);
				} else {
					btnEdit.setEnabled(true);
					btnRemovePatient.setEnabled(true);
					btnAdd.setEnabled(true);

					model_1.removeAllElements();
					selectedPatient = list.getSelectedValue();

					VisitDAO visitDAO = new VisitDAO(dbConnection);
					List<Visit> visits;
					try {
						visits = visitDAO.getVisits(selectedPatient);
						for (Visit visit : visits) {
							model_1.addElement(visit);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
		});
		scrollPane.setViewportView(list);

		panel_PatientBookings = new JPanel();
		panel_PatientBookings.setToolTipText("Patient bookings");
		panel_PatientBookings.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Patient bookings",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_PatientBookings.setBackground(new Color(51, 51, 51));
		panel_PatientBookings.setBounds(236, 11, 453, 333);
		registrationPanel.add(panel_PatientBookings);
		panel_PatientBookings.setLayout(null);

		list_Bookings = new JList<Visit>();
		list_Bookings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model_1 = new DefaultListModel<>();
		list_Bookings.setBounds(10, 23, 433, 219);
		panel_PatientBookings.add(list_Bookings);
		list_Bookings.setModel(model_1);
		list_Bookings.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (list_Bookings.getSelectedIndex() == -1) {
					btnRemoveBooking.setEnabled(false);
					btnApprove.setEnabled(false);
				} else {
					btnRemoveBooking.setEnabled(true);
					Visit visit = list_Bookings.getSelectedValue();
					if (!visit.getConfirmed())
						btnApprove.setEnabled(true);
					else
						btnApprove.setEnabled(false);
				}
			}
		});

		btnApprove = new JButton("Approve ");
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Visit visit = list_Bookings.getSelectedValue();
				visit.setConfirmed(true);
				VisitDAO visitDAO = new VisitDAO(dbConnection);
				try {
					visitDAO.modifyVisit(visit);
					list_Bookings.setSelectedIndex(-1);
					btnApprove.setEnabled(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnApprove.setEnabled(false);
		btnApprove.setForeground(Color.WHITE);
		btnApprove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApprove.setBackground(new Color(51, 51, 51));
		btnApprove.setBounds(10, 299, 433, 23);
		panel_PatientBookings.add(btnApprove);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Give the name of the doctor", "Adding a new visit",
						JOptionPane.PLAIN_MESSAGE);
				if (name != null) {
					String surname = JOptionPane.showInputDialog(null, "Give the name of the doctor",
							"Dodawanie nowej wizyty", JOptionPane.PLAIN_MESSAGE);

					DoctorDAO doctorDAO = new DoctorDAO(dbConnection);
					Doctor doctor;
					try {
						doctor = doctorDAO.getDoctor(name, surname);

						if (doctor == null)
							JOptionPane.showMessageDialog(null, "No doctor found!", "Error!",
									JOptionPane.ERROR_MESSAGE);
						else
							new VisitPlanner(App.window, doctor, selectedPatient);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btnAdd.setEnabled(false);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.setBackground(new Color(51, 51, 51));
		btnAdd.setBounds(10, 265, 207, 23);
		panel_PatientBookings.add(btnAdd);

		btnRemoveBooking = new JButton("Remove");
		btnRemoveBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitDAO visitDAO = new VisitDAO(dbConnection);
				try {
					visitDAO.deleteVisit(list_Bookings.getSelectedValue());
					refreshReceptionist();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRemoveBooking.setEnabled(false);
		btnRemoveBooking.setForeground(Color.WHITE);
		btnRemoveBooking.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemoveBooking.setBackground(new Color(51, 51, 51));
		btnRemoveBooking.setBounds(236, 265, 207, 23);
		panel_PatientBookings.add(btnRemoveBooking);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 51, 51));
		panel_2.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panel_2.setBounds(10, 11, 216, 44);
		registrationPanel.add(panel_2);
		panel_2.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 11, 196, 22);
		panel_2.add(textField);
		textField.setColumns(10);

		panelDoctor = new JPanel();
		panelDoctor.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Doctor's Panel", null, panelDoctor, null);
		panelDoctor.setLayout(null);

		panel_DoctorLogin = new JPanel();
		panel_DoctorLogin.setBackground(new Color(51, 51, 51));
		panel_DoctorLogin.setBounds(10, 11, 719, 374);
		panelDoctor.add(panel_DoctorLogin);
		panel_DoctorLogin.setLayout(null);

		lblDoctorID = new JLabel("Doctor's ID");
		lblDoctorID.setBounds(178, 11, 366, 32);
		panel_DoctorLogin.add(lblDoctorID);
		lblDoctorID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoctorID.setForeground(new Color(255, 255, 255));

		txtDoctorID = new JTextField();
		txtDoctorID.setBounds(178, 54, 366, 32);
		panel_DoctorLogin.add(txtDoctorID);
		txtDoctorID.setColumns(10);

		btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				refreshDoctor(Integer.parseInt(txtDoctorID.getText()));
			}
		});
		btnNewButton_1.setBounds(178, 97, 366, 32);
		panel_DoctorLogin.add(btnNewButton_1);
		btnNewButton_1.setBackground(new Color(51, 51, 51));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblDrName = new JLabel("Name");
		lblDrName.setForeground(Color.WHITE);
		lblDrName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDrName.setBounds(10, 165, 366, 32);
		panel_DoctorLogin.add(lblDrName);

		txtDrName = new JTextField();
		txtDrName.setColumns(10);
		txtDrName.setBounds(10, 199, 699, 32);
		panel_DoctorLogin.add(txtDrName);

		lvlDrSurName = new JLabel("Surname");
		lvlDrSurName.setForeground(Color.WHITE);
		lvlDrSurName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lvlDrSurName.setBounds(10, 242, 366, 32);
		panel_DoctorLogin.add(lvlDrSurName);

		txtDrSurName = new JTextField();
		txtDrSurName.setColumns(10);
		txtDrSurName.setBounds(10, 275, 699, 32);
		panel_DoctorLogin.add(txtDrSurName);

		btnAddANew = new JButton("Add a new Doctor to the base");
		btnAddANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addNewDoctor();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddANew.setForeground(Color.WHITE);
		btnAddANew.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddANew.setBackground(new Color(51, 51, 51));
		btnAddANew.setBounds(10, 318, 699, 32);
		panel_DoctorLogin.add(btnAddANew);

		panel = new JPanel();
		panel.setFocusTraversalKeysEnabled(false);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Doctor's hours of admission",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBackground(new Color(51, 51, 51));
		panel.setBounds(357, 11, 372, 374);
		panelDoctor.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		btnAddAdmission = new JButton("Add");
		btnAddAdmission.setForeground(new Color(255, 255, 255));
		btnAddAdmission.setBackground(new Color(51, 51, 51));
		btnAddAdmission.setBounds(10, 340, 170, 23);
		panel.add(btnAddAdmission);

		btnRemove = new JButton("Remove");
		btnRemove.setBackground(new Color(51, 51, 51));
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemove.setBounds(192, 340, 170, 23);
		panel.add(btnRemove);

		scrollPane_Admission = new JScrollPane();
		scrollPane_Admission.setBounds(10, 27, 352, 302);
		panel.add(scrollPane_Admission);

		list_admission = new JList();
		scrollPane_Admission.setViewportView(list_admission);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setBorder(new TitledBorder(null, "Doctor's duty", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panel_1.setBounds(10, 11, 337, 374);
		panelDoctor.add(panel_1);
		panel_1.setLayout(null);

		lblStartDuty = new JLabel("Start: ");
		lblStartDuty.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStartDuty.setForeground(new Color(255, 255, 255));
		lblStartDuty.setBounds(10, 23, 317, 27);
		panel_1.add(lblStartDuty);

		lblEnd = new JLabel("End: ");
		lblEnd.setForeground(Color.WHITE);
		lblEnd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnd.setBounds(10, 61, 317, 27);
		panel_1.add(lblEnd);

		lblRoomNumber = new JLabel("Room number: ");
		lblRoomNumber.setForeground(Color.WHITE);
		lblRoomNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRoomNumber.setBounds(10, 99, 317, 27);
		panel_1.add(lblRoomNumber);
		panel_1.setVisible(false);
	}

	private void registerNewPatient() throws SQLException {

		if (textField_Forename.getText().isEmpty() || textField_Surname.getText().isEmpty()
				|| textField_Date.getText().isEmpty() || textField_ID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "All empty fields must be filled out");
			return;
		}
		String name = textField_Forename.getText();
		String surname = textField_Surname.getText();

		try {
			PatientDAO patientDAO = new PatientDAO(dbConnection);
			patientDAO.addPatient(name, surname);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		textField_Forename.setText(null);
		textField_Surname.setText(null);
		textField_Date.setText(null);
		textField_ID.setText(null);
		JOptionPane.showMessageDialog(this, "A new patient has been added to the database");
		refreshReceptionist();
	}

	private void addNewDoctor() throws SQLException {

		if (txtDrName.getText().isEmpty() || txtDrSurName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "All empty fields must be filled out");
			return;
		}
		DoctorDAO doctorDAO = new DoctorDAO(dbConnection);
		try {
			doctorDAO.addDoctor(txtDrName.getText(), txtDrSurName.getText());
			txtDrName.setText(null);
			txtDrSurName.setText(null);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		JOptionPane.showMessageDialog(this, "A new Doctor has been added to the database");
	}

	private void editPatient() throws SQLException {

		Patient patient = list.getSelectedValue();
		String name = JOptionPane.showInputDialog("Enter Patient's forename ", patient.getName());
		if (name != null) {
			String surname = JOptionPane.showInputDialog("Enter patient's surname", patient.getSurname());
			if (surname != null) {
				patient.setName(name);
				patient.setSurname(surname);
				PatientDAO patientDAO = new PatientDAO(dbConnection);
				patientDAO.modifyPatient(patient);
				refreshReceptionist();
			}
		}

	}

	private void refreshDoctor(int i) {

		panel_DoctorLogin.setVisible(false);
		panel.setVisible(true);
		panel_1.setVisible(true);
	}

	private void removePatient() throws SQLException {

		Patient patient = list.getSelectedValue();
		PatientDAO patientDAO = new PatientDAO(dbConnection);
		patientDAO.deletePatient(patient);
		refreshReceptionist();
	}

	public void refreshReceptionist() throws SQLException {

		PatientDAO patientDAO = new PatientDAO(dbConnection);
		model.removeAllElements();
		String filter = textField.getText();
		List<Patient> patients = patientDAO.getPatients(filter);
		for (Patient patient : patients) {
			model.addElement(patient);
		}

		if (model_1 != null)
			model_1.removeAllElements();

		this.repaint();

	}

}
