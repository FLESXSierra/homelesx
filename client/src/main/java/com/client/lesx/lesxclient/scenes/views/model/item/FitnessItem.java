package com.client.lesx.lesxclient.scenes.views.model.item;

import com.client.lesx.lesxclient.scenes.views.model.FitnessModel;
import com.client.lesx.lesxclient.scenes.views.model.item.base.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.controlsfx.control.PropertySheet;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class FitnessItem implements ItemList {

    private IntegerItem id;
    private DoubleItem weight;
    private BooleanItem workoutDay;
    private ObjectItem<LocalDate> date;

    @Override
    public List<PropertySheet.Item> getAllProperties() {
        return List.of(id, weight, workoutDay, date);
    }
}
