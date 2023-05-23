package model;

public class Disjunction extends LogicFormula{
    public Disjunction(LogicFormula left, LogicFormula right){
        super("∨");
        leftSubFormula = left;
        rightSubFormula = right;

    }
    @Override
    public boolean evaluate() {
        return super.leftSubFormula.evaluate() || super.rightSubFormula.evaluate();
    }
    @Override
    public String toString() {
        return '(' + super.toString() + ')';
    }

    public Disjunction() {
        super();
    }

}
