package com.company.documentstats;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Document {

    private static final DecimalFormat roundingFormat = new DecimalFormat("0.0");
    String filePath;

    public Document(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Counts number of lines in the given file.
     * @return int value of number of lines
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int getLineCount() throws FileNotFoundException, IOException {
        int lineCount = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            while ((br.readLine()) != null) {
                lineCount++;
            }
        }

        return lineCount;
    }

    /**
     * Calculates the average length of a word in the given file.
     * @return String of the average word length, rounded to one decimal place
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String getAverageWordLength() throws FileNotFoundException, IOException {
        int charCount = getCharacterCount();
        int wordCount = getWordCount();

        if (0 == wordCount) {
            return "0.0";// Avoid dividing by 0 if the file is empty
        }

        float averageWordLength = (float) charCount / (float) wordCount;

        return roundingFormat.format(averageWordLength);
    }

    /**
     * Counts the number of whitespace delimited words in the given file.
     * @return int value of the number of words
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int getWordCount() throws FileNotFoundException, IOException {
        int wordCount = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();// Trim whitespace otherwise the split below will create extra elements before or after surrounding whitespace
                if (!line.isEmpty()) {// An empty line still splits into an array with one element, so skip these
                    wordCount += line.split("\\s+").length;
                }
            }
        }

        return wordCount;
    }

    /**
     * Find the most common letter in the given file, case insensitive.
     * Note that only letters are included, as defined by the inbuilt Java Character.isLetter(char)
     * Other characters such as symbols are not included
     * @return char of the most common letter, or null char if there is no most common letter
     * @throws FileNotFoundException
     * @throws IOException
     */
    public char getMostCommonLetter() throws FileNotFoundException, IOException {
        Map<Character, Integer> letters = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char ch = Character.toLowerCase(line.charAt(i));
                    if (Character.isLetter(ch)) {
                        if (!letters.containsKey(ch)) {
                            letters.put(ch, 1);
                        } else {
                            letters.put(ch, letters.get(ch) + 1);
                        }
                    }
                }
            }
        }

        if (letters.isEmpty()) {// Given text contained no letters so return null char
            return Character.MIN_VALUE;
        }

        char mostCommonCharacter = Character.MIN_VALUE;
        int mostOccurences = 0;

        for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
            if (entry.getValue() > mostOccurences) {
                mostCommonCharacter = entry.getKey();
                mostOccurences = entry.getValue();
            }
        }

        return mostCommonCharacter;
    }

    /**
     * Count the number of characters in the given file.
     * @return int of the number of characters
     * @throws FileNotFoundException
     * @throws IOException
     */
    private int getCharacterCount() throws FileNotFoundException, IOException {
        int charCount = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    charCount += line.replaceAll("\\s+", "").length();
                }
            }
        }

        return charCount;
    }
}
