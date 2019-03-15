package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ICentrala extends Remote {

	public int zarejestrujBramke(Object bramka) throws RemoteException;

	public boolean wyrejestrujBramke(int nrBramki) throws RemoteException;

	public ArrayList<Object> getZarejestrowaneBramki() throws RemoteException;

	public void zarejestrujMonitor(Object o) throws RemoteException;

	public void wyrejestrujMonitor() throws RemoteException;

	public Object getMonitor() throws RemoteException;
}
