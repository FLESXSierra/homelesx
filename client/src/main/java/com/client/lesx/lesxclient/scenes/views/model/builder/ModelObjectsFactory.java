package com.client.lesx.lesxclient.scenes.views.model.builder;

import com.client.lesx.lesxclient.scenes.views.model.FitnessModel;
import com.client.lesx.lesxclient.scenes.views.model.builder.factories.FitnessDaoFactory;
import com.client.lesx.lesxclient.scenes.views.model.builder.factories.FitnessItemFactory;
import com.client.lesx.lesxclient.scenes.views.model.builder.factories.base.ItemFactory;
import com.client.lesx.lesxclient.scenes.views.model.item.FitnessItem;
import com.client.lesx.lesxclient.scenes.views.model.item.base.ItemList;
import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import org.controlsfx.control.PropertySheet;

import java.util.List;

public class ModelObjectsFactory {

    public static Fitness convertFitnessFromItemsToDao(List<PropertySheet.Item> items) {
        FitnessItem fitnessItem = buildFitnessItemFromPropertyItems(items);
        FitnessDaoFactory factory = new FitnessDaoFactory();
        return factory.convertItemToDao(fitnessItem);
    }

    public static FitnessItem buildFitnessItemFromPropertyItems(List<PropertySheet.Item> items) {
        return (FitnessItem) buildItemListFromPropertyItems(items, new FitnessItemFactory());
    }

    public static FitnessItem buildFitnessItemFromModel(FitnessModel model) {
        return (FitnessItem) buildItemListFromModel(model, new FitnessItemFactory());
    }

    private static ItemList buildItemListFromPropertyItems(List<PropertySheet.Item> items, ItemFactory factory) {
        factory.setPropertySheetItems(items);
        factory.buildParameters();
        return factory.getItem();
    }

    private static ItemList buildItemListFromModel(Object model, ItemFactory factory) {
        factory.buildParameters();
        factory.copyFromModel(model);
        return factory.getItem();
    }
}
