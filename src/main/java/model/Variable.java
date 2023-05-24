package model;

public class Variable extends LogicFormula{

    @Override
    public boolean evaluate() {
        return super.isCurrentValue();
    }

    public Variable(String name, boolean value)
    {
        super(name);
        // default value of variables is false;
        this.setCurrentValue(value);
    }
}
