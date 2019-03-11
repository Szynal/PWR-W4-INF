package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IBramka extends Remote {

	public int[] getStatystyka(Date pocz, Date kon) throws RemoteException;

	public boolean zamknijBramke() throws RemoteException;

	public int getNumer() throws RemoteException;
}