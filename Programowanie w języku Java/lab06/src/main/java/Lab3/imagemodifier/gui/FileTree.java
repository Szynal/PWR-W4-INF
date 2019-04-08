package Lab3.imagemodifier.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTree extends JPanel {

	private static final long serialVersionUID = 8546347094252577286L;
	JTree tree;

	public FileTree(File directory) {
		setLayout(new BorderLayout());
		tree = new JTree(addNodesToDirectory(null, directory));

		JScrollPane scrollpane = new JScrollPane();
		scrollpane.getViewport().add(tree);
		add(BorderLayout.CENTER, scrollpane);
	}

	DefaultMutableTreeNode addNodesToDirectory(DefaultMutableTreeNode currentTop, File directory) {
		String currentPath = directory.getPath();
		DefaultMutableTreeNode curDirectory = new DefaultMutableTreeNode(currentPath);

		if (currentTop != null) { // nie dla roota
			currentTop.add(curDirectory);
		}

		Vector<String> directoryElementsNamesList = new Vector<String>(Arrays.asList(directory.list()));
		Collections.sort(directoryElementsNamesList, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < directoryElementsNamesList.size(); i++) {
			String directoryElementName = (String) directoryElementsNamesList.elementAt(i);
			addDirectoryElementToDirectory(currentPath, curDirectory, directoryElementName);
		}
		return curDirectory;
	}

	private void addDirectoryElementToDirectory(String currentPath, DefaultMutableTreeNode curDirectory,
			String directoryElementName) {
		File file;

		String newPath = currentPath + File.separator + directoryElementName;

		if ((file = new File(newPath)).isDirectory())
			addNodesToDirectory(curDirectory, file); // rekurencja folderu
		else {
			curDirectory.add(new DefaultMutableTreeNode(directoryElementName)); // dodaj plik do tree
		}
	}

	public Dimension getMinimumSize() {
		return new Dimension(200, 400);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 400);
	}

	public void addTreeItemSelected(TreeSelectionListener e) {
		tree.addTreeSelectionListener(e);
	}

}
