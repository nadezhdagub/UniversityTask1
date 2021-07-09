package polyclass;

import org.junit.Test;
import polynomial.Poly;

import static org.junit.Assert.*;

public class PolyTest {

    @Test
    public void sum() {
        Poly obj = new Poly(new int[] { 1, 1, 1 });
        assertEquals(new Poly(new int[] { 2, 2, 2 }), obj.sum(new Poly(new int[] { 1, 1, 1 })));
        assertEquals(new Poly(new int[] { 3, 2, 3 }), obj.sum(new Poly(new int[] { 2, 1, 2 })));
        assertEquals(new Poly(new int[] { 3, 3, 3 }), obj.sum(new Poly(new int[] { 2, 2, 2 })));
        assertEquals(new Poly(new int[] { 4, 4, 4 }), obj.sum(new Poly(new int[] { 3, 3, 3 })));
        assertEquals(new Poly(new int[] { 1, 1, 1 }), obj.sum(new Poly(new int[] {})));
    }

    @Test
    public void value() {
        assertEquals(new Poly(new int[] { 1, 1, 1 }).value(1), 3);
        assertEquals(new Poly(new int[] { 1, 0, 1 }).value(1), 2);
        assertEquals(new Poly(new int[] { 1, 2, 3 }).value(2), 17);
        assertEquals(new Poly(new int[] { 1, 0, 3, 1 }).value(5), 201);
    }

    @Test
    public void multiplication() {
        Poly obj = new Poly(new int[] { 1, 1, 1 });
        assertEquals(obj.multiplication(new Poly(new int[] { 1, 4, 2 })), new Poly(new int[] { 1, 5, 7, 6, 2 }));
        assertEquals(obj.multiplication(new Poly(new int[] { 1, 1, 1 })), new Poly(new int[] { 1, 2, 3, 2, 1 }));
    }

    @Test
    public void subtraction() {
        Poly obj = new Poly(new int[] { 1, 1, 1 });
        assertEquals(obj.subtraction(new Poly(new int[] { 1, 1, 1 })), new Poly(new int[] {}));
        assertEquals(obj.subtraction(new Poly(new int[] { -1, 1, 1 })), new Poly(new int[] { 2 }));
        assertEquals(obj.subtraction(new Poly(new int[] {})), new Poly(new int[] { 1, 1, 1 }));
        assertEquals(obj.subtraction(new Poly(new int[] { -1, -1, -1 })), new Poly(new int[] { 2, 2, 2 }));
    }

    @Test
    public void division() {
        assertEquals(new Poly(new int[] { 1, 1, 1 }).division(new Poly(new int[] { 1, 1, 1 })),
                new Poly(new int[] { 1 }));
        assertEquals(new Poly(new int[] { 1, 1, 1 }).division(new Poly(new int[] { 1, 1 })),
                new Poly(new int[] { 0, 1 }));
        assertEquals(new Poly(new int[] { 1, 1, 1 }).division(new Poly(new int[] { 0, 1 })),
                new Poly(new int[] { 1, 1 }));
    }

    @Test
    public void remainder() {
        assertEquals(new Poly(new int[] { 1, 1, 1 }).remainder(new Poly(new int[] { 1, 1, 1 })),
                new Poly(new int[] {}));
        assertEquals(new Poly(new int[] { 1, 1, 1 }).remainder(new Poly(new int[] { 1, 1 })),
                new Poly(new int[] { 1 }));
        assertEquals(new Poly(new int[] { 1, 1, 1 }).remainder(new Poly(new int[] { 0, 1 })),
                new Poly(new int[] { 1 }));
    }

    @Test
    public void equals() {
        assertTrue(new Poly(new int[] {}).equals(new Poly(new int[] {})));
        assertFalse(new Poly(new int[] {}).equals(new Poly(new int[] { 1 })));
        assertTrue(new Poly(new int[] { 0, 1, 0, 1 }).equals(new Poly(new int[] { 0, 1, 0, 1 })));
        assertFalse(new Poly(new int[] { -1, -1, -1, -1 }).equals(new Poly(new int[] { 1, 1, 1, 1 })));
        assertTrue(new Poly(new int[] { 1, 1, 1, 1 }).equals(new Poly(new int[] { 1, 1, 1, 1 })));
        assertTrue(new Poly(new int[] { -1, -1, -1, -1 }).equals(new Poly(new int[] { -1, -1, -1, -1 })));
    }
}
