package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;

public class Application {

	private JFrame app;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane();
	private JLabel lblProcessedText = new JLabel("Processed text");
	private JButton btnLoadText = new JButton("Load Text");
	private JButton btnGetAllClasses = new JButton("Get All Classes");
	private final JTable table = new JTable();
	private final JButton btnUseThisClass = new JButton("Use this class");

	public LoadText LoadText = new LoadText();

	private ArrayList<Reflection> reflections = new ArrayList<Reflection>();
	private ArrayList<Object> loadedClasses = new ArrayList<Object>();

	URLClassLoader classLoader = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Application() {
		JFrameInitialize();
		JMenuInitialize();
		initialize();
	}

	private void JFrameInitialize() {
		try {

			app = new JFrame();
			app.setBackground(new Color(51, 51, 51));
			app.setTitle("Text Processing App");
			app.setForeground(new Color(0, 0, 0));
			app.getContentPane().setBackground(new Color(51, 51, 51));
			app.setResizable(false);
			BufferedImage appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			app.setIconImage(appIcon);
			app.setBounds(100, 100, 730, 532);
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void JMenuInitialize() {
		try {
			menuBar = new JMenuBar();
			app.setJMenuBar(menuBar);
			mnNewMenu = new JMenu("About");
			menuBar.add(mnNewMenu);
			mntmTask = new JMenuItem("Program description");
			mnNewMenu.add(mntmTask);
			mntmTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null,
							"http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/Zadania/ZadRMI_01.txt");
				}
			});
			mntmAuthor = new JMenuItem("Author");
			mnNewMenu.add(mntmAuthor);
			mntmAuthor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Pawe³ Szynal\n226026");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		app.getContentPane().add(scrollPane);
		lblProcessedText.setForeground(new Color(255, 255, 255));
		lblProcessedText.setBackground(new Color(51, 51, 51));
		app.getContentPane().add(lblProcessedText);
		app.getContentPane().setLayout(null);
		btnLoadText.setForeground(new Color(255, 255, 255));
		btnLoadText.setBackground(new Color(51, 51, 51));
		app.getContentPane().add(btnLoadText);
		btnGetAllClasses.setBackground(new Color(51, 51, 51));
		btnGetAllClasses.setForeground(new Color(255, 255, 255));
		app.getContentPane().add(btnGetAllClasses);
		btnUseThisClass.setForeground(new Color(255, 255, 255));
		btnUseThisClass.setBackground(new Color(51, 51, 51));
		app.getContentPane().add(btnUseThisClass);
		app.getContentPane().add(table);

		scrollPane.setRowHeaderView(textArea);
		scrollPane.setBounds(246, 51, 458, 419);
		lblProcessedText.setBounds(246, 11, 458, 29);
		table.setBounds(10, 91, 226, 339);
		btnLoadText.setBounds(10, 11, 226, 29);
		btnGetAllClasses.setBounds(10, 51, 226, 29);
		btnUseThisClass.setBounds(10, 441, 226, 29);
		btnUseThisClass.setEnabled(false);

		btnLoadText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoadText.TextLoading();
					textArea.setText(LoadText.getStringBuilder().toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnGetAllClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetAllClasses();
			}
		});

		btnUseThisClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UseClasses();
			}
		});

	}

	private void GetAllClasses() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fileChooser.showOpenDialog(fileChooser);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose directory: " + fileChooser.getSelectedFile().getAbsolutePath());
		}

		File dir = new File(fileChooser.getSelectedFile().getAbsolutePath());
		String[] extensions = new String[] { "class" };
		try {
			System.out.println(
					"Getting all .class files in " + dir.getCanonicalPath() + " including those in subdirectories");

			List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);

			for (File file : files) {

				if (CheckForbiddenPattern(file)) {
					Reflection reflection = new Reflection("file:/" + file.getAbsolutePath(), file.getName());
					reflection.setPath("file:/" + file.getAbsolutePath());
					reflections.add(reflection);
					System.out.println(file.getPath());
				}
			}

			TableUpdate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean CheckForbiddenPattern(File file) {
		Pattern pattern01 = Pattern.compile("Application", Pattern.CASE_INSENSITIVE);
		Pattern pattern02 = Pattern.compile("LoadText", Pattern.CASE_INSENSITIVE);
		Pattern pattern03 = Pattern.compile("Reflection", Pattern.CASE_INSENSITIVE);

		Matcher matcher01 = pattern01.matcher(file.getName());
		Matcher matcher02 = pattern02.matcher(file.getName());
		Matcher matcher03 = pattern03.matcher(file.getName());
		if (!matcher01.find() && !matcher02.find() && !matcher03.find()) {
			return true;
		} else {
			return false;
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
			table.setModel(model);
			btnUseThisClass.setEnabled(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void UseClasses() {
		try {

			for (Reflection reflection : reflections) {

				String path = reflection.getPath();
				String removeString = "\\textProcessing\\" + reflection.getClassName();
				path = path.replace(removeString, "");
				path += "/";

				classLoader = new URLClassLoader(new URL[] { new URL(path) });

				String className = "textProcessing." + reflection.getClassName();
				className = className.replace(".class", "");
				Class<?> ca = classLoader.loadClass(className);

				System.out.println(className + " loaded by " + ca.getClassLoader());

				@SuppressWarnings("rawtypes")
				Class[] cArg = new Class[1];
				cArg[0] = JTextArea.class;

				Method textProcces = ca.getMethod("setOutput", cArg);
				textProcces.invoke(ca.newInstance(), textArea);
				reflection.setLoaded("Load");

			}
			TableUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Object> getLoadedClasses() {
		return loadedClasses;
	}

	public void setLoadedClasses(ArrayList<Object> loadedClasses) {
		this.loadedClasses = loadedClasses;
	}
}
