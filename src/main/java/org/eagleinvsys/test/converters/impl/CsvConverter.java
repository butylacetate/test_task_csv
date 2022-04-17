package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;

import java.io.OutputStream;
import java.io.PrintWriter;

public class CsvConverter implements Converter {

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        if (collectionToConvert.getHeaders() == null || collectionToConvert.getRecords() == null) {
            return;
        }

        final int rowSize = collectionToConvert.getHeaders().size();
        final String headerRow = String.join(",", collectionToConvert.getHeaders());
        if (!headerRow.isEmpty()) {
            try (PrintWriter printWriter = new PrintWriter(outputStream)) {
                printWriter.println(headerRow);
                collectionToConvert.getRecords().forEach(message -> {
                    for (int i = 0; i < rowSize - 1; i++) {
                        printWriter.print(message.getElement(String.valueOf(i)));
                        printWriter.print(',');
                    }
                    printWriter.print(message.getElement(String.valueOf(rowSize - 1)));
                    printWriter.println();
                });
            }
        }
    }

}