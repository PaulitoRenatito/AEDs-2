package TravelingSalesmanProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSPHeuristic {
    private int[][] graph;
    private int numCities;
    private int[] bestRoute;
    private int bestDistance;

    public void solve(int[][] adjacencyMatrix) {
        numCities = adjacencyMatrix.length;
        graph = adjacencyMatrix;
        bestRoute = new int[numCities];
        bestDistance = Integer.MAX_VALUE;

        // Iniciar com uma cidade aleatória
        int initialCity = (int) (Math.random() * numCities);
        List<Integer> unvisitedCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            if (i != initialCity) {
                unvisitedCities.add(i);
            }
        }

        // Construir a rota
        int currentCity = initialCity;
        int[] route = new int[numCities];
        route[0] = currentCity;

        for (int i = 1; i < numCities; i++) {
            int nearestCity = findNearestCity(currentCity, unvisitedCities);
            route[i] = nearestCity;
            unvisitedCities.remove(Integer.valueOf(nearestCity));
            currentCity = nearestCity;
        }

        // Adicionar a cidade inicial ao final da rota
        route[numCities - 1] = initialCity;

        // Calcular a distância da rota
        int distance = calculateDistance(route);

        // Armazenar a melhor rota e distância encontradas
        bestRoute = route;
        bestDistance = distance;

        System.out.println("Melhor rota: " + Arrays.toString(bestRoute));
        System.out.println("Menor distância: " + bestDistance);
    }

    private int findNearestCity(int currentCity, List<Integer> unvisitedCities) {
        int nearestCity = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int city : unvisitedCities) {
            if (graph[currentCity][city] < minDistance) {
                minDistance = graph[currentCity][city];
                nearestCity = city;
            }
        }

        return nearestCity;
    }

    private int calculateDistance(int[] route) {
        int distance = 0;
        for (int i = 0; i < numCities - 1; i++) {
            distance += graph[route[i]][route[i + 1]];
        }
        distance += graph[route[numCities - 1]][route[0]]; // Voltar para a cidade inicial
        return distance;
    }

}
