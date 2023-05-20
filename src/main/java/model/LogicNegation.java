package model;

public class LogicNegation extends LogicFormula{
    public LogicNegation(LogicFormula sub){
        super("¬");
        rightSubFormula = sub;
    }

    @Override
    public boolean evaluate() {
        return !rightSubFormula.evaluate();
    }

}
