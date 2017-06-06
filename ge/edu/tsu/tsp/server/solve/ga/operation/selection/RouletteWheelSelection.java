package ge.edu.tsu.tsp.server.solve.ga.operation.selection;

import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.List;
import java.util.Random;

public class RouletteWheelSelection implements Selection {

	@Override
	public Tour select(List<Tour> tours) {
		Random r = new Random();
		double fSum = 0;
		for (Tour tour : tours) {
			fSum += tour.getFitness();
		}
		double sum = r.nextDouble() * fSum;
		int i = 0;
		while (true) {
			sum = sum - tours.get(i).getFitness();
			if (sum < 0) {
				break;
			}
			i++;
		}
		return tours.get(i);
	}
}
