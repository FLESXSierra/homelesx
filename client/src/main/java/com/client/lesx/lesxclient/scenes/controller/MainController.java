package com.client.lesx.lesxclient.scenes.controller;

import com.client.lesx.lesxclient.scenes.utils.SceneUtils;
import com.client.lesx.lesxclient.tasks.core.HealthCheckServerTask;
import javafx.fxml.FXML;
import lombok.extern.log4j.Log4j2;
import org.controlsfx.control.StatusBar;

import java.io.IOException;

@Log4j2
public class MainController {
    @FXML
    private StatusBar statusBar;

    @FXML
    public void initialize(){

        HealthCheckServerTask task = new HealthCheckServerTask(check -> afterCompleteChangeScene());
        statusBar.progressProperty().bind(task.progressProperty());
        statusBar.textProperty().bind(task.messageProperty());
        task.run();
    }

    private void afterCompleteChangeScene(){
        try {
            SceneUtils.setMainMenuRootOnScene();
        } catch (IOException e) {
            log.error("Error while setting scene from main manu", e);
            statusBar.progressProperty().unbind();
            statusBar.textProperty().unbind();
            statusBar.setProgress(-1);
            statusBar.setText("Couldn't load main menu");
        }
    }

}