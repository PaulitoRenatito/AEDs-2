package AbstractDataTypes.Graphs;

public class XCycle {

    /**
     * Utiliza uma abordagem de busca em profundidade para detectar a presença de ciclos
     */
    public static boolean hasCycle(XGraph graph) {
        int numberOfVertices = graph.getNumberOfVertices();
        boolean[] visited = new boolean[numberOfVertices];
        boolean[] recursionStack = new boolean[numberOfVertices];

        for (int v = 0; v < numberOfVertices; v++) {
            if (hasCycleUtil(graph, v, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Realiza a busca em profundidade recursivamente
     */
    private static boolean hasCycleUtil(XGraph graph, int v, boolean[] visited, boolean[] recursionStack) {
        if (recursionStack[v]) {
            return true; // Achou a mesma aresta novamente, existe ciclo
        }

        if (visited[v]) {
            return false; // Já foi visitada, não tem ciclo
        }

        visited[v] = true;
        recursionStack[v] = true;

        XGraph.Edge edge = graph.firstEdge(v);
        while (edge != null) {
            int neighbor = edge.getV2();
            if (hasCycleUtil(graph, neighbor, visited, recursionStack)) {
                return true;
            }
            edge = graph.nextEdge(v);
        }

        recursionStack[v] = false;

        return false;
    }

}
