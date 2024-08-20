package com.client.lesx.lesxclient.scenes.controller.graph.delegates.converter;

import com.client.lesx.lesxclient.scenes.utils.NamesMapUtils;
import javafx.util.StringConverter;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.client.lesx.lesxclient.constants.NameIdConstants.ALL;

public class StringConverterFromDate extends StringConverter<Integer> {

    private static final Integer ALL_ITEM = -1;
    private static final String ALL_STRING = NamesMapUtils.getStringValueFromMap(ALL);

    @Override
    public java.lang.String toString(Integer item) {
        return item == ALL_ITEM ? ALL_STRING : Month.of(item).getDisplayName(TextStyle.FULL, Locale.US);
    }

    @Override
    public Integer fromString(java.lang.String string) {
        return ALL_STRING.equals(string) ? ALL_ITEM : Month.valueOf(string).getValue();
    }
}
