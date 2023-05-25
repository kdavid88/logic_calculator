package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegationTest {
    final Variable x = new Variable("x",true);
    final Negation negX = new Negation(x);
    final Negation negNegX = new Negation(negX);
    final Negation invalidNeg = new Negation(null);

    @Test
    void testEvaluate_caseFalse() {
        assertFalse(negX.evaluate());

    }
    @Test
    void testEvaluate_caseTrue() {
        assertTrue(negNegX.evaluate());
    }
    @Test
    void testEvaluate_caseNull() {
        assertThrows(MissingArgumentException.class, invalidNeg::evaluate);
    }

}