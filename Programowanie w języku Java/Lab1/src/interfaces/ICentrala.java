package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 
 * @author Pawel Szynal 226026
 * @version Lab1 RMI
 *          {@link http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/Zadania/ZadRMI_01.txt}
 */
public interface ICentrala extends Remote {

	/**
	 * Do rejestrowania bramki w systemie, wynik – numer przydzielony bramce (bramka
	 * musi wpisać sobie ten numer po wywołaniu zdalnym) lub wartość ujemna, gdy
	 * operacja nieudana.
	 * 
	 * @param bramka
	 * @return
	 * @throws RemoteException
	 */
	public int zarejestrujBramke(Object bramka) throws RemoteException;

	/**
	 * Do wyrejestrowywania bramek
	 * 
	 * @param nrBramki
	 * @return
	 * @throws RemoteException
	 */
	public boolean wyrejestrujBramke(int nrBramki) throws RemoteException;

	/**
	 * Do pobierania listy bramek aktywnych (w celu monitorowania ich "aktywności")
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<Object> getZarejestrowaneBramki() throws RemoteException;

	/**
	 * Do rejestracji w centrali namiastki monitora (w celu późniejszego
	 * informowania o konieczności aktualizacji)
	 * 
	 * @param o
	 * @throws RemoteException
	 */
	public void zarejestrujMonitor(Object o) throws RemoteException;

	public void wyrejestrujMonitor() throws RemoteException;

	public Object getMonitor() throws RemoteException;
}