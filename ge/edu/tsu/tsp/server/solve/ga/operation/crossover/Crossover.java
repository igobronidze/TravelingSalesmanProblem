package ge.edu.tsu.tsp.server.solve.ga.operation.crossover;

import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

public interface Crossover {

	Tour applyCrossover(Tour tour1, Tour tour2);
}
