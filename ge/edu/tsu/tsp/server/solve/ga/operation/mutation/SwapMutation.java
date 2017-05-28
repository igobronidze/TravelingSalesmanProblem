package ge.edu.tsu.tsp.server.solve.ga.operation.mutation;

import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.Random;

public class SwapMutation implements Mutation {

	@Override
	public Tour applyMutation(Tour tour) {
		Random ran = new Random();
		int x = ran.nextInt(tour.getNodeIndexes().size());
		int y = ran.nextInt(tour.getNodeIndexes().size());
		while (y == x) {
			y = ran.nextInt(tour.getNodeIndexes().size());
		}
		int k = tour.getNodeIndexes().get(x);
		tour.getNodeIndexes().set(x, tour.getNodeIndexes().get(y));
		tour.getNodeIndexes().set(y, k);
		return new Tour(tour.getNodeIndexes(), tour.getGraph());
	}
}
