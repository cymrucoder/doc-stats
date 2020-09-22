package com.company.documentstats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Document {

    private static final DecimalFormat roundingFormat = new DecimalFormat("0.0");
    String filePath;

    public Document(String filePath) {
        this.filePath = filePath;
    }


    public int getLineCount() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int lineCount = 0;

        while ((br.readLine()) != null) {
            lineCount++;
        }

        br.close();

        return lineCount;
    }

    public String getAverageWordLength() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
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

    public int getWordCount() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int wordCount = 0;

        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();// Trim for the same reason as average word length method
            if (!line.isEmpty()) {
                wordCount += line.split("\\s+").length;
            }
        }

        return wordCount;
    }

    public String getMostCommonLetter() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        Map<Character, Integer> letters = new HashMap<>();

        String line;

        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (Character.isLetter(ch)) {
                    if (!letters.containsKey(ch)) {
                        letters.put(ch, 1);
                    } else {
                        letters.put(ch, letters.get(ch) + 1);
                    }
                }
            }
        }

        if (letters.isEmpty()) {// Given text contained no letters
            return "";
        }

        char mostCommonCharacter = 0;
        int mostOccurences = 0;

        for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
            if (entry.getValue() > mostOccurences) {
                mostCommonCharacter = entry.getKey();
                mostOccurences = entry.getValue();
            }
        }

        return "" + mostCommonCharacter;
    }
}
