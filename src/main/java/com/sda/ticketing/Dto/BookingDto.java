package com.sda.ticketing.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class BookingDto {
    @NonNull
    private String userId;

    @NonNull
    private Long attendanceDateTime = 0L;

    @NonNull
    private String churchId;

    @NonNull
    private String seatId;

    @NonNull
    private String sessionId;

}

