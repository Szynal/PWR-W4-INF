package core;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
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
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import interfaces.IBramka;
import interfaces.ICentrala;
import interfaces.IMonitor;
import java.awt.Color;

/**
 * @author Pawel Szynal 226026
 */
public class Monitor implements IMonitor {

	private JFrame app;
	private JTable table;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private Registry registry;
	private ICentrala icentrala;
	private IMonitor imonitor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitor monitor = new Monitor();
					monitor.app.setVisible(true);
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

		JFrameInitialize();
		JMenuInitialize();
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

	private void JFrameInitialize() {
		try {
			app = new JFrame();
			app.getContentPane().setBackground(new Color(51, 51, 51));
			BufferedImage appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			app.setIconImage(appIcon);
			app.setTitle("Monitor App");
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
					JOptionPane.showMessageDialog(null, "Pawe³ Szynal\n226026");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 364, 318);
		app.getContentPane().add(scrollPane);
		app.addWindowListener(new WindowAdapter() {
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
