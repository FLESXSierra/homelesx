package com.client.lesx.lesxclient.scenes.controller.graph.delegates;

import com.client.lesx.lesxclient.constants.EModelItems;
import com.client.lesx.lesxclient.scenes.controller.graph.delegates.base.GraphDelegate;
import com.client.lesx.lesxclient.scenes.controller.graph.delegates.converter.StringConverterFromDate;
import com.client.lesx.lesxclient.scenes.controller.graph.delegates.converter.StringConverterFromInteger;
import com.client.lesx.lesxclient.scenes.utils.NamesMapUtils;
import com.client.lesx.lesxclient.scenes.views.DataViews;
import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.client.lesx.lesxclient.constants.NameIdConstants.*;

@AllArgsConstructor
public class FitnessDelegates implements GraphDelegate {

    private static final Integer ALL_ITEM = -1;

    private ComboBox<Integer> year;
    private ComboBox<Integer> month;
    private LineChart<String, Number> lineChart;

    @Override
    public void buildFilters() {
        buildComboBoxItemsAndConverter();
        buildOnActionFromComboBox();
        buildLineChartAxis();
    }

    private void buildLineChartAxis() {
        Axis<String> xAxis = lineChart.getXAxis();
        xAxis.setLabel(NamesMapUtils.getStringValueFromMap(GRAPH_LABEL_DATE));
        Axis<Number> yAxis = lineChart.getYAxis();
        yAxis.setLabel(NamesMapUtils.getStringValueFromMap(FITNESS_WEIGHT));
    }

    private void buildOnActionFromComboBox() {
        year.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> yearChangedTo(newV));
        month.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> yearChangedTo(year.getSelectionModel().getSelectedItem()));
    }

    private void yearChangedTo(Integer newV) {
        if (newV != null) {
            if (ALL_ITEM == newV) {
                month.setDisable(true);
                month.getSelectionModel().clearAndSelect(0);
                lineChart.getData().clear();
                lineChart.getData().add(convertFitnessToSeries(DataViews.getFitnessList()));
            } else {
                month.setDisable(false);
                Integer selectedItem = month.getSelectionModel().getSelectedItem();
                Stream<Fitness> fitnessYear = DataViews.getFitnessList().stream()
                        .filter(item -> item.getDate().getYear() == newV);
                if (!ALL_ITEM.equals(selectedItem)) {
                    fitnessYear = fitnessYear.filter(item -> item.getDate().getMonth().getValue() == selectedItem);
                }
                lineChart.getData().clear();
                lineChart.getData().add(convertFitnessToSeries(fitnessYear.collect(Collectors.toList())));
            }
        }
    }

    private XYChart.Series<String, Number> convertFitnessToSeries(List<Fitness> fitnessList) {
        if (fitnessList != null) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(EModelItems.FITNESS.toString());
            series.getData().addAll(fitnessList.stream()
                            .sorted(Comparator.comparing(Fitness::getDate))
                    .map(item -> new XYChart.Data<>(item.getDate().toString(), (Number) item.getWeight()))
                    .collect(Collectors.toList()));
            return series;
        }
        return new XYChart.Series<>();
    }

    private void buildComboBoxItemsAndConverter() {
        ObservableList<Integer> fitnessYearsAsList = DataViews.getFitnessYearsAsList();
        fitnessYearsAsList.add(0, ALL_ITEM);
        year.setItems(fitnessYearsAsList);
        year.getSelectionModel().clearAndSelect(0);
        year.setConverter(new StringConverterFromInteger());
        ObservableList<Integer> fitnessMonthsAsList = DataViews.getFitnessMonthOfYear(ALL_ITEM);
        fitnessMonthsAsList.add(0, ALL_ITEM);
        month.setItems(fitnessMonthsAsList);
        month.getSelectionModel().clearAndSelect(0);
        month.setConverter(new StringConverterFromDate());

    }

    @Override
    public void buildDataIntoGraph() {
        yearChangedTo(ALL_ITEM);
    }
}
