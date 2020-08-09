package com.sda.ticketing.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class SessionDto {
    @NonNull
    private String sessionName;

    @NonNull
    private Long startTime;

    @NonNull
    private Long endTime;

    @NonNull
    private Long sessionDate;

    @NonNull
    private String churchId;

    private Integer seatsAvailable;
}
