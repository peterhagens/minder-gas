package com.peter.gas;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class MinderGasReadingMapperTest {

    @Test
    public void to() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("03-12-2022 00:00", formatter);

        var reading = MinderGasReading.builder()
                .time(dateTime)
                .reading(new BigDecimal("5081.045"))
                .build();

        assertEquals("5081.045", MinderGasReadingMapper.toApiReading(reading));
        assertEquals("2022-12-03T00:00:00", MinderGasReadingMapper.toApiTime(reading));
    }

    @Test
    public void from() {
        String input = "03-12-2022 02:00,5081.404";
        MinderGasReading minderGasReading = MinderGasReadingMapper.fromCsvLine(input);

        assertEquals("2022-12-03T02:00:00", MinderGasReadingMapper.toApiTime(minderGasReading));
        assertEquals("5081.404", MinderGasReadingMapper.toApiReading(minderGasReading));
    }

}