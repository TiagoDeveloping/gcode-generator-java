package main;

import java.io.File;
import java.io.IOException;

import img.processing.ImageProcessor;

public class Main {

	public static void main(String[] args) {
		try {
			File srcFile = new File(System.getProperty("user.dir") + "/assets/file.png");
			File trgFile = new File(System.getProperty("user.dir") + "/assets/target.png");
			ImageProcessor imgProc = new ImageProcessor(srcFile);
			
			imgProc.makeGreyScaleImage(trgFile);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
