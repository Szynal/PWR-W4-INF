package lab08;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import lab08.soap.core.Product;
import lab08.soap.interfaces.INewspaperMachineService;

@WebService(endpointInterface = "lab08.soap.interfaces.INewspaperMachineService")
public class NewspaperMachineService implements INewspaperMachineService {

	private static List<Product> productList;

	public NewspaperMachineService() {
		productList = new ArrayList<Product>();
	}

	public void buyProduct(String productName) {
		int size = productList.size();

		for (int i = 0; i < size; ++i) {
			if (productList.get(i).getName().equals(productName)) {
				int productAmount = productList.get(i).getAmount();
				if (productAmount == 1) {
					productList.remove(productList.get(i));
					return;
				} else {
					--productAmount;
					productList.get(i).setAmount(productAmount);
					return;
				}
			}
		}
	}

	public void addProduct(Product product) {
		productList.add(product);
	}

	public Product[] getProducts() {
		int size = productList.size();
		Product[] array = new Product[size];

		for (int i = 0; i < size; ++i) {
			array[i] = new Product(productList.get(i).getName(), productList.get(i).getAmount());
		}
		return array;
	}

	@Override
	public int getProductMenuAmount() {
		return productList.size();
	}

	@Override
	public int getProductAmount(int index) {
		int size = productList.size();

		if (index < size) {
			return productList.get(index).getAmount();
		}

		return 0;
	}

	@Override
	public String getProduct(int index) {
		int size = productList.size();

		if (index < size) {
			return productList.get(index).getName();
		}
		return "";
	}
}