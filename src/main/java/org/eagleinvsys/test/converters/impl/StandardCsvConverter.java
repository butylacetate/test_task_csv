package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.StandardConverter;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StandardCsvConverter implements StandardConverter {

    private final CsvConverter csvConverter;

    public StandardCsvConverter(CsvConverter csvConverter) {
        this.csvConverter = csvConverter;
    }

    /**
     * Converts given {@link List<Map>} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format. All maps must have the same set of keys
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(List<Map<String, String>> collectionToConvert, OutputStream outputStream) {
        if (collectionToConvert == null) {
            return;
        }

        Optional<Map<String, String>> firstRowOptional = collectionToConvert.stream().findFirst();

        if (firstRowOptional.isPresent()) {
            final Set<String> headers = firstRowOptional.get().keySet();
            ConvertibleCollection collection =
                    new ConvertibleCollectionImpl(
                            headers,
                            collectionToConvert.stream().map(row ->
                                            new ConvertibleMessageImpl(headers.stream().map(row::get)
                                                    .collect(Collectors.toList())))
                                    .collect(Collectors.toList()));

            csvConverter.convert(collection, outputStream);
        }
    }

}