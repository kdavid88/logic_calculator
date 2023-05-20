package model;

public abstract class LogicFormula {
    protected LogicFormula leftSubFormula, rightSubFormula;

    public boolean isCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(boolean currentValue) {
        this.currentValue = currentValue;
    }

    private boolean currentValue;
    protected String symbol;



    @Override
    public String toString(){
        String right = (rightSubFormula == null) ? "" : rightSubFormula.toString();
        String left = (leftSubFormula == null) ? "" : leftSubFormula.toString();
        return left + symbol + right;
    }

    public abstract boolean evaluate();

    ;
}
