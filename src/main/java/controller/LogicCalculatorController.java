package controller;

import javafx.beans.Observable;
import javafx.beans.value.ObservableStringValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class LogicCalculatorController {
     @FXML
     private ListView formulaList;

     @FXML
     private Label leftFormulaLabel, righFormulaLabel;
     @FXML
     private TextField newVariable;




     @FXML
     public void initialize(){

     }

     public void formulaToLEft(){
          String leftFormula = (String) formulaList.getSelectionModel().getSelectedItem();
          leftFormulaLabel.setText(leftFormula);
     }
     public void formulaToRight(){
          String rightFormula = (String) formulaList.getSelectionModel().getSelectedItem();
          righFormulaLabel.setText(rightFormula);
     }

     public void addNewVariable(){
          String newVariableName = newVariable.getText();
          formulaList.getItems().add(newVariableName);
          newVariable.clear();
     }
}
