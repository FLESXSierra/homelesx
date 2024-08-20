package com.client.lesx.lesxclient.scenes.views;

import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import com.client.lesx.lesxclient.scenes.views.objects.SmokeJournal;
import com.client.lesx.lesxclient.scenes.views.objects.StickyNotes;
import com.client.lesx.lesxclient.tasks.fitness.DeleteFitnessTask;
import com.client.lesx.lesxclient.tasks.fitness.GetFitnessListTask;
import com.client.lesx.lesxclient.tasks.fitness.SaveFitnessTask;
import com.client.lesx.lesxclient.tasks.fitness.UpdateFitnessTask;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DataViews {

    private static final Integer ALL_ITEMS = -1;

    private static List<Fitness> fitnessList;
    private static List<SmokeJournal> smokeJournalList;
    private static List<StickyNotes> stickyNotesList;
    private static DataViews instance;

    private DoubleProperty doubleProperty = new SimpleDoubleProperty();
    private StringProperty stringProperty = new SimpleStringProperty();

    public DataViews() {
        instance = this;
    }

    public static DataViews getInstance() {
        return instance;
    }

    public static List<Fitness> getFitnessList() {
        return getFitnessList(null);
    }

    public static List<Fitness> getFitnessList(Consumer<List<Fitness>> afterRun) {
        if (fitnessList == null) {
            forceGetFitnessList(afterRun);
        }
        return fitnessList;
    }

    public static void forceGetFitnessList(Consumer<List<Fitness>> afterRun) {
        instance.doublePropertyProperty().unbind();
        instance.stringPropertyProperty().unbind();
        Consumer<List<Fitness>> onSuccess = (list) -> {
            fitnessList = list != null ? new ArrayList<>(list) : new ArrayList<>(0);
            if (afterRun != null) {
                afterRun.accept(new ArrayList<>(fitnessList));
            }
        };
        GetFitnessListTask task = new GetFitnessListTask(onSuccess);
        instance.doublePropertyProperty().bind(task.progressProperty());
        instance.stringPropertyProperty().bind(task.messageProperty());
        task.run();
    }

    public static void saveFitnessValue(Fitness fitness, Consumer<Fitness> afterSuccess) {
        instance.doublePropertyProperty().unbind();
        instance.stringPropertyProperty().unbind();
        Consumer<Fitness> onSuccess = (saved) -> {
            fitnessList.add(saved);
            afterSuccess.accept(saved);
        };
        SaveFitnessTask task = new SaveFitnessTask(onSuccess, fitness);
        instance.doublePropertyProperty().bind(task.progressProperty());
        instance.stringPropertyProperty().bind(task.messageProperty());
        task.run();
    }

    public static Fitness updateFitnessValue(Fitness fitness, Consumer<Fitness> onSave) {
        instance.doublePropertyProperty().unbind();
        instance.stringPropertyProperty().unbind();
        Consumer<Fitness> onSuccess = (updated) -> {
            fitnessList.removeIf(current -> current.getId().equals(updated.getId()));
            fitnessList.add(updated);
            onSave.accept(updated);
        };
        UpdateFitnessTask task = new UpdateFitnessTask(onSuccess, fitness);
        instance.doublePropertyProperty().bind(task.progressProperty());
        instance.stringPropertyProperty().bind(task.messageProperty());
        task.run();
        return fitness;
    }

    public static void deleteFitnessByIds(List<Integer> ids, Consumer<Boolean> onSave) {
        instance.doublePropertyProperty().unbind();
        instance.stringPropertyProperty().unbind();
        Consumer<Boolean> onSuccess = (deleted) -> {
            if(deleted) {
                fitnessList.removeIf(current -> ids.contains(current.getId()));
            }
            onSave.accept(deleted);
        };
        DeleteFitnessTask task = new DeleteFitnessTask(ids, onSuccess);
        instance.doublePropertyProperty().bind(task.progressProperty());
        instance.stringPropertyProperty().bind(task.messageProperty());
        task.run();
    }

    public static ObservableList<Integer> getFitnessYearsAsList() {
        List<Integer> years = fitnessList.stream()
                .map(Fitness::getDate)
                .map(LocalDate::getYear)
                .distinct()
                .collect(Collectors.toList());
        return FXCollections.observableList(years);
    }

    public static ObservableList<Integer> getFitnessMonthOfYear(Integer year) {
        List<Integer> months = fitnessList.stream()
                .map(Fitness::getDate)
                .filter(date -> ALL_ITEMS.equals(year) || date.getYear() == year)
                .map(LocalDate::getMonthValue)
                .distinct()
                .collect(Collectors.toList());
        return FXCollections.observableList(months);
    }

    public DoubleProperty doublePropertyProperty() {
        return doubleProperty;
    }

    public StringProperty stringPropertyProperty() {
        return stringProperty;
    }
}
