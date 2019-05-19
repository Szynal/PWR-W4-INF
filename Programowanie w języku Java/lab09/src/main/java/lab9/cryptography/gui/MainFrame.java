package lab9.cryptography.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8255412204648907508L;

	private JPanel panelImages;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private JButton button;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;

	public MainFrame() {
		initialize();
		JMenuInitialize();
	}


	private void JFrameInitialize() {
		try {

			this.setBackground(new Color(51, 51, 51));
			this.setTitle("Cryptography in Java ");
			this.setForeground(new Color(0, 0, 0));
			this.getContentPane().setBackground(new Color(51, 51, 51));
			this.setResizable(false);
			BufferedImage appIcon = ImageIO.read(getClass().getClassLoader().getResource("pwr.jpg"));
			this.setIconImage(appIcon);
			this.setBounds(100, 100, 750, 550);
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
					JOptionPane.showMessageDialog(null, "Pawel� Szynal\n226026");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		JFrameInitialize();
		setBounds(100, 100, 750, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabbedPane.setBounds(0, 0, 744, 430);
		getContentPane().add(tabbedPane);

		panelImages = new JPanel();
		panelImages.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Images", null, panelImages, null);
		panelImages.setLayout(null);

		JButton button_1 = new JButton("Get Pictures folder");
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(new Color(51, 51, 51));
		button_1.setBounds(15, 270, 354, 40);
		panelImages.add(button_1);

		JButton button_2 = new JButton("Get Pictures folder");
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBackground(new Color(51, 51, 51));
		button_2.setBounds(15, 325, 354, 40);
		panelImages.add(button_2);

		button = new JButton("Get Pictures folder");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(new Color(51, 51, 51));
		button.setBounds(15, 215, 354, 40);
		panelImages.add(button);

		button_3 = new JButton("Get Pictures folder");
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_3.setBackground(new Color(51, 51, 51));
		button_3.setBounds(15, 159, 354, 40);
		panelImages.add(button_3);

		button_4 = new JButton("Get Pictures folder");
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_4.setBackground(new Color(51, 51, 51));
		button_4.setBounds(15, 102, 354, 40);
		panelImages.add(button_4);

		button_5 = new JButton("Get Pictures folder");
		button_5.setForeground(Color.WHITE);
		button_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_5.setBackground(new Color(51, 51, 51));
		button_5.setBounds(15, 46, 354, 40);
		panelImages.add(button_5);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panel.setBounds(384, 34, 335, 331);
		panelImages.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("No File");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(51, 51, 51));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(15, 26, 305, 289);
		panel.add(lblNewLabel);
	}

	

	@SuppressWarnings("unused")
	private ImageIcon loadIcon(String path, File file) {
		BufferedImage tmp = null;
		Image scalledImage;
		System.out.println("Loading: " + path);

		try {
			tmp = ImageIO.read(file);

		} catch (IOException e) {
			e.printStackTrace();
		}

		scalledImage = Objects.requireNonNull(tmp).getScaledInstance(500, 200, Image.SCALE_SMOOTH);

		return new ImageIcon(scalledImage);
	}
	
}
