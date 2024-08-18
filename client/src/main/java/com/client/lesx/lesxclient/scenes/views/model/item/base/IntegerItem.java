package com.client.lesx.lesxclient.scenes.views.model.item.base;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import lombok.Setter;
import org.controlsfx.control.PropertySheet;

import java.util.Optional;

@Setter
public class IntegerItem implements PropertySheet.Item {

    private String category;
    private String name;
    private String description;
    private IntegerProperty value = new SimpleIntegerProperty();
    private boolean isEditable = true;


    @Override
    public Class<?> getType() {
        return Integer.class;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Object getValue() {
        return value.getValue();
    }

    @Override
    public void setValue(Object v) {
        this.value.setValue((Integer) v);
    }

    @Override
    public Optional<ObservableValue<? extends Object>> getObservableValue() {
        return Optional.of(value);
    }

    @Override
    public boolean isEditable() {
        return isEditable;
    }
}
