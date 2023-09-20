package AbstractDataTypes.Graphs;

public class XGraph {

    public static class Edge {
        private int v1, v2;
        private int distance;
        private int time;

        public Edge(int v1, int v2, int distance, int time) {
            this.v1 = v1;
            this.v2 = v2;
            this.distance = distance;
            this.time = time;
        }

        public int getV1() {
            return v1;
        }

        public int getV2() {
            return v2;
        }

        public int getDistance() {
            return distance;
        }

        public int getTime() {
            return time;
        }
    }

    int[][] distances;
    int[][] times;
    private int numberOfVertices;
    private int[] currentPosition;

    public XGraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.distances = new int[numberOfVertices][numberOfVertices];
        this.times = new int[numberOfVertices][numberOfVertices];
        this.currentPosition = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                this.distances[i][j] = 0;
                this.times[i][j] = 0;
            }
            this.currentPosition[i] = -1;
        }
    }

    public void insertEdge(int v1, int v2, int distance, int time) {
        this.distances[v1][v2] = distance;
        this.times[v1][v2] = time;
    }

    public Edge removeEdge(int v1, int v2) {
        if (this.distances[v1][v2] == 0 && this.times[v1][v2] == 0) {
            return null;
        } else {
            Edge edge = new Edge(v1, v2, this.distances[v1][v2], this.times[v1][v2]);
            this.distances[v1][v2] = 0;
            this.times[v1][v2] = 0;
            return edge;
        }
    }

    public Edge firstEdge(int v) {
        this.currentPosition[v] = -1;
        return this.nextEdge(v);
    }

    public Edge nextEdge(int v) {
        this.currentPosition[v]++;
        while ((this.currentPosition[v] < this.numberOfVertices)
                && (this.distances[v][this.currentPosition[v]] == 0 && this.times[v][this.currentPosition[v]] == 0)) {
            this.currentPosition[v]++;
        }

        if (this.currentPosition[v] == this.numberOfVertices) {
            return null;
        } else {
            return new Edge(v, this.currentPosition[v], this.distances[v][this.currentPosition[v]], this.times[v][this.currentPosition[v]]);
        }
    }

    public boolean haveEdge(int v1, int v2) {
        return (this.distances[v1][v2] > 0 && this.times[v1][v2] > 0);
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }
}
