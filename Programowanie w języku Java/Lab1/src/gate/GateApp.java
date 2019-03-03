package gate;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GateApp {

	private JFrame frmGateApp;
	private JTextField textLoadGate;

	private ArrayList<Gate> ListOfGates;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GateApp window = new GateApp();
					window.frmGateApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GateApp() {
		ListOfGates = new ArrayList<Gate>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGateApp = new JFrame();
		frmGateApp.setResizable(false);
		frmGateApp.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\szyna\\Desktop\\Documents\\Java-OLD\\Java(Lab5)\\img\\pwr.png"));
		frmGateApp.setTitle("GateApplication");
		frmGateApp.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 6));
		frmGateApp.setBounds(100, 100, 600, 415);
		frmGateApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGateApp.getContentPane().setLayout(null);

		JButton btnAddNewGate = new JButton("Add a new gate");

		btnAddNewGate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAddNewGate.setBounds(163, 82, 120, 24);
		frmGateApp.getContentPane().add(btnAddNewGate);

		JButton btnRemoveGate = new JButton("Remove gate");

		btnRemoveGate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRemoveGate.setBounds(293, 83, 120, 23);
		frmGateApp.getContentPane().add(btnRemoveGate);

		JButton btnLoad = new JButton("Load Gate");

		btnLoad.setBounds(23, 131, 260, 23);
		frmGateApp.getContentPane().add(btnLoad);

		JButton btnLoadSampleListOfGates = new JButton("Load the sample list of gates");
		btnLoadSampleListOfGates.setBounds(23, 349, 260, 23);
		frmGateApp.getContentPane().add(btnLoadSampleListOfGates);

		JButton btnUpdateGate = new JButton("Update gate");

		btnUpdateGate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnUpdateGate.setBounds(23, 263, 260, 23);
		frmGateApp.getContentPane().add(btnUpdateGate);

		JButton btnDisplayTheList = new JButton("Display the list of gates");

		btnDisplayTheList.setBounds(23, 315, 260, 23);
		frmGateApp.getContentPane().add(btnDisplayTheList);

		textLoadGate = new JTextField();
		textLoadGate.setBounds(23, 82, 130, 24);
		frmGateApp.getContentPane().add(textLoadGate);
		textLoadGate.setColumns(10);

		JLabel lblGateNumber02 = new JLabel("Gate number");
		lblGateNumber02.setBounds(23, 57, 130, 14);
		frmGateApp.getContentPane().add(lblGateNumber02);

		JLabel lblGateManager = new JLabel("Gate Manager");
		lblGateManager.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGateManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblGateManager.setBounds(10, 11, 564, 23);
		frmGateApp.getContentPane().add(lblGateManager);

		JLabel lblStateOfGates = new JLabel("State of gate");
		lblStateOfGates.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateOfGates.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblStateOfGates.setBounds(10, 160, 281, 14);
		frmGateApp.getContentPane().add(lblStateOfGates);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(293, 131, 281, 241);
		frmGateApp.getContentPane().add(scrollPane);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

		JRadioButton rdbtnGateStateTurnedOn = new JRadioButton("Turned On");
		rdbtnGateStateTurnedOn.setBounds(23, 181, 120, 23);
		frmGateApp.getContentPane().add(rdbtnGateStateTurnedOn);

		JRadioButton rdbtnGateStateTurnedOff = new JRadioButton("Turned Off");
		rdbtnGateStateTurnedOff.setBounds(23, 207, 109, 23);
		frmGateApp.getContentPane().add(rdbtnGateStateTurnedOff);

		JRadioButton rdbtnGateStateBroken = new JRadioButton("Broken");
		rdbtnGateStateBroken.setBounds(23, 233, 109, 23);
		frmGateApp.getContentPane().add(rdbtnGateStateBroken);

		btnAddNewGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewGate(textPane);
			}
		});

		btnRemoveGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveGate(textPane);
			}
		});

		btnDisplayTheList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DisplayTheList(textPane);
			}
		});

		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnUpdateGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

	}

	public ArrayList<Gate> getListOfGates() {
		return ListOfGates;
	}

	public void setListOfGates(ArrayList<Gate> listOfGates) {
		ListOfGates = listOfGates;
	}

	public void AddNewGate(JTextPane textPane) {
		if (textLoadGate != null) {

			try {
				Gate newGate = new Gate(Integer.parseInt(textLoadGate.getText()));
				ListOfGates.add(newGate);
				textPane.setText("Gate number " + textLoadGate.getText() + " has been added");
				textLoadGate.setText(null);
			} catch (Exception addNewGateException) {
				textPane.setText(addNewGateException.toString());
				textLoadGate.setText(null);
			}

		}
	}

	public void RemoveGate(JTextPane textPane) {
		if (textLoadGate != null && ListOfGates != null) {

			try {
				if (ListOfGates.isEmpty()) {
					textPane.setText("List is empty");
					textLoadGate.setText(null);
				} else {
					for (Gate gate : ListOfGates) {
						if (gate.getNumer() == Integer.parseInt(textLoadGate.getText())) {
							ListOfGates.remove(gate);
							DisplayTheList(textPane);
							textLoadGate.setText(null);
						}
					}
				}

			} catch (Exception addNewGateException) {
				textLoadGate.setText(null);
			}

		}
	}

	public void DisplayTheList(JTextPane textPane) {
		if (ListOfGates != null) {

			try {
				if (ListOfGates.isEmpty()) {
					textPane.setText("List is empty");
					textLoadGate.setText(null);
				} else {
					textPane.setText(null);
					for (Gate gate : ListOfGates) {

						textPane.setText(textPane.getText() + "Gate number " + gate.getNumer() + "  State: "
								+ gate.getState().toString() + "Counter " + gate.getTransitionCounter() + "\n\n");
					}
				}

			} catch (Exception addNewGateException) {
				textPane.setText(addNewGateException.toString());
				textLoadGate.setText(null);
			}

		}
	}

}
