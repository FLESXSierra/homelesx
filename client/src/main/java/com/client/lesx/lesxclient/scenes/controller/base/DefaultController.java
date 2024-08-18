package com.client.lesx.lesxclient.scenes.controller.base;

import com.client.lesx.lesxclient.constants.EControllerConstants;
import javafx.beans.property.BooleanProperty;

public interface DefaultController {

    String getWindowsName();

    void afterInitialize();

    default void setProperty(EControllerConstants key, Object value) {
        //Not All Scenes has description or other properties, maybe implement as a hint ?
    }

    BooleanProperty getCloseProperty();
}
