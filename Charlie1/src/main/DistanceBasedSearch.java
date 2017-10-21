package main;

public class DistanceBasedSearch {

	/**
	 * Computes the mean absolute error between two RGB pixels, channel by channel.
	 * @param patternPixel : a integer, the second RGB pixel.
	 * @param imagePixel : a integer, the first RGB pixel.
	 * @return a double, the value of the error for the RGB pixel pair. (an integer in [0, 255])
	 */
	public static double pixelAbsoluteError(int patternPixel, int imagePixel) {
		int patternRed = ImageProcessing.getRed(patternPixel);
		int patternGreen = ImageProcessing.getGreen(patternPixel);
		int patternBlue = ImageProcessing.getBlue(patternPixel);
		
		int imageRed = ImageProcessing.getRed(imagePixel);
		int imageGreen = ImageProcessing.getGreen(imagePixel);
		int imageBlue = ImageProcessing.getBlue(imagePixel);
		
		return (Math.abs(patternRed-imageRed)+Math.abs(patternGreen-imageGreen) + Math.abs(patternBlue-imageBlue))
				/3.0;
	}

	/**
	 * Computes the mean absolute error loss of a RGB pattern if positioned
	 * at the provided row, column-coordinates in a RGB image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a double, the mean absolute error
	 * @return a double, mean absolute error value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is -1
	 */
	public static double meanAbsoluteError(int row, int col, int[][] pattern, int[][] image) {
		if(!(row+pattern.length<image.length  && col+pattern[0].length< image[0].length))
			throw new IllegalArgumentException("peu pas colle cette image");
		int d = pattern.length*pattern[0].length;
		double error = 0;
		for(int ligne = 0; ligne<pattern.length; ligne++) {
			for(int colonne = 0; colonne<pattern[0].length; colonne++) {
				error += pixelAbsoluteError(pattern[ligne][colonne], 
						image[row+ligne][col + colonne]);
			}
		}
		return error/d; 
	}

	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original RGB image, 
	 * the distance (meanAbsoluteError) between the image's window and the pattern
	 * placed over this pixel (upper-left corner) 
	 */
	public static double[][] distanceMatrix(int[][] pattern, int[][] image) {

		double[][] matrix = new double[image.length][image[0].length]; //TODO OPTI : moins gros
		
		for(int row =0; row+pattern.length<image.length; row++) {
			for(int col =0; col+pattern[0].length<image[0].length; col++) {
				matrix[row][col] = meanAbsoluteError(row, col, pattern, image);
			}
		}

		return matrix; 
	}
}
