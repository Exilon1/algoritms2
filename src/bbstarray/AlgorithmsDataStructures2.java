package bbstarray;

import java.util.*;

public class AlgorithmsDataStructures2 {

    public static int[] GenerateBBSTArray(int[] a) {
        int[] copy = new int[a.length];

        System.arraycopy(a, 0, copy, 0, a.length);

        Arrays.sort(copy);

        int[] bst = new int[a.length];

        fillBBSTArray(copy, bst, 0);

        return bst;
    }

    private static void fillBBSTArray(int[] sortedArray, int[] bstArray, int index) {
        int val = sortedArray[sortedArray.length/2];

        bstArray[index] = val;

        if (sortedArray.length/2 == 0) {
            return;
        }

        int[] leftHalfArray = new int[sortedArray.length/2];
        int[] rightHalfArray = new int[sortedArray.length/2];

        System.arraycopy(sortedArray, 0, leftHalfArray, 0, leftHalfArray.length);
        System.arraycopy(sortedArray, sortedArray.length/2 + 1, rightHalfArray, 0, rightHalfArray.length);

        fillBBSTArray(leftHalfArray, bstArray, 2 * index + 1);
        fillBBSTArray(rightHalfArray, bstArray, 2 * index + 2);
    }

}
