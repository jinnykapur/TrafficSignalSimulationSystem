package com.traffic.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    
    public static void ensureLogDirectory() {
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }
    
    public static void writeToFile(String filename, String content) {
        ensureLogDirectory();
        
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(content + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return lines;
    }
}