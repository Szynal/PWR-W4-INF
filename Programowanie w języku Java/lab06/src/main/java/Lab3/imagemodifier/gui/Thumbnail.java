package Lab3.imagemodifier.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.WeakHashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Thumbnail extends JLabel implements Runnable {

	private static final long serialVersionUID = -4900196832378830115L;
	private static WeakHashMap<String, Icon> weakMap = new WeakHashMap<String, Icon>(128);

	private File imageFile;
	private byte[] image;
	private Thread imageExtractionThread;

	public Thumbnail(File imageFile) {
		this.imageFile = imageFile;
		this.setText(getImageFile().getName());
		System.out.println("CTOR ");
		loadImageAndSetIcon();
	}

	private void loadImageAndSetIcon() {
		try {

			imageExtractionThread = new Thread(this);
			imageExtractionThread.start();

			Icon ico = null;
			// dopoki nie dostanie instancji ikonki
			while (ico == null) {
				ico = getImageIconThumbnailFromCacheOrImageBytes(); // Get Icon from cache or from loaded Image
				Thread.sleep(100);
			}

			this.setIcon(ico);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Thumbnail(byte[] image) {
		this.image = image;

		try {
			this.setIcon(getIconFromImageBytes(200, 200));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public byte[] extractImage(File imagePath) throws IOException {
		return Files.readAllBytes(imagePath.toPath());
	}

	public File getImageFile() {
		return imageFile;
	}

	public byte[] getOrginalImageBytes() {
		return this.image;
	}

	public Icon getImageIconThumbnailFromCacheOrImageBytes() throws IOException {
		int width = 40, height = 40;
		return getImageIconThumbnailFromCacheOrImageBytes(width, height);		
	}
	
	public Icon getImageIconThumbnailFromCacheOrImageBytes(int width, int height) throws IOException {	
		String key = imageFile.getAbsolutePath();
		Icon icon;

		icon = getIconFromCache(key);

		if (icon == null) {
			icon = getIconFromImageBytes(width, height);
			saveToCache(key, icon);
		}

		return icon;
	}

	private void saveToCache(String key, Icon icon) {
		if (icon != null) {
			weakMap.put(key, icon);
			System.out.println("SAVED");
		}
	}

	private Icon getIconFromImageBytes(int width, int height) throws IOException {
		byte[] bytes = getOrginalImageBytes();
		if (bytes == null)
			return null;
		BufferedImage imgOrg = ImageIO.read(new ByteArrayInputStream(bytes));
		System.out.println("FROM BYTES");
		Image newImg = imgOrg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}

	private Icon getIconFromCache(String key) {
		if (weakMap.containsKey(key)) {
			System.out.println("CACHED");
			return weakMap.get(key);
		}
		return null;
	}

	@Override
	public void run() {
		try {
			image = extractImage(getImageFile());
			System.out.println("EXTRACTION DONE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
