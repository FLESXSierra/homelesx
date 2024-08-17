package com.client.lesx.lesxclient.scenes.views;

import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import com.client.lesx.lesxclient.scenes.views.objects.SmokeJournal;
import com.client.lesx.lesxclient.scenes.views.objects.StickyNotes;
import com.client.lesx.lesxclient.tasks.fitness.GetFitnessListTask;
import com.client.lesx.lesxclient.tasks.fitness.SaveFitnessTask;
import com.client.lesx.lesxclient.tasks.fitness.UpdateFitnessTask;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DataViews {

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

    public static void saveFitnessValue(Fitness fitness, Consumer<List<Fitness>> afterSuccess) {
        instance.doublePropertyProperty().unbind();
        instance.stringPropertyProperty().unbind();
        Consumer<List<Fitness>> onSuccess = (saved) -> {
            fitnessList.addAll(saved);
            afterSuccess.accept(saved);
        };
        SaveFitnessTask task = new SaveFitnessTask(onSuccess, List.of(fitness));
        instance.doublePropertyProperty().bind(task.progressProperty());
        instance.stringPropertyProperty().bind(task.messageProperty());
        task.run();
    }

    public static Fitness updateFitnessValue(Fitness fitness) {
        instance.doublePropertyProperty().unbind();
        instance.stringPropertyProperty().unbind();
        UpdateFitnessTask task = new UpdateFitnessTask(null, fitness);
        instance.doublePropertyProperty().bind(task.progressProperty());
        instance.stringPropertyProperty().bind(task.messageProperty());
        task.run();
        return fitness;
    }

    public DoubleProperty doublePropertyProperty() {
        return doubleProperty;
    }

    public StringProperty stringPropertyProperty() {
        return stringProperty;
    }
}
