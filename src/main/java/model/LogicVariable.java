package model;

public class LogicVariable extends LogicFormula{

    @Override
    public boolean evaluate() {
        return super.isCurrentValue();
    }

    public LogicVariable(String name)
    {
        super();
        this.symbol = name;
    }
}
