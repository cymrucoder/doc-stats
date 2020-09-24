package com.company.documentstats;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentTest {

    Document document;
    Document emptyDocument;
    Document multipleCasesDocument;
    Document chineseTextDocument;

    public DocumentTest() {
        document = new Document(getClass().getClassLoader().getResource("testdoc.txt").getPath());// Test file is lorem ipsum split into separate lines
        emptyDocument = new Document(getClass().getClassLoader().getResource("emptydoc.txt").getPath());// Test file is empty
        multipleCasesDocument = new Document(getClass().getClassLoader().getResource("casetestdoc.txt").getPath());// Test file is empty
        chineseTextDocument = new Document(getClass().getClassLoader().getResource("chinesetext.txt").getPath());// Test file is empty
    }

    @Test
    public void testCountLines_whenFileExists_shouldReturnCorrectNumber() throws IOException {
        assertEquals(6, document.getLineCount());
    }

    @Test
    public void testCountLines_whenFileEmpty_shouldReturnCorrectNumber() throws IOException {
        assertEquals(0, emptyDocument.getLineCount());
    }

    @Test
    public void testCountLines_whenFileDoesntExist_shouldThrowException() throws IOException {
        Document documentThatDoesntExist = new Document("filethatdoesntexist");
        assertThrows(FileNotFoundException.class, () -> {
            documentThatDoesntExist.getLineCount();
        }, "Exception not thrown when file doesn't exist.");
    }

    @Test
    public void testAverageWordLength_whenFileExists_shouldReturnCorrectNumber() throws IOException {
        assertEquals("5.5", document.getAverageWordLength());// 377 characters / 69 words
    }

    @Test
    public void testAverageWordLength_whenFileEmpty_shouldReturnCorrectNumber() throws IOException {
        assertEquals("0.0", emptyDocument.getAverageWordLength());// 0 characters or words, so the average is 0
    }

    @Test
    public void testWordCount_whenFileExists_shouldReturnCorrectNumber() throws IOException {
        assertEquals(69, document.getWordCount());// 69 words
    }

    @Test
    public void testWordCount_whenFileEmpty_shouldReturnCorrectNumber() throws IOException {
        assertEquals(0, emptyDocument.getWordCount());
    }

    @Test
    public void testMostCommonLetter_whenFileExists_shouldReturnCorrectLetter() throws IOException {
        assertEquals('i', document.getMostCommonLetter());// i appears 42 times
    }

    @Test
    public void testMostCommonLetter_whenFileEmpty_shouldReturnNullChar() throws IOException {
        assertEquals(Character.MIN_VALUE, emptyDocument.getMostCommonLetter());
    }

    @Test
    public void testMostCommonLetter_whenDifferentCases_shouldReturnCorrectLetter() throws IOException {
        assertEquals('e', multipleCasesDocument.getMostCommonLetter());// e appears 3 times in each case so in total is most common
    }

    @Test
    public void testMostCommonLetter_whenChineseText_shouldReturnCorrectLetter() throws IOException {
        assertEquals('聞', chineseTextDocument.getMostCommonLetter());
    }
}
