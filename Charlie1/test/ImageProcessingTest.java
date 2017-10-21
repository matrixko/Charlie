import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import main.ImageProcessing;

class ImageProcessingTest {

	@Test
	public void RGB() {
		int color = 0b11110000_00001111_01010101;
		assertEquals(240, ImageProcessing.getRed(color));
		assertEquals(15, ImageProcessing.getGreen(color));
		assertEquals(85, ImageProcessing.getBlue(color));
		double gray = ImageProcessing.getGray(color);
		assertEquals(113.333, gray, 0.001);
		
		assertEquals(255, ImageProcessing.getRGB(0, 0, 255), 0.001);
		assertEquals(8355711, ImageProcessing.getRGB(127.0));
		assertEquals(255, ImageProcessing.getRGB(-175, 0, 255), 0.001);
		assertEquals(0, ImageProcessing.getRGB(-255), 0.001);


	}
	
	@Test
	public void tabWorks() {
		int[][] image = { {0x20c0ff, 0x123456}, {0xffffff, 0x000000}
		};
		double[][] gray = ImageProcessing.toGray(image);
		double[][] correctGray = { 
				{159.66666, 52.0},
				{255.0, 0.0} };
		for(int ligne=0; ligne<gray.length; ligne++) {
			for(int col=0; col<gray[ligne].length; col++) {
				assertEquals(gray[ligne][col], correctGray[ligne][col], 0.00001);
			}
		
		}
		
		int[][] back = ImageProcessing.toRGB(gray);
		int[][] correctBack = {{10526880, 3421236}, {0x343434, 0}};
		
		for(int ligne=0; ligne<back.length; ligne++) {
			for(int col=0; col<back[ligne].length; col++) {
				assertEquals(back[ligne][col], correctBack[ligne][col], 0.00001);
			}
		
		}
	}
	

}
