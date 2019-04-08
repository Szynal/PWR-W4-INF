package Lab3.imagemodifier.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	final File PLUGIN_FOLDER = new File("target\\classes\\Lab3\\imagemodifier\\pluginsystem\\plugins");
	ThumbnailsLoaderWorker thumbnailWorker;
	Thumbnail choosenThumbnailToModify;
	FileTree panelFileTree;

	JPanel panelThumbnails;
	JPanel panelDirictories;

	Thread pluginWorkingThread;

	MouseAdapter thumbnailClickedAdapter;

	JScrollPane scrollPane;
	private JPanel panelImages;
	private JTable tablePictureList;
	private JTextField textField;

	public MainFrame() {
		initialize();
		JMenuInitialize();
		addEvents();
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
			this.setBounds(100, 100, 750, 460);
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
			}
		});
		btnPicturesFolder.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPicturesFolder.setForeground(new Color(255, 255, 255));
		btnPicturesFolder.setBackground(new Color(51, 51, 51));
		btnPicturesFolder.setBounds(10, 11, 214, 29);
		panelImages.add(btnPicturesFolder);

		tablePictureList = new JTable();
		tablePictureList.setBounds(10, 51, 216, 240);
		panelImages.add(tablePictureList);
		
		JScrollPane scrollPanePictures = new JScrollPane();
		scrollPanePictures.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePictures.setBounds(234, 11, 495, 280);
		panelImages.add(scrollPanePictures);
		
		JPanel panelImageCanvas = new JPanel();
		scrollPanePictures.setViewportView(panelImageCanvas);
		
		textField = new JTextField();
		textField.setBounds(10, 302, 719, 89);
		panelImages.add(textField);
		textField.setColumns(10);
	}

	private void addPluginPanel(JTabbedPane tabbedPane) {
	}

	public void addEvents() {
		this.thumbnailClickedAdapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Thumbnail clickedThumbnail = (Thumbnail) e.getSource();

				if (choosenThumbnailToModify != null)
					choosenThumbnailToModify.setForeground(Color.BLACK);

				clickedThumbnail.setForeground(Color.GREEN);

				choosenThumbnailToModify = clickedThumbnail;
			}
		};

		panelFileTree.addTreeItemSelected(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();

				// Stop worker if is already running
				if (thumbnailWorker != null) {
					thumbnailWorker.terminate();
				}

				thumbnailWorker = new ThumbnailsLoaderWorker(new File(node.toString()), panelThumbnails,
						thumbnailClickedAdapter);

				thumbnailWorker.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent event) {
						validate();
						repaint();
					}
				});

				thumbnailWorker.execute();
			}
		});
	}
}
