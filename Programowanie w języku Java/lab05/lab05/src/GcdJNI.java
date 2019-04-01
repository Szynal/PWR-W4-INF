
public class GcdJNI {

	// Exception in thread "main" java.lang.UnsatisfiedLinkError:
	// C:\Users\szyna\Desktop\Documents\PWR-W4-INF\Programowanie w języku
	// Java\lab05\GCD\Debug\GCD.dll: Can't load IA 32-bit .dll on a AMD 64-bit
	// platform

	/**
	 * Vector "A"
	 */
	public Double[] a;
	/**
	 * Vector "B"
	 */
	public Double[] b;
	/**
	 * Scalar product of two vectors
	 */
	public Double c;
	/**
	 * Vector dimension
	 */
	public int Dimension;

	private static String dllPath = "C:\\Users\\szyna\\Desktop\\Documents\\PWR-W4-INF\\Programowanie w języku Java\\lab05\\GCD\\x64\\Debug\\GCD.dll";

	static {
		System.load(dllPath);
	}

	/**
	 * A=(a1;a2)
	 * 
	 * B=(b1;b2)
	 * 
	 * A⋅B=a1⋅b1+a2⋅b2
	 */

	/**
	 * 
	 * @param a vector
	 * @param b vector
	 */
	public native Double multi01(Double[] a, Double[] b);

	/**
	 * 
	 * @param a vector
	 * @return Scalar product of two vectors (we assume that the second attribute
	 *         will be taken from the object passed to the native method)
	 */
	public native Double multi02(Double[] a);

	/**
	 * we assume that on the native side a window will be created for attributes,
	 * and after reading and rewriting them to a, b, the result will be calculated.
	 * The result should be calculated using the Java multi04 method (using
	 * parameters a, b and entering the result in c).
	 */
	public native void multi03();

	/**
	 * Multiplies a and b, the result is entered in c variable
	 */
	public void multi04() {
		if (a.length == b.length) {
			Dimension = a.length;
			c = 0.0;
			for (int i = 0; i < Dimension; ++i) {
				c += (a[i] * b[i]);
			}
		}
	}

}
