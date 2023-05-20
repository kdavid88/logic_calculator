package controller;

import javafx.beans.Observable;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.LogicCalculatorModel;
import model.LogicFormula;
import model.LogicVariable;


public class LogicCalculatorController {
     LogicCalculatorModel model;



     @FXML
     private ListView formulaList;

     @FXML
     private Label leftFormulaLabel, righFormulaLabel;
     @FXML
     private TextField newVariable;




     @FXML
     public void initialize(){
          model = new LogicCalculatorModel();



     }

     public void formulaToLEft(){
          String leftFormula = formulaList.getSelectionModel().getSelectedItem().toString();
          leftFormulaLabel.setText(leftFormula);
     }
     public void formulaToRight(){
          String rightFormula = formulaList.getSelectionModel().getSelectedItem().toString();
          righFormulaLabel.setText(rightFormula);
     }

     public void addNewVariable(){
          String newVariableName = newVariable.getText();
          model.Formulas.add(new LogicVariable(newVariableName));
          formulaList.getItems().clear();
          formulaList.getItems().setAll(model.Formulas);
          newVariable.clear();

     }
}
