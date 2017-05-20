package ge.edu.tsu.tsp.solve;

import ge.edu.tsu.tsp.common.TSPInput;
import ge.edu.tsu.tsp.common.TSPOutput;

public interface TSPSolver {

    TSPOutput solve(TSPInput input);
}
