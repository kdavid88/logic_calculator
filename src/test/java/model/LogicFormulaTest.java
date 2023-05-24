package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicFormulaTest {
    Variable x1 = new Variable("x1",true);
    Variable x2 = new Variable("x2",true);
    Negation negation = new Negation(x1);
    Conjunction conjunction = new Conjunction(x1,x2);
    Disjunction disjunction = new Disjunction(x1,x2);
    Negation negatedConjunction = new Negation(conjunction);
    Conjunction doubleConjunction = new Conjunction(x1,conjunction);
    @BeforeEach
    void setUp() {

    }
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