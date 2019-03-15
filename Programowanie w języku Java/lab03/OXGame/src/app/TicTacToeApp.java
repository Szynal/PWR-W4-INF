package app;

import ai.TicTacToeAI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;

public class TicTacToeApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = -1904240656666374784L;
	private JButton[][] buttons = new JButton[3][3];
	private JButton playButton = new JButton();
	private JLabel statusLabel = new JLabel("");
	private TicTacToeAI game = null;
	private int human = 0;
	private int computer = 0;
	private boolean isPlay = false;
	private String[] chars = new String[] { "", "X", "O" };

	private BufferedImage playImage;
	private BufferedImage appIcon;

	private void setStatus(String s) {
		statusLabel.setBackground(new Color(0, 0, 0));
		statusLabel.setForeground(new Color(255, 255, 255));
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		statusLabel.setText(s);
	}

	private void setButtonsEnabled(boolean enabled) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				buttons[i][j].setEnabled(enabled);
				if (enabled)
					buttons[i][j].setText(" ");
			}
	}

	public TicTacToeApp() {

		try {
			appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			setIconImage(appIcon);
			getContentPane().setBackground(new Color(0, 51, 51));

			setTitle("Tic Tac Toe");
			setResizable(false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel gamePanel = new JPanel(new GridLayout(3, 3));
		gamePanel.setBackground(new Color(0, 51, 51));
		gamePanel.setBounds(10, 92, 314, 214);
		Font font = new Font("Arial", Font.BOLD, 32);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
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
					Image scaled = playImage.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					btn.setIcon(new ImageIcon(scaled));
					Border emptyBorder = BorderFactory.createEmptyBorder();
					btn.setBorder(emptyBorder);
				}
			});

		} catch (IOException exp) {
			exp.printStackTrace();
		}

		JPanel startLabelPanel = new JPanel();
		startLabelPanel.setBackground(new Color(0, 51, 51));
		startLabelPanel.setBounds(10, 48, 314, 33);
		startLabelPanel.add(statusLabel);

		JPanel playPanel = new JPanel();
		playPanel.setBackground(new Color(0, 51, 51));
		playPanel.setBounds(10, 306, 314, 74);
		playPanel.add(playButton);

		setStatus("Click 'Play' To Start");
		setButtonsEnabled(false);
		getContentPane().setLayout(null);

		getContentPane().add(startLabelPanel);
		getContentPane().add(gamePanel);
		getContentPane().add(playPanel);

		JPanel difficultyLevelPanel = new JPanel();
		difficultyLevelPanel.setBackground(new Color(0, 51, 51));
		difficultyLevelPanel.setBounds(10, 11, 314, 35);
		getContentPane().add(difficultyLevelPanel);

		JComboBox<String> difficultyLevelComboBox = new JComboBox<String>();
		difficultyLevelComboBox.setOpaque(false);
		difficultyLevelComboBox.setMinimumSize(new Dimension(100, 30));
		difficultyLevelComboBox.setSize(new Dimension(200, 0));
		difficultyLevelComboBox.setLightWeightPopupEnabled(false);
		difficultyLevelComboBox.setForeground(new Color(0, 0, 51));
		difficultyLevelComboBox.setBackground(new Color(255, 255, 255));
		difficultyLevelComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Easy", "Normal", "Hard" }));
		difficultyLevelPanel.add(difficultyLevelComboBox);

		setSize(340, 420);

		// I'm lazy to implement the correct way
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new TicTacToeApp().setVisible(true);
	}

	private void computerTurn() {
		if (!isPlay)
			return;
		/*
		 * int[] pos = game.nextMove(computer); if (pos != null) { int i = pos[0]; int j
		 * = pos[1]; buttons[i][j].setText(chars[computer]); game.setBoardValue(i, j,
		 * computer); }
		 * 
		 * checkState();
		 */
	}

	private void gameOver(String s) {
		setStatus(s);
		setButtonsEnabled(false);
		isPlay = false;
	}

	private void checkState() {
		/*
		 * if (game.isWin(human)) { gameOver("Congratulations, You've Won!"); } if
		 * (game.isWin(computer)) { gameOver("Sorry, You Lose!"); } if
		 * (game.nextMove(human) == null && game.nextMove(computer) == null) {
		 * gameOver("Draw, Click 'Play' For Rematch!"); }
		 */
	}

	private void click(int i, int j) {
		/*
		 * if (game.getBoardValue(i, j) == TicTacToeAI.EMPTY) {
		 * buttons[i][j].setText(chars[human]); game.setBoardValue(i, j, human);
		 * checkState(); computerTurn(); }
		 */
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == playButton) {
			play();
		} else {
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (event.getSource() == buttons[i][j])
						click(i, j);
		}
	}

	private void play() {
		playButton.setFocusPainted(false);
		game = new TicTacToeAI();
		/*
		 * human = TicTacToeAI.ONE; computer = TicTacToeAI.TWO;
		 */
		setStatus("Your Turn");
		setButtonsEnabled(true);
		isPlay = true;
	}
}