package javaclass;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.StringWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class App {

	protected static final String File = null;
	private JFrame frame;
	private JTextField Price;
	private JTextField ID;
	private JTextField Office;
	private JTextField Data;
	private JTextField Facilities;
	private JTextField xlsName;

	Offer offer = new Offer();
	private final JLabel lblNewLabel_6 = new JLabel("XML Name");
	private JTextField xmlName;
	private JLabel xls;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1003, 569);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 303, 464);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(0, 1, 151, 76);
		panel_1.add(lblNewLabel);

		ID = new JTextField();
		ID.setBounds(151, 1, 151, 76);
		panel_1.add(ID);
		ID.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Office");
		lblNewLabel_2.setBounds(0, 77, 151, 76);
		panel_1.add(lblNewLabel_2);

		Office = new JTextField();
		Office.setBounds(151, 77, 151, 76);
		panel_1.add(Office);
		Office.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setBounds(0, 153, 151, 76);
		panel_1.add(lblNewLabel_1);

		Data = new JTextField();
		Data.setBounds(151, 153, 151, 76);
		panel_1.add(Data);
		Data.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setBounds(0, 229, 151, 76);
		panel_1.add(lblNewLabel_4);

		Price = new JTextField();
		Price.setBounds(151, 229, 151, 76);
		panel_1.add(Price);
		Price.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Facilities");
		lblNewLabel_3.setBounds(0, 305, 151, 76);
		panel_1.add(lblNewLabel_3);

		Facilities = new JTextField();
		Facilities.setBounds(151, 305, 151, 76);
		panel_1.add(Facilities);
		Facilities.setColumns(10);

		JButton btnNewButton = new JButton("CONFIRM");
		btnNewButton.setBounds(0, 381, 303, 83);
		panel_1.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				offer.setId(Integer.parseInt(ID.getText()));
				offer.setOffice(Office.getText());
				offer.setData(Data.getText());
				offer.setPrice(Price.getText());
				offer.setFacilities(Facilities.getText());
				File tmpDir = new File("dane\\offer" + ID.getText() + ".xml");
				boolean exists = tmpDir.exists();
				System.out.println(exists);
				if (exists) {
					System.out.println(exists);
					tmpDir.delete();
				}
				JAXBContext jaxbContext;
				Marshaller marshaller;
				try {
					jaxbContext = JAXBContext.newInstance(Offer.class);
					marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					marshaller.marshal(offer, new File("dane\\offer" + ID.getText() + ".xml"));
					marshaller.marshal(offer, System.out);
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton btnNewButton_1 = new JButton("UPLOAD");
		btnNewButton_1.setBounds(566, 0, 97, 77);
		panel.add(btnNewButton_1);

		/////////////////////
		// create jeditorpane
		JEditorPane jEditorPane = new JEditorPane();

		// make it read-only
		jEditorPane.setEditable(false);

		// create a scrollpane; modify its attributes as desired
		JScrollPane scrollPane = new JScrollPane(jEditorPane);

		// add an html editor kit
		HTMLEditorKit kit = new HTMLEditorKit();
		jEditorPane.setEditorKit(kit);

		// create a document, set it on the jeditorpane, then add the html
		Document doc = (Document) kit.createDefaultDocument();
		jEditorPane.setDocument((javax.swing.text.Document) doc);

		////////////////////

		JTextPane textPane = new JTextPane();

		xls = new JLabel("XLS Name");
		xls.setBackground(Color.WHITE);
		xls.setBounds(310, -2, 110, 41);
		panel.add(xls);

		xlsName = new JTextField();
		xlsName.setBounds(419, 0, 151, 39);
		panel.add(xlsName);
		xlsName.setColumns(10);
		lblNewLabel_6.setBounds(310, 38, 110, 36);
		panel.add(lblNewLabel_6);

		xmlName = new JTextField();
		xmlName.setBounds(419, 38, 151, 39);
		panel.add(xmlName);
		xmlName.setColumns(10);

		scrollPane.setBounds(315, 76, 348, 388);
		panel.add(scrollPane);

		textPane.setBounds(676, 0, 294, 464);
		panel.add(textPane);

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String xml_file = xmlName.getText();
				String xls_file = xlsName.getText();
				System.out.println(xml_file);
				System.out.println(xls_file);
				File xml = new File("dane\\" + xml_file);
				File xsl = new File("transformacje\\" + xls_file);

				StreamSource source = new StreamSource(xml);
				StreamSource stylesource = new StreamSource(xsl);

				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = null;
				try {
					transformer = factory.newTransformer(stylesource);
				} catch (TransformerConfigurationException e1) {
					e1.printStackTrace();
				}
				StringWriter writer = new StringWriter();
				StreamResult result = new StreamResult(writer);
				String finalstring = null;
				try {
					transformer.transform(source, result);
					StringBuffer sb = writer.getBuffer();
					finalstring = sb.toString();

				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.print(finalstring);
				jEditorPane.setText(finalstring);
				textPane.setText(finalstring);

			}
		});

	}
}
