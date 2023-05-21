package model;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LogicCalculatorModel {
    //  should be private?
    public Vector<LogicFormula> Formulas = new Vector<LogicFormula>();

    // todo: move more vars here

    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    public void toJsonTest(LogicFormula formula) throws IOException {

        try (var writer = new FileWriter("filename.json")) {
            objectMapper.writeValue(writer, formula);
        }
    }

    public void read() throws IOException {
        Formulas.add(objectMapper.readValue(new FileReader("filename.json"), LogicFormula.class));

    }



}
