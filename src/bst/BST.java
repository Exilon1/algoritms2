package bst;

import trees.SimpleTreeNode;

import java.io.*;
import java.util.*;


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

        if (find.ToLeft) {
            find.Node.LeftChild = newNode;
        } else {
            find.Node.RightChild = newNode;
        }

        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
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

        if (find.Node == null || ! find.NodeHasKey) {
            return false;
        }

        boolean containsInLeftParentNode = find.Node.Parent.LeftChild == find.Node;

        if (find.Node.LeftChild == null && find.Node.RightChild == null) {
            if (containsInLeftParentNode) {
                find.Node.Parent.LeftChild = null;
            } else {
                find.Node.Parent.RightChild = null;
            }

            find.Node.Parent = null;
            return true;
        }

        if (find.Node.RightChild == null) {
            find.Node.LeftChild.Parent = find.Node.Parent;

            if (containsInLeftParentNode) {
                find.Node.Parent.LeftChild = find.Node.LeftChild;
            } else {
                find.Node.Parent.RightChild = find.Node.LeftChild;
            }

            find.Node.Parent = null;

            return true;
        }

        if (find.Node.LeftChild == null) {
            find.Node.RightChild.Parent = find.Node.Parent;

            if (containsInLeftParentNode) {
                find.Node.Parent.LeftChild = find.Node.RightChild;
            } else {
                find.Node.Parent.RightChild = find.Node.RightChild;
            }

            find.Node.Parent = null;

            return true;
        }

        BSTNode<T> node = FinMinMax(find.Node.RightChild, false);

        if (node.Parent.LeftChild == node) {
            node.Parent.LeftChild = null;
        } else {
            node.Parent.RightChild = null;
        }

        node.Parent = find.Node.Parent;
        node.LeftChild = find.Node.LeftChild;

        if (node != find.Node.RightChild) {
            node.RightChild = find.Node.RightChild;
        }

        if (containsInLeftParentNode) {
            find.Node.Parent.LeftChild = node;
        } else {
            find.Node.Parent.RightChild = node;
        }

        find.Node.Parent = null;

        return true;
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

}
