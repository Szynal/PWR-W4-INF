package app;

import ai.TicTacToeAI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;

/**
 * @author Pawel Szynal
 */
public class TicTacToeApp implements ActionListener {

	public enum Difficulty {
		EASY, NORMAL, HARD;
	}

	private URLClassLoader classLoader = null;
	private ArrayList<Reflection> reflections = new ArrayList<Reflection>();
	private ArrayList<Object> loadedClasses = new ArrayList<Object>();
	private Class<?> AIclass;

	private int sizeOfTicTacToe = 3;

	private JFrame app;

	private JPanel difficultyLevelPanel;
	private JPanel startLabelPanel;
	private JPanel gamePanel;
	private JPanel playPanel;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmTask;
	private JMenuItem mntmAuthor;
	private JSpinner spinner;
	private JLabel lblSizeOfBoard;
	private JButton btnGetAllClasses;
	private JButton[][] buttons = new JButton[sizeOfTicTacToe][sizeOfTicTacToe];
	private JButton playButton = new JButton();
	private JLabel statusLabel = new JLabel("");
	private JComboBox<Difficulty> difficultyLevelComboBox;

	private BufferedImage playImage;
	private BufferedImage appIcon;
	Font font = new Font("Arial", Font.BOLD, 32);

	private TicTacToeAI game = null;
	private int human = 0;
	private int computer = 0;
	private boolean isPlay = false;
	private String[] chars = new String[] { "", "X", "O" };
	private JLabel lblDifficultyLevel;
	private JTable table;
	private JButton btnUseThisClass;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToeApp ticTacToeApp = new TicTacToeApp();
					ticTacToeApp.app.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TicTacToeApp() {

		JFrameInitialize();
		entryMenuInitialize();
		JPanelsInitialize();
		JLabelsInitialize();
		JButtonsInitialize();

		setStatus("Click 'Play' To Start");
		setButtonsEnabled(false);
		app.getContentPane().setLayout(null);

		JComboBoxInitialize();
		JMenuInitialize();
	}

	private void JFrameInitialize() {
		try {
			app = new JFrame();
			appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			app.setIconImage(appIcon);
			app.getContentPane().setBackground(new Color(0, 51, 51));
			app.setTitle("Tic Tac Toe");
			app.setSize(420, 480);
			app.setLocationRelativeTo(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void entryMenuInitialize() {
		btnGetAllClasses = new JButton("Get All Classes");
		btnGetAllClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetAllClasses();
			}
		});
		btnGetAllClasses.setBounds(10, 11, 384, 33);
		app.getContentPane().add(btnGetAllClasses);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 384, 290);
		app.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnUseThisClass = new JButton("Use this class");
		btnUseThisClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UseClasses();
			}
		});
		btnUseThisClass.setEnabled(false);
		btnUseThisClass.setBounds(10, 356, 384, 29);
		app.getContentPane().add(btnUseThisClass);
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

	private void UseClasses() {
		try {

			Reflection reflection = reflections.get(table.getSelectedRow());

			String path = reflection.getPath();
			String removeString = reflection.getClassName();
			path = path.replace(removeString, "");
			path += "/";
			System.out.println(path);
			classLoader = new URLClassLoader(new URL[] { new URL(path) });

			String className = "ai." + reflection.getClassName();
			className = className.replace(".class", "");
			System.out.println(className);
			AIclass = classLoader.loadClass(className);
			System.out.println(className + " loaded by " + AIclass.getClassLoader());

			table.setVisible(false);
			scrollPane.setVisible(false);
			btnGetAllClasses.setVisible(false);
			btnUseThisClass.setVisible(false);
			startLabelPanel.setVisible(true);
			difficultyLevelPanel.setVisible(true);
			playPanel.setVisible(true);
			gamePanel.setVisible(true);
			startLabelPanel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean CheckForbiddenPattern(File file) {
		Pattern pattern01 = Pattern.compile("TicTacToeApp", Pattern.CASE_INSENSITIVE);
		Pattern pattern02 = Pattern.compile("Reflection", Pattern.CASE_INSENSITIVE);

		Matcher matcher01 = pattern01.matcher(file.getName());
		Matcher matcher02 = pattern02.matcher(file.getName());
		if (!matcher01.find() && !matcher02.find()) {
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

	private void JPanelsInitialize() {
		try {
			gamePanel = new JPanel(new GridLayout(3, 3));
			gamePanel.setVisible(false);
			gamePanel.setBackground(new Color(0, 51, 51));
			gamePanel.setBounds(10, 114, 384, 214);

			playPanel = new JPanel();
			playPanel.setVisible(false);
			playPanel.setBackground(new Color(0, 51, 51));
			playPanel.setBounds(10, 339, 384, 70);
			GridBagLayout gbl_playPanel = new GridBagLayout();
			gbl_playPanel.columnWidths = new int[] { 152, 60, 0 };
			gbl_playPanel.rowHeights = new int[] { 60, 0 };
			gbl_playPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
			gbl_playPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
			playPanel.setLayout(gbl_playPanel);

			startLabelPanel = new JPanel();
			startLabelPanel.setVisible(false);
			startLabelPanel.setBackground(new Color(0, 51, 51));
			startLabelPanel.setBounds(10, 70, 384, 33);
			startLabelPanel.add(statusLabel);

			difficultyLevelPanel = new JPanel();
			difficultyLevelPanel.setVisible(false);
			difficultyLevelPanel.setBackground(new Color(0, 51, 51));
			difficultyLevelPanel.setBounds(10, 11, 384, 48);

			app.getContentPane().add(difficultyLevelPanel);
			app.getContentPane().add(startLabelPanel);
			app.getContentPane().add(gamePanel);
			app.getContentPane().add(playPanel);
			GridBagConstraints gbc_playButton = new GridBagConstraints();
			gbc_playButton.anchor = GridBagConstraints.NORTHWEST;
			gbc_playButton.gridx = 1;
			gbc_playButton.gridy = 0;
			playPanel.add(playButton, gbc_playButton);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void JButtonsInitialize() {
		try {
			sizeOfTicTacToe = (int) spinner.getValue();
			for (int i = 0; i < sizeOfTicTacToe; i++)
				for (int j = 0; j < sizeOfTicTacToe; j++) {
					buttons[i][j] = new JButton(" ");
					buttons[i][j].setFont(font);
					buttons[i][j].addActionListener(this);
					buttons[i][j].setFocusable(false);
					gamePanel.add(buttons[i][j]);
				}

			try {
				playButton.setBackground(new Color(0, 51, 51));
				playButton.setForeground(new Color(0, 51, 51));
				playImage = ImageIO.read((getClass().getClassLoader().getResource("playIcon.jpg")));

				playButton.setPreferredSize(new Dimension(60, 60));
				playButton.addActionListener(this);

				playButton.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						JButton btn = (JButton) e.getComponent();
						Dimension size = btn.getSize();
						Insets insets = btn.getInsets();
						size.width -= insets.left;// + insets.right;
						size.height -= insets.top;// + insets.bottom;
						if (size.width > size.height) {
							size.width = -1;
						} else {
							size.height = -1;
						}
						Image scaled = playImage.getScaledInstance(size.width, size.height,
								java.awt.Image.SCALE_SMOOTH);
						btn.setIcon(new ImageIcon(scaled));
						Border emptyBorder = BorderFactory.createEmptyBorder();
						btn.setBorder(emptyBorder);
					}
				});

			} catch (IOException exp) {
				exp.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void JComboBoxInitialize() {
		try {
			difficultyLevelPanel.setLayout(null);
			difficultyLevelComboBox = new JComboBox<Difficulty>();
			difficultyLevelComboBox.setLocation(273, 11);
			difficultyLevelComboBox.setOpaque(false);
			difficultyLevelComboBox.setMinimumSize(new Dimension(100, 30));
			difficultyLevelComboBox.setSize(new Dimension(101, 26));
			difficultyLevelComboBox.setLightWeightPopupEnabled(false);
			difficultyLevelComboBox.setForeground(new Color(0, 0, 51));
			difficultyLevelComboBox.setBackground(new Color(255, 255, 255));
			difficultyLevelComboBox.setModel(new DefaultComboBoxModel<Difficulty>(Difficulty.values()));
			difficultyLevelPanel.add(difficultyLevelComboBox);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	private void JLabelsInitialize() {
		try {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(3, 3, 12, 2));
			spinner.setBounds(118, 11, 45, 26);
			difficultyLevelPanel.add(spinner);

			lblSizeOfBoard = new JLabel("Size of the board");
			lblSizeOfBoard.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblSizeOfBoard.setForeground(Color.WHITE);
			lblSizeOfBoard.setBounds(10, 8, 98, 29);
			difficultyLevelPanel.add(lblSizeOfBoard);

			lblDifficultyLevel = new JLabel("Difficulty level");
			lblDifficultyLevel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDifficultyLevel.setForeground(Color.WHITE);
			lblDifficultyLevel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblDifficultyLevel.setBounds(173, 11, 90, 26);
			difficultyLevelPanel.add(lblDifficultyLevel);
		} catch (Exception e) {
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
							"Write a program that is used to play a man's corner and cross with a computer on a board of a given size (wins a player who will set up 5 neighboring characters sign in row / column / diagonally).\r\n"
									+ "The program should use the extended - compiled classes loaded with any loader from the indicated directory.\r\n"
									+ "Each extension class represents a particular strategy and has methods for generating subsequent computer traffic with a different level of difficulty,\r\n"
									+ "The name of the strategy is to be declared using class annotations. The difficulty level should be specified in the method annotation.\r\n"
									+ "It is possible to use methods that generate subsequent moves to have a list of attributes and return values ​​of the same type - eg download a \"board\" and return the coordinate \" shot. \"\r\n"
									+ "The peer strategy (class) and level of difficulty (method) should be changed during the game.");
				}
			});
			mntmAuthor = new JMenuItem("Author");
			mnNewMenu.add(mntmAuthor);
			mntmAuthor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Paweł Szynal\n226026");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setStatus(String s) {
		statusLabel.setBackground(new Color(0, 0, 0));
		statusLabel.setForeground(new Color(255, 255, 255));
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		statusLabel.setText(s);
	}

	private void setButtonsEnabled(boolean enabled) {
		sizeOfTicTacToe = (int) spinner.getValue();
		for (int i = 0; i < sizeOfTicTacToe; i++)
			for (int j = 0; j < sizeOfTicTacToe; j++) {
				buttons[i][j].setEnabled(enabled);
				if (enabled)
					buttons[i][j].setText(" ");
			}
	}

	private void play() {
		try {

			gamePanel.removeAll();
			JButtonsInitialize();

			playButton.setFocusPainted(false);

			@SuppressWarnings("rawtypes")
			Class[] cArg = new Class[1];
			cArg[0] = Integer.class;

			Constructor<?> AIConstructor = AIclass.getDeclaredConstructor(cArg);
			game = (TicTacToeAI) AIConstructor.newInstance((int) sizeOfTicTacToe);
			human = TicTacToeAI.PLAYER_ONE;
			computer = TicTacToeAI.PLAYER_TWO;

			setStatus("Your Turn");
			setButtonsEnabled(true);
			isPlay = true;
		} catch (Exception e) {
		}
	}

	private void click(int i, int j) {
		if (game.getBoardValue(i, j, sizeOfTicTacToe) == TicTacToeAI.EMPTY) {
			buttons[i][j].setText(chars[human]);
			game.setBoardValue(i, j, human, sizeOfTicTacToe);
			checkState();
			computerTurn();
		}
	}

	private void computerTurn() {
		if (!isPlay)
			return;

		int[] pos = game.calculateMoveForCurrentPlayer(computer, sizeOfTicTacToe,
				(Difficulty) difficultyLevelComboBox.getSelectedItem());
		if (pos != null) {
			int i = pos[0];
			int j = pos[1];
			buttons[i][j].setText(chars[computer]);
			game.setBoardValue(i, j, computer, sizeOfTicTacToe);
		}

		checkState();

	}

	private void gameOver(String s) {
		setStatus(s);
		setButtonsEnabled(false);
		isPlay = false;
	}

	private void checkState() {

		if (game.determineCurrentMove(human, sizeOfTicTacToe)) {
			gameOver("Congratulations, You've Won!");
		}
		if (game.determineCurrentMove(computer, sizeOfTicTacToe)) {
			gameOver("Sorry, You Lose!");
		}
		if (game.calculateMoveForCurrentPlayer(human, sizeOfTicTacToe,
				(Difficulty) difficultyLevelComboBox.getSelectedItem()) == null
				&& game.calculateMoveForCurrentPlayer(computer, sizeOfTicTacToe,
						(Difficulty) difficultyLevelComboBox.getSelectedItem()) == null) {
			gameOver("Draw, Click 'Play' For Rematch!");
		}
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == playButton) {
			play();
		} else {
			for (int i = 0; i < sizeOfTicTacToe; i++)
				for (int j = 0; j < sizeOfTicTacToe; j++)
					if (event.getSource() == buttons[i][j])
						click(i, j);
		}
	}

	public URLClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(URLClassLoader classLoader) {
		this.classLoader = classLoader;
	}
}