package app;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URLClassLoader;

import javax.imageio.ImageIO;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class NotesView extends JFrame {

	private static final long serialVersionUID = -4287775650050067623L;
	private JMenuBar menuBar;

	URLClassLoader classLoader = null;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	public NotesView(int x, int y, int width, int height, String title, String label, String data, String text) {
		super("NotesView 1.0");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		try {
			JFrame frame = new JFrame();
			frame.setSize(width, height);
			frame.setResizable(false);
			frame.setTitle("NotesView");
			frame.pack();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setMinimumSize(this.getSize());

			BufferedImage appIcon = ImageIO.read((getClass().getClassLoader().getResource("pwr.jpg")));
			frame.setIconImage(appIcon);
			frame.setBounds(x, y, width, height);
			frame.setBackground(new Color(51, 51, 51));
			frame.setBackground(new Color(51, 51, 51));
			frame.getContentPane().setBackground(new Color(51, 51, 51));
			getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

			panel = new JPanel();
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			frame.getContentPane().add(panel);

			scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panel.add(scrollPane);

			textArea = new JTextArea();
			textArea.setLineWrap(true);
			scrollPane.setViewportView(textArea);
			textArea.setMargin(new Insets(5, 5, 5, 5));
			textArea.setDropMode(DropMode.INSERT);
			textArea.setDragEnabled(true);
			textArea.setDoubleBuffered(true);
			textArea.setText(text);
			textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

			menuBar = new JMenuBar();
			frame.setJMenuBar(menuBar);
			lblNewLabel = new JLabel("Title: " + title + "    Label: " + label + "  \nData:  " + data);
			menuBar.add(lblNewLabel);
			lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setRequestFocusEnabled(false);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setForeground(new Color(0, 0, 0));

			pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
