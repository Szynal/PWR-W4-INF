package Lab3.imagemodifier.pluginsystem;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class BaseImagePlugin {
	public abstract byte[] transformImage(byte[] imageBytes);
	public abstract String getName();
	
	protected byte[] toBytesImage(BufferedImage image, String imgExtension) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, imgExtension, baos);
		byte[] result = baos.toByteArray();
		baos.close();
		
		return result;
	}
	
	protected BufferedImage toBufferedImage(byte[] imageBytes) throws IOException {
		return ImageIO.read(new ByteArrayInputStream(imageBytes));
	}
	
}
