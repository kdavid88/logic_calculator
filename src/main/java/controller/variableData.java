package controller;

import javafx.beans.property.SimpleStringProperty;

// used in the tableView the edit values of variables.
public class variableData {
    final private SimpleStringProperty name,value;
    final int index;

    public variableData(String name, String value, int index) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
        this.index = index;
    }

    // The tableView needs these but intelliJ marks them as unused.
    @SuppressWarnings("unused")
    public String getName() {
        return name.get();
    }

    @SuppressWarnings("unused")
    public SimpleStringProperty nameProperty() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name.set(name);
    }

    @SuppressWarnings("unused")
    public String getValue() {
        return value.get();
    }

    @SuppressWarnings("unused")
    public SimpleStringProperty valueProperty() {
        return value;
    }

    @SuppressWarnings("unused")
    public void setValue(String value) {
        this.value.set(value);
    }


}