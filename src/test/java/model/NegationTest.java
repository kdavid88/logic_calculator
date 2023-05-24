package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegationTest {
    Variable x = new Variable("x",true);
    Negation negx = new Negation(x);
    Negation negnegx = new Negation(negx);


    @Test
    void testEvaluate() {
        assertFalse(negx.evaluate());
        assertTrue(negnegx.evaluate());
    }
}