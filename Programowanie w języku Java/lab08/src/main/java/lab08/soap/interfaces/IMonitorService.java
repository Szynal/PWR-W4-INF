package lab08.soap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IMonitorService {

	@WebMethod
	public int register();

	@WebMethod
	public void unregister(int id);
}