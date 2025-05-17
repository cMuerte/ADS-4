import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {
    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<V> graph, V source) {
        Queue<V> queue = new LinkedList<>();
        marked.add(source);
        queue.offer(source);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (Vertex<V> w : graph.adjacencyList(v)) {
                if (!marked.contains(w.getData())) {
                    marked.add(w.getData());
                    edgeTo.put(w.getData(), v);
                    queue.offer(w.getData());
                }
            }
        }
    }
}