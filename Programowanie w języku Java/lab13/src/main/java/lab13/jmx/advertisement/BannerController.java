package lab13.jmx.advertisement;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import lab13.jmx.interfaces.IBillboard;

/**
 * Implementing Billboard with a Dynamic MBean.
 * 
 * @categoryMBean refers to the fact that the interface is revealed at runtime,
 *                instead of through the introspection of static class names.
 *                The term dynamic is not meant to imply that the MBean can
 *                dynamically change its management interface.
 * 
 * @author Pawel Szynal 226026
 * 
 * @version 1.0
 *
 */
public class BannerController implements DynamicMBean, IBillboard, Runnable {

	// internal variables describing the MBean
	private MBeanInfo dMBeanInfo = null;
	private String dClassName = this.getClass().getName();
	private String dDescription = "Lab13 -JMX. Implementation of a dynamic MBean.";

	// internal variables for describing MBean elements
	private MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[2];
	private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
	private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];

	private HashMap<Integer, Advertisement> hmap = new HashMap();
	private int k = 0;
	private AtomicBoolean running = new AtomicBoolean();
	private JTextPane adField;
	private JLabel timerText;
	private JLabel idText;
	private Thread t;
	private ObjectName tmpObjectName = null;
	private MBeanServer server = null;

	public BannerController(JTextPane text, JLabel timer, JLabel id) {

		dAttributes[0] = new MBeanAttributeInfo("State", // name
				"java.lang.String", // type
				"State: state string.", // description
				true, // readable
				true, false); // writable
		dAttributes[1] = new MBeanAttributeInfo("NbChanges", "java.lang.Integer",
				"NbChanges: number of times the State string has been changed.", true, false, false);

		// use reflection to get constructor signatures
		Constructor[] constructors = this.getClass().getConstructors();
		dConstructors[0] = new MBeanConstructorInfo("SimpleDynamic(): No-parameter constructor", // description
				constructors[0]); // the contructor object

		MBeanParameterInfo[] params = null;
		dOperations[0] = new MBeanOperationInfo("reset", // name
				"Resets State and NbChanges attributes to their initial values",
				// description
				params, // parameter types
				"void", // return type
				MBeanOperationInfo.ACTION); // impact

		dMBeanInfo = new MBeanInfo(dClassName, dDescription, dAttributes, dConstructors, dOperations,
				new MBeanNotificationInfo[0]);

		this.adField = text;
		this.timerText = timer;
		this.idText = id;

		try {
			this.tmpObjectName = new ObjectName("lab13.Advertisement:type=Controller,name=BannerController");
		} catch (MalformedObjectNameException var6) {
			var6.printStackTrace();
		}

		this.server = ManagementFactory.getPlatformMBeanServer();

		try {
			this.server.registerMBean(this, this.tmpObjectName);
		} catch (MBeanRegistrationException | NotCompliantMBeanException | InstanceAlreadyExistsException var5) {
			var5.printStackTrace();
		}

	}

	@Override
	public MBeanInfo getMBeanInfo() {
		// return the information we want to expose for management:
		// the dMBeanInfo private field has been built at instantiation time,
		return (dMBeanInfo);
	}

	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException, ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
		// TODO Auto-generated method stub

	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<Integer, Advertisement> getHmap() {
		return this.hmap;
	}

	public void setHmap(HashMap<Integer, Advertisement> hmap) {
		this.hmap = hmap;
	}

	public void addAdvert(int time, String adv) {
		Advertisement advert = new Advertisement();
		advert.setAddvTime(time);
		advert.setAddvString(adv);
		this.hmap.put(this.k, advert);

		try {
			this.tmpObjectName = new ObjectName("lab13.Advertisement:type=Advert,name=Advert_" + this.k);
		} catch (MalformedObjectNameException var6) {
			var6.printStackTrace();
		}

		try {
			this.server.registerMBean(advert, this.tmpObjectName);
		} catch (MBeanRegistrationException | NotCompliantMBeanException | InstanceAlreadyExistsException var5) {
			var5.printStackTrace();
		}

		++this.k;
	}

	public void Start() {
		this.running.set(true);
		this.t = new Thread(this);
		this.t.start();
	}

	public void Stop() {
		this.running.set(false);
	}

	public void run() {
		label28: while (true) {
			if (this.running.get()) {
				Iterator var1 = this.hmap.keySet().iterator();

				while (true) {
					if (!var1.hasNext()) {
						continue label28;
					}

					int key = (Integer) var1.next();
					if (!this.running.get()) {
						continue label28;
					}

					this.timerText.setText(Integer.toString(((Advertisement) this.hmap.get(key)).getAddvTime()));
					this.adField.setText(((Advertisement) this.hmap.get(key)).getAddvString());
					this.idText.setText(Integer.toString(key));

					try {
						Thread.sleep((long) ((Advertisement) this.hmap.get(key)).getAddvTime());
					} catch (InterruptedException var4) {
						var4.printStackTrace();
					}
				}
			}

			this.timerText.setText("");
			this.adField.setText("");
			return;
		}
	}

}
