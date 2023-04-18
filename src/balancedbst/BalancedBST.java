package balancedbst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }

    public BSTNode(int key, BSTNode parent, int level) {
        this(key, parent);
        Level = level;
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        int[] copy = new int[a.length];

        System.arraycopy(a, 0, copy, 0, a.length);

        Arrays.sort(copy);

        generate(null, copy, 0, copy.length - 1, false, 0);
    }

    private void generate(BSTNode parent, int[] arr, int startIndex,
                          int endIndex, boolean isLeft, int level) {
        int centralIndex = (endIndex + startIndex) / 2;

        int val = arr[centralIndex];

        BSTNode node = new BSTNode(val, parent, level);

        if (parent == null) {
            Root = node;
        } else if (isLeft) {
            parent.LeftChild = node;
        } else {
            parent.RightChild = node;
        }

        if (startIndex == endIndex) {
            return;
        }

        if (centralIndex == startIndex) {
            node.RightChild = new BSTNode(arr[centralIndex + 1], node, level + 1);
            return;
        }

        int sameValueSequenceIndex = centralIndex;
        while (val == arr[sameValueSequenceIndex - 1]) {
            sameValueSequenceIndex--;
        }

        generate(node, arr, startIndex, sameValueSequenceIndex - 1, true, level + 1);

        while (arr[sameValueSequenceIndex] == arr[sameValueSequenceIndex + 1]) {
            BSTNode sameNode = new BSTNode(val, node, ++level);
            node.RightChild = sameNode;
            node = sameNode;
            sameValueSequenceIndex++;
        }

        generate(node, arr, sameValueSequenceIndex + 1, endIndex, false, level + 1);
    }

    public boolean IsBalanced(BSTNode root_node) {
        int minLevel = Integer.MAX_VALUE;
        int maxLevel = 0;

        for (Integer level : getAllLeafs(Root)) {
            if (minLevel > level) {
                minLevel = level;
            }

            if (maxLevel < level) {
                maxLevel = level;
            }
        }

        return maxLevel - minLevel <= 1;
    }

    private List<Integer> getAllLeafs(BSTNode node) {
        List<Integer> levels = new ArrayList<>();

        if (node.LeftChild == null && node.RightChild == null) {
            levels.add(node.Level);
            return levels;
        }

        if (node.LeftChild == null) {
            levels.add(node.Level);
            levels.addAll(getAllLeafs(node.RightChild));
            return levels;
        }

        if (node.RightChild == null) {
            levels.add(node.Level);
            levels.addAll(getAllLeafs(node.LeftChild));
            return levels;
        }

        levels.addAll(getAllLeafs(node.LeftChild));
        levels.addAll(getAllLeafs(node.RightChild));

        return levels;
    }
}
