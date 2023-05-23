package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LogicCalculatorModel {
    private Vector<LogicFormula> Formulas = new Vector<LogicFormula>();
    private Vector<LogicFormulaSignature> Log = new Vector<LogicFormulaSignature>();
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final StringProperty filePath = new SimpleStringProperty(null);

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
        var s = Files.readString(Path.of(filePath));
        this.filePath.set(filePath);
        LogicFormulaSignature[] formulasRead = objectMapper.readValue(new FileReader(filePath), LogicFormulaSignature[].class);
        for (LogicFormulaSignature signature : formulasRead) {
            addFormulaOfType(signature);
        }
        //modified.set(false);
    }

    public void clearFormulas(){
        Formulas.clear();
        Log.clear();
    }
    public void save() throws IOException {
        if (filePath.get() == null) {
            throw new IllegalStateException();
        }
        saveAs(filePath.get());
    }

    public void saveAs(String filePath) throws IOException {
        var writer = new FileWriter(filePath);
        objectMapper.writeValue(writer, Log);
        //Files.writeString(Path.of(filePath), content.get());
        this.filePath.set(filePath);
        //modified.set(false);
    }

    // This exports a single formula with the entire tree.
    public void export(String filePath, int index) throws IOException {
        var writer = new FileWriter(filePath);
        objectMapper.writeValue(writer, Formulas.get(index));
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

    public void addFormula(LogicFormula formula){
        Formulas.add(formula);
    }

    public void addFormulaOfType(LogicFormulaSignature signature){
        switch (signature.type()) {
            case VAR -> Formulas.add(new Variable(signature.label()));
            case NEG -> Formulas.add(new Negation(Formulas.get(signature.leftSubFormulaIndex())));
            case CON -> Formulas.add(new Conjunction(Formulas.get(signature.leftSubFormulaIndex()),Formulas.get(signature.rightSubFormulaIndex())));
            case DIS -> Formulas.add(new Disjunction(Formulas.get(signature.leftSubFormulaIndex()),Formulas.get(signature.rightSubFormulaIndex())));
        }
        Log.add(signature);
    }

    public Vector<LogicFormula> getFormulas(){
        return Formulas;
    }
}
