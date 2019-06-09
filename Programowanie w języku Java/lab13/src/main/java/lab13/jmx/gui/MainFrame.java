package lab13.jmx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8255412204648907508L;

	// GUI
	private JTabbedPane tabbedPane;
	private JPanel panelJMX;
	private JPanel panel_AdvertisementEditor;
	private JPanel panel_Output;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private JTextField textField_Id;
	private JTextArea textArea_Description;
	private JSpinner spinner_price;

	private JLabel lblofferID;
	private JLabel lblTime;
	private JLabel lblDescription;

	private JScrollPane scrollPaneNewOffer;
	private JComboBox<String> comboBox_Data;
	private JButton btnNewOffer;
	private JLabel lblms;
	private JLabel lblPicture;
	private JLabel lblNotificationIDValue;
	private JLabel lblNotificationTimeValue;

	public MainFrame() {
		try {
			guiInit();
			JMenuInitialize();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void JFrameInitialize() {
		try {

			this.setBackground(new Color(51, 51, 51));
			this.setTitle("Lab13 - JMX");
			this.setForeground(new Color(0, 0, 0));
			this.getContentPane().setBackground(new Color(51, 51, 51));
			this.setResizable(false);
			BufferedImage appIcon = ImageIO.read(getClass().getClassLoader().getResource("pwr.jpg"));
			this.setIconImage(appIcon);
			this.setBounds(100, 100, 750, 518);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void JMenuInitialize() {
		try {
			menuBar = new JMenuBar();
			this.setJMenuBar(menuBar);
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

	private void guiInit() {
		JFrameInitialize();
		setBounds(100, 100, 750, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabbedPane.setBounds(0, 0, 744, 430);
		getContentPane().add(tabbedPane);

		panel_AdvertisementEditor = new JPanel();
		panel_AdvertisementEditor.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Advertisement Editor", null, panel_AdvertisementEditor, null);
		panel_AdvertisementEditor.setLayout(null);

		lblofferID = new JLabel("Id");
		lblofferID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblofferID.setForeground(new Color(255, 255, 255));
		lblofferID.setBounds(10, 117, 167, 34);
		panel_AdvertisementEditor.add(lblofferID);

		lblTime = new JLabel("Time");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTime.setBounds(10, 162, 167, 34);
		panel_AdvertisementEditor.add(lblTime);

		lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(10, 207, 167, 34);
		panel_AdvertisementEditor.add(lblDescription);
		
		JLabel lblListOfLoaded = new JLabel("List of loaded ads");
		lblListOfLoaded.setForeground(Color.WHITE);
		lblListOfLoaded.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListOfLoaded.setBounds(10, 21, 417, 34);
		panel_AdvertisementEditor.add(lblListOfLoaded);
		
				lblPicture = new JLabel("");
				lblPicture.setIcon(new ImageIcon(
						"C:\\Users\\szyna\\Documents\\PWR-W4-INF\\Programowanie w j\u0119zyku Java\\lab12\\transformacje\\picture.jpg"));
				lblPicture.setBounds(437, 11, 292, 185);
				panel_AdvertisementEditor.add(lblPicture);
		
				lblms = new JLabel("ms");
				lblms.setForeground(Color.WHITE);
				lblms.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblms.setBounds(388, 162, 39, 34);
				panel_AdvertisementEditor.add(lblms);

		textField_Id = new JTextField();
		textField_Id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_Id.setText("1");
		textField_Id.setBounds(187, 118, 240, 34);
		panel_AdvertisementEditor.add(textField_Id);
		textField_Id.setColumns(10);

		scrollPaneNewOffer = new JScrollPane();
		scrollPaneNewOffer.setBounds(187, 208, 542, 124);
		panel_AdvertisementEditor.add(scrollPaneNewOffer);

		textArea_Description = new JTextArea();
		textArea_Description.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPaneNewOffer.setViewportView(textArea_Description);

		btnNewOffer = new JButton("Add a new offer");
		btnNewOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewOffer.setForeground(new Color(255, 255, 255));
		btnNewOffer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewOffer.setBackground(new Color(51, 51, 51));
		btnNewOffer.setBounds(10, 343, 719, 46);
		panel_AdvertisementEditor.add(btnNewOffer);

		spinner_price = new JSpinner();
		spinner_price.setModel(new SpinnerNumberModel(new Float(1000), new Float(0), null, new Float(1)));
		spinner_price.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner_price.setBounds(187, 163, 182, 34);
		panel_AdvertisementEditor.add(spinner_price);
		
				comboBox_Data = new JComboBox<String>();
				comboBox_Data.setBounds(10, 66, 417, 40);
				panel_AdvertisementEditor.add(comboBox_Data);
				comboBox_Data.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});

		panelJMX = new JPanel();
		panelJMX.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("JMX", null, panelJMX, null);
		panelJMX.setLayout(null);

		panel_Output = new JPanel();
		panel_Output.setBackground(new Color(51, 51, 51));
		panel_Output.setForeground(new Color(255, 255, 255));
		panel_Output.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panel_Output.setBounds(198, 11, 521, 378);
		panelJMX.add(panel_Output);
		panel_Output.setLayout(null);
		
		JPanel panel_Notification = new JPanel();
		panel_Notification.setLayout(null);
		panel_Notification.setForeground(Color.WHITE);
		panel_Notification.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Notification", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_Notification.setBackground(new Color(51, 51, 51));
		panel_Notification.setBounds(10, 11, 178, 378);
		panelJMX.add(panel_Notification);
		
		JLabel lblNotificationID = new JLabel("ID:");
		lblNotificationID.setForeground(new Color(255, 255, 255));
		lblNotificationID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNotificationID.setBounds(10, 24, 36, 39);
		panel_Notification.add(lblNotificationID);
		
		JLabel lblNotificationTime = new JLabel("Time:");
		lblNotificationTime.setForeground(Color.WHITE);
		lblNotificationTime.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNotificationTime.setBounds(10, 74, 61, 39);
		panel_Notification.add(lblNotificationTime);
		
		lblNotificationIDValue = new JLabel("0");
		lblNotificationIDValue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNotificationIDValue.setForeground(Color.WHITE);
		lblNotificationIDValue.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNotificationIDValue.setBounds(56, 24, 112, 39);
		panel_Notification.add(lblNotificationIDValue);
		
		lblNotificationTimeValue = new JLabel("0");
		lblNotificationTimeValue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNotificationTimeValue.setForeground(Color.WHITE);
		lblNotificationTimeValue.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNotificationTimeValue.setBounds(56, 74, 112, 39);
		panel_Notification.add(lblNotificationTimeValue);

	}
}
