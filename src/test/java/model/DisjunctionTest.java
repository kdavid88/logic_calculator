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
    void TestEvaluate_caseTrueTrue() {
        assertTrue(disjunction11.evaluate());
    }
    @Test
    void TestEvaluate_caseTrueFalse() {
        assertTrue(disjunction10.evaluate());
    }
    @Test
    void TestEvaluate_caseFalseFalse() {
        assertFalse(disjunction00.evaluate());
    }
    @Test
    void TestEvaluate_leftNull() {
        assertThrows(MissingArgumentException.class, invalidDisjunctionLeft::evaluate);
    }
    @Test
    void TestEvaluate_rightNull() {
        assertThrows(MissingArgumentException.class, invalidDisjunctionRight::evaluate);
    }
    @Test
    void TestEvaluate_bothNull() {
        assertThrows(MissingArgumentException.class, invalidDisjunctionBoth::evaluate);
    }
    
    
}