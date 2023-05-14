package controller;

import javafx.beans.property.SimpleStringProperty;

public class FormulaToList {
    private SimpleStringProperty formula;
    public FormulaToList (String formula){
        this.formula = new SimpleStringProperty(formula);
    }
}
