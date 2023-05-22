package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import jdk.dynalink.CallSiteDescriptor;
import model.*;

import java.io.File;
import java.io.IOException;


public class LogicCalculatorController {
     private final LogicCalculatorModel model = new LogicCalculatorModel();

     // Maybe replace with references?
     private int lastSelected ;

     private int leftIndex, rightIndex;
     private boolean leftAdded = false , rightAdded = false;

     // Probably would be better with listeners but it gets the job done.
     private void activateOperations()
     {
          if (leftAdded) {
               makeNegButton.setDisable(false);
               if (rightAdded){
                    makeDisjButton.setDisable(false);
                    makeConjButton.setDisable(false);
               }
          }
     }

     @FXML
     private ListView formulaList;
     @FXML
     private Label leftFormulaLabel, righFormulaLabel, resultLabel;
     @FXML
     private TextField newVariable;

     @FXML
     Button calculateButton, addLeftFormulaButton, addRightFormulaButton, makeNegButton, makeDisjButton, makeConjButton;

     @FXML
     private TableView<variableData> tableView;
     @FXML
     private TableColumn<variableData,String> nameColumn;
     @FXML
     private TableColumn<variableData,String> valueColumn;

     private ObservableList<variableData> variablesToTable = FXCollections.observableArrayList();

     @FXML
     public void initialize() throws IOException {
          // Disabling buttons
          calculateButton.setDisable(true);
          addLeftFormulaButton.setDisable(true);
          addRightFormulaButton.setDisable(true);
          makeNegButton.setDisable(true);
          makeConjButton.setDisable(true);
          makeDisjButton.setDisable(true);

          //Setting up table for variables
          nameColumn.setCellValueFactory(new PropertyValueFactory<variableData,String>("name"));
          valueColumn.setCellValueFactory(new PropertyValueFactory<variableData,String>("value"));
          tableView.setItems(variablesToTable);
          tableView.setEditable(true);
          valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
          //model.read();
          formulaListReset();
     }

     @FXML
     private void onOpen(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Open");
          File file = fileChooser.showOpenDialog(null);
          if (file != null) {
               //Logger.debug("Opening file {}", file);
               try {
                    model.open(file.getPath());
               } catch (IOException e) {
                    //Logger.error("Failed to open file");
               }
          }
     }

     public void selectFormula(){
          calculateButton.setDisable(false);
          addLeftFormulaButton.setDisable(false);
          addRightFormulaButton.setDisable(false);
          lastSelected = formulaList.getSelectionModel().getSelectedIndex();
     }

     public void calculateResult() {
          String answer = String.valueOf(model.getFormula(lastSelected).evaluate());
          resultLabel.setText(answer);
          //model.toJsonTest();
     }

     public void formulaToLEft(){
          String leftFormula = formulaList.getSelectionModel().getSelectedItem().toString();
          leftFormulaLabel.setText(leftFormula);
          leftIndex = formulaList.getSelectionModel().getSelectedIndex();
          leftAdded = true;
          activateOperations();
     }
     public void formulaToRight(){
          String rightFormula = formulaList.getSelectionModel().getSelectedItem().toString();
          righFormulaLabel.setText(rightFormula);
          rightIndex = formulaList.getSelectionModel().getSelectedIndex();
          rightAdded = true;
          activateOperations();
     }

     public void addNewVariable(){
          String newVariableName = newVariable.getText();
          model.addFormula(new LogicVariable(newVariableName));
          formulaListReset();
          newVariable.clear();
     }

     private void formulaListReset(){
          formulaList.getItems().clear();
          formulaList.getItems().setAll(model.getFormulas());
          variablesToTable.clear();
          int index = 0;
          for (LogicFormula form : model.getFormulas())
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
          LogicFormula toAdd = new LogicConjunction(model.getFormula(leftIndex),model.getFormula(rightIndex));
          model.addFormula(toAdd);
          formulaListReset();
     }

     public void makeDisjunction(){
          LogicFormula toAdd = new LogicDisjunction(model.getFormula(leftIndex),model.getFormula(rightIndex));
          model.addFormula(toAdd);
          formulaListReset();
     }

     public void makeNegation(){
          LogicFormula toAdd = new LogicNegation(model.getFormula(leftIndex));
          model.addFormula(toAdd);
          formulaListReset();
     }

     // Commented lines would refresh tableview, but we relaod it from Formulas
     public void changeVarEvent(TableColumn.CellEditEvent editedCell){
          variableData varSelected = tableView.getSelectionModel().getSelectedItem();
          //int index = editedCell.getTablePosition().getRow();
          Boolean newValue;
          //String newValueString;
          if (editedCell.getNewValue().toString().equals("0")){
               newValue = false;
               //newValueString = "0";
          }
          else {
               newValue = true;
               //newValueString = "1";
          }
          //varSelected.setValue(newValueString);
          model.getFormula(varSelected.index).setCurrentValue(newValue);
          tableView.refresh();
          formulaListReset();
     }
}
