package com.app.BankingApplication.Exceptions;

public class InvalidDateRangeException extends RuntimeException{

    public InvalidDateRangeException(String message){
        super(message);
    }
}
