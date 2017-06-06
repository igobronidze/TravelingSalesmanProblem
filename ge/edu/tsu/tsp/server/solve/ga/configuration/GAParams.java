package ge.edu.tsu.tsp.server.solve.ga.configuration;

import ge.edu.tsu.tsp.server.solve.ga.operation.crossover.CrossoverType;
import ge.edu.tsu.tsp.server.solve.ga.operation.mutation.MutationType;
import ge.edu.tsu.tsp.server.solve.ga.operation.selection.SelectionType;

public class GAParams {

	public static int POPULATION_SIZE = 100;

	public static int SURVIVED_EXEMPLARS = 2;

	public static SelectionType SELECTION_TYPE = SelectionType.ROULETTE_WHEEL_SELECTION;

	public static CrossoverType CROSSOVER_TYPE = CrossoverType.PARTIAL_MAPPED_CROSSOVER;

	public static MutationType MUTATION_TYPE = MutationType.SWAP_MUTATION;

	public static double MUTATION_RATE = 0.05;
}
