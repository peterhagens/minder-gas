package com.peter.gas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiKeyLoader {
    public static void main(String[] args) {
        Properties properties = new Properties();
        ClassLoader classLoader = ApiKeyLoader.class.getClassLoader();
        File file = new File(classLoader.getResource("config.properties").getFile());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
            String apiKey = properties.getProperty("minderGas.key");
            System.out.println("API Key: " + apiKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
