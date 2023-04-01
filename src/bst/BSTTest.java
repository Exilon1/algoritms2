package bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BSTTest {

    private BST<Integer> bst;

    @BeforeEach
    void initialize() {
        BSTNode<Integer> nodeZero = new BSTNode<>(0, 1, null);

        bst = new BST<>(nodeZero);

        bst.AddKeyValue(-100,1);
        bst.AddKeyValue(100,2);
        bst.AddKeyValue(-150,3);
        bst.AddKeyValue(-50,4);
        bst.AddKeyValue(50,5);
        bst.AddKeyValue(150,6);
        bst.AddKeyValue(125,7);
        bst.AddKeyValue(175,8);
        bst.AddKeyValue(120,9);
        bst.AddKeyValue(130,10);
    }

    private void initializeSingleNode() {
        BSTNode<Integer> nodeZero = new BSTNode<>(0, 1, null);

        bst = new BST<>(nodeZero);
    }

    private void initializeSecondPreset() {
        BSTNode<Integer> nodeZero = new BSTNode<>(0, 1, null);

        bst = new BST<>(nodeZero);

        bst.AddKeyValue(-100,1);
        bst.AddKeyValue(100,2);
        bst.AddKeyValue(50,3);
    }

    private void initializeThirdPreset() {
        BSTNode<Integer> nodeZero = new BSTNode<>(0, 1, null);

        bst = new BST<>(nodeZero);

        bst.AddKeyValue(-100,1);
        bst.AddKeyValue(100,2);
        bst.AddKeyValue(150,3);
    }

    private void initializeFourthPreset() {
        BSTNode<Integer> nodeZero = new BSTNode<>(0, 1, null);

        bst = new BST<>(nodeZero);

        bst.AddKeyValue(-100,1);
        bst.AddKeyValue(100,2);
        bst.AddKeyValue(50,3);
        bst.AddKeyValue(150,4);
        bst.AddKeyValue(40,5);
        bst.AddKeyValue(30,6);
    }

    @Test
    void findNodeByKeyTest() {
        BSTFind<Integer> find = bst.FindNodeByKey(130);

        assertEquals(10, find.Node.NodeValue);
        assertTrue(find.NodeHasKey);

        BSTFind<Integer> findOne = bst.FindNodeByKey(180);
        BSTFind<Integer> findTwo = bst.FindNodeByKey(170);

        assertEquals(175, findOne.Node.NodeKey);
        assertFalse(findOne.NodeHasKey);
        assertFalse(findOne.ToLeft);

        assertEquals(175, findTwo.Node.NodeKey);
        assertFalse(findTwo.NodeHasKey);
        assertTrue(findTwo.ToLeft);

        initializeSingleNode();

        assertEquals(0, bst.FindNodeByKey(100).Node.NodeKey);
        assertEquals(0, bst.FindNodeByKey(-100).Node.NodeKey);

        bst.Root = null;

        assertNull(bst.FindNodeByKey(0).Node);
    }

    @Test
    void addKeyValueTest() {
        assertEquals(11, bst.Count());

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

        assertEquals(12, bst.Count());

        bst.AddKeyValue(127,11);

        find = bst.FindNodeByKey(127);

        assertEquals(127, find.Node.NodeKey);
        assertEquals(130, find.Node.Parent.NodeKey);
        assertEquals(127, find.Node.Parent.LeftChild.NodeKey);

        assertEquals(13, bst.Count());

        bst.AddKeyValue(130,99);

        find = bst.FindNodeByKey(130);

        assertEquals(130, find.Node.NodeKey);
        assertEquals(99, find.Node.NodeValue);

        assertEquals(13, bst.Count());

        bst.Root = null;

        assertEquals(0, bst.Count());

        bst.AddKeyValue(100,1);
        find = bst.FindNodeByKey(100);

        assertEquals(100, find.Node.NodeKey);
        assertEquals(1, bst.Count());
    }

    @Test
    void finMinMaxTest() {
        assertEquals(175, bst.FinMinMax(bst.Root, true).NodeKey);
        assertEquals(-150, bst.FinMinMax(bst.Root, false).NodeKey);

        BSTFind<Integer> find = bst.FindNodeByKey(150);

        assertEquals(175, bst.FinMinMax(find.Node, true).NodeKey);
        assertEquals(120, bst.FinMinMax(find.Node, false).NodeKey);
    }

    @Test
    void deleteNodeByKeyTest() {
        assertEquals(11, bst.Count());
        assertEquals(120, bst.FindNodeByKey(120).Node.NodeKey);
        bst.DeleteNodeByKey(120);
        assertNotEquals(120, bst.FindNodeByKey(120).Node.NodeKey);

        assertEquals(10, bst.Count());

        assertEquals(130, bst.FindNodeByKey(130).Node.NodeKey);
        bst.DeleteNodeByKey(130);
        assertNotEquals(130, bst.FindNodeByKey(130).Node.NodeKey);
        assertEquals(9, bst.Count());
    }

    @Test
    void deleteSingleNodeByKeyTest() {
        assertEquals(11, bst.Count());
        assertEquals(120, bst.FindNodeByKey(120).Node.NodeKey);
        bst.DeleteNodeByKey(120);
        assertNotEquals(120, bst.FindNodeByKey(120).Node.NodeKey);

        assertEquals(10, bst.Count());

        assertEquals(130, bst.FindNodeByKey(130).Node.NodeKey);
        bst.DeleteNodeByKey(130);
        assertNotEquals(130, bst.FindNodeByKey(130).Node.NodeKey);
        assertEquals(9, bst.Count());

        initializeSingleNode();

        assertEquals(1, bst.Count());
        assertEquals(0, bst.FindNodeByKey(0).Node.NodeKey);
        bst.DeleteNodeByKey(0);
        assertNull(bst.FindNodeByKey(0).Node);
        assertEquals(0, bst.Count());

        initializeSecondPreset();

        assertEquals(4, bst.Count());
        bst.DeleteNodeByKey(100);
        assertNotEquals(100, bst.FindNodeByKey(100).Node.NodeKey);
        assertEquals(3, bst.Count());

        initializeThirdPreset();

        assertEquals(4, bst.Count());
        bst.DeleteNodeByKey(100);
        assertNotEquals(100, bst.FindNodeByKey(100).Node.NodeKey);
        assertEquals(3, bst.Count());

        initializeFourthPreset();

        assertEquals(7, bst.Count());
        bst.DeleteNodeByKey(0);
        assertNotEquals(0, bst.FindNodeByKey(0).Node.NodeKey);
        assertEquals(6, bst.Count());
    }
}
