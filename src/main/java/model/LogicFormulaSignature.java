package model;

public class LogicFormulaSignature {
    private FormulaType type;
    private int leftSubFormulaIndex, rightSubFormulaIndex;
    private String label;
    private boolean value;

    public LogicFormulaSignature(FormulaType type, int leftSubFormulaIndex, int rightSubFormulaIndex, String label, boolean value) {
        this.type = type;
        this.leftSubFormulaIndex = leftSubFormulaIndex;
        this.rightSubFormulaIndex = rightSubFormulaIndex;
        this.label = label;
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public FormulaType getType() {
        return type;
    }

    public int getLeftSubFormulaIndex() {
        return leftSubFormulaIndex;
    }

    public int getRightSubFormulaIndex() {
        return rightSubFormulaIndex;
    }

    /*
    public void setRightSubFormulaIndex(int rightSubFormulaIndex) {
        this.rightSubFormulaIndex = rightSubFormulaIndex;
    }

     */

    public String getLabel() {
        return label;
    }

    @SuppressWarnings("unused")
    public LogicFormulaSignature() {
    }

    public boolean isValue() {
        return value;
    }
}
