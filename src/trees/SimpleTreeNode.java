package trees;

import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    private int level;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;

        if (parent == null) {
            level = 0;
        } else {
            level = parent.getLevel() + 1;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
        if (Root != null) {
            Root.setLevel(0);
        }
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode == null && Root == null) {
            Root = NewChild;
            Root.setLevel(0);
            return;
        }

        if (ParentNode == null || NewChild == null) {
            return;
        }

        NewChild.Parent = ParentNode;

        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }

        ParentNode.Children.add(NewChild);
        NewChild.setLevel(ParentNode.getLevel() + 1);
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        NodeToDelete.Parent.Children.remove(NodeToDelete);
        NodeToDelete.Parent = null;
        NodeToDelete.setLevel(-1);
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        return getNodes(Root);
    }

    private List<SimpleTreeNode<T>> getNodes(SimpleTreeNode<T> node) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();

        nodes.add(node);

        if (node.Children == null) {
            return nodes;
        }

        for (SimpleTreeNode<T> n: node.Children) {
            nodes.addAll(getNodes(n));
        }

        return nodes;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        return findNodesByValue(val, Root);
    }

    private List<SimpleTreeNode<T>> findNodesByValue(T val, SimpleTreeNode<T> node) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();

        if (val.equals(node.NodeValue)) {
            nodes.add(node);
        }

        if (childrenIsEmpty(node)) {
            return nodes;
        }

        for (SimpleTreeNode<T> n: node.Children) {
            nodes.addAll(findNodesByValue(val, n));
        }

        return nodes;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        return count(Root);
    }

    private int count(SimpleTreeNode<T> node) {
        int count = 0;

        if (node == null) {
            return count;
        }

        count++;

        if (childrenIsEmpty(node)) {
            return count;
        }

        for (SimpleTreeNode<T> n: node.Children) {
            count = count + count(n);
        }

        return count;
    }

    public int LeafCount() {
        return leafCount(Root);
    }

    private int leafCount(SimpleTreeNode<T> node) {
        int count = 0;

        if (node == null) {
            return count;
        }

        if (childrenIsEmpty(node)) {
            count++;
            return count;
        }

        for (SimpleTreeNode<T> n: node.Children) {
            count = count + leafCount(n);
        }

        return count;
    }

    private boolean childrenIsEmpty(SimpleTreeNode<T> node) {
        return node.Children == null ||
                node.Children.isEmpty() ||
                node.Children.stream().allMatch(Objects::isNull);
    }
}
