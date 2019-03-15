package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMonitor extends Remote {
	public void koniecznaAktualizacja() throws RemoteException;
}
