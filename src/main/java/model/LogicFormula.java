package model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public abstract class LogicFormula {

    protected LogicFormula leftSubFormula, rightSubFormula;
    private boolean currentValue;


    private String symbol;
    public String getSymbol() {
        return symbol;
    }


    public LogicFormula(String symbol) {
        this.symbol = symbol;
    }


    public boolean isCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(boolean currentValue) {
        this.currentValue = currentValue;

    }

    public LogicFormula getLeftSubFormula() {
        return leftSubFormula;
    }

    public void setLeftSubFormula(LogicFormula leftSubFormula) {
        this.leftSubFormula = leftSubFormula;
    }

    public LogicFormula getRightSubFormula() {
        return rightSubFormula;
    }

    public void setRightSubFormula(LogicFormula rightSubFormula) {
        this.rightSubFormula = rightSubFormula;
    }

    @Override
    public String toString(){
        String right = (rightSubFormula == null) ? "" : rightSubFormula.toString();
        String left = (leftSubFormula == null) ? "" : leftSubFormula.toString();
        return left + symbol + right;
    }

    public abstract boolean evaluate();

    public LogicFormula() {
    }
}
