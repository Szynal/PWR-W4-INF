package Lab6.images.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;

import Lab6.images.reflection.Reflection;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8255412204648907508L;

	private ArrayList<WeakReference<ImageIcon>> weakImages = new ArrayList<WeakReference<ImageIcon>>();
	private Queue<ImageIcon> cacheQueue = new LinkedList<ImageIcon>();

	final int numberOfPictures = 10;
	final int showedAtOnce = 2;
	private int currentIndex = 0;

	private JPanel panelImages;
	private JTable tablePictureList;
	private JPanel panelThumbnails;
	private JPanel panelDirictories;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	FileTree panelFileTree;

	Thread pluginWorkingThread;
	MouseAdapter thumbnailClickedAdapter;
	JScrollPane scrollPane;

	private ArrayList<Reflection> reflections = new ArrayList<Reflection>();
	private JScrollPane scrollPaneTable;
	private JPanel panel;
	private JLabel lblImage1;
	private JLabel lblImage2;
	private JButton btnUpImage;
	private JButton btnDownImage;
	private JScrollPane scrollPane_1;

	public MainFrame() {
		initialize();
		JMenuInitialize();
	}

	private ImageIcon loadIcon(String file) {
		BufferedImage tmp = null;
		Image scalledImage;
		System.out.println("Loading: " + file);

		try {
			tmp = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		scalledImage = Objects.requireNonNull(tmp).getScaledInstance(500, 200, Image.SCALE_SMOOTH);

		return new ImageIcon(scalledImage);
	}

	private void JFrameInitialize() {
		try {

			this.setBackground(new Color(51, 51, 51));
			this.setTitle("Weak References in Java ");
			this.setForeground(new Color(0, 0, 0));
			this.getContentPane().setBackground(new Color(51, 51, 51));
			this.setResizable(false);
			BufferedImage appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			this.setIconImage(appIcon);
			this.setBounds(100, 100, 750, 550);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (IOException e) {
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
					JOptionPane.showMessageDialog(null, "Pawel‚ Szynal\n226026");
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

		panelDirictories = new JPanel();
		panelDirictories.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Dirictories", null, panelDirictories, null);
		panelDirictories.setLayout(null);

		panelFileTree = new FileTree(new File("src"));
		panelFileTree.setBounds(10, 27, 302, 364);
		panelDirictories.add(panelFileTree);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(337, 25, 392, 366);
		panelDirictories.add(scrollPane);

		panelThumbnails = new JPanel();
		scrollPane.setViewportView(panelThumbnails);
		panelThumbnails.setLayout(new BoxLayout(panelThumbnails, BoxLayout.Y_AXIS));

		addPluginPanel(tabbedPane);

		panelImages = new JPanel();
		panelImages.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Images", null, panelImages, null);
		panelImages.setLayout(null);

		JButton btnPicturesFolder = new JButton("Get Pictures folder");
		btnPicturesFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetImages();
			}
		});
		btnPicturesFolder.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPicturesFolder.setForeground(new Color(255, 255, 255));
		btnPicturesFolder.setBackground(new Color(51, 51, 51));
		btnPicturesFolder.setBounds(10, 11, 214, 40);
		panelImages.add(btnPicturesFolder);

		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTable.setBounds(8, 62, 216, 327);
		panelImages.add(scrollPaneTable);

		tablePictureList = new JTable();
		scrollPaneTable.setViewportView(tablePictureList);

		panel = new JPanel();
		panel.setBounds(234, 11, 452, 378);
		panelImages.add(panel);
		panel.setPreferredSize(new Dimension(500, 500));
		panel.setMinimumSize(new Dimension(500, 500));
		panel.setMaximumSize(new Dimension(500, 500));

		lblImage1 = new JLabel();
		lblImage1.setMinimumSize(new Dimension(500, 200));
		panel.add(lblImage1);
		
		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);

		lblImage2 = new JLabel();
		scrollPane_1.setViewportView(lblImage2);
		lblImage2.setMinimumSize(new Dimension(500, 200));

		btnUpImage = new JButton("");
		btnUpImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentIndex == 0) {
					return;
				}
				currentIndex--;
				cacheQueue.add((ImageIcon) lblImage1.getIcon());
				lblImage2.setIcon(lblImage1.getIcon());
				if (weakImages.get(currentIndex).get() != null) {
					System.out.println("UA from reference");
					lblImage1.setIcon(weakImages.get(currentIndex).get());
				} else {
					System.out.println("UA loading");
					lblImage1.setIcon(loadIcon(Integer.toString(currentIndex) + ".jpg"));
					weakImages.set(currentIndex, new WeakReference<ImageIcon>((ImageIcon) lblImage1.getIcon()));
				}
				System.out.println(Integer.toString(currentIndex) + " " + Integer.toString(currentIndex + 1));
				cacheQueue.poll();
			}
		});
		btnUpImage.setIcon(new ImageIcon(
				"C:\\Users\\szyna\\Desktop\\Documents\\PWR-W4-INF\\Programowanie w języku Java\\lab06\\img\\long-arrow-up.png"));
		btnUpImage.setBounds(696, 11, 33, 182);
		panelImages.add(btnUpImage);

		btnDownImage = new JButton("");
		btnDownImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentIndex >= numberOfPictures - showedAtOnce) {
					return;
				}
				currentIndex++;
				cacheQueue.add((ImageIcon) lblImage1.getIcon());
				lblImage1.setIcon(lblImage2.getIcon());
				if (weakImages.get(currentIndex + 1).get() != null) {
					System.out.println("DA from reference");
					lblImage2.setIcon(weakImages.get(currentIndex + 1).get());
				} else {
					System.out.println("DA loading");
					lblImage2.setIcon(loadIcon(Integer.toString(currentIndex + 1) + ".jpg"));
					weakImages.set(currentIndex + 1, new WeakReference<ImageIcon>((ImageIcon) lblImage2.getIcon()));
				}
				System.out.println(Integer.toString(currentIndex) + " " + Integer.toString(currentIndex + 1));
				cacheQueue.poll();
			}
		});
		btnDownImage.setIcon(new ImageIcon(
				"C:\\Users\\szyna\\Desktop\\Documents\\PWR-W4-INF\\Programowanie w języku Java\\lab06\\img\\long-arrow-down.png"));
		btnDownImage.setBounds(696, 207, 33, 182);
		panelImages.add(btnDownImage);
	}

	private void addPluginPanel(JTabbedPane tabbedPane) {
	}

	private void GetImages() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fileChooser.showOpenDialog(fileChooser);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose directory: " + fileChooser.getSelectedFile().getAbsolutePath());
		}

		File dir = new File(fileChooser.getSelectedFile().getAbsolutePath());
		String[] extensions = new String[] { "jpg" };
		try {
			System.out.println(
					"Getting all .jpg files in " + dir.getCanonicalPath() + " including those in subdirectories");

			List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
			for (File file : files) {

				Reflection reflection = new Reflection("file:/" + file.getAbsolutePath(), file.getName());
				reflection.setPath("file:/" + file.getAbsolutePath());
				reflections.add(reflection);
				System.out.println(file.getPath());

			}

			TableUpdate();

			cacheQueue.add(null);
			cacheQueue.add(null);
			cacheQueue.add(null);

			for (int i = 0; i < numberOfPictures; i++) {
				System.out.println("adding to array " + Integer.toString(i) + ".jpg");
				weakImages.add(new WeakReference<ImageIcon>(null));
			}
			if (weakImages.get(0).get() != null) {
				lblImage1.setIcon(weakImages.get(0).get());
			} else {
				System.out.println("Init Loading 0.jpg");
				lblImage1.setIcon(loadIcon("0.jpg"));
				weakImages.set(0, new WeakReference<ImageIcon>((ImageIcon) lblImage1.getIcon()));
			}
			if (weakImages.get(1).get() != null) {
				lblImage2.setIcon(weakImages.get(1).get());
			} else {
				System.out.println("Init Loading 1.jpg");
				lblImage2.setIcon(loadIcon("1.jpg"));
				weakImages.set(1, new WeakReference<ImageIcon>((ImageIcon) lblImage2.getIcon()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void TableUpdate() {

		try {
			String column[] = { "Id", "Name", "Loaded" };
			DefaultTableModel model = new DefaultTableModel(column, 0);
			int id = 0;
			for (Reflection reflection : reflections) {
				id++;
				Object[] content = { id, reflection.getClassName(), reflection.getLoaded() };
				model.addRow(content);

			}
			tablePictureList.setModel(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
