package model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
   //Jackson needs these
    @SuppressWarnings("unused")
    public LogicFormula getLeftSubFormula() {
        return leftSubFormula;
    }
    @SuppressWarnings("unused")
    public LogicFormula getRightSubFormula() {
        return rightSubFormula;
    }
    @SuppressWarnings("unused")
    public LogicFormula() {
    }
}
