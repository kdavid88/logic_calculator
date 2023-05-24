package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.nio.file.Files;
import java.nio.file.Path;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LogicCalculatorModel {
    final private Vector<LogicFormula> Formulas = new Vector<>();
    final private Vector<LogicFormulaSignature> formulasLog = new Vector<>();
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final StringProperty filePath = new SimpleStringProperty(null);

    // Selection control
    private int lastSelected, leftIndex, rightIndex;

    public String getFilePath() {
        return filePath.get();
    }

    public StringProperty filePathProperty() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath.set(filePath);
    }
    public void open(String filePath) throws IOException {
        this.filePath.set(filePath);
        LogicFormulaSignature[] formulasRead = objectMapper.readValue(new FileReader(filePath), LogicFormulaSignature[].class);
        for (LogicFormulaSignature signature : formulasRead) {
            addFormulaOfType(signature);
        }
        //modified.set(false);
    }

    public void clearFormulas(){
        Formulas.clear();
        formulasLog.clear();
    }
    public void save() throws IOException {
        if (filePath.get() == null) {
            throw new IllegalStateException();
        }
        saveAs(filePath.get());
    }

    public void saveAs(String filePath) throws IOException {
        var writer = new FileWriter(filePath);
        objectMapper.writeValue(writer, formulasLog);
        //Files.writeString(Path.of(filePath), content.get());
        this.filePath.set(filePath);
        //modified.set(false);
    }

    // This exports a single formula with the entire tree.
    public void export(String filePath) throws IOException {
        var writer = new FileWriter(filePath);
        objectMapper.writeValue(writer, Formulas.get(lastSelected));
        //This could expot the entire vector of formulas. Could not make type annotation work with Vector, had to convert to array
        //LogicFormula[] formulasToSave = new LogicFormula[Formulas.size()];
        //Formulas.toArray(formulasToSave);
    }

    /*
    // Could be used for reading back single formula from json, but it doesn't record interdependencies.
    public void read() throws IOException {
        LogicFormula[] formulasRead = objectMapper.readValue(new FileReader("filename.json"), LogicFormula[].class);
        Formulas = new Vector<LogicFormula>(Arrays.asList(formulasRead));
    }
    */
    public LogicFormula getFormula(int index){
        return Formulas.get(index);
    }

    /*
    public void addFormula(LogicFormula formula){
        Formulas.add(formula);
    }
    */

    public void addFormulaOfType(LogicFormulaSignature signature){
        switch (signature.getType()) {
            case VAR -> Formulas.add(new Variable(signature.getLabel(),signature.isValue()));
            case NEG -> Formulas.add(new Negation(Formulas.get(signature.getLeftSubFormulaIndex())));
            case CON -> Formulas.add(new Conjunction(Formulas.get(signature.getLeftSubFormulaIndex()),Formulas.get(signature.getRightSubFormulaIndex())));
            case DIS -> Formulas.add(new Disjunction(Formulas.get(signature.getLeftSubFormulaIndex()),Formulas.get(signature.getRightSubFormulaIndex())));
        }
        formulasLog.add(signature);
    }

    public void setVariableValue(int index, boolean newValue) {
        Formulas.get(index).setCurrentValue(newValue);
        formulasLog.get(index).setValue(newValue);
    }

    public Vector<LogicFormula> getFormulas() {
        return Formulas;
    }

    public LogicFormula getSelectedFormula() {
        return Formulas.get(lastSelected);
    }

    public int getLastSelected() {
        return lastSelected;
    }

    public void setLastSelected(int lastSelected) {
        this.lastSelected = lastSelected;
    }

    public int getLeftIndex() {
        return leftIndex;
    }

    public void setLeftIndex(int leftIndex) {
        this.leftIndex = leftIndex;
    }

    public int getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(int rightIndex) {
        this.rightIndex = rightIndex;
    }
}
