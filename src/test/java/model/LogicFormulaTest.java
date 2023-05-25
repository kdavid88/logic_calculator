package model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicFormulaTest {
    final Variable x1 = new Variable("x1",true);
    final Variable x2 = new Variable("x2",true);
    final Negation negation = new Negation(x1);
    final Conjunction conjunction = new Conjunction(x1,x2);
    final Disjunction disjunction = new Disjunction(x1,x2);
    final Negation negatedConjunction = new Negation(conjunction);
    final Conjunction doubleConjunction = new Conjunction(x1,conjunction);
    @Test
    void testToString() {
        assertEquals("x1",x1.toString());
        assertEquals("¬x1",negation.toString());
        assertEquals("(x1∧x2)",conjunction.toString());
        assertEquals("(x1∨x2)",disjunction.toString());
        assertEquals("¬(x1∧x2)",negatedConjunction.toString());
        assertEquals("(x1∧(x1∧x2))",doubleConjunction.toString());
    }



}