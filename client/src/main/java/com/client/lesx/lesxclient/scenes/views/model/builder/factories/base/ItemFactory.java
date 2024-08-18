package com.client.lesx.lesxclient.scenes.views.model.builder.factories.base;

import com.client.lesx.lesxclient.scenes.views.model.item.base.ItemList;
import org.controlsfx.control.PropertySheet;

import java.util.List;

public interface ItemFactory {

    void copyFromModel(Object copy);
    void setPropertySheetItems(List<PropertySheet.Item> items);
    void buildParameters();
    ItemList getItem();
}
