package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisjunctionTest {
    final Variable trueVar = new Variable("x",true);
    final Variable falseVar = new Variable("y",false);
    final Disjunction disjunction11 = new Disjunction(trueVar,trueVar);
    final Disjunction disjunction10 = new Disjunction(trueVar,falseVar);
    final Disjunction disjunction00 = new Disjunction(falseVar,falseVar);
    final Disjunction invalidDisjunctionLeft = new Disjunction(null,trueVar);
    final Disjunction invalidDisjunctionRight = new Disjunction(trueVar,null);
    final Disjunction invalidDisjunctionBoth = new Disjunction(null,null);

    @Test
    void evaluate() {
        assertTrue(disjunction11.evaluate());
        assertTrue(disjunction10.evaluate());
        assertFalse(disjunction00.evaluate());
        assertThrows(MissingArgumentException.class, ()->invalidDisjunctionLeft.evaluate());
        assertThrows(MissingArgumentException.class, ()->invalidDisjunctionRight.evaluate());
        assertThrows(MissingArgumentException.class, ()->invalidDisjunctionBoth.evaluate());
    }
}