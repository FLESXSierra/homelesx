package com.client.lesx.lesxclient.scenes.controller.propertysheet;

import com.client.lesx.lesxclient.constants.EControllerConstants;
import com.client.lesx.lesxclient.scenes.controller.base.DefaultController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.PropertySheet;

import java.util.EnumMap;

public class PropertySheetController implements DefaultController {

    private final EnumMap<EControllerConstants, Object> properties = new EnumMap<>(EControllerConstants.class);

    @FXML
    Label description;
    @FXML
    PropertySheet propertySheet;

    @FXML
    public void initialize() {
        description.setText((String) properties.get(EControllerConstants.DESCRIPTION));
        propertySheet.getItems().addAll((PropertySheet.Item) properties.get(EControllerConstants.PROPERTIES));
    }

    @Override
    public String getWindowsName() {
        return null;
    }

    @Override
    public void setProperty(EControllerConstants key, Object value) {
        properties.put(key, value);
    }
}
