package com.project.Assesment.exceptionHandler;

public class NotEnoughBalanceException extends RuntimeException{

    public NotEnoughBalanceException(String message){
        super(message);
    }

}
