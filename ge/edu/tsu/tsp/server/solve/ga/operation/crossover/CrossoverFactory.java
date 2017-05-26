package ge.edu.tsu.tsp.server.solve.ga.operation.crossover;

public class CrossoverFactory {

	public static Crossover createCrossover(CrossoverType type) {
		Crossover crossover = null;
		switch (type) {
			case PARTIAL_MAPPED_CROSSOVER:
				crossover = new PartiallyMappedCrossover();
				break;
		}
		return crossover;
	}
}
