package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
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
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8255412204648907508L;

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
	private JList list_Bookings;
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

	public MainFrame() {
		initialize();
		JMenuInitialize();
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

		tabbedPaneRegistration = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneRegistration.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPaneRegistration.setBackground(new Color(255, 255, 255));
		tabbedPaneRegistration.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabbedPaneRegistration.setBounds(10, 11, 719, 378);
		panelRegistration.add(tabbedPaneRegistration);

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
				registerNewPatient();
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
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBackground(new Color(51, 51, 51));
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(10, 282, 216, 23);
		registrationPanel.add(btnEdit);
		
		JButton btnRemovePatient = new JButton("Remove patient");
		btnRemovePatient.setForeground(new Color(255, 255, 255));
		btnRemovePatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemovePatient.setBackground(new Color(51, 51, 51));
		btnRemovePatient.setBounds(10, 310, 216, 23);
		registrationPanel.add(btnRemovePatient);
		
		panel_PatientBookings = new JPanel();
		panel_PatientBookings.setToolTipText("Patient bookings");
		panel_PatientBookings.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Patient bookings", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_PatientBookings.setBackground(new Color(51, 51, 51));
		panel_PatientBookings.setBounds(236, 11, 453, 333);
		registrationPanel.add(panel_PatientBookings);
		panel_PatientBookings.setLayout(null);
		
		list_Bookings = new JList();
		list_Bookings.setBounds(10, 23, 433, 231);
		panel_PatientBookings.add(list_Bookings);
		
		JButton btnBookingsRemove = new JButton("Remove");
		btnBookingsRemove.setForeground(Color.WHITE);
		btnBookingsRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBookingsRemove.setBackground(new Color(51, 51, 51));
		btnBookingsRemove.setBounds(236, 265, 207, 23);
		panel_PatientBookings.add(btnBookingsRemove);
		
		btnApprove = new JButton("Approve ");
		btnApprove.setForeground(Color.WHITE);
		btnApprove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApprove.setBackground(new Color(51, 51, 51));
		btnApprove.setBounds(10, 299, 433, 23);
		panel_PatientBookings.add(btnApprove);
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.setBackground(new Color(51, 51, 51));
		btnAdd.setBounds(10, 265, 207, 23);
		panel_PatientBookings.add(btnAdd);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 51, 51));
		panel_2.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
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
		
		lblDoctorID = new JLabel("Doctor's ID");
		lblDoctorID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDoctorID.setForeground(new Color(255, 255, 255));
		lblDoctorID.setBounds(185, 106, 366, 32);
		panelDoctor.add(lblDoctorID);
		
		txtDoctorID = new JTextField();
		txtDoctorID.setBounds(185, 151, 366, 32);
		panelDoctor.add(txtDoctorID);
		txtDoctorID.setColumns(10);
		
		btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.setBackground(new Color(51, 51, 51));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(185, 194, 366, 32);
		panelDoctor.add(btnNewButton_1);
		
		panel = new JPanel();
		panel.setFocusTraversalKeysEnabled(false);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Doctor's hours of admission", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBackground(new Color(51, 51, 51));
		panel.setBounds(357, 11, 372, 374);
		panelDoctor.add(panel);
		panel.setLayout(null);
		
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
		panel_1.setBorder(new TitledBorder(null, "Doctor's duty", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
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
	}

	private void registerNewPatient() {

		String name = textField_Forename.getText();
		String surname = textField_Surname.getText();
		// conn.addPatient(name, surname);
	}
}
