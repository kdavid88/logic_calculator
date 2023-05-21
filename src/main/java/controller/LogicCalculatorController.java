package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import jdk.dynalink.CallSiteDescriptor;
import model.*;

import java.io.IOException;


public class LogicCalculatorController {
     LogicCalculatorModel model;
     private int leftIndex, rightIndex;
     @FXML
     private ListView formulaList;
     @FXML
     private Label leftFormulaLabel, righFormulaLabel, resultLabel;
     @FXML
     private TextField newVariable;

     @FXML
     private TableView<variableData> tableView;
     @FXML
     private TableColumn<variableData,String> nameColumn;
     @FXML
     private TableColumn<variableData,String> valueColumn;

     private ObservableList<variableData> variablesToTable = FXCollections.observableArrayList();

     int lastSelected = -1;

     @FXML
     public void initialize(){
          model = new LogicCalculatorModel();
          nameColumn.setCellValueFactory(new PropertyValueFactory<variableData,String>("name"));
          valueColumn.setCellValueFactory(new PropertyValueFactory<variableData,String>("value"));
          tableView.setItems(variablesToTable);
          tableView.setEditable(true);
          valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());




     }

     public void formulaToLEft(){
          String leftFormula = formulaList.getSelectionModel().getSelectedItem().toString();
          leftFormulaLabel.setText(leftFormula);
          leftIndex = formulaList.getSelectionModel().getSelectedIndex();
     }
     public void formulaToRight(){
          String rightFormula = formulaList.getSelectionModel().getSelectedItem().toString();
          righFormulaLabel.setText(rightFormula);
          rightIndex = formulaList.getSelectionModel().getSelectedIndex();
     }

     public void addNewVariable(){
          String newVariableName = newVariable.getText();
          model.Formulas.add(new LogicVariable(newVariableName));
          formulaListReset();
          newVariable.clear();
     }

     private void formulaListReset(){
          formulaList.getItems().clear();
          formulaList.getItems().setAll(model.Formulas);

          variablesToTable.clear();
          int index = 0;
          for (LogicFormula form : model.Formulas)
          {
               if (form.getClass() == LogicVariable.class)
               {
                    String value = form.isCurrentValue() ? "1" : "0";
                    variablesToTable.add(new variableData(form.getSymbol(),value,index));
               }
               index++;
          }


     }

     // todo: exception handling for empty subformulas.
     // toto: maybe put these into interface?
     public void makeConjunction(){
          LogicFormula toAdd = new LogicConjunction(model.Formulas.get(leftIndex),model.Formulas.get(rightIndex));
          model.Formulas.add(toAdd);
          formulaListReset();
     }

     public void makeDisjunction(){
          LogicFormula toAdd = new LogicDisjunction(model.Formulas.get(leftIndex),model.Formulas.get(rightIndex));
          model.Formulas.add(toAdd);
          formulaListReset();
     }


     public void makeNegation(){
          LogicFormula toAdd = new LogicNegation(model.Formulas.get(leftIndex));
          model.Formulas.add(toAdd);
          formulaListReset();
     }

     public void selectFormula(){
          lastSelected = formulaList.getSelectionModel().getSelectedIndex();
     }

     public void calculateResult() {
          String answer = "no formula selected yet";
          if (lastSelected != -1)
               answer = String.valueOf(model.Formulas.get(lastSelected).evaluate());
          resultLabel.setText(answer);
     }

     // a tableview frissíteset ki lehet venni, mert ugy is reseteljuk
     public void changeVarEvent(TableColumn.CellEditEvent editedCell){
          variableData varSelected = tableView.getSelectionModel().getSelectedItem();
          int index = editedCell.getTablePosition().getRow();
          Boolean newValue;
          String newValueString;
          if (editedCell.getNewValue().toString().equals("0")){
               newValue = false;
               newValueString = "0";
          }
          else {
               newValue = true;
               newValueString = "1";
          }
          //varSelected.setValue(newValueString);
          model.Formulas.get(index).setCurrentValue(newValue);
          tableView.refresh();
          formulaListReset();

     }



}
