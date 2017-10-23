package main;

public class Program {

	public static void main(String[] args) {
		System.out.println("Testing program");
		int[][] image = Helper.read("images/charlie_beach.png");
		int[][] charlie = Helper.read("images/charlie.png");

		double[][] distance = DistanceBasedSearch.distanceMatrix(charlie, image);
		
		int[] p = Collector.findBest(distance, true);
		System.out.println("best ="+p[0]+" "+p[1]
				);
		Helper.drawBox(p[0], p[1], charlie[0].length, charlie.length, image);
		Helper.show(image, "Found!");
	}

}
