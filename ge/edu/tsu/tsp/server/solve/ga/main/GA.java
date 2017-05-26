package ge.edu.tsu.tsp.server.solve.ga.main;

import ge.edu.tsu.tsp.server.graph.Graph;
import ge.edu.tsu.tsp.server.solve.ga.configuration.GAParams;
import ge.edu.tsu.tsp.server.solve.ga.helper.PopulationHelper;
import ge.edu.tsu.tsp.server.solve.ga.operation.crossover.Crossover;
import ge.edu.tsu.tsp.server.solve.ga.operation.crossover.CrossoverFactory;
import ge.edu.tsu.tsp.server.solve.ga.operation.mutation.Mutation;
import ge.edu.tsu.tsp.server.solve.ga.operation.mutation.MutationFactory;
import ge.edu.tsu.tsp.server.solve.ga.operation.selection.Selection;
import ge.edu.tsu.tsp.server.solve.ga.operation.selection.SelectionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA {

	public static Tour getBestTour(Graph graph, int gaIteration) {
		Population population = PopulationHelper.getRandomPopulation(GAParams.POPULATION_SIZE, graph);
		for (int i = 0; i < gaIteration; i++) {
			Population newPopulation = new Population();
			List<Tour> allTour = new ArrayList<>(population.getTours());
			for (int j = 0; j < GAParams.SURVIVED_EXEMPLARS; j++) {
				newPopulation.getTours().add(population.getTours().poll());
			}
			Selection selection = SelectionFactory.createSelection(GAParams.SELECTION_TYPE);
			Crossover crossover = CrossoverFactory.createCrossover(GAParams.CROSSOVER_TYPE);
			Mutation mutation = MutationFactory.createMutation(GAParams.MUTATION_TYPE);
			for (int j = 0; j < GAParams.POPULATION_SIZE - GAParams.SURVIVED_EXEMPLARS; j++) {
				Tour tour1 = selection.select(allTour);
				Tour tour2 = selection.select(allTour);
				newPopulation.getTours().add(crossover.applyCrossover(tour1, tour2));
			}
			if (new Random().nextDouble() < GAParams.MUTATION_RATE) {
				mutation.applyMutation(new ArrayList<>(newPopulation.getTours()).get(new Random().nextInt(GAParams.POPULATION_SIZE)));
			}
			population = newPopulation;
		}
		return population.getTours().peek();
	}
}
