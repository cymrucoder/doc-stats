package com.company.documentstats;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentTest {

    Document document;
    
    public DocumentTest() {
        document = new Document();
    }

    @Test
    public void testRead() {
        File file = new File(getClass().getClassLoader().getResource("testdoc.txt").getFile());
        assertTrue(file.exists());
    }

}
