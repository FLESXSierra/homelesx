package com.client.lesx.lesxclient.scenes.controller.base;

import com.client.lesx.lesxclient.constants.EControllerConstants;

public interface DefaultController {

    String getWindowsName();

    default void setProperty(EControllerConstants key, Object value) {
        //Not All Scenes has description or other properties, maybe implement as a hint ?
    }
}
