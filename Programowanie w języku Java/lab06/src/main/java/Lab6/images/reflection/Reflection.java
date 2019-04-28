package Lab6.images.reflection;

import java.io.File;

public class Reflection {

	private String path = null;
	private String name = null;
	private String loaded;
	private File file = null;

	public Reflection(String url, String className, File file) {

		this.path = url;
		this.setName(className);
		this.file = file;
		setLoaded("Unloaded");
	}

	public String getName() {
		return name;
	}

	public void setName(String className) {
		this.name = className;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
