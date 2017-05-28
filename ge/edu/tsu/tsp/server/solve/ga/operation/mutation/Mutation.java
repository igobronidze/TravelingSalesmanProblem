package ge.edu.tsu.tsp.server.solve.ga.operation.mutation;

import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

public interface Mutation {

	Tour applyMutation(Tour tour);
}
