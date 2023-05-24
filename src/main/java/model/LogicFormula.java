package model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

// todo something breaks when serialized as array
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
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
