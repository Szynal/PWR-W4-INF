package lab08;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import lab08.soap.interfaces.IMonitorService;

@WebService(endpointInterface = "lab08.soap.interfaces.IMonitorService")
public class GazetomatService implements IMonitorService {

	private int lastId;
	private List<Integer> vendingMachineIds;

	GazetomatService() {
		lastId = -1;
		vendingMachineIds = new ArrayList<Integer>();
	}

	@Override
	public int register() {
		++lastId;
		vendingMachineIds.add(lastId);
		return lastId;
	}

	@Override
	public void unregister(int id) {
		vendingMachineIds.remove(id);
	}

	public List<Integer> getVendingMachinesIds() {
		return vendingMachineIds;
	}
}