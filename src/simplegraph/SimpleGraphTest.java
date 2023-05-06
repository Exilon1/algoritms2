package simplegraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleGraphTest {

    private SimpleGraph graph;

    @BeforeEach
    void init() {
        graph = new SimpleGraph(5);
    }

    void initialize() {
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
    }

    void initializeSecondPreset() {
        graph.AddVertex(6);
        graph.AddVertex(7);
        graph.AddVertex(8);
    }

    void initializeThirdPreset() {
        graph.AddVertex(9);
    }

    void addEdges() {
        graph.AddEdge(0, 0);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(0, 4);

        graph.AddEdge(0, 0);
        graph.AddEdge(1, 0);
        graph.AddEdge(2, 0);
        graph.AddEdge(3, 0);
        graph.AddEdge(4, 0);
    }

    @Test
    void addVertexTest() {
        assertNull(graph.vertex[0]);

        initialize();

        assertEquals(1, graph.vertex[0].Value);
        assertEquals(2, graph.vertex[1].Value);
        assertEquals(3, graph.vertex[2].Value);
        assertEquals(4, graph.vertex[3].Value);
        assertEquals(5, graph.vertex[4].Value);

        assertEquals(0, graph.m_adjacency[0][0]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(0, graph.m_adjacency[3][0]);
        assertEquals(0, graph.m_adjacency[4][0]);

        assertEquals(0, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[1][1]);
        assertEquals(0, graph.m_adjacency[2][1]);
        assertEquals(0, graph.m_adjacency[3][1]);
        assertEquals(0, graph.m_adjacency[4][1]);

        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(0, graph.m_adjacency[1][2]);
        assertEquals(0, graph.m_adjacency[2][2]);
        assertEquals(0, graph.m_adjacency[3][2]);
        assertEquals(0, graph.m_adjacency[4][2]);

        assertEquals(0, graph.m_adjacency[0][3]);
        assertEquals(0, graph.m_adjacency[1][3]);
        assertEquals(0, graph.m_adjacency[2][3]);
        assertEquals(0, graph.m_adjacency[3][3]);
        assertEquals(0, graph.m_adjacency[4][3]);

        assertEquals(0, graph.m_adjacency[0][4]);
        assertEquals(0, graph.m_adjacency[1][4]);
        assertEquals(0, graph.m_adjacency[2][4]);
        assertEquals(0, graph.m_adjacency[3][4]);
        assertEquals(0, graph.m_adjacency[4][4]);

        graph.AddVertex(6);

        assertEquals(1, graph.vertex[0].Value);
        assertEquals(2, graph.vertex[1].Value);
        assertEquals(3, graph.vertex[2].Value);
        assertEquals(4, graph.vertex[3].Value);
        assertEquals(5, graph.vertex[4].Value);

        assertEquals(0, graph.m_adjacency[0][0]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(0, graph.m_adjacency[3][0]);
        assertEquals(0, graph.m_adjacency[4][0]);

        assertEquals(0, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[1][1]);
        assertEquals(0, graph.m_adjacency[2][1]);
        assertEquals(0, graph.m_adjacency[3][1]);
        assertEquals(0, graph.m_adjacency[4][1]);

        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(0, graph.m_adjacency[1][2]);
        assertEquals(0, graph.m_adjacency[2][2]);
        assertEquals(0, graph.m_adjacency[3][2]);
        assertEquals(0, graph.m_adjacency[4][2]);

        assertEquals(0, graph.m_adjacency[0][3]);
        assertEquals(0, graph.m_adjacency[1][3]);
        assertEquals(0, graph.m_adjacency[2][3]);
        assertEquals(0, graph.m_adjacency[3][3]);
        assertEquals(0, graph.m_adjacency[4][3]);

        assertEquals(0, graph.m_adjacency[0][4]);
        assertEquals(0, graph.m_adjacency[1][4]);
        assertEquals(0, graph.m_adjacency[2][4]);
        assertEquals(0, graph.m_adjacency[3][4]);
        assertEquals(0, graph.m_adjacency[4][4]);
    }

    @Test
    void addEdgeTest() {
        initialize();

        assertEquals(0, graph.m_adjacency[0][0]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(0, graph.m_adjacency[3][0]);
        assertEquals(0, graph.m_adjacency[4][0]);

        assertEquals(0, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(0, graph.m_adjacency[0][3]);
        assertEquals(0, graph.m_adjacency[0][4]);

        addEdges();

        assertEquals(1, graph.m_adjacency[0][0]);
        assertEquals(1, graph.m_adjacency[1][0]);
        assertEquals(1, graph.m_adjacency[2][0]);
        assertEquals(1, graph.m_adjacency[3][0]);
        assertEquals(1, graph.m_adjacency[4][0]);

        assertEquals(1, graph.m_adjacency[0][1]);
        assertEquals(1, graph.m_adjacency[0][2]);
        assertEquals(1, graph.m_adjacency[0][3]);
        assertEquals(1, graph.m_adjacency[0][4]);
    }

    @Test
    void removeVertexTest() {
        initialize();
        addEdges();

        graph.RemoveVertex(0);

        assertNull(graph.vertex[0]);

        assertEquals(0, graph.m_adjacency[0][0]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(0, graph.m_adjacency[3][0]);
        assertEquals(0, graph.m_adjacency[4][0]);

        assertEquals(0, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(0, graph.m_adjacency[0][3]);
        assertEquals(0, graph.m_adjacency[0][4]);
    }

    @Test
    void removeEdge() {
        initialize();
        addEdges();

        graph.RemoveEdge(0, 1);
        graph.RemoveEdge(1, 0);
        graph.RemoveEdge(0, 0);

        assertEquals(0, graph.m_adjacency[0][0]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[0][1]);
    }

    @Test
    void isEdge() {
        initialize();
        addEdges();

        assertTrue(graph.IsEdge(0, 0));
        assertTrue(graph.IsEdge(0, 1));
        assertTrue(graph.IsEdge(1, 0));
        assertFalse(graph.IsEdge(1, 1));
        assertFalse(graph.IsEdge(1, 2));
        assertFalse(graph.IsEdge(2, 1));
        assertFalse(graph.IsEdge(2, 2));
    }

    @Test
    void depthFirstSearchT() {
        initialize();

        graph.AddEdge(0, 0);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);

        List<Vertex> list = graph.DepthFirstSearch(0, 4);

        assertEquals(4, list.size());
        assertEquals(graph.vertex[0], list.get(0));
        assertEquals(graph.vertex[2], list.get(1));
        assertEquals(graph.vertex[3], list.get(2));
        assertEquals(graph.vertex[4], list.get(3));
    }

    @Test
    void breadthFirstSearch() {
        graph = new SimpleGraph(8);

        initialize();
        initializeSecondPreset();
        graph.AddEdge(0, 0);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(2, 4);
        graph.AddEdge(1, 5);
        graph.AddEdge(1, 6);
        graph.AddEdge(1, 7);

        List<Vertex> list = graph.BreadthFirstSearch(0, 7);

        assertEquals(3, list.size());
        assertEquals(graph.vertex[0], list.get(0));
        assertEquals(graph.vertex[1], list.get(1));
        assertEquals(graph.vertex[7], list.get(2));
    }

    @Test
    void weakVerticesTest() {
        graph = new SimpleGraph(9);

        initialize();
        initializeSecondPreset();
        initializeThirdPreset();

        graph.AddEdge(0, 0);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(2, 2);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(2, 5);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 6);
        graph.AddEdge(5, 7);
        graph.AddEdge(6, 7);
        graph.AddEdge(9, 8);

        List<Vertex> list = graph.WeakVertices();

        assertEquals(2, list.size());
        assertEquals(graph.vertex[4], list.get(0));
        assertEquals(graph.vertex[8], list.get(1));
    }
}
