package com.peter.gas;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MinderGasReadingMapper {

    public static MinderGasReading fromCsvLine(String line) {
        String[] split = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(split[0], formatter);
        BigDecimal reading = new BigDecimal(split[1]);

        return MinderGasReading.builder()
                .time(dateTime)
                .reading(reading)
                .build();
    }

    public static String toApiReading(MinderGasReading reading) {
        // todo: 3 digits
        return reading.getReading().toString();
    }

    public static String toApiTime(MinderGasReading reading) {
        DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return reading.getTime().format(iso);
    }

    public static String toApiJson(MinderGasReading reading) {
        var apiTime = MinderGasReadingMapper.toApiTime(reading);
        var apiReading = MinderGasReadingMapper.toApiReading(reading);
        return String.format("{ \"date\": \"%s\", \"reading\": %s }", apiTime, apiReading);
    }
}
