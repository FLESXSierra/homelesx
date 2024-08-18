package com.client.lesx.lesxclient.scenes.views.model.builder.factories;

import com.client.lesx.lesxclient.constants.EModelItems;
import com.client.lesx.lesxclient.scenes.utils.NamesMapUtils;
import com.client.lesx.lesxclient.scenes.views.model.FitnessModel;
import com.client.lesx.lesxclient.scenes.views.model.builder.factories.base.ItemFactory;
import com.client.lesx.lesxclient.scenes.views.model.item.FitnessItem;
import com.client.lesx.lesxclient.scenes.views.model.item.base.*;
import org.controlsfx.control.PropertySheet;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.client.lesx.lesxclient.constants.NameIdConstants.*;
import static com.client.lesx.lesxclient.constants.NameIdConstants.FITNESS_WORKOUT_DATE;

public class FitnessItemFactory implements ItemFactory {

    private static final String ID_NAME = NamesMapUtils.getStringValueFromMap(ID);
    private static final String WEIGHT_NAME = NamesMapUtils.getStringValueFromMap(FITNESS_WEIGHT);
    private static final String WORKOUT_NAME = NamesMapUtils.getStringValueFromMap(FITNESS_IS_WORKING_DAY);
    private static final String DATE_NAME = NamesMapUtils.getStringValueFromMap(FITNESS_WORKOUT_DATE);

    private final FitnessItem fitnessItem = new FitnessItem();
    private List<PropertySheet.Item> items;

    @Override
    public void copyFromModel(Object copy) {
        FitnessModel model = (FitnessModel) copy;
        fitnessItem.getId().setValue(model.getId());
        fitnessItem.getWeight().setValue(model.getWeight());
        fitnessItem.getWorkoutDay().setValue(model.isWorkoutDay());
        fitnessItem.getDate().setValue(model.getDate());
    }

    @Override
    public void setPropertySheetItems(List<PropertySheet.Item> items) {
        this.items = items;
    }

    @Override
    public void buildParameters() {
        initializeItems();
        updateValueFromItems();
    }

    @Override
    public ItemList getItem() {
        return fitnessItem;
    }

    private void updateValueFromItems() {
        if(items != null) {
            Map<String, PropertySheet.Item> mapNames = new HashMap<>(4);
            mapNames.put(ID_NAME, fitnessItem.getId());
            mapNames.put(WEIGHT_NAME, fitnessItem.getWeight());
            mapNames.put(WORKOUT_NAME, fitnessItem.getWorkoutDay());
            mapNames.put(DATE_NAME, fitnessItem.getDate());
            for(PropertySheet.Item item : items) {
                PropertySheet.Item current = mapNames.get(item.getName());
                if(current != null) {
                    current.setValue(item.getValue());
                }
            }
        }
    }

    private void initializeItems() {
        fitnessItem.setId(new IntegerItem());
        fitnessItem.getId().setCategory(EModelItems.FITNESS.toString());
        fitnessItem.getId().setName(ID_NAME);
        fitnessItem.getId().setEditable(false);
        fitnessItem.getId().setDescription(NamesMapUtils.getStringValueFromMap(ID_DESCRIPTION));

        fitnessItem.setWeight(new DoubleItem());
        fitnessItem.getWeight().setCategory(EModelItems.FITNESS.toString());
        fitnessItem.getWeight().setName(WEIGHT_NAME);
        fitnessItem.getWeight().setDescription(NamesMapUtils.getStringValueFromMap(FITNESS_WEIGHT_DESC));

        fitnessItem.setWorkoutDay(new BooleanItem());
        fitnessItem.getWorkoutDay().setCategory(EModelItems.FITNESS.toString());
        fitnessItem.getWorkoutDay().setName(WORKOUT_NAME);
        fitnessItem.getWorkoutDay().setDescription(NamesMapUtils.getStringValueFromMap(FITNESS_IS_WORKING_DAY_DESC));

        fitnessItem.setDate(new ObjectItem<>(LocalDate.class));
        fitnessItem.getDate().setCategory(EModelItems.FITNESS.toString());
        fitnessItem.getDate().setName(DATE_NAME);
        fitnessItem.getDate().setDescription(NamesMapUtils.getStringValueFromMap(FITNESS_WORKOUT_DATE_DESC));
    }
}
