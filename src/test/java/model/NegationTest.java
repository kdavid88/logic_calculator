package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegationTest {
    final Variable x = new Variable("x",true);
    final Negation negX = new Negation(x);
    final Negation negNegX = new Negation(negX);
    final Negation invalidNeg = new Negation(null);

    @Test
    void testEvaluate() {
        assertFalse(negX.evaluate());
        assertTrue(negNegX.evaluate());
        assertThrows(MissingArgumentException.class, ()->invalidNeg.evaluate());
    }

}