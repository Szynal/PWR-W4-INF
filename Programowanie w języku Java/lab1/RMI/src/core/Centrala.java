package core;

import java.awt.EventQueue;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import interfaces.IBramka;
import interfaces.ICentrala;
import interfaces.IMonitor;

import javax.swing.ListSelectionModel;
import java.awt.Toolkit;

/**
 * 
 * @author Pawel Szynal 226026
 *
 */
public class Centrala implements ICentrala, Serializable {

	private JTable table;
	private JButton btnStop;
	private JFrame gui;

	private static final long serialVersionUID = 1411280806053515502L;
	private int id = 0;
	private IMonitor monitor;

	private ArrayList<Object> registeredGates = new ArrayList<Object>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Centrala window = new Centrala();
					window.initialize();
					window.gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public int zarejestrujBramke(Object bramka) throws RemoteException {

		boolean status = registeredGates.add((IBramka) bramka);

		if (!status) {
			return -1;
		}

		if (monitor != null) {
			monitor.koniecznaAktualizacja();
		}

		UpdateTable();
		btnStop.setEnabled(true);
		return id++;
	}

	@Override
	public boolean wyrejestrujBramke(int nrBramki) throws RemoteException {
		for (Object b : registeredGates) {
			IBramka ibramka = (IBramka) b;
			int ID = ibramka.getNumer();
			if (ID == nrBramki) {
				registeredGates.remove(ibramka);
				if (registeredGates.size() == 0)
					btnStop.setEnabled(false);
				if (monitor != null)
					monitor.koniecznaAktualizacja();
				UpdateTable();
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<Object> getZarejestrowaneBramki() throws RemoteException {
		return registeredGates;
	}

	@Override
	public void zarejestrujMonitor(Object o) throws RemoteException {
		monitor = (IMonitor) o;
	}

	@Override
	public void wyrejestrujMonitor() throws RemoteException {
		monitor = null;
	}

	@Override
	public Object getMonitor() throws RemoteException {
		return monitor;
	}

	public Centrala() {
		int port = Integer.parseInt(JOptionPane.showInputDialog("Podaj port:"));
		try {

			Registry registry = LocateRegistry.createRegistry(port);
			ICentrala icentrala = (ICentrala) UnicastRemoteObject.exportObject(this, 0);

			registry.rebind("Centrala", icentrala);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		monitor = null;
	}

	private void initialize() {
		gui = new JFrame();
		gui.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\szyna\\Desktop\\Documents\\PWR-W4-INF\\Programowanie w j\u0119zyku Java\\lab1\\RMI\\img\\pwr.jpg"));
		gui.setTitle("Centrala");
		gui.setBounds(100, 100, 214, 363);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 183, 261);
		gui.getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stop();
			}
		});
		btnStop.setBounds(10, 283, 183, 34);
		gui.getContentPane().add(btnStop);
	}

	private void UpdateTable() {
		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {

					Thread.sleep(100);

					String col[] = { "ID" };
					DefaultTableModel model = new DefaultTableModel(col, 0);

					for (Object gate : registeredGates) {

						IBramka ibramka = (IBramka) gate;
						try {

							int ID = ibramka.getNumer();
							Object[] stat = { ID };
							model.addRow(stat);

						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
					table.setModel(model);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void Stop() {
		int index = table.getSelectedRow();
		IBramka ibramka = (IBramka) registeredGates.get(index);
		registeredGates.remove(ibramka);

		if (registeredGates.size() == 0) {
			btnStop.setEnabled(false);
		}

		try {
			ibramka.zamknijBramke();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		UpdateTable();

		if (monitor != null)
			try {
				monitor.koniecznaAktualizacja();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	}

}