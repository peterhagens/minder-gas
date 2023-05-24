package com.peter.gas;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Properties;

public class MinderGasClient {

    private final HttpClient httpClient;
    private String apiKey;

    public MinderGasClient() {
        // init
        httpClient = HttpClient.newHttpClient();


        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("config.properties").getFile());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
            apiKey = properties.getProperty("minderGas.key");
            System.out.println("API Key: " + apiKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendReading(MinderGasReading reading) {
        var jsonBody = MinderGasReadingMapper.toApiJson(reading);
        System.out.println("Sending: " + jsonBody);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://www.mindergas.nl/api/meter_readings"))
                    .header("Content-Type", "application/json")
                    .header("AUTH-TOKEN",apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response " + response.statusCode() + ": " + response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
