package lab08;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import lab08.soap.core.Product;
import lab08.soap.interfaces.IMonitorService;

public class NewspaperMachine {
	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;

	private int Id;
	private Endpoint endpoint;
	private NewspaperMachineService machineService;
	private IMonitorService monitorService;
	private int lastSelectedRow = 0;

	private static int MachinesPortSift = 10000;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewspaperMachine window = new NewspaperMachine();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewspaperMachine() {
		initialize();
		try {
			initVendingMachineService();
			registerToMonitor();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		refreshGui();
	}

	private void initialize() {
		initFrame();

		JButton btnBuy = initButtonBuy();

		initScrollPane();

		initTable(btnBuy);
	}

	private void initFrame() {
		frame = new JFrame();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// unregister and stop
				if (monitorService != null) {
					try {
						monitorService.unregister(Id);
					} catch (WebServiceException ex) {
						JOptionPane.showMessageDialog(frame, "Nie znaleziono monitora");
					}
				}
				if (endpoint != null) {
					endpoint.stop();
				}
			}
		});

		frame.setTitle("Newspaper Machine " + Id);
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private JButton initButtonBuy() {
		JButton btnBuy = new JButton("Buy");
		btnBuy.setEnabled(false);

		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					String selectedTitle = (String) model.getValueAt(index, 0);
					machineService.buyProduct(selectedTitle);

					refreshGui();

					int countRow = table.getRowCount();
					if (countRow < 1)
						return;
					table.setRowSelectionInterval(0, index >= countRow ? countRow - 1 : index);
				}
			}
		});
		btnBuy.setBounds(366, 207, 58, 43);
		frame.getContentPane().add(btnBuy);
		return btnBuy;
	}

	private void refreshGui() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Available");
		table.setModel(model);

		Product[] products = machineService.getProducts();

		for (Product product : products) {
			String[] productRow = { product.getName(), Integer.toString(product.getAmount()) };
			model.addRow(productRow);
		}
		this.model = model;
	}

	private void initScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 346, 239);
		frame.getContentPane().add(scrollPane);
	}

	private void initTable(JButton btnBuy) {
		model = new DefaultTableModel();
		table = new JTable(model);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int selected = table.getSelectedRow();
				if (selected >= 0)
					btnBuy.setEnabled(true);
				else
					btnBuy.setEnabled(false);
			}
		});

		scrollPane.setViewportView(table);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void registerToMonitor() throws MalformedURLException {
		try {
			initMonitorService();
			Id = monitorService.register();

			publishVendingMachineService();

		} catch (WebServiceException e) {
			JOptionPane.showMessageDialog(frame, "Blad rejestracji do monitora");
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}

	}

	private void initVendingMachineService() {
		machineService = new NewspaperMachineService();
		machineService.addProduct(new Product("Mars", 8));
		machineService.addProduct(new Product("Snickers", 9));
		machineService.addProduct(new Product("Pepsi Light", 4));
		machineService.addProduct(new Product("RedBull 0.5", 2));
	}

	private void initMonitorService() throws MalformedURLException {
		Service webMonitorService = initWebMonitorService();

		monitorService = webMonitorService.getPort(IMonitorService.class);
	}

	private Service initWebMonitorService() throws MalformedURLException, WebServiceException {
		URL monitorURL = createMonitorUrl();
		QName monitorQName = createMonitorQName();
		Service webMonitorService = Service.create(monitorURL, monitorQName);
		return webMonitorService;
	}

	private QName createMonitorQName() {
		return new QName("http://lab08/", "MonitorServiceService");
	}

	private URL createMonitorUrl() throws MalformedURLException {
		return new URL("http://localhost:9000/monitor?wsdl");
	}

	private void publishVendingMachineService() {
		int port = MachinesPortSift + Id;
		String myURL = "http://localhost:" + port + "/newspaperMachine";
		endpoint = Endpoint.create(machineService);
		endpoint.publish(myURL);
	}
}
