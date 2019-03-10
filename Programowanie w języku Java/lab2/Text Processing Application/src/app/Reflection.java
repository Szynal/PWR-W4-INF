package app;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Reflection {

	private URLClassLoader urlClassLoader = null;
	private String className = null;
	private String loaded;

	public Reflection(String url, String className) {

		try {
			setUrlClassLoader(new URLClassLoader(new URL[] { new URL(url) }));
			this.setClassName(className);
			setLoaded("Unloaded");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public URLClassLoader getUrlClassLoader() {
		return urlClassLoader;
	}

	public void setUrlClassLoader(URLClassLoader urlClassLoader) {
		this.urlClassLoader = urlClassLoader;
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

}
