package beans;

/**
   @version 1.0 25-03-2019
   @author Pawel Szynal
*/
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class Timetable extends JPanel {

	private PropertyChangeSupport propertyChangeSupp = new PropertyChangeSupport(this);
	private VetoableChangeSupport vetoableChangeSupp = new VetoableChangeSupport(this);

	private String title = "Title";

	public Timetable() {

	}

	private static final long serialVersionUID = 1L;

	public void setTitle(String t) {
		title = t;
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), t, TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
	}

	public String getTitle() {
		return title;
	}

	public void addPropertyChangeListener(PropertyChangeListener p) {
		propertyChangeSupp.addPropertyChangeListener(p);
	}

	public void removePropertyChangeListener(PropertyChangeListener p) {
		propertyChangeSupp.removePropertyChangeListener(p);
	}

	public void addVetoableChangeListener(VetoableChangeListener v) {
		vetoableChangeSupp.addVetoableChangeListener(v);
	}

	public void removeVetoableChangeListener(VetoableChangeListener v) {
		vetoableChangeSupp.removeVetoableChangeListener(v);
	}
}
