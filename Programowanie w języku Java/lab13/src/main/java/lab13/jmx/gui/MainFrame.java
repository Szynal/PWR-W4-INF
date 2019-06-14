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
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
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

import lab13.jmx.advertisement.AdType;
import lab13.jmx.advertisement.BannerController;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8255412204648907508L;

	private BannerController bannerController;
	public String Dane = "./dane/";

	private AdType adType;
	private List<String> xmlFiles = null;

	// GUI
	private JTabbedPane tabbedPane;
	private JPanel panelJMX;
	private JPanel panel_AdvertisementEditor;
	private JPanel panel_Output;
	private JPanel panel_Notification;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private JLabel lblofferID;
	private JLabel lblTime;
	private JLabel lblDescription;
	private JLabel lblms;
	private JLabel lblPicture;

	private JLabel lblNotificationIDValue;
	private JLabel lblNotificationTimeValue;
	private JLabel lblListOfLoaded;
	private JLabel lblNotificationID;
	private JLabel lblNotificationTime;

	private JTextField textFieldId;
	private JTextArea textArea_newDescription;
	private JTextArea textAreaDescription;
	private JSpinner spinnerPrice;
	private JScrollPane scrollPaneNewOffer;
	private JComboBox<String> comboBox_Data;
	private JButton btnConfirm;
	private JTextPane textPaneOutput;
	private JPanel panel_NewAD;
	private JTextField textField_newID;
	private JSpinner spinner_newTime;

	public MainFrame() {
		try {
			guiInit();
			menuInitialize();
			bannerInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void bannerInit() {

		bannerController = new BannerController(textPaneOutput, lblNotificationTimeValue, lblNotificationIDValue);

		bannerController.addAdvert(1000, "AD0");
		bannerController.addAdvert(3000, "AD1");
		bannerController.addAdvert(5000, "AD2");
		bannerController.addAdvert(10000, "AD3");
		bannerController.Start();

	}

	private void frameInitialize() {
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

	private List<String> getFiles(String pathname, String suffix) {

		File dir = new File(pathname);
		File[] files = dir.listFiles((File dir1, String name1) -> name1.endsWith(suffix));
		LinkedList<String> result = new LinkedList<String>();
		result.add("None");
		if (files != null) {
			for (File file : files) {
				System.out.println("From" + pathname + " was loaded " + file.getName());
				result.push(file.getName());
			}
		}
		return result;
	}

	private void menuInitialize() {
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
		frameInitialize();
		setBounds(100, 100, 750, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabbedPane.setBounds(0, 0, 744, 430);
		getContentPane().add(tabbedPane);

		panel_NewAD = new JPanel();
		panel_NewAD.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Add New Advertisement", null, panel_NewAD, null);
		panel_NewAD.setLayout(null);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addNewOffer();
				} catch (DatatypeConfigurationException | JAXBException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBackground(new Color(51, 51, 51));
		btnAdd.setBounds(10, 343, 719, 46);
		panel_NewAD.add(btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 719, 165);
		panel_NewAD.add(scrollPane);

		textArea_newDescription = new JTextArea();
		scrollPane.setViewportView(textArea_newDescription);
		textArea_newDescription.setFont(new Font("Monospaced", Font.PLAIN, 15));

		JLabel label = new JLabel("Description");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 123, 719, 34);
		panel_NewAD.add(label);

		spinner_newTime = new JSpinner();
		spinner_newTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner_newTime.setBounds(132, 78, 183, 34);
		panel_NewAD.add(spinner_newTime);

		JLabel label_1 = new JLabel("Time");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(10, 78, 167, 34);
		panel_NewAD.add(label_1);

		JLabel label_2 = new JLabel("Id");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(10, 33, 167, 34);
		panel_NewAD.add(label_2);

		textField_newID = new JTextField();
		textField_newID.setText("1");
		textField_newID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_newID.setColumns(10);
		textField_newID.setBounds(132, 33, 183, 34);
		panel_NewAD.add(textField_newID);

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

		lblListOfLoaded = new JLabel("List of loaded ads");
		lblListOfLoaded.setForeground(Color.WHITE);
		lblListOfLoaded.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListOfLoaded.setBounds(10, 21, 417, 34);
		panel_AdvertisementEditor.add(lblListOfLoaded);

		lblPicture = new JLabel("image");
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setForeground(new Color(255, 255, 255));
		lblPicture.setBackground(new Color(51, 102, 153));
		lblPicture.setIcon(null);
		lblPicture.setBounds(437, 11, 292, 185);
		panel_AdvertisementEditor.add(lblPicture);

		lblms = new JLabel("ms");
		lblms.setForeground(Color.WHITE);
		lblms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblms.setBounds(388, 162, 39, 34);
		panel_AdvertisementEditor.add(lblms);

		textFieldId = new JTextField();
		textFieldId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldId.setText("1");
		textFieldId.setBounds(107, 118, 320, 34);
		panel_AdvertisementEditor.add(textFieldId);
		textFieldId.setColumns(10);

		scrollPaneNewOffer = new JScrollPane();
		scrollPaneNewOffer.setBounds(107, 208, 622, 124);
		panel_AdvertisementEditor.add(scrollPaneNewOffer);

		textAreaDescription = new JTextArea();
		textAreaDescription.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPaneNewOffer.setViewportView(textAreaDescription);

		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConfirm.setBackground(new Color(51, 51, 51));
		btnConfirm.setBounds(10, 343, 719, 46);
		panel_AdvertisementEditor.add(btnConfirm);

		spinnerPrice = new JSpinner();
		spinnerPrice.setModel(new SpinnerNumberModel(new Float(1000), new Float(0), null, new Float(1)));
		spinnerPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerPrice.setBounds(107, 163, 262, 34);
		panel_AdvertisementEditor.add(spinnerPrice);

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

		textPaneOutput = new JTextPane();
		textPaneOutput.setBounds(10, 25, 501, 342);
		panel_Output.add(textPaneOutput);

		panel_Notification = new JPanel();
		panel_Notification.setLayout(null);
		panel_Notification.setForeground(Color.WHITE);
		panel_Notification.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Notification",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_Notification.setBackground(new Color(51, 51, 51));
		panel_Notification.setBounds(10, 11, 178, 378);
		panelJMX.add(panel_Notification);

		lblNotificationID = new JLabel("ID:");
		lblNotificationID.setForeground(new Color(255, 255, 255));
		lblNotificationID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNotificationID.setBounds(10, 24, 36, 39);
		panel_Notification.add(lblNotificationID);

		lblNotificationTime = new JLabel("Time:");
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

	private void addNewOffer() throws DatatypeConfigurationException, JAXBException {

		if (textField_newID.getText().isEmpty() || textArea_newDescription.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "All fields must be completed", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			adType = new AdType();

			adType.setId(BigInteger.valueOf(Integer.parseInt(textField_newID.getText())));
			adType.setTime(spinner_newTime.getValue().toString());

			JAXBContext jaxbContext = null;
			jaxbContext = JAXBContext.newInstance(AdType.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(adType, new File("./dane/ad" + adType.getId() + ".xml"));
			jaxbMarshaller.marshal(adType, System.out);

			JOptionPane.showMessageDialog(null, ("ad" + adType.getId() + ".xml" + " offer has been added."));
			refresh();
		}
	}

	private void refresh() {
		xmlFiles = getFiles(Dane, ".xml");
		comboBox_Data.setModel(new DefaultComboBoxModel<>(xmlFiles.toArray(new String[0])));
	}

}
