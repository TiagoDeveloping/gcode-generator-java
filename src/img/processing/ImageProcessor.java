package img.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

public class ImageProcessor {

	private boolean chunking = true;
	private File imageFile;
	private BufferedImage buffImg;
	
	private int threshold = Color.black.getRGB() / 2;
	
	private int BLACK = Color.black.getRGB();
	private int WHITE = Color.white.getRGB();
	
	/**
	 * @param boolean chunking, chunks avg grayscale of pixels into one(faster)
	 */
	public ImageProcessor(boolean chunking, File file) throws IOException {
		this.imageFile = file;
		this.chunking = chunking;
		
		init();
	}
	
	
	/**
	 * @implNote chunking enabled.
	 */
	public ImageProcessor(File file) throws IOException {
		this.imageFile = file;
		
		init();
	}
	
	private void init() throws IOException {
		buffImg = ImageIO.read(imageFile);
	}
	
	/**
	 * @implNote assumes targetFile exists!
	 * 
	 * @param targetFile File to write image to in black and white;
	 */
	public void makeGreyScaleImage(File targetFile) {
		try {
			BufferedImage targetImg = ImageIO.read(targetFile);
			
			int width, height;
			
			width = buffImg.getWidth();
			height = buffImg.getHeight();
			
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int gs = greyScale(buffImg.getRGB(x, y));
					if (gs > threshold) {
						targetImg.setRGB(x, y, WHITE);
					} else {
						targetImg.setRGB(x, y, BLACK);
					}
				}
			}
			
			System.out.println(ImageIO.write(targetImg, "png", targetFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private int greyScale(int RGB) {
		Color c = new Color(RGB);
		
		int red = (int)(c.getRed());// * 0.299);
		int green = (int)(c.getGreen());// * 0.587);
		int blue = (int)(c.getBlue());// * 0.114);
		
		int sum = red + green + blue;
		
		Color b = new Color(sum / 3, sum / 3, sum / 3);
		
		return b.getRGB();
	}

}
