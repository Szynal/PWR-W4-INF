package core;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import interfaces.IBramka;
import interfaces.ICentrala;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

/**
 * 
 * @author Pawel Szynal 226026
 *
 */
public class Bramka implements IBramka, Serializable {

	private static final long serialVersionUID = -9031940404394611336L;
	private JFrame app;
	private BufferedImage appIcon = null;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

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
					window.app.setVisible(true);
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
			app.setTitle("Bramka");
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

		JFrameInitialize();
		JMenuInitialize();
		JButtonInitialize();
		initialize();
	}

	private void JFrameInitialize() {
		try {
			app = new JFrame();
			appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			app.setIconImage(appIcon);
			app.getContentPane().setBackground(new Color(51, 51, 51));
			app.setTitle("Gate App");
			app.setBounds(100, 100, 250, 250);
			app.setBounds(100, 100, 250, 250);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void JButtonInitialize() {
		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStart.setForeground(new Color(255, 255, 255));
		btnStart.setBackground(new Color(51, 51, 51));
		btnStart.setBounds(10, 86, 95, 45);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		app.getContentPane().setLayout(null);
		app.getContentPane().add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStop.setForeground(new Color(255, 255, 255));
		btnStop.setBackground(new Color(51, 51, 51));
		btnStop.setBounds(129, 86, 95, 45);
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		app.getContentPane().add(btnStop);

		btnEntrance = new JButton("Entrance");
		btnEntrance.setForeground(new Color(255, 255, 255));
		btnEntrance.setBackground(new Color(51, 51, 51));
		btnEntrance.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEntrance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				++entrance;
			}
		});
		btnEntrance.setBounds(10, 142, 95, 45);
		btnEntrance.setEnabled(false);
		app.getContentPane().add(btnEntrance);

		btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(51, 51, 51));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				++exit;
			}
		});
		btnExit.setBounds(129, 142, 95, 45);
		btnExit.setEnabled(false);
		app.getContentPane().add(btnExit);
	}

	private void initialize() {
		app.addWindowListener(new WindowAdapter() {

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
							"http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/Zadania/ZadRMI_01.txt");
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

	private void start() {
		int x = -1;
		try {
			x = icentrala.zarejestrujBramke(ibramka);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (x >= 0) {
			ID = x;
			app.setTitle("Bramka | ID: " + Integer.toString(ID));
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			btnEntrance.setEnabled(true);
			btnExit.setEnabled(true);
		}
	}

	private void stop() {
		boolean done = false;
		try {
			done = icentrala.wyrejestrujBramke(ID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (done) {
			app.setTitle("Bramka");
			btnStop.setEnabled(false);
			btnStart.setEnabled(true);
			btnEntrance.setEnabled(false);
			btnExit.setEnabled(false);

			btnStop.setFocusable(false);
			btnStart.setFocusable(false);
			btnEntrance.setFocusable(false);
			btnExit.setFocusable(false);
		}
	}
}
