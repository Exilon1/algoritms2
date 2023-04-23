package heap;

import java.math.BigInteger;
import java.util.Arrays;

class Heap {
    public int[] HeapArray; // хранит неотрицательные числа-ключи

    public Heap() {
        HeapArray = null;
    }

    public void MakeHeap(int[] a, int depth) {
        HeapArray = new int[
                new BigInteger(String.valueOf(2)).pow(depth + 1).subtract(BigInteger.ONE).intValue()
                ];

        int size = Math.min(a.length, HeapArray.length);

        for (int i = 0; i < size; i++) {
            Add(a[i]);
        }
    }

    public int GetMax() {
        int lastIndex = getLastIndex();

        if (lastIndex == -1) {
            return -1;
        }

        int rootValue = HeapArray[0];
        int currentValue = HeapArray[lastIndex];

        HeapArray[lastIndex] = 0;
        HeapArray[0] = currentValue;

        changePositionWithChildren(0);

        return rootValue;
    }

    private void changePositionWithChildren(int sourceIndex) {
        int value = HeapArray[sourceIndex];

        int leftChildIndex = sourceIndex * 2 + 1;
        int rightChildIndex = sourceIndex * 2 + 2;

        if (leftChildIndex >= HeapArray.length || rightChildIndex >= HeapArray.length) {
            return;
        }

        int leftChild = HeapArray[leftChildIndex];
        int rightChild = HeapArray[rightChildIndex];

        int maxChild = Math.max(leftChild, rightChild);

        if (maxChild <= value) {
            return;
        }

        if (maxChild == leftChild) {
            HeapArray[sourceIndex] = leftChild;
            HeapArray[leftChildIndex] = value;

            changePositionWithChildren(leftChildIndex);

            return;
        }

        HeapArray[sourceIndex] = rightChild;
        HeapArray[rightChildIndex] = value;

        changePositionWithChildren(rightChildIndex);
    }

    public boolean Add(int key) {
        int lastIndex = getLastIndex();

        if (lastIndex == HeapArray.length - 1) {
            return false;
        }

        HeapArray[lastIndex + 1] = key;

        changePositionWithParent(lastIndex + 1);

        return true;
    }

    private void changePositionWithParent(int sourceIndex) {
        if (sourceIndex == 0) {
            return;
        }

        int value = HeapArray[sourceIndex];

        int parentIndex = (sourceIndex - 1) / 2;

        int parent = HeapArray[parentIndex];

        if (parent < value) {
            HeapArray[parentIndex] = value;
            HeapArray[sourceIndex] = parent;

            changePositionWithParent(parentIndex);
        }
    }

    private int getLastIndex() {
        int lastIndex = -1;

        for (int i = HeapArray.length - 1; i >= 0; i--) {
            if (HeapArray[i] != 0) {
                lastIndex = i;
                break;
            }
        }

        return lastIndex;
    }
}
