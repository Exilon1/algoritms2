package bst;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }

    public BSTFind(BSTNode<T> node) {
        Node = node;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        BSTFind<T> find = new BSTFind<>(Root);
        return findNodeByKey(find, key);
    }

    private BSTFind<T> findNodeByKey(BSTFind<T> find, int key) {
        if (find.Node == null) {
            return find;
        }

        if (find.Node.NodeKey == key) {
            find.NodeHasKey = true;
            return find;
        }

        if (key < find.Node.NodeKey && find.Node.LeftChild == null) {
            find.NodeHasKey = false;
            find.ToLeft = true;
            return find;
        }

        if (key > find.Node.NodeKey && find.Node.RightChild == null) {
            find.NodeHasKey = false;
            find.ToLeft = false;
            return find;
        }

        if (key < find.Node.NodeKey) {
            find.Node = find.Node.LeftChild;
            return findNodeByKey(find, key);
        }

        find.Node = find.Node.RightChild;
        return findNodeByKey(find, key);
    }

    public boolean AddKeyValue(int key, T val) {
        BSTFind<T> find = FindNodeByKey(key);

        if (find.NodeHasKey) {
            find.Node.NodeValue = val;
            return false;
        }

        BSTNode<T> newNode = new BSTNode<>(key, val, find.Node);

        if (find.Node == null) {
            Root = newNode;
            return true;
        }

        if (find.ToLeft) {
            find.Node.LeftChild = newNode;
        } else {
            find.Node.RightChild = newNode;
        }

        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        if (FromNode == null) {
            return null;
        }

        if ((FindMax && FromNode.RightChild == null) ||
                (!FindMax && FromNode.LeftChild == null)) {
            return FromNode;
        }

        if (FindMax) {
            return FinMinMax(FromNode.RightChild, true);
        }

        return FinMinMax(FromNode.LeftChild, false);
    }

    public boolean DeleteNodeByKey(int key) {
        BSTFind<T> find = FindNodeByKey(key);

        if (find.Node == null || !find.NodeHasKey) {
            return false;
        }

        deleteNodeByKey(find.Node);

        return true;
    }

    public void deleteNodeByKey(BSTNode<T> nodeToDelete) {
        if (nodeToDelete.LeftChild == null &&
                nodeToDelete.RightChild == null &&
                nodeToDelete.Parent == null) {
            Root = null;
            return;
        }

        if (nodeToDelete.LeftChild == null &&
                nodeToDelete.RightChild == null &&
                nodeToDelete.Parent.LeftChild == nodeToDelete) {
            nodeToDelete.Parent.LeftChild = null;
            nodeToDelete.Parent = null;
            return;
        }

        if (nodeToDelete.LeftChild == null && nodeToDelete.RightChild == null) {
            nodeToDelete.Parent.RightChild = null;
            nodeToDelete.Parent = null;
            return;
        }

        if (nodeToDelete.RightChild == null && nodeToDelete.Parent == null) {
            Root = nodeToDelete.LeftChild;
            Root.Parent = null;
            return;
        }

        if (nodeToDelete.RightChild == null && nodeToDelete.Parent.LeftChild == nodeToDelete) {
            nodeToDelete.LeftChild.Parent = nodeToDelete.Parent;
            nodeToDelete.Parent.LeftChild = nodeToDelete.LeftChild;
            nodeToDelete.Parent = null;
            return;
        }

        if (nodeToDelete.RightChild == null) {
            nodeToDelete.LeftChild.Parent = nodeToDelete.Parent;
            nodeToDelete.Parent.RightChild = nodeToDelete.LeftChild;
            nodeToDelete.Parent = null;
            return;
        }

        if (nodeToDelete.LeftChild == null && nodeToDelete.Parent == null) {
            Root = nodeToDelete.RightChild;
            Root.Parent = null;
            return;
        }

        if (nodeToDelete.LeftChild == null && nodeToDelete.Parent.LeftChild == nodeToDelete) {
            nodeToDelete.RightChild.Parent = nodeToDelete.Parent;
            nodeToDelete.Parent.LeftChild = nodeToDelete.RightChild;
            nodeToDelete.Parent = null;
            return;
        }

        if (nodeToDelete.LeftChild == null) {
            nodeToDelete.RightChild.Parent = nodeToDelete.Parent;
            nodeToDelete.Parent.RightChild = nodeToDelete.RightChild;
            nodeToDelete.Parent = null;
            return;
        }

        BSTNode<T> node = FinMinMax(nodeToDelete.RightChild, false);

        deleteNodeByKey(node);

        node.Parent = nodeToDelete.Parent;
        node.LeftChild = nodeToDelete.LeftChild;
        node.RightChild = nodeToDelete.RightChild;

        if (nodeToDelete.Parent == null) {
            Root = node;
            return;
        }

        if (nodeToDelete.Parent.LeftChild == nodeToDelete) {
            nodeToDelete.Parent.LeftChild = node;
        } else {
            nodeToDelete.Parent.RightChild = node;
        }

        nodeToDelete.Parent = null;
    }

    public int Count() {
        if (Root == null) {
            return 0;
        }

        return count(Root);
    }

    private int count(BSTNode<T> node) {
        int count = 1;

        if (node.LeftChild != null) {
            count = count + count(node.LeftChild);
        }

        if (node.RightChild != null) {
            count = count + count(node.RightChild);
        }

        return count;
    }

    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<BSTNode> wideNodeList = new ArrayList<>();
        List<Boolean> rightChildren = new LinkedList<>();

        if (Root == null) {
            return wideNodeList;
        }

        wideNodeList.add(Root);
        rightChildren.add(false);

        while (true) {
            List<BSTNode<T>> levelNodes = wideLevelNodes(Root, rightChildren);

            if (levelNodes.isEmpty()) {
                break;
            }
            wideNodeList.addAll(levelNodes);

            rightChildren.add(false);
        }

        return wideNodeList;
    }

    private List<BSTNode<T>> wideLevelNodes(BSTNode<T> node, List<Boolean> rightChildren) {
        List<BSTNode<T>> lavelNodeList = new ArrayList<>();
        List<Boolean> nextRightChildren = rightChildren;

        for (int i = 0; i < new BigInteger(String.valueOf(2)).pow(rightChildren.size()).intValue(); i++) {

            BSTNode<T> nextNode = node;

            for (boolean right : nextRightChildren) {
                if (nextNode == null) {
                    break;
                }

                if (right) {
                    nextNode = nextNode.RightChild;
                } else {
                    nextNode = nextNode.LeftChild;
                }
            }

            if (nextNode != null) {
                lavelNodeList.add(nextNode);
            }

            nextRightChildren = addOneToBinaryNumber(nextRightChildren);
        }

        return lavelNodeList;
    }

    private List<Boolean> addOneToBinaryNumber(List<Boolean> binaryNum) {
        LinkedList<Boolean> result = new LinkedList<>();
        boolean flipNext = false;

        for (int i = binaryNum.size() - 1; i >= 0; i--) {
            boolean val = binaryNum.get(i);

            if (!val && i == binaryNum.size() - 1) {
                result.addFirst(true);
                continue;
            }

            if (val && i == binaryNum.size() - 1) {
                result.addFirst(false);
                flipNext = true;
                continue;
            }

            if (val && flipNext) {
                result.addFirst(false);
                continue;
            }

            if (flipNext) {
                result.addFirst(true);
                flipNext = false;
                continue;
            }

            result.addFirst(val);
        }

        return result;
    }

    public ArrayList<BSTNode> DeepAllNodes(int order) {
        return deepAllNodes(order, Root).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<BSTNode<T>> deepAllNodes(int order, BSTNode<T> node) {
        List<BSTNode<T>> deepNodeList = new ArrayList<>();

        if (node == null) {
            return deepNodeList;
        }

        if (order == 0) {
            deepNodeList.addAll(deepAllNodes(order, node.LeftChild));
            deepNodeList.add(node);
            deepNodeList.addAll(deepAllNodes(order, node.RightChild));

            return deepNodeList;
        }

        if (order == 1) {
            deepNodeList.addAll(deepAllNodes(order, node.LeftChild));
            deepNodeList.addAll(deepAllNodes(order, node.RightChild));
            deepNodeList.add(node);

            return deepNodeList;
        }

        if (order == 2) {
            deepNodeList.add(node);
            deepNodeList.addAll(deepAllNodes(order, node.LeftChild));
            deepNodeList.addAll(deepAllNodes(order, node.RightChild));

            return deepNodeList;
        }

        return deepNodeList;
    }
}
