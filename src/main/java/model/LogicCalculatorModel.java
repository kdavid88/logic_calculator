package model;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.Arrays;
//import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
//import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LogicCalculatorModel {
    //  should be private?
    private Vector<LogicFormula> Formulas = new Vector<LogicFormula>();
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
// todo evaluation doesnt work on loadad compound formulas.
    public void open(String filePath) throws IOException {
        var s = Files.readString(Path.of(filePath));
        this.filePath.set(filePath);
        LogicFormula[] formulasRead = objectMapper.readValue(new FileReader(filePath), LogicFormula[].class);
        Formulas.clear();
        for (LogicFormula formula : formulasRead)
        {
            Formulas.add(formula);
        }

        //modified.set(false);
    }

    public void toJsonTest() throws IOException {
        //Could not make type annotation work with Vector, had to convert to array
        LogicFormula[] formulasToSave = new LogicFormula[Formulas.size()];
        Formulas.toArray(formulasToSave);
        try (var writer = new FileWriter("filename.json")) {
            objectMapper.writeValue(writer, formulasToSave);
        }
    }

    public void read() throws IOException {

        LogicFormula[] formulasRead = objectMapper.readValue(new FileReader("filename.json"), LogicFormula[].class);
        Formulas = new Vector<LogicFormula>(Arrays.asList(formulasRead));
    }

    public LogicFormula getFormula(int index){
        return Formulas.get(index);
    }

    public void addFormula(LogicFormula formula){
        Formulas.add(formula);
    }

    public Vector<LogicFormula> getFormulas(){
        return Formulas;
    }










}
