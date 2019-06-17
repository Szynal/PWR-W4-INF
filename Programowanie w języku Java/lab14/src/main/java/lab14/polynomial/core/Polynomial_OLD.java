package lab14.polynomial.core;

import java.util.ArrayList;

public class Polynomial_OLD extends ArrayList<Double> {

	private static final long serialVersionUID = -449796883768511665L;

	/**
	 * no-arg constructor returns a polynomial of degree 1, with value 0
	 */

	public Polynomial_OLD() {
		super(1);
		this.add(0, 0.0);
	}

	/**
	 * get the degree of the polynomial. Always >= 1
	 * 
	 * @return degree of the polynomial
	 */
	public int getDegree() {
		return this.size() - 1;
	}

	/**
	 * 
	 * @param coeffs array of coefficients from largest degree down to smallest.
	 */
	public Polynomial_OLD(int[] coeffs) {
		super(coeffs.length);
		for (int i = 0; i < coeffs.length; i++) {
			this.add(0, (double) coeffs[i]);
		}
	}

	/**
	 * Construct polynomial from double array of coefficients.
	 */
	public Polynomial_OLD(double[] coeffs) {
		// invoke superclass constructor, i.e. the
		// constructor of ArrayList<Double> with
		// capacity at least as large as we need

		super(coeffs.length);
		for (int i = coeffs.length - 1; i >= 0; i--) {
			this.add(coeffs[i]);
		}
	}

	public String toString() {

		String result = "";

		for (int i = this.getDegree(); i >= 0; i--) {
			result += (get(i) + "x^" + i + " ");
		}

		return result;
	}

	public boolean equals(Object o) {

		if (o == null)
			return false;
		if (!(o instanceof Polynomial_OLD))
			return false;

		Polynomial_OLD p = (Polynomial_OLD) o;

		final double TOL = 0.000001;
		return false;
	}
}