package com.client.lesx.lesxclient;

import com.client.lesx.lesxclient.constants.NameIdConstants;
import com.client.lesx.lesxclient.scenes.utils.NamesMapUtils;
import com.client.lesx.lesxclient.scenes.utils.SceneUtils;
import com.client.lesx.lesxclient.scenes.views.DataViews;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.client.lesx.lesxclient.constants.EControllerSceneFX.INITIAL_VIEW_FXML;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DataViews instance = new DataViews();
        new NamesMapUtils();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(INITIAL_VIEW_FXML.getFileName()));
        Scene scene = new Scene(fxmlLoader.load());
        SceneUtils.getInstance().setScene(scene);
        stage.setScene(scene);
        stage.show();
        stage.setTitle(NamesMapUtils.getStringValueFromMap(NameIdConstants.MAIN_WINDOWS_NAME));
    }

    public static void main(String[] args) {
        new SceneUtils();
        launch();
    }
}