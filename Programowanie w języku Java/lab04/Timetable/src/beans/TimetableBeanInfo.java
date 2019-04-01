package beans;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

public class TimetableBeanInfo extends SimpleBeanInfo {

	public BeanDescriptor getBeanDescriptor() {
		return new BeanDescriptor(Timetable.class, TimetableCustomizer.class);
	}

	public PropertyDescriptor[] getPropertyDescriptors() {
		try {
			PropertyDescriptor titleDescriptor = new PropertyDescriptor("title", Timetable.class);
			PropertyDescriptor panelSizeDescriptor = new PropertyDescriptor("panelSize", Timetable.class);
			PropertyDescriptor beginDateDescriptor = new PropertyDescriptor("beginDate", Timetable.class);
			PropertyDescriptor endDateDescriptor = new PropertyDescriptor("endDate", Timetable.class);

			return new PropertyDescriptor[] { titleDescriptor, panelSizeDescriptor, beginDateDescriptor,
					endDateDescriptor };
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public MethodDescriptor[] getMethodDescriptors() {

		Method addPropertyChangeListener, removePropertyChangeListener;
		Method addVetoableChangeListener, removeVetoableChangeListener;

		Class<?> propertyChangeArgs[] = { java.beans.PropertyChangeListener.class };
		Class<?> vetoableChangeArgs[] = { java.beans.VetoableChangeListener.class };

		try {
			addPropertyChangeListener = Timetable.class.getMethod("addPropertyChangeListener", propertyChangeArgs);
			removePropertyChangeListener = Timetable.class.getMethod("removePropertyChangeListener",
					propertyChangeArgs);
			addVetoableChangeListener = Timetable.class.getMethod("addVetoableChangeListener", vetoableChangeArgs);
			removeVetoableChangeListener = Timetable.class.getMethod("removeVetoableChangeListener",
					vetoableChangeArgs);
		} catch (Exception ex) {
			throw new Error("Missing method: " + ex);
		}

		MethodDescriptor result[] = { new MethodDescriptor(addPropertyChangeListener),
				new MethodDescriptor(removePropertyChangeListener), new MethodDescriptor(addVetoableChangeListener),
				new MethodDescriptor(removeVetoableChangeListener) };

		return result;
	}

}
