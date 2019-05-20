package lab07;


import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VisitPlanner {

	private JFrame frame;
	private JTextField txtDate;
	private JButton btnSzukaj;
	private JList<Admission> list;
	private DefaultListModel<Admission> model;
	private JScrollPane scrollPane;
	private JButton btnOrder;
	
	private Window window;
	private Doctor doctor;
	private Patient patient;
	private String date;

	/**
	 * Create the application.
	 */
	public VisitPlanner(Window window, Doctor doctor, Patient patient) {
		this.window = window;
		this.doctor = doctor;
		this.patient = patient;
		
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Podaj dat\u0119", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
				try{
					date = txtDate.getText();
					
					List<Admission> admissions = window.conn.getAdmissionsByDate(date, doctor);
					model.removeAllElements();
					for (Admission admission : admissions) {
						model.addElement(admission);
					}
				}
				catch(IllegalArgumentException e1) {
					
				}
			}
		});
		btnSzukaj.setBounds(230, 48, 89, 23);
		frame.getContentPane().add(btnSzukaj);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 104, 338, 86);
		frame.getContentPane().add(scrollPane);
		
		list = new JList<Admission>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(list.getSelectedIndex() == -1)
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
				window.conn.order(list.getSelectedValue(), patient, doctor, date);
				window.refreshReceptionist();
				frame.dispose();
			}
		});
		btnOrder.setEnabled(false);
		btnOrder.setBounds(41, 205, 338, 23);
		frame.getContentPane().add(btnOrder);
	}
}
