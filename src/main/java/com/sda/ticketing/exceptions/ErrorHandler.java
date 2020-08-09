package com.sda.ticketing.exceptions;

public class ErrorHandler {
    public static void handleError(Throwable t){
        t.printStackTrace();
    }
}
