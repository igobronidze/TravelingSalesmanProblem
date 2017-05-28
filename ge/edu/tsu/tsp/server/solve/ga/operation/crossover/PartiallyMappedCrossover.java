package ge.edu.tsu.tsp.server.solve.ga.operation.crossover;

import ge.edu.tsu.tsp.server.solve.ga.main.Tour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PartiallyMappedCrossover implements Crossover {

	@Override
	public Tour applyCrossover(Tour tour1, Tour tour2) {
		Random ran = new Random();
		int x = ran.nextInt(tour1.getNodeIndexes().size());
		int y = ran.nextInt(tour1.getNodeIndexes().size());
		if (x > y) {
			int a = x;
			x = y;
			y = a;
		}
		List<Integer> indexes = new ArrayList<>(tour2.getNodeIndexes());
		for (int i = x; i <= y; i++) {
			indexes.set(i, tour1.getNodeIndexes().get(i));
		}
		Set<Integer> duplicate = new HashSet<>();
		Set<Integer> used = new HashSet<>();
		Set<Integer> notUsed = new HashSet<>();
		for (int i = 1 ; i <= indexes.size(); i++) {
			notUsed.add(i);
		}
		for (int i = 0 ; i < indexes.size(); i++) {
			notUsed.remove(new Integer(indexes.get(i)));
			if (used.contains(indexes.get(i))) {
				duplicate.add(indexes.get(i));
			}
			used.add(indexes.get(i));
		}
		Iterator<Integer> it = notUsed.iterator();
		for (int i = 0 ; i < indexes.size(); i++) {
			if (i < x || i > y) {
				if (duplicate.contains(indexes.get(i))) {
					int k = it.next();
					indexes.set(i, k);
				}
			}
		}
		return new Tour(indexes, tour1.getGraph());
	}
}
