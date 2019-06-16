package lab08.soap.core;

import java.io.Serializable;

public class Newspaper implements Serializable {

	private static final long serialVersionUID = 5849753695354247012L;
	private String name;
	private int amount;

	Newspaper() {
		name = "Newspaper";
		amount = 10;
	}

	Newspaper(Newspaper product) {
		name = product.name;
		amount = product.amount;
	}

	public Newspaper(String name, int amount) {
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
