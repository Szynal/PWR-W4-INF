package app;

public class Reflection {

	private String path = null;
	private String className = null;
	private String loaded;

	public Reflection(String url, String className) {

		setClassName(url);
		this.setClassName(className);
		setLoaded("Unloaded");
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLoaded() {
		return loaded;
	}

	public void setLoaded(String loaded) {
		this.loaded = loaded;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
