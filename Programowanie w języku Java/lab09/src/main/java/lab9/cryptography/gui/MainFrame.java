package lab9.cryptography.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.border.TitledBorder;

import lab9.cryptography.core.Cryptosystem;
import lab9.cryptography.core.Cryptosystem.EncryptionMethod;
import lab9.cryptography.core.KeyGenerator;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8255412204648907508L;

	Cryptosystem cryptosystem;
	KeyGenerator keyGenerator;
	File file = null;
	File publicKey = null;
	File privateKey = null;

	String encrypted = "test/text_encrypted.txt";
	String decrypted = "test/text_decrypted.txt";
	String text = "test/text.txt";

	// GUI
	private JTabbedPane tabbedPane;
	private JPanel panelCpryptography;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private JComboBox<Object> comboBox;

	private JButton btnEncryptFile;
	private JButton btnDecryptFile;
	private JButton btnChoseFile;
	private JButton btnChosePublicKey;
	private JButton btnChosePrivateKey;
	private JButton btnGenerateKeys;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;

	public MainFrame() {
		try {
			guiInit();
			JMenuInitialize();
			coreInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void coreInit() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		cryptosystem = new Cryptosystem(comboBox.getSelectedItem().toString());
		keyGenerator = new KeyGenerator(1024);

	}

	private void encrypt() throws IOException {
		try {
			coreInit();
			cryptosystem.encryptFile(cryptosystem.getFileInBytes(file), new File(encrypted),
					cryptosystem.getPrivate(privateKey.getAbsolutePath(), comboBox.getSelectedItem().toString()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Encrypt error");
			e.printStackTrace();
		}
	}

	private void decrypt() throws IOException {
		try {
			coreInit();
			cryptosystem.decryptFile(cryptosystem.getFileInBytes(new File(encrypted)), new File(decrypted),
					cryptosystem.getPublic(publicKey.getAbsolutePath(), comboBox.getSelectedItem().toString()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Decrypt error");
			e.printStackTrace();
		}
	}

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

	private void JFrameInitialize() {
		try {

			this.setBackground(new Color(51, 51, 51));
			this.setTitle("Cryptosystem");
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

		panelCpryptography = new JPanel();
		panelCpryptography.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Cryptography", null, panelCpryptography, null);
		panelCpryptography.setLayout(null);

		comboBox = new JComboBox<Object>(EncryptionMethod.values());
		comboBox.setBounds(154, 104, 215, 30);
		panelCpryptography.add(comboBox);

		btnGenerateKeys = new JButton("Generate Keys");
		btnGenerateKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyGenerator.createKeys();
				JOptionPane.showMessageDialog(null, "The keys were generated.");
			}
		});
		btnGenerateKeys.setForeground(Color.WHITE);
		btnGenerateKeys.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGenerateKeys.setBackground(new Color(51, 51, 51));
		btnGenerateKeys.setBounds(15, 145, 354, 40);
		panelCpryptography.add(btnGenerateKeys);

		btnChosePrivateKey = new JButton("Chose Private Key");
		btnChosePrivateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println(": " + chooser.getSelectedFile().getName());
					privateKey = chooser.getSelectedFile();
				}

			}
		});
		btnChosePrivateKey.setForeground(Color.WHITE);
		btnChosePrivateKey.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChosePrivateKey.setBackground(new Color(51, 51, 51));
		btnChosePrivateKey.setBounds(15, 196, 354, 40);
		panelCpryptography.add(btnChosePrivateKey);

		btnEncryptFile = new JButton("Encrypt File");
		btnEncryptFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					encrypt();
					readFile(encrypted);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEncryptFile.setForeground(Color.WHITE);
		btnEncryptFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEncryptFile.setBackground(new Color(51, 51, 51));
		btnEncryptFile.setBounds(15, 247, 354, 40);
		panelCpryptography.add(btnEncryptFile);

		btnDecryptFile = new JButton("Decrypt File");
		btnDecryptFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					decrypt();
					readFile(decrypted);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDecryptFile.setForeground(Color.WHITE);
		btnDecryptFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDecryptFile.setBackground(new Color(51, 51, 51));
		btnDecryptFile.setBounds(15, 349, 354, 40);
		panelCpryptography.add(btnDecryptFile);

		btnChoseFile = new JButton("Chose File");
		btnChoseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choseFile();
				try {
					readFile(text);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnChoseFile.setForeground(Color.WHITE);
		btnChoseFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChoseFile.setBackground(new Color(51, 51, 51));
		btnChoseFile.setBounds(15, 38, 354, 55);
		panelCpryptography.add(btnChoseFile);

		btnChosePublicKey = new JButton("Chose Public Key");
		btnChosePublicKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println(": " + chooser.getSelectedFile().getName());
					publicKey = chooser.getSelectedFile();
				}

			}
		});
		btnChosePublicKey.setForeground(Color.WHITE);
		btnChosePublicKey.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChosePublicKey.setBackground(new Color(51, 51, 51));
		btnChosePublicKey.setBounds(15, 298, 354, 40);
		panelCpryptography.add(btnChosePublicKey);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panel.setBounds(384, 11, 335, 378);
		panelCpryptography.add(panel);
		panel.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 25, 305, 342);
		panel.add(scrollPane_1);

		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane_1.setViewportView(textArea);

		JLabel lblMethod = new JLabel("Encryption method");
		lblMethod.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMethod.setForeground(new Color(255, 255, 255));
		lblMethod.setBounds(15, 104, 166, 30);
		panelCpryptography.add(lblMethod);
	}

	@SuppressWarnings("resource")
	private void readFile(String filePath) throws IOException {
		textArea.setText("");
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String st;
		while ((st = br.readLine()) != null) {
			textArea.append(st);
			textArea.append("\n");
		}

	}
}
