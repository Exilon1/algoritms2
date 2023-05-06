package simplegraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Vertex {
    public int Value;

    private boolean hit;

    public Vertex(int val) {
        Value = val;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
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

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        ArrayList<Vertex> list = new ArrayList<>();

        if (VFrom < 0 ||
                VTo < 0 ||
                VFrom >= max_vertex ||
                VTo >= max_vertex) {
            return list;
        }

        clearSearchInfo();

        list.addAll(depthFirstSearch(new LinkedList<>(), VFrom, VTo));

        return list;
    }

    private LinkedList<Vertex> depthFirstSearch(LinkedList<Vertex> stack, int VFrom, int VTo) {
        vertex[VFrom].setHit(true);

        stack.addLast(vertex[VFrom]);

        if (m_adjacency[VFrom][VTo] == 1) {
            stack.addLast(vertex[VTo]);
            return stack;
        }

        for (int i = 0; i < max_vertex; i++) {
            if (VFrom == i) {
                continue;
            }

            if (m_adjacency[VFrom][i] == 1 && !vertex[i].isHit()) {
                return depthFirstSearch(stack, i, VTo);
            }
        }

        stack.removeLast();

        if (stack.isEmpty()) {
            return stack;
        }

        Vertex v = stack.removeLast();

        int index = getVertexIndex(v);

        return depthFirstSearch(stack, index, VTo);
    }

    private void clearSearchInfo() {
        for (Vertex v : vertex) {
            v.setHit(false);
        }
    }

    private int getVertexIndex(Vertex v) {
        int index = 0;

        for (int i = 0; i < max_vertex; i++) {
            if (v.equals(vertex[i])) {
                index = i;
            }
        }

        return index;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        ArrayList<Vertex> list = new ArrayList<>();

        if (VFrom < 0 ||
                VTo < 0 ||
                VFrom >= max_vertex ||
                VTo >= max_vertex) {
            return list;
        }

        clearSearchInfo();

        vertex[VFrom].setHit(true);

        LinkedList<LinkedList<Vertex>> paths = new LinkedList<>();
        LinkedList<Vertex> path = new LinkedList<>();
        path.addLast(vertex[VFrom]);
        paths.addLast(path);

        list.addAll(breadthFirstSearch(new LinkedList<>(), paths, VFrom, VTo));

        return list;
    }

    private LinkedList<Vertex> breadthFirstSearch(LinkedList<Vertex> queue, LinkedList<LinkedList<Vertex>> paths, int VFrom, int VTo) {
        boolean found = false;

        final Vertex v = vertex[VFrom];
        LinkedList<Vertex> path = paths.stream()
                .filter(p -> p.getLast().equals(v))
                .findFirst()
                .orElseGet(LinkedList::new);

        paths.remove(path);

        for (int i = 0; i < max_vertex; i++) {
            if (VFrom == i) {
                continue;
            }

            if (m_adjacency[VFrom][i] == 1 && !vertex[i].isHit() && i == VTo) {
                path.addLast(vertex[i]);
                found = true;
                break;
            }

            if (m_adjacency[VFrom][i] == 1 && !vertex[i].isHit()) {
                vertex[i].setHit(true);
                queue.addLast(vertex[i]);

                LinkedList<Vertex> newPath = new LinkedList<>(path);
                newPath.addLast(vertex[i]);
                paths.addLast(newPath);
            }
        }


        if (found) {
            return path;
        }

        if (queue.isEmpty()) {
            return new LinkedList<>();
        }

        Vertex vert = queue.removeFirst();
        int index = getVertexIndex(vert);

        return breadthFirstSearch(queue, paths, index, VTo);
    }

    public ArrayList<Vertex> WeakVertices() {
        clearSearchInfo();

        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i].isHit()) {
                continue;
            }

            List<Integer> adjacent = new ArrayList<>();

            for (int j = 0; j < max_vertex; j++) {
                if (i == j) {
                    continue;
                }

                if (m_adjacency[i][j] == 1 && !vertex[j].isHit()) {
                    adjacent.add(j);
                }
            }

            for (int j = 0; j < adjacent.size(); j++) {
                for (int k = j + 1; k < adjacent.size(); k++) {
                    if (IsEdge(adjacent.get(j), adjacent.get(k))) {
                        vertex[i].setHit(true);
                        vertex[adjacent.get(j)].setHit(true);
                        vertex[adjacent.get(k)].setHit(true);
                    }
                }
            }
        }

        return Arrays.stream(vertex)
                .filter(v -> !v.isHit())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
