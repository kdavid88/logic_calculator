package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConjunctionTest {
    Variable trueVar = new Variable("x",true);
    Variable falseVar = new Variable("y",false);
    Conjunction conjunction11 = new Conjunction(trueVar,trueVar);
    Conjunction conjunction10 = new Conjunction(trueVar,falseVar);
    Conjunction conjunction00 = new Conjunction(falseVar,falseVar);

    @Test
    void TestEvaluate() {
        assertTrue(conjunction11.evaluate());
        assertFalse(conjunction10.evaluate());
        assertFalse(conjunction00.evaluate());
    }
}