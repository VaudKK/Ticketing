package com.sda.ticketing.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class BookingDto {
    @NonNull
    private String userId;

    @NonNull
    private String churchId;

    @NonNull
    private Long bookingDate = 0L;

    @NonNull
    private String seatId;

    @NonNull
    private String sessionId;

}

