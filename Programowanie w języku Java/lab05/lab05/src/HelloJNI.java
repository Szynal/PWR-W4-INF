
public class HelloJNI { // Save as HelloJNI.java
	static {
		System.load(
				"C:\\Users\\szyna\\Desktop\\Documents\\PWR-W4-INF\\Programowanie w jêzyku Java\\lab05\\GCD\\x64\\Debug\\GCD.dll");
	}

	// Declare an instance native method sayHello() which receives no parameter and
	// returns void
	private native void sayHello();

	// Test Driver
	public static void main(String[] args) {
		new HelloJNI().sayHello(); // Create an instance and invoke the native method
	}
}