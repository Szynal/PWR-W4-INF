package lab13.jmx.advertisement;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.ReflectionException;

import lab13.jmx.interfaces.IAdvertisementMBean;

public class Advertisement implements IAdvertisementMBean, DynamicMBean {
	private String addvString;
	private int addvTime;

	public Advertisement() {
	}

	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException, ReflectionException {
		return null;
	}

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		return null;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		return null;
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		return null;
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		return null;
	}

	public String getAddvString() {
		return this.addvString;
	}

	public void setAddvString(String addvString) {
		this.addvString = addvString;
	}

	public int getAddvTime() {
		return this.addvTime;
	}

	public void setAddvTime(int addTime) {
		this.addvTime = addTime;
	}

}
