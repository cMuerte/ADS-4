import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<V, Double> distTo;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        distTo = new HashMap<>();
        dijkstra(graph, source);
    }

    private void dijkstra(WeightedGraph<V> graph, V source) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (V v : graph.vertices.keySet()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);
        pq.offer(new Node(source, 0.0));

        while (!pq.isEmpty()) {
            V v = pq.poll().vertex;
            if (marked.contains(v)) continue;
            marked.add(v);

            for (Vertex<V> w : graph.adjacencyList(v)) {
                V wData = w.getData();
                double weight = graph.vertices.get(v).getAdjacentVertices().get(w);
                double distanceThroughV = distTo.get(v) + weight;

                if (distanceThroughV < distTo.get(wData)) {
                    distTo.put(wData, distanceThroughV);
                    edgeTo.put(wData, v);
                    pq.offer(new Node(wData, distanceThroughV));
                }
            }
        }
    }

    private class Node implements Comparable<Node> {
        V vertex;
        double distance;

        Node(V vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}