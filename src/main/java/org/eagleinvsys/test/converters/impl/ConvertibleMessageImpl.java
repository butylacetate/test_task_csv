package org.eagleinvsys.test.converters.impl;

import java.util.ArrayList;
import java.util.List;

import org.eagleinvsys.test.converters.ConvertibleMessage;

public class ConvertibleMessageImpl implements ConvertibleMessage {

    private final List<String> elements;

    public ConvertibleMessageImpl(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public String getElement(String elementId) {
        return elements.get(Integer.parseInt(elementId));
    }
}
