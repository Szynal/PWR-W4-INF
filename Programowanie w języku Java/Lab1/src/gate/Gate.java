package gate;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.UUID;

import interfaces.IBramka;

public class Gate implements IBramka {

	private String uniqueID;
	private int gateNumber;
	private int transitionCounter;
	private GateSate state;

	public enum GateSate {
		TurnedOn, TurnedOff, Broken;
	}

	public Gate(int number) {
		uniqueID = UUID.randomUUID().toString();
		gateNumber = number;
		state = GateSate.TurnedOff;
		transitionCounter = 0;
	}

	@Override
	public int[] getStatystyka(Date pocz, Date kon) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean zamknijBramke() throws RemoteException {
		state = GateSate.TurnedOff;
		return false;
	}

	@Override
	public int getNumer() throws RemoteException {
		return getGateNumber();
	}

	public int getGateNumber() {
		return gateNumber;
	}

	public void setGateNumber(int gateNumber) {
		this.gateNumber = gateNumber;
	}

	public int getTransitionCounter() {

		return transitionCounter;
	}

	public void setTransitionCounter(int transitionCounter) {
		this.transitionCounter = transitionCounter;
	}

	public GateSate getState() {
		return state;
	}

	public void setState(GateSate state) {
		this.state = state;
	}

}
