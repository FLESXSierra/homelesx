package com.client.lesx.lesxclient.scenes.utils;

import com.client.lesx.lesxclient.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import static com.client.lesx.lesxclient.constants.EControllerSceneFX.MAIN_MENU;

public class SceneUtils {

    private static Scene scene;
    private static SceneUtils instance;

    public SceneUtils() {
        instance = this;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static SceneUtils getInstance() {
        return instance;
    }

    public static void setMainMenuRootOnScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(MAIN_MENU.getFileName()));
        scene.setRoot(fxmlLoader.load());
        if (scene.getWindow() != null) {
            scene.getWindow().sizeToScene();
        }
    }
}
