package ge.edu.tsu.tsp.server.solve.ga.operation.selection;

public class SelectionFactory {

	public static Selection createSelection(SelectionType type) {
		Selection selection = null;
		switch (type) {
			case RANDOM_SELECTION:
				selection = new RandomSelection();
				break;
			case ROULETTE_WHEEL_SELECTION:
				selection = new RouletteWheelSelection();
				break;
		}
		return selection;
	}
}
