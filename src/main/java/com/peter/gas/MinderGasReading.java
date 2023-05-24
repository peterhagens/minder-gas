package com.peter.gas;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class MinderGasReading {
    private final LocalDateTime time;
    private final BigDecimal reading;

    public boolean isAtStartOfDay() {
        return time.getHour() == 0 && time.getMinute() == 0;
    }

    public boolean isAtEndOfDay() {
        return time.getHour() == 23 && time.getMinute() == 0;
    }
}
