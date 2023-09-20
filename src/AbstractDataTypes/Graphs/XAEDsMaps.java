package AbstractDataTypes.Graphs;

import java.util.*;

public class XAEDsMaps {
    private XGraph graph;
    private int startVertex;
    private int endVertex;

    public XAEDsMaps(XGraph graph, int startVertex, int endVertex) {
        this.graph = graph;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }

    public void findShortestDistancePath() {
        int[] distances = new int[graph.getNumberOfVertices()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        int[] previousVertices = new int[graph.getNumberOfVertices()];
        Arrays.fill(previousVertices, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            XGraph.Edge edge = graph.firstEdge(currentVertex);

            while (edge != null) {
                int neighbor = edge.getV2();
                int distance = edge.getDistance();

                if (distances[currentVertex] + distance < distances[neighbor]) {
                    distances[neighbor] = distances[currentVertex] + distance;
                    previousVertices[neighbor] = currentVertex;
                    queue.add(neighbor);
                }

                edge = graph.nextEdge(currentVertex);
            }
        }

        printPath(distances, previousVertices);
    }

    public void findShortestTimePath() {
        int[] times = new int[graph.getNumberOfVertices()];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[startVertex] = 0;

        int[] previousVertices = new int[graph.getNumberOfVertices()];
        Arrays.fill(previousVertices, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            XGraph.Edge edge = graph.firstEdge(currentVertex);

            while (edge != null) {
                int neighbor = edge.getV2();
                int time = edge.getTime();

                if (times[currentVertex] + time < times[neighbor]) {
                    times[neighbor] = times[currentVertex] + time;
                    previousVertices[neighbor] = currentVertex;
                    queue.add(neighbor);
                }

                edge = graph.nextEdge(currentVertex);
            }
        }

        printPath(times, previousVertices);
    }

    private void printPath(int[] values, int[] previousVertices) {
        List<Integer> path = new ArrayList<>();
        int currentVertex = endVertex;
        while (currentVertex != -1) {
            path.add(currentVertex);
            currentVertex = previousVertices[currentVertex];
        }
        Collections.reverse(path);

        System.out.println("Path: " + path);
        System.out.println("Values: ");
        for (int i = 0; i < path.size() - 1; i++) {
            int current = path.get(i);
            int next = path.get(i + 1);
            int distance = graph.distances[current][next];
            int time = graph.times[current][next];
            System.out.println("From " + current + " to " + next + ": Distance = " + distance + ", Time = " + time);
        }
    }
}
