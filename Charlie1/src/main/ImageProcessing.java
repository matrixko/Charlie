package main;

import java.util.Arrays;

public final class ImageProcessing {
	
	public static void main(String[] args) {
		
	}
	
    /**
     * Returns red component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer,  between 0 and 255
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getRed(int rgb) {

    	// TODO implement me !
    	return 0b11111111 & (rgb >> 16); 
    }

    /**
     * Returns green component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getGreen(int rgb) {
    	// TODO implement me !
    	return 0b11111111 & (rgb >> 8); 
    }

    /**
     * Returns blue component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getRGB(int, int, int)
     */
    public static int getBlue(int rgb) {
    	// TODO implement me !
        return 0b11111111 & rgb;
    }

   
    /**
     * Returns the average of red, green and blue components from given packed color.
     * @param rgb : 32-bits RGB color
     * @return a double between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int)
     */
    public static double getGray(int rgb) {
        return (getRed(rgb) + getGreen(rgb) + getBlue(rgb))/3.0;
    }
    
    private static int checkColorBound(int color) {
    	if(color<0)
    		return 0;
    	if(color >255)
    		return 255;
    	return color;
    }

    /**
     * Returns packed RGB components from given red, green and blue components.
     * @param red : an integer 
     * @param green : an integer 
     * @param blue : an integer
     * @return a 32-bits RGB color
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    public static int getRGB(int red, int green, int blue) {
    	red = checkColorBound(red);
    	green = checkColorBound(green);
    	blue = checkColorBound(blue);
    int rgb = (((red << 8) | green) << 8) | blue;
    	return rgb; 
    }

    /**
     * Returns packed RGB components from given gray-scale value.
     * @param gray : an integer 
     * @return a 32-bits RGB color
     * @see #getGray
     */
    public static int getRGB(double gray) {
    	int gray2 = checkColorBound((int)gray);
    	
    	return (((gray2 << 8) | gray2) << 8) | gray2; 
    }

    /**
     * Converts packed RGB image to gray-scale image.
     * @param image : a HxW integer array
     * @return a HxW double array
     * @see #encode
     * @see #getGray
     */
    public static double[][] toGray(int[][] image) {
    	
    	double[][] gray = new double[image.length][image[0].length];
    	for(int ligne=0; ligne<image.length; ligne++) {
    		for(int col=0; col<image[ligne].length; col++) {
    			gray[ligne][col] = getGray(image[ligne][col]);
    		}
    	}
    	
    	return gray;
    }

    /**
     * Converts gray-scale image to packed RGB image.
     * @param channels : a HxW double array
     * @return a HxW integer array
     * @see #decode
     * @see #getRGB(double)
     */
    public static int[][] toRGB(double[][] gray) {

    	int[][] rgb = new int[gray.length][gray[0].length];
    	for(int ligne=0; ligne<gray.length; ligne++) {
    		for(int col=0; col<gray[ligne].length; col++) {
    			rgb[ligne][col] = getRGB(gray[ligne][col]);
    		}
    	}
    	
    	return rgb;
    }

    
    /**
     * Convert an arbitrary 2D double matrix into a 2D integer matrix 
     * which can be used as RGB image
     * @param matrix : the arbitrary 2D double array to convert into integer
     * @param min : a double, the minimum value the matrix could theoretically contains
     * @param max : a double, the maximum value the matrix could theoretically contains
     * @return an 2D integer array, containing a RGB mapping of the matrix 
     */
    public static int[][] matrixToRGBImage(double[][] matrix, double min, double max) {
    	int[][] rgb = new int[matrix.length	][matrix[0].length];
    	for(int row=0; row<matrix.length; row++) {
    		for(int col=0; col<matrix[0].length; col++) {
    			double matrixValue = matrix[row][col];
    			rgb[row][col] = getRGB(255*(matrixValue-min)/(max-min));
    		}
    	}
    	return rgb;
    }
}
