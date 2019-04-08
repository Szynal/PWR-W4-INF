package Lab3.weakReferencesApp;

import javax.swing.SwingUtilities;
import Lab3.imagemodifier.gui.MainFrame;

public class App 
{
    public static void main( String[] args )
    {
    	SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
