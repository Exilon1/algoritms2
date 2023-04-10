package arraybst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class aBSTTest {

    private aBST aBst;

    @BeforeEach
    void initialize() {
        aBst = new aBST(4);

        aBst.AddKey(0);
        aBst.AddKey(-100);
        aBst.AddKey(100);
        aBst.AddKey(-150);
        aBst.AddKey(-50);
        aBst.AddKey(50);
        aBst.AddKey(150);
        aBst.AddKey(125);
        aBst.AddKey(175);
        aBst.AddKey(120);
        aBst.AddKey(130);
    }

    @Test
    void findKeyIndex() {
        assertEquals(31, aBst.Tree.length);

        assertEquals(0, aBst.FindKeyIndex(0));
        assertEquals(1, aBst.FindKeyIndex(-100));
        assertEquals(2, aBst.FindKeyIndex(100));
        assertEquals(3, aBst.FindKeyIndex(-150));
        assertEquals(4, aBst.FindKeyIndex(-50));
        assertEquals(5, aBst.FindKeyIndex(50));
        assertEquals(6, aBst.FindKeyIndex(150));
        assertEquals(13, aBst.FindKeyIndex(125));
        assertEquals(14, aBst.FindKeyIndex(175));
        assertEquals(27, aBst.FindKeyIndex(120));
        assertEquals(28, aBst.FindKeyIndex(130));

        assertEquals(-29, aBst.FindKeyIndex(170));
        assertEquals(-30, aBst.FindKeyIndex(180));

        aBst.AddKey(180);

        assertEquals(30, aBst.FindKeyIndex(180));
        assertNull(aBst.FindKeyIndex(190));
    }

    @Test
    void addKey() {
        aBst.AddKey(130);

        assertEquals(28, aBst.FindKeyIndex(130));

        aBst.AddKey(180);

        assertEquals(30, aBst.FindKeyIndex(180));
    }
}
