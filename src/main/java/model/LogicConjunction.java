package model;

public class LogicConjunction extends LogicFormula{
    public LogicConjunction(LogicFormula left, LogicFormula right){
        super("∧");
        leftSubFormula = left;
        rightSubFormula = right;

    }
    @Override
    public boolean evaluate() {
        return super.leftSubFormula.evaluate() && super.rightSubFormula.evaluate();
    }
    @Override
    public String toString() {
        return '(' + super.toString() + ')';
    }

    public LogicConjunction() {
        super();
    }
}
