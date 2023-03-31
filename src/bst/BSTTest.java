package bst;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BSTTest {

    private static BST<Integer> bst;

    @BeforeAll
    static void initialize() {
        BSTNode<Integer> nodeZero = new BSTNode<>(0, 1, null);

        bst = new BST<>(nodeZero);

        bst.AddKeyValue(-100,1);
        bst.AddKeyValue(100,2);
        bst.AddKeyValue(-150,3);
        bst.AddKeyValue(-50,4);
        bst.AddKeyValue(150,5);
        bst.AddKeyValue(125,6);
        bst.AddKeyValue(175,7);
        bst.AddKeyValue(120,8);
        bst.AddKeyValue(130,9);
    }

    @Test
    void findNodeByKeyTest() {
        BSTFind<Integer> find = bst.FindNodeByKey(130);

        assertEquals(9, find.Node.NodeValue);
        assertTrue(find.NodeHasKey);

        BSTFind<Integer> findOne = bst.FindNodeByKey(180);
        BSTFind<Integer> findTwo = bst.FindNodeByKey(170);

        assertEquals(175, findOne.Node.NodeKey);
        assertFalse(findOne.NodeHasKey);
        assertFalse(findOne.ToLeft);

        assertEquals(175, findTwo.Node.NodeKey);
        assertFalse(findTwo.NodeHasKey);
        assertTrue(findTwo.ToLeft);
    }

    @Test
    void addKeyValueTest() {
        assertEquals(10, bst.Count());

        BSTFind<Integer> find = bst.FindNodeByKey(133);
        assertEquals(130, find.Node.NodeKey);
        assertFalse(find.ToLeft);

        find = bst.FindNodeByKey(127);
        assertEquals(130, find.Node.NodeKey);
        assertTrue(find.ToLeft);

        bst.AddKeyValue(133,10);

        find = bst.FindNodeByKey(133);

        assertEquals(133, find.Node.NodeKey);
        assertEquals(130, find.Node.Parent.NodeKey);
        assertEquals(133, find.Node.Parent.RightChild.NodeKey);

        assertEquals(11, bst.Count());

        bst.AddKeyValue(127,11);

        find = bst.FindNodeByKey(127);

        assertEquals(127, find.Node.NodeKey);
        assertEquals(130, find.Node.Parent.NodeKey);
        assertEquals(127, find.Node.Parent.LeftChild.NodeKey);

        assertEquals(12, bst.Count());

        bst.AddKeyValue(130,99);

        find = bst.FindNodeByKey(130);

        assertEquals(130, find.Node.NodeKey);
        assertEquals(99, find.Node.NodeValue);

        assertEquals(12, bst.Count());
    }

    @Test
    void finMinMaxTest() {
        assertEquals(175, bst.FinMinMax(bst.Root, true).NodeKey);
        assertEquals(-150, bst.FinMinMax(bst.Root, false).NodeKey);

        BSTFind<Integer> find = bst.FindNodeByKey(100);

        assertEquals(175, bst.FinMinMax(find.Node, true).NodeKey);
        assertEquals(100, bst.FinMinMax(find.Node, false).NodeKey);
    }

    @Test
    void deleteNodeByKeyTest() {
        assertEquals(10, bst.Count());

        assertEquals(100, bst.FindNodeByKey(100).Node.NodeKey);

        bst.DeleteNodeByKey(150);

        assertEquals(130, bst.FindNodeByKey(150).Node.NodeKey);

        assertEquals(9, bst.Count());
    }
}
