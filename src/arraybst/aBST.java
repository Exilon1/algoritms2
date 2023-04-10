package arraybst;

import java.math.BigInteger;

class aBST {
    public Integer Tree[]; // массив ключей

    public aBST(int depth) {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = 0;

        for (int i = 0; i <= depth; i++) {
            tree_size = new BigInteger(String.valueOf(2))
                    .pow(i)
                    .add(new BigInteger(String.valueOf(tree_size)))
                    .intValue();
        }
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) {
            Tree[i] = null;
        }
    }

    public Integer FindKeyIndex(int key) {
        // ищем в массиве индекс ключа
        return findNodeByKey(0, key);
    }

    private Integer findNodeByKey(int index, int key) {
        if (index < 0 || index >= Tree.length) {
            return null;
        }

        if (Tree[index] == null) {
            return -index;
        }

        if (Tree[index] == key) {
            return index;
        }

        if (key < Tree[index]) {
            return findNodeByKey(index * 2 + 1, key);
        }

        return findNodeByKey(index * 2 + 2, key);
    }

    public int AddKey(int key) {
        Integer index = FindKeyIndex(key);
        if (index == null) {
            return -1;
        }

        if (index == 0 && Tree[index] == null) {
            Tree[index] = key;
            return index;
        }

        if (index >= 0) {
            return index;
        }

        Tree[-index] = key;
        return -index;
    }

}
