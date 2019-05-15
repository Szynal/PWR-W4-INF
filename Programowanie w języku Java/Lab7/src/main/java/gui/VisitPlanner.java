package gui;

import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.Clinic;
import core.Doctor;
import core.Patient;
import dao.ClinicDAO;
import dao.DbConnection;
import dao.VisitDAO;

public class VisitPlanner {

	private JFrame frame;
	private JTextField txtDate;
	private JButton btnSzukaj;
	private JList<Clinic> list;
	private DefaultListModel<Clinic> model;
	private JScrollPane scrollPane;
	private JButton btnOrder;

	private MainFrame window;
	private Doctor doctor;
	private Patient patient;
	private String date;

	public VisitPlanner(MainFrame window, Doctor doctor, Patient patient) {
		this.window = window;
		this.doctor = doctor;
		this.patient = patient;

		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Podaj dat\u0119",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(109, 37, 109, 43);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		txtDate = new JTextField();
		txtDate.setBounds(6, 16, 86, 20);
		panel.add(txtDate);
		txtDate.setColumns(10);

		btnSzukaj = new JButton("Szukaj");
		btnSzukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					date = txtDate.getText();
					DbConnection dbConnection = new DbConnection();
					ClinicDAO clinicDAO = new ClinicDAO(dbConnection);
					List<Clinic> admissions = clinicDAO.getAdmissionsByDate(date, doctor);
					model.removeAllElements();
					for (Clinic admission : admissions) {
						model.addElement(admission);
					}
				} catch (IllegalArgumentException | SQLException e1) {

				}
			}
		});
		btnSzukaj.setBounds(230, 48, 89, 23);
		frame.getContentPane().add(btnSzukaj);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 104, 338, 86);
		frame.getContentPane().add(scrollPane);

		list = new JList<Clinic>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (list.getSelectedIndex() == -1)
					btnOrder.setEnabled(false);
				else
					btnOrder.setEnabled(true);
			}
		});
		model = new DefaultListModel<>();
		list.setModel(model);
		scrollPane.setViewportView(list);

		btnOrder = new JButton("Rezerwuj");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					DbConnection dbConnection = new DbConnection();
					VisitDAO visitDAO = new VisitDAO(dbConnection);
					visitDAO.order(list.getSelectedValue(), patient, doctor, date);
					window.refreshReceptionist();
					frame.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOrder.setEnabled(false);
		btnOrder.setBounds(41, 205, 338, 23);
		frame.getContentPane().add(btnOrder);
	}
}
