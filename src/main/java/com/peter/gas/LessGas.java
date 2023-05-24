package com.peter.gas;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LessGas {


    public static void main(String[] args) throws IOException {

        var path = Paths.get("/Users/peterhagens/Downloads/Temp/", "Meterstanden_Gas_042023.csv");
        List<String> list = Files.readAllLines(path);
        list.remove(0); // remove header

        list.forEach(line -> {
            MinderGasClient minderGasClient = new MinderGasClient();
            var reading = MinderGasReadingMapper.fromCsvLine(line);

            if(reading.isAtEndOfDay()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                minderGasClient.sendReading(reading);
            }
        });
    }
}
