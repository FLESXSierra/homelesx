package com.client.lesx.lesxclient.scenes.views.model.item.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import lombok.Setter;
import org.controlsfx.control.PropertySheet;

import java.util.Optional;

@Setter
public class ObjectItem<T> implements PropertySheet.Item {

    private String category;
    private String name;
    private String description;
    private ObjectProperty<T> value = new SimpleObjectProperty<>();
    private boolean isEditable = true;
    private Class<T> type;

    public ObjectItem(Class<T> type) {
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
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
        this.value.setValue((T) v);
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
