package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Path filePath = file.toPath();
        Map<String, String> keyToValueMap = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    keyToValueMap.put(key, value);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }

        return new Profile(
                keyToValueMap.get("Name"),
                Integer.parseInt(keyToValueMap.get("Age")),
                keyToValueMap.get("Email"),
                Long.parseLong(keyToValueMap.get("Phone"))
        );
    }
}
