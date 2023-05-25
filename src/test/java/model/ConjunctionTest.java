package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConjunctionTest {
    final Variable trueVar = new Variable("x",true);
    final Variable falseVar = new Variable("y",false);
    final Conjunction conjunction11 = new Conjunction(trueVar,trueVar);
    final Conjunction conjunction10 = new Conjunction(trueVar,falseVar);
    final Conjunction conjunction00 = new Conjunction(falseVar,falseVar);
    final Conjunction invalidConjunctionLeft = new Conjunction(null,trueVar);
    final Conjunction invalidConjunctionRight = new Conjunction(trueVar,null);
    final Conjunction invalidConjunctionBoth = new Conjunction(null,null);

    @Test
    void TestEvaluate_caseTrueTrue() {
        assertTrue(conjunction11.evaluate());
    }
    @Test
    void TestEvaluate_caseTrueFalse() {
        assertFalse(conjunction10.evaluate());
    }
    @Test
    void TestEvaluate_caseFalseFalse() {
        assertFalse(conjunction00.evaluate());
    }
    @Test
    void TestEvaluate_leftNull() {
        assertThrows(MissingArgumentException.class, invalidConjunctionLeft::evaluate);
    }
    @Test
    void TestEvaluate_rightNull() {
        assertThrows(MissingArgumentException.class, invalidConjunctionRight::evaluate);
    }
    @Test
    void TestEvaluate_bothNull() {
        assertThrows(MissingArgumentException.class, invalidConjunctionBoth::evaluate);
    }
}