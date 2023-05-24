package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisjunctionTest {
    Variable trueVar = new Variable("x",true);
    Variable falseVar = new Variable("y",false);
    Disjunction disjunction11 = new Disjunction(trueVar,trueVar);
    Disjunction disjunction10 = new Disjunction(trueVar,falseVar);
    Disjunction disjunction00 = new Disjunction(falseVar,falseVar);

    @Test
    void evaluate() {
        assertTrue(disjunction11.evaluate());
        assertTrue(disjunction10.evaluate());
        assertFalse(disjunction00.evaluate());
    }
}