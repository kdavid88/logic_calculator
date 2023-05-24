package controller;

import javafx.beans.property.SimpleStringProperty;

public class variableData {
    final private SimpleStringProperty name,value;
    final int index;

    public variableData(String name, String value, int index) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
        this.index = index;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }
}