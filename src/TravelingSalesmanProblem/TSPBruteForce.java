package TravelingSalesmanProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSPBruteForce {
    private int[][] graph;
    private int numCities;
    private int[] bestRoute;
    private int bestDistance;

    public void solve(int[][] adjacencyMatrix) {
        numCities = adjacencyMatrix.length;
        graph = adjacencyMatrix;
        bestRoute = new int[numCities];
        bestDistance = Integer.MAX_VALUE;

        // Criar uma lista com as cidades
        List<Integer> cities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            cities.add(i);
        }

        // Gerar todas as permutações das cidades
        permute(cities, 0);

        System.out.println("Melhor rota: " + Arrays.toString(bestRoute));
        System.out.println("Menor distância: " + bestDistance);
    }

    private void permute(List<Integer> cities, int startIndex) {
        if (startIndex == numCities - 1) {
            // Verificar a distância da rota atual
            int[] route = new int[numCities];
            for (int i = 0; i < numCities; i++) {
                route[i] = cities.get(i);
            }
            int distance = calculateDistance(route);

            // Atualizar a melhor rota e distância
            if (distance < bestDistance) {
                bestRoute = route;
                bestDistance = distance;
            }
        } else {
            for (int i = startIndex; i < numCities; i++) {
                // Trocar a posição das cidades
                swap(cities, startIndex, i);

                // Chamada recursiva para permutar as cidades restantes
                permute(cities, startIndex + 1);

                // Reverter a troca para manter a ordem original das cidades
                swap(cities, startIndex, i);
            }
        }
    }

    private int calculateDistance(int[] route) {
        int distance = 0;
        for (int i = 0; i < numCities - 1; i++) {
            distance += graph[route[i]][route[i + 1]];
        }
        distance += graph[route[numCities - 1]][route[0]]; // Voltar para a cidade inicial
        return distance;
    }

    private void swap(List<Integer> cities, int i, int j) {
        int temp = cities.get(i);
        cities.set(i, cities.get(j));
        cities.set(j, temp);
    }

}

