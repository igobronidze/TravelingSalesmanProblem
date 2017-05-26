package ge.edu.tsu.tsp.server.solve.ga.main;

import java.util.PriorityQueue;
import java.util.Queue;

public class Population {

	private Queue<Tour> tours = new PriorityQueue<>();

	public Queue<Tour> getTours() {
		return tours;
	}

	public void setTours(Queue<Tour> tours) {
		this.tours = tours;
	}
}
