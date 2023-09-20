import TravelingSalesmanProblem.TSPBruteForce;
import TravelingSalesmanProblem.TSPHeuristic;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        solveTSPUsingHeuristic("res\\TSP_1.txt");

        solveTSPUsingHeuristic("res\\TSP_2.txt");

        solveTSPUsingHeuristic("res\\TSP_3.txt");

    }

    private static void solveTSPUsingForce() {
        for (int i = 1; i <= 20; i++) {
            System.out.println("Instancias: " + i);
            int[][] adjacencyMatrix = Utils.GenerateRandomAdjacencyMatrix(i);
            System.out.println(Arrays.deepToString(adjacencyMatrix));
            TSPBruteForce tsp = new TSPBruteForce();
            long start = System.nanoTime();
            tsp.solve(adjacencyMatrix);
            long end = System.nanoTime();
            System.out.println("Execution time: " + (end - start) + "ns or " + ((end - start)/1000000f) + "ms");
            System.out.println();
        }
    }

    private static void solveTSPUsingHeuristic(String fileName) {
        int[][] adjacencyMatrix = Utils.GetAdjacencyMatrixFromFile(fileName);
        TSPHeuristic tsp = new TSPHeuristic();
        long start = System.nanoTime();
        tsp.solve(adjacencyMatrix);
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) + "ns or " + ((end - start)/1000000f) + "ms");
        System.out.println();
    }

}