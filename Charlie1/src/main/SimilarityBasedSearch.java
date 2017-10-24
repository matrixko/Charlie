package main;

public class SimilarityBasedSearch {


	private static double windowMean(double[][] matrix, int row, int col, int width, int height) {
		
		double[][] window = new double[height][width];
		for (int i = 0; i < window.length; i++) {
			for (int j = 0; j < window[i].length; j++) {
				int indexI = i+row;
				int indexJ = j+col;
				

				window[i][j] = matrix[indexI][indexJ];
			}
		}
		return mean(window);
	}

	/**
	 * Computes the mean value of a gray-scale image given as a 2D array 
	 * @param image : a 2D double array, the gray-scale Image
	 * @return a double value between 0 and 255 which is the mean value
	 */
	public static double mean(double[][] image) {

		double sum =0;

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				sum += image[i][j];
			}
		}
		return sum/(image.length*image[0].length*1.0);
	}


	/**
	 * Computes the Normalized Cross Correlation of a gray-scale pattern if positioned
	 * at the provided row, column-coordinate in a gray-scale image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of double, the gray-scale image where to look for the pattern
	 * @return a double, the Normalized Cross Correlation value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is 0
	 */
	public static double normalizedCrossCorrelation(int row, int col, double[][] pattern, double[][] image) {
		if(row+pattern.length>=image.length)
			System.out.println("erreur1");
		if(col+pattern[0].length>=image[0].length)
			System.out.println("erreur2");
		double nominateur = 0;
		double denominateur1 = 0;
		double denominateur12 = windowMean(image, row, col, pattern[0].length, pattern.length);
		double denominateur2 =0;
		double denominateur22 = mean(pattern);
		double denominateur = 0;
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[i].length; j++) {
				//System.out.println("row "+row+", col "+col+", window width "+pattern.length+", height "+pattern[0].length);
				double val1 = image[i+row][j+col] - denominateur12;
				double val2 = pattern[i][j] - denominateur22;
				nominateur += (val1*val2);
				denominateur1 += val1*val1;
				denominateur2 += val2*val2;



			}
		}

		denominateur = Math.sqrt(denominateur1 * denominateur2);
		return nominateur/denominateur; 
	}


	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale pattern
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of doubles, the gray-scale image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image) {

		double[][] matrix = new double[image.length-pattern.length][image[0].length-pattern[0].length]; //TODO OPTI : moins gros
		//System.out.println("matrix size: "+matrix.length+" "+matrix[0].length);

		for(int row =0; row <matrix.length; row++) {
			for(int col =0; col<matrix[row].length; col++) {
				matrix[row][col] = normalizedCrossCorrelation(row, col, pattern, image);
			}
		}

		return matrix; 
	}

}
