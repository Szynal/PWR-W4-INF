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

	private JFrame frmGateappo;
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
					window.frmGateappo.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGateappo = new JFrame();
		frmGateappo.setResizable(false);
		frmGateappo.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\szyna\\Desktop\\Documents\\Java-OLD\\Java(Lab5)\\img\\pwr.png"));
		frmGateappo.setTitle("GateApplication");
		frmGateappo.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 6));
		frmGateappo.setBounds(100, 100, 600, 415);
		frmGateappo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGateappo.getContentPane().setLayout(null);

		JButton btnAddNewGate = new JButton("Add a new gate");

		btnAddNewGate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAddNewGate.setBounds(163, 82, 120, 24);
		frmGateappo.getContentPane().add(btnAddNewGate);

		JButton btnRemoveGate = new JButton("Remove gate");
		btnRemoveGate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRemoveGate.setBounds(293, 83, 120, 23);
		frmGateappo.getContentPane().add(btnRemoveGate);

		JButton btnLoad = new JButton("Load Gate");
		btnLoad.setBounds(23, 131, 260, 23);
		frmGateappo.getContentPane().add(btnLoad);

		JButton btnLoadSampleListOfGates = new JButton("Load the sample list of gates");
		btnLoadSampleListOfGates.setBounds(23, 349, 260, 23);
		frmGateappo.getContentPane().add(btnLoadSampleListOfGates);

		JButton btnUpdateGate = new JButton("Update gate");
		btnUpdateGate.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnUpdateGate.setBounds(23, 263, 260, 23);
		frmGateappo.getContentPane().add(btnUpdateGate);

		JButton btnDisplayTheList = new JButton("Display the list of gates");
		btnDisplayTheList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDisplayTheList.setBounds(23, 315, 260, 23);
		frmGateappo.getContentPane().add(btnDisplayTheList);

		textLoadGate = new JTextField();
		textLoadGate.setBounds(23, 82, 130, 24);
		frmGateappo.getContentPane().add(textLoadGate);
		textLoadGate.setColumns(10);

		JLabel lblGateNumber02 = new JLabel("Gate number");
		lblGateNumber02.setBounds(23, 57, 130, 14);
		frmGateappo.getContentPane().add(lblGateNumber02);

		JLabel lblGateManager = new JLabel("Gate Manager");
		lblGateManager.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGateManager.setHorizontalAlignment(SwingConstants.CENTER);
		lblGateManager.setBounds(10, 11, 564, 23);
		frmGateappo.getContentPane().add(lblGateManager);

		JLabel lblStateOfGates = new JLabel("State of gate");
		lblStateOfGates.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateOfGates.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblStateOfGates.setBounds(10, 160, 281, 14);
		frmGateappo.getContentPane().add(lblStateOfGates);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(293, 131, 281, 241);
		frmGateappo.getContentPane().add(scrollPane);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);

		JRadioButton rdbtnGateStateTurnedOn = new JRadioButton("Turned On");
		rdbtnGateStateTurnedOn.setBounds(23, 181, 120, 23);
		frmGateappo.getContentPane().add(rdbtnGateStateTurnedOn);

		JRadioButton rdbtnGateStateTurnedOff = new JRadioButton("Turned Off");
		rdbtnGateStateTurnedOff.setBounds(23, 207, 109, 23);
		frmGateappo.getContentPane().add(rdbtnGateStateTurnedOff);

		JRadioButton rdbtnGateStateBroken = new JRadioButton("Broken");
		rdbtnGateStateBroken.setBounds(23, 233, 109, 23);
		frmGateappo.getContentPane().add(rdbtnGateStateBroken);

		btnAddNewGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewGate(textPane);
			}
		});

	}

	public void AddNewGate(JTextPane textPane) {
		if (textLoadGate != null) {
			try {
				Gate newGate = new Gate(Integer.parseInt(textLoadGate.getText()));
				textPane.setText("Gate number " + textLoadGate.getText() + " has been added");
				textLoadGate.setText(null);
			} catch (Exception addNewGateException) {

			}

		}
	}

	public ArrayList<Gate> getListOfGates() {
		return ListOfGates;
	}

	public void setListOfGates(ArrayList<Gate> listOfGates) {
		ListOfGates = listOfGates;
	}
}
