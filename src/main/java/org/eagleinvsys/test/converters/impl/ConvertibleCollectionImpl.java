package org.eagleinvsys.test.converters.impl;

import java.util.Collection;
import java.util.List;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

public class ConvertibleCollectionImpl implements ConvertibleCollection {

    private final Collection<String> headers;
    private final Collection<ConvertibleMessage> records;

    public ConvertibleCollectionImpl(Collection<String> headers, Collection<ConvertibleMessage> records) {
        this.headers = headers;
        this.records = records;
    }

    @Override
    public Collection<String> getHeaders() {
        return headers;
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        return records;
    }
}
