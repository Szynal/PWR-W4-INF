package lab08;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import lab08.soap.interfaces.INewspaperMachineService;
import java.awt.Color;

public class Monitor {

	private JFrame frmLabSoap;
	private JTabbedPane tabbedPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private javax.xml.ws.Endpoint endpoint;
	private MonitorService monitorService;

	private static int MachinesPortSift = 10000;
	private static int MonitorPort = 9000;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitor window = new Monitor();
					window.frmLabSoap.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Monitor() {
		monitorService = new MonitorService();
		endpoint = Endpoint.create(monitorService);
		endpoint.publish("http://localhost:" + MonitorPort + "/monitor");
		initialize();
		jMenuInitialize();
		run();
	}

	private void jMenuInitialize() {
		try {
			menuBar = new JMenuBar();
			frmLabSoap.setJMenuBar(menuBar);
			mnNewMenu = new JMenu("About");
			menuBar.add(mnNewMenu);
			mntmTask = new JMenuItem("Program description");
			mnNewMenu.add(mntmTask);
			mntmTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "");
				}
			});
			mntmAuthor = new JMenuItem("Author");
			mnNewMenu.add(mntmAuthor);
			mntmAuthor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Pawel Szynal\n226026");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		frmLabSoap = new JFrame();
		frmLabSoap.setBackground(new Color(51, 51, 51));
		frmLabSoap.setResizable(false);
		frmLabSoap.setTitle("Lab08 - SOAP");
		frmLabSoap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLabSoap.setBounds(100, 100, 500, 300);
		frmLabSoap.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// stop
				endpoint.stop();
			}
		});

		frmLabSoap.getContentPane().setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(51, 51, 51));
		frmLabSoap.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}

	private void run() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					refreshMachines();
				} catch (InterruptedException | MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void refreshMachines() throws InterruptedException, MalformedURLException {
		while (true) {
			Thread.sleep(5000);

			List<JPanel> panels = new ArrayList<JPanel>();
			List<Integer> machinesId = monitorService.getVendingMachinesIds();

			for (Integer id : machinesId) {
				INewspaperMachineService vendingMachineService = createVendingMachineService(id);

				DefaultTableModel model = createTableModelWithProducts(vendingMachineService);

				panels.add(makeNewTablePanel(model));
			}
			refreshTabbedPaneWithPanels(panels, machinesId);
		}
	}

	private INewspaperMachineService createVendingMachineService(Integer id) throws MalformedURLException {
		Service webVendingMachineService = initWebService(id);

		INewspaperMachineService vendingMachineService = webVendingMachineService
				.getPort(INewspaperMachineService.class);
		return vendingMachineService;
	}

	private Service initWebService(Integer id) throws MalformedURLException {
		URL vendingMachineURL = createUrl(id);
		QName qName = createQName();
		Service webVendingMachineService = Service.create(vendingMachineURL, qName);

		return webVendingMachineService;
	}

	private URL createUrl(Integer id) throws MalformedURLException {
		int port = MachinesPortSift + id;
		String url = "http://localhost:" + port + "/newspaperMachine?wsdl";
		URL wsdlURL = new URL(url);
		return wsdlURL;
	}

	private QName createQName() {
		return new QName("http://lab08/", "NewspaperMachineServiceService");
	}

	private DefaultTableModel createTableModelWithProducts(INewspaperMachineService vendingMachineService) {
		DefaultTableModel model = createTableModel();

		int amount = vendingMachineService.getProductMenuAmount();

		for (int i = 0; i < amount; ++i) {
			int titleAmount = vendingMachineService.getProductAmount(i);
			String title = vendingMachineService.getProduct(i);
			String[] row = new String[] { title, Integer.toString(titleAmount) };
			model.addRow(row);
		}
		return model;
	}

	private DefaultTableModel createTableModel() {
		DefaultTableModel model;
		model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Available");
		return model;
	}

	private JPanel makeNewTablePanel(DefaultTableModel model) {
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		JTable table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		return panel;
	}

	private void refreshTabbedPaneWithPanels(List<JPanel> panels, List<Integer> machinesId) {
		int tabsCount = panels.size();
		tabbedPane.removeAll();
		for (int i = 0; i < tabsCount; ++i) {
			JPanel tab = panels.get(i);
			tabbedPane.addTab("Newspaper Machine " + machinesId.get(i), null, tab, null);
		}
	}
}
