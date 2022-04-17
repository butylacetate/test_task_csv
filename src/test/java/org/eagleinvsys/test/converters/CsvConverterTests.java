package org.eagleinvsys.test.converters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.eagleinvsys.test.converters.impl.ConvertibleCollectionImpl;
import org.eagleinvsys.test.converters.impl.ConvertibleMessageImpl;
import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CsvConverterTests {

    private CsvConverter csvConverter;

    @Before
    public void setUp() {
        csvConverter = new CsvConverter();
    }

    @Test
    public void convertTest() throws IOException {
        ConvertibleCollection collection = new ConvertibleCollectionImpl(
                List.of("ID", "NAME", "VALUE", "ENABLED"),
                List.of(
                        new ConvertibleMessageImpl(List.of("1", "name1", "1", "true")),
                        new ConvertibleMessageImpl(List.of("2", "name2", "2", "false")),
                        new ConvertibleMessageImpl(List.of("3", "name3", "3", "true"))
                )
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            csvConverter.convert(collection, byteArrayOutputStream);
            Assert.assertEquals(
                    "ID,NAME,VALUE,ENABLED\r\n" +
                            "1,name1,1,true\r\n" +
                            "2,name2,2,false\r\n" +
                            "3,name3,3,true\r\n",
                    byteArrayOutputStream.toString(Charset.defaultCharset()));
        }
    }

    @Test
    public void convertTest_oneColumn() throws IOException {
        ConvertibleCollection collection = new ConvertibleCollectionImpl(
                List.of("ID"),
                List.of(
                        new ConvertibleMessageImpl(List.of("1")),
                        new ConvertibleMessageImpl(List.of("2")),
                        new ConvertibleMessageImpl(List.of("3"))
                )
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            csvConverter.convert(collection, byteArrayOutputStream);
            Assert.assertEquals(
                    "ID\r\n" +
                            "1\r\n" +
                            "2\r\n" +
                            "3\r\n",
                    byteArrayOutputStream.toString(Charset.defaultCharset()));
        }
    }

    @Test
    public void convertTest_empty() throws IOException {
        ConvertibleCollection collection = new ConvertibleCollectionImpl(
                List.of(),
                List.of()
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            csvConverter.convert(collection, byteArrayOutputStream);
            Assert.assertEquals(
                    "",
                    byteArrayOutputStream.toString(Charset.defaultCharset()));
        }
    }

    @Test
    public void convertTest_emptyHeaders() throws IOException {
        ConvertibleCollection collection = new ConvertibleCollectionImpl(
                List.of(),
                List.of(
                        new ConvertibleMessageImpl(List.of("1", "name1", "1", "true")),
                        new ConvertibleMessageImpl(List.of("2", "name2", "2", "false")),
                        new ConvertibleMessageImpl(List.of("3", "name3", "3", "true"))
                )
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            csvConverter.convert(collection, byteArrayOutputStream);
            Assert.assertEquals(
                    "",
                    byteArrayOutputStream.toString(Charset.defaultCharset()));
        }
    }

    @Test
    public void convertTest_nullData() throws IOException {
        ConvertibleCollection collection = new ConvertibleCollectionImpl(
                null,
                null
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            csvConverter.convert(collection, byteArrayOutputStream);
            Assert.assertEquals(
                    "",
                    byteArrayOutputStream.toString(Charset.defaultCharset()));
        }
    }

    @Test
    public void convertTest_nullHeaders() throws IOException {
        ConvertibleCollection collection = new ConvertibleCollectionImpl(
                null,
                List.of(
                        new ConvertibleMessageImpl(List.of("1", "name1", "1", "true")),
                        new ConvertibleMessageImpl(List.of("2", "name2", "2", "false")),
                        new ConvertibleMessageImpl(List.of("3", "name3", "3", "true"))
                )
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            csvConverter.convert(collection, byteArrayOutputStream);
            Assert.assertEquals(
                    "",
                    byteArrayOutputStream.toString(Charset.defaultCharset()));
        }
    }

    @Test
    public void convertTest_nullRows() throws IOException {
        ConvertibleCollection collection = new ConvertibleCollectionImpl(
                List.of("ID"),
                null
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            csvConverter.convert(collection, byteArrayOutputStream);
            Assert.assertEquals(
                    "",
                    byteArrayOutputStream.toString(Charset.defaultCharset()));
        }
    }
}