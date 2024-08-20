package com.client.lesx.lesxclient.scenes.controller.graph.delegates.converter;

import com.client.lesx.lesxclient.scenes.utils.NamesMapUtils;
import javafx.util.StringConverter;

import static com.client.lesx.lesxclient.constants.NameIdConstants.ALL;

public class StringConverterFromInteger extends StringConverter<Integer> {

    private static final Integer ALL_ITEM = -1;

    @Override
    public java.lang.String toString(Integer item) {
        return item == ALL_ITEM ? NamesMapUtils.getStringValueFromMap(ALL) : "" + item;
    }

    @Override
    public Integer fromString(java.lang.String string) {
        return Integer.valueOf(string);
    }
}
