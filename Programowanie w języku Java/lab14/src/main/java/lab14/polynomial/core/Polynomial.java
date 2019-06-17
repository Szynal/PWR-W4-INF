package lab14.polynomial.core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Class for calculating grade 3 polynomials
 * 
 * @author Pawel Szynal
 *
 */
public class Polynomial {

	private static final BigDecimal TWO_PI = new BigDecimal(2.0 * Math.PI);
	private static final BigDecimal FOUR_PI = new BigDecimal(4.0 * Math.PI);

	private int nRoots;

	private BigDecimal x1;
	private BigDecimal x2;
	private BigDecimal x3;

	public void solve(double _a, double _b, double _c, double _d) {

		BigDecimal a = new BigDecimal(_a).setScale(30, BigDecimal.ROUND_HALF_UP);
		BigDecimal b = new BigDecimal(_b).setScale(30, BigDecimal.ROUND_HALF_UP);
		BigDecimal c = new BigDecimal(_c).setScale(30, BigDecimal.ROUND_HALF_UP);
		BigDecimal d = new BigDecimal(_d).setScale(30, BigDecimal.ROUND_HALF_UP);

		if (a.equals(new BigDecimal(0))) {
			throw new RuntimeException(" a = 0");
		}

		BigDecimal denom = a;
		a = b.divide(denom, BigDecimal.ROUND_HALF_EVEN);
		b = c.divide(denom, BigDecimal.ROUND_HALF_EVEN);
		c = d.divide(denom, BigDecimal.ROUND_HALF_EVEN);

		BigDecimal a_over_3 = a.divide(new BigDecimal(3.0), BigDecimal.ROUND_HALF_EVEN);
		BigDecimal Q = b.multiply(new BigDecimal(3)).subtract(a.pow(2)).divide(new BigDecimal(9),
				BigDecimal.ROUND_HALF_EVEN);
		BigDecimal Q_CUBE = Q.pow(3);
		BigDecimal R = new BigDecimal(9).multiply(a).multiply(b).subtract(c.multiply(new BigDecimal(27)))
				.subtract(a.pow(3).multiply(new BigDecimal(2)))
				.divide(new BigDecimal(54.0), BigDecimal.ROUND_HALF_EVEN);
		BigDecimal R_SQR = R.pow(2);
		BigDecimal D = Q_CUBE.add(R_SQR);

		if (D.doubleValue() < 0.0) {
			nRoots = 3;
			BigDecimal theta = new BigDecimal(
					Math.acos(R.divide(new BigDecimal(Math.sqrt(-Q_CUBE.doubleValue())), BigDecimal.ROUND_HALF_EVEN)
							.doubleValue()));
			BigDecimal SQRT_Q = new BigDecimal(Math.sqrt(Q.negate().doubleValue()));
			x1 = new BigDecimal(Math.cos(theta.divide(new BigDecimal(3), BigDecimal.ROUND_HALF_EVEN).doubleValue()))
					.multiply(SQRT_Q).multiply(new BigDecimal(2)).subtract(a_over_3);
			x2 = new BigDecimal(
					Math.cos((theta.add(TWO_PI)).divide(new BigDecimal(3.0), BigDecimal.ROUND_HALF_EVEN).doubleValue()))
							.multiply(SQRT_Q).multiply(new BigDecimal(2)).subtract(a_over_3);
			x3 = new BigDecimal(Math
					.cos((theta.add(FOUR_PI)).divide(new BigDecimal(3.0), BigDecimal.ROUND_HALF_EVEN).doubleValue()))
							.multiply(SQRT_Q).multiply(new BigDecimal(2)).subtract(a_over_3);
			sortRoots();
		} else if (D.doubleValue() > 0.0) {
			nRoots = 1;
			BigDecimal SQRT_D = new BigDecimal(Math.sqrt(D.doubleValue()));
			BigDecimal S = new BigDecimal(Math.cbrt(R.add(SQRT_D).doubleValue()));
			BigDecimal T = new BigDecimal(Math.cbrt(R.subtract(SQRT_D).doubleValue()));
			x1 = (S.add(T)).subtract(a_over_3);
			x2 = null;
			x3 = null;
		} else {
			nRoots = 3;
			BigDecimal CBRT_R = new BigDecimal(Math.cbrt(R.doubleValue()));
			x1 = CBRT_R.multiply(new BigDecimal(2)).subtract(a_over_3);
			x2 = x3 = CBRT_R.subtract(a_over_3);
			sortRoots();
		}
	}

	private void sortRoots() {
		if (x1.doubleValue() < x2.doubleValue()) {
			BigDecimal tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
		if (x2.doubleValue() < x3.doubleValue()) {
			BigDecimal tmp = x2;
			x2 = x3;
			x3 = tmp;
		}
		if (x1.doubleValue() < x2.doubleValue()) {
			BigDecimal tmp = x1;
			x1 = x2;
			x2 = tmp;
		}
	}

	/**
	 * TEST
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Polynomial st3 = new Polynomial();
		st3.solve(1, 0, 0, 0);
		System.out.println("x1 = " + st3.x1);
		if (st3.nRoots == 3) {
			System.out.println("x2 = " + st3.x2);
			System.out.println("x3 = " + st3.x3);
		}
	}

	public String getResultString() {
		StringBuilder sb = new StringBuilder();

		sb.append("x1 = ").append(x1.round(new MathContext(30, RoundingMode.HALF_UP))).append("\n");
		if (nRoots == 3) {
			sb.append("x2 = ").append(x2.round(new MathContext(30, RoundingMode.HALF_UP))).append("\n");
			sb.append("x3 = ").append(x3.round(new MathContext(30, RoundingMode.HALF_UP))).append("\n");
		}

		return sb.toString();
	}
}
