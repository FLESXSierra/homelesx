package com.client.lesx.lesxclient.scenes.utils;

import com.client.lesx.lesxclient.MainApplication;
import com.client.lesx.lesxclient.constants.EActions;
import com.client.lesx.lesxclient.constants.EControllerConstants;
import com.client.lesx.lesxclient.constants.EModelItems;
import com.client.lesx.lesxclient.scenes.controller.base.DefaultController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import org.controlsfx.control.PropertySheet;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import static com.client.lesx.lesxclient.constants.EControllerSceneFX.*;

@Log4j2
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

    public static void showPropertyDialog(List<PropertySheet.Item> items, EActions action, EModelItems fromItem, Consumer<List<PropertySheet.Item>> onSave) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(PROPERTY_SHEETS.getFileName()));
            Pane root = fxmlLoader.load();
            DefaultController controller = fxmlLoader.getController();
            controller.setProperty(EControllerConstants.ACTION, action);
            controller.setProperty(EControllerConstants.PROPERTIES, items);
            controller.setProperty(EControllerConstants.FROM_ITEM, fromItem);
            controller.setProperty(EControllerConstants.ON_SAVE_ACTION, onSave);
            controller.afterInitialize();
            Stage stage = new Stage();
            stage.setTitle(controller.getWindowsName());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.sizeToScene();
            stage.show();
            stage.getScene()
                    .getWindow()
                    .addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, controller::onCloseEvent);
            controller.getCloseProperty().addListener((obs, oldV, newV) -> {
                if(newV){
                    stage.close();
                }
            });
        } catch (IOException e) {
            log.error("Error while loading property dialog window", e);
        }
    }

    public static void showGraph(EModelItems fromItem) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(GRAPH_VIEW.getFileName()));
            Pane root = fxmlLoader.load();
            Stage stage = new Stage();
            DefaultController controller = fxmlLoader.getController();
            controller.setProperty(EControllerConstants.FROM_ITEM, fromItem);
            controller.afterInitialize();
            stage.setTitle(controller.getWindowsName());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.sizeToScene();
            stage.show();

        } catch (IOException e) {
            log.error("Error while loading Graph window", e);
        }
    }
}
