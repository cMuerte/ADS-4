import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    final Map<V, Vertex<V>> vertices = new HashMap<>();;

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V data) {
        if (hasVertex(data))
            return;
        vertices.put(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);
        if (!hasVertex(dest))
            addVertex(dest);

        Vertex<V> fromVertex = vertices.get(source);
        Vertex<V> toVertex = vertices.get(dest);

        if (source.equals(dest)) return;
        if (fromVertex.getAdjacentVertices().containsKey(toVertex)) return;

        fromVertex.addAdjacentVertex(toVertex, weight);
        if (undirected)
            toVertex.addAdjacentVertex(fromVertex, weight);
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<V> v : vertices.values()) {
            count += v.getAdjacentVertices().size();
        }
        if (undirected) count /= 2;
        return count;
    }

    public boolean hasVertex(V data) {
        return vertices.containsKey(data);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source) || !hasVertex(dest)) return false;
        return vertices.get(source).getAdjacentVertices().containsKey(vertices.get(dest));
    }

    public List<Vertex<V>> adjacencyList(V data) {
        if (!hasVertex(data)) return null;
        return new ArrayList<>(vertices.get(data).getAdjacentVertices().keySet());
    }

    public void printGraph() {
        for (Vertex<V> v : vertices.values()) {
            System.out.println(v.getData() + " -> ");
            for (Map.Entry<Vertex<V>, Double> entry : v.getAdjacentVertices().entrySet()) {
                System.out.println(entry.getKey().getData() + "(" + entry.getValue() + "), ");
            }
            System.out.println();
        }
    }
}
