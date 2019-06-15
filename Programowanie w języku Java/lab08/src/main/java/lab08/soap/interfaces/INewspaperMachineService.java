package lab08.soap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface INewspaperMachineService {

	@WebMethod
	public int getProductMenuAmount();

	@WebMethod
	public int getProductAmount(int index);

	@WebMethod
	public String getProduct(int index);
}