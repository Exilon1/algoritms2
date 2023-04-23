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

    void initializeSecond() {
        arr = new int[]{
                110,
                90,
                40,
                70,
                80,
                30,
                10,
                20,
                50,
                60,
                65,
                31,
                29,
                11,
                9
        };
    }

    @Test
    void makeHeapTest() {
        heap.MakeHeap(arr, 3);

        assertEquals(15, heap.HeapArray.length);
        assertEquals(6, heap.HeapArray[9]);
        assertEquals(0, heap.HeapArray[10]);

        initializeSecond();

        heap.MakeHeap(arr, 3);

        assertEquals(110, heap.HeapArray[0]);
        assertEquals(90, heap.HeapArray[1]);
        assertEquals(40, heap.HeapArray[2]);
        assertEquals(70, heap.HeapArray[3]);
        assertEquals(80, heap.HeapArray[4]);
        assertEquals(31, heap.HeapArray[5]);
        assertEquals(11, heap.HeapArray[6]);
        assertEquals(20, heap.HeapArray[7]);
        assertEquals(50, heap.HeapArray[8]);
        assertEquals(60, heap.HeapArray[9]);
        assertEquals(65, heap.HeapArray[10]);
        assertEquals(30, heap.HeapArray[11]);
        assertEquals(29, heap.HeapArray[12]);
        assertEquals(10, heap.HeapArray[13]);
        assertEquals(9, heap.HeapArray[14]);
    }

    @Test
    void getMaxTest() {
        heap.MakeHeap(arr, 3);

        assertEquals(11, heap.GetMax());
        assertEquals(0, heap.HeapArray[9]);
        assertEquals(9, heap.HeapArray[0]);
        assertEquals(8, heap.HeapArray[1]);
        assertEquals(6, heap.HeapArray[4]);

        assertEquals(9, heap.GetMax());
        assertEquals(0, heap.HeapArray[8]);

        assertEquals(8, heap.GetMax());
        assertEquals(0, heap.HeapArray[7]);
    }

    @Test
    void addTest() {
        heap.MakeHeap(new int[0], 1);

        assertTrue(heap.Add(1));
        assertTrue(heap.Add(2));
        assertTrue(heap.Add(3));
        assertFalse(heap.Add(4));

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
        assertEquals(0, heap.HeapArray[10]);

    }
}
