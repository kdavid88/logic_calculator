package model;

public class Negation extends LogicFormula{
    public Negation(LogicFormula sub){
        super("¬");
        rightSubFormula = sub;
    }

    @Override
    public boolean evaluate() {
        if (rightSubFormula == null) throw new MissingArgumentException();

        return !rightSubFormula.evaluate();
    }
}
