package com.company.documentstats;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentTest {

    Document document;

    public DocumentTest() {
        document = new Document(getClass().getClassLoader().getResource("testdoc.txt").getPath());
    }

    @Test
    public void testCountLines_whenFileExists_shouldReturnCorrectNumber() throws IOException {// TODO handle exceptions
        assertEquals(document.getLineCount(), 6);
    }

}
