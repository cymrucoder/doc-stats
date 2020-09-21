package com.company.documentstats;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentTest {

    Document document;

    public DocumentTest() {
        document = new Document(getClass().getClassLoader().getResource("testdoc.txt").getPath());
    }

    @Test
    public void testCountLines_whenFileExists_shouldReturnCorrectNumber() throws IOException {
        assertEquals(document.getLineCount(), 6);
    }

    @Test
    public void testCountLines_whenFileDoesntExist_shouldThrowException() throws IOException {
        Document documentThatDoesntExist = new Document("filethatdoesntexist");
        assertThrows(FileNotFoundException.class, () -> {
            documentThatDoesntExist.getLineCount();
        }, "Exception not thrown when file doesn't exist.");
    }
}
