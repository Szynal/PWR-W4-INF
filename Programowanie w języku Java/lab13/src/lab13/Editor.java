package lab13;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Editor {

	private JFrame frame;

	public static String HOST;
	public static String PORT;
	
	BoardMBean boardMBean;
	private JTextField textField;
	private JTextField textField_1;

	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		HOST = args[0];
		PORT = args[1];
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Editor() {
		initialize();
		
		try {
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + HOST + ":" + PORT + "/jmxrmi");
			JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
			MBeanServerConnection mbeanServerConnection = jmxConnector.getMBeanServerConnection();
			// ObjectName should be same as your MBean name
			ObjectName mbeanName = new ObjectName("lab13.Board:type=Controller,name=Board");
			boardMBean = (BoardMBean) MBeanServerInvocationHandler.newProxyInstance(mbeanServerConnection,
					mbeanName, BoardMBean.class, true);
			
			mbeanName = new ObjectName("lab13.Advert:type=Controller,name=advert1");
			AdvertMBean advertMBean = (AdvertMBean) MBeanServerInvocationHandler.newProxyInstance(mbeanServerConnection,
					mbeanName, AdvertMBean.class, false);
			addAdvertTab(advertMBean, "Advert 1");
			
			mbeanName = new ObjectName("lab13.Advert:type=Controller,name=advert2");
			AdvertMBean advertMBean1 = (AdvertMBean) MBeanServerInvocationHandler.newProxyInstance(mbeanServerConnection,
					mbeanName, AdvertMBean.class, false);
			addAdvertTab(advertMBean1, "Advert 2");

			
			mbeanName = new ObjectName("lab13.Advert:type=Controller,name=advert3");
			AdvertMBean advertMBean2 = (AdvertMBean) MBeanServerInvocationHandler.newProxyInstance(mbeanServerConnection,
					mbeanName, AdvertMBean.class, false);
			addAdvertTab(advertMBean2, "Advert 3");
			
		} catch (IOException | MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 627, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Board", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("W\u0141\u0104CZ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardMBean.start();
			}
		});
		btnNewButton.setBounds(152, 80, 129, 63);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("WY\u0141\u0104CZ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardMBean.stop();
			}
		});
		btnNewButton_1.setBounds(327, 80, 129, 63);
		panel.add(btnNewButton_1);
	}
	
	private void addAdvertTab(AdvertMBean advert, String name) {
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab(name, null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Has\u0142o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(15, 32, 581, 89);
		panel_1.add(panel_2);
		
		textField = new JTextField();
		textField.setText(advert.getText());
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD, 30));
		textField.setColumns(10);
		textField.setBounds(6, 16, 565, 66);
		panel_2.add(textField);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Czas wy\u015Bwietlania has\u0142a", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(40, 131, 166, 43);
		panel_1.add(panel_3);
		
		textField_1 = new JTextField();
		textField_1.setText(String.valueOf(advert.getDuration()));
		textField_1.setColumns(10);
		textField_1.setBounds(6, 16, 130, 20);
		panel_3.add(textField_1);
		
		JButton button = new JButton("Zapisz has\u0142o");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				int dur = advert.getDuration();
				try {
					dur = Integer.parseInt(textField_1.getText());
					if(dur <= 200 || dur >= 30000) throw new NumberFormatException();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "B³¹d", "Niepoprawna d³ugoœæ wyœwietlania has³a.", JOptionPane.ERROR_MESSAGE);
				}
				advert.setText(text);
				advert.setDuration(dur);
			}
		});
		button.setBounds(215, 144, 113, 23);
		panel_1.add(button);
	}
}
