package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import model.*;

import java.io.File;
import java.io.IOException;


public class LogicCalculatorController {
     private final LogicCalculatorModel model = new LogicCalculatorModel();

     // Should these be in the model?
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
     private ListView<LogicFormula> formulaList;
     @FXML
     private Label leftFormulaLabel, righFormulaLabel, resultLabel;
     @FXML
     private MenuItem exportMenuItem;
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

     final private ObservableList<variableData> variablesToTable = FXCollections.observableArrayList();

     @FXML
     public void initialize() {
          // Disabling buttons
          calculateButton.setDisable(true);
          addLeftFormulaButton.setDisable(true);
          addRightFormulaButton.setDisable(true);
          makeNegButton.setDisable(true);
          makeConjButton.setDisable(true);
          makeDisjButton.setDisable(true);
          exportMenuItem.setDisable(true);

          //Setting up table for variables
          nameColumn.setCellValueFactory(new PropertyValueFactory<variableData,String>("name"));
          valueColumn.setCellValueFactory(new PropertyValueFactory<variableData,String>("value"));
          tableView.setItems(variablesToTable);
          tableView.setEditable(true);
          valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
     }

     //////   File handling

     @FXML
     private void onNew(){
          model.clearFormulas();
          formulaListReset();
     }

     @FXML
     private void onOpen() {
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Open");
          File file = fileChooser.showOpenDialog(null);
          if (file != null) {
               //Logger.debug("Opening file {}", file);
               try {
                    model.open(file.getPath());
                    formulaListReset();
               } catch (IOException e) {
                    //Logger.error("Failed to open file");
               }
          }
     }

     @FXML
     private void onSave() {
          if (model.getFilePath() != null) {
               //Logger.debug("Saving file");
               try {
                    model.save();
               } catch (IOException e) {
                    //Logger.error(e, "Failed to save file");
               }
          } else {
               performSaveAs();
          }
     }

     @FXML
     private void onSaveAs() {
          performSaveAs();
     }

     private void performSaveAs() {
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Save As");
          File file = fileChooser.showSaveDialog(null);
          if (file != null) {
               //Logger.debug("Saving file as {}", file);
               try {
                    model.saveAs(file.getPath());
               } catch (IOException e) {
                    //Logger.error(e, "Failed to save file");
               }
          }
     }

     @FXML
     private void onExport(){
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Export Selected formula");
          File file = fileChooser.showSaveDialog(null);
          if (file != null) {
               //Logger.debug("Saving file as {}", file);
               try {
                    model.export(file.getPath(),lastSelected);
               } catch (IOException e) {
                    //Logger.error(e, "Failed to save file");
               }
          }

     }
     //////   Hangling formulas
     public void selectFormula(){
          calculateButton.setDisable(false);
          addLeftFormulaButton.setDisable(false);
          addRightFormulaButton.setDisable(false);
          lastSelected = formulaList.getSelectionModel().getSelectedIndex();
          exportMenuItem.setDisable(false);
     }



     public void calculateResult() {
          String answer = String.valueOf(model.getFormula(lastSelected).evaluate());
          resultLabel.setText(answer);
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

     public void addNewVariable() {
          String newVariableName = newVariable.getText();
          LogicFormulaSignature signature = new LogicFormulaSignature(FormulaType.VAR,0,0,newVariableName,false);
          model.addFormulaOfType(signature);
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
               if (form.getClass() == Variable.class)
               {
                    String value = form.isCurrentValue() ? "1" : "0";
                    variablesToTable.add(new variableData(form.getSymbol(),value,index));
               }
               index++;
          }
     }

     public void makeConjunction(){
          LogicFormulaSignature signature = new LogicFormulaSignature(FormulaType.CON,leftIndex,rightIndex,"",false);
          model.addFormulaOfType(signature);
          formulaListReset();
     }

     public void makeDisjunction(){
          LogicFormulaSignature signature = new LogicFormulaSignature(FormulaType.DIS,leftIndex,rightIndex,"",false);
          model.addFormulaOfType(signature);
          formulaListReset();
     }

     public void makeNegation(){
          LogicFormulaSignature signature = new LogicFormulaSignature(FormulaType.NEG,leftIndex,0,"",false);
          model.addFormulaOfType(signature);
          formulaListReset();
     }

     // Commented lines would refresh tableview, but we relaod it from Formulas
     public void changeVarEvent(TableColumn.CellEditEvent editedCell){
          variableData varSelected = tableView.getSelectionModel().getSelectedItem();
          boolean newValue = !(editedCell.getNewValue().toString().equals("0"));
          model.getFormula(varSelected.index).setCurrentValue(newValue);
          tableView.refresh();
          formulaListReset();
     }
}
