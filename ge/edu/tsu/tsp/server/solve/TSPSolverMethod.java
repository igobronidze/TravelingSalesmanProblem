package ge.edu.tsu.tsp.server.solve;

public class TSPSolverMethod {

    private String description;

    private int priority;

    private String complexity;

    private TSPSolver tspSolver;

    public TSPSolverMethod() {}

    public TSPSolverMethod(String description, int priority, String complexity, TSPSolver tspSolver) {
        this.description = description;
        this.priority = priority;
        this.complexity = complexity;
        this.tspSolver = tspSolver;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public TSPSolver getTspSolver() {
        return tspSolver;
    }

    public void setTspSolver(TSPSolver tspSolver) {
        this.tspSolver = tspSolver;
    }
}
