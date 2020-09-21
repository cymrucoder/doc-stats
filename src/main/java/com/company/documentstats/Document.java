package com.company.documentstats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Document {

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
}
