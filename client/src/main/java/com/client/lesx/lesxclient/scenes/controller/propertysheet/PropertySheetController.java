package com.client.lesx.lesxclient.scenes.controller.propertysheet;

import com.client.lesx.lesxclient.constants.EControllerConstants;
import com.client.lesx.lesxclient.constants.EModelItems;
import com.client.lesx.lesxclient.scenes.controller.base.DefaultController;
import com.client.lesx.lesxclient.scenes.utils.NamesMapUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import org.controlsfx.control.PropertySheet;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Consumer;

import static com.client.lesx.lesxclient.constants.EControllerConstants.*;
import static com.client.lesx.lesxclient.constants.NameIdConstants.*;

@Log4j2
public class PropertySheetController implements DefaultController {

    private final EnumMap<EControllerConstants, Object> properties = new EnumMap<>(EControllerConstants.class);

    @FXML
    Label description;
    @FXML
    PropertySheet propertySheet;
    @FXML
    Button okButton;
    @FXML
    Button cancelButton;

    private String windowName;
    private EModelItems fromItem;
    private Consumer<List<PropertySheet.Item>> onSaveConsumer;
    private BooleanProperty closeProperty = new SimpleBooleanProperty(false);
    private BooleanProperty dirtyProperty = new SimpleBooleanProperty(false);

    @FXML
    public void initialize() {
        cancelButton.setOnAction(obs -> closeDialog());
        okButton.disableProperty().bind(Bindings.not(dirtyProperty));
        okButton.setOnAction(obs -> saveAction());
    }

    @Override
    public String getWindowsName() {
        return windowName;
    }

    @Override
    public void afterInitialize() {
        buildTextsBasedOnFromItem();
        List<PropertySheet.Item> items = (List<PropertySheet.Item>) properties.get(PROPERTIES);
        addListenerToItemsForChangeTrack(items);
        propertySheet.getItems().addAll(items);
        onSaveConsumer = (Consumer<List<PropertySheet.Item>>) properties.get(ON_SAVE_ACTION);
    }

    private void buildTextsBasedOnFromItem() {
        fromItem = (EModelItems) properties.get(FROM_ITEM);
        String rawWinName;
        String desc;
        switch (fromItem) {
            case FITNESS:
                rawWinName = NamesMapUtils.getStringValueFromMap(FITNESS_WINDOWS_NAME);
                desc = NamesMapUtils.getStringValueFromMap(FITNESS_DESCRIPTION);
                break;
            default:
                rawWinName = NamesMapUtils.getStringValueFromMap(DEFAULT_PROPERTY_WINDOWS_NAME);
                desc = NamesMapUtils.getStringValueFromMap(DEFAULT_PROPERTY_DESCRIPTION);
                break;
        }
        String actionName = properties.get(ACTION).toString();
        windowName = String.format(rawWinName, actionName);
        description.setText(desc);
    }

    @Override
    public void setProperty(EControllerConstants key, Object value) {
        properties.put(key, value);
    }

    @Override
    public BooleanProperty getCloseProperty() {
        return closeProperty;
    }

    @Override
    public void onCloseEvent(WindowEvent onCloseEvent) {
        closeDialogEvent(onCloseEvent);
    }

    private void saveAction() {
        if (onSaveConsumer != null) {
            onSaveConsumer.accept(propertySheet.getItems());
        } else {
            log.warn("No Save Consumer was set in properties for {}, so there is no execute in save changes.", fromItem);
        }
        closeProperty.setValue(true);
    }

    private void closeDialogEvent(WindowEvent onCloseEvent) {
        if (dirtyProperty.get()) {
            ButtonType save = new ButtonType(NamesMapUtils.getStringValueFromMap(SAVE));
            ButtonType dontSave = new ButtonType(NamesMapUtils.getStringValueFromMap(DONT_SAVE));
            ButtonType cancel = new ButtonType(NamesMapUtils.getStringValueFromMap(CANCEL));
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Window window = alert.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(e -> alert.hide());
            alert.setTitle(NamesMapUtils.getStringValueFromMap(DEFAULT_PROPERTY_ALERT_TITLE));
            alert.setHeaderText(String.format(NamesMapUtils.getStringValueFromMap(DEFAULT_PROPERTY_ALERT_DESCRIPTION), fromItem.toString()));
            alert.getButtonTypes()
                    .clear();
            alert.getButtonTypes()
                    .addAll(save, dontSave, cancel);
            ButtonType result = alert.showAndWait()
                    .orElse(null);
            if (cancel.equals(result)) {
                if(onCloseEvent != null) {
                    onCloseEvent.consume();
                }
                return;
            }
            if (save.equals(result)) {
                if (onSaveConsumer != null) {
                    onSaveConsumer.accept(propertySheet.getItems());
                    dirtyProperty.set(false);
                } else {
                    log.warn("No Save Consumer was set in properties for {}, so there is no execute in save changes.", fromItem);
                }
            }
        }
        closeProperty.setValue(true);
    }

    private void closeDialog() {
        closeDialogEvent(null);
    }

    private void addListenerToItemsForChangeTrack(List<PropertySheet.Item> items) {
        for (PropertySheet.Item item : items) {
            if (item.getObservableValue().isPresent()) {
                item.getObservableValue().get().addListener(((observable, oldValue, newValue) -> dirtyProperty.setValue(true)));
            }
        }
    }
}
