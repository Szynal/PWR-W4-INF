package lab11.nashorn.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.commons.io.FileUtils;

import lab11.nashorn.app.App;

/**
 * https://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial/
 * 
 * @author Pawel Szynal 226026
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8255412204648907508L;

//	NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
	private ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
	private List<String> files = null;

	// GUI
	private JTabbedPane tabbedPane;

	private JPanel panel;
	private JPanel panel_input;
	private JPanel panel_output;

	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;

	private JTextArea textArea_input;
	private JTextArea textArea_output;
	private JScrollPane scrollPane_output;
	private JScrollPane scrollPane_input;
	private JLabel lblMethod;
	private JComboBox<String> comboBox;
	private JButton btnExecute;

	public MainFrame() {
		try {
			guiInit();
			JMenuInitialize();
			files = getJSFiles();
			comboBox.setModel(new DefaultComboBoxModel<>(files.toArray(new String[0])));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void JFrameInitialize() {
		try {

			this.setBackground(new Color(51, 51, 51));
			this.setTitle("Lab11");
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

		panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		tabbedPane.addTab("Nashorn JavaScript Engine in Java", null, panel, null);
		panel.setLayout(null);

		btnExecute = new JButton("Execute");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					formatAction();
				} catch (FileNotFoundException | ScriptException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExecute.setForeground(Color.WHITE);
		btnExecute.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExecute.setBackground(new Color(51, 51, 51));
		btnExecute.setBounds(383, 349, 346, 40);
		panel.add(btnExecute);

		panel_input = new JPanel();
		panel_input.setLayout(null);
		panel_input.setForeground(Color.WHITE);
		panel_input.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Input",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_input.setBackground(new Color(51, 51, 51));
		panel_input.setBounds(10, 11, 346, 310);
		panel.add(panel_input);

		scrollPane_input = new JScrollPane();
		scrollPane_input.setBounds(10, 26, 326, 273);
		panel_input.add(scrollPane_input);

		textArea_input = new JTextArea();
		textArea_input.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane_input.setViewportView(textArea_input);
		panel_output = new JPanel();
		panel_output.setBackground(new Color(51, 51, 51));
		panel_output.setForeground(new Color(255, 255, 255));
		panel_output.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panel_output.setBounds(383, 11, 346, 310);
		panel.add(panel_output);
		panel_output.setLayout(null);

		scrollPane_output = new JScrollPane();
		scrollPane_output.setBounds(10, 26, 326, 273);
		panel_output.add(scrollPane_output);

		textArea_output = new JTextArea();
		textArea_output.setFont(new Font("Arial", Font.PLAIN, 12));
		scrollPane_output.setColumnHeaderView(textArea_output);

		lblMethod = new JLabel("JavaScript on the JVM");
		lblMethod.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMethod.setForeground(new Color(255, 255, 255));
		lblMethod.setBounds(10, 353, 153, 30);
		panel.add(lblMethod);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(173, 349, 183, 40);
		panel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxAction();
			}
		});
	}

	private void formatAction() throws FileNotFoundException, ScriptException {

		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(new FileReader("./js/" + comboBox.getSelectedItem().toString()));

		if (Objects.requireNonNull(comboBox.getSelectedItem()).toString().equals("None")) {
			textArea_output.setText(textArea_input.getText());
			return;
		}
		textArea_output.setText("");
		Invocable invocable = (Invocable) engine;
		Object result = null;
		try {
			result = invocable.invokeFunction("process", textArea_input.getText());
		} catch (ScriptException | NoSuchMethodException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}
		assert result != null;
		textArea_output.setText(result.toString());
	}

	private List<String> getJSFiles() throws IOException {
		File dir = new File("./js");
		String[] extensions = new String[] { "js" };
		System.out.println("Getting all .js files in " + dir.getCanonicalPath());
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		LinkedList<String> result = new LinkedList<String>();
		assert files != null;
		result.add("None");
		for (File file : files) {
			result.push(file.getName());
			System.out.println("+ " + file.getName());
		}
		return result;
	}

	private void comboBoxAction() {
		String converterName = (String) comboBox.getSelectedItem();
		assert converterName != null;
		if (converterName.equals("None")) {
			return;
		}
	}

	public ScriptEngine getScriptEngine() {
		return scriptEngine;
	}

	public void setScriptEngine(ScriptEngine scriptEngine) {
		this.scriptEngine = scriptEngine;
	}

}
