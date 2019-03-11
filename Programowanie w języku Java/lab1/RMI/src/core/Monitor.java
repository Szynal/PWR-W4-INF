package core;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import interfaces.IBramka;
import interfaces.ICentrala;
import interfaces.IMonitor;
import java.awt.Toolkit;

/**
 * 
 * @author Pawel Szynal 226026
 *
 */
public class Monitor implements IMonitor {

	private JFrame frame;
	private JTable table;

	private Registry registry;
	private ICentrala icentrala;
	private IMonitor imonitor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitor window = new Monitor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void koniecznaAktualizacja() throws RemoteException {
		Thread thread = new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(200);

					ArrayList<Object> gates = icentrala.getZarejestrowaneBramki();

					String col[] = { "Id", "Entrance", "Exit" };

					DefaultTableModel model = new DefaultTableModel(col, 0);

					for (Object gate : gates) {

						IBramka ibramka = (IBramka) gate;
						int[] statistics = ibramka.getStatystyka(new Date(), new Date());
						int ID = ibramka.getNumer();

						Object[] stat = { ID, statistics[0], statistics[1] };
						model.addRow(stat);

					}
					table.setModel(model);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public Monitor() {

		String address = JOptionPane.showInputDialog("Podaj adres IP");
		int port = Integer.parseInt(JOptionPane.showInputDialog("Podaj port"));

		try {

			registry = LocateRegistry.getRegistry(address, port);
			icentrala = (ICentrala) registry.lookup("Centrala");
			imonitor = (IMonitor) UnicastRemoteObject.exportObject(this, 0);
			icentrala.zarejestrujMonitor(imonitor);

		} catch (Exception e) {
			e.printStackTrace();
		}

		initialize();

		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						koniecznaAktualizacja();
						Thread.sleep(5000);
					}
				} catch (RemoteException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\szyna\\Desktop\\Documents\\PWR-W4-INF\\Programowanie w j\u0119zyku Java\\lab1\\RMI\\img\\pwr.jpg"));
		frame.setTitle("Monitor");
		frame.setBounds(100, 100, 240, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 212, 339);
		frame.getContentPane().add(scrollPane);

		frame.addWindowListener(new WindowAdapter() {

			@Override

			public void windowClosing(WindowEvent e) {
				try {
					icentrala.wyrejestrujMonitor();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
