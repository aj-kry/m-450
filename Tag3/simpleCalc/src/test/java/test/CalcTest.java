package test;

import main.Calc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalcTest {
    @Test
    void testAdd() {
        Calc c = new Calc();
        assertEquals(5.0, c.add(2, 3));
    }

    @Test
    void testSubtract() {
        Calc c = new Calc();
        assertEquals(-1.0, c.subtract(2, 3));
    }

    @Test
    void testMultiply() {
        Calc c = new Calc();
        assertEquals(6.0, c.multiply(2, 3));
    }

    @Test
    void testDivide() {
        Calc c = new Calc();
        assertEquals(2.0, c.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        Calc c = new Calc();
        assertThrows(IllegalArgumentException.class, () -> c.divide(1, 0));
    }
}
