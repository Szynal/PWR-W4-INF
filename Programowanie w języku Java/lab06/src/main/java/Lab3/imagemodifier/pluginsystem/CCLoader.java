package Lab3.imagemodifier.pluginsystem;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Lab3.imagemodifier.gui.MainFrame;

public class CCLoader extends ClassLoader {

	static CCLoader instance;

	public static CCLoader get() {
		if (instance == null)
			instance = new CCLoader(MainFrame.class.getClassLoader());

		return instance;
	}

	public static void unload() {
		instance = null;
	}


	// This constructor is used to set the parent ClassLoader	
	public CCLoader(ClassLoader parent) {
		super(parent);
	}

	private Class<?> getClass(String name) throws ClassNotFoundException {
		String file = name.replace('.', File.separatorChar) + ".class";

		byte[] b = null;
		try {
			// This loads the byte code data from the file
			b = loadClassFileData(file);
			// defineClass is inherited from the ClassLoader class
			// that converts byte array into a Class. defineClass is Final
			// so we cannot override it
			Class<?> c = defineClass(name, b, 0, b.length);
			resolveClass(c);
			return c;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("Loading Class '" + name + "'");
		if (name.startsWith("Lab3.imagemodifier.pluginsystem.plugins")) {
			System.out.println("Loading Class using CCLoader");
			return getClass(name);
		}
		return super.loadClass(name);
	}

	public List<Class<?>> loadImagePluginClasses(File pluginFolder) {

		List<File> pluginFiles = Arrays.asList(pluginFolder.listFiles()).stream()
				.filter(x -> x.getName().endsWith("Plugin.class")).collect(Collectors.toList());

		CCLoader ccl = CCLoader.get();

		return pluginFiles.stream().map(f -> {
			String fileName = f.getName().substring(0, f.getName().length() - 6);

			Class<?> clazz = null;
			try {
				clazz = ccl.loadClass("Lab3.imagemodifier.pluginsystem.plugins." + fileName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			return clazz;
		}).filter(x -> x != null).collect(Collectors.toList());

	}

	private byte[] loadClassFileData(String name) throws IOException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}
}