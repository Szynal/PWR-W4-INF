package lab08.soap.core;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 5849753695354247012L;
	private String name;
	private int amount;

	Product() {
		name = "7Days";
		amount = 10;
	}

	Product(Product product) {
		name = product.name;
		amount = product.amount;
	}

	public Product(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}
}
