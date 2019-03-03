package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * @author Pawel Szynal 226026
 *
 */
public interface IBramka extends Remote {

	/**
	 * Pobiera informacje o statystyce - wykorzystywana przez Monitors Statystyka to
	 * tablica z dwiema wartoœciami: liczba wejœæ, liczba wyjœæ
	 * 
	 * @param pocz
	 * @param kon
	 * @return
	 * @throws RemoteException
	 */
	public int[] getStatystyka(Date pocz, Date kon) throws RemoteException;

	/**
	 * Zdalnie zatrzymuje bramki
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public boolean zamknijBramke() throws RemoteException;

	/**
	 * Pobiera numer bramki
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public int getNumer() throws RemoteException;
}