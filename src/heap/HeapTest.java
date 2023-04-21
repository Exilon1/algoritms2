package heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeapTest {

    private int[] arr;
    private Heap heap = new Heap();

    @BeforeEach
    void initialize() {
        arr = new int[]{
                11,
                9,
                4,
                7,
                8,
                3,
                1,
                2,
                5,
                6
        };
    }

    @Test
    void makeHeapTest() {
        heap.MakeHeap(arr, 3);

        assertEquals(15, heap.HeapArray.length);
        assertEquals(6, heap.HeapArray[9]);
        assertEquals(Integer.MIN_VALUE, heap.HeapArray[10]);
    }

    @Test
    void getMaxTest() {
        heap.MakeHeap(arr, 3);

        assertEquals(11, heap.GetMax());
        assertEquals(Integer.MIN_VALUE, heap.HeapArray[9]);
        assertEquals(9, heap.HeapArray[0]);
        assertEquals(8, heap.HeapArray[1]);
        assertEquals(6, heap.HeapArray[4]);

        assertEquals(9, heap.GetMax());
        assertEquals(Integer.MIN_VALUE, heap.HeapArray[8]);

        assertEquals(8, heap.GetMax());
        assertEquals(Integer.MIN_VALUE, heap.HeapArray[7]);
    }

    @Test
    void addTest() {
        heap.MakeHeap(new int[0], 1);

        assertTrue(heap.Add(0));
        assertTrue(heap.Add(1));
        assertTrue(heap.Add(2));
        assertFalse(heap.Add(3));

        heap.MakeHeap(new int[0], 3);

        assertTrue(heap.Add(11));
        assertTrue(heap.Add(9));
        assertTrue(heap.Add(4));
        assertTrue(heap.Add(7));
        assertTrue(heap.Add(8));
        assertTrue(heap.Add(3));
        assertTrue(heap.Add(1));
        assertTrue(heap.Add(2));
        assertTrue(heap.Add(5));
        assertTrue(heap.Add(6));

        assertEquals(11, heap.HeapArray[0]);
        assertEquals(9, heap.HeapArray[1]);
        assertEquals(4, heap.HeapArray[2]);
        assertEquals(7, heap.HeapArray[3]);
        assertEquals(8, heap.HeapArray[4]);
        assertEquals(3, heap.HeapArray[5]);
        assertEquals(1, heap.HeapArray[6]);
        assertEquals(2, heap.HeapArray[7]);
        assertEquals(5, heap.HeapArray[8]);
        assertEquals(6, heap.HeapArray[9]);
        assertEquals(Integer.MIN_VALUE, heap.HeapArray[10]);

    }
}
