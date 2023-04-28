package trees;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTreeTest {

    private static SimpleTree<Integer> simpleTree;

    @BeforeAll
    static void initialize() {
        simpleTree = new SimpleTree<>(null);

        SimpleTreeNode<Integer> nodeZero = new SimpleTreeNode<>(0, null);
        SimpleTreeNode<Integer> nodeOne = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> nodeTwo = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> nodeThree = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> nodeFour = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> nodeFive = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> nodeSix = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> nodeSeven = new SimpleTreeNode<>(7, null);

        simpleTree.AddChild(null, nodeZero);
        simpleTree.AddChild(nodeZero, nodeOne);
        simpleTree.AddChild(nodeZero, nodeTwo);
        simpleTree.AddChild(nodeZero, nodeThree);
        simpleTree.AddChild(nodeThree, nodeFour);
        simpleTree.AddChild(nodeThree, nodeFive);
        simpleTree.AddChild(nodeFour, nodeSix);
        simpleTree.AddChild(nodeSix, nodeSeven);
    }

    void initializeSecondPreset() {
        simpleTree = new SimpleTree<>(null);

        SimpleTreeNode<Integer> nodeOne = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> nodeTwo = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> nodeThree = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> nodeFour = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> nodeFive = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> nodeSix = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> nodeSeven = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> nodeEight = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> nodeNine = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> nodeTen = new SimpleTreeNode<>(10, null);

        simpleTree.AddChild(null, nodeOne);
        simpleTree.AddChild(nodeOne, nodeTwo);
        simpleTree.AddChild(nodeOne, nodeThree);
        simpleTree.AddChild(nodeOne, nodeSix);
        simpleTree.AddChild(nodeTwo, nodeFive);
        simpleTree.AddChild(nodeTwo, nodeSeven);
        simpleTree.AddChild(nodeThree, nodeFour);
        simpleTree.AddChild(nodeSix, nodeEight);
        simpleTree.AddChild(nodeEight, nodeNine);
        simpleTree.AddChild(nodeEight, nodeTen);
    }

    void initializeThirdPreset() {
        simpleTree = new SimpleTree<>(null);

        SimpleTreeNode<Integer> nodeOne = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> nodeTwo = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> nodeThree = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> nodeFour = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> nodeFive = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> nodeSix = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> nodeSeven = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> nodeEight = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> nodeNine = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> nodeTen = new SimpleTreeNode<>(10, null);

        simpleTree.AddChild(null, nodeOne);
        simpleTree.AddChild(nodeOne, nodeTwo);
        simpleTree.AddChild(nodeTwo, nodeThree);
        simpleTree.AddChild(nodeThree, nodeFour);
        simpleTree.AddChild(nodeFour, nodeFive);
        simpleTree.AddChild(nodeFive, nodeSix);
        simpleTree.AddChild(nodeSix, nodeSeven);
        simpleTree.AddChild(nodeSeven, nodeEight);
        simpleTree.AddChild(nodeEight, nodeNine);
        simpleTree.AddChild(nodeNine, nodeTen);
    }

    void initializeFourthPreset() {
        simpleTree = new SimpleTree<>(null);

        SimpleTreeNode<Integer> nodeOne = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> nodeTwo = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> nodeThree = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> nodeFour = new SimpleTreeNode<>(4, null);

        simpleTree.AddChild(null, nodeOne);
        simpleTree.AddChild(nodeOne, nodeTwo);
        simpleTree.AddChild(nodeTwo, nodeThree);
        simpleTree.AddChild(nodeTwo, nodeFour);
    }

    @Test
    void addChildTest() {
        assertEquals(8, simpleTree.Count());

        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(8, null);
        assertEquals(0, node.getLevel());

        simpleTree.AddChild(simpleTree.Root, node);

        assertEquals(9, simpleTree.Count());
        assertEquals(1, node.getLevel());
        assertEquals(node, simpleTree.FindNodesByValue(8).get(0));
        assertTrue(simpleTree.Root.Children.contains(node));
        assertEquals(simpleTree.Root, node.Parent);

        simpleTree.DeleteNode(node);
    }

    @Test
    void deleteNodeTest() {
        assertEquals(8, simpleTree.Count());

        SimpleTreeNode<Integer> node = new SimpleTreeNode<>(8, null);

        simpleTree.AddChild(simpleTree.Root, node);
        simpleTree.DeleteNode(node);

        assertEquals(8, simpleTree.Count());
        assertTrue(simpleTree.FindNodesByValue(8).isEmpty());
        assertFalse(simpleTree.Root.Children.contains(node));
        assertNotEquals(simpleTree.Root, node.Parent);
    }

    @Test
    void getNodeTest() {
        assertEquals(8, simpleTree.GetAllNodes().size());
        assertEquals(0, simpleTree.GetAllNodes().get(0).NodeValue);
    }

    @Test
    void moveNodeTest() {
        SimpleTreeNode<Integer> nodeEight = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> nodeNine = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> nodeTen = new SimpleTreeNode<>(10, null);
        SimpleTreeNode<Integer> nodeEleven = new SimpleTreeNode<>(11, null);

        simpleTree.AddChild(simpleTree.Root, nodeEight);
        simpleTree.AddChild(simpleTree.Root, nodeNine);
        simpleTree.AddChild(nodeNine, nodeTen);
        simpleTree.AddChild(nodeNine, nodeEleven);

        simpleTree.MoveNode(nodeNine, nodeEight);

        assertNotEquals(simpleTree.Root, nodeNine.Parent);
        assertEquals(nodeEight, nodeNine.Parent);
        assertFalse(simpleTree.Root.Children.contains(nodeNine));
        assertTrue(nodeEight.Children.contains(nodeNine));
    }

    @Test
    void countTest() {
        simpleTree.Root = null;
        initialize();

        assertEquals(8, simpleTree.Count());
        assertEquals(4, simpleTree.LeafCount());
    }

    @Test
    void evenTreesTest() {
        initializeSecondPreset();

        ArrayList<Integer> list = simpleTree.EvenTrees();

        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(1, list.get(2));
        assertEquals(6, list.get(3));

        initializeThirdPreset();

        list = simpleTree.EvenTrees();

        assertEquals(8, list.size());
        assertEquals(8, list.get(0));
        assertEquals(9, list.get(1));
        assertEquals(6, list.get(2));
        assertEquals(7, list.get(3));
        assertEquals(4, list.get(4));
        assertEquals(5, list.get(5));
        assertEquals(2, list.get(6));
        assertEquals(3, list.get(7));

        initializeFourthPreset();

        list = simpleTree.EvenTrees();

        assertEquals(0, list.size());
    }
}
