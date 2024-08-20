package com.client.lesx.lesxclient.scenes.controller.graph;

import com.client.lesx.lesxclient.constants.EControllerConstants;
import com.client.lesx.lesxclient.constants.EModelItems;
import com.client.lesx.lesxclient.scenes.controller.base.DefaultController;
import com.client.lesx.lesxclient.scenes.controller.graph.delegates.FitnessDelegates;
import com.client.lesx.lesxclient.scenes.controller.graph.delegates.base.GraphDelegate;
import com.client.lesx.lesxclient.scenes.utils.NamesMapUtils;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

import static com.client.lesx.lesxclient.constants.NameIdConstants.DEFAULT_GRAPH_HEADER;
import static com.client.lesx.lesxclient.constants.NameIdConstants.DEFAULT_GRAPH_TITLE;

@Log4j2
public class GraphItemController implements DefaultController {

    @FXML
    private LineChart<String, Number> graph;
    @FXML
    private ComboBox<Integer> filter1;
    @FXML
    private ComboBox<Integer> filter2;

    private final Map<EControllerConstants, Object> properties = new EnumMap<>(EControllerConstants.class);

    @Override
    public String getWindowsName() {
        return NamesMapUtils.getStringValueFromMap(DEFAULT_GRAPH_TITLE);
    }

    @Override
    public void afterInitialize() {
        EModelItems fromItem = (EModelItems) properties.get(EControllerConstants.FROM_ITEM);
        graph.setTitle(String.format(NamesMapUtils.getStringValueFromMap(DEFAULT_GRAPH_HEADER), fromItem));
        GraphDelegate delegate = null;
        switch (fromItem) {
            case FITNESS:
                delegate = new FitnessDelegates(filter1, filter2, graph);
                break;
            default:
                log.error("Unsupported Model Item to Graph: {}", fromItem);
                break;
        }
        if (delegate != null) {
            delegate.buildFilters();
            delegate.buildDataIntoGraph();
        }
    }

    @Override
    public void setProperty(EControllerConstants key, Object value) {
        properties.put(key, value);
    }

    @Override
    public BooleanProperty getCloseProperty() {
        // No dirty property required.
        return null;
    }

    @Override
    public void onCloseEvent(WindowEvent onCloseEvent) {
        // Do nothing.
    }
}
