package ge.edu.tsu.tsp.server.solve.ga.configuration;

import ge.edu.tsu.tsp.server.solve.ga.operation.crossover.CrossoverType;
import ge.edu.tsu.tsp.server.solve.ga.operation.mutation.MutationType;
import ge.edu.tsu.tsp.server.solve.ga.operation.selection.SelectionType;

public class GAParams {

	public static final int POPULATION_SIZE = 200;

	public static final int SURVIVED_EXEMPLARS = 20;

	public static final SelectionType SELECTION_TYPE = SelectionType.RANDOM_SELECTION;

	public static final CrossoverType CROSSOVER_TYPE = CrossoverType.PARTIAL_MAPPED_CROSSOVER;

	public static final MutationType MUTATION_TYPE = MutationType.SWAP_MUTATION;

	public static double MUTATION_RATE = 0.05;
}
