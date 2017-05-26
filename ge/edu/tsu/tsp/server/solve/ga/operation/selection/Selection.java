package ge.edu.tsu.tsp.server.solve.ga.operation.selection;

import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.List;

public interface Selection {

	Tour select(List<Tour> tours);
}
