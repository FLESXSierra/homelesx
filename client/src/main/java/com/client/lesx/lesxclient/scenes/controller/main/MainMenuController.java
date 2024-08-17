package com.client.lesx.lesxclient.scenes.controller.main;

import com.client.lesx.lesxclient.scenes.controller.base.DefaultController;
import com.client.lesx.lesxclient.scenes.views.DataViews;
import com.client.lesx.lesxclient.scenes.views.model.FitnessModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.stream.Collectors;

import static com.client.lesx.lesxclient.constants.ColumnNamesConstants.*;

public class MainMenuController implements DefaultController {

    @FXML
    public TableView<FitnessModel> table;
    @FXML
    public ProgressIndicator progress;
    @FXML
    public Label action;
    @FXML
    public MenuBar menuBar;
    @FXML
    public MenuItem add;
    @FXML
    public MenuItem edit;
    @FXML
    public MenuItem delete;

    private static ObservableList<FitnessModel> fitnessList = FXCollections.observableArrayList();
    private BooleanProperty isItemInTableSelected = new SimpleBooleanProperty(Boolean.FALSE, "isItemInTableSelected");

    @FXML
    public void initialize() {
        initializeFitnessList();
        initializeTable();
        bindDataViewProgress();
        bindMenuBarFromTableSelector();
    }

    private void bindMenuBarFromTableSelector() {
        table.selectionModelProperty().addListener(obs -> observableForSelectedItem());
        edit.disableProperty().bind(Bindings.not(isItemInTableSelected));
        delete.disableProperty().bind(isItemInTableSelected);
    }

    private void observableForSelectedItem() {
        isItemInTableSelected.set(table.getSelectionModel()
                .getSelectedItem() != null);
    }

    private void bindDataViewProgress() {
        action.textProperty().bind(DataViews.getInstance().stringPropertyProperty());
        progress.progressProperty().bind(DataViews.getInstance().doublePropertyProperty());
    }

    private void initializeTable() {
        table.setItems(fitnessList);
        createColumns();
    }

    private void createColumns() {
        TableColumn<FitnessModel, Number> weight = new TableColumn<>(WEIGHT);
        weight.setCellValueFactory(param -> param.getValue().weightProperty());
        TableColumn<FitnessModel, Boolean> workoutDay = new TableColumn<>(WORKDAY);
        workoutDay.setCellValueFactory(param -> param.getValue().workoutDayProperty());
        TableColumn<FitnessModel, String> date = new TableColumn<>(DATE_COLUMN);
        date.setCellValueFactory(param -> param.getValue().dateProperty().asString());
        table.getColumns().addAll(weight, workoutDay, date);
    }

    private void initializeFitnessList() {
        DataViews.getFitnessList(list -> {
            fitnessList.addAll(list.stream()
                    .map(FitnessModel::new)
                    .collect(Collectors.toList()));
        });
    }

    @Override
    public String getWindowsName() {
        return null;
    }
}
