package core;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import interfaces.IBramka;
import interfaces.ICentrala;
import interfaces.IMonitor;

import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Font;

/**
 * 
 * @author Pawel Szynal 226026
 *
 */
public class Centrala implements ICentrala, Serializable {

	private JFrame app;

	private JTable table;
	private JButton btnStop;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private static final long serialVersionUID = 1411280806053515502L;
	private int id = 0;
	private IMonitor monitor;

	private ArrayList<Object> registeredGates = new ArrayList<Object>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Centrala central = new Centrala();
					central.app.setVisible(true);
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

		JFrameInitialize();
		initialize();
		JMenuInitialize();
	}

	private void JFrameInitialize() {
		try {
			app = new JFrame();
			app.getContentPane().setBackground(new Color(51, 51, 51));
			BufferedImage appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			app.setIconImage(appIcon);
			app.setTitle("Central App");
			app.setBounds(100, 100, 400, 400);
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.getContentPane().setLayout(null);
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
							"http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/index.html");
				}
			});
			mntmAuthor = new JMenuItem("Author");
			mnNewMenu.add(mntmAuthor);
			mntmAuthor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Paweł Szynal\n226026");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 364, 271);
		app.getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		btnStop = new JButton("Stop");
		btnStop.setForeground(new Color(255, 255, 255));
		btnStop.setBackground(new Color(51, 51, 51));
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stop();
			}
		});
		btnStop.setBounds(10, 295, 364, 34);
		app.getContentPane().add(btnStop);
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