package model;

public class Negation extends LogicFormula{
    public Negation(LogicFormula sub){
        super("¬");
        rightSubFormula = sub;
    }

    @Override
    public boolean evaluate() {
        return !rightSubFormula.evaluate();
    }

    public Negation() {
        super();
    }


}
