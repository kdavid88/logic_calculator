package model;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    public void toJsonTest() throws IOException {

        try (var writer = new FileWriter("filename.json")) {
            objectMapper.writeValue(writer, Formulas);
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
