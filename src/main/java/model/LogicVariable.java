package model;

public class LogicVariable extends LogicFormula{

    @Override
    public boolean evaluate() {
        return super.isCurrentValue();
    }

    public LogicVariable(String name)
    {
        super(name);
        // default value of variables is false;
        this.setCurrentValue(false);
    }
}
