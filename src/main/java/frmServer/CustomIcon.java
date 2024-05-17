package frmServer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CustomIcon extends ImageIcon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage bufferedImage;

	/**
	 * @return the bufferedImage
	 */
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	/**
	 * @param bufferedImage the bufferedImage to set
	 */
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	/**
	 * @param bufferedImage the bufferedImage to set
	 */
	public void setBufferedImage(String url) {
		try {
			bufferedImage = ImageIO.read(new File(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CustomIcon(BufferedImage bufferedImage) {
		super();
		setBufferedImage(bufferedImage);
		setImage(bufferedImage);
	}
	public CustomIcon(String url,int x,int y) {
		super();
		setBufferedImage(url);
		resizeImage(x, y);
		setImage(bufferedImage);
	}
	public CustomIcon(String url) {
		super();
		setBufferedImage(url);
		setImage(bufferedImage);
	}
	public void resizeImage(int x,int y) {
		bufferedImage = CustomFunction.resize(bufferedImage, x, y);
		setBufferedImage(bufferedImage);
	}
	public void reconnerImage(int radius) {
		bufferedImage = CustomFunction.makeRoundedCorner(bufferedImage, radius);
		setBufferedImage(bufferedImage);
	}
}
