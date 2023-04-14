package bbstarray;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmsDataStructures2Test {

    private int[] arr;

    @BeforeEach
    void initialize() {
        arr = new int[] {
                -150,
                -100,
                -50,
                0,
                50,
                100,
                150
        };
    }

    @Test
    void generateBBSTArrayTest() {
        int[] result = AlgorithmsDataStructures2.GenerateBBSTArray(arr);

        assertEquals(arr.length, result.length);

        assertEquals(0, result[0]);
        assertEquals(-100, result[1]);
        assertEquals(100, result[2]);
        assertEquals(-150, result[3]);
        assertEquals(-50, result[4]);
        assertEquals(50, result[5]);
        assertEquals(150, result[6]);

        result = AlgorithmsDataStructures2.GenerateBBSTArray(new int[] {1});

        assertEquals(1, result.length);

        assertEquals(1, result[0]);
    }
}
