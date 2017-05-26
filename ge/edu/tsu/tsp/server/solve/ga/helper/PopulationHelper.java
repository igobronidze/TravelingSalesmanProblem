package ge.edu.tsu.tsp.server.solve.ga.helper;

import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.ga.main.Population;
import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

public class PopulationHelper {

	public static Population getRandomPopulation(int size, Graph graph) {
		Population population = new Population();
		for (int i = 0; i < size; i++) {
			Tour tour = TourHelper.getRandomTour(graph);
			population.getTours().add(tour);
		}
		return population;
	}
}
