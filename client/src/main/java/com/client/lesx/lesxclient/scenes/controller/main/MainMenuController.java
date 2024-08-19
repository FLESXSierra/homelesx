package com.client.lesx.lesxclient.scenes.controller.main;

import com.client.lesx.lesxclient.constants.EActions;
import com.client.lesx.lesxclient.constants.EModelItems;
import com.client.lesx.lesxclient.scenes.controller.base.DefaultController;
import com.client.lesx.lesxclient.scenes.utils.NamesMapUtils;
import com.client.lesx.lesxclient.scenes.utils.SceneUtils;
import com.client.lesx.lesxclient.scenes.views.DataViews;
import com.client.lesx.lesxclient.scenes.views.model.FitnessModel;
import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import org.controlsfx.control.PropertySheet;

import java.util.List;
import java.util.stream.Collectors;

import static com.client.lesx.lesxclient.constants.ColumnNamesConstants.*;
import static com.client.lesx.lesxclient.constants.NameIdConstants.*;
import static com.client.lesx.lesxclient.scenes.views.model.builder.ModelObjectsFactory.convertFitnessFromItemsToDao;

@Log4j2
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
    private BooleanProperty isBulkItemInTableSelected = new SimpleBooleanProperty(Boolean.FALSE, "isItemInTableSelected");

    @FXML
    public void initialize() {
        initializeFitnessList();
        initializeTable();
        bindDataViewProgress();
        bindMenuBarFromTableSelector();
        addListenerToMenuItems();
    }

    private void addListenerToMenuItems() {
        edit.setOnAction(action -> updateFitnessObject());
        add.setOnAction(action -> addNewFitnessObject());
        delete.setOnAction(action -> deleteFitnessObject());
    }

    private void addNewFitnessObject() {
        SceneUtils.showPropertyDialog(new FitnessModel()
                        .getAsFitnessItem()
                        .getAllProperties(),
                EActions.CREATE,
                EModelItems.FITNESS,
                items -> convertAndSave(items));
    }

    private void updateFitnessObject() {
        SceneUtils.showPropertyDialog(table.getSelectionModel()
                        .getSelectedItem()
                        .getAsFitnessItem()
                        .getAllProperties(), EActions.UPDATE, EModelItems.FITNESS,
                items -> convertAndUpdate(items));
    }

    private void deleteFitnessObject() {
        List<Integer> ids = table.getSelectionModel()
                .getSelectedItems()
                .stream()
                .map(FitnessModel::getId)
                .collect(Collectors.toList());
        ButtonType ok = new ButtonType(NamesMapUtils.getStringValueFromMap(OK));
        ButtonType cancel = new ButtonType(NamesMapUtils.getStringValueFromMap(CANCEL));
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> alert.hide());
        alert.setTitle(NamesMapUtils.getStringValueFromMap(DEFAULT_PROPERTY_ALERT_DELETE_TITLE));
        alert.setHeaderText(String.format(NamesMapUtils.getStringValueFromMap(DEFAULT_PROPERTY_ALERT_DELETE_DESCRIPTION), EModelItems.FITNESS, ids));
        alert.getButtonTypes()
                .clear();
        alert.getButtonTypes()
                .addAll(ok, cancel);
        ButtonType result = alert.showAndWait()
                .orElse(null);
        if (ok.equals(result)) {
            DataViews.deleteFitnessByIds(ids, succeeded -> removeFromList(succeeded, ids));
        }
    }

    private void removeFromList(Boolean succeeded, List<Integer> ids) {
        if(succeeded) {
            fitnessList.removeIf(item -> ids.contains(item.getId()));
        }
    }

    private void convertAndSave(List<PropertySheet.Item> items) {
        Fitness fitnessItem = convertFitnessFromItemsToDao(items);
        DataViews.saveFitnessValue(fitnessItem, fitness -> fitnessList.add(new FitnessModel(fitness)));
    }

    private void convertAndUpdate(List<PropertySheet.Item> items) {
        Fitness fitnessItem = convertFitnessFromItemsToDao(items);
        DataViews.updateFitnessValue(fitnessItem, fitness -> updateFitnessInTable(fitness));
    }

    private void updateFitnessInTable(Fitness fitness) {
        FitnessModel fitnessModel = fitnessList.stream()
                .filter(item -> item.getId().equals(fitness.getId()))
                .findAny()
                .orElse(null);
        if(fitnessModel != null) {
            fitnessModel.setWeight(fitness.getWeight());
            fitnessModel.setDate(fitness.getDate());
            fitnessModel.setWorkoutDay(fitness.isWorkoutDay());
        } else {
            log.error(String.format("Not possible to update object [%s]", fitness));
        }
    }

    private void bindMenuBarFromTableSelector() {
        table.getSelectionModel().selectedItemProperty().addListener(obs -> observableForSelectedItem());
        edit.disableProperty().bind(Bindings.not(isItemInTableSelected).or(isBulkItemInTableSelected));
        delete.disableProperty().bind(Bindings.not(isItemInTableSelected));
        table.getSelectionModel().clearSelection();
    }

    private void observableForSelectedItem() {
        isItemInTableSelected.set(table.getSelectionModel()
                .getSelectedItem() != null);
        isBulkItemInTableSelected.set(table.getSelectionModel().getSelectedItems().size() > 1);
    }

    private void bindDataViewProgress() {
        action.textProperty().bind(DataViews.getInstance().stringPropertyProperty());
        progress.progressProperty().bind(DataViews.getInstance().doublePropertyProperty());
    }

    private void initializeTable() {
        table.setItems(fitnessList);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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

    @Override
    public void afterInitialize() {
        // Do nothing
    }

    @Override
    public BooleanProperty getCloseProperty() {
        return new SimpleBooleanProperty();
    }

    @Override
    public void onCloseEvent(WindowEvent onCloseEvent) {
        // Do nothing Maybe add Alert?
    }
}
