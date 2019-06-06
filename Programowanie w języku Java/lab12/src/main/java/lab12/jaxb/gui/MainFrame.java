package lab12.jaxb.gui;

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

import lab12.jaxb.offer.OfferType;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

	public String Dane = "./dane/";
	public String Transformacje = "./transformacje/";

	private static final long serialVersionUID = 8255412204648907508L;

	private OfferType offer;

	private List<String> xmlFiles = null;
	private List<String> styleFiles = null;

	File file = null;
	File publicKey = null;
	File privateKey = null;

	// GUI
	private JTabbedPane tabbedPane;
	private JPanel panelJAXB;
	private JPanel panelNewOffer;
	private JPanel panel_Output;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private JTextField textField_Id;
	private JTextField textField_CompanyName;
	private JTextField textField_start_date;
	private JTextField textField_end_date;
	private JTextArea textArea_Description;
	private JEditorPane outputEditorPane;
	private JSpinner spinner_price;

	private JLabel lblofferID;
	private JLabel lblCompanyName;
	private JLabel lblPrice;
	private JLabel lblDescription;
	private JLabel lbl_XMLData;
	private JLabel lbl_Transformations;

	private JScrollPane scrollPaneNewOffer;
	private JScrollPane scrollPane_1;
	private JComboBox<String> comboBox_Data;
	private JComboBox<String> comboBox_Transformations;
	private JButton btnFormat;
	private JButton btnChoseFile;
	private JButton btnNewOffer;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JLabel lblPln;
	private JLabel lblPicture;

	public MainFrame() {
		try {
			guiInit();
			JMenuInitialize();
			refresh();
			outputEditorPane.setEditable(false);
			outputEditorPane.setContentType("text/html");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private void choseFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("Chose file: " + chooser.getSelectedFile().getName());
			file = chooser.getSelectedFile();
			JOptionPane.showMessageDialog(this, "The file was selected correctly\n " + file.getAbsolutePath());
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

	private void JFrameInitialize() {
		try {

			this.setBackground(new Color(51, 51, 51));
			this.setTitle("Lab12 - JAXB");
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

		panelNewOffer = new JPanel();
		panelNewOffer.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("New offer", null, panelNewOffer, null);
		panelNewOffer.setLayout(null);

		lblofferID = new JLabel("Id");
		lblofferID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblofferID.setForeground(new Color(255, 255, 255));
		lblofferID.setBounds(10, 11, 167, 34);
		panelNewOffer.add(lblofferID);

		lblCompanyName = new JLabel("Company name");
		lblCompanyName.setForeground(Color.WHITE);
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompanyName.setBounds(10, 56, 167, 34);
		panelNewOffer.add(lblCompanyName);

		lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(10, 101, 167, 34);
		panelNewOffer.add(lblPrice);

		lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setBounds(10, 241, 167, 34);
		panelNewOffer.add(lblDescription);

		textField_Id = new JTextField();
		textField_Id.setBounds(187, 11, 240, 34);
		panelNewOffer.add(textField_Id);
		textField_Id.setColumns(10);

		textField_CompanyName = new JTextField();
		textField_CompanyName.setColumns(10);
		textField_CompanyName.setBounds(187, 58, 240, 34);
		panelNewOffer.add(textField_CompanyName);

		scrollPaneNewOffer = new JScrollPane();
		scrollPaneNewOffer.setBounds(187, 241, 542, 91);
		panelNewOffer.add(scrollPaneNewOffer);

		textArea_Description = new JTextArea();
		scrollPaneNewOffer.setViewportView(textArea_Description);

		btnNewOffer = new JButton("Add a new offer");
		btnNewOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addNewOffer();
					refresh();
				} catch (DatatypeConfigurationException | JAXBException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewOffer.setForeground(new Color(255, 255, 255));
		btnNewOffer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewOffer.setBackground(new Color(51, 51, 51));
		btnNewOffer.setBounds(10, 343, 719, 46);
		panelNewOffer.add(btnNewOffer);

		textField_start_date = new JTextField();
		textField_start_date.setColumns(10);
		textField_start_date.setBounds(187, 148, 240, 34);
		panelNewOffer.add(textField_start_date);

		textField_end_date = new JTextField();
		textField_end_date.setColumns(10);
		textField_end_date.setBounds(187, 193, 240, 37);
		panelNewOffer.add(textField_end_date);

		lblStartDate = new JLabel("Start date");
		lblStartDate.setForeground(Color.WHITE);
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartDate.setBounds(10, 146, 167, 34);
		panelNewOffer.add(lblStartDate);

		lblEndDate = new JLabel("End date");
		lblEndDate.setForeground(Color.WHITE);
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEndDate.setBounds(10, 191, 167, 34);
		panelNewOffer.add(lblEndDate);

		spinner_price = new JSpinner();
		spinner_price.setModel(new SpinnerNumberModel(new Float(1000), new Float(0), null, new Float(1)));
		spinner_price.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner_price.setBounds(187, 102, 182, 34);
		panelNewOffer.add(spinner_price);
		
		lblPln = new JLabel("PLN");
		lblPln.setForeground(Color.WHITE);
		lblPln.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPln.setBounds(388, 101, 39, 34);
		panelNewOffer.add(lblPln);
		
		lblPicture = new JLabel("");
		lblPicture.setIcon(new ImageIcon("C:\\Users\\szyna\\Documents\\PWR-W4-INF\\Programowanie w j\u0119zyku Java\\lab12\\transformacje\\picture.jpg"));
		lblPicture.setBounds(437, 11, 292, 219);
		panelNewOffer.add(lblPicture);

		panelJAXB = new JPanel();
		panelJAXB.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("JAXB", null, panelJAXB, null);
		panelJAXB.setLayout(null);

		btnFormat = new JButton("Format");
		btnFormat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				format();
			}
		});
		btnFormat.setForeground(Color.WHITE);
		btnFormat.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFormat.setBackground(new Color(51, 51, 51));
		btnFormat.setBounds(15, 298, 354, 91);
		panelJAXB.add(btnFormat);

		btnChoseFile = new JButton("Chose File");
		btnChoseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChoseFile.setForeground(Color.WHITE);
		btnChoseFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChoseFile.setBackground(new Color(51, 51, 51));
		btnChoseFile.setBounds(15, 38, 354, 55);
		panelJAXB.add(btnChoseFile);

		panel_Output = new JPanel();
		panel_Output.setBackground(new Color(51, 51, 51));
		panel_Output.setForeground(new Color(255, 255, 255));
		panel_Output.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panel_Output.setBounds(384, 11, 335, 378);
		panelJAXB.add(panel_Output);
		panel_Output.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 25, 305, 342);
		panel_Output.add(scrollPane_1);

		outputEditorPane = new JEditorPane();
		outputEditorPane.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane_1.setViewportView(outputEditorPane);

		lbl_XMLData = new JLabel("Xml data");
		lbl_XMLData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_XMLData.setForeground(new Color(255, 255, 255));
		lbl_XMLData.setBounds(15, 104, 166, 30);
		panelJAXB.add(lbl_XMLData);

		lbl_Transformations = new JLabel("Transformations");
		lbl_Transformations.setForeground(Color.WHITE);
		lbl_Transformations.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Transformations.setBounds(15, 206, 166, 30);
		panelJAXB.add(lbl_Transformations);

		comboBox_Data = new JComboBox<String>();
		comboBox_Data.setBounds(15, 145, 354, 40);
		panelJAXB.add(comboBox_Data);
		comboBox_Data.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboOffersAction();
			}
		});

		comboBox_Transformations = new JComboBox<String>();
		comboBox_Transformations.setBounds(15, 247, 354, 40);
		panelJAXB.add(comboBox_Transformations);
		comboBox_Transformations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboStylesAction();
			}
		});

	}

	private void addNewOffer() throws DatatypeConfigurationException, JAXBException {

		if (textField_Id.getText().isEmpty() || textField_CompanyName.getText().isEmpty()
				|| textArea_Description.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "All fields must be completed", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			offer = new OfferType();

			offer.setId(BigInteger.valueOf(Integer.parseInt(textField_Id.getText())));
			offer.setPrice((float) spinner_price.getValue());
			offer.setDescription(textArea_Description.getText());
			offer.setOfficeData(textField_CompanyName.getText());

			Date date = new Date();
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);

			offer.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
			offer.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));

			JAXBContext jaxbContext = null;
			jaxbContext = JAXBContext.newInstance(OfferType.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(offer, new File("./dane/offer" + offer.getId() + ".xml"));
			jaxbMarshaller.marshal(offer, System.out);

			JOptionPane.showMessageDialog(null, ("offer" + offer.getId() + ".xml" + " offer has been added."));
			refresh();
		}

	}

	private void comboOffersAction() {
		String converterName = (String) comboBox_Data.getSelectedItem();
		assert converterName != null;
		if (converterName.equals("None")) {
			return;
		}
	}

	private void comboStylesAction() {
		String converterName = (String) comboBox_Transformations.getSelectedItem();
		assert converterName != null;
		if (converterName.equals("None")) {
			return;
		}
	}

	private void refresh() {
		xmlFiles = getFiles(Dane, ".xml");
		styleFiles = getFiles(Transformacje, ".xsl");

		comboBox_Data.setModel(new DefaultComboBoxModel<>(xmlFiles.toArray(new String[0])));
		comboBox_Transformations.setModel(new DefaultComboBoxModel<>(styleFiles.toArray(new String[0])));
	}

	private void format() {
		if (Objects.requireNonNull(comboBox_Data.getSelectedItem()).toString().equals("None")
				&& Objects.requireNonNull(comboBox_Transformations.getSelectedItem()).toString().equals("None")) {
			JOptionPane.showMessageDialog(null, "There is nothing to format");
			return;
		}

		StringWriter stringWriter = new StringWriter();

		Source xml = new StreamSource(new File(Dane + (String) comboBox_Data.getSelectedItem()));
		Source xslt = new StreamSource(Transformacje + (String) comboBox_Transformations.getSelectedItem());

		try {

			FileWriter fw = new FileWriter("out.html");
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer trasform = tFactory.newTransformer(xslt);

			trasform.transform(xml, new StreamResult(stringWriter));
			fw.write(stringWriter.toString());
			fw.close();

			System.out.println("out.html generated successfully");

		} catch (IOException | TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

		try {
			outputEditorPane.setText(new String(Files.readAllBytes(Paths.get("out.html"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
