package com.company.documentstats;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stats {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: \"java -jar DocumentStats-1.0.jar [file]\"");
        } else {
            try {
                Document doc = new Document(args[0]);                
                System.out.println("Statistics for file: " + args[0]);
                System.out.println("Word count: " + doc.getWordCount());
                System.out.println("Line count: " + doc.getLineCount());
                System.out.println("Average word length (1dp): " + doc.getAverageWordLength());
                
                PrintStream out = new PrintStream(System.out, true, "UTF-8");// This is the only statistic that might need to print non ASCII characters
                out.println("Most common letter: " + doc.getMostCommonLetter());
            } catch (IOException ex) {
                Logger.getLogger(Stats.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
