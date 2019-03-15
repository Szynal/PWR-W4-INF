package app;

import ai.TicTacToeAI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;

/**
 * @author Pawel Szynal
 */
public class TicTacToeApp implements ActionListener {

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
	private JButton[][] buttons = new JButton[sizeOfTicTacToe][sizeOfTicTacToe];
	private JButton playButton = new JButton();
	private JLabel statusLabel = new JLabel("");

	private BufferedImage playImage;
	private BufferedImage appIcon;
	Font font = new Font("Arial", Font.BOLD, 32);

	private TicTacToeAI game = null;
	private int human = 0;
	private int computer = 0;
	private boolean isPlay = false;
	private String[] chars = new String[] { "", "X", "O" };

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
		JPanelsInitialize();
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
			app.setSize(400, 450);
			app.setLocationRelativeTo(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void JPanelsInitialize() {
		try {
			gamePanel = new JPanel(new GridLayout(3, 3));
			gamePanel.setBackground(new Color(0, 51, 51));
			gamePanel.setBounds(10, 92, 364, 214);

			playPanel = new JPanel();
			playPanel.setBackground(new Color(0, 51, 51));
			playPanel.setBounds(10, 317, 364, 70);
			playPanel.add(playButton);

			startLabelPanel = new JPanel();
			startLabelPanel.setBackground(new Color(0, 51, 51));
			startLabelPanel.setBounds(10, 48, 364, 33);
			startLabelPanel.add(statusLabel);

			difficultyLevelPanel = new JPanel();
			difficultyLevelPanel.setBackground(new Color(0, 51, 51));
			difficultyLevelPanel.setBounds(10, 11, 364, 35);

			app.getContentPane().add(difficultyLevelPanel);
			app.getContentPane().add(startLabelPanel);
			app.getContentPane().add(gamePanel);
			app.getContentPane().add(playPanel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void JButtonsInitialize() {
		try {
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
			JComboBox<String> difficultyLevelComboBox = new JComboBox<String>();
			difficultyLevelComboBox.setOpaque(false);
			difficultyLevelComboBox.setMinimumSize(new Dimension(100, 30));
			difficultyLevelComboBox.setSize(new Dimension(200, 0));
			difficultyLevelComboBox.setLightWeightPopupEnabled(false);
			difficultyLevelComboBox.setForeground(new Color(0, 0, 51));
			difficultyLevelComboBox.setBackground(new Color(255, 255, 255));
			difficultyLevelComboBox
					.setModel(new DefaultComboBoxModel<String>(new String[] { "Easy", "Normal", "Hard" }));
			difficultyLevelPanel.add(difficultyLevelComboBox);
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
		for (int i = 0; i < sizeOfTicTacToe; i++)
			for (int j = 0; j < sizeOfTicTacToe; j++) {
				buttons[i][j].setEnabled(enabled);
				if (enabled)
					buttons[i][j].setText(" ");
			}
	}

	private void computerTurn() {
		if (!isPlay)
			return;

		int[] pos = game.nextMove(computer, sizeOfTicTacToe);
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

		if (game.isWin(human, sizeOfTicTacToe)) {
			gameOver("Congratulations, You've Won!");
		}
		if (game.isWin(computer, sizeOfTicTacToe)) {
			gameOver("Sorry, You Lose!");
		}
		if (game.nextMove(human, sizeOfTicTacToe) == null && game.nextMove(computer, sizeOfTicTacToe) == null) {
			gameOver("Draw, Click 'Play' For Rematch!");
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

	private void play() {
		playButton.setFocusPainted(false);
		game = new TicTacToeAI(sizeOfTicTacToe);

		human = TicTacToeAI.ONE;
		computer = TicTacToeAI.TWO;

		setStatus("Your Turn");
		setButtonsEnabled(true);
		isPlay = true;
	}
}