package simplegraph;

class Vertex {
    public int Value;

    public Vertex(int val) {
        Value = val;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        if (vertex[max_vertex - 1] != null) {
            return;
        }

        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value);
                break;
            }
        }
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        if (v < 0 || v >= max_vertex) {
            return;
        }

        vertex[v] = null;

        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[i][v] = 0;
        }

        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[v][i] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2) {
        if (v1 < 0 ||
                v2 < 0 ||
                v1 >= max_vertex ||
                v2 >= max_vertex) {
            return false;
        }

        if (vertex[v1] == null || vertex[v2] == null) {
            return false;
        }

        return m_adjacency[v1][v2] != 0 || m_adjacency[v2][v1] != 0;
    }

    public void AddEdge(int v1, int v2) {
        if (v1 < 0 ||
                v2 < 0 ||
                v1 >= max_vertex ||
                v2 >= max_vertex) {
            return;
        }

        if (vertex[v1] == null || vertex[v2] == null) {
            return;
        }

        m_adjacency[v1][v2] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        if (v1 < 0 ||
                v2 < 0 ||
                v1 >= max_vertex ||
                v2 >= max_vertex) {
            return;
        }

        m_adjacency[v1][v2] = 0;
    }
}
