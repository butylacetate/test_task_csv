package org.eagleinvsys.test.converters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableSortedMap;

public class StandardCsvConverterTests {

    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String VALUE = "VALUE";
    private static final String ENABLED = "ENABLED";

    private StandardCsvConverter standardCsvConverter;

    @Before
    public void setUp() {
        standardCsvConverter = new StandardCsvConverter(new CsvConverter());
    }

    @Test
    public void convertTest() throws IOException {
        List<Map<String, String>> collectionToConvert = List.of(
                ImmutableSortedMap.of(ID, "1", NAME, "name1", VALUE, "1", ENABLED, "true"),
                ImmutableSortedMap.of(ID, "2", NAME, "name2", VALUE, "2", ENABLED, "false"),
                ImmutableSortedMap.of(ID, "3", NAME, "name3", VALUE, "3", ENABLED, "true")
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            standardCsvConverter.convert(collectionToConvert, byteArrayOutputStream);
            Assert.assertEquals("ENABLED,ID,NAME,VALUE\r\n" +
                    "true,1,name1,1\r\n" +
                    "false,2,name2,2\r\n" +
                    "true,3,name3,3\r\n",
                    byteArrayOutputStream.toString(Charset.defaultCharset())
            );
        }
    }

    @Test
    public void convertTest_oneColumn() throws IOException {
        List<Map<String, String>> collectionToConvert = List.of(
                ImmutableSortedMap.of(ID, "1"),
                ImmutableSortedMap.of(ID, "2"),
                ImmutableSortedMap.of(ID, "3")
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            standardCsvConverter.convert(collectionToConvert, byteArrayOutputStream);
            Assert.assertEquals("ID\r\n" +
                            "1\r\n" +
                            "2\r\n" +
                            "3\r\n",
                    byteArrayOutputStream.toString(Charset.defaultCharset())
            );
        }
    }

    @Test
    public void convertTest_empty() throws IOException {
        List<Map<String, String>> collectionToConvert = List.of(
                ImmutableSortedMap.of()
        );

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            standardCsvConverter.convert(collectionToConvert, byteArrayOutputStream);
            Assert.assertEquals("",
                    byteArrayOutputStream.toString(Charset.defaultCharset())
            );
        }
    }

    @Test
    public void convertTest_nullData() throws IOException {
        List<Map<String, String>> collectionToConvert = List.of();

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            standardCsvConverter.convert(collectionToConvert, byteArrayOutputStream);
            Assert.assertEquals("",
                    byteArrayOutputStream.toString(Charset.defaultCharset())
            );
        }
    }

    @Test
    public void convertTest_nullCollection() throws IOException {

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            standardCsvConverter.convert(null, byteArrayOutputStream);
            Assert.assertEquals("",
                    byteArrayOutputStream.toString(Charset.defaultCharset())
            );
        }
    }

}