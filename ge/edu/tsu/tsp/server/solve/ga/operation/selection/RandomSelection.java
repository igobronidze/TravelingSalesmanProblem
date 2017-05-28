package ge.edu.tsu.tsp.server.solve.ga.operation.selection;

import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.List;
import java.util.Random;

public class RandomSelection implements Selection {

	@Override
	public Tour select(List<Tour> tours) {
		Random ran = new Random();
		return  tours.get(ran.nextInt(tours.size()));
	}
}
