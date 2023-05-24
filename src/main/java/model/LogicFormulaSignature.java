package model;

public record LogicFormulaSignature(FormulaType type, int leftSubFormulaIndex, int rightSubFormulaIndex, String label, boolean value) {}
