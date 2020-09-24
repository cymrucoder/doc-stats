package com.company.documentstats;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        int lineCount = 0;

        while ((br.readLine()) != null) {
            lineCount++;
        }

        br.close();

        return lineCount;
    }

    /**
     * Calculates the average length of a word in the given file.
     * @return String of the average word length, rounded to one decimal place
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String getAverageWordLength() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        int charCount = 0;
        int wordCount = 0;

        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();// Trim whitespace otherwise the split below will create extra elements before or after surrounding whitespace
            if (!line.isEmpty()) {// An empty line still splits into an array with one element, so skip these
                wordCount += line.split("\\s+").length;
                charCount += line.replaceAll("\\s+", "").length();
            }
        }

        br.close();

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
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        int wordCount = 0;

        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();// Trim for the same reason as average word length method
            if (!line.isEmpty()) {
                wordCount += line.split("\\s+").length;
            }
        }

        br.close();

        return wordCount;
    }

    /**
     * Find the most common letter in the given file, case insensitive.
     * @return char of the most common letter, or null char if there is no most common letter
     * @throws FileNotFoundException
     * @throws IOException
     */
    public char getMostCommonLetter() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        Map<Character, Integer> letters = new HashMap<>();

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

        br.close();

        if (letters.isEmpty()) {// Given text contained no letters so return null char
            return Character.MIN_VALUE;
        }

        char mostCommonCharacter = 0;
        int mostOccurences = 0;

        for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
            if (entry.getValue() > mostOccurences) {
                mostCommonCharacter = entry.getKey();
                mostOccurences = entry.getValue();
            }
        }

        return mostCommonCharacter;
    }
}
