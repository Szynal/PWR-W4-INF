package core;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import interfaces.IBramka;
import interfaces.ICentrala;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * 
 * @author Pawel Szynal 226026
 *
 */
public class Bramka implements IBramka, Serializable {

	private static final long serialVersionUID = -9031940404394611336L;
	private JFrame frmBramka;
	private JButton btnStart;
	private JButton btnStop;
	private JButton btnEntrance;
	private JButton btnExit;

	private int ID;

	private Registry registry;
	private ICentrala icentrala;
	private IBramka ibramka;

	private int entrance, exit;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bramka window = new Bramka();
					window.frmBramka.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int[] getStatystyka(Date pocz, Date kon) throws RemoteException {
		int[] statystyka = { entrance, exit };
		return statystyka;
	}

	@Override
	public int getNumer() throws RemoteException {
		return ID;
	}

	@Override
	public boolean zamknijBramke() throws RemoteException {
		if (btnStop.isEnabled()) {
			frmBramka.setTitle("Bramka");
			btnStop.setEnabled(false);
			btnStart.setEnabled(true);
			btnEntrance.setEnabled(false);
			btnExit.setEnabled(false);
			return true;
		} else
			return false;
	}

	public Bramka() {

		try {
			String address = JOptionPane.showInputDialog("Podaj adres IP:");
			int port = Integer.parseInt(JOptionPane.showInputDialog("Podaj port:"));

			registry = LocateRegistry.getRegistry(address, port);
			icentrala = (ICentrala) registry.lookup("Centrala");
			ibramka = (IBramka) UnicastRemoteObject.exportObject(this, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		initialize();
	}

	private void initialize() {
		frmBramka = new JFrame();
		frmBramka.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\szyna\\Desktop\\Documents\\PWR-W4-INF\\Programowanie w j\u0119zyku Java\\lab1\\RMI\\img\\pwr.jpg"));
		frmBramka.setTitle("Bramka");
		frmBramka.setBounds(100, 100, 204, 120);
		frmBramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnStart = new JButton("Start");
		btnStart.setBounds(10, 11, 78, 23);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = -1;
				try {
					x = icentrala.zarejestrujBramke(ibramka);
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				if (x >= 0) {
					ID = x;
					frmBramka.setTitle("Bramka | ID: " + Integer.toString(ID));
					btnStart.setEnabled(false);
					btnStop.setEnabled(true);
					btnEntrance.setEnabled(true);
					btnExit.setEnabled(true);
				}
			}
		});
		frmBramka.getContentPane().setLayout(null);
		frmBramka.getContentPane().add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.setBounds(98, 11, 78, 23);
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean done = false;
				try {
					done = icentrala.wyrejestrujBramke(ID);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (done) {
					frmBramka.setTitle("Bramka");
					btnStop.setEnabled(false);
					btnStart.setEnabled(true);
					btnEntrance.setEnabled(false);
					btnExit.setEnabled(false);
				}
			}
		});
		frmBramka.getContentPane().add(btnStop);

		btnEntrance = new JButton("Entrance");
		btnEntrance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				++entrance;
			}
		});
		btnEntrance.setBounds(10, 45, 78, 23);
		btnEntrance.setEnabled(false);
		frmBramka.getContentPane().add(btnEntrance);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				++exit;
			}
		});
		btnExit.setBounds(98, 45, 76, 23);
		btnExit.setEnabled(false);
		frmBramka.getContentPane().add(btnExit);

		frmBramka.addWindowListener(new WindowAdapter() {

			@Override

			public void windowClosing(WindowEvent e) {
				if (btnStop.isEnabled()) {
					try {
						icentrala.wyrejestrujBramke(ID);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

}
