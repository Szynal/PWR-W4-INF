package Lab3.imagemodifier.gui;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ThumbnailsLoaderWorker extends SwingWorker<Integer, String> {
	final List<String> SUPPORTED_IMAGE_EXTENSIONS = Arrays.asList(".jpg");

	boolean shouldCancel = false;
	ForkJoinPool customThreadPool = new ForkJoinPool(3);

	File directory;
	JPanel panelThumbnails;
	MouseAdapter thumbnailClicked;

	Object counterMx = new Object();
	int counter;

	public void terminate() {
		shouldCancel = true;
		customThreadPool.shutdown();
	}

	public ThumbnailsLoaderWorker(final File directory, final JPanel panelThumbnails, MouseAdapter thumbnailClicked) {
		this.directory = directory;
		this.panelThumbnails = panelThumbnails;
		this.thumbnailClicked = thumbnailClicked;
	}

	@Override
	protected Integer doInBackground() throws Exception {

		String dirPath = directory.getAbsolutePath();

		File[] allImageFiles = fileFinder(dirPath, SUPPORTED_IMAGE_EXTENSIONS);

		if (allImageFiles == null || allImageFiles.length == 0)
			return null;

		System.out.println("You selected " + dirPath + " It contains " + allImageFiles.length);

		panelThumbnails.removeAll();

		Arrays.asList(allImageFiles).forEach(file -> {
			Thumbnail t = new Thumbnail(file);
			t.addMouseListener(thumbnailClicked);
			panelThumbnails.add(t);
		});

		return null;
	}

	public File[] fileFinder(String dirName, final List<String> supportedExtensions) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return supportedExtensions.stream().anyMatch(x -> filename.endsWith(x));
			}
		});
	}
}
