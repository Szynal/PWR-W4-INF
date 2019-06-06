package lab12.jaxb.app;

import javax.swing.SwingUtilities;

import lab12.jaxb.gui.MainFrame;

public class App {
	public static MainFrame window;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
