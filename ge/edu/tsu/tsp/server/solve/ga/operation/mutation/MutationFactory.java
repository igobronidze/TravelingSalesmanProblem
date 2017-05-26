package ge.edu.tsu.tsp.server.solve.ga.operation.mutation;

public class MutationFactory {

	public static Mutation createMutation(MutationType type) {
		Mutation mutation = null;
		switch (type) {
			case SWAP_MUTATION:
				mutation = new SwapMutation();
				break;
		}
		return mutation;
	}
}
