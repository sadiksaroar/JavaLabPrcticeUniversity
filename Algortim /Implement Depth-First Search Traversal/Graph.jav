import java.util.LinkedList;

class Graph {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list representation

    // Constructor
    public Graph(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adjList[i] = new LinkedList();
    }

    // Add an edge to the graph
    void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    // Depth-First Search traversal starting from source s
    void DFS(int s) {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];

        // Call the recursive helper function to perform DFS traversal
        DFSUtil(s, visited);
    }

    // Recursive helper function for DFS traversal
    private void DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all adjacent vertices of the current vertex
        for (Integer neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Depth-First Traversal (starting from vertex 2): ");
        graph.DFS(2);
    }
}
