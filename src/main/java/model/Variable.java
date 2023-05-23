package model;

public class Variable extends LogicFormula{

    @Override
    public boolean evaluate() {
        return super.isCurrentValue();
    }

    public Variable(String name)
    {
        super(name);
        // default value of variables is false;
        this.setCurrentValue(false);
    }

    public Variable() {
        super();
    }

}
