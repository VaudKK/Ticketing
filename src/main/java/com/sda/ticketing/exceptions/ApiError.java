package com.sda.ticketing.exceptions;

import com.sda.ticketing.exceptions.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private HttpStatus status;
    private ErrorType errorType;
    private String message;
    private String error;
    private String developerMessage;
}