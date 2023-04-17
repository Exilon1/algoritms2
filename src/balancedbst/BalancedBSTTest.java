package balancedbst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BalancedBSTTest {

    private int[] arr;
    private BalancedBST bst = new BalancedBST();

    @BeforeEach
    void initialize() {
        arr = new int[]{
                -150,
                -100,
                -50,
                0,
                50,
                100,
                150,
                175
        };
    }

    void initializeSecondPreset() {
        arr = new int[]{
                -150,
                -100,
                0,
                0,
                0,
                100,
                150,
                175
        };
    }

    @Test
    void generateTreeTest() {
        bst.GenerateTree(arr);

        assertEquals(0, bst.Root.NodeKey);
        assertEquals(0, bst.Root.Level);
        assertEquals(-100, bst.Root.LeftChild.NodeKey);
        assertEquals(1, bst.Root.LeftChild.Level);
        assertEquals(100, bst.Root.RightChild.NodeKey);
        assertEquals(1, bst.Root.RightChild.Level);
        assertEquals(-150, bst.Root.LeftChild.LeftChild.NodeKey);
        assertEquals(2, bst.Root.LeftChild.LeftChild.Level);
        assertEquals(-50, bst.Root.LeftChild.RightChild.NodeKey);
        assertEquals(2, bst.Root.LeftChild.RightChild.Level);

        assertEquals(150, bst.Root.RightChild.RightChild.NodeKey);
        assertEquals(2, bst.Root.RightChild.RightChild.Level);

        assertEquals(175, bst.Root.RightChild.RightChild.RightChild.NodeKey);
        assertEquals(3, bst.Root.RightChild.RightChild.RightChild.Level);
    }

    @Test
    void isBalancedTest() {
        bst.GenerateTree(arr);

        assertTrue(bst.IsBalanced(bst.Root));

        initializeSecondPreset();

        bst.GenerateTree(arr);

        assertFalse(bst.IsBalanced(bst.Root));
    }
}
