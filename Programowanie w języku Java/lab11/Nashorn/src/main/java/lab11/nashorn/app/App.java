package lab11.nashorn.app;

import javax.swing.SwingUtilities;

import lab11.nashorn.gui.MainFrame;

/**
 * 
 * @author Pawel Szynal 226026
 * 
 *         JS: `jjs -cp ./App.jar app.js`
 *
 */
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
